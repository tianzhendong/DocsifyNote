[toc]

# QT

## QT 安装



## 回顾C++基础

### 类和对象

```C++
//新建类
class student{
public:
        string name;
        int age;
        void test()；
};
void student::test(){//类外定义
    cout<<this->age<<endl;
}

    student stu1;//实例化对象
    stu1.age = 10;//通过点访问
    stu1.test();
    student *stu2 = new student;//在堆里定义,需要删除
    stu2->age = 11;//通过箭头访问
    stu2->test();
```

### 重载

**重载**：函数名相同，但是参数不同

### 构造函数和析构函数

**析构函数**：对象被删除或者生命周期结束时触发

**构造函数** ：对象被创建的时候触发、

```c++
student::student(){
    cout<<"hello，构造函数"<<endl;
}
student::~student(){
    cout<<"bye，析构函数"<<endl;
}
```

### 虚函数和纯虚函数

**虚函数**：有实际定义的，允许派生类对他进行覆盖替换，用virtual修饰

**虚函数**：没有实际定义的虚函数

## QT工程基础

### 新建



### 项目文件结构

![image-20220122165058047](https://s2.loli.net/2022/01/22/q7dae3mKW2ITYUG.png)

.pro文件为项目文件

widget.ui文件为UI设计文件，双击进入UI界面设计

sources文件夹为代码文件夹

headers为库文件

### .PRO文件解析

```properties
#-------------------------------------------------
#
# Project created by QtCreator 2022-01-22T16:46:31
#
#-------------------------------------------------

QT       += core gui    #添加core gui模块

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets #qt大于4时加入widgets模块

TARGET = QTDemo2    # 生成app的名称
TEMPLATE = app  ##编译产物的类型

# The following define makes your compiler emit warnings if you use
# any feature of Qt which as been marked as deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS   #定义的一个宏

# You can also make your code fail to compile if you use deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0


SOURCES += \    #指定工程里有哪些CPP
        main.cpp \
        widget.cpp

HEADERS += \    #指定工程中有哪些文件
        widget.h

FORMS += \  #指定工程中有哪些ui文件
        widget.ui

```

### widget.ui

![image-20220122165312247](https://s2.loli.net/2022/01/22/pdRG9q34ZuEUKO5.png)

## 第一个qt工程

### 添加控件

![image-20220122170231198](https://s2.loli.net/2022/01/22/XwNTV5jEZOHtvco.png)

密码输入设置密文：

![image-20220122171208294](https://s2.loli.net/2022/01/22/34YDbvTk6ZmIeus.png)

![image-20220122170248539](https://s2.loli.net/2022/01/22/dVmUZ6GQbuPjoTE.png)

### 控件改名

为了分析代码方便，需要改名字

要通俗易懂

![image-20220122171521061](https://s2.loli.net/2022/01/22/fUCVs9mbzDP3jle.png)

### 信号和槽

**信号**：控件发出的特定的信号

**槽**：槽函数，把控件发出的信号绑定到特定的槽函数

### 关联信号和槽

1. **自动关联**：右键控件——转到槽，选择相应的信号
2. **手动关联**

#### 自动关联

**右键控件——转到槽，选择相应的信号**

![image-20220122171503322](https://s2.loli.net/2022/01/22/Np73wr9UCTxAYfF.png)

**选择相应的信号后，会自动进行：**

1. 在`widget.h`中`private slots`下增加槽函数声明（注意，只能在`private slots`或者`public slots`下面）

```c++
private slots:
    void on_btnLogin_clicked();
```

2. 自动在`widget.cpp`中增加函数定义（具体内容需要自己定义）

```c++
void Widget::on_btnLogin_clicked()
{
    
}
```

3. **随后自己定义相应的槽函数即可**

```c++
void Widget::on_btnLogin_clicked()
{
    qDebug("login");//在调试台输出login
}
```

#### 手动关联

手动关联需要用到`connect()`函数

1. 将自动关联步骤中的1、2、3手动实现，实现槽函数

```c++
void Widget::on_btnRegister_clicked()
{
    qDebug("register");
}
```

2. 在`widget.cpp`中，添加关联函数

`connect(ui->btnLogin,SIGNAL(clicked()),this,SLOT(on_btnRegister_clicked()));`

```c++
Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    connect(ui->btnRegister,SIGNAL(clicked()),this,SLOT(on_btnRegister_clicked()));//添加的关联函数
}
```

其中`ui`为指向整个窗口的指针，`btnLogin`为要关联的控件A，`SIGNAL()`函数中指定**信号**B，`SLOT()`函数中为**槽函数**C

当A发出B信号时，会触发槽函数C

## 添加图片

[icon网址](http://www.easyicon.net)

[图标之家](http://www.icosky.com/)

### 添加资源目录

![image-20220122193234441](https://s2.loli.net/2022/01/22/XYjrqiCbU6MzaTV.png)

工程会出现Resources文件夹

### 将图片放入QT工程目录下

1. 右键Resources文件夹下的文件，

![image-20220122193716310](https://s2.loli.net/2022/01/22/4MWZv3ltOqam1ds.png)

2. 添加前缀

前缀设置成`/`，并保存

![image-20220122193812191](https://s2.loli.net/2022/01/22/dl8oAMyxzrYV7hJ.png)

3. 点击`添加`并选择添加的图片

### 引用文件

1. 在ui页面添加`label`控件（也支持按钮控件）
2. 删掉控件中的文字，并右键——改变样式表——添加资源右侧的箭头——选择border-image——点击左侧<resource root>——选择文件

![image-20220122194046904](https://s2.loli.net/2022/01/22/Ridprh2zasqc1lx.png)

![image-20220122194138559](https://s2.loli.net/2022/01/22/524AtPKZs1dMBTf.png)

## UI界面布局

> 页面布局不会改变任何代码

### 概述

**用途**：防止因不同设备、不同缩放、不同分辨率导致布局错乱问题

**QT布局样式：**

* 水平布局：对应1
* 垂直布局：对应2
* 栅格布局：对应3

![image-20220122194911328](https://s2.loli.net/2022/01/22/3URe1THBVsWoD2i.png)

### 使用

选中两个控件，点击对应的布局按钮即可

#### 弹簧控件Spacers

弹簧用来当缩放UI时，周边间距进行自动变化

![image-20220122195329642](https://s2.loli.net/2022/01/22/CdmcWSjxwBlhVpY.png)

#### 最终

![image-20220122195549478](https://s2.loli.net/2022/01/22/gCZIDWbJYyOQ9St.png)

## 界面切换

### 创建一个新的界面

右键工程——添加新文件——QT——QT设计师界面类——widget——输入名字，这里为`form`

会自动在Headers文件夹下生成`form.h`头文件

![image-20220122195907707](https://s2.loli.net/2022/01/22/EeoYpPWuvG7byaI.png)

### 关联控件

修改`widget.cpp`文件，添加**头文件**和关联槽函数

```c++
#include "form.h"   //添加头文件
//省略
void Widget::on_btnLogin_clicked()
{
    //qDebug("register");
    Form *form1 = new Form;//创建一个Form对象
    form1->setGeometry(this->geometry());//设置form.ui的尺寸为当前ui的尺寸
    form1->show();//展示
}
```

### 关闭新界面

1. 新UI界面控件改名
2. 关联信号
3. 设置关闭槽函数

```c++
void Form::on_pushButton_clicked()
{
    this->close();
}
```

## 获取界面输入

```c++
void Widget::on_btnLogin_clicked()
{
    //获取输入的参数
    QString name = ui->inputName->text();
    QString psd = ui->inputPassword->text();
    if(name == "admin" && psd == "123123"){
        Form *form1 = new Form;//创建一个Form对象
        form1->setGeometry(this->geometry());//设置form.ui的尺寸为当前ui的尺寸
        form1->show();//展示
    }
}
```

## QT串口调试工具

网络编程、串口编程、操作GPIO

仿写

![image-20220122215752070](https://s2.loli.net/2022/01/22/wA6FI9foa1V2edh.png)

### 串口调试助手UI界面

#### 添加控件

1. 设计UI界面大小`800*480`
2. 添加数据接收框`Plain Text Edit`，并在属性设置区勾选`read only`
3. 添加参数下拉选择输入框`Combo Box`和问题提示框`label`
4. 添加发送框`line Edit`
5. 添加按钮`push button`
6. 添加广告框`GroupBox`+`Label`

![image-20220122225909615](https://s2.loli.net/2022/01/22/4w1sceg8J9ILXNC.png)

#### 添加属性

1. 双击波特率、数据位、停止位、校验位下拉输入框，添加属性

![image-20220122230043510](https://s2.loli.net/2022/01/22/QfVq7xgpyjBc9rv.png)

2. 通过属性`currentIndex`设置默认值

![image-20220122231854565](https://s2.loli.net/2022/01/22/TXhKWq7FQPzLB1C.png)

#### 控件改名

添加库支持

在`.pro`文件中，增加`serialport`

```c++
QT       += core gui serialport
```



#### 搜索串口并显示

`widget.cpp`

```c++
#include "widget.h"
#include "ui_widget.h"
#include <QSerialPortInfo>//添加串口头文件

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    //获取串口号并显示
    QStringList serialPortNums;
    foreach (const QSerialPortInfo &info, QSerialPortInfo::availablePorts()) {
        serialPortNums<<info.portName();
    }
    ui->serialPortNum->addItems(serialPortNums);
}

Widget::~Widget()
{
    delete ui;
}

```

### 实现逻辑功能

#### 创建串口对象

在widget.h中添加头文件，并声明串口对象指针

```c++
#include<QSerialPort>//引入串口头文件
public:
	QSerialPort *serialPort;//声明串口类指针
```

zai widget.cpp中创建对象

```c++
    serialPort = new QSerialPort（this）;//创建串口对象
```

#### 打开串口并初始化

1. 关联打开串口控件的信号和槽函数

2. 定义槽函数：定义串口数据——初始化
3. 给当前串口对象赋值

```c++
void Widget::on_btnOpenSerial_clicked()//打开串口-点击信号对应的槽函数
{
    //声明变量
    QSerialPort::BaudRate baudRate;
    QSerialPort::DataBits dataBits;
    QSerialPort::StopBits stopBits;
    QSerialPort::Parity parity;
    //赋值，初始化
    //波特率
    if(ui->baudRate->currentText() == "4800"){
        baudRate = QSerialPort::Baud4800;
    }else if(ui->baudRate->currentText() == "9600"){
        baudRate = QSerialPort::Baud9600;
    }else if(ui->baudRate->currentText() == "115200"){
        baudRate = QSerialPort::Baud115200;
    }
    //数据位
    if(ui->dataBits->currentText()=="5"){
        dataBits = QSerialPort::Data5;
    }else if(ui->dataBits->currentText()=="6"){
        dataBits = QSerialPort::Data6;
    }else if(ui->dataBits->currentText()=="7"){
        dataBits = QSerialPort::Data7;
    }else if(ui->dataBits->currentText()=="8"){
        dataBits = QSerialPort::Data8;
    }
    //停止位
    if(ui->stopBits->currentText()=="1"){
        stopBits = QSerialPort::OneStop;
    }else if(ui->stopBits->currentText()=="1.5"){
        stopBits = QSerialPort::OneAndHalfStop;
    }else if(ui->stopBits->currentText()=="2"){
        stopBits = QSerialPort::TwoStop;
    }
    //校验位
    if(ui->parity->currentText()=="none"){
        parity = QSerialPort::NoParity;
    }

    //获取当前选择的串口号
    serialPort->setPortName(ui->serialPortNum->currentText());
    //设置串口参数
    serialPort->setBaudRate(baudRate);
    serialPort->setDataBits(dataBits);
    serialPort->setStopBits(stopBits);
    serialPort->setParity(parity);
}
```

4. 判断串口是否打开成功

```c++
    //判断串口是否打开成功，并提示，需要添加<QMessgaeBox>头文件
    if(serialPort->open(QIODevice::ReadWrite) == true){
        QMessageBox::information(this,"提示","成功");
    }else {
        QMessageBox::critical(this,"提示","失败");
    }
```

![image-20220123104230714](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123104230714.png)

![image-20220123104241542](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123104241542.png)

#### 关闭串口

1. 关联信号和槽函数
2. 定义逻辑

```c++
void Widget::on_btnCloseSerial_clicked()
{
    serialPort->close();
}
```

#### 数据接收

1. 在`widget.h`中`private slots`下增加槽函数声明

```c++
private slots:
    void serialDataReadReady_Slot();//读数据槽函数声明
```

2. 在`widget.cpp`中增加槽函数

```c++
//读数据槽函数定义
void Widget::serialDataReadReady_Slot(){
    QString buff;//暂存数据
    buff = QString(serialPort->readAll());//读取串口的数据
    ui->dataRCV->appendPlainText(buff);//将数据发送到接受区内
}
```

3. 在`widget.cpp`中，添加关联函数

```c++
    //关联数据接收槽函数
    connect(serialPort,SIGNAL(readyRead()),this,SLOT(serialDataReadReady_Slot()));
```

#### 数据发送

```c++
void Widget::on_btnSend_clicked()//数据发送槽函数
{
    serialPort->write(ui->dataInput->text().toLocal8Bit().data());
}
```

#### 清空

```c++
void Widget::on_btnClear_clicked()//清空槽函数
{
    ui->dataRCV->clear();
}
```

## QT程序打包和部署

### 为什么

1. 把写好的程序给别人用

2. 源码不能随便给别人

### 怎么做

#### release模式

1. 把工程切换到release模式，然后编译

release模式，没有调试信息

debug模式，有很多调试信息

![image-20220123112932311](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123112932311.png)



2. 找到release模式构建的文件夹，并进入release文件夹

![image-20220123113146728](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123113146728.png)

![image-20220123113218111](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123113218111.png)



**Serial.exe文件就是所需的文件，但是之间无法打开，需要动态库**

#### 修改图标

[图标之家](http://www.icosky.com/)

1. 下载图标，**格式为.ico**，并拷贝到工程目录下，注意，不是编译目录

这里图标为：`serial_ico.ico`

![image-20220123114501066](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123114501066.png)

2. 在.pro中增加图标，并编译

```properties
RC_ICONS = serial_ico.ico
```

编译可以看到左上角图标已经改变

![image-20220123114615068](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123114615068.png)

同时release目录下的exe文件的图标也已经改变

![image-20220123114654768](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123114654768.png)

#### 封包

1. 创建一个新的文件夹，不要有中文路径`C:\code\QT\Serial_deploy`

2. 拷贝release下的exe文件到文件夹中

3. 进入QT控制台，并进入新建的文件夹

直接搜索QT，应用下面的就是

![image-20220123114858514](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123114858514.png)

4. 输入`windeployqt Serial.exe`命令后自动封包，结果如下

![image-20220123115455018](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123115455018.png)

5. 双击里面的Serial.exe文件可以直接运行

### 程序打成安装包

Inno Setup软件

[参考](https://www.cnblogs.com/linuxAndMcu/p/10974927.html)

## QT网络编程-TCP通信

### TcpServer

两个类：

* QTcpServer
* QTcpSocket

#### QTcpServer类

提供一个TCP基础服务类 继承自QObject

这个类用来接收到来的TCP连接，可以**指定TCP端口**或者用QTcpServer自己挑选一个端口，可以**监听一个指定的地址或者所有的机器地址**。

 调用`listen()`来监听所有的连接，每当一个新的客户端连接到服务端就会**发射信号**`newConnection()`

调用`nextPendingConnection()`来接受待处理的连接。返回一个连接的`QTcpSocket()`，我们可以用这个返回的套接字和客户端进行连接

如果有错误，`serverError()`返回错误的类型。调用`errorString()`来把错误打印出来。

当监听连接时候，可以调用`serverAddress()`和`serverPort()`来返回服务端的地址和端口。

调用`close()`来关闭套接字，停止对连接的监听。

| Header:       | #include <QTcpServer>             |
| ------------- | --------------------------------- |
| qmake:        | QT += network                     |
| Inherits:     | [QObject](../qtcore/qobject.html) |
| Inherited By: | [QSctpServer](qsctpserver.html)   |

**成员函数**

| 函数                                                         | 用途                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `void close() `                                              | 关闭服务，然后服务器讲不再监听任何连接                       |
| `QString errorString()const`                                 | 错误时候返回错误的字符串                                     |
| `bool hasPendingConnections()const`                          | 如果服务端有一个待处理的连接，就返回真，否则返回假           |
| `QTcpSocket* nextPendingConnection()`                        | 返回一个套接字来处理一个连接，这个套接字作为服务端的一个子对象，意味着当QTcpServer对象销毁时候，这个套接字也自动删除，当使用完后明确的删除这个套接字也好，这样可以避免内存浪费。当没有可处理的连接时候，这个函数返回0。 |
| `void incomingConnection(int socketDescriptor)[virtualprotected]` | 这个函数新建一个QTcpSocket套接字，建立套接字描述符，然后存储套接字在一个整形的待连接链表中。最后发射信号newConnection() |
| `bool isListening()const`                                    | 当服务端正在监听连接时候返回真，否则返回假                   |
| `bool listen( const QHostAddress & address =QHostAddress::Any, quint16 port = 0 )` | 告诉服务端监听所有来自地址为address端口为Port的连接，如果Port为0，那么会自动选择，如果address是QHostAddress::Any,那么服务端监听所有连接，成功返回1，否则返回0 |
| `int maxPendingConnections()const`                           | 返回最大允许连接数。默认是30                                 |
| `void setMaxPendingConnections(int numConnections)`          | 设定待处理的连接最大数目为numConnections,当超过了最大连接数后，客户端仍旧可以连接服务端，但是服务端不在接受连接，操作系统会把这些链接保存在一个队列中。 |
| `QNetworkProxy proxy()const`                                 | 返回这个套接字的网络代理层。                                 |
| `void setProxy(const QNetworkProxy & networkProxy)`          | 设置这个套接字的网络代理层，进制使用代理时候，使用QNetworkProxy::NoProxy类型，例如server->setProxy(QNetworkProxy::NoProxy); |
| `quint16serverPort()const   serverAddress()`                 | 当服务端正在监听时候，返回服务端的端口和地址                 |
| `bool waitForNewConnection(int msec=0,bool *timedOut=0)`     | 最大等待msec毫秒或者等待一个新连接可用。如果一个连接可用，返回真，否则返回假。如果msec不等于0，那么超时将会被调用 |

#### QTcpSocket类

QTcpSocket 类提供一个TCP套接字

TCP是一个面向连接，可靠的的通信协议，非常适合于连续不断的数据传递

QTcpSocket 是QAbstractSocket类非常方便的一个子类，让你创建一个TCP连接和数据流交流。

| Header:       | #include <QTcpSocket>                                        |
| ------------- | ------------------------------------------------------------ |
| qmake:        | QT += network                                                |
| Inherits:     | [QAbstractSocket](qabstractsocket.html)                      |
| Inherited By: | [QSctpSocket](qsctpsocket.html) and [QSslSocket](qsslsocket.html) |

成员函数

| 函数                                            | 用途                                                         |
| ----------------------------------------------- | ------------------------------------------------------------ |
| QTcpSocket::QTcpSocket ( QObject * parent = 0 ) | 以UnconnectedState态创建一个QTcpSocket对象                   |
| QTcpSocket::~QTcpSocket ()  [virtual]‘          | 析构函数，销毁对象                                           |
| bool waitForConnected(int *msecs* = 30000)      | 等待，直到套接字被连接，最高为msecs毫秒。如果连接已经建立，这个函数返回true;否则返回false。在返回false的情况下，可以调用error()来确定错误的原因。 |
| void  connectToHost()                           | 尝试连接给定端口上的主机名。如果查找成功，则发出hostFound()， QAbstractSocket进入ConnectingState。然后，它尝试连接到查找返回的一个或多个地址。最后，如果连接建立，QAbstractSocket进入ConnectedState并发出connected()。 |
| [signal] void QAbstractSocket::connected()      | 这个信号在调用connectToHost()并成功建立连接之后发出。        |
| bool disconnect()                               | 断开对象发送器中的信号与对象接收器中的方法。如果连接成功断开，返回true;否则返回false。 |
| void QAbstractSocket::disconnectFromHost()      | 试图关闭socket。如果有挂起的数据等待写入，QAbstractSocket将进入ClosingState并等待，直到所有的数据都被写入。最终，它将进入UnconnectedState并发出disconnected()信号。 |



#### TcpServer编写

##### 创建ui界面

![image-20220123152716836](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123152716836.png)

##### 引入network和两个头文件

```properties
QT       += core gui network
```

```c++
#include <QTcpServer>
#include <QTcpSocket>
```

##### 声明对象

```c++
    //声明tcp所用的对象
    QTcpServer *tcpServer;
    QTcpSocket *tcpSocket;
```

##### 创建对象`widget.cpp`

```c++
    //创建对象
    tcpServer = new QTcpServer(this);
    tcpSocket = new QTcpSocket(this);
```

##### 编写槽函数

1. 打开服务器时开启监听

调用`listen()`来监听所有的连接，每当一个新的客户端连接到服务端就会**发射信号**`newConnection()`

2. 有新连接时创建tcpsocket，

调用`nextPendingConnection()`来接受待处理的连接。返回一个连接的`QTcpSocket()`，我们可以用这个返回的套接字和客户端进行连接

3. 读取数据到接收框中

`readyRead()`和`readAll()`

4. 关闭服务器

`close()`

5. 发送数据

`write()`

#### 代码

`widget.h`

```c++
#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <QTcpServer>
#include <QTcpSocket>

namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT

public:
    explicit Widget(QWidget *parent = 0);
    ~Widget();

    //声明tcp所用的对象
    QTcpServer *tcpServer;
    QTcpSocket *tcpSocket;


private slots:
    void on_btnOpenServer_clicked();
    //2.有新连接时创建tcpsocket
    void newConnection_Slot();
    //3.读取就绪时读取数据
    void readyRead_SLOT();
    //4. 关闭服务器
    void on_btnCloseServer_clicked();
    //5. 发送数据
    void on_btnSendMessage_clicked();

private:
    Ui::Widget *ui;
};

#endif // WIDGET_H

```

`widget.cpp`

```c++
#include "widget.h"
#include "ui_widget.h"

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    //创建对象
    tcpServer = new QTcpServer(this);
    tcpSocket = new QTcpSocket(this);

    //2.有新连接时创建tcpSocket
    connect(tcpServer,SIGNAL(newConnection()),this,SLOT(newConnection_Slot()));

}

Widget::~Widget()
{
    delete ui;
}

//打开服务器
void Widget::on_btnOpenServer_clicked()
{
    //1.打开监听，所有地址，端口由输入框设定
    tcpServer->listen(QHostAddress::Any,ui->tcpPort->text().toUInt());
}

//有新连接时创建tcpsocket
void Widget::newConnection_Slot(){
    //2.调用`nextPendingConnection()`来接受待处理的连接。返回一个连接的`QTcpSocket()`
    tcpSocket = tcpServer->nextPendingConnection();
    //3.有数据时，读取tcpSocket数据
    connect(tcpSocket,SIGNAL(readyRead()),this,SLOT(readyRead_SLOT()));
}

//3.有数据时，读取tcpSocket数据
void Widget::readyRead_SLOT(){
    QString buff;
    buff = tcpSocket->readAll();
    ui->messageRCV->appendPlainText(buff);
}

//4.关闭服务器
void Widget::on_btnCloseServer_clicked()
{
    //tcpServer->close();
    tcpSocket->close();
}

//5.发送数据
void Widget::on_btnSendMessage_clicked()
{
    //toLocal8Bit()转化为字符数组，data()转化为字符指针
    tcpSocket->write(ui->editMessageSend->text().toLocal8Bit().data());
}
```

#### 测试

![image-20220123161139876](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123161139876.png)

### TcpClient

#### QTcpSocket类

主要用到QTcpSocket类

#### QTcpClient编写

1. 添加支持

`.pro`

```properties
QT       += core gui network #引入network
```

2. 创建UI界面

![image-20220123171112644](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123171112644.png)

3. 声明、创建QTcpSocket对象

创建QTcpSocket对象

4. 打开连接

`connectToHost()`

5. 连接成功时触发槽函数

`connected()`这个信号在调用connectToHost()并成功建立连接之后发出。

6. 新数据时，槽函数

`readyRead()`有新数据到来时触发

7. 发送数据

`write()`

`toLocal8Bit()`转化为字符数组，`data()`转化为字符指针

8. 关闭连接

`close()`

#### 代码

##### `TcpClient.pro`

略去

##### `widget.h`

```c++
#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
//只需要引入socket
#include <QTcpSocket>

namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT

public:
    explicit Widget(QWidget *parent = 0);
    ~Widget();
    //1.socket对象
    QTcpSocket *tcpSocket;

private slots:
    void on_btnConnect_clicked();
    //3.连接成功槽函数
    void connected_Slot();
    //4.新数据到来时触发的槽函数
    void readyRead_Slot();
    //5.发送数据
    void on_btnSend_clicked();
    //6.关闭连接
    void on_btnClose_clicked();

private:
    Ui::Widget *ui;
};

#endif // WIDGET_H
```

##### `widget.cpp`

```c++
#include "widget.h"
#include "ui_widget.h"
#include <QMessageBox>

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    //1.socket对象
    tcpSocket = new QTcpSocket(this);

}

Widget::~Widget()
{
    delete ui;
}

void Widget::on_btnConnect_clicked()
{
    //2.打开连接
    tcpSocket->connectToHost(ui->editIpInput->text(),ui->editPortInput->text().toUInt());
    if(tcpSocket->waitForConnected(1000)){
        QMessageBox::information(this,"提示","连接成功");
    }else{
        QMessageBox::critical(this,"提示","连接失败");
    }
    //3.连接成功时连接函数
    //3.`connected()`这个信号在调用connectToHost()并成功建立连接之后发出。
    connect(tcpSocket,SIGNAL(connected()),this,SLOT(connected_Slot()));
}
//3.连接成功槽函数
void Widget::connected_Slot(){
    //4.新数据时，连接函数
    //`readyRead()`有新数据到来时触发
    connect(tcpSocket,SIGNAL(readyRead()),this,SLOT(readyRead_Slot()));
}
//4.新数据时槽函数
//发送数据到接收框
void Widget::readyRead_Slot(){
    QString buff;
    buff = tcpSocket->readAll();
    ui->pteMessageRCV->appendPlainText(buff);
}

//5.发送数据槽函数
void Widget::on_btnSend_clicked()
{
    //toLocal8Bit()转化为字符数组，data()转化为字符指针
    tcpSocket->write(ui->editMessageSend->text().toLocal8Bit().data());
}


//6.关闭连接槽函数
void Widget::on_btnClose_clicked()
{
    tcpSocket->close();
    //tcpSocket->disconnectFromHost();
    QMessageBox::information(this,"提示","断开连接");
}
```

#### 测试

![image-20220123170825078](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123170825078.png)

![image-20220123172226132](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123172226132.png)

## QT网络编程-UDP通信

### 概述

UDP不分客户端和服务端

只需要使用`QUdpSocket`类

### QUdpSocket类

#### 判断是否有数据等待读取

`bool QUdpSocket::hasPendingDatagrams() const`

如果至少有一个数据报正在等待读取，则返回true;否则返回false。

#### 返回数据报的大小

`qint64 QUdpSocket::pendingDatagramSize() const`

返回第一个挂起的UDP数据报的大小。如果没有可用的数据报，这个函数返回-1。

#### 读取数据

`qint64 QUdpSocket::readDatagram(char **data*, qint64 *maxSize*, QHostAddress **address* = Q_NULLPTR, quint16 **port* = Q_NULLPTR)`

接收不大于maxSize字节的数据报，并将其存储在数据中。发送者的主机地址和端口存储在*address和*port中(除非指针为0)。

成功时返回数据报的大小;否则返回1。

如果maxSize太小，则数据报的其余部分将丢失。为了避免数据丢失，在尝试读取挂起的数据报之前，调用pendingDatagramSize()来确定它的大小。如果maxSize为0，该数据报将被丢弃。

#### 绑定端口

bool QAbstractSocket::bind(const [QHostAddress](qhostaddress.html) &*address*, [quint16](../qtcore/qtglobal.html#quint16-typedef) *port* = 0, [BindMode](qabstractsocket.html#BindFlag-enum) *mode* = DefaultForPlatform)

使用BindMode模式在端口端口上绑定到地址。

将这个套接字绑定到地址地址和端口端口。

对于UDP套接字，绑定后，当UDP数据报到达指定的地址和端口时，就会发出信号QUdpSocket::readyRead()。因此，这个函数对编写UDP服务器很有用。

对于TCP套接字，这个函数可以用来指定出连接使用哪个接口，这在有多个网络接口的情况下很有用。

默认情况下，套接字使用DefaultForPlatform BindMode绑定。如果不指定端口，则选择随机端口。

成功时，函数返回true，套接字进入BoundState;否则返回false。

bool QAbstractSocket::bind([quint16](../qtcore/qtglobal.html#quint16-typedef) *port* = 0, [BindMode](qabstractsocket.html#BindFlag-enum) *mode* = DefaultForPlatform)

这是一个重载函数。
绑定到QHostAddress:任何端口端口，使用BindMode模式。
默认情况下，套接字使用DefaultForPlatform BindMode绑定。如果不指定端口，则选择随机端口。

#### 发送数据

[q](../qtcore/qtglobal.html#qint64-typedef)int64 QUdpSocket::writeDatagram(const char **data*, [qint64](../qtcore/qtglobal.html#qint64-typedef) *size*, const [QHostAddress](qhostaddress.html) &*address*, [quint16](../qtcore/qtglobal.html#quint16-typedef) *port*)

将数据报以数据大小发送到主机地址在端口的地址端口。返回成功时发送的字节数;否则返回1。

数据报总是写成一个块。数据报的最大大小高度依赖于平台，但可以低至8192字节。如果数据报太大，这个函数将返回-1,error()将返回DatagramTooLargeError。

发送大于512字节的数据报通常是不建议的，因为即使它们成功发送，它们也可能在到达最终目的地之前被IP层分片。

警告:在一个连接的UDP套接字上调用这个函数可能会导致错误和没有发送数据包。如果您正在使用已连接的套接字，请使用write()发送数据报。

[q](../qtcore/qtglobal.html#qint64-typedef)int64 QUdpSocket::writeDatagram(const [QNetworkDatagram](qnetworkdatagram.html) &*datagram*)

这是一个重载函数。

使用那里设置的网络接口和跳数限制，将数据报数据报发送到包含在数据报中的主机地址和端口号。如果没有设置目的地址和端口号，这个函数将发送到传递给connectToHost()的地址。

如果目的地址是IPv6，范围id非空，但与数据报中的接口索引不同，则操作系统将选择发送哪个接口是未定义的。

如果函数成功，则返回发送的字节数;如果遇到错误，则返回-1。

警告:在一个连接的UDP套接字上调用这个函数可能会导致错误和没有发送数据包。如果您正在使用已连接的套接字，请使用write()发送数据报。

[q](../qtcore/qtglobal.html#qint64-typedef)int64 QUdpSocket::writeDatagram(const [QByteArray](../qtcore/qbytearray.html) &*datagram*, const [QHostAddress](qhostaddress.html) &*host*, [quint16](../qtcore/qtglobal.html#quint16-typedef) *port*)

这是一个重载函数。
将数据报数据报发送到主机地址主机和在端口端口。
如果函数成功，则返回发送的字节数;如果遇到错误，则返回-1。

#### 其他

` bool QUdpSocket::joinMulticastGroup(const QHostAddress&*groupAddress*)`

加入操作系统选择的缺省接口上的groupAddress指定的组播组。套接字必须处于BoundState状态，否则将发生错误。

注意，如果你试图加入一个IPv4组，你的套接字不能使用IPv6(或双模式，使用QHostAddress::Any)绑定。你必须使用QHostAddress::AnyIPv4代替。

如果成功，此函数返回true;否则返回false并设置相应的socket错误。

`bool QUdpSocket::joinMulticastGroup(const QHostAddress &*groupAddress*, const QNetworkInterface &*iface*)`

这是一个重载函数。

加入该接口的组播组地址groupAddress。

`bool QUdpSocket::leaveMulticastGroup(const QHostAddress &*groupAddress*)`

将groupAddress指定的组播组保留在操作系统选择的缺省接口上。套接字必须处于BoundState状态，否则将发生错误。

如果成功，此函数返回true;否则返回false并设置相应的socket错误。

`bool QUdpSocket::leaveMulticastGroup(const QHostAddress &*groupAddress*, const QNetworkInterface &*iface*)`

这是一个重载函数。

离开接口上的groupAddress指定的组播组。

`QNetworkInterface QUdpSocket::multicastInterface() const`

返回组播数据报的出接口的接口。这对应于IPv4套接字的IP_MULTICAST_IF套接字选项和IPv6套接字的IPV6_MULTICAST_IF套接字选项。如果之前没有设置接口，这个函数返回一个无效的QNetworkInterface。套接字必须处于BoundState，否则返回无效的QNetworkInterface。

`QNetworkDatagram QUdpSocket::receiveDatagram(qint64 *maxSize* = -1)`

接收一个不大于maxSize字节的数据报，并在QNetworkDatagram对象中返回它，以及发送方的主机地址和端口。如果可能，此函数还将尝试确定数据报的目的地址、端口和接收时的跳数。

失败时，返回一个报告无效的QNetworkDatagram。

如果maxSize太小，则数据报的其余部分将丢失。如果maxSize为0，该数据报将被丢弃。如果maxSize为-1(默认值)，这个函数将尝试读取整个数据报。

`void QUdpSocket::setMulticastInterface(const QNetworkInterface &*iface*)`

设置组播数据报的出接口为接口interface。这对应于IPv4套接字的IP_MULTICAST_IF套接字选项和IPv6套接字的IPV6_MULTICAST_IF套接字选项。套接字必须处于BoundState，否则这个函数什么都不做。



### 实例

#### 编写界面

![image-20220123173536182](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123173536182.png)

#### 引入支持文件

qmake：`network`

header：`\#include <QUdpSocket> `

#### 代码

* 创建QUdpSocket对象

* 打开连接

`bind()`

`readyRead()`

* 读取数据

`hasPendDatagrams()`

`hasPendDatagramsSize()`

`readDatagrams()`

* 发送数据

`writeDatagram()`

* 关闭

##### `widget.h`

```c++
#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
//添加头文件Qudpsocket
#include <QUdpSocket>

namespace Ui {
class Widget;
}

class Widget : public QWidget
{
    Q_OBJECT

public:
    explicit Widget(QWidget *parent = 0);
    ~Widget();

    //1.创建对象
    QUdpSocket *udpSocket;

private slots:
    //2.打开连接槽函数
    void on_btnOpen_clicked();
    //3.readyRead槽函数
    void readyRead();
    //4.发送
    void on_btnSend_clicked();
    //关闭
    void on_btnClose_clicked();

private:
    Ui::Widget *ui;
};

#endif // WIDGET_H

```

##### `widget.cpp`

```c++
#include "widget.h"
#include "ui_widget.h"
#include <QMessageBox>
#include <QHostAddress>

Widget::Widget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Widget)
{
    ui->setupUi(this);
    //1.创建对象
    udpSocket = new QUdpSocket(this);

}

Widget::~Widget()
{
    delete ui;
}

void Widget::on_btnOpen_clicked()
{
    //2.打开连接
    //判断是否成功
    if(udpSocket->bind(ui->editLocalPort->text().toUInt()) == true){
        QMessageBox::information(this,"提示","成功");
    }else{
        QMessageBox::critical(this,"提示","失败");
    };
    //3.连接成功触发函数
    connect(udpSocket,SIGNAL(readyRead()),this,SLOT(readyRead()));
}

void Widget::readyRead(){
    //3.槽函数,读取数据
    while(udpSocket->hasPendingDatagrams()){    //判断是否有数据
        QByteArray ary;
        ary.resize(udpSocket->pendingDatagramSize());
        udpSocket->readDatagram(ary.data(),ary.size());

        QString buff;
        buff = ary.data();
        ui->editMessageRCV->appendPlainText(buff);
    }
}

void Widget::on_btnSend_clicked()
{
    //4.发送数据
    //数据
    QString sendBuff;
    sendBuff = ui->editMessageSend->text();
    //ip
    QHostAddress addr;
    addr.setAddress(ui->editTargetIP->text());
    //端口
    quint16 port;
    port = ui->editTargetPort->text().toUInt();
    //发送数据
    udpSocket->writeDatagram(sendBuff.toLocal8Bit().data(),sendBuff.length(),addr,port);
}

void Widget::on_btnClose_clicked()
{
    //5.关闭
    udpSocket->close();
}

```

#### 测试

![image-20220123182754858](https://gitee.com/tianzhendong/img/raw/master//images/image-20220123182754858.png)



## QT定时器

```c++
		QTimer *timer = new QTimer(this);
      connect(timer, SIGNAL(timeout()), this, SLOT(update()));
      timer->start(1000)
```

