---

title: JavaWeb
tags: Java
notebook: JAVA
---



[toc]

# JavaWeb

# 0、JavaEE三层架构

![image-20210803175103445](https://gitee.com/tianzhendong/img/raw/master//images/image-20210803175103445.png)

# 1、概述

## 软件结构

C/S：客户端client/服务器server

B/S：客户端采用浏览器，服务器端采用web服务器

## 页面组成

* 内容（结构）：在页面中看到的数据，一般内容采用html技术
* 表现：内容在页面上的展示形式，一般用CSS技术
* 行为：页面中的元素与输入设备交互的响应，一般使用JavaScript技术

# 2、HTML

> HTML：超文本标记语言，通过标签来标记要显示的网页中的各个部分，网页文件本身是一种文本文件，通过在文本文件中添加标记符，告诉浏览器如何显示其中的内容

```html
<!DOCTYPE html><!--约束，声明-->
<html lang="en"><!--heml标签，表示html的开始，lang="zh_CN"表示中文，html标签中一般分为两部分：head和body-->
<head><!--表示头部信息，包括：title标签，css样式，js代码-->
    <meta charset="UTF-8"><!--表示当前页面使用UTF-8字符集-->
    <title>标题</title><!--表示页面的标题-->
</head>
<body><!--body是整个heml页面显示的主题内容-->
    tianzhendong<!--页面显示的内容，显示tianzhendong-->
</body>
</html><!--整个页面的结束-->
```

## HTML标签介绍



```html
<标签名>封装的数据</标签名>
```

* 标签名不区分大小写
* 标签拥有自己的属性
  * 基本属性：bgcolor = “red”	可以修改简单的样式效果
  * 事件属性：onclick= “alert(‘你好!’);”   可以设置事件响应后的代码
* 单双标签
  * 单标签：<标签名 />   br表示换行，hr表示水平线
  * 双标签：<标签名>封装的数据</标签名>

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>标题</title>
</head>
<body>
    tianzhendong
<br/><!--换行-->
<button>按钮1</button><!--生成一个叫按钮1的按钮-->
<hr/><!--水平线-->
<!--onclick是一个点击事件
alert()是一个警告框函数，弹出框，提示里面的参数内容-->
<button onclick="alert('hello')">点击显示警告</button>
</body>
</html>
```



## 常用标签

### 字体font

```html
<font color = "red" face="宋体" size="7">我是字体标签</font>
```

### 字符实体&

一些字符在 HTML 中拥有特殊的含义，比如小于号 (<) 用于定义 HTML 标签的开始。如果我们希望浏览器正确地显示这些字符，我们必须在 HTML 源码中插入字符实体。

字符实体有三部分：一个和号 (&)，一个实体名称，或者 # 和一个实体编号，以及一个分号 (;)。

![image-20210728211011236](https://i.loli.net/2021/07/28/KObBFExtv9IMgDh.png)

### 标题标签h1

支持h1-h6，align为对其属性

```html
<h1 align="center">标题1</h1><!--一级标题，并居中对齐，left=左对齐，right=右对齐-->
```

### 超链接a

* href属性：设置连接的地址
* target属性：设置哪个目标进行跳转
  * _self：默认值，当前页面
  * _blank：打开新页面进行跳转

```html
<a href="www.baidu.com">百度</a>
<a href="www.baidu.com" target="_blank">百度</a>
<a href="www.baidu.com" target="_self">百度</a>
```



### 列表标签ul/ol

* 无序列表始于<**ul**> 标签，每个列表项始于 <**li**>
* 有序列表始于<**ol**>标签，每个列表始于<**li**>
* 属性：type=”none“，取消前面的标号或者小圆点

```html
<ul>
    <li>1</li>
    <li>2</li>
    <li>3</li>
</ul>
```

### 图像标签img

* src属性：src=“url”，url为图像的位置
* alt属性：设置替代的文本属性
* width属性：设置宽度
* height属性：设置高度
* border属性：设置边框宽度

```html
<img src="./灵笼.png" height="108" width="192" />
<img src="./灵笼2.png" height="108" width="192" alt="找不到图片" />
```

### 表格标签table

表格由 <**table**> 标签来定义。每个表格均有若干行（由 <**tr**>标签定义），每行被分割为若干单元格（由 <**td**>  标签定义）。字母 td 指表格数据（table data），即数据单元格的内容。数据单元格可以包含文本、图片、列表、段落、表单、水平线、表格等等。表格的表头使用 <**th**> 标签进行定义。

* <**th**>：表头
* <**tr**>：行
* <**td**>：列

```html
<table align="center" border="1" width="300" height="100" cellspacing="0"><!--设置表格剧中，边框线粗细，每个格子的长宽,单元格之间的间距为0-->
    <tr>
        <th colspan="2">表头1</th><!--表头，自带加粗居中属性,colspan设置合并1行1列和2列的单元格，rowspan设置跨列合并-->
    </tr>
    <tr>
        <td align="center"><b>row 1, cell 1</b></td><!--设置字体居中，加粗-->
        <td>row 1, cell 2</td>
    </tr>
    <tr>
        <td>row 2, cell 1</td>
        <td>row 2, cell 2</td>
    </tr>
</table>
```

### 内嵌窗口iframe

在页面上开辟一个小区域，显示一个单独的页面

iframe和a标签组合使用：

* 在iframe标签中使用name属性定义一个名称
* 在a标签的target属性上设置iframe的name属性值

```html
<iframe src="1.html" width="500" height="500" name="abc"></iframe>
<br/>
<ul>
    <li><a href="2.html" target="abc">2.html</a> </li>
    <li><a href="3.html" target="abc">3.html</a> </li>
</ul>
```

### 表单标签form

html页面中用来收集用户信息的所有元素集合，表单元素是允许用户在表单中（比如：文本域、下拉列表、单选框、复选框等等）输入信息的元素。

```html
<body>
    <!--
    表单提交注意：
    1. 表单项中需要设置name属性才可以提交
    2. 单选、复选需要添加value属性，以便发送给服务器-->
    <form action="http://localhost:8080" method="get"><!--表单,提交的服务器地址，提交的方式get（默认）post-->
        <!--隐藏域钮，当要发送某些信息，而这信息不需要用户参与
        设置发送的信息-->
        <input type="hidden" name="action" value="login"><br>
        <!--标题-->
        <h1 align="center">用户注册</h1>
        <!--单行文本输入框，并显示默认值-->
        用户名称：<input type="text" value="请输入用户名" name="name"><br>
        <!--密码输入框，不显示输入的东西-->
        用户密码：<input type="password" name="password"><br>
        确认密码：<input type="password" name="password"><br>
        <!--单选框，并通过name属性设置分组，分组后同一组的不能多选,checked属性设置默认选中-->
        性别：<input type="radio" name="sex" value="boy" checked="checked">男
        <input type="radio" name="sex" value="girl">女<br>
        <!--复选框，checked属性设置默认选中-->
        兴趣爱好：<input type="checkbox" name="hobby" value="java">java
        <input type="checkbox" name="hobby" value="c">c
        <input type="checkbox" name="hobby" value="c++">c++<br>
        <!--下拉列表,selected属性设置默认值，不写的话默认第一个-->
        国籍：<select name="country">
            <option selected="selected" value="none">请选择国籍</option>
            <option value="cn">中国</option>
            <option value="usa">美国</option>
            <option value="jp">日本</option>
        </select><br>
        <!--多行文本输入框，rows设置显示几行高度，cols设置每行可以显示几个字符，注意默认值的位置-->
        自我评价：<textarea rows="10" cols="20" name="pingjia">默认值</textarea><br>
        <!--重置按钮，点击后重置为默认值-->
        <input type="reset" value="我是重置按钮" ><br>
        <!--提交按钮-->
        <input type="submit" value="我是提交按钮"><br>
        <!--选择文件按钮-->
        <input type="file" value="我是选择文件按钮"><br>
        <!--隐藏域钮，当要发送某些信息，而这信息不需要用户参与-->
        <input type="hidden" ><br>
    </form>
</body>
```

一般把表单放在一个表格table中

# 3、CSS
CSS：层叠样式菜单，用于增强控制网页样式并允许将样式信息与网页内容分离的一种标记性语言

## 语法规则

* 选择器：浏览器根据“选择器”决定受CSS样式影响的HTML元素（标签）
* 属性：要改变的样式名，并且每个属性有一个值，属性和值由：分开，并且由花括号包围
* 值

```html
body {color: blue}
```

将 body 元素内的文字颜色定义为蓝色。在上述例子中，body 是选择器，而包括在花括号内的的部分是声明。声明依次由两部分构成：属性和值，color  为属性，blue 为值。

注意：

* 如果值为若干单词，则要给值加引号

```html
p {font-family: "sans serif";}
```

* 如果要定义不止一个声明，则需要用分号将每个声明分开

```html
p {
text-align: center;
color: black;
font-family: arial;
}
```

## CSS和HTML结合方式

### 方法1

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CSS-HTML</title>
    <!--CSS代码-->
    <style type="text/css">
        /*CSS注释*/
        font{
            border:1px solid red;
        }
    </style>
</head>
<body>
    <font >tianzhendong</font>
</body>
</html>
```

缺点：

* 只能在同一页面内复用代码，不能再多个页面中复用CSS代码
* 维护起来不方便，实际的项目中会有成千上万的页面，要到每个页面中修改，工作量太大

### 方法2

把CSS样式写成一个单独的CSS文件，通过link标签引入即可复用

```css
font{
    border : 1px solid red;
}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CSS-HTML</title>
    <!--引入CSS代码-->
    <link rel="stylesheet" type="text/css" href="1.css">
</head>
<body>
    <font >tianzhendong</font>
</body>
</html>
```

## 常用选择器

### 标签名选择器

可以决定哪些标签被动的使用这个样式

格式：

```css
标签名{
	属性：值；
}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CSS-HTML</title>
    <!--CSS代码-->
    <!--标签选择器-->
    <style type="text/css">
        font{
            color: red;
            font-size: 30px;
        }
    </style>
</head>
<body>
    <font>tianzhendong</font><br>
    <font>tianzhendong</font><br>
</body>
</html>
```

### id选择器

可以通过id属性选择性的去使用这个样式

```html
#id属性值{
	属性：值；
}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CSS-HTML</title>
    <!--CSS代码-->
    <!--id选择器-->
    <style type="text/css">
        /*让第二个应用*/
        #id2{
            color: red;
            font-size: 30px;
        }
    </style>
</head>
<body>
    <font id="id1">tianzhendong</font><br>
    <font id="id2">tianzhendong</font><br>
</body>
</html>
```

id不能为数字，需要字母开头

### Class（类）选择器

```html
.class属性值{
	属性：值；
}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CSS-HTML</title>
    <!--CSS代码-->
    <!--id选择器-->
    <style type="text/css">
        /*让第二个应用*/
        .class1{
            color: red;
            font-size: 30px;
        }
    </style>
</head>
<body>
    <font class="class1">tianzhendong</font><br>
    <font class="class2">tianzhendong</font><br>
</body>
</html>
```

### 组合选择器

```html
选择器1，选择器2，选择器n{
	属性：值；
}
```

## 常用样式

```html
color: red; /*修改字体颜色*/
width: 300px; /*宽度*/
height: 300px; /*高度*/
background-color: aqua; /*背景颜色*/
font-size: 400px; /*字体大小*/
border: 1px solid red; /*红色的1像素的边框*/
margin-left: auto; /*DIV居中*/
margin-right: auto; /*DIV居中*/
text-align: center; /*文本居中*/
text-decoration: none; /*超链接去下划线*/
/*表格细线*/
border: 1px solid black; /*设置边框*/
border-collapse: collapse;/*将边框合并*/
list-style: none;/*列表去除修饰*/
```

# 4、JavaScript

## 概述

主要用于完成页面的数据验证，运行在客户端，需要运行浏览器来解析执行JavaScript代码

> **JS是弱类型（类型可变），java是强类型（定义变量时类型已定，不能改变）**

**特点：**

* 交互性（它可以做的就是信息的动态交互）
* 安全性（不允许直接访问本地硬盘）
* 跨平台性（只要是可以解释JS的浏览器都可以执行，和平台无关）

## JavaScript和html结合的方式

### 方式1

在head标签中或者在body标签中，使用script标签来书写JavaScript代码

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        alert("hello");
    </script>
</head>
<body>

</body>
</html>
```

### 方式2

使用Script标签引入单独的JavaScript代码文件

```js
alert("hello");
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--引入JavaScript代码
    使用src引入外部的js文件
    注意：script标签也可以用来定义js代码，但是不能和引入同时使用
    相同时使用，可以下面再写一个script标签
    -->
    <script type="text/javascript" src="./1.js"></script>
</head>
<body>

</body>
</html>
```

## 变量



|  变量类型  | 表达式   |
| :--------: | -------- |
|  数值类型  | number   |
| 字符串类型 | string   |
|  对象类型  | object   |
|  布尔类型  | boolean  |
|  函数类型  | function |

特殊的值

* undefined：未定义，所有js变量未赋值时，都是undefined
* null：空值
* NAN：not a number 非数字

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        var i;
        alert(i); //undefined
        i=1;
        alert(i);//number
        i="tian";
        alert(i);//tian
    </script>
</head>
<body></body>
</html>
```

## 关系运算

* 等于：==，简单的字面值比较
* 全等于：===，除了字面值比较外，还会做类型比较

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        var i = 12;
        var j = "12"
        alert(i==j);//true
        alert(i===j);//false
    </script>
</head>
<body></body>
</html>
```

## 逻辑运算

* 且运算：&&
  * 当表达式全为真，返回最后一个表达式的值
  * 当表达式中有一个为假，返回第一个为假的表达式的值
* 或运算：||
  * 表达式全为假，返回最后要给表达式的值
  * 有一个表达式为真，返回第一个为真的表达式的值
* 取反运算：！

在JavaScript中，所有的变量，都可以作为一个boolean类型的变量去使用，0、null、undefined、‘’‘’(空串)都可以认为是false

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        var a = 0;
        var b = null;
        var c = undefined;
        var d = "";
        if(a||b||c||d){
            alert(true);
        }else {
            alert("均为false");//均为false
        }
    </script>
</head>
<body></body>
</html>
```

## 数组



```js
var 数组名 =[];//空数组
var 数组名 =[1,'a',true]//定义数组同时赋值元素
```

JavaScript中数组会自动扩容

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        var arr =[];//空数组
        alert(arr.length);//0
        arr[10] = 100;
        alert(arr.length);//11
        for(var i = 0; i<arr.length; i++){
            alert(arr[i]);
        }
    </script>
</head>
<body></body>
</html>
```

## 函数

js中函数重载会覆盖上面的定义，不允许重载函数

### 方式1：使用function关键字定义

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        //定义无参函数
        function fun(){
            alert("无参函数");
        }
        //调用无参函数
        fun();

        //定义有参函数
        function fun1(a,b){
            alert("有参数函数a："+a+",b:"+b);
        }
        //调用
        fun1(12,13);

        //有返回值函数,直接用return返回
        function fun3(a,b){
            return a+b;
        }
        alert(fun3(1,2));
    </script>
</head>
<body></body>
</html>
```

### 方式2：var fun=function(){}

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        //定义无参函数
        var fun=function(){
            alert("无参函数");
        }
        //调用无参函数
        fun();

        //定义有参函数
        var fun1=function(a,b){
            alert("有参数函数a："+a+",b:"+b);
        }
        //调用
        fun1(12,13);

        //有返回值函数,直接用return返回
        var fun3=function(a,b){
            return a+b;
        }
        alert(fun3(1,2));
    </script>
</head>
<body></body>
</html>
```

### 隐形参数

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        function fun(){
            alert(arguments[0]);//隐形参数
            alert(arguments[1]);
            alert(arguments.length);
        }
        fun(2,4);//输出2，4，2
    </script>
</head>
<body></body>
</html>
```

## js中的事件

> 事件是由电脑输入设备与页面进行交互的响应

### 常用事件：

| 事件名称                 | 功能                                         |
| ------------------------ | -------------------------------------------- |
| onload加载完成事件       | 页面加载完成后，常用于做页面js代码初始化操作 |
| onclick单机事件          | 用于按钮的点击响应事件                       |
| onblur失去焦点事件       | 用于输入框失去焦点后验证其输入内容是否合法   |
| onchange内容发生改变事件 | 用于下拉列表和输入框内容发生改变后操作       |
| onsubmint表单提交事件    | 用于表单提交前，验证所有表单项是否合法       |

### 注册事件

> 告诉浏览器，事件响应后要执行哪些操作代码，叫事件注册或事件绑定

* 静态注册:通过html标签的事件属性直接赋予事件响应后的代码
* 动态注册:先通过js代码得到标签的dom对象，再通过dom对象.事件名 = function(){}形式赋予事件响应后的代码.动态注册基本步骤：
  * 获取标签对象
  * 标签对象.事件名= function(){}

#### onload事件注册

onload加载完成事件 ：页面加载完成后，常用于做页面js代码初始化操作

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        function onloadFun(){
            alert("静态注册onload");
        }
    </script>
</head>
<body onload="onloadFun()"><!--静态注册onload-->
</body>
</html>
```

```HTML
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        window.onload = function (){
            alert("动态注册onload");/*动态注册，固定写法*/
        }
    </script>
</head>
<body>
</body>
</html>
```

#### onclick事件注册

onclick单机事件：用于按钮的点击响应事件

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        /*静态注册onclick*/
        function onclickFun(){
            alert("静态注册onclick");
        }

        /*动态注册onclick
        * 1. 获取标签对象
        * 2. 通过标签对象.事件名=function（）{}*/
        window.onclick = function (){
            /*document是js语言提供的一个文档对象*/
            var btnObj = document.getElementById("btn01");//通过id属性获取标签对象
            btnObj.onclick = function (){
                alert("动态注册onclick");
            }
        }
    </script>
</head>
<body>
    <button onclick="onclickFun()">按钮1</button><!--静态注册onclick-->
    <button id="btn01">按钮2</button><!--动态注册onclick-->
</body>
</html>
```

#### onblur事件注册

onblur失去焦点事件：用于输入框失去焦点后验证其输入内容是否合法

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        /*静态注册*/
        function onblurFun(){
            //console是控制台对象，用于js向浏览器控制台（浏览器页面中按f12进入，console）打印输出，用于测试使用
            // console.log("静态注册");
            alert("静态注册");
        }

        /*动态注册
        * 1. 获取标签对象
        * 2. 通过标签对象.事件名=function（）{}*/
        window.onload = function (){
            /*document是js语言提供的一个文档对象*/
            var btnObj = document.getElementById("btn01");//通过id属性获取标签对象
            btnObj.onblur = function (){
                alert("动态注册");
            }
        }
    </script>
</head>
<body>
    <table align="center">
        <th>
            用户注册
        </th>
        <tr>
            <td>
                用户名：
            </td>
            <td>
                <input type="text"  value="请输入用户名"  onblur="onblurFun()"><!--静态注册-->
            </td>
        </tr>
        <tr>
            <td>
                密码：
            </td>
            <td>
                <input type="text" id="btn01"><!--动态注册-->
            </td>
        </tr>
    </table>
</body>
</html>
```

#### onchange事件注册

onchange内容发生改变事件：用于下拉列表和输入框内容发生改变后操作

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        /*静态注册*/
        function onchangeFun(){
            alert("静态注册");
        }

        /*动态注册
        * 1. 获取标签对象
        * 2. 通过标签对象.事件名=function（）{}*/
        window.onload = function (){
            /*document是js语言提供的一个文档对象*/
            var btnObj = document.getElementById("btn01");//通过id属性获取标签对象
            btnObj.onchange = function (){
                alert("动态注册");
            }
        }
    </script>
</head>
<body>
    <table align="center">
        <th>
            用户注册
        </th>
        <tr>
            <td>
                选择1：
            </td>
            <td>
                <!--静态注册-->
                <select onchange="onchangeFun()">
                    <option>选项1</option>
                    <option>选项2</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                选择2：
            </td>
            <td>
                <!--动态注册-->
                <select id="btn01">
                    <option>选项1</option>
                    <option>选项2</option>
                </select>
            </td>
        </tr>
    </table>
</body>
</html>
```

#### onsubmit事件注册

onsubmint表单提交事件 ：用于表单提交前，验证所有表单项是否合法

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--JavaScript代码-->
    <script type="text/javascript">
        /*静态注册
        * 验证所有表单项是否合法，如果有一个不合法，则阻止表单提交*/
        function onsubmitFun(){
            alert("静态注册，发现不合法");
            return false;//通过return false 组织继续提交
        }

        /*动态注册
        * 1. 获取标签对象
        * 2. 通过标签对象.事件名=function（）{}*/
        window.onload = function (){
            /*document是js语言提供的一个文档对象*/
            var btnObj = document.getElementById("btn01");//通过id属性获取标签对象
            btnObj.onsubmit = function (){
                alert("动态注册");
                return false;//通过return false 组织继续提交
            }
        }
    </script>
</head>
<body>
<table align="center">
    <form action="http://localhost:8080" method="get" onsubmit="return onsubmitFun()">
        <input type="submit" value="静态注册提交">
    </form>
    <form action="http://localhost:8080" id="btn01">
        <input type="submit" value="动态注册提交">
    </form>
</table>
</body>
</html>
```

## DOM模型

Document Object Model文档对象模型：把文档中的标签、属性、文本转化为对象进行管理

![image-20210730210623985](https://i.loli.net/2021/07/30/5vLdeYW4cHU1gqR.png)



### 方法

| close()                | 关闭用 document.open() 方法打开的输出流，并显示选定的数据。  |
| ---------------------- | ------------------------------------------------------------ |
| getElementById()       | 返回对拥有指定 id 的第一个对象的引用。                       |
| getElementsByName()    | 返回带有指定名称的对象集合。                                 |
| getElementsByTagName() | 返回带有指定标签名的对象集合。                               |
| open()                 | 打开一个流，以收集来自任何 document.write() 或 document.writeln() 方法的输出。 |
| write()                | 向文档写 HTML 表达式 或 JavaScript 代码。                    |
| writeln()              | 等同于 write() 方法，不同的是在每个表达式之后写一个换行符。  |

三种查询方法：

* 优先使用getElementById()
* 其次getElementsByName()
* 最后getElementsByTagName()

越往后，查询范围越大

#### getElementById()

返回对拥有指定 id 的第一个对象的引用。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--js-->
    <script type="text/javascript">
        function onclickFun() {
            //通过id获取对象 input
            var elementById = document.getElementById("passWord");
            //获取输入的值对象
            var object = elementById.value;
            //    使用正则表达式验证输入的值是否包含字母、数字、下划线，并且长度再3-15之间
            var patt = /^\w{5,13}$/;
            //获取span对象，用于提示密码是否合法
            var elementById1 = document.getElementById("span");
            /*
            * test()方法用于测试某个字符串是不是匹配正则表达式的要求*/
            if (patt.test(object)) {
                //对提示内容赋值 ,innerHTML表示标签中间的文本
                elementById1.innerHTML = "密码合法";
            }else {
                elementById1.innerHTML = "密码不合法";
            }
        }
    </script>
</head>
<body>
    密码：<input type="password" id="passWord"><br>
    <span id="span" style="color: red"></span>
    <button onclick="onclickFun()">校验</button>
</body>
</html>
```

![image-20210730221657830](https://i.loli.net/2021/07/30/nyg56iMB8HtTwsc.png)

#### getElementsByName()  

返回带有指定名称的对象集合

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JavascriptDemo</title>
    <!--js-->
    <script type="text/javascript">
        /*动态注册onclick，全选按钮*/
        window.onclick =function (){
            //获取对象
        	var elementsByName = document.getElementsByName("hobby");
            //1.获取标签对象
            var elementById1 = document.getElementById("btn1");
            elementById1.onclick = function () {
                for (var i = 0; i < elementsByName.length; i++) {
                    elementsByName[i].checked =true;
                }
            };
        }

        /*静态注册onclick，反选按钮*/
        function onclickFun1(){
            //获取对象
        	var elementsByName = document.getElementsByName("hobby");
            for (var i = 0; i < elementsByName.length; i++) {
                elementsByName[i].checked = !elementsByName[i].checked;
            }
        }

        /*静态注册onclick，全不按钮*/
        function onclickFun2(){
            //获取对象
        	var elementsByName = document.getElementsByName("hobby");
            for (var i = 0; i < elementsByName.length; i++) {
                elementsByName[i].checked = false;
            }
        }
    </script>
</head>
<body>
    兴趣爱好：
    <input type="checkbox" name="hobby" value="c">C;
    <input type="checkbox" name="hobby" value="java">Java;
    <input type="checkbox" name="hobby" value="python">Python;
    <br>
    <button id="btn1">全选</button><!--动态注册-->
    <button onclick="onclickFun1()">反选</button><!--静态注册-->
    <button onclick="onclickFun2()">全不选</button><!--静态注册-->
</body>
</html>
```

#### getElementsByTagName() 

 返回带有指定标签名的对象集合。

和getElementsByName() 类似，不过是使用标签名进行查询

### 节点（标签对象）的常用属性和方法

节点就是标签对象

#### 方法

| 方法                      | 功能                             |
| ------------------------- | -------------------------------- |
| getElementsByTagName()    | 获取当前节点的指定标签名孩子节点 |
| appendChild（oChildNode） | 添加一个子节点                   |

#### 属性

| 属性            | 功能                                       |
| --------------- | ------------------------------------------ |
| childNodes      | 获取当前节点的**所有子节点**               |
| firstChild      | 获取当前节点的**第一个**子节点             |
| lastChild       | 获取当前节点的**最后一个**子节点           |
| parentNode      | 获取当前节点的父节点                       |
| nextSibling     | **下一个**节点                             |
| previousSibling | **上一个**节点                             |
| className       | 用于获取或者设置标签的**class属性值**      |
| innerHTML       | 获取或者设置起始标签和结束标签**中的内容** |
| innerText       | 获取或者设置起始标签和结束标签**中的文本** |



# 5、jQuery

## 介绍

**概述：**jQuery就是JavaScript和查询Query，辅助JavaScript开发的js类库

**核心思想：**写的更少，做的更多，所以它实现了很多浏览器的兼容问题

**优势：**免费、开源，语法设计可以使开发更加便捷

## 安装

### 从网页添加

可以通过多种方法在网页中添加 jQuery。 您可以使用以下方法：

* 从 [jquery.com](http://jquery.com/download/) 下载 jQuery 库
* 从 CDN 中载入 jQuery, 如从 Google 中加载 jQuery

### 下载 jQuery

有两个版本的 jQuery 可供下载：

* Production version - 用于实际的网站中，已被精简和压缩。
* Development version - 用于测试和开发（未压缩，是可读的代码）

以上两个版本都可以从 [jquery.com](http://jquery.com/download/) 中下载。

jQuery 库是一个 JavaScript 文件，您可以使用 HTML 的 <**script**> 标签引用它：

```html
<head>
<script src="jquery-1.10.2.min.js"></script>
</head>
```

**提示：** 将下载的文件放在网页的同一目录下，就可以使用jQuery。

### 替代方案

如果您不希望下载并存放 jQuery，那么也可以通过 CDN（内容分发网络） 引用它。

```html
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
</script>
<script src="https://upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.2.min.js">
</script>
<script src="https://lib.sinaapp.com/js/jquery/2.0.2/jquery-2.0.2.min.js">
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
```

## 初体验

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>jQueryDemo</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        // window.onload = function () {
        //   var btn1obj = document.getElementById("btn1");
        //   btn1obj.onclick = function () {
        //     alert("tian");
        //   };
        // };
        $(function () {         // window.onload = function () {
            var $btn1obj = $("#btn1");  //   var btn1obj = document.getElementById("btn1");
            $btn1obj.click(function () {    //   btn1obj.onclick = function () {
                alert("tianzhendong")
            });
        });
    </script>
</head>
<body>
    <button id="btn1">SayHello</button>
</body>
</html>
```

## jQuery核心函数

**$**是jQuery的核心函数，能完成很多功能，**$**()就是调用这个函数：

1. 传入参数为函数时：表示页面加载完成以后，相当于window.onload = function ()
2. 传入html字符串时：创建html标签对象
3. 传入选择器字符串时：
   1. **$**(“#id属性值”)，根据id查询标签对象。
   2. **$**(“标签名”)，根据标签名查询标签对象。
   3. **$**(“.class”)，根据class查询标签对象
4. 传入DOM对象时：把DOM对象转换为jQuery对象

## jQuery语法

jQuery 语法是通过选取 HTML 元素，并对选取的元素执行某些操作。

#### 基础语法： **$(selector\).action\()**

* 美元符号定义 jQuery
* 选择符（selector）"查询"和"查找" HTML 元素
* jQuery 的 action() 执行对元素的操作

#### 实例:

* $(this).hide() - 隐藏当前元素
* $("p").hide() - 隐藏所有 <**p**> 元素
* $("p.test").hide() - 隐藏所有 class="test" 的 <**p**> 元素
* $("#test").hide() - 隐藏 id="test" 的元素

#### 文档就绪事件

```html
$(document).ready(function(){
 
   // 开始写 jQuery 代码...
 
});
```

这是为了防止文档在完全加载（就绪）之前运行 jQuery 代码，即在 DOM 加载完成后才可以对 DOM 进行操作。

**提示：**简洁写法（与以上写法效果相同）:

```html
$(function(){
 
   // 开始写 jQuery 代码...
 
});
```

## jQuery选择器

jQuery 选择器允许您对 HTML 元素组或单个元素进行操作。

jQuery 选择器基于元素的 id、类、类型、属性、属性值等"查找"（或选择）HTML 元素。 它基于已经存在的 CSS 选择器，除此之外，它还有一些自定义的选择器。

jQuery 中所有选择器都以美元符号开头：$()。

**实例**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>jQueryDemo</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $("button").click(function(){  //标签选择器
                $("p").hide();  //元素选择器
                $("#id1").hide();    //id选择器
                $(".cls1").hide();  //.class选择器
            });
        });
    </script>
</head>

<body>
<h1 id="id1">1级标题</h1>
<h2 class="cls1">这是一个标题</h2>
<p>这是一个段落。</p>
<p>这是另一个段落。</p>

<button>点我</button>
</body>
</html>
```

## jQuery事件

jQuery 是为事件处理特别设计的。

页面对不同访问者的响应叫做事件。

事件处理程序指的是当 HTML 中发生某些事件时所调用的方法。

实例：

* 在元素上移动鼠标。
* 选取单选按钮
* 点击元素

### jQuery 事件方法语法

```html
$("p").click(function(){//定义了一个事件
    // 动作触发后执行的代码!!
});
```

### 常用事件

| 事件名       | 功能                                                         |
| ------------ | ------------------------------------------------------------ |
| click()      | click() 方法是当按钮点击事件，被触发时会调用一个函数。       |
| dblclick()   | 当双击元素时，会发生 dblclick 事件。                         |
| mouseenter() | 当鼠标指针穿过元素时，会发生 mouseenter 事件。               |
| mouseleave() | 当鼠标指针离开元素时，会发生 mouseleave 事件。               |
| mousedown()  | 当鼠标指针移动到元素上方，并按下鼠标按键时，会发生 mousedown 事件。 |
| mouseup()    | 当在元素上松开鼠标按钮时，会发生 mouseup 事件。              |
| hover()      | hover()方法用于模拟光标悬停事件。当鼠标移动到元素上时，会触发指定的第一个函数(mouseenter);当鼠标移出这个元素时，会触发指定的第二个函数(mouseleave)。 |
| focus()      | 当元素获得焦点时，发生 focus 事件。当通过鼠标点击选中元素或通过 tab 键定位到元素时，该元素就会获得焦点。 |
| blur()       | 当元素失去焦点时，发生 blur 事件。                           |
| keydown()    | 当键盘键被按下时发生 keydown 事件。                          |
| keypress()   | 键按下的过程                                                 |
| keyup()      | 当键盘键被松开时发生 keyup 事件。                            |
| submit()     | 当提交表单时，会发生 submit 事件。                           |
| change()     | 当元素的值改变时发生 change 事件（仅适用于表单字段           |

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>jQueryDemo</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
        $(document).ready(function(){
            //click事件
            $("#btn1").click(function () {
                alert("click()事件");
            });
            //dbclick事件
            $("#btn2").dblclick(function () {
                alert("dbclick()事件");
            });
            //mouseenter事件
            $("#id1").mouseenter(function () {
                alert("mouseenter()事件");
            });
            //mouseleave事件
            $("#id1").mouseleave(function () {
                alert("mouseleaver()事件");
            });
        });
    </script>
</head>

<body>
    <button id="btn1">点击按钮</button>
    <button id="btn2">双击按钮</button>
    <br>
    <span id="id1">mouseenter</span>
</body>
</html>
```

## jQuery效果

### 显示/隐藏

#### jQuery hide() 和 show()

通过 jQuery，您可以使用 hide() 和 show() 方法来隐藏和显示 HTML 元素：

参数：

*  speed 参数规定隐藏/显示的速度，可以取以下值："slow"、"fast" 或毫秒。
* callback 参数是隐藏或显示完成后所执行的函数名称。

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>jQueryDemo</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#hide").click(function(){
                $("p").hide(1000);
            });
            $("#show").click(function(){
                $("p").show(1000, function () {//速度和callback函数
                    alert("显示已完成")
                });
            });
        });
    </script>
