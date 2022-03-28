# [PID控制器改进笔记之二：改进PID控制器之手自动切换](https://www.cnblogs.com/foxclever/p/12631224.html)

　　前面我们发布了一系列PID控制器相关的文章，包括经典PID控制器以及参数自适应的PID控制器。这一系列PID控制器虽说实现了主要功能，也在实际使用中取得了良好效果，但还有很多的细节部分可以改进以提高性能和灵活性。所以在这篇中我们来讨论改进PID控制器以实现手自动的方便切换。

## 问题

　　PID控制器的效果是众所周知的，但有些时候我们希望强制输出某个值以查看执行机构的响应，或者有些时候我们希望直接指定执行机构的行为而不需要它随时调整，在这些情况下我们该怎么做呢？

　　这个时候我们需要说明两个定义。当使用PID控制器自动调节时，我们称之为自动操作；而如上述情况下我们直接指定控制器的输出时，或者说直接指定执行机构的行为时，我们称之为手动。很显然为了实现前述的相应操作，我们需要为PID控制器添加上手自动转换功能。在这一篇中我们就来讨论这个问题。

## 设计

　　现在我们需要考虑怎么来实现手自动转换功能。首先我们需要在PID对象中添加一个属性，这个属性用于标识PID控制器究竟是处于手动模式还是自动模式。为了方便在外部修改PID对象的手自动模式，我们设计一个指向uint16_t的指针类型。所以我们设计PID对象如下：

```c
/*定义PID对象类型*/
typedef struct CLASSIC
{
  float *pPV;                   //测量值指针
  float *pSV;                   //设定值指针
  float *pMV;                   //输出值指针
  float *pKp;                   //比例系数指针
  float *pKi;                   //积分系数指针
  float *pKd;                   //微分系数指针
  uint16_t *pMA;                //手自动操作指针

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

　　对于手自动操作，我们需要通过外部变量来赋值外，在初始化时我们将其默认初始化为自动模式，毕竟我们使用PID控制器的目的不是为了手动操作它。

## 实现

　　我们为PID对象添加了手自动操作属性，那么根据这个属性我们需要对PID控制器进行哪些修改呢？我们考虑一下在自动状态下和手动状态下都需要做什么工作。

　　在自动状态下没有什么需要做的，就是让PID控制器正常输出就可以了。而在手动状态下，我们要让PID控制器的输出为我们人为给定的输出。仅仅如此，自然是不行的。我们考虑一下，PID控制器由自动转为手动时，可能会给系统有什么影响。事实上，我们由自动转为手动时，只要不修改输出，整个系统的状态不会发生变化。但由手动切换到自动时，由于我们修改了输出值，测量值也会跟着发生变化，在原有设定值的情况下，由手动转为自动时必然会出现阶跃干扰，为了避免这种情况我们在手动状态下让设定值跟随到测量值以达到无扰动切换。据此，我们修改PID控制器代码为：

```c
/* 通用PID控制器,采用增量型算法，具有变积分，梯形积分和抗积分饱和功能，微分项采用不完全微分，一阶滤波，alpha值越大滤波作用越强                    */
void PIDRegulator(CLASSICPID *vPID)
{
  float thisError;
  float result;
  float factor;
  float increment;
  float pError,dError,iError;

  if(*vPID->pMA<1)      //手动模式
  {
    vPID->output=*vPID->pMV;
    //设置无扰动切换
    vPID->result=(vPID->maximum-vPID->minimum)*vPID->output/100.0+-vPID->minimum;
    *vPID->pSV=*vPID->pPV;
    vPID->setpoint=*vPID->pSV;
  }
  else                  //自动模式
  {
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

　　我们添加了手自动转换功能，并在手动转为自动时，做了无扰动切换的预置。经测试效果与我们的预期一致。当我们将PID控制器置为自动状态时，我们通过修改设定值（SV）来实现对系统的影响。而当我们将PID控制器置为手动状态时，我们通过修改输出值（MV）来实现对系统的影响。