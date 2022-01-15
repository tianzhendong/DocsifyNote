---
title: JavaNote1
tags: code
notebook: JAVA
---

[TOC]

# 概述
**java的核心优势**：跨平台/可移植性

**特点**：安全性、面向对象、简单性（C++-，C++的简化版，去掉一些内容）、高性能、分布式、多线程、健壮性

**java的版本：**
1. **JAVASE**：JAVA的标准版本，定位在个人计算机的应用
2. **JAVAEE**：java的企业版，定位在服务器端的应用，是javaSE的扩展，增加了用于服务器开发的类库。
3. **javaME**：微型版，定位在消费性电子产品上的开发。不用学习。
   
**计算机高级语言的类型**：编译型（C、C++）、解释型（python），java属于两种类型的综合。

**JVM**：JAVA虚拟机，一个虚拟的用于执行bytecode字节码的“虚拟计算机”。JVM负责将jaba字节码解释运行，边解释边运行，速度会收到一定的影响。

**jvm屏蔽了底层运行平台的差别，是实现跨平台的核心机制。**

.java源文件编译成.class字节码文件，经过JVM for Unix/Windows/other解释执行

**JRE**：java运行环境，包括JVM，库函数、运行java应用程序必须的文件。

**JDK**：java开发工具，包含JRE，以及增加编译器和调试器等用于程序开发的文件。

*如果只是要运行java程序，只需要安装JRE，如果需要开发java程序，则需要安装JDK。*

# 基本语法
## 注释
给程序员看的， 不是给机器看的，//代表单行注释，/*,,,,*/多行注释，多行注释不能嵌套，/**,,,*/文档注释，中间的每一行都要以*开头
## 标识符
给变量、类、方法以及包进行命名的，
1. 标识符必须以**字母、下划线、$**开头；
2. Java标识符对大小写敏感，长度无限制；
3. 标识符不可以是java关键字；
4. 表示类名的标识符，首字母大写；
5. 表示方法和变量的标识符，第一个单词小写，后面的单词开始，首字母大写，如eatFood（），驼峰原则；

java采用的是**unicode字符集**，**汉字和英文字母一样，都是普通字符，可以作为标识符。**

## 变量：
本质上代表一个“可操作的存储空间”，空间位置是确定的，但是里面的东西不确定。

java是一种强类型语言，**变量必须声明类型**。
```java
double salary，b；//尽量不要这样，尽量逐一声明每一个变量，提高程序可读性。
double salary = 1000;
```
一个字节byte有8个比特（位）bite，double8个字节，64位。

变量包括：**局部变量、成员变量、静态变量**

类型|声明位置|从属于|作用域
---|---|---|--
局部变量|方法或语句块内部|方法/语句块|方法/语句块执行期间
成员变量|类内部，方法外部|对象|跟随对象
静态变量|类外部，static修饰|类|类的加载和卸载

## 常量
变量声明前加**final**   

`final double PI = 3.14;`

## 基本数据类型
Java数据类型可以分为两类：**基本数据类型、引用数据类型**   

基本数据类型有**8**种，包括：
1. 数值型：整数类型（字节byte、短整型short、整型int、长整型long，分别具有1个字节、2个字节、4个字节、8个字节）、浮点类型（float、double，分别具有4个字节、8个字节）；
2. 字符型char；
3. 布尔型boolen。

引用数据类型有3种：
1. 类class；
2. 接口interface；
3. 数组。

>**引用数据类型的大小统一为4个字节byte，记录的是引用对象的地址；**   
 
>java语言整型常量四种表示形式：
* 十进制整数，如：88；
* 八进制整数，以0开头，如：015；
* 十六进制，以0x或者0X开头，如：0x15；
* 二进制，以0b或者0B开头，如0b001.

