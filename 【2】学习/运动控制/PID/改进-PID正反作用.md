# [PID控制器改进笔记之三：改进PID控制器之正反作用](https://www.cnblogs.com/foxclever/p/12731042.html)

　　前面我们发布了一系列PID控制器相关的文章，包括经典PID控制器以及参数自适应的PID控制器。这一系列PID控制器虽说实现了主要功能，也在实际使用中取得了良好效果，但还有很多的细节部分可以改进以提高性能和灵活性。所以在这篇中我们来讨论改进PID控制器以实现正反作用转换。

## 问题

　　到目前为止我们讨论的情况都是在增大输出而控制变量也随之增加的情况，而实际的情况并非完全如此。有些系统当我们增加输出时，控制变量会随之减小，这就是我们在此要讨论的问题。在前面的PID控制器开发中我们并未考虑这一问题，为了增强PID控制器使用的适应性和方便性，我们需要添加上控制PID输出方向的功能，这就是所说的正反作用控制。

　　事实上，PID控制器分为正作用和反作用两种，这本身并没有什么本质上的区别。但需要使用者自己清楚，是希望输出增加时输入也增加还是输出增加时输入减小，并以此来确定控制器的正反作用。

## 设计

　　我们要添加上正反作用的功能该如何实现呢？首先我们需要明白，所谓正作用实际就是输出增加输入值也随之增加的控制方式；而反作用就是输出增加输入值随之减小的控制方式。一个系统选择什么样的作用方式是由系统本身的特性决定的，这包括被控对象的属性，执行机构的操作特性等。换句话说我们没办法要通过对输入的处理实现作用方式，但可以从输出的角度来实现我们的要求。

　　首先，我们需要为PID对象添加一个描述正反作用的属性。这个属性的取值决定了控制器对象采用何种作用方式。

```c
/*定义正反作用枚举类型*/
typedef enum ClassicPIDDR {
  DIRECT,       //正作用
  REVERSE       //反作用
}ClassicPIDDRType;

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

  ClassicPIDDRType direct;      //正反作用
}CLASSICPID;
```

　　其次，我们要在初始化对象时，对这一属性进行初始化。至于初始化的值，就需要根据实际使用需求选择枚举。

　　最后还需要在PID控制器中实现这部分的操作。实现这一操作的方式有两种。一种方法是将PID的三个参数取反变为负值，这样就可以实现反作用。第二种方法是我们正常使用PID的三个参数计算而将增量部分取反，这样也可实现反作用。显然第一种方法便于理解，而第二种方法更便于操作，这里我们使用第二种方法来实现。

## 实现

　　我们已经设计好使用参数正常计算增量，在正作用时将增量取加，而在反作用时将增量取为减，这样就实现了正反作用的转换。这里所说的“加”与“减”是纯粹的数学运算，不用考虑增量本身的符号是正是负。正反作用与手自动转换的性质不同，并不需要在线修改，在系统确定后就已经确定，所以我们需要在初始化中设定它。而在PID控制器中我们根据正反作用这一属性的取值来决定对增量部分是做“加”运算还是“减”运算。据此，我们修改PID控制器为：

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

    vPID->preerror=vPID->lasterror;  //存放偏差用于下次运算
    vPID->lasterror=thisError;
    vPID->result=result;

    vPID->output=(vPID->result-vPID->minimum)/(vPID->maximum-vPID->minimum)*100.0;
    *vPID->pMV=vPID->output;
  }
}
```

## 总结

　　我们在PID控制器中增加了正反作用的处理，经验证与我们预期的结果是一致的。其实正反作用并不复杂，就是要在我们期望输入增加时，如何控制输出的方向。如果输入输出的变化趋势一致，我们就需要采用正作用；如果输入输出的变化趋势相反，则我们就需要选择反作用。

　　关于如何确定一个控制器的正反作用，这里我们不妨举一个例子。我们设想我们需要控制一个水槽的液位，怎么样确定这个控制器的正反作用呢？我们说过，确定控制器的正反作用需要考虑被控对象的特性和执行机构的特性。对于这个例子，被控对象我们要考虑是入水口可控还是出水口可控，执行机构是常闭还是常开，所以有四种情况：

 ![img](https://gitee.com/tianzhendong/img/raw/master/images/202203281027252.png)

　　所谓常闭型和常开型是指在没有信号输入的时候，执行机构所处的状态。所以常开型给的信号越大开度越小，常闭型给的信号越大开度越大。当然，这只是一个简单的例子，实际使用中会更复杂，但都可从被控对象和执行机构的特性入手来选取控制器的正反作用。