</head>
<body>
<p>如果你点击“隐藏” 按钮，我将会消失。</p>
<button id="hide">隐藏</button>
<button id="show">显示</button>
</body>
</html>
```

#### toggle()

可以使用 toggle() 方法来切换 hide() 和 show() 方法。显示被隐藏的元素，并隐藏已显示的元素：

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>jQueryDemo</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#id").click(function(){
                $("p").toggle(1000, function () {
                    alert("完成");
                });
            });
        });
    </script>
</head>
<body>
<p>如果你点击按钮，我将会消失/显示。</p>
<button id="id">隐藏/显示</button>
</body>
</html>
```

### 淡入淡出

通过 jQuery，您可以实现元素的淡入淡出效果。

jQuery 拥有下面四种 fade 方法：

| 方法         | 用途                                             |
| ------------ | ------------------------------------------------ |
| fadeIn()     | 用于淡入已隐藏的元素                             |
| fadeOut()    | 用于淡出可见元素。                               |
| fadeToggle() | 在 fadeIn() 与 fadeOut() 方法之间进行切换。      |
| fadeTo()     | 允许渐变为给定的不透明度（值介于 0 与 1 之间）。 |

* 可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。.

* 可选的 callback 参数是 fading 完成后所执行的函数名称。

### 滑动