java语言整型常数默认为int类型，声明long型常量可以再后面加”l“、”L“；
```
long a = 5555; //编译成功，5555再int表示范围内；
long a = 5555555555；//编译错误，默认为int，超过范围。
long b = 5555555555L; //编译正确
```
**浮点型**中float是4个字节，可以精确到7位有效数字，double为8个字节；**绝大多数使用double类型**
浮点型是有误差的，高精度中不能使用   
float一般有F或者f作为后缀，没有的默认使用double类型。   

**字符型**内存中占用两个字节，使用单引号表示字符常量，双引号表示字符串。
java中可以中十六进制表示字符型，前面加\u，如`char c= '\u0061'`表示c=‘a’；

**转义字符**
转义符|含义|unicode值
-|-|-
\b|退格|\u0008
\n|换行|\u000a
\r|回车|\u000d
\t|tab|\u0009
\ "|双引号|\u0022
\ '|单引号|\u0027
\ \|反斜杠|\u005c

**boolen型有true和false两个，占用一个或者4个字节，不可以用0或者非0表示！！**

## **运算符**

### 自动类型转换

容量小的数据类型可以自动转换为容量大的，但是整数型可以转换为浮点型，即使long的容量大于float
 ### 强制类型转换

 
   


# 控制语句
**顺序结构**代表**先执行a再执行b**；   
**条件判断结构**代表**如果。。则。。**；   
**循环结构**代表**如果。。则重复执行。。**；

## if语句
```
if(判断语句){
    。。。
}else if{
    ...
}else{
    ...
}
```
## switch语句
适用于多值判断，可以用if语句代替
```
switch(表达式){
    case 值1：
        语句1；
        break；//可有可无
    case 值2：//没有语句的话执行下一个语句。
    case 值3：
        语句2：
    default:
        语句n；
}
```

## while循环
```
while(布尔表达式){
    循环体；
}

do{
    循环体；
}while(布尔表达式)；
```
## for循环
```
for(初始表达式；布尔表达式；迭代){
    循环体；
}
for（;;）{  //死循环，while（true）
    ...
}
```
## 嵌套循环   

## 方法
**方法**类似于其他语言的**函数**

方法的声明格式：
`[修饰符1   修饰符2]    返回值类型   方法名（形参1 形参2）`

方法的调用
`类名.方法名（）`


==========================



# 面向对象

>**类和对象、内存底层、面向对象三个特征、特殊类**

> 互联网上的所有数据，本质上都是“表格”

**面向过程时一种“设计者”思维；   面向对象是一种“设计者”思维。**

## 对象和类

### 类的声明

```
public class SxtSut{
    //属性（成员变量）
    int id;
    String sname;
    int age;
    //方法
    void study(){
        ...
    }
    //构造方法，可有可无
    SxtStu(){
    }
}
```
### 成员变量的默认值：

数据类型|默认值
--|-|
整型|0
浮点型|0.0
字符型|'\u0000"
布尔型|false
所有引用类型|null

### 构造方法

构造器也叫构造方法，用于对象的初始化，再对象创建的时候会自动调用；

* 构造器的调用是通过new调用的
* 构造器虽然有返回值，但是不能定义返回值类型（返回值的类型是本类），不能使用return
* 如果没有定义构造器，编译器会自动定义一个无参的构造函数，如果自己定义了，那么系统不会自定义
* 构造器的方法名称必须和类完全相同

## java虚拟机内存模型

### 从属于线程的内存分析

1. 程序计数器，每个线程都有自己的程序计数器，是一块比较小的内存空间，存储当前线程正在执行的java方法的JVM指令地址，即字节码的行号
2. java虚拟机栈，线程的私有区域，每个线程再创建的时候都会创建一个虚拟机栈，线程退出时，会被回收。方法调用时进行压栈操作，方法调用结束后进行出栈操作。该区域存储着局部变量表
3. 本地方法栈：与虚拟机栈类似，调用本地方法时使用的栈，每个线程都有一个本地方法栈。

#### 栈

