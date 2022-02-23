---
title: VUE
tags: 前端框架
notebook: JAVA
---
[toc]

# VUE

# 1、前端

## 1.1、前端概述

### 什么是前端

* 前端：针对浏览器的开发，代码在浏览器运行
* 后端：针对服务器的开发，代码在服务器运行

###  前端三剑客

* HTML
* CSS
* JavaScript

>  HTML

HTML（超文本标记语言——HyperText Markup Language）是构成 Web 世界的基石。HTML是一种基础技术，常与CSS、JavaScript一起被众多网站用于设计令人赏心悦目的网页、网页应用程序以及移动应用程序的用户界面。
超文本标记语言（第一版）——在1993年6月作为互联网工程工作小组（IETF）工作草案发布（并非标准）：
HTML 2.0——1995年11月作为RFC 1866发布，在RFC 2854于2000年6月发布之后被宣布已经过时
HTML 3.2——1997年1月14日，W3C推荐标准
HTML 4.0——1997年12月18日，W3C推荐标准
HTML 4.01（微小改进）——1999年12月24日，W3C推荐标准
HTML 5——2014年10月28日，W3C推荐标准

>  CSS

层叠样式表(英文全称：Cascading Style Sheets)是一种用来表现HTML（标准通用标记语言的一个应用）或XML（标准通用标记语言的一个子集）等文件样式的计算机语言。CSS不仅可以静态地修饰网页，还可以配合各种脚本语言动态地对网页各元素进行格式化。
CSS 能够对网页中元素位置的排版进行像素级精确控制，支持几乎所有的字体字号样式，拥有对网页对象和模型样式编辑的能力。

>  JavaScript

JavaScript一种直译式脚本语言，是一种动态类型、弱类型、基于原型的语言，内置支持类型。它的解释器被称为JavaScript引擎，为浏览器的一部分，广泛用于客户端的脚本语言，最早是在HTML（标准通用标记语言下的一个应用）网页上使用，用来给HTML网页增加动态功能。

## 1.2、发展历史

### 静态页面阶段

互联网发展的早期，网站的前后端开发是一体的，即前端代码是后端代码的一部分。
 1.后端收到浏览器的请求
 2.生成静态页面
 3.发送到浏览器

那时的前端页面都是静态的，所有前端代码和前端数据都是后端生成的。前端只是纯粹的展示功能，脚本的作用只是增加一些特殊效果，比如那时很流行用脚本控制页面上飞来飞去的广告。

那时的网站开发，采用的是后端 MVC 模式。

* Model（模型层）：提供/保存数据
* Controller（控制层）：数据处理，实现业务逻辑
* View（视图层）：展示数据，提供用户界面

前端只是后端 MVC 的 V。

### AJAX 阶段

2004年，AJAX 技术诞生，改变了前端开发。Gmail 和 Google 地图这样革命性的产品出现，使得开发者发现，前端的作用不仅仅是展示页面，还可以管理数据并与用户互动。

AJAX 技术指的是脚本独立向服务器请求数据，拿到数据以后，进行处理并更新网页。整个过程中，后端只是负责提供数据，其他事情都由前端处理。前端不再是后端的模板，而是实现了从“获取数据 --》 处理数据 --》展示数据”的完整业务逻辑。

就是从这个阶段开始，前端脚本开始变得复杂，不再仅仅是一些玩具性的功能。

### Web 2.0

Ajax技术促成了 Web 2.0 的诞生。
Web 1.0：静态网页，纯内容展示
Web 2.0：动态网页，富交互，前端数据处理

至此，前端早期的发展史就介绍完了，当时对于前端的要求并不高，只要掌握html css js和一个jquery就足够开发网页了

### 新时代的前端

到目前为止 HTML已经发展到HTML5
CSS已经发展到CSS3.0
JavaScript已经发展到ES9，但是常用的还是ES5和ES6
现代标准浏览器(遵循W3C标准的浏览器)基本已经支持HTML5 CSS3 ES6的大部分特性

### 前端 MVC 阶段

前端代码有了读写数据、处理数据、生成视图等功能，因此迫切需要辅助工具，方便开发者组织代码。这导致了前端 MVC 框架的诞生。

2010年，第一个前端 MVC 框架 Backbone.js 诞生。它基本上是把 MVC 模式搬到了前端，但是只有 M （读写数据）和 V（展示数据），没有 C（处理数据）。因为，Backbone 认为前端 Controller 与后端不同，不需要、也不应该处理业务逻辑，只需要处理 UI 逻辑，响应用户的一举一动。所以，数据处理都放在后端，前端只用事件响应处理 UI 逻辑（用户操作）。

后来，更多的前端 MVC 框架出现。另一些框架提出 MVVM 模式，用 View Model 代替 Controller。MVVM 模式也将前端应用分成三个部分。

* Model：读写数据
* View：展示数据
* View-Model：数据处理

View Model 是简化的 Controller，所有的数据逻辑都放在这个部分。它的唯一作用就是为 View 提供处理好的数据，不含其他逻辑。也就是说，Model 拿到数据以后，View Model 将数据处理成视图层（View）需要的格式，在视图层展示出来。

这个模型的特点是 View 绑定 View Model。如果 View Model 的数据变了，View（视图层）也跟着变了；反之亦然，如果用户在视图层修改了数据，也立刻反映在 View Model。整个过程完全不需要手工处理。

