# [PID控制器改进笔记之一：改进PID控制器之参数动态调整](https://www.cnblogs.com/foxclever/p/12537507.html)

　　前面我们发布了一系列PID控制器相关的文章，包括经典PID控制器以及参数自适应的PID控制器。这一系列PID控制器虽说实现了主要功能，也在实际使用中取得了良好效果，但还有很多的细节部分可以改进以提高性能和灵活性。所以在这篇中我们来讨论改进PID控制器以实现动态调整参数的目的。

## 提出问题

　　在我们一开始开发PID控制器时，我们主要是关注于其算法的实现而没有过多的关心其使用过程。但在我们的使用过程中发现有些不够灵活的地方。

　　在原有的PID控制器中，设定值是通过在外部给PID对象的参数赋值实现的，虽然说并不影响使用，但我们若想对PID控制器中的参数设定值进行某些处理就不是很方便了。而在原有的PID控制器中，输出值在外部是不可见的，只能通过PID对象查看且不可更改。这些使得对这些参数的操作显得不够灵活。

　　而且在原有的PID控制器中3个调节参数也不能在外部随时调整，这显然不符合很多应用的需要，因为PID参数的调整是很常见的工作。所以在这篇中我们来考虑实现这些参数的动态调整。

## 分析设计

　　为了使得PID控制器使用起来更为灵活，我们需要将PID对象作必要的改动。关于PID对象我们考虑将测量值、设定值、输出值作为对象的属性。但我们不是直接将这几个变量作为对象属性，因为这样达不到我们从外部灵活操作的目的，我们将几个指向浮点变量的指针作为对象的属性，而初始化后这几个指针将指向我们的测量值、设定值、输出值变量。

　　同样的三个PID参数我们想要在外部修改它，我们也将其在外部定义为变量，而在PID对象中定义为指向这三个变量的浮点数指针。在对对象进行初始化时，我们将变量地址赋值给这几个指针。据此我们定义PID对象类型为：

```c
/*定义结构体和公用体*/
typedef struct CLASSIC
{
  float *pPV;                   //测量值指针
  float *pSV;                   //设定值指针
  float *pMV;                   //输出值指针
  float *pKp;                   //比例系数指针
  float *pKi;                   //积分系数指针
  float *pKd;                   //微分系数指针

  float setpoint;               //设定值
  float lasterror;              //前一拍偏差
  float preerror;               //前两拍偏差
  float deadband;               //死区
  float result;                 //PID控制器计算结果
  float output;                 //输出值0-100%
  float maximum;                //输出值上限
  float minimum;                //输出值下限
  float errorabsmax;            //偏差绝对值最大值
  float errorabsmin;            //偏差绝对值最小值
  float alpha;                  //不完全微分系数
  float deltadiff;              //微分增量
  float integralValue;          //积分累计量
  float gama;                   //微分先行滤波系数
  float lastPv;                 //上一拍的过程测量值
  float lastDeltaPv;            //上一拍的过程测量值增量
}CLASSICPID;
```

## 软件实现

　　我们计划将PID参数和过程变量改成指向浮点型变量的指针，那么代码上需要做哪些修改呢？需要修改的主要是两个函数：PID调节函数和PID对象初始化函数。

　　首先，我们来看一看PID对象的初始化函数。我们知道将这些变量修改为指向浮点变量法的指针后，我们就必须在初始化时指定具体的变量地址，否则指向的将是不可预知的位置。所以我们修改初始化函数如下：

```c
/* PID初始化操作,需在对vPID对象的值进行修改前完成 */
void PIDParaInitialization(CLASSICPID *vPID,    //PID控制器对象
                           float *pPV,          //测量值指针
                           float *pSV,          //设定值指针
                           float *pMV,          //输出值指针
                           float *pKp,          //比例系数指针
                           float *pKi,          //积分系数指针
                           float *pKd,          //微分系数指针
                           float vMax,          //控制变量量程
                           float vMin,          //控制变量的零点
                             )
{
  if((vPID==NULL)||(pPV==NULL)||(pSV==NULL)||(pMV==NULL)||(pKp==NULL)||(pKi==NULL)||(pKd==NULL))
  {
    return;
  }
  vPID->pPV=pPV;
  vPID->pSV=pSV;
  vPID->pMV=pMV;
  vPID->pKp=pKp;
  vPID->pKi=pKi;
  vPID->pKd=pKd;

  vPID->maximum=vMax;                /*输出值上限*/
  vPID->minimum=vMin;                /*输出值下限*/

  vPID->setpoint=*pPV;               /*设定值*/

  vPID->lasterror=0.0;              /*前一拍偏差*/
  vPID->preerror=0.0;               /*前两拍偏差*/
  vPID->result=vMin;                /*PID控制器结果*/
  vPID->output=0.0;                 /*输出值，百分比*/

  vPID->errorabsmax=(vMax-vMin)*0.8;
  vPID->errorabsmin=(vMax-vMin)*0.2;

  vPID->deadband=(vMax-vMin)*0.0005;               /*死区*/
  vPID->alpha=0.2;                  /*不完全微分系数*/
  vPID->deltadiff=0.0;                /*微分增量*/

  vPID->integralValue=0.0;

  vPID->mode=mode;
}
```

　　其次，我们还需要修改PID调节函数。在原来的PID调节器中过程值是作为函数的参数输入的，而且PID参数是作为变量存在于对象内部的，所以要针对这两个方面做相应的修改：

```c
/* 通用PID控制器,采用增量型算法，具有变积分，梯形积分和抗积分饱和功能，微分项采用不完全微分，一阶滤波，alpha值越大滤波作用越强                    */
void PIDRegulator(CLASSICPID *vPID)
{
  float thisError;
  float result;
  float factor;
  float increment;
  float pError,dError,iError;

  vPID->setpoint=*vPID->pSV;
  thisError=vPID->setpoint-(*vPID->pPV); //得到偏差值
  result=vPID->result;
  if (fabs(thisError)>vPID->deadband)
  {
    pError=thisError-vPID->lasterror;
    iError=(thisError+vPID->lasterror)/2.0;
    dError=thisError-2*(vPID->lasterror)+vPID->preerror;

    //变积分系数获取
     factor=VariableIntegralCoefficient(thisError,vPID->errorabsmax,vPID->errorabsmin);

    //计算微分项增量带不完全微分
      vPID->deltadiff=(*vPID->pKd)*(1-vPID->alpha)*dError+vPID->alpha*vPID->deltadiff;

      increment=(*vPID->pKp)*pError+(*vPID->pKi)*factor*iError+vPID->deltadiff;   //增量计算
    }
    else
    {
      if((fabs(vPID->setpoint-vPID->minimum)<vPID->deadband)&&(fabs((*vPID->pPV)-vPID->minimum)<vPID->deadband))
      {
        result=vPID->minimum;
      }
      increment=0.0;
    }

      result=result+increment;

    /*对输出限值，避免超调和积分饱和问题*/
    if(result>=vPID->maximum)
    {
      result=vPID->maximum;
    }
    if(result<=vPID->minimum)
    {
      result=vPID->minimum;
    }

    vPID->preerror=vPID->lasterror;  //存放偏差用于下次运算
    vPID->lasterror=thisError;
    vPID->result=result;

    vPID->output=(vPID->result-vPID->minimum)/(vPID->maximum-vPID->minimum)*100.0;

      *vPID->pMV=vPID->output;
  }
}
```

## 总结

　　我们将PID参数和过程变量都改为了对象所包含的指针，这样当我们从上位机或者其他进程修改变量的值时，也同步修改了PID对象中的值。测试的结果比原来的方式操作更为方便。