1. 描述的是方法执行的内存模型，每个方法被调用时都被创建一个栈帧，存储局部变量、操作数、方法出口等；
2. jvm会为每个线程创建一个栈
3. 属于线程私有，不能共享
4. 存储特性：先进后出，后进先出

### 堆heap

几乎所有创建的java对象都会被直接分配到堆上。堆被所有的线程共享，再堆上的区域，会被垃圾回收器进一步划分为新生代、老年代。java虚拟机启动时，可以使用Xmx之类的参数指定堆区域的大小。

1. 存储数组（数组也是对象）和对象
2. jvm只有一个堆，被所有线程共享
3. 堆是一个不连续的空间，分配灵活，速度慢！

### 方法区

被所有线程共享，时一种java虚拟机的规范，存储被虚拟机加载的元数据，包括类信息、常量、静态变量、即时编译器编译后的代码等数据

由于方法去存储的数据和队中存储的数据一致，实质上也是堆。java不同版本实现方式不同

1. jvm只有一个方法区，被所有线程共享
2. 实际也是堆，不连续，只是用来存储类、常量等信息
3. 用来存放线程中永远是不变或者唯一的内容（类信息、静态常量、字符串常量）。

### 运行时常量池（方法区中）

存储final常量等

### 直接内存

直接内存并不属于java规范规定的属于java虚拟机运行时数据区的一部分。

## 垃圾回收机制Garbage Collection

**java的内存管理很大程度上指的就是：堆中对象的管理，其中包括对象空间的分配和释放。**


**对象空间的分配：new；对象空间的释放：赋值null即可，垃圾回收器将负责回收所有“不可达”对象（没有引用）的内存空间**

**垃圾回收相关算法**

1. 引用计数法：堆中每个对象都引用一个计数器。有点：算法简单；缺点：循环引用的无用对象无法识别
2. 引用可达法（根搜索算法）：程序把所有的引用关系看作一张图

## 通用的分代垃圾回收机制

不同的对象的生命周期是不一样的，采用不同的回收算法，提高回收效率。

分为年轻代、年老代、持久代，将不同状态的对象放到堆中不同的区域，JVM将堆内存划分为Eden、Survivor、Tenured/Old空间。

1. Eden区：存储了从未经过垃圾回收的新对象
2. Survivor区，存放经过垃圾回收后，仍然有用的对象，survivor1和2循环存放，小于15次垃圾回收次数
3. Tenured区，年老代区域存放超过15次垃圾回收的对象

1. Minor GC：用于清理年轻代区域，Eden区满了就会触发一次，清理无用对象，将有用对象复制到survivor1和2区
2. Major GC：用于清理年老代区域
3. Full GC：用于清理年轻代、年老代区域，成本较高，会对系统性能产生影响

**程序员无权调用垃圾回收器**

## 容易造成内存泄漏的情况

1. 创建大量无用对象，比如再需要大量拼接字符串时，使用了String而不是StringBuilder
2. 静态集合类的使用，如：HashMap、Vector、List等使用最容易造成内存泄漏，这些静态变量的生命周期和应用程序一致，所有的对象Object也不能被释放
3. 各种连接对象（IO流对象、数据库连接对象、网络连接对象）未关闭，这些对象属于物理连接，和硬盘或者网络连接，不适用的时候要关闭
4. 监听器的使用，释放对象时，未删除相应的监听器

System.gc()的作用：程序员建议启用垃圾回收进程

##  包机制（package、import）

###  package

通过package实现对类的管理，包对于类，相当于文件夹对于文件的作用：
1. 通常是类的第一句非注释性语句；
2. 包名：域名倒着写即可，再加上模块名，便于内部管理类
```
com.sun.test;
com.oracle.test;
cn.sxt.gao.test;
```

导入：

```
import com.sun.test.* //导入该包下的所有类;
```


导入一个包内所有的类，会降低编译速度，但不会降低运行速度

## 继承extends

java中只有单继承，没有C++中的多继承，一个子类只有一个父类；

