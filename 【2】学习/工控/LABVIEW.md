[toc]

# Labview

## 用LabVIEW打包EXE应用文件和打包程序安装文件的方法

[blog.csdn.net](https://blog.csdn.net/weixin_41692285/article/details/87079330)

我同学现在单位也要从事LabVIEW的编程学习，他们想把编写的软件放到另外的电脑上执行。网上搜索了一些，但是没有一个系统的归纳和汇总。借此机会，我把几种打包方案和区别整理一下发布在这里，欢迎大家批评指正，互相学习。

### 打包方式比对表

表1 打包方式比对

![image-20220218162532787](https://gitee.com/tianzhendong/img/raw/master//images/202202181625844.png)



### 对应打包方式的具体方法

第1种VI打包方式，这里就不赘述了，拿个U盘拷贝一下就可以了。

### 应用文件打包

首先，必须在工程模式下，也就是打开名为“拟\*.lvproj”的文件。如果刚开始的时候，不是用创建工程的方式创建项目，然后一步步新建VI来编程的同学，也不用担心，打开VI，工具栏依次点击“**工具->通过VI生成应用程序(EXE)...**”，会弹出让你创建工程的提示，如图1所示，可以根据需要，确定工程的保存目录以及工程名称；

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202181625076.png)

图1 通过VI生成应用程序提醒界面

 点击“**继续**”后，就会自动打开lvproj工程文件，并开始新建应用程序流程。我们这里不着急往下。如果我们通过新建工程在创建VI进行编程，那么我们打开工程后**右击**其中的“程序生成规范”选择“**新建->应用程序（EXE）**”便可得到应用程序生成配置界面，该界面用户通过VI生成应用程序中点击“**继续**”按钮后的界面相同，配置界面如图3所示。

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202181626273.png)

图2 工程中新建应用程序选择界面

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202181626357.png)

图3 生成应用程序配置界面-初始

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202181626523.png)

图4 生成应用程序配置界面-源文件

1.  信息:修改应用程序目标文件的名称和保存路径；
2.  源文件：选择需要生成应用程序的源VI文件，单击选中后，点击最上的向右箭头，启动VI设置为程序的第一个VI，其他子VI（若有），则放置在“始终包括”栏中，如图4所示；
3.  其他设置可以根据需要，自行修改，单击生成即可在指定未知生成\*.exe文件。此时本地电脑中是有LabVIEW的，可以打开保存目录，查看一下.exe文件是否好用，保存目录截图如图5所示；

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202181626257.png)

图5 应用程序保存文件

到此，应用程序打包结束；

### 安装程序打包

安装程序打包同应用程序打包的流程类似。**首先必须在工程下新建应用程序！**非常重要；否则没有生成安装程序的源应用程序文件！

安装程序的配置界面与图3类似，不再赘述，在源文件配置时，应当选择之前保存的应用程序文件，添加到程序文件对应目录中，如图6所示，选中后，点击亮起的向右箭头即可；

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202181632690.png)

图6 安装程序配置界面-源文件

附加安装程序选择需要的核心程序文件即可，我的电脑中只有2013版，所以只有一个引擎可选，如图7。剩余选项根据自己的需要，自行定制。

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202181626616.png)

图7 安装程序配置界面-附加安装程序

点击生成，即可生成对应应用程序的安装程序。

到此为止，应用程序的打包和对应的安装程序打包均已演示完毕。如果有不对的地方，欢迎大家批评指正。

谢谢。

[查看原网页: blog.csdn.net](https://blog.csdn.net/weixin_41692285/article/details/87079330)

## LABVIEW数值型的数据类型及切换

### 数值型数据类型

| 数值类型         | 图标                                                         | 存储占位数 | 数值范围                                                     |
| ---------------- | ------------------------------------------------------------ | ---------- | ------------------------------------------------------------ |
| 64位整型         | ![img](https://gitee.com/tianzhendong/img/raw/master//images/45797.jpg) | 64         | -18 446 744 073 709 551 616~+18 446 744 073 709 551 615      |
| 长整型           | ![img](https://gitee.com/tianzhendong/img/raw/master//images/52457.jpg) | 32         | -2 147 483 648~+2 147 483 647                                |
| 双字节整型       | ![img](https://gitee.com/tianzhendong/img/raw/master//images/64233.jpg) | 16         | -32 768~+32767                                               |
| 单字节整型       | ![img](https://gitee.com/tianzhendong/img/raw/master//images/62727.jpg) | 8          | -128~+127                                                    |
| 无符号64位整型   | ![img](https://gitee.com/tianzhendong/img/raw/master//images/61801.jpg) | 63         | 0~1 844 674 407 309 551 615                                  |
| 无符号长整型     | ![img](https://gitee.com/tianzhendong/img/raw/master//images/25470.jpg) | 32         | 0~4 294 967 295                                              |
| 无符号双字节整型 | ![img](https://gitee.com/tianzhendong/img/raw/master//images/58650.jpg) | 16         | 0~65 535                                                     |
| 无符号单字节整型 | ![img](https://gitee.com/tianzhendong/img/raw/master//images/69679.jpg) | 8          | 0~255                                                        |
| 扩展精度         | ![img](https://gitee.com/tianzhendong/img/raw/master//images/50710.jpg) | 128        | 最小正数：6.48E-4966 最大正数：1.19E+4932最小负数：-6.48E-4966 最大负数：-1.19E+4932 |
| 双精度           | ![img](https://gitee.com/tianzhendong/img/raw/master//images/34970.jpg) | 64         | 最小正数：4.94E-324  最大正数：1.79E+308最小负数：-4.94E-324  最大负数：-1.79E+308 |
| 单精度           | ![img](https://image.cubox.pro/article/2022011410243917075/58650.jpg) | 32         | 最小正数：1.40E-45  最大正数：3.40E+38最小负数：-1.40E-45  最大负数：-3.40E+38 |
| 定点型           | ![img](https://gitee.com/tianzhendong/img/raw/master//images/53615.jpg) |            |                                                              |
| 扩展精度复数     | ![img](https://gitee.com/tianzhendong/img/raw/master//images/90555.jpg) | 256        | 实部与虚部分别与扩展精度浮点型相同                           |
| 双精度复数       | ![img](https://gitee.com/tianzhendong/img/raw/master//images/69749.jpg) | 128        | 实部与虚部分别与双精度浮点型相同                             |
| 单精度复数       | ![img](https://gitee.com/tianzhendong/img/raw/master//images/92031.jpg) | 64         | 实部与虚部分别与单精度浮点型相同                             |

### 数据类型切换方法

选择需要转换的数据，鼠标单击右键，选择“属性”，弹出“数值类的属性”界面，选择“数据类型”，双击“表示法”，选择需要切换的数据类型，确认。

![img](https://gitee.com/tianzhendong/img/raw/master//images/20191119151332578.png)