jQuery 拥有以下滑动方法：

* slideDown()：向下滑动元素
* slideUp()：用于向上滑动元素。
* slideToggle()：在 slideDown() 与 slideUp() 方法之间进行切换。

### 动画

jQuery animate() 方法用于创建自定义动画。

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script> 
$(document).ready(function(){
  $("button").click(function(){
    $("div").animate({
      left:'250px',
      opacity:'0.5',
      height:'150px',
      width:'150px'
    });
  });
});
});
</script> 
</head>
 
<body>
<button>开始动画</button>
<p>默认情况下，所有的 HTML 元素有一个静态的位置，且是不可移动的。 
如果需要改变，我们需要将元素的 position 属性设置为 relative, fixed, 或 absolute!</p>
<div style="background:#98bf21;height:100px;width:100px;position:absolute;">
</div>

</body>
</html>
```

* 必需的 params 参数定义形成动画的 CSS 属性。
* 可选的 speed 参数规定效果的时长。它可以取以下值："slow"、"fast" 或毫秒。
* 可选的 callback 参数是动画完成后所执行的函数名称。

#### 使用相对值

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script> 
$(document).ready(function(){
  $("button").click(function(){
    $("div").animate({
      left:'250px',
      height:'+=150px',
      width:'+=150px'
    });
  });
});
</script> 
</head>
 
<body>
<button>开始动画</button>
<p>默认情况下，所有的 HTML 元素有一个静态的位置，且是不可移动的。 
如果需要改变为，我们需要将元素的 position 属性设置为 relative, fixed, 或 absolute!</p>
<div style="background:#98bf21;height:100px;width:100px;position:absolute;">
</div>

</body>
</html>
```