如果定义了一个类，没有调用extends，那么他的父类是java.lang.object

### instanceof运算符

二元运算符，左边是对象，右边是类，如果左边是右边类火子类的对象，则返回true，否则false

### 重写overwrite

1. 方法名、形参列表必须相同；
2. 返回值类型和声明异常类型，子类小于等于父类
3. 访问权限，子类大于等于父类。

## final关键字

1. 修饰变量，该变量不可改变；
2. 修饰方法，该方法不可以被子类重写，但是可以被重载！（重写：参数列表相同，重载：参数列表不同）；
3. 修饰类：修饰的类不能被重载；

## 继承和组合

组合：将父类对象作为子类的属性


## Object类

所有java类的父类；

### toString方法

```
public String toString(){
    return getClass().getName()+"@"+Integer.toHexString(hashCode());
}
// 默认返回“类名+@+16进制的hashcode”，再打印输出或者用字符串连接对象时，会自动调用该方法
```

###  "=="和equals方法

```
public boolean equals(Object obj){
    return(this == obj)
}
```
==的作用：

1. 基本类型：值是否相等；
2. 引用类型：内存地址是否相等；


equals：默认情况下比较内存地址是否相等，一般进行重写。

### super方法

子类中使用super方法，调用父类中的方法或属性。

子类中所有构造方法的第一句总会构造父类的构造器，你不加，编译器会自动加super（）进行调用父类的无参构造器。

## 封装

程序设计追求高内聚、低耦合，高内聚就是类的内部数据操作细节自己完成，不允许内部干涉；低耦合就是仅暴漏少量方法给外部使用，尽量方便外部调用；

封装的有点：

1. 提高代码的安全性
2. 提高代码的复用性
3. 高内聚：封装细节，便于修改内部代码，提高可维护性；
4. 低耦合：简化外部调用，便于调用者使用，方便扩展和写作。

### 封装的实现——访问控制符


修饰符|同一个类|同一个包|子类|所有类
-|-|-|-|-
private|yes|-|-|-
default|yes|yes|-|-
protected|yes|yes|yes|-
public|yes|yes|yes|yes

### 封装的简单规则：

1. 一般使用private访问权限；
2. 提供相应的get/set方法来访问相关属性，这些方法通常是public修饰的（注意：boolean变量的get方法是is开头）
3. 一些只用于本类的辅助性方法可以用private修饰，希望其他类调用的方法用public调用


## 多态

多态：同一个方法调用，由于对象不同可能会有不同的行为。

1. 多态是方法的多态，不是属性的多态，多态和属性无关；
2. 多态存在的必要条件：继承，方法重写，父类引用指向子类对象。
3. 父类引用指向子类对象后（如：`Animal a1 = new Cat();`），用该父类引用调用子类重写的方法，此时多态就出现了。

多态的作用，定义方法时，只需要定义一个
```
void animalShout（Animal a）{
    a.shout();
}       //shout（）方法为类中重写的方法
```


##  对象的转型

向上转型：父类引用指向子类对象，称之为向上转型，属于自动类型转换；`Animal a1 = new Cat();`

向下转型：把animal转换成cat。

```java
Animal a = new Dog();
Dog d2 = (Dog) a;
```

# 抽象方法和抽象类

## 抽象方法：

使用abstract修饰的方法，没有方法体，只有声明，定义的是一种规范，告诉子类，必须要给抽象方法提供具体的实现；

## 抽象类

包含抽象方法的类就是抽象类，通过abstract方法定义规范，要求子类必须定义具体实现，通过抽象类，可以做到严格限制子类的设计，使子类之间更加通用。

```java
//抽象类
abstract class Animal{
    public abstract void shout();//抽象方法
}

class Dog extends Animal{
    public void shout(){
        ......
    }
}
```
注意：
1. 有抽象方法的类只能定义成抽象类；
2. 抽象类不能实例化，不能用new新建
3. 抽象类可以半酣属性、方法、构造方法。但是构造方法不能用来new实例，只能用来被子类调用；
4. 抽象类只能用来被继承；
5. 抽象方法必须被子类实现。

