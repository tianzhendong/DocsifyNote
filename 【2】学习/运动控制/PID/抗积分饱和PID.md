# 抗积分饱和PID

## 前言

> 积分是 PID 控制器的一个环节，它的作用是为了消除单纯的比例控制所带来的系统静差，使控制更精准。当积分作用不断累加，执行机构达到极限时，控制器的输出 Uout 还在继续增大，但是执行机构不可能无限增大，这个时候控制器输出超出了正常范围，也就是进入了饱和区。

当系统出现反向偏差时，输出量 Uout 逐渐退出饱和区，但是进入饱和区的时间越久，退出饱和区所需的时间就越长，控制器退出饱和区这段时间不能快速的响应外部输入控制量，因此影响系统性能，这种现象工程上称为积分饱和。

## 解决

有很多解决积分饱和的方法，常见的一种方法是在计算输出量 Uout(k) 时，首先判断上一时刻的输出量 Uout(k-1) 是否已经超出了限制范围，如果输出量 Uout(k-1) 超出了限制范围的最大值，那么控制器只累加负偏差；如果输出量 Uout(k-1) 超出了限制范围的最小值，那么控制器只累加正偏差，这样能有效避免 PID 控制器的输出长时间滞留饱和区，提高控制器的响应能力。

![img](https://gitee.com/tianzhendong/img/raw/master/images/202203240933843.png)

## 实现

### 位置型

对于位置型PID的抗积分饱和算法其实就是在基本的PID基础上加上抗积分饱和的操作，增加量个机锋的极限值。

- 首先定义PID对象的结构体：

```c
/*定义结构体和公用体*/

typedef struct

{

  float setpoint;       //设定值

  float proportiongain;     //比例系数

  float integralgain;      //积分系数

  float derivativegain;    //微分系数

  float lasterror;     //前一拍偏差

  float result; //输出值

  float integral;//积分值

  float maximum;//最大值

  float minimum;//最小值

}PID;
```

- 实现PID控制器

```c
void PIDRegulation(PID *vPID, float processValue)

{

  float thisError;

  thisError=vPID->setpoint-processValue;

  if(vPID->result>vPID->maximum)

  {

    if(thisError<=0)

    {

      vPID->integral+=thisError;

    }

  }

  else if(vPID->result<vPID->minimum)

  {

    if(thisError>=0)

    {

      vPID->integral+=thisError;

    }

  }

  else

  {

    vPID->integral+=thisError;

  }



  vPID->result=vPID->proportiongain*thisError+vPID->integralgain*vPID->integral+vPID->derivativegain*(thisError-vPID->lasterror);

  vPID->lasterror=thisError;

}
```

### 增量型

增量型PID的抗积分饱和的实现也是一样在最基本的增量型PID算法中引入极大极小的限值，并在算法中通过比较限值实现抗饱和的操作。

- 首先定义PID对象的结构体：

```c
/*定义结构体和公用体*/

typedef struct

{

  float setpoint;       //设定值

  float proportiongain;     //比例系数

  float integralgain;      //积分系数

  float derivativegain;    //微分系数

  float lasterror;     //前一拍偏差

  float preerror;     //前两拍偏差

  float deadband;     //死区

  float result; //输出值

  float maximum;//最大值

  float minimum;//最小值

}PID;
```

- 实现PID

```c
void PIDRegulation(PID *vPID, float processValue)
{
  float thisError;
  float increment;
  float pError,dError,iError;
  thisError=vPID->setpoint-processValue; //得到偏差值
  pError=thisError-vPID->lasterror;
  iError=0;
  dError=thisError-2*(vPID->lasterror)+vPID->preerror;

  if(vPID->result>vPID->maximum)
  {
    if(thisError<=0)
    {
      iError=thisError;
    }
  }

  else if(vPID->result<vPID->minimum)
  {
    if(thisError>=0)
    {
      iError=thisError;
    }
  }
  else
  {
    iError=thisError;
  }

  increment=vPID->proportiongain*pError+vPID->integralgain*iError+vPID->derivativegain*dError;   //增量计算

  vPID->preerror=vPID->lasterror;  //存放偏差用于下次运算

  vPID->lasterror=thisError;

  vPID->result+=increment;

}
```





## 参考

[PID控制器开发笔记之三：抗积分饱和PID控制器的实现](https://www.cnblogs.com/foxclever/p/8995308.html)

[PID 控制器抗积分饱和模型与算法](https://zhuanlan.zhihu.com/p/372357040)

