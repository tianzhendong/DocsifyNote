[TOC]



# IEEE754标准的浮点数存储格式

操作系统 ： CentOS7.3.1611\_x64

gcc版本 ：4.8.5

基本存储格式（从高到低） ： Sign + Exponent + Fraction

Sign ： 符号位

Exponent ： 阶码

Fraction ： 有效数字

## 32位浮点数存储格式解析

Sign ： 1 bit（第31个bit）

Exponent ：8 bits （第 30 至 23 共 8 个bits）

Fraction ：23 bits （第 22 至 0 共 23 个bits）

32位非0浮点数的真值为(python语法) ：

(-1) \*\*Sign \* 2 \*\*(Exponent-127) \* (1 + Fraction)

示例如下：

a = 12.5

1、求解符号位

a大于0，则 Sign 为 0 ，用二进制表示为： 0

2、求解阶码

a表示为二进制为： 1100.0

小数点需要向左移动3位，则 Exponent 为 130 （127 + 3），用二进制表示为： 10000010

3、求解有效数字

有效数字需要去掉最高位隐含的1，则有效数字的整数部分为 ： 100

将十进制的小数转换为二进制的小数的方法为将小数\*2，取整数部分，则小数部分为： 1

后面补0，则a的二进制可表示为： 01000001010010000000000000000000

即 ： 0100 0001 0100 1000 0000 0000 0000 0000

用16进制表示 ： 0x41480000

4、还原真值

```
Sign = bin(0) = 0 
Exponent = bin(10000010) = 130 
Fraction = bin(0.1001) = 2 ** (-1) + 2 ** (-4) = 0.5625
```

真值：

(-1) \*\*0 \* 2 \*\*(130\-127) \* (1 + 0.5625) = 12.5

32位浮点数二进制存储解析代码（c++）：

[https://github.com/mike-zhang/cppExamples/blob/master/dataTypeOpt/IEEE754Relate/floatTest1.cpp](https://github.com/mike-zhang/cppExamples/blob/master/dataTypeOpt/IEEE754Relate/floatTest1.cpp)

运行效果：

```
[root@localhost floatTest1]# ./floatToBin1
sizeof(float) : 4
sizeof(int) : 4
a = 12.500000
showFloat : 0x 41 48 00 00
UFP : 0,82,480000
b : 0x41480000
showIEEE754 a = 12.500000
showIEEE754 varTmp = 0x00c00000
showIEEE754 c = 0x00400000
showIEEE754 i = 19 , a1 = 1.000000 , showIEEE754 c = 00480000 , showIEEE754 b = 0x41000000
showIEEE754 i = 18 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 17 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 16 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 15 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 14 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 13 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 12 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 11 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 10 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 9 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 8 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 7 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 6 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 5 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 4 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 3 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 2 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 i = 1 , a1 = 0.000000 , showIEEE754 b = 0x41000000
showIEEE754 : 0x41480000
[root@localhost floatTest1]#
```

## 64位浮点数存储格式解析

Sign ： 1 bit（第31个bit）

Exponent ：11 bits （第 62 至 52 共 11 个bits）

Fraction ：52 bits （第 51 至 0 共 52 个bits）

64位非0浮点数的真值为(python语法) ：

```(-1) **Sign * 2 **(Exponent-1023) * (1 + Fraction)```

示例如下：

a = 12.5

1、求解符号位

a大于0，则 Sign 为 0 ，用二进制表示为： 0

2、求解阶码

a表示为二进制为： 1100.0

小数点需要向左移动3位，则 Exponent 为 1026 （1023 + 3），用二进制表示为： 10000000010

3、求解有效数字

有效数字需要去掉最高位隐含的1，则有效数字的整数部分为 ： 100

将十进制的小数转换为二进制的小数的方法为将小数\*2，取整数部分，则小数部分为： 1

后面补0，则a的二进制可表示为：

0100000000101001000000000000000000000000000000000000000000000000

即 ： 0100 0000 0010 1001 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000

用16进制表示 ： 0x4029000000000000

4、还原真值

```
Sign = bin(0) = 0 
Exponent \= bin(10000000010) = 1026 
Fraction \= bin(0.1001) = 2 \*\* (-1) + 2 \*\* (-4) = 0.5625
```

真值：

```(-1) **0 * 2 **(1026-1023) * (1 + 0.5625) = 12.5```

64位浮点数二进制存储解析代码（c++）：

[https://github.com/mike-zhang/cppExamples/blob/master/dataTypeOpt/IEEE754Relate/doubleTest1.cpp](https://github.com/mike-zhang/cppExamples/blob/master/dataTypeOpt/IEEE754Relate/doubleTest1.cpp)

运行效果：

```
[root@localhost t1]# ./doubleToBin1
sizeof(double) : 8
sizeof(long) : 8
a = 12.500000
showDouble : 0x 40 29 00 00 00 00 00 00
UFP : 0,402,0
b : 0x0
showIEEE754 a = 12.500000
showIEEE754 logLen = 3
showIEEE754 c = 4620693217682128896(0x4020000000000000)
showIEEE754 b = 0x4020000000000000
showIEEE754 varTmp = 0x8000000000000
showIEEE754 c = 0x8000000000000
showIEEE754 i = 48 , a1 = 1.000000 , showIEEE754 c = 9000000000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 47 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 46 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 45 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 44 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 43 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 42 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 41 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 40 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 39 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 38 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 37 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 36 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 35 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 34 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 33 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 32 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 31 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 30 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 29 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 28 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 27 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 26 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 25 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 24 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 23 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 22 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 21 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 20 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 19 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 18 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 17 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 16 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 15 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 14 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 13 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 12 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 11 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 10 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 9 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 8 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 7 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 6 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 5 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 4 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 3 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 2 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 i = 1 , a1 = 0.000000 , showIEEE754 b = 0x4020000000000000
showIEEE754 : 0x4029000000000000
[root@localhost t1]#
```



好，就这些了，希望对你有帮助。

本文github地址：

[https://github.com/mike-zhang/mikeBlogEssays/blob/master/2018/20180117\_IEEE754标准的浮点数存储格式.rst-
](https://github.com/mike-zhang/mikeBlogEssays/blob/master/2018/20180117_IEEE754%E6%A0%87%E5%87%86%E7%9A%84%E6%B5%AE%E7%82%B9%E6%95%B0%E5%AD%98%E5%82%A8%E6%A0%BC%E5%BC%8F.rst)

欢迎补充

[查看原网页: www.cnblogs.com](https://www.cnblogs.com/MikeZhang/p/IEEE754FloatEncode20180117.html)