# 接口

接口就是比“抽象类”还“抽象”的“抽象类”，可以更加规范的对子类进行约束。全面地专业地实现了：规范和具体实现的分离。

 抽象类还提供某些具体实现，接口不提供任何实现，接口中所有方法都是抽象方法。接口是完全面向规范的，规定了一批类具有的公共方法规范。

 从接口的实现者角度看，接口定义了可以向外部提供的服务。
   从接口的调用者角度看，接口定义了实现者能提供那些服务。
   接口是两个模块之间通信的标准，通信的规范。如果能把你要设计的系统之间模块之间的接口定义好，就相当于完成了系统的设计大纲，剩下的就是添砖加瓦的具体实现了。大家在工作以后，做系统时往往就是使用“面向接口”的思想来设计系统。

```java
//定义接口
interface Volant{
    int a = 100; //总是：public static final类型的；
    void fly(); //总是public abstract void fly；
}

//实现接口
class Angel implements Volant{
    public void fly(){
        ...
    }
}

//接口的继承
interface a extends Volant{
    ....
}
```

## 接口中定义静态方法和默认方法（JDK8以后）

java8以前，接口里的方法要求全是抽象方法；

java8及以后，i允许再接口里定义默认方法和类方法。

### 默认方法
（扩展方法）：非抽象的方法实现，使用default关键字。，子类不需要实现，都带有。

### 静态方法

允许加静态方法

默认方法需要类实现后才能调用，静态方法不用实现对象，可以直接调用。


## 接口的多继承

类只能单继承，但是接口可以多继承

```java
interface C extends A ,B{

}
```

# String类

用的最多的类；

## String基础

1. String类又称作不可变字符序列
2. 位于java.lang包
3. java字符串就是Unicode字符序列，java就是j、a、v、a组成

##  String 方法

### 创建字符串
创建一个String对象，并初始化一个值。
String类是不可改变的，一旦创建了一个String对象，它的值就不能改变了。
如果想对字符串做修改，需要使用StringBuffer&StringBuilder类。
```java
//直接创建方式
String str1 = "abc";
//提供一个 字符数组 参数来初始化字符串
char[] strarray = {'a','b','c'};
String str2 = new String(strarray);

```

### 字符串长度获取

int length()方法：返回字符串对象包含的字符数。
```java
int len = str.length();
```
## 连接字符串
String concat(String str)：连接两个字符串的方法
或者直接用‘+’操作符来连接
```java
//String对象的连接
str1.concat(str2);

"两个字符串连接结果："+str1+str2; 
```
## 字符串查找
int indexOf(String s)：字符串s在指定字符串中首次出现的索引位置，如果没有检索到字符串s，该方法返回-1

int lastIndexOf(String s)：字符串s在指定字符串中最后一次出现的索引位置，如果没有检索到字符串s，该方法返回-1；

如果s是空字符串，则返回的结果与length方法的返回结果相同，即返回整个字符串的长度。
```java
int idx = str.indexOf("a");//字符a在str中首次出现的位置
int idx = str.lastIndexOf("a");

```

## 获取指定位置的字符串
char charAt(int index)方法：返回指定索引出的字符
```java
String str = "abcde";
char thischar = str.charAt(3);//索引为3的thischar是"d"

```
## 获取子字符串
String substring()方法：实现截取字符串，利用字符串的下标索引来截取(字符串的下标是从0开始的，在字符串中空格占用一个索引位置)

substring(int beginIndex)：截取从指定索引位置开始到字符串结尾的子串
substring(int beginIndex, int endIndex)：从beginIndex开始，到endIndex结束(不包括endIndex)
```java
String str = "abcde";
String substr1 = str.substring(2);//substr1为"cde"
String substr2 = str.substring(2,4);//substr2为"cd"

```

## 去除字符串首尾的空格()