#### 使用队列功能

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script> 
$(document).ready(function(){
  $("button").click(function(){
    var div=$("div");
    div.animate({height:'300px',opacity:'0.4'},"slow");
    div.animate({width:'300px',opacity:'0.8'},"slow");
    div.animate({height:'100px',opacity:'0.4'},"slow");
    div.animate({width:'100px',opacity:'0.8'},"slow");
  });
});
</script> 
</head>
 
<body>
<button>开始动画</button>
<p>默认情况下，所有的 HTML 元素有一个静态的位置，且是不可移动的。 
如果需要改变为，我们需要将元素的 position 属性设置为 relative, fixed, 或 absolute!</p>
<div style="background:#98bf21;height:100px;width:100px;position:absolute;">
</div>

</body>
</html>
```

#### 停止动画

jQuery stop() 方法用于停止动画或效果，在它们完成之前。

stop() 方法适用于所有 jQuery 效果函数，包括滑动、淡入淡出和自定义动画。

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script> 
$(document).ready(function(){
  $("#flip").click(function(){
    $("#panel").slideDown(5000);
  });
  $("#stop").click(function(){
    $("#panel").stop();
  });
});
</script>
 
<style type="text/css"> 
#panel,#flip
{
	padding:5px;
	text-align:center;
	background-color:#e5eecc;
	border:solid 1px #c3c3c3;
}
#panel
{
	padding:50px;
	display:none;
}
</style>
</head>
<body>
 
<button id="stop">停止滑动</button>
<div id="flip">点我向下滑动面板</div>
<div id="panel">Hello world!</div>

</body>
</html>
```

