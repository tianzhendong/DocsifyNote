[toc]

# PID

## 基本pid

### 基本原理

PID算法的执行流程是非常简单的，即利用反馈来检测偏差信号，并通过偏差信号来控制被控量。而控制器本身就是比例、积分、微分三个环节的加和。其功能框图如下：

![image-20220324100424825](https://gitee.com/tianzhendong/img/raw/master/images/202203241004886.png)

PID控制律：

![img](https://gitee.com/tianzhendong/img/raw/master/images/202203240934428.png)

其中$K_p$为比例带，$T_i$为积分时间，$T_d$为微分时间。

#### 比例

假设我有一个水缸，最终的控制目的是要保证水缸里的水位永远的维持在1米的高度。假设初试时刻，水缸里的水位是0.2米，那么当前时刻的水位和目标水位之间是存在一个误差的error，且error为0.8.这个时候，假设旁边站着一个人，这个人通过往缸里加水的方式来控制水位。如果单纯的用比例控制算法，就是指加入的水量u和误差error是成正比的。即$u=k_p*error$

假设kp取0.5，那么t=1时（表示第1次加水，也就是第一次对系统施加控制），那么$u=0.5*0.8=0.4$，所以这一次加入的水量会使水位在0.2的基础上上升0.4，达到0.6

接着，t=2时刻（第2次施加控制），当前水位是0.6，所以error是0.4。$u=0.5*0.4=0.2$，会使水位再次上升0.2，达到0.8

如此这么循环下去，就是比例控制算法的运行方法。

可以看到，最终水位会达到我们需要的1米。

但是，单单的比例控制存在着一些不足，其中一点就是 –稳态误差！

像上述的例子，根据$k_p$取值不同，系统最后都会达到1米，不会有稳态误差。但是，考虑另外一种情况，假设这个水缸在加水的过程中，存在漏水的情况，假设每次加水的过程，都会漏掉0.1米高度的水。仍然假设$k_p$取0.5，那么会存在着某种情况，假设经过几次加水，水缸中的水位到0.8时，水位将不会再变换！！！因为，水位为0.8，则误差error=0.2. 所以每次往水缸中加水的量为u=0.5*0.2=0.1，同时，每次加水缸里又会流出去0.1米的水！！！加入的水和流出的水相抵消，水位将不再变化！！

也就是说，我的目标是1米，但是最后系统达到0.8米的水位就不在变化了，且系统已经达到稳定。由此产生的误差就是稳态误差了。

（在实际情况中，这种类似水缸漏水的情况往往更加常见，比如控制汽车运动，**摩擦阻力**就相当于是“漏水”，控制机械臂、无人机的飞行，**各类阻力和消耗**都可以理解为本例中的“漏水”）

所以，单独的比例控制，在很多时候并不能满足要求。

#### 积分

还是用上面的例子，如果仅仅用比例，可以发现存在暂态误差，最后的水位就卡在0.8了。于是，在控制中，我们再引入一个分量，该分量和误差的积分是正比关系。所以，比例+积分控制算法为：$u=k_p*error+k_i∗∫error$

还是用上面的例子来说明，第一次的误差error是0.8，第二次的误差是0.4，至此，误差的积分（离散情况下积分其实就是做累加），$∫error=0.8+0.4=1.2$. 这个时候的控制量，除了比例的那一部分，还有一部分就是一个系数$k_i$乘以这个积分项。由于这个积分项会将前面若干次的误差进行累计，所以可以很好的消除稳态误差（假设在仅有比例项的情况下，系统卡在稳态误差了，即上例中的0.8，由于加入了积分项的存在，会让输入增大，从而使得水缸的水位可以大于0.8，渐渐到达目标的1.0，这就是积分项的作用。

#### 微分

换一个另外的例子，考虑刹车情况。平稳的驾驶车辆，当发现前面有红灯时，为了使得行车平稳，基本上提前几十米就放松油门并踩刹车了。当车辆离停车线非常近的时候，则使劲踩刹车，使车辆停下来。整个过程可以看做一个加入微分的控制策略。

微分，说白了在离散情况下，就是error的差值，就是t时刻和t-1时刻error的差，即$u=k_d*(error(t)-error(t-1))$

其中的$k_d$是一个系数项。可以看到，在刹车过程中，因为$error$是越来越小的，所以这个微分控制项一定是负数，在控制中加入一个负数项，他存在的作用就是为了防止汽车由于刹车不及时而闯过了线。从常识上可以理解，越是靠近停车线，越是应该注意踩刹车，不能让车过线，所以这个微分项的作用，就可以理解为刹车，当车离停车线很近并且车速还很快时，这个微分项的绝对值（实际上是一个负数）就会很大，从而表示应该用力踩刹车才能让车停下来。

切换到上面给水缸加水的例子，就是当发现水缸里的水快要接近1的时候，加入微分项，可以防止给水缸里的水加到超过1米的高度，说白了就是减少控制过程中的震荡。

### 离散化

比例就是用来对系统的偏差进行反应，所以只要存在偏差，比例就会起作用。

积分主要是用来消除静差，所谓静差就是指系统稳定后输入输出之间依然存在的差值，而积分就是通过偏差的累计来抵消系统的静差。

微分则是对偏差的变化趋势做出反应，根据偏差的变化趋势实现超前调节，提高反应速度。

在实现离散前，我们假设系统采样周期为T。假设我们检查第K个采样周期，很显然系统进行第K次采样。此时的偏差可以表示为$err(K)=rin(K)-rout(K)$，那么积分就可以表示为：$err(K)+ err(K+1)+┈┈$，而微分就可以表示为：$(err(K)- err(K-1))/T$。于是我们可以将第K次采样时，PID算法的离线形式表示为：

![img](https://gitee.com/tianzhendong/img/raw/master/images/202203240934647.png)

或者

![img](https://gitee.com/tianzhendong/img/raw/master/images/202203240934993.png)

这就是所谓的位置型PID算法的离散描述公式。

我们知道还有一个增量型PID算法，那么接下来我们推到一下增量型PID算法的公式。上面的公式描述了第k个采样周期的结果，那么前一时刻也就是k-1个采样周期就不难表示为：

![img](https://gitee.com/tianzhendong/img/raw/master/images/202203240934529.png)

那么我们再来说第K个采样周期的增量，很显然就是U(k)-U(k-1)。于是我们用第k个采样周期公式减去第k-1个采样周期的公式，就得到了增量型PID算法的表示公式：

![img](https://gitee.com/tianzhendong/img/raw/master/images/202203240934645.png)

当然，增量型PID必须记得一点，就是在记住$U(k)=U(k-1)+∆U(k)$。

### 实现

#### 位置型

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

}PID;
```

- 接下来实现PID控制器：

```c
void PIDRegulation(PID *vPID, float processValue)
{

  float thisError;

  thisError=vPID->setpoint-processValue;

  vPID->integral+=thisError;

  vPID->result=vPID->proportiongain*thisError+vPID->integralgain*vPID->integral+vPID->derivativegain*(thisError-vPID->lasterror);

  vPID->lasterror=thisError;
}
```

#### 增量型

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
}PID;
```



```c
void PIDRegulation(PID *vPID, float processValue)
{
  float thisError;

  float increment;

  float pError,dError,iError;

  thisError=vPID->setpoint-processValue; //得到偏差值

  pError=thisError-vPID->lasterror;

  iError=thisError;

  dError=thisError-2*(vPID->lasterror)+vPID->preerror;

  increment=vPID->proportiongain*pError+vPID->integralgain*iError+vPID->derivativegain*dError;   //增量计算 

  vPID->preerror=vPID->lasterror;  //存放偏差用于下次运算

  vPID->lasterror=thisError;

  vPID->result+=increment;
}
```

### 总结

前面讲述并且实现了PID控制器，包括位置型PID控制器和增量型PID控制器。界限来我们对这两种类型的控制器的特点作一个简单的描述。

位置型PID控制器的基本特点：

- 位置型PID控制的输出与整个过去的状态有关，用到了偏差的累加值，容易产生累积偏差。
- 位置型PID适用于执行机构不带积分部件的对象。
- 位置型的输出直接对应对象的输出，对系统的影响比较大。

增量型PID控制器的基本特点：

- 增量型PID算法不需要做累加，控制量增量的确定仅与最近几次偏差值有关，计算偏差的影响较小。
- 增量型PID算法得出的是控制量的增量，对系统的影响相对较小。
- 采用增量型PID算法易于实现手动到自动的无扰动切换。

### 参考

[PID控制器开发笔记之一：PID算法原理及基本实现](https://www.cnblogs.com/foxclever/p/8902029.html)

[一文读懂PID控制算法（抛弃公式，从原理上真正理解PID控制）](https://blog.csdn.net/qq_25352981/article/details/81007075?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-0.control&spm=1001.2101.3001.4242)

