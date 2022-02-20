[toc]

# OpenCV学习笔记

 [OpenCV中文官方文档](http://www.woshicver.com/)

## 图像入门

### imread()读取图像

第一个参数为图像路径

第二个参数可以为1，0，-1，分别表示彩色模式、灰度模式、加载图像包括alpha通道

### imshow()显示图像

窗口自动适合图像尺寸。

第一个参数是窗口名称，它是一个字符串。第二个参数是我们的对象。你可以根据需要创建任意多个窗口，但可以使用不同的窗口名称。

### **waitKey**()键盘绑定函数

参数是以毫秒为单位的时间。该函数等待任何键盘事件指定的毫秒。如果您在这段时间内按下任何键，程序将继续运行。如果**0**被传递，它将无限期地等待一次敲击键。

通常和**destroyAllWindows**()配合使用

### **destroyAllWindows**()破坏创建的所有窗口

**destroyAllWindows**()只会破坏我们创建的所有窗口。

如果要销毁任何特定的窗口，请使用函数 **destroyWindow**()在其中传递确切的窗口名称作为参数。

### namedWindow()创建一个空窗口

然后再将图像加载到该窗口

   * 在这种情况下，你可以指定窗口是否可调整大小。这是通过功能**cv.namedWindow**()完成的。
   *  默认情况下，该标志为**cv.WINDOW_AUTOSIZE**。但是，如果将标志指定为**cv.WINDOW_NORMAL**，则可以调整窗口大小。

通常和imread()配合使用

### imwrite()保存图像

第一个参数是文件名，第二个参数是要保存的图像。 

### 例子

```c++
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
#include <QDebug>
#include <QString>
using namespace cv;

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    /**
     * @brief 加载图像imread
     * 第二个参数可以为1，0，-1，分别表示彩色模式、灰度模式、加载图像包括alpha通道
     */
    Mat src = imread("./../../images/book.bmp",0);

    /**
     * @brief namedWindow创建一个空窗口，然后再将图像加载到该窗口
     * 在这种情况下，你可以指定窗口是否可调整大小。这是通过功能**cv.namedWindow**()完成的。
     * 默认情况下，该标志为**cv.WINDOW_AUTOSIZE**。但是，如果将标志指定为**cv.WINDOW_NORMAL**，则可以调整窗口大小。
     */
    namedWindow("img");
//    namedWindow("img",WINDOW_NORMAL);

    /**
     * @brief imshow显示图像
     *  1.窗口自动适合图像尺寸。
    2.第一个参数是窗口名称，它是一个字符串。第二个参数是我们的对象。你可以根据需要创建任意多个窗口，但可以使用不同的窗口名称。
     */
    imshow("img",src);

    /**
     * @brief imwrite保存图像
     * 第一个参数是文件名，第二个参数是要保存的图像
     */
//    imwrite("./../../images/new1.bmp",src);

    //按s保存图像并退出，或者按ESC键直接退出而不保存。
    char k = waitKey(0);
    if(k == 27){
        destroyAllWindows();
    }else if(k == 's') {
        imwrite("./../../images/new1.bmp",src);
        destroyAllWindows();

}
    /**
     * @brief waitKey键盘绑定函数。其参数是以毫秒为单位的时间
     *该函数等待任何键盘事件指定的毫秒。如果您在这段时间内按下任何键，程序将继续运行。如果**0**被传递，它将无限期地等待一次敲击键。
     */
//    waitKey(0);

    /**
     * @brief destroyAllWindows破坏我们创建的所有窗口。
     * 如果要销毁任何特定的窗口，请使用函数 cv.destroyWindow()在其中传递确切的窗口名称作为参数。
     */
//    destroyAllWindows();

    return a.exec();
}
```

## 视频入门

https://www.cnblogs.com/little-monkey/p/7162340.html

### 前言

视频读取本质上就是读取图像，因为视频是由一帧一帧图像组成的。1秒24帧基本就能流畅的读取视频了。 

### 捕获视频

要捕获视频，你需要创建一个 **VideoCapture** 对象。它的参数可以是**设备索引或视频文件的名称**。设备索引就是指定哪个摄像头的数字。正常情况下，一个摄像头会被连接。所以我简单地传0(或-1)。你可以通过传递1来选择第二个相机，以此类推。在此之后，你可以逐帧捕获。但是在最后，不要忘记释放俘虏。

```c++
//方法1
    VideoCapture cap;
    cap.open(0);
//方法2
    VideoCapture cap(0);
//方法3
    VideoCapture cap = VideoCapture(0);
```

注: 
1)如果默认笔记本/台式机只有一个USB摄像头,Index=0; 如果有2个，一般Index为0和1，根据具体情况区分，摄像头接入和断开会改变Index值 

2)如果接入2个以上，但只想用指定的一个，可以在设备管理器中禁用其他，同时Index设置为0 

3)因为开启摄像头后一直在进行读取，所以需要用waitKey()返回值判断退出预览 



### 循环显示每一帧

- `cap.read(frame)`返回布尔值(`True`/ `False`)。如果正确读取了帧，它将为`True`。因此，你可以通过检查此返回值来检查视频的结尾。
- `cap>>frame`

在显示框架时，请使用适当的时间`waitKey()`。如果太小，则视频将非常快，而如果太大，则视频将变得很慢（嗯，这就是显示慢动作的方式）。正常情况下25毫秒就可以了。


```c++
while(1) 
{ 
    Mat frame; //定义Mat变量，用来存储每一帧 
    cap>>frame; //读取当前帧方法一 
    //cap.read(frame); //读取当前帧方法二 
    imshow(“视频显示”, frame); //显示一帧画面 
    waitKey(30); //延时30ms 
}
```