## 链(Chaining)

Chaining 允许我们在一条语句中运行多个 jQuery 方法（在相同的元素上）

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function()
  {
  $("button").click(function(){
    $("#p1").css("color","red")//("#p1").css("color","red").slideUp(2000).slideDown(2000);
      .slideUp(2000)
      .slideDown(2000);
  });
});
</script>
</head>
<body>

<p id="p1">菜鸟教程!!</p>
<button>点我</button>

</body>
</html>
```

## 获取内容和属性

### 获得内容 - text()、html() 以及 val()

三个简单实用的用于 DOM 操作的 jQuery 方法：

* text() - 设置或返回所选元素的文本内容
* html() - 设置或返回所选元素的内容（包括 HTML 标记）
* val() - 设置或返回表单字段的值

### 获取属性 - attr()

jQuery attr() 方法用于获取属性值。

下面的例子演示如何获得链接中 href 属性的值：

## 添加元素

* append() - 在被选元素的结尾插入内容
* prepend() - 在被选元素的开头插入内容
* after() - 在被选元素之后插入内容
* before() - 在被选元素之前插入内容

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function(){
  $("#btn1").click(function(){
    $("p").append(" <b>追加文本</b>。");
  });

  $("#btn2").click(function(){
    $("ol").append("<li>追加列表项</li>");
  });
});
</script>
</head>

<body>
<p>这是一个段落。</p>
<p>这是另外一个段落。</p>
<ol>
<li>List item 1</li>
<li>List item 2</li>
<li>List item 3</li>
</ol>
<button id="btn1">添加文本</button>
<button id="btn2">添加列表项</button>
</body>
</html>
```



## 删除元素

* remove() - 删除被选元素（及其子元素）
* empty() - 从被选元素中删除子元素

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function(){
  $("button").click(function(){
    $("#div1").remove();
  });
});
</script>
</head>
<body>

<div id="div1" style="height:100px;width:300px;border:1px solid black;background-color:yellow;">

这是 div 中的一些文本。
<p>这是在 div 中的一个段落。</p>
<p>这是在 div 中的另外一个段落。</p>

</div>
<br>
<button>移除div元素</button>

</body>
</html>
```

## 获取并设置 CSS 类

* addClass() - 向被选元素添加一个或多个类
* removeClass() - 从被选元素删除一个或多个类
* toggleClass() - 对被选元素进行添加/删除类的切换操作
* css() - 设置或返回样式属性

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function(){
  $("button").click(function(){
    $("h1,h2,p").addClass("blue");
    $("div").addClass("important");
  });
});
</script>
<style type="text/css">
.important
{
	font-weight:bold;
	font-size:xx-large;
}
.blue
{
	color:blue;
}
</style>
</head>
<body>

<h1>标题 1</h1>
<h2>标题 2</h2>
<p>这是一个段落。</p>
<p>这是另外一个段落。</p>
<div>这是一些重要的文本!</div>
<br>
<button>为元素添加 class</button>

</body>
</html>
```

## css() 方法

设置或返回被选元素的一个或多个样式属性。

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function(){
  $("button").click(function(){
    alert("背景颜色 = " + $("p").css("background-color"));
  });
});
</script>
</head>

<body>
<h2>这是一个标题</h2>
<p style="background-color:#ff0000">这是一个段落。</p>
<p style="background-color:#00ff00">这是一个段落。</p>
<p style="background-color:#0000ff">这是一个段落。</p>
<button>返回第一个 p 元素的 background-color </button>
</body>
</html>
```





# 6、XML

XML 指可扩展标记语言（e**X**tensible **M**arkup **L**anguage）。被设计用来**传输和存储数据**。

HTML 被设计用来显示数据。

XML 不是 HTML 的替代。

XML 和 HTML 为不同的目的而设计：

* XML 被设计用来传输和存储数据，其焦点是数据的内容。
* HTML 被设计用来显示数据，其焦点是数据的外观。

### 用途

XML 应用于 Web 开发的许多方面，常用于简化数据的存储和共享。

### XML 语法规则

1. **XML 文档必须有根元素，它是所有其他元素的父元素，比如以下实例中 root 就是根元素**

```xml
<root>
  <child>
    <subchild>.....</subchild>
  </child>
