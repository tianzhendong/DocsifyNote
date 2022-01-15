[toc]

### 概述

#### 组态

> “组态(Configure)”的含义是“配置”、“设定”、“设置”等意思，是指用户通过类似“搭积木”的简单方式来完成自己所需要的软件功能，而不需要编写计算机程序，也就是所谓的“组态”。它有时候也称为“二次开发”，组态软件就称为“二次开发平台”。 
>
> “监控（Supervisory Control）”，即“监视和控制”，是指通过计算机信号对自动化设备或过程进行监视、控制和管理。
>
> 简单地说，组态软件能够实现对自动化过程和装备的监视和控制。它能从自动化过程和装备中采集各种信息，并将信息以图形化等更易于理解的方式进行显示，将重要的信息以各种手段传送到相关人员，对信息执行必要分析处理和存储，发出控制指令等等。



组态软件是有专业性的。一种组态软件只能适合某种领域的应用。组态的概念最早出现在[工业计算机](https://baike.baidu.com/item/工业计算机)控制中。如DCS([集散控制系统](https://baike.baidu.com/item/集散控制系统))组态，PLC（[可编程控制器](https://baike.baidu.com/item/可编程控制器)）梯形图组态。人机界面生成软件就叫[工控组态软件](https://baike.baidu.com/item/工控组态软件)。其实在其他行业也有组态的概念，人们只是不这么叫而已。如[AutoCAD](https://baike.baidu.com/item/AutoCAD)，PhotoShop，[办公软件](https://baike.baidu.com/item/办公软件)(PowerPoint)都存在相似的操作，即用软件提供的工具来形成自己的作品，并以数据文件保存作品，而不是执行程序。组态形成的数据只有其制造工具或其他专用工具才能识别。



控制逻辑组态软件就实现了组态编程的功能，工业中，向PLC写逻辑、写控制策略，国际上有标准的5种编程语言：梯形图、功能块、结构化文本等，梯形图与功能块的操作过程类似于组态的过程，将一个一个东西搭建起来，配置参数，而结构化文本的操作过程就类似于编程，因此很多时候也听到组态编程的概念。



组态软件：组态软件就是用图形代表实物，在屏幕上用生动的画面表现实物运行的情况，操作图形即达到操作实物的功能。同时兼具数据记录，通讯，报警等功能。

#### TIA博途

Totally Integrated Automation（ 全集成自动化）,是世界第一款将所有自动化任务整合在一个工程设计环境下的软件。

> 百度百科：TIA博途是[全集成自动化](https://baike.baidu.com/item/全集成自动化/4566464)软件[TIA portal](https://baike.baidu.com/item/TIA portal/1607853)的简称，是[西门子](https://baike.baidu.com/item/西门子/25878)工业自动化集团发布的一款全新的全集成自动化软件。它是业内首个采用统一的工程组态和软件项目环境的自动化软件，几乎适用于所有自动化任务。借助该全新的工程技术软件平台，用户能够快速、直观地开发和调试自动化系统。

[官网](https://new.siemens.com/cn/zh/products/automation/industry-software/automation-software/tia-portal.html)

西门子官网介绍：

>  TIA Portal – 不只是一个工程组态平台
>
> TIA Portal 提供了各种功能和创新的仿真工具，可用来缩短产品上市时间，通过附加诊断及能源管理功能来提高工厂的生产力，并通过协调的团队工作来获得更大灵活性。



**软件：**

通过完整的自动化任务软件包，优化工程组态

使用TIA Portal，您不仅可以在一个界面中集成基本软件（STEP 7，WinCC，SINAMICS Startdrive，SIMOCODE ES和SIMOTION SCOUT TIA），还可以集成新功能，例如多用户和能源管理。

![img](https://gitee.com/tianzhendong/img/raw/master//images/image001-26.png)

不仅仅是各部分的简单组合：完整软件包的各个组件紧密链接在一起。因此，TIA Portal 提供了用于以高效且（最重要的是）可管理的方式将自动化与数字化联系在一起的各种功能。

![image-20211230140650060](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230140650060.png)

包含了如下软件系统：

1）SIMATIC Step 7：用于控制器（PLC）与分布式设备的组态和编程；

2）SIMATIC WinCC：用于人机界面（HMI）的组态；

3）SIMATIC Safety：用于安全控制器（Safety PLC）的组态和编程；

4）SINAMICS Startdrive：用于驱动设备的组态与配置；

5）SIMOTION Scout：用于运动控制的配置、编程与调试；

**优势：**

通过TIA Portal ，可以不受限制地访问西门子的完整数字化服务系列：从数字化规划和一体化工程到透明的运行。 通过仿真工具等来缩短产品上市时间，通过附加诊断及能源管理功能提高工厂生产力，并通过连接到管理层来提供更大灵活性。将使系统集成商和机器制造商以及工厂运营商获益。因此，TIA Portal不只是一个工程组态平台，它是数字化企业实现自动化的理想途径。TIA Portal 与 PLM 和 MES 一起，成为构成数字化企业套件的一部分。

![image-20211230141228826](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230141228826.png)

![image-20211230141315304](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230141315304.png)

![image-20211230141336997](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230141336997.png)

![image-20211230141403933](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230141403933.png)

![image-20211230141423874](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230141423874.png)

![image-20211230141440137](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230141440137.png)

![image-20211230141550141](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230141550141.png)

![image-20211230141603769](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230141603769.png)

#### 支持的设备

![image-20211230161710132](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230161710132.png)



![image-20211230161729747](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230161729747.png)



### 软件使用

#### 视图

博途视图、任务视图

通过左下角切换 

#### 操作

打开项目、plc编程

拖拽指令到程序段、

拖拽左下角详细视图中的变量到程序块上

分屏打开设备后，直接拖拽设备上的io点到程序段

#### 高效组态

S7-1500有32个导轨，1号放置CPU，0号放置系统电源模块或者负载电源模块

##### 添加控制器

S7-1500支持模块检测功能，首次连接时可以插入一个非指定的CPU1500

![image-20211230163615008](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230163615008.png)

点击获取

![image-20211230163726129](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230163726129.png)

选择网卡，将自动扫描所有的模块，并按出厂设置的参数上载

![image-20211230163911885](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230163911885.png)

添加电源、IO模块

![image-20220111094423002](https://gitee.com/tianzhendong/img/raw/master//images/image-20220111094423002.png)

修改IO变量名称，支持批量下拉复制功能（excel）

![image-20211230164112273](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230164112273.png)



##### 扩展

S71500没有扩展机架，用分布式io实现扩展

ET200SP 和ET200MP是专门为S71200和1500设计的分布式IO

S71500的主机架和ET200MP使用同样的电源、信号、通信、工艺模块，合称为S71500_ET200MP自动化系统

打开网络视图，将右侧分布式IO中的ET200MP拖入到中间，双击打开，添加相同的电源、IO模块

![image-20220111094540392](https://gitee.com/tianzhendong/img/raw/master//images/image-20220111094540392.png)

##### 网络连接

![image-20220111094629742](https://gitee.com/tianzhendong/img/raw/master//images/image-20220111094629742.png)

连接后，IP变为192.168.0.2

##### 导出标签条

可以使用办公软件打印

![image-20211230164315426](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230164315426.png)

![image-20211230164340452](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230164340452.png)

##### 模块暂存

支持将暂时不需要的模块拖出到上方，需要的时候再拖入，参数不会i丢失

![image-20211230164639053](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230164639053.png)

#### 高效编程

##### 添加程序块

S7-1500支持多个主程序看，可以手动设置程序块的编号，运行时根据编号大小依次循环运行

![image-20211230165145424](https://gitee.com/tianzhendong/img/raw/master//images/image-20211230165145424.png)

##### 指令类别

在不同设备下面打开对应的程序，右侧的指令自动变为该设备支持的指令

##### 指令收藏夹

可以通过拖拽的方式将指令拖入到收藏夹中

##### 编程

不同分支之间，通过拖拽合并分支

点击指令右上角可以直接改变相同类型的指令类型，如取反等

未定义的变量通过右键直接可以进行定义、分配地址

打开变量表，可以直接将变量拖拽到程序上

支持将程序中的变量直接拖拽到设备IO口

支持在右侧PLC变量中对变量进行分组

PLC变量页面支持变量表导出，支持将excel中的变量批量复制导入到项目中

##### 注释

支持对每个独立的块和输出添加注释：右键-插入注释

#### 高效仿真PLCSIM

需要设置密码

监控、调试

sim表

### 数据类型

位(bit)，字节(Byte)，字(Word)，双字(Double Word)，整型数(INT)，双整型书(DINT)，及实数/[浮点数](https://so.csdn.net/so/search?q=浮点数&spm=1001.2101.3001.7020)(Real)

#### 位(bit)

常称作布尔量BOOL，在高级语言中，会说布尔变量，布尔控件等。

取值：0 ，1

寻址：I0.0 M0.0 Q0.0 等。

应用：在DI，DO梯形图编程中，会大量用到I点，M点，Q点。

####  字节(Byte)

8个bit组成一个Byte，其中0位表示最低位，7位表示最高位。如MB0(包括M0.0-M0.7位)，IB0(包括I0.0-I0.7位)，QB0(包括Q0.0-Q0.7位)。

寻址：MB0，IB0，QB0，VB0等。

范围：00-FF(十进制0-255)

应用：MB0赋值为1(0000 0001)即M0.0赋值为真，其余M0.1-M0.7为假。

MB2赋值为1(0000 0010)即M0.1赋值为真，其余为假。

MB3赋值为1(0000 0011)即M0.1和M0.0赋值为真，其余为假。

![f39fff4c380e64947524be0f0b6a1ffe.png](https://gitee.com/tianzhendong/img/raw/master//images/f39fff4c380e64947524be0f0b6a1ffe.png)

#### 字(Word)

相邻的两个字节(Byte)组成一个字(Word)，来表示一个无符号数。一个Word包括16bit。

寻址：MW0，IW0，QW0，VW0等。

范围：0000-FFFF(十进制0-65536)

应用：在模拟量的处理采集中会大量涉及到Word的应用。要注意数据的转换，在后面一块说。

注意：相邻两个字要慎用，如MW0(MB0+MB1)，MW1(MB1+MB2)，所以用了MW0，再用就从MW2，MW4这么用。

![da347094b540fd554a30c831081c5d7a.png](https://gitee.com/tianzhendong/img/raw/master//images/da347094b540fd554a30c831081c5d7a.png)

![9bba9ec630307b9a3348ef4dd9dc900a.png](https://gitee.com/tianzhendong/img/raw/master//images/9bba9ec630307b9a3348ef4dd9dc900a.png)

#### 双字(Double Word)

相邻的两个字(Word)组成一个字(DWord)，来表示一个无符号数。一个双字包括32bit。如MD0由MW0(MB0，MB1)，MW2(MB2，MB3)组成。

寻址：MD0，ID0，QD0，VD0等。

范围：0000 0000-FFFF FFFF(十进制0-4294967295)

应用：在模拟量的处理采集中会大量涉及到DWord的应用。要注意数据的转换，在后面一块说。

······

注意：相邻两个字要慎用，如MD0(MW0+MW1)，MD2(MW2+MW3)，所以用了MD0，再用就从MD2，MD4这么用

![cdc3b95c5394351edc820240f6cc0940.png](https://gitee.com/tianzhendong/img/raw/master//images/cdc3b95c5394351edc820240f6cc0940.png)

#### 16位整形(INT，Integer)

整数为有符号数，最高位为符号位，1表示负数，0表示正数。范围为-32768-32767.

寻址：MW0，IW0，QW0，VW0，DB1.DBW0等。

范围：-32768-32767

应用：IW0包括IB0和IB1，IB0为高字节，IB1为低字节；

注意：16位整形数和Word的寻址地址是一样的，这里就看把这个寻址地址定义为什么数据类型了。

注意2：注意区分寻址地址和数据类型，这里讨论的是数据类型。可以把一个寻址地址(DB1.DBW0)里的数定义为无符号数(Word)或(INT)

#### 32位整形(INT，Integer)

一个32位整数里包括两个字或者说4个字节共32位(bit0-bit31)，最高位的bit31表示符号位，bit31=1表示为负数。

寻址：MD0，ID0，QD0，VD0，DB0.DBD0等。

范围：-2147483648-2147483647

注意：32位整形数和DWord的寻址方式是一样的，这里就看把这个寻址地址定义为什么数据类型了。

注意2：注意区分寻址地址和数据类型，这里讨论的是数据类型。可以把一个寻址地址(DB1.DBD0)里的数定义为无符号数(DWord)或(DINT)

#### 实数/浮点数(Real)

浮点数为32为，可以用小数来表示。

寻址：MD0，ID0，QD0，VD0，DB0.DBD0等。

范围：±1.75495×10 -38-±1.75495×10 38

####  位、字节、字、双字关系表

![9b1690f17fa7e9e656fdd55ab6b7b350.png](https://gitee.com/tianzhendong/img/raw/master//images/9b1690f17fa7e9e656fdd55ab6b7b350.png)

### M区和DB块的区别

二者的差别，1200与300/400差异很大，按S7-1200来回答：
  1200的M区亦称之为位存储区，系统预定义，使用方法与300/400类似。它的存在使得S7-1200可以与S7-300的用户程序兼容变得简单，但它是非优化访问的，故其运行效率不高。
  DB是用户定义的数据块，处于工作存储器内，它可以是兼容结构，也可以为优化存储结构。在优化访问时，运行效率高，可以单独设置每一个数据的掉电保持属性。兼容存储结构时，按整个数据块设置掉电保持属性。
  在S7-1200中，数据(块)的缺省设置状态是非掉电保持的

![img](https://gitee.com/tianzhendong/img/raw/master//images/image.png)

### 寻址

