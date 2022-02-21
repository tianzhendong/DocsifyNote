## 图像基本操作

http://codec.wang/#/opencv/start/04-basic-operations

总结：

- `img[y,x]`获取/设置像素点值，`img.shape`：图片的形状（行数、列数、通道数）,`img.dtype`：图像的数据类型。
- `img[y1:y2,x1:x2]`进行ROI截取，`cv2.split()/cv2.merge()`通道分割/合并。更推荐的获取单通道方式：`b = img[:, :, 0]`。

接口：

- [cv2.split()](https://docs.opencv.org/4.0.0/d2/de8/group__core__array.html#ga0547c7fed86152d7e9d0096029c8518a)
- [cv2.merge()](https://docs.opencv.org/4.0.0/d2/de8/group__core__array.html#ga7d7b4d6c6ee504b30a20b1680029c7b4)

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

![image-20220221201534258](https://gitee.com/tianzhendong/img/raw/master//images/202202212015491.png)