</root>
```

2. **XML 声明文件的可选部分，如果存在需要放在文档的第一行**

```xml
<?xml version="1.0" encoding="utf-8"?>
```

3. **在 XML 中，省略关闭标签是非法的。所有元素都必须有关闭标签：**

```xml
<p>This is a paragraph.</p>
<br />
```

声明不是 XML 文档本身的一部分，它没有关闭标签。

4. **XML 标签对大小写敏感。标签 <**Letter**> 与标签 <**letter**> 是不同的。**

5. **在 XML 中，所有元素都必须彼此正确地嵌套：**

```xml
<b><i>This text is bold and italic</i></b>
```

6. **在 XML 中，XML 的属性值必须加引号。**

在 XML 中，一些字符拥有特殊的意义。如果您把字符 "<" 放在 XML 元素中，会发生错误，这是因为解析器会把它当作新元素的开始。为了避免这个错误，请用**实体引用**来代替特殊字符：

| &lt;   | <    | less than      |
| ------ | ---- | -------------- |
| &gt;   | >    | greater than   |
| &amp;  | &    | ampersand      |
| &apos; | '    | apostrophe     |
| &quot; | "    | quotation mark |

7. **在 XML 中编写注释的语法与 HTML 的语法很相似。**

8. **在 XML 中，文档中的空格不会被删减。**

HTML 会把多个连续的空格字符裁减（合并）为一个，在 XML 中，文档中的空格不会被删减。

9. **XML 以 LF 存储换行。**

在 Windows 应用程序中，换行通常以一对字符来存储：回车符（CR）和换行符（LF）。

在 Unix 和 Mac OSX 中，使用 LF 来存储新行。

在旧的 Mac 系统中，使用 CR 来存储新行。

XML 以 LF 存储换行。

### XML元素

#### 概述

XML 元素指的是从（且包括）开始标签直到（且包括）结束标签的部分。

一个元素可以包含：

* 其他元素
* 文本
* 属性
* 或混合以上所有...

#### 元素命名规则

XML 元素必须遵循以下命名规则：

* 名称可以包含字母、数字以及其他的字符
* 名称不能以数字或者标点符号开始
* 名称不能以字母 xml（或者 XML、Xml 等等）开始
* 名称不能包含空格

可使用任何名称，没有保留的字词。

### XML解析

。。。。

# 7、Tomcat

## JavaWeb概述

### 概念

javaweb是指所有通过java语言编写的可以通过浏览器访问的程序的总称，叫javaweb

javaweb是基于请求和响应来开发的

* 请求：客户端给服务器发送数据，叫请求request
* 响应：服务器给客户端回传数据，叫响应response

请求和响应是成对出现的，有请求就有响应

![image-20210731113642368](https://i.loli.net/2021/07/31/9mdOKJ47HFMA3p8.png)

### Web资源分类

根据实现的技术和呈现的效果不同，分为静态资源和动态资源

* 静态：html、css、js、txt、MP4视频、jpg图片
* 动态：jsp页面、Servlet程序

### web服务器

其他常用的web服务器：

1. Tomcat
2. Jboss

2. GlassFish

3. Resin

4. Weblogic

## Tomcat概述

由Apache组织提出的一种web服务器，**提供对jsp和Servlet的支持**，是一种**轻量级**的javaweb容器（服务器），也是当前应用最广的javaweb服务器，并且**免费**

### 与servlet版本对应关系

![image-20210731114737054](https://i.loli.net/2021/07/31/ZLPvEwz2aJoKVrR.png)

Servlet程序从2.5版本是现在市面使用最多的版本（xml配置）

Servlet3.0以后，就是注解的版本

## Tomcat使用

### 安装

官网链接：http://tomcat.apache.org/
选择download，找到自己所要的版本，下载对应版本的Tomcat。

有zip和exe两种格式的，zip（64-bit Windows zip(pgp,md5,sha1)）是免安装版的，exe（32-bit/64-bit Windows Service installer(pgp,md5,sha1)）是安装版。同时观察自己的电脑是64位系统还是32位系统。

![在这里插入图片描述](https://img-blog.csdnimg.cn/img_convert/12dcf4419fef39c06c0fb63464792779.png)

### 安装目录介绍

![image-20210731123357654](https://i.loli.net/2021/07/31/WLstoRXBVlYF6y9.png)

### 启动

1. 方法1：双击bin目录下的startup.bat

2. 方法2：在bin目录下打开命令行，输入catalina run命令

### 测试

打开浏览器，键入 http://localhost:8080 进入tomcat页面则表示安装成功

### 停止

双击bin目录下的shutdown.bat

### 启动界面中文乱码

修改C:\apache-tomcat-10.0.8\conf\logging.properties文件中的java.util.logging.ConsoleHandler.encoding = UTF-8为java.util.logging.ConsoleHandler.encoding = GBK

### 修改Tomcat端口号

mysql默认端口号3306

Tomcat默认端口号8080

修改C:\apache-tomcat-10.0.8\conf\server.xml

修改<Connector标签中的端口号，范围1-65535

### 部署web工程到Tomcat

#### 方法1：

部署：

把web工程目录拷贝到webapps目录下即可，如book工程（在webapps生成一个book文件夹）

访问：如果要访问book工程下的index.html：

浏览器输入 http://localhost:8080/book/index.html 

http://localhost:8080代表webapps文件夹

#### 方法2：

部署：

在C:\apache-tomcat-10.0.8\conf\Catalina\localhost\下创建配置文件abc.xml

```xml
<Context path="/abcdsf" docBase="E:\book"></Context>
```

启动时会根据path加载abc配置文件，根据dacBase在E盘找到book工程目录

访问：

浏览器输入 http://localhost:8080/abc/index.html

### 手拖浏览器页面打开和浏览器地址打开页面的区别

手拖使用的是file://协议

浏览器地址访问使用的是：http协议

### 默认工程访问

浏览器地址：http:/ip:port/    没有工程名的时候，默认访问root工程

浏览器地址：http:/ip:port/工程名/  没有资源名的时候，默认访问index.html页面

### IDEA配置Tmocat

…

# 8、Servlet

## 概述

JavaWeb三大组件：

1. Servlet程序
2. Fliter过滤器
3. Listener监听器

servlet是javaEE规范之一，规范就是接口

Servlet是运行在服务器上的一个java小程序，通过HTTP接收和响应客户端发送过来的请求

## 手动实现Servlet程序

1. 编写一个类去实现servlet接口
2. 实现service方法，处理请求，并响应数据
3. 到web.xml中去配置servlet程序的访问地址

web.xml配置文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--servlet标签给tomcat配置servlet程序-->
    <servlet>
        <!--起一个别名-->
        <servlet-name>Hello</servlet-name>
        <!--全类名-->
        <servlet-class>com.example.demo.Hello</servlet-class>
    </servlet>
    <!--配置访问地址-->
    <servlet-mapping>
        <!--告诉服务器当前配置的地址给哪个servlet程序使用-->
        <servlet-name>Hello</servlet-name>
        <!--配置访问地址
        / 斜杠表示地址为：http://ip:port/工程路径
        /h   表示http://ip:port/工程路径/h，可以自定义，需要用/开头
        -->
        <url-pattern>/h</url-pattern>
    </servlet-mapping>
</web-app>
```



### Tomcat 10.0.4 构建类servlet报错：类HelloServlet不是Servlet

tomcat10有个最大的变动就是包名javax.servlet改成了"jakarta.servlet

## Servlet声明周期

1. 执行servlet构造器方法
2. init方法
3. service方法
4. destory方法

1和2在第一次访问的时候创建servlet程序会调用

3是每次访问都会调用

4在web工程停止时调用

## Get、Post请求分发处理

servelet接口实现类：

```java
package com.example.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class Hello implements Servlet {
    public Hello() {
        System.out.println("构造函数");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
//        获取servletRequest的子类型，因为子类型中有getmethd方法
        HttpServletRequest hsr = (HttpServletRequest) servletRequest;
        //获取请求的方式
        String method = hsr.getMethod();
        //输出请求方式是get还是post,根据不同的请求做不同的事情
        if ("GET".equals(method)) {
            doGet();
        } else if ("POST".equals(method)) {
            doPost();
        }
    }
    //post方法
    private void doPost() {
        System.out.println("POST请求");
    }
    //get方法
    private void doGet() {
        System.out.println("get请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destory");

    }
}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="http://localhost:8080/Demo_1/h" method="get">
        <input type="submit">
    </form>
</body>
</html>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--servlet标签给tomcat配置servlet程序-->
    <servlet>
        <!--起一个别名-->
        <servlet-name>Hello</servlet-name>
        <!--全类名-->
        <servlet-class>com.example.demo.Hello</servlet-class>
    </servlet>
    <!--配置访问地址-->
    <servlet-mapping>
        <!--告诉服务器当前配置的地址给哪个servlet程序使用-->
        <servlet-name>Hello</servlet-name>
        <!--配置访问地址
        / 斜杠表示地址为：http://ip:port/工程路径
        /h   表示http://ip:port/工程路径/h，可以自定义，需要用/开头
        -->
        <url-pattern>/h</url-pattern>
    </servlet-mapping>
</web-app>
```

## HttpServlet类

一般在实际开发项目中，都是使用继承httpservlet类的方式实现servlet程序

httpservlet简单，已经实现了get和post的分发处理

1. 继承httpservlet类
2. 根据业务需要重写doGet或者doPost方法

```java
package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Hello2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dopost");
    }
}
```

3. web.xml中配置servlet程序的访问地址

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">


    <servlet>
        <servlet-name>hello2</servlet-name>
        <servlet-class>com.example.demo.Hello2</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>hello2</servlet-name>
        <url-pattern>/h2</url-pattern>
    </servlet-mapping>
</web-app>
```

4. 编写html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="http://localhost:8080/Demo_1/h2" method="get">
        <input type="submit">
    </form>
</body>
</html>
```

## Servlet类继承体系

