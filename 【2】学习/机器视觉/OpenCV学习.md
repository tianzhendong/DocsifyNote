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