String trim()方法

```java
String str = " ab cde ";
String str1 = str.trim();//str1为"ab cde"
```

## 字符串替换
1. String replace(char oldChar, char newChar)：将指定的字符/字符串oldchar全部替换成新的字符/字符串newChar
2. String replaceAll(String regex, String replacement)：使用给定的参数 replacement替换字符串所有匹配给定的正则表达式的子字符串
3. String replaceFirst(String regex, String replacement)：使用给定replacement 替换此字符串匹配给定的正则表达式的第一个子字符串
regex是正则表达式，替换成功返回替换的字符串，替换失败返回原字符串
```java
String str = "abcde";
String newstr = str.replace("a","A");//newstr为"Abcde"
```

## 判断字符串的开始与结尾

boolean startsWith()

1. boolean startsWith(String prefix)：判断此字符串是否以指定的后缀prefix开始
2. boolean startsWith(String prefix, int beginidx)：判断此字符串中从beginidx开始的子串是否以指定的后缀prefix开始
boolean endsWith(String suffix)：判断此字符串是否以指定的后缀suffix结束
```java
String str = "abcde";
boolean res = str.startsWith("ab");//res为true
boolean res = str.StartsWith("bc",1);//res为true
boolean res = str.endsWith("de");//res为true
```
## 判断字符串是否相等
boolean equals(Object anObject)：将此字符串与指定的对象比较，区分大小写
boolean equalsIgnoreCase(String anotherString)：将此 String 与另一个 String 比较，不考虑大小写
```java
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
```java
String str1 = "abcde";
String str2 = "abcde123";
String str3 = str1;
int res = str1.compareTo(str2);//res = -3
int res = str1.compareTo(str3);//res = 0
int res = str2.compareTo(str1);//res = 3

```
## 把字符串转换为相应的数值

String转int型：
```java
//第一种
int i = Integer.parseInt(String str)
//第二种
int i = Integer.valueOf(s).intValue();

```

String转long型：

```java
long l = Long.parseLong(String str);
1
```
String转double型：

```java
double d = Double.valueOf(String str).doubleValue();//doubleValue()不要也可
double d = Double.parseDouble(str);

```

int转string型：

```java
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

```java
String str1 = "abcde";
String str2 = str1.toUpperCase();//str2 = "ABCDE";
String str3 = str2.toLowerCase();//str3 = "abcde";

```

## 字符串分割

String[] split()：根据匹配给定的正则表达式来拆分字符串，将分割后的结果存入字符数组中。

String[] split(String regex)：regex为正则表达式分隔符, . 、 $、 | 和 * 等转义字符，必须得加 \\；多个分隔符，可以用 | 作为连字符。
String[] split(String regex, int limit)：limit为分割份数
```java
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

```java
String str = "abcde";
char mychar[] = str.toCharArray();//char[0] = 'a'; char[1] = 'b'...

```

## 字符串与byte数组的转换

byte[] getBytes()

byte[] getBytes()：使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
byte[] getBytes(String charsetName)：使用指定的字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
```java
byte[] Str2 = Str1.getBytes();


```

## StringBuffer&StringBuilder类

与String类最大的不同在于这两个类可以对字符串进行修改。
StringBuilder相较StringBuffer来说速度较快，多数情况下使用StringBuilder，但是StringBuilder的方法不是线性安全的（不能同步访问），所以在应用程序要求线程安全的情况下，必须使用StringBuffer。

## 创建StringBuffer字符串
```java
StringBuffer str = new StringBuffer("");
```

## 添加字符(最常用方法)

public StringBuffer append(String s)：将指定的字符串追加到字符序列中
```java

str.append("abc");//此时str为“abc”
```
## 删除字符串中的指定字符
public delete(int start,int end)：移除此序列中的子字符串的内容

public deleteCharAt(int i)：删除指定位置的字符
```java
str.delete(0,1);//此时str为“c”
str.deleteCharAt(str.length()-1);//删除最后一个字符