![image-20210731211509936](https://i.loli.net/2021/07/31/et1soMiyUY8fFPR.png)

## ServletConfig类

Servlet程序的配置信息类

作用：

1. 获取Servlet程序的别名Servlet-name值
2. 初始化init-param
3. 获取servletcontext对象

```java
public class Hello implements Servlet {
    public Hello() {
        System.out.println("构造函数");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
        //1. 获取Servlet程序的别名Servlet-name值
        System.out.println("别名是："+servletConfig.getServletName());//别名是：hello
        //2. 初始化init-param
        System.out.println("init-parm:"+servletConfig.getInitParameter("name1"));//init-parm:tian
        //3. 获取ServletContext对象
        System.out.println(servletConfig.getServletContext());
    }
    //此处略去其他的代码
}
```

## ServletContext类

* 是一个接口，表示Servlet上下文对象
* 一个web工程只有一个servletcontext对象实例
* servletcontext对象是一个域对象

### 域对象

域对象是可以像map一样存取数据的对象，域i指的是存取数据的操作范围

### 作用

1. 获取web.xml中配置的 上下文参数context-param
2. 获取当前的工程路径，格式：/工程路径
3. 获取工程部署后在服务器硬盘上的绝对路径
4. 像map一样存取数据

```java
package com.example.demo;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Hello2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dopost");
        //获取servletcontext对象
        ServletContext servletContext = getServletConfig().getServletContext();
        //获取上下文参数context-param
        System.out.println(servletContext.getInitParameter("namespace"));;
        //获取当前的工程路径，格式：/工程路径
        System.out.println(servletContext.getContextPath());
        //获取工程部署后在服务器硬盘上的绝对路径
        System.out.println(servletContext.getRealPath("/"));
    }
}
```

## HTTP协议

### http协议概述

客户端和服务器之间通信时，发送的数据，需要遵循的规则，叫HTTP协议

HTTP协议中的数据又叫报文

**HTTP协议格式**

* 请求：客户端给服务器发送数据
  * get请求
  * post请求
* 响应：服务器给客户端回传数据

### 请求的HTTP协议格式：

#### GET请求

* 请求行
  * 请求的方式：GET
  * 请求的资源路径[+?+请求参数]
  * 请求的协议版本号    HTTP/1.1
* 请求头：key:value组成，不同的键值对表示不同的涵义

![image-20210731222752388](https://i.loli.net/2021/07/31/VHux2IzkNrqaPv9.png)

#### Post请求

* 请求行
  * 请求的方式：POST
  * 请求的资源路径[+?+请求参数]
  * 请求的协议版本号    HTTP/1.1
* 请求头
  * key:value组成，不同的键值对表示不同的涵义
* 空行
* 请求体：发送给服务器的数据

![image-20210731223112742](https://i.loli.net/2021/07/31/86rKuwIFHBvNRsy.png)

#### 常用请求头说明

![image-20210731223237993](https://i.loli.net/2021/07/31/YIEvoh4SmXNpiJA.png)

#### 请求区分

**GET请求：**

* form标签  method=get
* a标签
* link标签引入css
* Script标签引入js文件
* img标签引入图片
* iframe引入html页面
* 在浏览器地址栏输入地址后敲回车

**POST请求**

* form标签  method=post

### 响应的HTTP协议格式

* 响应行
  * 响应的协议和版本号
  * 响应状态码
  * 响应状态描述符
* 响应头
  * key:value  不同的响应头有不同的涵义
* 空行
* 响应体： 回传给客户端的数据

![image-20210731224119102](https://i.loli.net/2021/07/31/coN1dhXbDsuBmw8.png)

#### 常见的响应码说明

* 200：表示请求成功
* 302：表示请求重定向
* 404：表示请求服务器已经收到了，但是要的数据不存在（请求地址错误）
* 500：表示服务器已经收到请求，但是服务器内部错误（代码错误）

## HttpServletRequest类

### 作用

每次只要有请求进入tomcat服务器，服务器就会把请求过来的HTTP协议信息解析好封装到Request对象中，然后传递到service方法（doGet和doPost）中给我们使用，可以通过HttpServletRequest对象获取到所有请求的信息

### 常用方法

| 方法                | 功能                                 |
| ------------------- | ------------------------------------ |
| req.getRequestURI() | 获取请求的资源路径                   |
| req.getRequestURL() | 获取请求的统一资源定位符（绝对路径） |
| req.getRemoteHost() | 获取客户端的ip地址                   |
| req.getHeader()     | 获取请求头                           |
| req.getMethod()     | 获取请求的方式GET或者POST            |
| req.getParameter()  | 获取请求参数                         |

```java
package com.example.servletdemo;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RequestServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //i.getRequestURI()  获取请求的资源路径
        System.out.println(req.getRequestURI());    //    /Request_servlet/r
        //i.getRequestURL()  获取请求的统一资源定位符（绝对路径）
        System.out.println(req.getRequestURL());    //  http://localhost:8080/Request_servlet/r
        //i.getRemoteHost()     获取客户端的ip地址
        System.out.println(req.getRemoteHost());
        //i.getHeader()     获取请求头
        System.out.println(req.getHeader("USER-Agent"));
        //i.getMethod()     获取请求的方式GET或者POST
        System.out.println(req.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

#### req.getParameter()

```java
package com.example.servletdemo;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RequestServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        String hobby = req.getParameter("hobby");     //适用于单个值
        String[] hobbies = req.getParameterValues("hobby");  //适用于多个值
        System.out.println(username);
        System.out.println(password);
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
    }
}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="http://localhost:8080/Request_servlet/r" method="get">
        用户名：<input type="text" name="username"><br>
        密码：<input type="password" name="password"><br>
        兴趣爱好：<input type="checkbox" value="cpp" name="hobby">C++
        <input type="checkbox" value="c" name="hobby">C
        <input type="checkbox" value="java" name="hobby">Java<br>
        <input type="submit">
    </form>
</body>
</html>
```

### post请求中文乱码问题

在dopost函数中设置请求体的字符集为UTF-8

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //设置设置请求体的字符集为UTF-8，解决post请求的中文乱码问题
    req.setCharacterEncoding("UTF-8");
    //省略其他
}
```

### 请求转发

服务器收到请求后从一个资源跳转到另一个资源

![image-20210803152530155](https://gitee.com/tianzhendong/img/raw/master//images/image-20210803152530155.png)

特点：

1. 浏览器地址栏没有发生变化
2. 是一次请求
3. 共享request域中的数据
4. 可以转发到web-inf目录下
5. 不可以访问工程以外的目录

Servlet1：

```java
package com.example.javaWeb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TianZhendong
 * @date 2021/8/3
 */
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    获取请求的参数,（办事的材料）查看
        String username = req.getParameter("username");
        System.out.println("请求的参数为：" + username);
    //    给材料盖章
        req.setAttribute("key1", "servlet1");
    //    获取转发的地址
        /*
        * 请求转发必须以斜杠开头
        * / 表示http://ip:port/工程名/
        * */
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/s2");
    //    转向servlet2
        requestDispatcher.forward(req, resp);
    }
}
```

servlet2：

```java
package com.example.javaWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TianZhendong
 * @date 2021/8/3
 */
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    获取请求参数
        String username = req.getParameter("username");
        System.out.println("请求参数为：" + username);
    //    查看柜台1的章
        Object key1 = req.getAttribute("key1");
        System.out.println("是否有柜台1的章：" + key1);
    //    处理请求
        System.out.println("处理业务，这里省略");
    }
}
```

web.xml

```xml
<servlet>
    <servlet-name>servlet1</servlet-name>
    <servlet-class>com.example.javaWeb.Servlet1</servlet-class>
</servlet>
<servlet>
    <servlet-name>servlet2</servlet-name>
    <servlet-class>com.example.javaWeb.Servlet2</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>servlet1</servlet-name>
    <url-pattern>/s1</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>servlet2</servlet-name>
    <url-pattern>/s2</url-pattern>
</servlet-mapping>
```

### base标签的作用

设置页面相对路径工作时参照的地址，href属性就是参数的地址值

```html
<head>
    <base href = "http://localhost:8080/demo1/a/b/c.html">
</head>
```

### web中/意义

在web中/是一种绝对路径

* 被浏览器解析得到：http://ip:port/

```html
<a href = "/">斜杠</a>
```

* 被服务器解析得到：http://ip:port/工程路径

```xml
<url-pattern>/s</url-pattern>
```

```java
servletContext.getRealPath("/");
```

## HttpServletResponse类

### 概述

和HttpServletRequest一样，每次请求进来，tomcat服务器都会创建一个response对象传递给servlet程序使用，HttpServletRequest表示请求过来的信息，HttpServletResponse表示相应的信息

### 两个输出流

* 字节流：getOutputStream();常用有下载（传递二进制数据）
* 字符流：getWriter()常用于回传字符串（常用）

两个流同时只能使用一个

### 往客户端回传数据

```java
package com.example.javaWeb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author TianZhendong
 * @date 2021/8/3
 */
public class ResponseIoServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    //    设置服务器和客户端使用UTF-8字符集，解决乱码问题
        response.setContentType("text/html; charset=UTF-8");
        System.out.println(response.getCharacterEncoding());

    //    回传数据
        PrintWriter writer = response.getWriter();
        writer.write("田真帅");
    }
}
```

### 请求重定向

#### 概念

客户端给服务器发送请求，服务器给客户端一些地址，让客户端去新地址访问（之前的地址可能被废弃）

![image-20210803170323153](https://gitee.com/tianzhendong/img/raw/master//images/image-20210803170323153.png)

#### 特点

1. 浏览器地址栏有变化
2. 两次请求
3. 不共享resquest域中的数据
4. 不能访问web-inf下的资源
5. 可以访问工程以外的数据，如百度

#### 使用

resonse1

```java
package com.example.javaWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TianZhendong
 * @date 2021/8/3
 */
public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("访问了源地址response1");
    //    设置相应码的状态，表示重定向
        resp.setStatus(302);
    //    设置响应头，说明新的地址在哪里
        resp.setHeader("Location", "http://localhost:8080/demo1/response2");
    }
}
```

response2

```java
package com.example.javaWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TianZhendong
 * @date 2021/8/3
 */
public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("访问了源地址response1");
    //    设置相应码的状态，表示重定向
        resp.setStatus(302);
    //    设置响应头，说明新的地址在哪里
    //    可以访问
        resp.setHeader("Location", "http://www.baidu.com");
    //    resp.setHeader("Location", "http://localhost:8080/demo1/response2");
    }
}
```

#### 简单写法sendRedirect()

```java
package com.example.javaWeb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TianZhendong
 * @date 2021/8/3
 */
public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("访问了源地址response1");
        resp.sendRedirect("http://www.baidu.com");
    }   
}
```

# 9、jsp

## 概述

全称是java serverpages：java服务器页面

作用：代替servlet程序回传html页面的数据

已逐渐被淘滩

## EL表达式

expression language表达式语言

作用：代替jsp页面中的表达式脚本在jsp页面中进行数据的输出

## jstl标签库

jsp标准标签库，替换jsp中的代码脚本，使得jsp页面变得更加简洁

# 10、 文件的上传和下载

## 文件的上传

1. 有一个form标签，method=post请求
2. form标签的encType属性值必须为multipart/form-data值
3. 在form标签中使用input type=file添加上传的文件
4. 编写服务器代码接收，处理上传的数据

## 文件的下载



# 11、Cookie和Session

## Cookie

### 概述

服务器通知客户端保存键值对的一种技术

客户端有了cookie后，每次请求都发送给服务器

每个Cookie的大小不能超过4kb

### 使用



## Session

- Session就一个接口（HttpSession）
- Session就是会话，用来伟华客户端和服务器之间关联的一种技术
- 每个客户端都有自己的一个Session会话
- Session会话中，经常用来保存用户登陆之后的信息

# 12、Filter过滤器

## 概述

* javaweb三个组件之一，servlet、listener、filter
* filter过滤器是javaee的规范，也就是接口
* 作用：拦截请求，过滤响应

# 13、json

## 概述

JSON（javascript object notation）是一种轻量级的数据交换格式，易于人阅读和编写，同时也易于机器解析和生成。JSON采用完全独立于语言的文本格式，很多语言都提供了对json的支持，使得json成为理想的数据交换格式

轻量级指的是跟xml作比较

数据交换指的是客户端和服务器i之间业务数据的传输格式

## JSON语法

JSON 语法是 JavaScript 语法的子集。

### JSON 语法规则

JSON 语法是 JavaScript 对象表示语法的子集。

- 数据在名称/值对中
- 数据由逗号分隔
- 大括号 **{}** 保存对象
- 中括号 **[]** 保存数组，数组可以包含多个对象

### JSON 值

JSON 值可以是：

- 数字（整数或浮点数）
- 字符串（在双引号中）
- 逻辑值（true 或 false）
- 数组（在中括号中）
- 对象（在大括号中）
- null

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JSON</title>
</head>
<body>
<p>
  姓名：<span id="name"></span><br>
  年龄：<span id="age"></span><br>
  学校：<span id="school"></span><br>
</p>
<script>
  var Json = {
    "name1":"tian",
    "age1":14,
    "school1":"npu"
  };
  document.getElementById("name").innerHTML = Json.name1;
  document.getElementById("age").innerHTML = Json.age1;
  document.getElementById("school").innerHTML = Json.school1;
</script>
</body>
</html>
```

![image-20210804154554069](https://gitee.com/tianzhendong/img/raw/master//images/image-20210804154554069.png)

## JSON vs XML

JSON 和 XML 都用于接收 web 服务端的数据。

JSON 和 XML在写法上有所不同，如下所示：

```json
{
    "sites": [
    { "name":"菜鸟教程" , "url":"www.runoob.com" }, 
    { "name":"google" , "url":"www.google.com" }, 
    { "name":"微博" , "url":"www.weibo.com" }
    ]
}
```

```xml
<sites>
  <site>
    <name>菜鸟教程</name> <url>www.runoob.com</url>
  </site>
  <site>
    <name>google</name> <url>www.google.com</url>
  </site>
  <site>
    <name>微博</name> <url>www.weibo.com</url>
  </site>
</sites>
```

## JSON.parse()

JSON字符串转换为JSON对象

JSON 通常用于与服务端交换数据。

在接收服务器数据时一般是字符串。

我们可以使用 JSON.parse() 方法将数据转换为 JavaScript 对象。

```json
JSON.parse(text[, reviver])
```

**参数说明：**

- **text:**必需， 一个有效的 JSON 字符串。
- **reviver:** 可选，一个转换结果的函数， 将为对象的每个成员调用此函数。

```js
var obj = JSON.parse('{ "name":"runoob", "alexa":10000, "site":"www.runoob.com" }');
```

## JSON.stringify()

JSON对象转换为JSON字符串

例如我们向服务器发送以下数据

```js
var obj = { "name":"runoob", "alexa":10000, "site":"www.runoob.com"};
var myJSON = JSON.stringify(obj);
document.getElementById("demo").innerHTML = myJSON;
```

## JSON使用

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title>
</head>
<body>
<h2>从 JSON 字符串中创建对象</h2>
<p>
网站名: <span id="name"></span><br> 
网站地址: <span id="url"></span><br> 
</p> 
<script>
var txt = '{ "sites" : [' +
'{ "name":"菜鸟教程" , "url":"www.runoob.com" },' +
'{ "name":"google" , "url":"www.google.com" },' +
'{ "name":"微博" , "url":"www.weibo.com" } ]}';

var obj = eval ("(" + txt + ")");

document.getElementById("name").innerHTML=obj.sites[0].name 
document.getElementById("url").innerHTML=obj.sites[0].url 
</script>
</body>
</html>
```

## JSON在java中使用

### JavaBean和JSON转换

* toJson()方法可以把java对象转换成json字符串

* fromJson()相反

```java
Person person = new Person(1, "测试");
//创建GSON对象实例
Gson gson = new Gson();
//java对象转换成json字符串
String s = gson.toJson(person);
//json字符串转换为java对象
Person person1 = gson.fronJson(s,Person.class);
```

# 14、AJAX请求

## 概述

AJAX = Asynchronous JavaScript and XML（异步的 JavaScript 和 XML）。

AJAX 不是新的编程语言，而是一种使用现有标准的新方法。

AJAX 最大的优点是在**不重新加载整个页面的情况下**，可以与服务器交换数据并更新部分网页内容。

AJAX 不需要任何浏览器插件，但需要用户允许JavaScript在浏览器上执行。

**浏览器通过js异步发起请求，局部更新页面的技术**

## 使用

* 第一步：创建 XMLHttpRequest 对象

XMLHttpRequest 用于在后台与服务器交换数据。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。

```js
var xmlhttp;
if (window.XMLHttpRequest)
{
    //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    xmlhttp=new XMLHttpRequest();
}
else
{
    // IE6, IE5 浏览器执行代码
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
```

* 第二步：向服务器发送请求

XMLHttpRequest 对象用于和服务器交换数据。

如需将请求发送到服务器，我们使用 **XMLHttpRequest** 对象的 **open()** 和 **send()** 方法：

```js
xmlhttp.open("GET","ajax_info.txt",true);
xmlhttp.send();
```

| 方法                         | 描述                                                         |
| :--------------------------- | :----------------------------------------------------------- |
| open(*method*,*url*,*async*) | 规定请求的类型、URL 以及是否异步处理请求。*method*：请求的类型；GET 或 POST*url*：文件在服务器上的位置*async*：true（异步）或 false（同步） |
| send(*string*)               | 将请求发送到服务器。*string*：仅用于 POST 请求               |

如果来自服务器的响应并非 XML，请使用 responseText 属性。

responseText 属性返回字符串形式的响应，因此您可以这样使用：

```js
document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
```

如果来自服务器的响应是 XML，而且需要作为 XML 对象进行解析，请使用 responseXML 属性：

```js
xmlDoc=xmlhttp.responseXML;
txt="";
x=xmlDoc.getElementsByTagName("ARTIST");
for (i=0;i<x.length;i++)
{
    txt=txt + x[i].childNodes[0].nodeValue + "<br>";
}
document.getElementById("myDiv").innerHTML=txt;
```

