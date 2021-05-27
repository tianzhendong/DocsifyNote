---
title: String类方法
tags: java
notebook: Java
---


# String类

用的最多的类；

## String基础

1. String类又称作不可变字符序列
2. 位于java.lang包
3. java字符串就是Unicode字符序列，java就是j、a、v、a组成



#  String 方法

## 创建字符串
创建一个String对象，并初始化一个值。
String类是不可改变的，一旦创建了一个String对象，它的值就不能改变了。
如果想对字符串做修改，需要使用StringBuffer&StringBuilder类。
```
//直接创建方式
String str1 = "abc";
//提供一个 字符数组 参数来初始化字符串
char[] strarray = {'a','b','c'};
String str2 = new String(strarray);

```

## 字符串长度获取

int length()方法：返回字符串对象包含的字符数。
```
int len = str.length();
```
## 连接字符串
String concat(String str)：连接两个字符串的方法
或者直接用‘+’操作符来连接
```
//String对象的连接
str1.concat(str2);

"两个字符串连接结果："+str1+str2; 
```
## 字符串查找
int indexOf(String s)：字符串s在指定字符串中首次出现的索引位置，如果没有检索到字符串s，该方法返回-1

int lastIndexOf(String s)：字符串s在指定字符串中最后一次出现的索引位置，如果没有检索到字符串s，该方法返回-1；

如果s是空字符串，则返回的结果与length方法的返回结果相同，即返回整个字符串的长度。
```
int idx = str.indexOf("a");//字符a在str中首次出现的位置
int idx = str.lastIndexOf("a");

```

## 获取指定位置的字符串
char charAt(int index)方法：返回指定索引出的字符
```
String str = "abcde";
char thischar = str.charAt(3);//索引为3的thischar是"d"

```
## 获取子字符串
String substring()方法：实现截取字符串，利用字符串的下标索引来截取(字符串的下标是从0开始的，在字符串中空格占用一个索引位置)

substring(int beginIndex)：截取从指定索引位置开始到字符串结尾的子串
substring(int beginIndex, int endIndex)：从beginIndex开始，到endIndex结束(不包括endIndex)
```
String str = "abcde";
String substr1 = str.substring(2);//substr1为"cde"
String substr2 = str.substring(2,4);//substr2为"cd"

```

## 去除字符串首尾的空格()

String trim()方法

```
String str = " ab cde ";
String str1 = str.trim();//str1为"ab cde"
```

## 字符串替换
1. String replace(char oldChar, char newChar)：将指定的字符/字符串oldchar全部替换成新的字符/字符串newChar
2. String replaceAll(String regex, String replacement)：使用给定的参数 replacement替换字符串所有匹配给定的正则表达式的子字符串
3. String replaceFirst(String regex, String replacement)：使用给定replacement 替换此字符串匹配给定的正则表达式的第一个子字符串
regex是正则表达式，替换成功返回替换的字符串，替换失败返回原字符串
```
String str = "abcde";
String newstr = str.replace("a","A");//newstr为"Abcde"
```

## 判断字符串的开始与结尾

boolean startsWith()

1. boolean startsWith(String prefix)：判断此字符串是否以指定的后缀prefix开始
2. boolean startsWith(String prefix, int beginidx)：判断此字符串中从beginidx开始的子串是否以指定的后缀prefix开始
boolean endsWith(String suffix)：判断此字符串是否以指定的后缀suffix结束
```
String str = "abcde";
boolean res = str.startsWith("ab");//res为true
boolean res = str.StartsWith("bc",1);//res为true
boolean res = str.endsWith("de");//res为true
```
## 判断字符串是否相等
boolean equals(Object anObject)：将此字符串与指定的对象比较，区分大小写
boolean equalsIgnoreCase(String anotherString)：将此 String 与另一个 String 比较，不考虑大小写
```
String str1 = "abcde";
String str2 = str1;//字符串str1和str2都是一个字符串对象
String str3 = "ABCDE";
boolean isEqualed = str1.equals(str2);//返回true
boolean isEqualed = str1.equals(str3);//返回false
boolean isEqualed = str1.equlasIgnoreCase(str3);//返回true

```
## 比较两个字符串