## 1.3、MVVM

MVVM最早由微软提出来，它借鉴了桌面应用程序的MVC思想，在前端页面中，把Model用纯JavaScript对象表示，View负责显示，两者做到了最大限度的分离 把Model和View关联起来的就是ViewModel。
ViewModel负责把Model的数据同步到View显示出来，还负责把View的修改同步回Model
View 和 Model 之间的同步工作完全是自动的，无需人为干涉
因此开发者只需关注业务逻辑，不需要手动操作DOM, 不需要关注数据状态的同步问题，复杂的数据状态维护完全由 MVVM 来统一管理

![img](https://i.loli.net/2021/08/20/Kn9bm7Eq1e25I6h.jpg)



一个MVVM框架和jQuery操作DOM相比有什么区别？ 我们先看用jQuery实现的修改两个DOM节点的例子：

```text
<!-- HTML -->
<p>Hello, <span id="name">Bart</span>!</p>
<p>You are <span id="age">12</span>.</p>

Hello, Bart!

You are 12.
```

用jQuery修改name和age节点的内容：

```text
var name = 'Homer';
var age = 51;

$('#name').text(name);
$('#age').text(age);
```

如果我们使用MVVM框架来实现同样的功能，我们首先并不关心DOM的结构，而是关心数据如何存储。最简单的数据存储方式是使用JavaScript对象：

```text
var person = {
    name: 'Bart',
    age: 12
}
```

我们把变量person看作Model，把HTML某些DOM节点看作View，并假定它们之间被关联起来了。

要把显示的name从Bart改为Homer，把显示的age从12改为51，我们并不操作DOM，而是直接修改JavaScript对象：

```text
person.name = 'Homer';
person.age = 51;
```

执行上面的代码，我们惊讶地发现，改变JavaScript对象的状态，会导致DOM结构作出对应的变化！这让我们的关注点从如何操作DOM变成了如何更新JavaScript对象的状态，而操作JavaScript对象比DOM简单多了！

这就是MVVM的设计思想：关注Model的变化，让MVVM框架去自动更新DOM的状态，从而把开发者从操作DOM的繁琐步骤中解脱出来！



![img](https://i.loli.net/2021/08/20/aAzwQS5eGK7vWtZ.jpg)

### 三大MVVM框架

* Vue
* React
* Angular

>  Vue

Vue框架诞生于2014年，其作者为中国人——尤雨溪，也是新人最容易入手的框架之一，不同于React和Angular,其中文文档也便于大家阅读和学习。

> React

React起源于Facebook的内部项目，因为该公司对市场上所有JavaScript MVC框架，都不满意，就决定自己写一套，用来架设Instagram的网站。做出来以后，发现这套东西很好用，就在2013年5月开源了。

> Angular

Angular是谷歌开发的 Web 框架，具有优越的性能和绝佳的跨平台性。通常结合TypeScript开发，也可以使用JavaScript或Dart，提供了无缝升级的过渡方案。于2016年9月正式发布。

目前国内使用人数最多、最火的框架是Vue

### webpack

如今对于每一个前端工程师来说，webpack已经成为了一项基础技能，它基本上包办了本地开发、编译压缩、性能优化的所有工作。
它的诞生意味着一整套工程化体系开始普及，并且让前端开发彻底告别了以前刀耕火种的时代。现在webpack之于前端开发，正如同gcc/g++之于C/C++，是一个无论如何都绕不开的工具。

### TypeScript(TS)

TypeScript 是 Microsoft 开发和维护的一种面向对象的编程语言。它是JavaScript的超集，包含了JavaScript的所有元素，可以载入JavaScript代码运行，并扩展了JavaScript的语法。 TypeScript 具有以下特点：

* TypeScript是Microsoft推出的开源语言，使用Apache授权协议
* TypeScript增加了静态类型、类、模块、接口和类型注解

在开发大型项目时使用TS更有优势

### NodeJs

Node.js是一个Javascript运行环境(runtime environment)，发布于2009年5月，由Ryan Dahl开发，实质是对Chrome V8引擎进行了封装。Node.js对一些特殊用例进行优化，提供替代的API，使得V8在非浏览器环境下运行得更好。严格的来说，Node.js其实是一个后端语言。

特点： *单线程* 非阻塞IO *事件驱动* V8引擎

## 1.4、现在的前端能做什么？

* 游戏开发(Egret Layabox coco2d-js)
* web开发(pc 移动端设备)
* webApp开发(Dcloud RN weex ionic)
* 图形开发WebGl(three.js)
* 小程序/快应用
* 后端(nodejs)
* 桌面应用(electron)
* 嵌入式开发(Ruff)

## 1.5、前端的未来

现在基于Web的前端技术，将演变为未来所有软件的通用的GUI解决方案。 所以前端有可能会变成一名端工程师。 *PC端* 手机端 *TV端* VR端

......

## 1.6、一名合格的前端需要掌握哪些技能

* photoshop切图(必修)
* html css js(特别是html5 css3 es6)(必修)
* 三大前端框架至少精通一个(必修)
* nodejs(选修)
* 自动化构建工具webpack(必修)
* http协议(必修)
* 浏览器渲染流程及原理(必修)
* TypeScript(选修)

## 2、
