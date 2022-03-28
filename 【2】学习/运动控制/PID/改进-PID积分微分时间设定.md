# [PID控制器改进笔记之六：改进PID控制器之参数设定](https://www.cnblogs.com/foxclever/p/15866293.html)

  前面我们发布了一系列PID控制器相关的文章，包括经典PID控制器以及参数自适应的PID控制器。这一系列PID控制器虽说实现了主要功能，也在实际使用中取得了良好效果，但还有很多的细节部分可以改进以提高性能和灵活性。这篇中我们来讨论改进PID控制器参数设置的问题。

## 问题提出

  在前面的文章中我们曾推导过PID控制器的公式，并且对其进行了离散化以适用于程序实现，具体的离散化公式如下：

![img](https://gitee.com/tianzhendong/img/raw/master/images/202203281034475.png)

  在编写程序时，我们将比例项的系数设定为Kp、积分项的系数设定为Ki、微分项的系数设定为Kd，其中：

![img](https://gitee.com/tianzhendong/img/raw/master/images/202203281034374.png)

  这其中T是采样周期，Ti是积分时间，Td是微分时间。所以在设置参数的时候我们需要先去顶比例系数Kp，然后在根据采样周期和积分微分时间来计算Ki和Kd。这么做虽然是公式变得简单了，但与我们传统的参数设置相比就显得不那么直观，所以有些使用者希望还是以传统的比例带PB、积分时间Ti、微分时间Td来配置相应的参数，这一篇中就来分析并解决这个问题。

## 分析设计

  对于上述这个问题，我们需要搞清楚Kp、Ki、Kd与PB、Ti、Td之间的关系。事实上，它们之间的关系并不复杂。首先比例系数Kp与比例带之间是互为倒数的关系，所以我们知道了其中一个就可以得到另一个。而Ti和Ki的关系以及Td和Kd的关系我们前面已经给出了。

接下来我们需要做的事，实际上就是让我们的PID控制器在不同的应用需求下呈现出不同的参数设置就可以设置不同的参数形式了。

## 软件实现

  我们已经分析了需要实现的内容，接下来我们就来考虑怎么实现。关于这一点，我们考虑我们的PID控制器的设计形式，需要修改的主要是三个方面的内容。第一个需要修改的地方就是PID控制器对象的定义。我们定义一个宏来实现条件编译，以实现在不同的需求下实现不同的参数定义，所以我们实现PID控制器的对象类型定义如下：

```C
/*定义PID对象类型*/
typedef struct CLASSIC
{
  float *pPV;          //测量值指针
  float *pSV;          //设定值指针
  float *pMV;          //输出值指针
  uint16_t *pMA;        //手自动操作指针
  
\#if PID_PARAMETER_STYLE > (0)
  float *pKp;          //比例系数指针
  float *pKi;          //积分系数指针
  float *pKd;          //微分系数指针
\#else
  float *pPb;          //比例带
  float *pTi;          //积分时间，单位为秒
  float *pTd;          //微分时间，单位为秒
  float ts;           //采样周期，单位为秒
\#endif
 
  float setpoint;        //设定值
  float lasterror;       //前一拍偏差
  float preerror;        //前两拍偏差
  float deadband;        //死区
  float result;         //PID控制器计算结果
  float output;         //输出值0-100%
  float maximum;        //输出值上限
  float minimum;        //输出值下限
  float errorabsmax;      //偏差绝对值最大值
  float errorabsmin;      //偏差绝对值最小值
  float alpha;         //不完全微分系数
  float deltadiff;       //微分增量
  float integralValue;     //积分累计量
  float gama;          //微分先行滤波系数
  float lastPv;         //上一拍的过程测量值
  float lastDeltaPv;      //上一拍的过程测量值增量
   
  ClassicPIDDRType direct;   //正反作用
  ClassicPIDSMType sm;     //设定值平滑
  ClassicPIDCSType cas;     //串级设定
  
}CLASSICPID;
```

  我们定义了对象类型，可以得到我们需要的对象变量，但这个对象变量需要初始化才能使用。所以第二个需要修改的地方就是PID控制器对象初始化函数。我们使用条件编译，在不同的应用需求下我们初始化不同的对象参数，具体实现如下：

```C
/* PID初始化操作,需在对vPID对象的值进行修改前完成               */
/* CLASSICPID vPID，普通PID对象变量，实现数据交换与保存            */
/* float vMax,float vMin，过程变量的最大最小值（量程范围）          */
void PIDParaInitialization(CLASSICPID *vPID,  //PID控制器对象
               float *pPV,     //测量值指针
               float *pSV,     //设定值指针
               float *pMV,     //输出值指针

\#if PID_PARAMETER_STYLE > (0)
               float *pKp,     //比例系数指针
               float *pKi,     //积分系数指针
               float *pKd,     //微分系数指针
\#else
               float *pPb;          //比例带
               float *pTi;          //积分时间
               float *pTd;          //微分时间
               float ts,      //采样周期，单位为秒
\#endif

               uint16_t *pMA,    //手自动操作指针
               float vMax,     //控制变量量程
               float vMin,     //控制变量的零点
               ClassicPIDDRType direct,   //正反作用
               ClassicPIDSMType sm,     //设定值平滑
               ClassicPIDCSType cas     //串级设定
                 )
{
  if((vPID==NULL)||(pPV==NULL)||(pSV==NULL)||(pMV==NULL)||(pMA==NULL))
  {
     return;
  }
  
  vPID->pPV=pPV;
  vPID->pSV=pSV;
  vPID->pMV=pMV;
  vPID->pMA=pMA;
  
\#if PID_PARAMETER_STYLE > (0)
  if((pKp==NULL)||(pKi==NULL)||(pKd==NULL))
  {
     return;
  }
  
  vPID->pKp=pKp;
  vPID->pKi=pKi;
  vPID->pKd=pKd;
  
  if(*vPID->pKp<=0.00001)
  {
     *vPID->pKp=1.0;       //比例系数
     *vPID->pKi=0.01;       //积分系数
     *vPID->pKd=0.01;       //微分系数
  }
\#else
  if((pPb==NULL)||(pTi==NULL)||(pTd==NULL))
  {
     return;
  }
  
  vPID->pPb=pPb;
  vPID->pTi=pTi;
  vPID->pTd=pTd;
  vPID->ts=ts;
  
  if(*vPID->pPb<=0.00001)
  {
     *vPID->pPb=1.0;       //比例带
     *vPID->pTi=1.0;       //积分时间，单位为秒
     *vPID->pTd=0.0001;     //微分时间，单位为秒
  }
\#endif
 
  vPID->maximum=vMax;      //控制变量的量程
  vPID->minimum=vMin;      //控制变量的零点
  
  *vPID->pSV=*pPV;       //设定值
  vPID->setpoint=*pPV;     //设定值
  *vPID->pMA=1;         //初始化为自动模式
 
  vPID->direct=direct;     //设定PID对象的正反作用
  vPID->cas=cas;        //设定是否启用串级
  vPID->sm=sm;         //设定是否启用设定值平滑

  if(vPID->cas==CASCADE)
  {
     vPID->sm=SMOOTH_DISABLE;
  }
  
  vPID->lasterror=0.0;     //前一拍偏差
  vPID->preerror=0.0;      //前两拍偏差
  vPID->result=vMin;      //PID控制器结果
  vPID->output=0.0;       //输出值，百分比
  *vPID->pMV=vPID->output;   //输出值，百分比
  
  vPID->errorabsmax=(vMax-vMin)*0.9;
  vPID->errorabsmin=(vMax-vMin)*0.1;
  vPID->deadband=(vMax-vMin)*0.001;   //死区
  
  vPID->alpha=0.2;   //不完全微分系数
  
  vPID->deltadiff=0.0;     //微分增量
  vPID->integralValue=0.0;
  
}
```

  第三个需要修改的是PID控制器对象的实现。在前面我们已经描述PB、Ti、Td与Kp、Ki、Kd之间的数学关系。为了方便处理，我们通过条件编译在不同应用需求下将参数均转化为统一的Kp、Ki、Kd来进行计算。具体的实现方式如下：

```C
/* 通用PID控制器,采用增量型算法，具有变积分，梯形积分和抗积分饱和功能     */
/* 微分项采用不完全微分，一阶滤波，alpha值越大滤波作用越强          */
/* CLASSICPID vPID，PID对象变量，实现数据交换与保存              */
/* float pv，过程测量值，对象响应的测量数据，用于控制反馈           */
void PIDRegulator(CLASSICPID *vPID)
{
  float thisError;
  float result;
  float factor;
  float increment;
  float pError,dError,iError;
  float kp,ki,kd;
  
\#if PID_PARAMETER_STYLE > (0)
  kp=*vPID->pKp;
  ki=*vPID->pKi;
  kd=*vPID->pKd;
\#else
  if((*vPID->pTi)<vPID->ts)
  {
     *vPID->pTi=vPID->ts;
  }
  kp=1/(*vPID->pPb);
  ki=kp*(vPID->ts/(*vPID->pTi));
  kd=kp*((*vPID->pTd)/vPID->ts);
\#endif
  
  if(*vPID->pMA<1)   //手动模式
  {
     vPID->output=*vPID->pMV;
     //设置无扰动切换
     vPID->result=(vPID->maximum-vPID->minimum)*vPID->output/100.0+vPID->minimum;
     *vPID->pSV=*vPID->pPV;
     vPID->setpoint=*vPID->pSV;
  }
  else          //自动模式
  {
     if(vPID->sm==SMOOTH_ENABLE) //设定值平滑变化
     {
       SmoothSetpoint(vPID);
     }
     else
     {
       if(vPID->cas==CASCADE)  //串级处理
       {
         vPID->setpoint=(vPID->maximum-vPID->minimum)*(*vPID->pSV)/100.0+vPID->minimum;
       }
       else
       {
         vPID->setpoint=*vPID->pSV;
       }
     }
     
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
       vPID->deltadiff=kd*(1-vPID->alpha)*dError+vPID->alpha*vPID->deltadiff;
       
       increment=kp*pError+ki*factor*iError+vPID->deltadiff;  //增量计算
     }
     else
     {
       if((fabs(vPID->setpoint-vPID->minimum)<vPID->deadband)&&(fabs((*vPID->pPV)-vPID->minimum)<vPID->deadband))
       {
         result=vPID->minimum;
       }
       increment=0.0;
     }
     
     //正反作用设定
     if(vPID->direct==DIRECT)
     {
       result=result+increment;
     }
     else
     {
       result=result-increment;
     }
     
     /*对输出限值，避免超调和积分饱和问题*/
     if(result>=vPID->maximum)
     {
       result=vPID->maximum;
     }
     if(result<=vPID->minimum)
     {
       result=vPID->minimum;
     } 
     
     vPID->preerror=vPID->lasterror; //存放偏差用于下次运算
     vPID->lasterror=thisError;
     vPID->result=result;
     
     vPID->output=(vPID->result-vPID->minimum)/(vPID->maximum-vPID->minimum)*100.0;
     *vPID->pMV=vPID->output;
  }
}
```

##  总结

  在这一篇中，我们只是为了实现不同使用者的需求，将PID控制器的参数定义做了相应的修改，而控制器本身的功能并没有什么变化。这样既保证原有的应用不会受到影响，新的应用也可以根据需要定义参数，使用Kp、Ki、Kd或是PB、Ti、Td由应用设计的需要选择。

  这里需要说一下，不论我们如何定义参数，采样周期的选择都需要认真考虑。即使我们采用相同的参数整定，当采样周期不同时，效果可能会有较大差异，所以在整定参数前应根据系统的特性采用合适的采样周期。