int compareTo(Object o)：把这个字符串和另一个对象比较。
int compareTo(String anotherString)：按字典顺序比较两个字符串。
比较对应字符的大小(ASCII码顺序)，如果参数字符串等于此字符串，则返回值 0；如果此字符串小于字符串参数，则返回一个小于 0 的值；如果此字符串大于字符串参数，则返回一个大于 0 的值。
```
String str1 = "abcde";
String str2 = "abcde123";
String str3 = str1;
int res = str1.compareTo(str2);//res = -3
int res = str1.compareTo(str3);//res = 0
int res = str2.compareTo(str1);//res = 3

```
## 把字符串转换为相应的数值

String转int型：
```
//第一种
int i = Integer.parseInt(String str)
//第二种
int i = Integer.valueOf(s).intValue();

```

String转long型：

```
long l = Long.parseLong(String str);
1
```
String转double型：

```
double d = Double.valueOf(String str).doubleValue();//doubleValue()不要也可
double d = Double.parseDouble(str);

```

int转string型：

```
//第一种
String s = String.valueOf(i)；
//第二种
String s = Integer.toString(i);
//第三种
String s = "" + i;

```

## 字符大小写转换

String toLowerCase()：将字符串中的所有字符从大写字母改写为小写字母
String toUpperCase()：将字符串中的所有字符从小写字母改写为大写字母

```
String str1 = "abcde";
String str2 = str1.toUpperCase();//str2 = "ABCDE";
String str3 = str2.toLowerCase();//str3 = "abcde";

```

## 字符串分割

String[] split()：根据匹配给定的正则表达式来拆分字符串，将分割后的结果存入字符数组中。

String[] split(String regex)：regex为正则表达式分隔符, . 、 $、 | 和 * 等转义字符，必须得加 \\；多个分隔符，可以用 | 作为连字符。
String[] split(String regex, int limit)：limit为分割份数
```
String str = "Hello World A.B.C"
String[] res = str.split(" ");//res = {"Hello","World","A.B.C"}
String[] res = str.split(" ",2);//res = {"Hello","World A.B.C"}
String[] res = str.split("\\.");//res = {"Hello World A","B","C"}

String str = "A=1 and B=2 or C=3"
String[] res = str.split("and|or");//res = {"A=1 "," B=2 "," C=3"}

```

## 字符数组与字符串的转换
public String(char[] value) ：通过char[]数组来创建字符串
char[] toCharArray()：将此字符串转换为一个新的字符数组。

```
String str = "abcde";
char mychar[] = str.toCharArray();//char[0] = 'a'; char[1] = 'b'...

```

## 字符串与byte数组的转换

byte[] getBytes()

byte[] getBytes()：使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
byte[] getBytes(String charsetName)：使用指定的字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
```
byte[] Str2 = Str1.getBytes();


```

## StringBuffer&StringBuilder类

与String类最大的不同在于这两个类可以对字符串进行修改。
StringBuilder相较StringBuffer来说速度较快，多数情况下使用StringBuilder，但是StringBuilder的方法不是线性安全的（不能同步访问），所以在应用程序要求线程安全的情况下，必须使用StringBuffer。

## 创建StringBuffer字符串
```
StringBuffer str = new StringBuffer("");
```

## 添加字符(最常用方法)

public StringBuffer append(String s)：将指定的字符串追加到字符序列中
```

str.append("abc");//此时str为“abc”
```
## 删除字符串中的指定字符
public delete(int start,int end)：移除此序列中的子字符串的内容

public deleteCharAt(int i)：删除指定位置的字符
```
str.delete(0,1);//此时str为“c”
str.deleteCharAt(str.length()-1);//删除最后一个字符

```
## 翻转字符串
public StringBuffer reverse()

```
str.reverse();

```
## 换字符串中内容

replace(int start,int end,String str)：用String类型的字符串str替换此字符串的子字符串中的内容

```
String s = "1";
str.replace(1,1,s);//此时str为"a1c"

```
## 插入字符

public insert(int offset, int i)：将int参数形式的字符串表示形式插入此序列中
```
str.insert(1,2);
```
## 字符串长度
int length()：返回长度（字符数）
void setLength(int new Length)：设置字符序列的长度

```
str.length();
str.setLength(4);
```
## 当前容量
int capacity()：获取当前容量
void ensureCapacity(int minimumCapacity)：确保容量小于指定的最小值
```
str.capacity();
```
## 将其转变为String
String toString()
```
str.toString();//将StringBuffer类型的序列转变为String类型的字符串
```
## 设置指定索引处的字符
void setCharAt(int index，char ch)：将给定索引处的字符设置为ch

其余方法和String类型的方法大致相同。

