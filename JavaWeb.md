---

title: JavaWeb
tags: Java
notebook: JAVA
---



[toc]

# JavaWeb

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

```html
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

# JavaScript

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

```html
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



# jQuery

## 介绍

**概述：**jQuery就是JavaScript和查询Query，辅助JavaScript开发的js类库

**核心思想：**写的更少，做的更多，所以它实现了很多浏览器的兼容问题

**优势：**免费、开源，语法设计可以使开发更加便捷

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



# 删除元素

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

