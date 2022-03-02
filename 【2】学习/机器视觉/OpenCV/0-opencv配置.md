[toc]

# OpenCV环境配置

## Python（Windows）

### 环境

电脑：win11


### 安装Anaconda

参考文章[Anaconda](Anaconda.md)

### 配置虚拟环境

参考文章[Anaconda](Anaconda.md)

```
conda create -n opencv numpy matplotlib python=3.9.7
```

### 下载opencv 

```
activate opencv
pip install opencv-python
```

### 安装pycharm

### 测试

```python
import cv2 as cv
print(cv.__version__)
```



## QT（Windows）

### 环境

电脑：win11

QT：Qt5.12.9

QTCreater：4.12.2（社区版）

vs:VisualStudio Community 2017

### 前言

涉及两种编译器，msvc和mingw，opencv4.1.0支持vc14和vc15，分别对应vs2015和vs2017，若使用mingw编译，需要下载cmake对opencv重新编译生成。

Qt配置OpenCV教程，亲测已试过（详细版）https://blog.csdn.net/weixin_43763292/article/details/112975207

在重新编译的过程中，出现了各种各样的问题，最终，cmake编译opencv未完成。

因此，决定采用MSVC+qt creator+qt+opencv

### QT配置msvc编译器

[Qt5.9.6使用MSVC（VS2017）开发环境搭建](https://blog.csdn.net/KirkSong/article/details/80874766)

#### 安装VisualStudio Community 2017

[  https://visualstudio.microsoft.com/zh-hans/downloads/](https://visualstudio.microsoft.com/zh-hans/downloads/) 官方网站下载Visual Studio Community 2017

下载后运行vs_community.exe，选择安装**通用Windows平台开发**和**使用C++的桌面开发**，除默认选项外**在使用C++的桌面开发中选择适用于桌面的VC++2015.3 v14.00(v140)工具集**。等待安装完成后，打开VS2017登陆微软账号即可获取使用权限。

![image-20220218151811267](https://gitee.com/tianzhendong/img/raw/master//images/202202181553922.png)

#### windows10SDK安装

安装Windows 10 SDK是为了能在Qt Creator Debugger andTools中使用调试器。

经测试，在安装vs2017时勾选安装windows SDK后，在QT中不能正常使用MSVC，前面一个黄色感叹号。

[  https://developer.microsoft.com/en-us/windows/downloads/windows-10-sdk](https://developer.microsoft.com/en-us/windows/downloads/windows-10-sdk) 打开Windows 10SDK的官方网站下载，winsdksetup.exe，下完完成后运行。

这里下载的是ISO版本

下载后装载

![image-20220218152004171](https://gitee.com/tianzhendong/img/raw/master//images/202202181553738.png)

运行winsdksetup.exe

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202181553510.jpeg)

在这里我们只需要选择Debugging Toolsfor Windows，点击下载。

下载完成后在installers目录有几个安装文件

dotNetFx45_Full_x86_x64.exe、Kits Configuration Installer-x86-en-us.msi、SDKDebuggers-x86_en-us.msi、Windows SDK EULA-x86_en-us.msi、X64 Debuggers And Tools-x64_en-us.msi、X86Debuggers And Tools-x86_en-us.msi

依次安装

#### 安装QT

在清华源下载QThttps://mirrors.tuna.tsinghua.edu.cn/qt/official_releases/qt/

安装时组件选择：选择msvc2017 64-bit、mingw 和QTCreator，QT开头的组件最好全选

图片来源：https://blog.csdn.net/weixin_45525272/article/details/113062352

![在这里插入图片描述](https://gitee.com/tianzhendong/img/raw/master//images/202202181600724.png)

#### 构建环境配置

（1） 点击工具——选项——构建和运行——构建套件

 如果上述安装过程中，按照步骤安装好了VisualStudio 2017和Windows 10 SDK,自动检测出来的构建套件应该是没有问题的。如图

![image-20220218160253149](https://gitee.com/tianzhendong/img/raw/master//images/202202181602298.png)

（2） 点击工具——选项——构建和运行——Debuggers

![image-20220218160317206](https://gitee.com/tianzhendong/img/raw/master//images/202202181603284.png)



### opencv

#### 下载

https://opencv.org/releases.html

![image-20220218161636498](https://gitee.com/tianzhendong/img/raw/master//images/202202181616553.png)

#### 解压

双击下载后的程序

![image-20220218161725764](https://gitee.com/tianzhendong/img/raw/master//images/202202181617798.png)

![在这里插入图片描述](https://gitee.com/tianzhendong/img/raw/master//images/202202181617991.png)

![image-20220218161804933](https://gitee.com/tianzhendong/img/raw/master//images/202202181618997.png)

#### 配置环境变量

```
C:\opencv\opencv4.5.5\build\x64\vc15\bin
```

### QT使用OpenCV

#### 新建工程

#### 在pro文件中添加opencv

```properties
INCLUDEPATH+= C:\opencv\opencv4.5.5\build\include\
              C:\opencv\opencv4.5.5\build\include\opencv2

LIBS+= -LC:\opencv\opencv4.5.5\build\x64\vc15\lib\
       -lopencv_world455d\
        -lopencv_world455
```



![image-20220218162023678](https://gitee.com/tianzhendong/img/raw/master//images/202202181620733.png)

####  在cpp中加入头文件和命名空间

```c++
#include "opencv2/opencv.hpp"
using namespace cv;
```

#### 测试

```c
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
using namespace cv;

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    Mat src = imread("./../../images/book.bmp");
    imshow("img",src);
    waitKey(0);
    destroyAllWindows();
    return 0;
}
```

![image-20220221145436340](https://gitee.com/tianzhendong/img/raw/master//images/202202211454543.png)

****
