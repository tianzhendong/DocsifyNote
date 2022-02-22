[toc]

# OpenCV学习笔记

 [OpenCV中文官方文档](http://www.woshicver.com/)

## 图像入门

参考[OpenCV中文官方文档](http://www.woshicver.com/ThirdSection/2_4_%E9%BC%A0%E6%A0%87%E4%BD%9C%E4%B8%BA%E7%94%BB%E7%AC%94/)

### imread()读取图像

```c
Mat imread( const String& filename, int flags = IMREAD_COLOR );
```

第一个参数为图像路径

第二个参数可以为1，0，-1，分别表示彩色模式、灰度模式、加载图像包括alpha通道

### imshow()显示图像

```c
void imshow(const String& winname, const ogl::Texture2D& tex);
```



窗口自动适合图像尺寸。

第一个参数是窗口名称，它是一个字符串。第二个参数是我们的对象。你可以根据需要创建任意多个窗口，但可以使用不同的窗口名称。

### **waitKey**()键盘绑定函数

```c
int waitKey(int delay = 0);
```

参数是以毫秒为单位的时间。该函数等待任何键盘事件指定的毫秒。如果您在这段时间内按下任何键，程序将继续运行。如果**0**被传递，它将无限期地等待一次敲击键。

通常和**destroyAllWindows**()配合使用

### **destroyAllWindows**()破坏创建的所有窗口

**destroyAllWindows**()只会破坏我们创建的所有窗口。

如果要销毁任何特定的窗口，请使用函数 **destroyWindow**()在其中传递确切的窗口名称作为参数。

### namedWindow()创建一个空窗口

```c
void namedWindow(const String& winname, int flags = WINDOW_AUTOSIZE);
```

然后再将图像加载到该窗口

   * 在这种情况下，你可以指定窗口是否可调整大小。这是通过功能**cv.namedWindow**()完成的。
   *  默认情况下，该标志为**cv.WINDOW_AUTOSIZE**。但是，如果将标志指定为**cv.WINDOW_NORMAL**，则可以调整窗口大小。

### imwrite()保存图像

```c
bool imwrite( const String& filename, InputArray img,
              const std::vector<int>& params = std::vector<int>());
```

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

参考[OpenCV中文官方文档](http://www.woshicver.com/ThirdSection/2_4_%E9%BC%A0%E6%A0%87%E4%BD%9C%E4%B8%BA%E7%94%BB%E7%AC%94/)

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

## OpenCV绘图

参考[OpenCV中文官方文档](http://www.woshicver.com/ThirdSection/2_4_%E9%BC%A0%E6%A0%87%E4%BD%9C%E4%B8%BA%E7%94%BB%E7%AC%94/)

### 前言

通过**cv.line()**，**cv.circle()**，**cv.rectangle()**，**cv.ellipse()**，**cv.putText**()绘制不同的几何形状

参数：

- img：您要绘制形状的图像
- color：形状的颜色。对于BGR，将其作为元组传递，例如：(255,0,0)对于蓝色。对于灰度，只需传递标量值即可。
- 厚度：线或圆等的粗细。如果对闭合图形（如圆）传递`-1` ，它将填充形状。*默认厚度= 1*
- lineType：线的类型，是否为8连接线，抗锯齿线等。*默认情况下*，为8连接线。**cv.LINE_AA**给出了抗锯齿的线条，看起来非常适合曲线。

### 直线`line()`

```c++
CV_EXPORTS_W void line(InputOutputArray img, Point pt1, Point pt2, const Scalar& color,
                     int thickness = 1, int lineType = LINE_8, int shift = 0);
```

- img：图像.
- pt1：线条起点.
- pt2：线条终点.
- color：线条颜色.
- thickness：线条宽度.
- lineType：线型

```c++
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
using namespace cv;
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    Mat src = imread("./../../images/book.bmp");
    line(src,Point(511,0),Point(511,511),Scalar(255,0,0),5);
    imshow("line",src);
    waitKey(0);
    return 0;
}
```

<center>
    <img src = "https://gitee.com/tianzhendong/img/raw/master//images/image-20220221085438392.png" width = 50%>
    <br>
    插入直线
</center>

### 矩形`rectangle()`

要绘制矩形，您需要矩形的左上角和右下角。

```c++
//方法1，矩形的左上角和右下角
void rectangle(InputOutputArray img, Point pt1, Point pt2,
                          const Scalar& color, int thickness = 1,
                          int lineType = LINE_8, int shift = 0);
//方法2，矩形的位置和长宽
void rectangle(InputOutputArray img, Rect rec,
                          const Scalar& color, int thickness = 1,
                          int lineType = LINE_8, int shift = 0);
```

- img：图像。
- pt1、pt2：矩形的左上角和右下角
- rec：矩形的位置和长宽
- color：线条颜色 (RGB) 或亮度（灰度图像 ）(grayscale image）。
- thickness：组成矩形的线条的粗细程度。取负值时（如CV_FILLED）函数绘制填充了色彩的矩形。
- line_type：线条的类型。见cvLine的描述
- shift：坐标点的小数点位数。

```c++
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
using namespace cv;
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    Mat src = imread("./../../images/book.bmp");
    rectangle(src,Rect(0, 0, 100, 100),Scalar(255,0,0),1);
    //rectangle(src,Point(0,0),Rect(100,100)，Scalar(255,0,0),1);
    imshow("rectangle",src);
    waitKey(0);
    return 0;
}
```

<center>
    <img src = "https://gitee.com/tianzhendong/img/raw/master//images/image-20220221090010962.png" width = 50%>
    <br>
    画矩形
</center>

### 圆圈、点`circle()`

画圆画点都是使用circle()函数来画，点就是圆，我们平常所说的圆只不过是半径大一点而已。

```c++
void circle(InputOutputArray img, Point center, int radius,
                       const Scalar& color, int thickness = 1,
                       int lineType = LINE_8, int shift = 0);
```

- img：图像。
- center：圆心坐标。
- radius：圆形的半径。
- color：线条的颜色。
- thickness：如果是正数，表示组成圆的线条的粗细程度。否则，表示圆是否被填充。
- line_type：线条的类型。见 cvLine 的描述
- shift：圆心坐标点和半径值的小数点位数。

```c++
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
using namespace cv;
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    Mat src = imread("./../../images/book.bmp");
    //画空心圆圈
    circle(src,Point(100,100),50,Scalar(0,0,255),1);
    //画实心圆圈
    circle(src,Point(200,200),50,Scalar(0,0,255),-1);
    //画点
    circle(src,Point(100,100),1,Scalar(0,0,255),-1);
    imshow("circle",src);
    waitKey(0);
    return 0;
}
```

<center>
    <img src="https://gitee.com/tianzhendong/img/raw/master//images/20220221091936.png" width =50%>
    <br>
    画点和圆
</center>

### 画椭圆`ellipse()`

```c++
void ellipse(InputOutputArray img, const RotatedRect& box, const Scalar& color,
                        int thickness = 1, int lineType = LINE_8);

void ellipse(InputOutputArray img, Point center, Size axes,
                        double angle, double startAngle, double endAngle,
                        const Scalar& color, int thickness = 1,
                        int lineType = LINE_8, int shift = 0);
```

- img：图像。
- center：椭圆圆心坐标。
- axes：轴的长度(横轴，纵轴)。
- angle：偏转的角度。
- start_angle：圆弧起始角的角度。
- end_angle：圆弧终结角的角度。
- color：线条的颜色。
- thickness：线条的粗细程度。
- line_type：线条的类型,见CVLINE的描述。
- shift：圆心坐标点和数轴的精度。

```c++
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
using namespace cv;
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    Mat src = imread("./../../images/book.bmp");
    //画椭圆
    ellipse(src,Point(100,100),Size(100,50),30,0,360,Scalar(0,0,255),1,8);

    imshow("circle",src);
    waitKey(0);
    return 0;
}
```

<center>
    <img src="https://gitee.com/tianzhendong/img/raw/master//images/20220221093515.png" width=50%>
    <br>
    画椭圆
</center>

### 多边形`polylines()`

```c++
void polylines(InputOutputArray img, InputArrayOfArrays pts,
                            bool isClosed, const Scalar& color,
                            int thickness = 1, int lineType = LINE_8, int shift = 0 );
```

### 添加文本`putText()`

```c++
void putText( InputOutputArray img, const String& text, Point org,
                         int fontFace, double fontScale, Scalar color,
                         int thickness = 1, int lineType = LINE_8,
                         bool bottomLeftOrigin = false );
```

- img：图像
- text：文本，string格式
- org：放置的点位置（数据开始的左下角）
- fontFace：字体类型
- fontScale：字体比例
- color：字体颜色
- thickness：字体粗细
- lineType：线形

```c++
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
using namespace cv;
int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    Mat src = imread("./../../images/book.bmp");
    //文本
    putText(src,"text",Point(100,100),FONT_HERSHEY_PLAIN,4,Scalar(255,0,0),2,LINE_AA);

    imshow("circle",src);
    waitKey(0);
    return 0;
}
```

<center>
    <img src="https://gitee.com/tianzhendong/img/raw/master//images/20220221094849.png" width=50%>
    <br>
    文本
</center>

## 鼠标事件

参考[OpenCV中文官方文档](http://www.woshicver.com/ThirdSection/2_4_%E9%BC%A0%E6%A0%87%E4%BD%9C%E4%B8%BA%E7%94%BB%E7%AC%94/)

### `setMouseCallback()`函数

```c++
void setMouseCallback(const String& winname, MouseCallback onMouse, void* userdata = 0);
```

- winname: 窗口名称
- OnMouse : 鼠标事件的回调函数
- userdata : 传递给回调函数的参数，可选

```c++
typedef void (*MouseCallback)(int event, int x, int y, int flags, void* userdata);
```

参数说明：

- event: 鼠标事件
- x : 鼠标事件的x坐标
- y : 鼠标事件的y坐标
- flags: 鼠标事件的标志
- userdata : 传递给回调函数的参数，可选

### event事件

- CV_EVENT_MOUSEMOVE ：鼠标移动
- CV_EVENT_LBUTTONDOWN : 鼠标左键按下
- CV_EVENT_RBUTTONDOWN : 鼠标右键按下
- CV_EVENT_MBUTTONDOWN ： 鼠标中键按下
- CV_EVENT_LBUTTONUP ： 鼠标左键放开
- CV_EVENT_RBUTTONUP ： 右键放开
- CV_EVENT_MBUTTONUP ： 中键放开
- CV_EVENT_LBUTTONDBLCLK ： 左键双击
- CV_EVENT_RBUTTONDBLCLK ： 右键双击
- CV_EVENT_MBUTTONDBLCLK ： 中键双击
- CV_EVENT_MOUSEWHEEL ： 鼠标向前（+）或向后（-）滑动
- CV_EVENT_MOUSEHWHEEL ： 鼠标向右（+）或向左（-）滑动

### flags

- CV_EVENT_FLAG_LBUTTON ：左键拖拽
- CV_EVENT_FLAG_RBUTTON ： 右键拖拽
- CV_EVENT_FLAG_MBUTTON ： 中键拖拽
- CV_EVENT_FLAG_CTRLKEY ： Ctrl按下不放
- CV_EVENT_FLAG_SHIFTKEY ： shift按下不放
- CV_EVENT_FLAG_ALTKEY ： alt按下不放

### 示例1-双击画圆

> 目标

双击鼠标左键时，在图像上以当前鼠标点为圆心绘制圆心和空心圆

> 代码

```c++
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
using namespace cv;

Mat src = imread("./../../images/book.bmp");
//鼠标事件回调函数，双击左键时，以鼠标点为圆心，半径为100，画空心圆
void mouseCallBack(int event,int x,int y, int flags, void *data){
    if (event == EVENT_LBUTTONDBLCLK){
        //画圆
        circle(src,Point(x,y),100,Scalar(255,0,0),1);
        //画圆心
        circle(src,Point(x,y),1,Scalar(255,0,0),-1);
    }
}

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
	//创建一个窗口
    namedWindow("mouseDemo");
    //鼠标事件回调
    setMouseCallback("mouseDemo",mouseCallBack);
    while (1) {
        imshow("mouseDemo",src);	//显示
        if(waitKey(20) == 'q'){		//按q退出
            break;
        };
    }
    destroyAllWindows();
    return 0;
}
```

> 结果

<center>
    <img src="https://gitee.com/tianzhendong/img/raw/master//images/image-20220221110443579.png" width = 50%>
    <br>
    鼠标事件
</center>

### 示例2-拖拽画矩形

> 目标

- modeFlag为真时，按下并拖拽鼠标画矩形
- modeFlag为假时，按下并拖拽鼠标时画连续的圆

> 代码

```c++
#include <QCoreApplication>
#include <QString>
#include "opencv2/opencv.hpp"
using namespace cv;

bool drawingFlag = false;//按下鼠标为真
bool modeFlag = true; //为真时绘制矩形，如果要绘制圆，把他设置为false即可
int ix = -1;
int iy = -1;

Mat src = imread("./../../images/book.bmp");
//鼠标事件回调函数
void mouseCallBack(int event,int x,int y, int flags, void *data){

    if (event == EVENT_LBUTTONDOWN){  //鼠标左键按下
        drawingFlag = true;
        ix = x;
        iy = y;
    }
    else if(event == EVENT_MOUSEMOVE){  //鼠标移动
        if(drawingFlag == true){    //鼠标左键按下
            if(modeFlag == true){   //画矩形
                //以当前点坐标为矩形左上角，按下点坐标为矩形右下角，画矩形
                rectangle(src,Point(ix,iy),Point(x,y),Scalar(0,255,0),-1);
            }
            else{   //画圆
                circle(src,Point(x,y),50,Scalar(255,0,0),1);
                //画圆心
                circle(src,Point(x,y),1,Scalar(255,0,0),-1);
            }
        }
    }
    else if(event == EVENT_LBUTTONUP){  //鼠标左键松开
        drawingFlag = false;
        if(modeFlag == true){
            //以当前点坐标为矩形左上角，按下点坐标为矩形右下角，画矩形
            rectangle(src,Point(ix,iy),Point(x,y),Scalar(0,255,0),-1);
        }
        else{   //画圆
            circle(src,Point(x,y),50,Scalar(255,0,0),1);
            //画圆心
            circle(src,Point(x,y),1,Scalar(255,0,0),-1);
        }
    }
}


int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    namedWindow("mouseDemo");
    //鼠标
    setMouseCallback("mouseDemo",mouseCallBack);
    while (1) {
        imshow("mouseDemo",src);
        if(waitKey(20) == 'q'){
            break;
        };
    }
    destroyAllWindows();
    return 0;
}
```



> 结果

<center>
    <img src = "https://gitee.com/tianzhendong/img/raw/master//images/image-20220221113648613.png" width = 45% >
    <img src ="https://gitee.com/tianzhendong/img/raw/master//images/20220221113552.png" width = 45%>
    <br>
    左：modeFlag=true ====================右：modeFlag=false
</center>



## 轨迹栏作为调色板

### 创建轨迹栏

函数：

```c
int createTrackbar(const String& trackbarname, const String& winname,
                              int* value, int count,
                              TrackbarCallback onChange = 0,
                              void* userdata = 0);
```

参数：

- trackbarname：轨迹栏名称
- winname：窗口
- value：指向滑块位置的整形指针
- count：滑块的最大位置。最小的位置总是0
- onChange：滑块值改变时时要调用的函数。默认为0，可选
- userdata：按原样传递给回调函数的用户数据。默认为0，可选

### 获取轨迹栏值

函数：

```c
int getTrackbarPos(const String& trackbarname, const String& winname);
```

参数

- trackbarname：轨迹栏名称
- winname：窗口

### 实例

注意，OpenCV中使用的是GBR(蓝绿红)

> 代码

```c
#include <QCoreApplication>
#include "opencv2/opencv.hpp"
#include "trackbar.h"
using namespace cv;

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    Mat src = imread("./../../images/book.bmp");
    namedWindow("img");
    //创建指向滑块位置的整形指针
    int *p1 = nullptr;
    int *p2 = nullptr;
    int *p3 = nullptr;
    //创建轨迹栏
    createTrackbar("R","img",p1,255);
    createTrackbar("G","img",p2,255);
    createTrackbar("B","img",p3,255);
    while (1) {
        imshow("img",src);
        if(waitKey(20) == 27){   //esc键退出
            break;
        }
        //获取轨迹当前位置
        int r = getTrackbarPos("R","img");
        int g = getTrackbarPos("G","img");
        int b = getTrackbarPos("B","img");

        //画实心圆
        //注意，R,G,B分别代表红、绿、蓝，OpenCV中使用的是GBR
        circle(src,Point(100,100),100,Scalar(b,g,r),-1);
    }

    destroyAllWindows();

    return 0;
}
```

> 效果



<center>
    <img src=https://gitee.com/tianzhendong/img/raw/master//images/202202211432588.gif width=50%>
    <br>
    轨迹条
</center>
## ====================分割线=========================

后面内容采用python，内容基本拷贝http://codec.wang/#/opencv/

## 图像基本操作

http://codec.wang/#/opencv/start/04-basic-operations

### 获取和修改像素值

- 方法1，通过行列坐标值获取

通过行列的坐标来获取某像素点的值，对于彩色图，结果是B,G,R三个值的列表，对于灰度图或单通道图，只有一个值。

- 方法2，通过`item()`函数和`itemset()`函数

注意：

- OpenCV采用BGR(蓝绿红)顺序
- 该操作只是内存中的img像素点值变了，因为没有保存，所以原图并没有更改。

```python
# 图像基本操作
# 访问和修改图片像素点的值
# 获取图片的宽、高、通道数等属性
# 了解感兴趣区域ROI
# 分离和合并图像通道
import cv2 as cv

img = cv.imread("./../../images/book.bmp")
# 方法1，通过行列坐标值获取
# opencv采用BGR(蓝绿红)顺序
px = img[100, 200]
print("行=100,列=200处的像素值为：", px)     # 行=100,列=200处的像素值为： [242 235 238]

# 只获取蓝色的值，BGR(蓝绿红)下标为0，红色下标为2
px_blue = img[100, 200, 0]
print("蓝色值：", px_blue)      # 蓝色值： 242

# 修改像素
img[100, 200] = [255, 255, 255]
print(img[100, 200])    # [255 255 255]

#=====================================#
# 方法2，通过item()函数和itemset()函数
# 注意，BGR只能一个一个的查询和设置，不能一起
print(img.item(100, 200, 0))    # 255
img.itemset((100, 200, 0), 0)
print(img.item(100, 200, 0))    # 0
```

### 图片属性

- `img.shape()`

获取图像的形状，图片是彩色的话，返回一个包含**行数（高度）、列数（宽度）和通道数**的元组，灰度图只返回行数和列数

- `img.dtype`

获取图像数据类型，多数错误是因为数据类型不对导致的，所以健壮的代码应该对这个属性加以判断。

- `img.size`

获取图像总像素数

```python
import cv2 as cv
# img.shape获取图像的形状，图片是彩色的话，返回一个包含行数（高度）、列数（宽度）和通道数的元组，灰度图只返回行数和列数：
print(img.shape)        # (513, 659, 3)
# img.dtype获取图像数据类型
print(img.dtype)    # uint8
# img.size获取图像总像素数
print(img.size)     # 1014201
print(513*659*3)    # 1014201
```

### ROI感兴趣区域

[ROI](https://baike.baidu.com/item/ROI/1125333#viewPageContent)：Region of Interest，感兴趣区域。什么意思呢？比如我们要检测眼睛，因为眼睛肯定在脸上，所以我们感兴趣的只有脸这部分，其他都不care，所以可以单独把脸截取出来，这样就可以大大节省计算量，提高运行速度。

截取ROI非常简单，指定图片的范围即可

```python
import cv2 as cv
cv.imshow('img', img)
roi = img[100:200, 270:500]
cv.imshow('roi', roi)
cv.waitKey(0)
```



![image-20220221200702890](https://gitee.com/tianzhendong/img/raw/master//images/202202212007164.png)

### 通道分割与合并

彩色图的BGR三个通道是可以分开单独访问的，也可以将单独的三个通道合并成一副图像。分别使用`cv2.split()`和`cv2.merge()`：

`split()`函数比较耗时，**更高效的方式是用numpy中的索引**，如提取R通道：

```python
# 通道分割与合并
b, g, r = cv.split(img)
img = cv.merge((b, g, r))
cv.imshow('blue1', b) 
# 方法2
r = img[:, :, 0]
cv.imshow('red', r)
cv.waitKey(0)
```

![image-20220221201534258](https://gitee.com/tianzhendong/img/raw/master//images/202202221432476.png)

### 小结

- `img[y,x]`获取/设置像素点值，`img.shape`：图片的形状（行数、列数、通道数）,`img.dtype`：图像的数据类型。
- `img[y1:y2,x1:x2]`进行ROI截取，`cv2.split()/cv2.merge()`通道分割/合并。更推荐的获取单通道方式：`b = img[:, :, 0]`。

### 接口

- [cv2.split()](https://docs.opencv.org/4.0.0/d2/de8/group__core__array.html#ga0547c7fed86152d7e9d0096029c8518a)
- [cv2.merge()](https://docs.opencv.org/4.0.0/d2/de8/group__core__array.html#ga7d7b4d6c6ee504b30a20b1680029c7b4)



## 颜色空间转换

[查看原网页: codec.wang](http://codec.wang/#/opencv/start/05-changing-colorspaces)

### 目标

*   颜色空间转换，如BGR↔Gray，BGR↔HSV等
*   追踪视频中特定颜色的物体
*   OpenCV函数：`cv2.cvtColor()`, `cv2.inRange()`

### 教程

#### 颜色空间转换

```python
import cv2
img = cv2.imread('lena.jpg')
# 转换为灰度图
img_gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
cv2.imshow('img', img)
cv2.imshow('gray', img_gray)
cv2.waitKey(0)
```

`cv2.cvtColor()`用来进行颜色模型转换，参数1是要转换的图片，参数2是转换模式， `COLOR_BGR2GRAY`表示BGR→Gray，可用下面的代码显示所有的转换模式：

```python
flags = [i for i in dir(cv2) if i.startswith('COLOR_')]
print(flags)
```

> 经验之谈：颜色转换其实是数学运算，如灰度化最常用的是：`gray=R*0.299+G*0.587+B*0.114`。

#### 视频中特定颜色物体追踪

[HSV](https://baike.baidu.com/item/HSV/547122)是一个常用于颜色识别的模型，相比BGR更易区分颜色，转换模式用`COLOR_BGR2HSV`表示。

> 经验之谈：OpenCV中色调H范围为\[0,179\]，饱和度S是\[0,255\]，明度V是\[0,255\]。虽然H的理论数值是0°~360°，但8位图像像素点的最大值是255，所以OpenCV中除以了2，某些软件可能使用不同的尺度表示，所以同其他软件混用时，记得归一化。

现在，我们实现一个使用HSV来只显示视频中蓝色物体的例子，步骤如下：

1.  捕获视频中的一帧
2.  从BGR转换到HSV
3.  提取蓝色范围的物体
4.  只显示蓝色物体

![image-20220222101034807](https://gitee.com/tianzhendong/img/raw/master//images/202202221432821.png)

```python
# 捕捉视频中的蓝色
cap = cv2.VideoCapture(0)

# # 获取蓝色的上限
# blue = np.uint8([[[255, 0, 0]]])
# hsv_blue = cv2.cvtColor(blue, cv2.COLOR_BGR2HSV)
# print(hsv_blue)

# 蓝色的范围，不同光照条件下不一样
lower_blue = np.array([100, 110, 110])
upper_blue = np.array([130, 255, 255])

while (1):
    ret, frame = cap.read()

    # bgr2hsv
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    # 3.inRange()：介于lower/upper之间的为白色，其余黑色
    mask = cv2.inRange(hsv, lower_blue, upper_blue)

    # 4.只保留原图中的蓝色部分
    res = cv2.bitwise_and(frame, frame, mask=mask)

    cv2.imshow('frame', frame)
    cv2.imshow('mask', mask)
    cv2.imshow('res', res)

    if cv2.waitKey(1) == 27:
        break
# cv2.waitKey(0)
cv2.destroyAllWindows()
```

其中，`bitwise_and()`函数暂时不用管，后面会讲到。那蓝色的HSV值的上下限lower和upper范围是怎么得到的呢？其实很简单，我们先把标准蓝色的BGR值用`cvtColor()`转换下：

```python
blue = np.uint8([[[255, 0, 0]]])
hsv_blue = cv2.cvtColor(blue, cv2.COLOR_BGR2HSV)
print(hsv_blue)  # [[[120 255 255]]]
```

结果是\[120, 255, 255\]，所以，我们把蓝色的范围调整成了上面代码那样。

### 小结

*   `cv2.cvtColor()`函数用来进行颜色空间转换，常用BGR↔Gray，BGR↔HSV。
*   HSV颜色模型常用于颜色识别。要想知道某种颜色在HSV下的值，可以将它的BGR值用`cvtColor()`转换得到。

### 接口文档

*   [cv2.cvtColor()](https://docs.opencv.org/4.0.0/d8/d01/group__imgproc__color__conversions.html#ga397ae87e1288a81d2363b61574eb8cab)
*   [cv2.inRange()](https://docs.opencv.org/4.0.0/d2/de8/group__core__array.html#ga48af0ab51e36436c5d04340e036ce981)
*   [cv2.bitwise\_and()](https://docs.opencv.org/4.0.0/d2/de8/group__core__array.html#ga60b4d04b251ba5eb1392c34425497e14)

[查看原网页: codec.wang](http://codec.wang/#/opencv/start/05-changing-colorspaces)

## 阈值分割

![image-20220222101259721](https://gitee.com/tianzhendong/img/raw/master//images/202202221432655.png)

### 目标

- 使用固定阈值、自适应阈值和Otsu阈值法"二值化"图像
- OpenCV函数：`cv2.threshold()`, `cv2.adaptiveThreshold()`

### 固定阈值分割

固定阈值分割很直接，一句话说就是像素点值大于阈值变成一类值，小于阈值变成另一类值。

```python
import cv2
# 读取，通过第二个参数转为灰度图
img = cv2.imread("images/book.bmp", 0)
cv2.imshow('img', img)

# 固定阈值分割
ret, th = cv2.threshold(img, 170, 255, cv2.THRESH_BINARY)
cv2.imshow('img222', th)
cv2.waitKey(0)
cv2.destroyAllWindows()
```

![image-20220222102129249](https://gitee.com/tianzhendong/img/raw/master//images/202202221021443.png)

`cv2.threshold()`用来实现阈值分割，ret是return value缩写，代表当前的阈值，暂时不用理会。函数有4个参数：

- 参数1：要处理的原图，**一般是灰度图**
- 参数2：设定的阈值
- 参数3：对于`THRESH_BINARY`、`THRESH_BINARY_INV`阈值方法所选用的最大阈值，**一般为255**
- 参数4：阈值的方式，主要有5种，详情：[ThresholdTypes](https://docs.opencv.org/4.0.0/d7/d1b/group__imgproc__misc.html#gaa9e58d2860d4afa658ef70a9b1115576)

```python
import cv2
import matplotlib.pyplot as plt
import matplotlib
matplotlib.use('TKAgg')
# 读取，通过第二个参数转为灰度图
img = cv2.imread("images/book.bmp", 0)
# 应用5种不同的阈值方法
ret, th1 = cv2.threshold(img, 127, 255, cv2.THRESH_BINARY)
ret, th2 = cv2.threshold(img, 127, 255, cv2.THRESH_BINARY_INV)
ret, th3 = cv2.threshold(img, 127, 255, cv2.THRESH_TRUNC)
ret, th4 = cv2.threshold(img, 127, 255, cv2.THRESH_TOZERO)
ret, th5 = cv2.threshold(img, 127, 255, cv2.THRESH_TOZERO_INV)

titles = ['Original', 'BINARY', 'BINARY_INV', 'TRUNC', 'TOZERO', 'TOZERO_INV']
images = [img, th1, th2, th3, th4, th5]

# 使用Matplotlib显示
for i in range(6):
    plt.subplot(2, 3, i + 1)
    plt.imshow(images[i], 'gray')
    plt.title(titles[i], fontsize=8)
    plt.xticks([]), plt.yticks([])  # 隐藏坐标轴

plt.show()
```

![image-20220222103446908](https://gitee.com/tianzhendong/img/raw/master//images/202202221432240.png)

>  经验之谈：很多人误以为阈值分割就是[二值化](https://baike.baidu.com/item/二值化)。从上图中可以发现，两者并不等同，阈值分割结果是两类值，而不是两个值

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202221432870.jpeg)

### 自适应阈值分割

固定阈值是在整幅图片上应用一个阈值进行分割，_它并不适用于明暗分布不均的图片_。 `cv2.adaptiveThreshold()`自适应阈值会每次取图片的一小部分计算阈值，这样图片不同区域的阈值就不尽相同。它有5个参数

- 参数1：要处理的原图
- 参数2：最大阈值，一般为255
- 参数3：小区域阈值的计算方式
  - `ADAPTIVE_THRESH_MEAN_C`：小区域内取均值
  - `ADAPTIVE_THRESH_GAUSSIAN_C`：小区域内加权求和，权重是个高斯核
- 参数4：阈值方法，只能使用`THRESH_BINARY`、`THRESH_BINARY_INV`，具体见前面所讲的阈值方法
- 参数5：小区域的面积，如11就是11*11的小块
- 参数6：最终阈值等于小区域计算出的阈值再减去此值

```python
# 自适应阈值分割cv2.adaptiveThreshold()
# - 参数1：要处理的原图
# - 参数2：最大阈值，一般为255
# - 参数3：小区域阈值的计算方式
#   - `ADAPTIVE_THRESH_MEAN_C`：小区域内取均值
#   - `ADAPTIVE_THRESH_GAUSSIAN_C`：小区域内加权求和，权重是个高斯核
# - 参数4：阈值方法，只能使用`THRESH_BINARY`、`THRESH_BINARY_INV`，具体见前面所讲的阈值方法
# - 参数5：小区域的面积，如11就是11*11的小块
# - 参数6：最终阈值等于小区域计算出的阈值再减去此值

import cv2
import matplotlib.pyplot as plt
import matplotlib

matplotlib.use('TKAgg')

img = cv2.imread("images/book.bmp", 0)

# 固定阈值分割
ret, th1 = cv2.threshold(img, 127, 255, cv2.THRESH_BINARY)

# 自适应阈值分割
th2 = cv2.adaptiveThreshold(img, 255, cv2.ADAPTIVE_THRESH_MEAN_C,
                            cv2.THRESH_BINARY, 11, 4)
th3 = cv2.adaptiveThreshold(img, 255, cv2.ADAPTIVE_THRESH_GAUSSIAN_C,
                            cv2.THRESH_BINARY, 11, 4)
th4 = cv2.adaptiveThreshold(img, 255, cv2.ADAPTIVE_THRESH_MEAN_C,
                            cv2.THRESH_BINARY_INV, 11, 4)
th5 = cv2.adaptiveThreshold(img, 255, cv2.ADAPTIVE_THRESH_GAUSSIAN_C,
                            cv2.THRESH_BINARY_INV, 11, 4)

titles = [
    'Original', 'Global(v = 127)', 'Adaptive Mean-BINARY',
    'Adaptive Gaussian-BINARY', 'Adaptive Mean-binary_BINARY_INV',
    'Adaptive Gaussian_BINARY_INV'
]
images = [img, th1, th2, th3, th4, th5]

for i in range(6):
    plt.subplot(3, 2, i + 1), plt.imshow(images[i], 'gray')
    plt.title(titles[i], fontsize=8)
    plt.xticks([]), plt.yticks([])
plt.show()
```



![image-20220222105702776](https://gitee.com/tianzhendong/img/raw/master//images/202202221432388.png)

### [Otsu阈值](http://codec.wang/#/opencv/start/06-image-thresholding?id=otsu阈值)

在前面固定阈值中，我们是随便选了一个阈值如127，那如何知道我们选的这个阈值效果好不好呢？答案是：不断尝试，所以这种方法在很多文献中都被称为经验阈值。[Otsu阈值法](https://baike.baidu.com/item/otsu/16252828)就提供了一种自动高效的二值化方法，不过我们直方图还没学，这里暂时略过。

### [小结](http://codec.wang/#/opencv/start/06-image-thresholding?id=小结)

- `cv2.threshold()`用来进行固定阈值分割。固定阈值不适用于光线不均匀的图片，所以用 `cv2.adaptiveThreshold()`进行自适应阈值分割。
- 二值化跟阈值分割并不等同。针对不同的图片，可以采用不同的阈值方法。

### [接口文档](http://codec.wang/#/opencv/start/06-image-thresholding?id=接口文档)

- [cv2.threshold()](https://docs.opencv.org/4.0.0/d7/d1b/group__imgproc__misc.html#gae8a4a146d1ca78c626a53577199e9c57)
- [cv2.adaptiveThreshold()](https://docs.opencv.org/4.0.0/d7/d1b/group__imgproc__misc.html#ga72b913f352e4a1b1b397736707afcde3)
- [cv2.ThresholdTypes()](https://docs.opencv.org/4.0.0/d7/d1b/group__imgproc__misc.html#gaa9e58d2860d4afa658ef70a9b1115576)

## 图像几何变换

### [目标](http://codec.wang/#/opencv/start/07-image-geometric-transformation?id=目标)

- 实现旋转、平移和缩放图片
- OpenCV函数：`cv2.resize()`, `cv2.flip()`, `cv2.warpAffine()`

### [教程](http://codec.wang/#/opencv/start/07-image-geometric-transformation?id=教程)

> 图像的几何变换从原理上看主要包括两种：基于2×3矩阵的仿射变换（平移、缩放、旋转和翻转等）、基于3×3矩阵的透视变换，感兴趣的小伙伴可参考[番外篇：仿射变换与透视变换](http://codec.wang/#/Extra-05-Warpaffine-Warpperspective/)。

#### [缩放图片](http://codec.wang/#/opencv/start/07-image-geometric-transformation?id=缩放图片)

缩放就是调整图片的大小，使用`cv2.resize()`函数实现缩放。可以按照比例缩放，也可以按照指定的大小缩放：

```python
# 图像几何变换
# - 实现旋转、平移和缩放图片
# - OpenCV函数：`cv2.resize()`, `cv2.flip()`, `cv2.warpAffine()`
import cv2

img = cv2.imread("images/book.bmp", 0)

# 原图大小形状
print(img.shape)    # (513, 659)

# 按照指定的宽度、高度缩放图片，注意x和y的顺序
res = cv2.resize(img, (200, 100))
print(res.shape)    # (100, 200)

res2 = cv2.resize(img, None, fx=2, fy=2, interpolation=cv2.INTER_LINEAR)
print(res2.shape)  # (1026, 1318)

cv2.imshow('origin', img)
cv2.imshow('shrink', res)
cv2.imshow('zoom', res2)

cv2.waitKey(0)
```

我们也可以指定缩放方法`interpolation`，更专业点叫插值方法，默认是`INTER_LINEAR`，全部可以参考：[InterpolationFlags](https://docs.opencv.org/4.0.0/da/d54/group__imgproc__transform.html#ga5bb5a1fea74ea38e1a5445ca803ff121)





#### [翻转图片](http://codec.wang/#/opencv/start/07-image-geometric-transformation?id=翻转图片)

镜像翻转图片，可以用`cv2.flip()`函数：

```python
# 图像几何变换
# - 实现旋转、平移和缩放图片
# - OpenCV函数：`cv2.resize()`, `cv2.flip()`, `cv2.warpAffine()`
import cv2
import matplotlib.pyplot as plt
import matplotlib

matplotlib.use('TKAgg')

img = cv2.imread("images/book.bmp", 0)

# ===========================resize()=============================

# =======================flip()=====================================
dst1 = cv2.flip(img, 0)
dst2 = cv2.flip(img, -1)
dst3 = cv2.flip(img, 1)
images = [img, dst1, dst3, dst2]
titles = ['origin', 'par=0垂直(x)', 'par=1水平(y)', 'par=-1水平垂直']
for i in range(4):
    plt.subplot(2, 2, i + 1), plt.imshow(images[i], 'gray')
    plt.title(titles[i], fontsize=8)
    plt.xticks([]), plt.yticks([])
plt.show()

cv2.waitKey(0)
```

其中，

- 参数2 = 0：垂直翻转(沿x轴)
- 参数2 > 0: 水平翻转(沿y轴)
- 参数2 < 0: 水平垂直翻转。

![image-20220222112453165](https://gitee.com/tianzhendong/img/raw/master//images/202202221432925.png)

#### [平移图片](http://codec.wang/#/opencv/start/07-image-geometric-transformation?id=平移图片)

要平移图片，我们需要定义下面这样一个矩阵，tx,ty是向x和y方向平移的距离：

$M = \left[ \begin{matrix} 1 & 0 & t_x \newline 0 & 1 & t_y \end{matrix} \right]$

平移是用仿射变换函数`cv2.warpAffine()`实现的：

```python
# 平移图片
import numpy as np

rows, cols = img.shape[:2]

# 定义平移矩阵，需要是numpy的float32类型
# x轴平移100，y轴平移50
M = np.float32([[1, 0, 100], [0, 1, 50]])
# 用仿射变换实现平移
dst = cv2.warpAffine(img, M, (cols, rows))

cv2.imshow('shift', dst)
cv2.waitKey(0)
```

![image-20220222113457715](https://gitee.com/tianzhendong/img/raw/master//images/202202221432533.png)

#### [旋转图片](http://codec.wang/#/opencv/start/07-image-geometric-transformation?id=旋转图片)

旋转同平移一样，也是用仿射变换实现的，因此也需要定义一个变换矩阵。OpenCV直接提供了 `cv2.getRotationMatrix2D()`函数来生成这个矩阵，该函数有三个参数：

- 参数1：图片的旋转中心
- 参数2：旋转角度(正：逆时针，负：顺时针)
- 参数3：缩放比例，0.5表示缩小一半

```python
# 45°旋转图片并缩小一半
rows, cols = img.shape[:2]
M = cv2.getRotationMatrix2D((cols / 2, rows / 2), -45, 0.5)
dst7 = cv2.warpAffine(img, M, (cols, rows))
cv2.imshow("img", img)
cv2.imshow("rot", dst7)
cv2.waitKey(0)
```

![逆时针旋转45°并缩放](https://gitee.com/tianzhendong/img/raw/master//images/202202221432305.png)

### [小结](http://codec.wang/#/opencv/start/07-image-geometric-transformation?id=小结)

- `cv2.resize()`缩放图片，可以按指定大小缩放，也可以按比例缩放。
- `cv2.flip()`翻转图片，可以指定水平/垂直/水平垂直翻转三种方式。
- 平移/旋转是靠仿射变换`cv2.warpAffine()`实现的。

### [接口文档](http://codec.wang/#/opencv/start/07-image-geometric-transformation?id=接口文档)

- [cv2.resize()](https://docs.opencv.org/4.0.0/da/d54/group__imgproc__transform.html#ga47a974309e9102f5f08231edc7e7529d)
- [cv2.filp()](https://docs.opencv.org/4.0.0/d2/de8/group__core__array.html#gaca7be533e3dac7feb70fc60635adf441)
- [cv2.warpAffine()](https://docs.opencv.org/4.0.0/da/d54/group__imgproc__transform.html#ga0203d9ee5fcd28d40dbc4a1ea4451983)
- [cv2.getRotationMatrix2D()](https://docs.opencv.org/4.0.0/da/d54/group__imgproc__transform.html#gafbbc470ce83812914a70abfb604f4326)

## [番外篇: 代码性能优化](http://codec.wang/#/opencv/start/extra-01-code-optimization?id=番外篇-代码性能优化)

学习如何评估和优化代码性能。（本节还没更新完…………）

完成一项任务很重要，高效地完成更重要。图像处理是对矩阵的操作，数据量巨大。如果代码写的不好，性能差距将很大，所以这节我们来了解下如何评估和提升代码性能。

### [评估代码运行时间](http://codec.wang/#/opencv/start/extra-01-code-optimization?id=评估代码运行时间)

```python
import cv2

start = cv2.getTickCount()
# 这里写测试代码...
end = cv2.getTickCount()
print((end - start) / cv2.getTickFrequency())
```

这段代码就是用来测量程序运行时间的（单位：s），其中`cv2.getTickCount()`函数得到电脑启动以来的时钟周期数，`cv2.getTickFrequency()`返回你电脑的主频，前后相减再除以主频就是你代码的运行时间（这样解释并不完全准确，但能理解就行）。

另外，也可以用Python中的time模块计时：

> The function `time.clock()` has been removed, after having been deprecated since Python 3.3: use `time.perf_counter()` or `time.process_time()` instead, depending on your requirements, to have well-defined behavior.

```python
import time

start = time.process_time()
# 这里写测试代码...
end = time.process_time()
print(end - start)
```

> 经验之谈：如果你使用的是[IPython](https://baike.baidu.com/item/ipython)或[Jupyter Notebook](https://baike.baidu.com/item/Jupyter)开发环境，性能分析将会非常方便，详情请参考：[Timing and Profiling in IPython](http://pynash.org/2013/03/06/timing-and-profiling/)

### [优化原则](http://codec.wang/#/opencv/start/extra-01-code-optimization?id=优化原则)

- 数据元素少时用Python语法，数据元素多时用Numpy：

```python
x = 10
z = np.uint8([10])

# 尝试比较下面三句话各自的运行时间
y = x * x * x   # (1.6410249677846285e-06)
y = x**3        # (2.461537451676943e-06)
y = z * z * z   # 最慢 (3.1179474387907945e-05) 
```

所以Numpy的运行速度并不一定比Python本身语法快，元素数量较少时，请用Python本身格式。

- 尽量避免使用循环，尤其嵌套循环，因为极其慢！！！
- 优先使用OpenCV/Numpy中封装好的函数
- 尽量将数据向量化，变成Numpy的数据格式
- 尽量避免数组的复制操作

### [接口文档](http://codec.wang/#/opencv/start/extra-01-code-optimization?id=接口文档)

- [cv2.getTickCount()](https://docs.opencv.org/4.0.0/db/de0/group__core__utils.html#gae73f58000611a1af25dd36d496bf4487)
- [cv2.getTickFrequency()](https://docs.opencv.org/4.0.0/db/de0/group__core__utils.html#ga705441a9ef01f47acdc55d87fbe5090c)



## [番外篇: 无损保存和Matplotlib](http://codec.wang/#/opencv/start/extra-02-high-quality-save-and-matplotlib?id=番外篇-无损保存和matplotlib)

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202221432385.jpeg)

了解常用图片格式和OpenCV高质量保存图片的方式，学习如何使用Matplotlib显示OpenCV图像。

### [无损保存](http://codec.wang/#/opencv/start/extra-02-high-quality-save-and-matplotlib?id=无损保存)

事实上，我们日常看到的大部分图片都是压缩过的，那么都有哪些常见的图片格式呢？

#### [常用图片格式](http://codec.wang/#/opencv/start/extra-02-high-quality-save-and-matplotlib?id=常用图片格式)

- bmp
  - 全称：Bitmap
  - **不压缩**
- jpg
  - 全称：Joint Photographic Experts Group
  - **有损压缩方式**
- png
  - 全称：Portable Network Graphics
  - **无损压缩方式**

简单来说，同一个文件保存成不同的格式后，文件大小上bmp肯定是最大的，而png和jpg，不同的压缩比结果会有所不同。可以用画图工具新建一副100×100的图像，分别保存成这三种格式来验证：

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202221432573.jpeg)

#### [高质量保存](http://codec.wang/#/opencv/start/extra-02-high-quality-save-and-matplotlib?id=高质量保存)

用`cv2.imwrite()`保存图片时，可以传入第三个参数（请参考[接口文档](http://codec.wang/#/)），用于控制保存质量：

- `cv2.IMWRITE_JPEG_QUALITY`：jpg质量控制，取值0~100，值越大，质量越好，默认为95
- `cv2.IMWRITE_PNG_COMPRESSION`：png质量控制，取值0~9，值越大，压缩比越高，默认为1

还有诸如`CV_IMWRITE_WEBP_QUALITY`的参量，不常用，请参考：[ImwriteFlags](https://docs.opencv.org/4.0.0/d4/da8/group__imgcodecs.html#ga292d81be8d76901bff7988d18d2b42ac>)。

举例来说，原图lena.jpg的分辨率是350×350，大小49.7KB。我们把它转成不同格式看下：

```python
import cv2

new_img = cv2.imread('lena.jpg')

# bmp
cv2.imwrite('img_bmp.bmp',new_img) # 文件大小：359KB

# jpg 默认95%质量
cv2.imwrite('img_jpg95.jpg',new_img) # 文件大小：52.3KB
# jpg 20%质量
cv2.imwrite('img_jpg20.jpg',new_img,[int(cv2.IMWRITE_JPEG_QUALITY),20]) # 文件大小：8.01KB
# jpg 100%质量
cv2.imwrite('img_jpg100.jpg',new_img,[int(cv2.IMWRITE_JPEG_QUALITY),100]) # 文件大小：82.5KB

# png 默认1压缩比
cv2.imwrite('img_png1.png',new_img) # 文件大小：240KB
# png 9压缩比
cv2.imwrite('img_png9.png',new_img,[int(cv2.IMWRITE_PNG_COMPRESSION),9]) # 文件大小：207KB 
```

可以看到：

- bmp文件是最大的，没有任何压缩（1个像素点1byte，3通道的彩色图总大小：350×350×3/1024 ≈ 359 KB）
- jpg/png本身就有压缩的，所以就算是100%的质量保存，体积也比bmp小很多
- jpg的容量优势很明显，这也是它为什么如此流行的原因

> 思考：为什么原图49.7KB，保存成bmp或其他格式反而大了呢？

这是个很有趣的问题，很多童鞋都问过我。这里需要明确的是保存新格式时，**容量大小跟原图的容量没有直接关系，而是取决于原图的分辨率大小和原图本身的内容（压缩方式）**，所以lena.jpg保存成不压缩的bmp格式时，容量大小就是固定的350×350×3/1024 ≈ 359 KB；另外，容量变大不代表画质提升噢，不然就逆天了~~

- 

### [Matplotlib](http://codec.wang/#/opencv/start/extra-02-high-quality-save-and-matplotlib?id=matplotlib)

Matplotlib是Python的一个很常用的绘图库，有兴趣的可以去[官网](http://codec.wang/#/www.matplotlib.org/)学习更多内容。

#### [显示灰度图](http://codec.wang/#/opencv/start/extra-02-high-quality-save-and-matplotlib?id=显示灰度图)

```python
import cv2
import matplotlib.pyplot as plt

img = cv2.imread('lena.jpg', 0)

# 灰度图显示，cmap(color map)设置为gray
plt.imshow(img, cmap='gray')
plt.show() 
```

结果如下：

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202221432385.jpeg)

#### [显示彩色图](http://codec.wang/#/opencv/start/extra-02-high-quality-save-and-matplotlib?id=显示彩色图)

**OpenCV中的图像是以BGR的通道顺序存储的**，但Matplotlib是以RGB模式显示的，所以直接在Matplotlib中显示OpenCV图像会出现问题，因此需要转换一下:

```python
import cv2
import matplotlib.pyplot as plt

img = cv2.imread('lena.jpg')
img2 = img[:, :, ::-1]
# 或使用
# img2 = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

# 显示不正确的图
plt.subplot(121),plt.imshow(img) 

# 显示正确的图
plt.subplot(122)
plt.xticks([]), plt.yticks([]) # 隐藏x和y轴
plt.imshow(img2)

plt.show()
```

> `img[:,:,0]`表示图片的蓝色通道，`img[:,:,::-1]`就表示BGR翻转，变成RGB，说明一下：

熟悉Python的童鞋应该知道，对一个字符串s翻转可以这样写：`s[::-1]`，'abc'变成'cba'，-1表示逆序。图片是二维的，所以完整地复制一副图像就是：

```python
img2 = img[:,:] # 写全就是：img2 = img[0:height,0:width]
```

而图片是有三个通道，相当于一个长度为3的字符串，所以通道翻转与图片复制组合起来便是`img[:,:,::-1]`。

结果如下：

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202221432307.jpeg)

#### [加载和保存图片](http://codec.wang/#/opencv/start/extra-02-high-quality-save-and-matplotlib?id=加载和保存图片)

不使用OpenCV，Matplotlib也可以加载和保存图片：

```python
import matplotlib.image as pli

img = pli.imread('lena.jpg')
plt.imshow(img)

# 保存图片，需放在show()函数之前
plt.savefig('lena2.jpg')
plt.show() 
```



## [番外篇: Otsu阈值法](http://codec.wang/#/opencv/start/extra-04-otsu-thresholding?id=番外篇-otsu阈值法)

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202221355242.jpeg)

大部分图像处理任务都需要先进行二值化操作，阈值的选取很关键，Otsu阈值法会自动计算阈值。

[Otsu阈值法](https://baike.baidu.com/item/otsu/16252828)（日本人大津展之提出的，也可称大津算法）非常适用于双峰图片，啥意思呢？

> [Otsu N. A threshold selection method from gray-level histograms[J\]. IEEE transactions on systems, man, and cybernetics, 1979, 9(1): 62-66.](https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=4310076)

### [什么是双峰图片？](http://codec.wang/#/opencv/start/extra-04-otsu-thresholding?id=什么是双峰图片？)

双峰图片就是指图片的灰度直方图上有两个峰值，直方图就是每个值（0~255）的像素点个数统计，后面会详细介绍。

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202221432213.jpeg)

Otsu算法假设这副图片由前景色和背景色组成，通过统计学方法（最大类间方差）选取一个阈值，将前景和背景尽可能分开，我们先来看下代码，然后详细说明下算法原理。

### [代码示例](http://codec.wang/#/opencv/start/extra-04-otsu-thresholding?id=代码示例)

下面这段代码对比了使用固定阈值和Otsu阈值后的不同结果：

**另外，对含噪点的图像，先进行滤波操作效果会更好。**

```python
import cv2
from matplotlib import pyplot as plt

img = cv2.imread('noisy.jpg', 0)

# 固定阈值法
ret1, th1 = cv2.threshold(img, 100, 255, cv2.THRESH_BINARY)

# Otsu阈值法
ret2, th2 = cv2.threshold(img, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)

# 先进行高斯滤波，再使用Otsu阈值法
blur = cv2.GaussianBlur(img, (5, 5), 0)
ret3, th3 = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)
```

下面我们用Matplotlib把原图、直方图和阈值图都显示出来：

```python
images = [img, 0, th1, img, 0, th2, blur, 0, th3]
titles = ['Original', 'Histogram', 'Global(v=100)',
          'Original', 'Histogram', "Otsu's",
          'Gaussian filtered Image', 'Histogram', "Otsu's"]

for i in range(3):
    # 绘制原图
    plt.subplot(3, 3, i * 3 + 1)
    plt.imshow(images[i * 3], 'gray')
    plt.title(titles[i * 3], fontsize=8)
    plt.xticks([]), plt.yticks([])

    # 绘制直方图plt.hist，ravel函数将数组降成一维
    plt.subplot(3, 3, i * 3 + 2)
    plt.hist(images[i * 3].ravel(), 256)
    plt.title(titles[i * 3 + 1], fontsize=8)
    plt.xticks([]), plt.yticks([])

    # 绘制阈值图
    plt.subplot(3, 3, i * 3 + 3)
    plt.imshow(images[i * 3 + 2], 'gray')
    plt.title(titles[i * 3 + 2], fontsize=8)
    plt.xticks([]), plt.yticks([])
plt.show()
```

![固定阈值 vs Otsu阈值](https://gitee.com/tianzhendong/img/raw/master//images/202202221432845.jpeg)

可以看到，Otsu阈值明显优于固定阈值，省去了不断尝试阈值判断效果好坏的过程。其中，绘制直方图时，使用了numpy中的[ravel()](https://docs.scipy.org/doc/numpy/reference/generated/numpy.ravel.html)函数，它会将原矩阵压缩成一维数组，便于画直方图。

### [Otsu算法详解](http://codec.wang/#/opencv/start/extra-04-otsu-thresholding?id=otsu算法详解)

Otsu阈值法将整幅图分为前景（目标）和背景，以下是一些符号规定：

* $ T $：分割阈值
* $ N\_0 $：前景像素点数
* $ N\_1 $：背景像素点数
* $ \omega\_0 $：前景的像素点数占整幅图像的比例
* $ \omega\_1 $：背景的像素点数占整幅图像的比例
* $ \mu\_0 $：前景的平均像素值
* $ \mu\_1 $：背景的平均像素值
* $ \mu $：整幅图的平均像素值
* $ rows\times cols $：图像的行数和列数

结合下图会更容易理解一些，有一副大小为4×4的图片，假设阈值T为1，那么：

![img](https://gitee.com/tianzhendong/img/raw/master//images/202202221347535.jpeg)

其实很好理解，$ N\_0+N\_1 $就是总的像素点个数，也就是行数乘列数：

$$
N_0+N_1=rows\times cols
$$

$ \omega\_0 $和$ \omega\_1 $是前/背景所占的比例，也就是：

$$
\omega_0=\frac{N_0}{rows\times cols}
$$

$$
\omega_1=\frac{N_1}{rows\times cols}
$$

$$
\omega_0+\omega_1=1 \tag{1}
$$

整幅图的平均像素值就是：

$$
\mu=\omega_0\times \mu_0+\omega_1\times \mu_1  \tag{2}
$$

此时，我们定义一个前景$ \mu\_0 $与背景$ \mu\_1 $的方差$ g $：

$$
g=\omega_0(\mu_0-\mu)^2+\omega_1(\mu_1-\mu)^2  \tag{3}
$$

将前述的1/2/3公式整合在一起，便是：

$$
g=\omega_0\omega_1(\mu_0-\mu_1)^2
$$

**$ g $就是前景与背景两类之间的方差，这个值越大，说明前景和背景的差别也就越大，效果越好。Otsu算法便是遍历阈值T，使得$ g $最大，所以又称为最大类间方差法。**基本上双峰图片的阈值T在两峰之间的谷底。

### [接口文档](http://codec.wang/#/opencv/start/extra-02-high-quality-save-and-matplotlib?id=接口文档)

- [cv2.imwrite()](https://docs.opencv.org/4.0.0/d4/da8/group__imgcodecs.html#gabbc7ef1aa2edfaa87772f1202d67e0ce)
- [ImwriteFlags](https://docs.opencv.org/4.0.0/d4/da8/group__imgcodecs.html#ga292d81be8d76901bff7988d18d2b42ac)





