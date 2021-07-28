---
title: JavaWeb
tags: Java
notebook: JAVA
---

[toc]

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

* 无序列表始于 <ul> 标签，每个列表项始于 <li>

* 有序列表始于<ol>标签，每个列表始于<li>
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

表格由 <table> 标签来定义。每个表格均有若干行（由 <tr> 标签定义），每行被分割为若干单元格（由 <td>  标签定义）。字母 td 指表格数据（table data），即数据单元格的内容。数据单元格可以包含文本、图片、列表、段落、表单、水平线、表格等等。表格的表头使用 <th> 标签进行定义。

* <th>：表头
* <tr>：行
* <td>：列

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