### 退出循环

```c++
if(waitKey(30)>0)//无按键按下返回-1，按键按下时退出 
break; 
//也可以指定按键退出： 
if(waitKey(30)==27) //Esc键退出，ESC的ASCLL码为27 
break
```

### 实例

```c++
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
#include <QDebug>
#include <QString>
using namespace cv;


int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    VideoCapture cap;
    cap.open(0);    //打开摄像头，等同于cap = VideoCapture(0);

    if(!cap.isOpened()){    //判断是否打开成功
        qDebug()<<"cannot open camera";
    }

    Mat frame;   //定义Mat变量，用来存储每一帧

    while(1){       //循环读取
        cap>>frame; //等同于cap.read(frame);
//        cvtColor(frame, frame, COLOR_BGR2HSV);//BGR空间转为HSV颜色空间，注意，两者不能同时对同一张图片（在此为frame）进行处理，否则报错
        if(!frame.empty()){     //判断读取的是否为空
            imshow("window",frame);     //显示每一帧
        }
        if(waitKey(20) == 'q'){     //按q时退出读取
            break;
        }
    }

    cap.release();  //释放摄像头资源
    destroyAllWindows();    //删除窗口
    return 0;
}
```

### 视频信息

- 获取信息`cap.get()`
- 设置信息`cap.set()`

可以通过`cap.get(CAP_PROP_FRAME_WIDTH)`和`cap.get(CAP_PROP_FRAME_HEIGHT)`检查框架的宽度和高度。默认情况下，它的分辨率为640x480。但我想将其修改为320x240。只需使用和即可。`cap.set(CAP_PROP_FRAME_WIDTH,320)` and `cap.set(CAP_PROP_FRAME_HEIGHT,240)`



```c++
    //获取视频信息
    int width = cap.get(CAP_PROP_FRAME_WIDTH);  //帧宽度
    int height = cap.get(CAP_PROP_FRAME_HEIGHT); //帧高度
    int frameRate = cap.get(CAP_PROP_FPS);  //帧率 x frames/s
    int totalFrames = cap.get(CAP_PROP_FRAME_COUNT); //总帧数
    cout<<"width="<<width<<endl;
    cout<<"length="<<height<<endl;
    cout<<"fps="<<totalFrames<<endl;
    cout<<"frame_rate="<<frameRate<<endl;
```

### 写入视频

```c++
    CV_WRAP VideoWriter(const String& filename, int fourcc, double fps,
                Size frameSize, bool isColor = true);
```

- 创建一个 **VideoWriter** 对象。我们应该指定输出文件名(例如: output.avi)。
- 指定 **FourCC** 代码(详见下一段)。
- 传递帧率的数量和帧大小。
- 颜色标志。如果为 `True`，编码器期望颜色帧，否则它与灰度帧一起工作。

FourCC：http://en.wikipedia.org/wiki/FourCC 是用于指定视频编解码器的4字节代码。可用代码列表可在fourcc.org中:http://www.fourcc.org/codecs.php 找到。它取决于平台。遵循编解码器对我来说效果很好

- 在Fedora中：DIVX，XVID，MJPG，X264，WMV1，WMV2。（最好使用XVID。MJPG会生成大尺寸的视频。X264会生成非常小的尺寸的视频）
- 在Windows中：DIVX（尚待测试和添加）
- 在OSX中：MJPG（.mp4），DIVX（.avi），X264（.mkv）。

FourCC代码作为MJPG的`VideoWriter_fourcc（'M'，'J'，'P'，'G'）`or `VideoWriter_fourcc（*'MJPG'）`传递。

```c++
VideoWriter writer("C:/Pictures/Camera Roll/3.mp4",cap.get(CAP_PROP_FOURCC),cap.get(CAP_PROP_FPS),Size(cap.get(CAP_PROP_FRAME_WIDTH),cap.get(CAP_PROP_FRAME_HEIGHT)),true);
```

①VideoWriter： [OpenCV](http://lib.csdn.net/base/opencv)提供VideoWriter类写视频文件，类的构造函数可以指定文件名、播放帧率、帧尺寸、是否创建彩色视频。 

②两种写入帧方法： 

1 ) writer.write(frame); 

2）writer >>frame(这里的箭头方向应该相反，不知道为什么箭头不能向←); 

③注意事项： 

1)写入视频前需安装对应的编解码器 

2)生成视频是否支持彩色应与构造函数设置一致 

3)**生成视频尺寸需与读取视频尺寸一致**

```c++
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
#include <QDebug>
using namespace cv;
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    VideoCapture cap = VideoCapture(0);
    //写入
    VideoWriter writer("C:/Pictures/Camera Roll/4.mp4",cap.get(CAP_PROP_FOURCC),30,Size(cap.get(CAP_PROP_FRAME_WIDTH),cap.get(CAP_PROP_FRAME_HEIGHT)),true);

    if(!cap.isOpened()){    //判断是否打开成功
        qDebug()<<"cannot open camera";
    }

    Mat frame;   //定义Mat变量，用来存储每一帧

    while(1){       //循环读取
        cap>>frame; //等同于cap.read(frame);
        writer<<frame;  //写入,等同于writer.write(frame);

        if(!frame.empty()){     //判断读取的是否为空
            imshow("frame",frame);     //显示每一帧
        }
        if(waitKey(20) == 'q'){     //按q时退出读取
            break;
        }
    }

    cap.release();  //释放摄像头资源
    destroyAllWindows();    //删除窗口
    return 0;
}
```

