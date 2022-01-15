[toc]

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