```
## 翻转字符串
public StringBuffer reverse()

```java
str.reverse();

```
## 换字符串中内容

replace(int start,int end,String str)：用String类型的字符串str替换此字符串的子字符串中的内容

```java
String s = "1";
str.replace(1,1,s);//此时str为"a1c"

```
## 插入字符

public insert(int offset, int i)：将int参数形式的字符串表示形式插入此序列中
```java
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
```java
str.capacity();
```
## 将其转变为String
String toString()
```java
str.toString();//将StringBuffer类型的序列转变为String类型的字符串
```
## 设置指定索引处的字符
void setCharAt(int index，char ch)：将给定索引处的字符设置为ch

其余方法和String类型的方法大致相同。


# 数组

数组是相同类型数据的有序集合
数组特点：

1. 长度是固定的，一旦创建，大小就是不可改变的；
2. 元素类型必须相同；
3. 数组变量属于引用类型 ，数组也是对象。

数组的生命（一维数组）
```java
//  type[] arr_name;

int[] s = null; //声明数组
s = new int[10 ];   //分配空间

//数组的初始化：三种
默认、动态、静态

//数组的遍历
for(int i = 0; i<s.length;i++){
    ...
}
for(s i:s){
    ...
}

//数组的拷贝 arraycopy（）
static void arraycopy（object s1, int srcpos, object s2, int destpos ,int length)
```

```java
package JavaStudy;

import java.util.Arrays;

/**
 * test arrays methods
 */

public class Test {
    public static void main(String[] args) {
        int[] s = new int[10];  //creat array
        int[] s1 ={1,3,2,5,1};  // init array s1
        System.out.println(Arrays.toString(s1));  //toString method
        System.arraycopy(s1,0,s,0,5);   //copy method
        System.out.println(Arrays.toString(s));
        Arrays.sort(s1);        //sort method
        System.out.println(Arrays.toString(s1));
        //二分法查找，使用该方法前，必须先使用sort进行排序
        Arrays.sort(s);
        System.out.println(Arrays.toString(s));
        System.out.println("位置为："+Arrays.binarySearch(s,3));        //该方法返回下标，如果未找到，则返回负数
    }
}
```

# 多维数组

```java
int[][] a = new int[3][];
int[][] b = {{1,2,3},{1},{3,2}};
```


# comparable接口

对某个类的对象进行比较，设置比较规则；


Comparable接口只有一个方法：
`public int compareTo(Object obj)//obj为要进行比较的对象`

```java
public int compareTo(Object obj){
    Man man = (Man)o;
    if(this.age<man.age){
        return -1;
    }
    if(this.age>man.age){
        return 1;
    }
    return 0;
}

```


# 异常
程序运行过程中出现的非正常情况
```java
try{
    ...
}catch(Exception e){
    e.printStackTrace();
}
```
java采用面向对象的方式来进行异常处理：

1. 抛出异常：如果发生异常，则这个方法生成代表该异常的一个对象，停止当前执行路径，并把异常对象提交给jre
2. 捕获异常：jre得到该异常后，寻找相应的代码来处理该异常


## 异常的分类

java对异常进行了分类，不同的异常用不同的java类表示，所有异常类的根类为java.lang . Throwable,Throwable下面派生了两个子类：error和exception


exception分为checkedexception和uncheckedexception异常，前者在编译时就能发现，后者需要在运行时发现。

## 异常的处理

### 捕获异常
```java
try{
    语句1； //语句1出现异常，语句2不会再执行
    语句2;  //抛出异常
}catch(Exception1 e){

}catch(Exception2 e){

}finally{
    语句3;        //统一出口，不管有无异常，都会执行
}
```


### 声明异常

```java
//逐级向上抛出异常，自己不处理
public static void main(String[] args) throws Exception{

}
```



## try-with-resource自动关闭closable接口的资源

```java
try(,,){
    ,,
}catch(Exception e){

}
```





