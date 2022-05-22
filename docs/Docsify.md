# Docsify

[toc]

[codesheep微信文章](https://mp.weixin.qq.com/s?__biz=MzU4ODI1MjA3NQ==&mid=2247510629&idx=1&sn=48e481b9f176ebeb2d68342af3f33317&chksm=fddd7ca1caaaf5b73efe05dcb5b87c570344708e2a7bddffb5c8bd08638b631e595a4d2b646c&mpshare=1&scene=1&srcid=0223V1apWEBHMQrFHzWwPQiN&sharer_sharetime=1645581220908&sharer_shareid=9615bd3fd5ee967a5d2b03b78f23d393#rd)

## 框架对比

其实做这种网站，完全不用自己手写，现成的工具太多了，这地方随便举几个典型例子吧。

### Hexo

这是一个大家用得非常广泛的静态博客，两年前咱们这里就已经演示过它的使用以及部署上线。

![图片](./assets/202202232332738.png)

工具地址：https://hexo.io

### VuePress

一款基于Vue框架驱动的静态网站生成器，比较符合用于知识文档网站的建站需求，现在用的小伙伴也很多。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232332139.png)

工具地址：https://vuepress.vuejs.org/

### docsify

docsify同样是一个很好用的知识文档网站生成框架，轻量简便，而且无需构建，写完内容就可以直接发布，界面干净又卫生。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232332353.png)

工具地址：https://docsify.js.org/

- 无需构建，写完文档直接发布
- 容易使用并且轻量 (压缩后 ~21kB)
- 智能的全文搜索
- 提供多套主题
- 丰富的 API
- 支持 Emoji
- 兼容 IE11
- 支持服务端渲染 SSR ([示例](https://github.com/docsifyjs/docsify-ssr-demo))

### Gitbook

GitBook也是一个可以用来制作知识库文档以及精美电子书网站的工具框架，不少技术电子书文档都是用它来做的。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232332503.png)

工具地址：https://github.com/GitbookIO/gitbook

## 前置环境

docsify唯一需要的一个前置工具就是`npm`工具

查看是否安装及版本

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232334892.png)

如果没有的话，直接在本地电脑上安装一个`node.js`环境就可以了。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232334539.jpeg)

不管是macOS还是Windows系统，直接去`https://nodejs.org/`官网下载个安装包就可以了，直接下一步下一步即可，安装完成之后会包含npm工具。

## 安装Docsify

```bash
npm install -g docsify-cli

docsify -v	//查看版本
```

## 开始

### 新建项目文件夹

接下来我们从零开始来新建一个本地的项目文件夹。

可以在任何地方新建，名字也可以随便命名，这里取名为`repository`，代表的意思是知识仓库

### 初始化网站

进入到上述新建的项目文件夹`repository`，执行如下命令即可初始化网站，非常简单：

```bash
docsify init
```

初始化成功之后，命令行里也会输出对应初始化成功的打印信息。

### 启动本地预览

```bash
docsify serve
```

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232337249.png)

我们会发现docsify会帮我们在`localhost:3000`启动一个知识库网站。

然后在浏览器打开`http://localhost:3000`即可看见网站效果。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232337010.png)

当然，这时候的页面看起来非常简陋，几乎什么也没有。

接下来我们来看一看网站的文件结构，这个工具是如何做到这么快就可以启动一个网站的呢。

## 项目结构

上文执行完`docsify init`命令成功初始化网站后，docsify会在项目文件夹里自动帮我们生成以下两个文件：

- `index.html`：网站主入口文件和配置文件
- `README.md`：网站内容文件。支持Markdown格式，docsify可以帮我们把该Markdown源文件渲染成网页进行展示。

所以我们只需要直接编辑生成的这个README.md文件就能更新网站的内容。

![image-20220223233926608](https://gitee.com/tianzhendong/img/raw/master//images/202202232339692.png)

`coverpage`：封面

`navbar`：导航栏

`sidebar`：侧边栏

`logo.ico`：网页图标

## 配置

https://blog.csdn.net/Lonelyooacz/article/details/103490545

### 页面和URL路径

如果需要创建多个页面，或者需要多级路由的网站，在docsify里能很容易实现。

打比方说，你的目录结构如下：

```
.
└── repository
    ├── README.md
    ├── test.md
    └── content
        ├── README.md
        └── bigdata.md
```

那么对应的页面访问URL地址将是:

```
repository/README.md          =>  http://127.0.0.1:3000/
repository/test.md            =>  http://127.0.0.1:3000/test
repository/content/README.md  => http://127.0.0.1:3000/content/
repository/content/bigdata.md => http://127.0.0.1:3000/content/bigdata
```

所以只需要在项目目录里添加其他`.md`文件，或者目录层级，即可形成多页面网站，非常简单易懂！

### 修改主题

docsify提供了多套主题可供使用。

直接修改`index.html`中引入的CSS文件即可修改网站主题。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232343575.png)

目前提供有好几套主题可供选择：

```html
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/vue.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/buble.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/dark.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/pure.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/themes/dolphin.css">
```

### 设置封面

像上文刚刚这样创建出来的网站其实是没有封面的，如果需要添加封面，可以通过在`index.html`中设置`coverpage`参数为`true`来实现：

```html
coverpage: true
```

这样既可开启封面功能。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232342034.png)

封面功能开启之后，我们可以直接去项目文件夹里创建一个`_coverpage.md`文件来制作封面内容。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232342150.png)

比如我们这地方，添加了`_coverpage.md`文件之后，在里面写上如下几段文字：

```markdown
![logo](https://cdn.jsdelivr.net/gh/justacoder99/r2coding@master/img/r2coding_logo_cover.7hb2s8l3eqk0.png)

- 本站取名为r2coding，即Road To Coding，意为编程自学之路，是自学编程以来所用资源和分享内容的大聚合。旨在为编程自学者提供一系列清晰的学习路线、靠谱的资源、高效的工具、和务实的文章，方便自己也方便他人。**网站内容会持续保持更新，欢迎收藏品鉴！**

## 记住，一定要善用 `Ctrl+F` 哦！

[**联系作者**](https://github.com/rd2coding/Road2Coding)
[**开启阅读**](README.md)
```

在网页上渲染之后，即可看到如下效果：

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232342169.png)

所以其操作是非常简单的。

### 设置导航栏

像上文刚刚这样创建出来的网站顶部右上角其实是没有导航栏的，如果需要添加导航栏，可以通过在`index.html`中设置`loadNavbar`参数来开启：

```
loadNavbar: true
```

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232343460.png)

导航栏功能开启之后，我们可以直接去项目文件夹里创建一个`_navbar.md`文件来制作导航栏菜单内容。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232342788.png)

比如我们这地方，添加了`_navbar.md`导航栏之后的效果大致如下：

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232342908.png)



### 设置章鱼猫

可以通过在`index.html`中设置`repo`参数来开启网站右上角的章鱼猫链接的小图标功能

```
repo: 'https://github.com/rd2coding/Road2Coding'
```

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232342609.png)

设置完成后的效果如下，还是很萌的，鼠标移到上面，小尾巴还能摇一摇。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232342876.png)

### 设置logo

可以通过在`index.html`中设置`name`参数或者`logo`参数来开启网站侧边栏左上角的标题或者logo显示功能：

```
name: 'Road To Coding',
logo: 'https://cdn.jsdelivr.net/gh/justacoder99/r2coding@master/img/r2coding_logo_sidebar.1na4hwjnopq8.png'
```

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232342324.png)

比如这地方设置了logo图片之后，效果大致如下：

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232342942.png)

### 设置tabs

在`index.html`中添加 docsify-tabs 插件。

```html
<!-- docsify (latest v4.x.x)-->
<script src="https://cdn.jsdelivr.net/npm/docsify@4"></script>

<!-- docsify-tabs (latest v1.x.x) -->
<script src="https://cdn.jsdelivr.net/npm/docsify-tabs@1"></script>
```

查看[选项](https://jhildenbiddle.github.io/docsify-tabs/#/?id=options)部分并根据需要进行配置。

```html
window.$docsify = {
  // ...
  tabs: {
    persist    : true,      // default
    sync       : true,      // default
    theme      : 'classic', // default
    tabComments: true,      // default
    tabHeadings: true       // default
  }
};
```

查看[自定义](https://jhildenbiddle.github.io/docsify-tabs/#/?id=customization)部分并根据需要设置主题属性。

```html
<style>
  :root {
    --docsifytabs-border-color: #ededed;
    --docsifytabs-tab-highlight-color: purple;
  }
</style>
```

使用HTML 注释定义选项卡集`tabs:start`。`tabs:end`

HTML 注释用于标记选项卡集的开始和结束。当 Markdown 在您的 docsify 站点（例如 GitHub、GitLab 等）之外呈现为 HTML 时，使用 HTML 注释可防止显示与选项卡相关的标记。

```markdown
<!-- tabs:start -->

...

<!-- tabs:end -->
```

使用**标题 + 粗体**标记定义选项卡集中的选项卡。

标题文本将用作选项卡标签，所有后续内容将与该选项卡相关联，直到下一个选项卡或`tab:end`评论的开始。使用标题 + 粗体标记允许使用标准标记定义选项卡，并确保选项卡内容在您的 docsify 站点（例如 GitHub、GitLab 等）之外呈现时显示带有标题。

```markdown
<!-- tabs:start -->

#### **English**

Hello!

#### **French**

Bonjour!

#### **Italian**

Ciao!

<!-- tabs:end -->
```

<!-- tabs:start -->

#### **English**

Hello!

#### **French**

Bonjour!

#### **Italian**

Ciao!

<!-- tabs:end -->

效果：

![image-20220224000446534](https://gitee.com/tianzhendong/img/raw/master//images/202202240004587.png)

### alert插件

https://github.com/fzankl/docsify-plugin-flexible-alerts

### 首页html文件

```html
<!DOCTYPE html>
<html lang="zh-CN">

<head>
  <meta charset="UTF-8">
  <title>Tian's Blog</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="description" content="Description">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"> -->
  <link rel="icon" href="./5.ico">
  <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify/lib/themes/vue.css">
  <!-- 谷歌站点收录 -->
  <meta name="google-site-verification" content="qTFCf1hJ275saQ7H1kin5t2DVpznBKAj0Gi50XMOVMo" />
  <!-- 百度站点收录-->
  <meta name="baidu-site-verification" content="SZyWUIzWiU" />
  <!-- gittalk -->
  <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/gitalk/dist/gitalk.css">

  <!-- 支持 LaTex 语言 -->
  <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/katex@latest/dist/katex.min.css" />
  <!-- <link rel="stylesheet" href="https://wugenqiang.js.org/src/css/iconfont.css"> -->
  <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/custom.css"> -->
    <!-- alert -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/sweetalert.min.css" type='text/css' media='all' />
  <!-- 友链 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/css/friends-link.css">
  <!-- 自定义特色样式：by myself -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/css/me.css">


</head>
<style>
    @import 'vuetian.css';
    /* @import 'base.user.css'; */
    /*加重描述strong*/
    .markdown-section strong {
      color: rgb(19, 192, 235);
    }

    .app-nav{
      position: fixed;
      margin: 0;
      padding: 10px 40px 10px 0px;/*上、右、下、左*/
      width: calc(100% - 325px);
      /* background-color: #fff; */
      height: 55px;
      color: rgb(22, 168, 107);
      /* border-bottom: 1px solid #eee; */
    }

    /*.github-corner{
      position: absolute;
      z-index: 999999;
      top: 0;
    }*/

    .markdown-section code {
      border-radius: 2px;
      font-family: "Helvetica Neue",Helvetica,"Hiragino Sans GB","Microsoft YaHei",Arial,sans-serif;
      font-size: 16px !important;
      margin: 0 2px;
      padding: 3px 5px;
      white-space: nowrap;
      border: 1px solid #282c34;
      color: rgb(184, 101, 208);
    }

    .markdown-section > div > img, .markdown-section pre {
      box-shadow: 2px 2px 20px 6px rgb(255, 255, 255) !important;
    }

    .markdown-section a:not(:hover) {
      text-decoration: none;
    }

    /*侧边栏*/
    .sidebar {
      padding-top: 6px;
      width: 200px;
    }

    /* aside.sidebar ul li {
      margin: 0;
      position: relative;
    }

    aside.sidebar ul li ul {
      margin: 6px 0;
    }

    aside.sidebar ul li p {
      padding-left: 22px;
      font-size: 18px;
      font-weight: normal;
    }

    aside.sidebar ul li a {
      line-height: 35px;
      font-size: 14px;
      padding: 3px 0 3px 22px;
    }

    aside.sidebar ul li.active > a {
      font-size: 16px !important;
    } */

    /* aside.sidebar ul li.active > a:before {
      content: '' !important;
      position: absolute !important;
      margin: 0 !important;
      width: 10px !important;
      height: 10px !important;
      top: 15px !important;
      left: 0px !important;
      border-radius: 50% !important;
      background-color: #fed24a !important;
      box-shadow: 0 0 0 3px rgba(254, 210, 74, 0.4) !important;
    } */


    /* 一级标题 */
    /* h1 span{
      display:inline-block;
      background: rgb(66, 185, 131);
      color:#ffffff;
      padding:  10px  16px;
      border-radius:5px;
      box-shadow: 1px 1px 3px black;
    } */
    


    /*代码块背景*/
    p code{
      background-color: rgb(255, 255, 255) !important;
    }

    .markdown-section p.tip,
    .markdown-section tr:nth-child(1n) {
      font-size: 16px !important;
    }

    .markdown-section h1 {
      margin: 3rem 0 2rem 0;
    }

    .markdown-section h2 {
      margin: 2rem 0 1rem;
    }
    img,
    pre {
      border-radius: 5px;
    }

    /*添加代码块复制按钮样式*/
    .docsify-copy-code-button {
      background: #00a1d6 !important;
      color: #FFFFFF !important;
      font-size: 13px !important;
    }

    ::after{
      color: #9da2fd !important;
      font-size: 13px !important;
    }
    .markdown-section>p {
      font-size: 16px !important;
    }


    /*代码块头部图标 start*/
    .markdown-section pre:before {
      content: '';
      display: block;
      background: url(https://gitee.com/tianzhendong/img/raw/master//images/202202280805295.png);
      height: 30px;
      background-size: 40px;
      background-repeat: no-repeat;
      background-color: #1C1C1C;
      background-position: 10px 10px;
    }
    /*代码块头部图标 end*/

    .markdown-section pre>code {
      color: #c0c3c1 !important;
      font-family: 'Inconsolata', consolas,"PingFang SC", "Microsoft YaHei", monospace !important;
      background-color: #212121 !important;    // #accfff  #282c34
      font-size: 15px !important;
      white-space: pre !important;
      line-height: 1.5 !important;
      -moz-tab-size: 4 !important;
      -o-tab-size: 4 !important;
      tab-size: 4 !important;
    }

    ol, ul, li{
      line-height: 27px !important;
      font-size: 16px !important;
    }
    @media (min-width:600px) {
      .markdown-section pre>code {
        font-size: 15px !important;
        letter-spacing: 1.1px !important;
      }

    }
    @media (max-width:600px) {
      .markdown-section pre>code {
        padding-top: 5px;
        padding-bottom: 5px;
        padding-left: 16px !important;
      }
      pre:after {
        content: "" !important;
      }
    }
    section.cover h1 {
      margin: 0;
    }

    pre {
      background-color: #212121 !important;
    }

    @media (min-width:600px) {
      pre code {
        /*box-shadow: 2px 1px 20px 2px #aaa;*/
        /*border-radius: 10px !important;*/
        padding-left: 20px !important;
      }

    }

    @media (max-width:600px) {
      pre {
        padding-left: 3px !important;
        padding-right: 3px !important;
        margin-left: -20px !important;
        margin-right: -20px !important;
        box-shadow: 0px 0px 20px 0px #f7f7f7 !important;
      }

      /*代码块复制按钮默认隐藏*/
      .docsify-copy-code-button {
        display: none;
      }

      .advertisement{
        display: none;
      }

    }

    .markdown-section pre {
      padding-left: 0 !important;
      padding-right: 0px !important;
    }

    .markdown-section {
      margin: 0 3.2% !important;
    }

    /*修改代码块代码颜色显示*/
    .token.directive.keyword{
      color: #4faee2 !important;
    }

    .token.keyword{
      color: #c678dd !important;
    }

    .token.comment{
      color: #737c8b !important;
    }

    .token.tag{
      color: #a589ad !important;
    }

    .token.attr-name{
      color: #de916c !important;
    }

    .token.attr-value{
      color: #4faee2 !important;
    }

    .token.macro.property{
      color: #4faee2 !important;
    }

    .token.function{
      color: #e6b456 !important;
    }
    .token.string{
      color: #98b755 !important;
    }
    .token.punctuation{
      color: #c0c3c1 !important;
    }

    .token.number{
      color:#c0c3c1  !important;
    }

    a.section-link{
      font-size: .9rem !important;
    }

    .advertisement {
      position: fixed;
      right: 20px;
      top: 100px;
      width: 110px;
      box-shadow: -1px 0 2px 0px #c5ebda;
      padding: 10px;
      z-index: 99;
      background-color: #fff;
      text-align: center;
    }

    .advertisement p,
    h4 {
      margin: 0;
      padding: 0;
    }

    .advertisement .Tencent_code h4 {
      font-size: 15px;
      color: #25a46a;
      margin-bottom: 10px;
    }

        /*滚动条样式 start*/
    /* 滚动条宽度 */
    ::-webkit-scrollbar{width:5px;}
    /* 滚动条颜色 */
    ::-webkit-scrollbar-thumb{
      background: #33a9dc;
      background-image: linear-gradient(#6ecd56, #33a9dc);
      border-radius: 2em;
    }

</style>

<body>
  <!-- 读条显示 -->
  <div id="app">🏃‍🏃‍🏃‍💨 加载中...</div>
  
  <!-- 右侧显示公众号 -->
  <!-- <div class="aside_container">
    <div class="advertisement">
      <div class="Tencent_code">
        <h4>关注作者公众号</h4>
        <p style="font-size: 12px;">万千小伙伴陪你一起学</p>
        <img src="https://cdn.jsdelivr.net/gh/wugenqiang/PictureBed/images01/20200808182633.jpg" alt="EnjoyToShare" />
      </div>
    </div>
</div> -->
      <!-- gittalk -->
  <!-- <script src="//cdn.jsdelivr.net/npm/docsify-edit-on-github"></script>
  <script src="//cdn.jsdelivr.net/npm/gitalk/dist/gitalk.min.js"></script> -->
  <script>
    window.$docsify = {
       // 项目名称
      name: 'Tian',
      logo: 'https://gitee.com/tianzhendong/img/raw/master//images/202202281213460.png',
          // 仓库地址，点击右上角的Github章鱼猫头像会跳转到此地址
      repo: 'https://gitee.com/tianzhendong/NoteBooks',

          // 侧边栏支持，默认加载的是项目根目录下的_sidebar.md文件
      // loadSidebar: true,
          // 导航栏支持，默认加载的是项目根目录下的_navbar.md文件
      coverpage: true,
          // 最大支持渲染的标题层级
      maxLevel: 4,
          // 自定义侧边栏后默认不会再生成目录，设置生成目录的最大层级（建议配置为2-4）
      subMaxLevel: 3,
      //主题颜色
      // themeColor: '#33a9dc',
      auto2top: true,  //当路线改变时,滚动到屏幕的顶部
      loadNavbar: true,//_navbar.md如果为真，则从_navbar.md文件加载navbar ，否则从指定的路径加载
      mergeNavbar: true,//Navbar将在小屏幕上与侧边栏合并
      executeScript: true,//执行页面上的脚本。只解析第一个脚本标记（演示）。如果存在Vue，则默认开
      //subMaxLevel: 6,//在自定义边栏中添加目录（TOC)
      externalLinkTarget: '_blank', //外链打开方式：_blank表示在新标签页中打开
      // onlyCover: true,   //设置后封面不显示导航栏
      topMargin: 60,//调整top
      //executeScript: true,//执行页面上的脚本，仅解析第一个脚本标签
      search: {
        paths: 'auto',
        placeholder: '🔍 搜索',
        noData: '😒 找不到结果',
        // Headline depth, 1 - 6
        depth: 6,
        maxAge: 86400000, // 过期时间，单位毫秒，默认一天
      },//添加搜索框
      // plugins: [
      //   EditOnGithubPlugin.create("https://gitee.com/tianzhendong")
      // ]
      // 谷歌分析 SEO
      ga: 'UA-164658031-2',

      // footer: {
      //   copy: '<div class = "over" >完结</div><br/><span>我能想到最浪漫的事，就是我喝咖啡你付钱~😆😏 ❤️ 打赏地址：<a href="https://wugenqiang.js.org/sponsor/" target="_blank">https://wugenqiang.js.org/sponsor/</a></span><iframe src="https://wugenqiang.js.org/sponsor/" style="overflow-x:hidden;overflow-y:hidden; min-height:240px; width:100%;"  frameborder="0" scrolling="no"></iframe><br/><span id="sitetime"></span><br/><span>Copyright &copy; 2019 - 至今</span>',
      //   auth: ' <a href="https://wugenqiang.github.io/" target="_blank">🏷️ EnjoyToShare Blog</a> <span> 一个人可以走的很快，但一群人才能走的更远！</span>',
      //   pre: '<hr/>',
      //   style: 'text-align: left;',
      // },//添加页脚

      markdown: {
        renderer: {
          code: function(code, lang, base=null) {

            if (lang === "dot") {
              return (
                      '<div class="viz">'+ Viz(code, "SVG")+'</div>'
              );
            }

            var pdf_renderer = function(code, lang, verify) {
              function unique_id_generator(){
                function rand_gen(){
                  return Math.floor((Math.random()+1) * 65536).toString(16).substring(1);
                }
                return rand_gen() + rand_gen() + '-' + rand_gen() + '-' + rand_gen() + '-' + rand_gen() + '-' + rand_gen() + rand_gen() + rand_gen();
              }
              if(lang && !lang.localeCompare('pdf', 'en', {sensitivity: 'base'})){
                if(verify){
                  return true;
                }else{
                  var divId = "markdown_code_pdf_container_" + unique_id_generator().toString();
                  var container_list = new Array();
                  if(localStorage.getItem('pdf_container_list')){
                    container_list = JSON.parse(localStorage.getItem('pdf_container_list'));
                  }
                  container_list.push({"pdf_location": code, "div_id": divId});
                  localStorage.setItem('pdf_container_list', JSON.stringify(container_list));
                  return (
                          '<div style="margin-top:'+ PDF_MARGIN_TOP +'; margin-bottom:'+ PDF_MARGIN_BOTTOM +';" id="'+ divId +'">'
                          + '<a href="'+ code + '"> Link </a> to ' + code +
                          '</div>'
                  );
                }
              }
              return false;
            }
            if(pdf_renderer(code, lang, true)){
              return pdf_renderer(code, lang, false);
            }
            //return this.origin.code.apply(this, arguments);
            return (base ? base : this.origin.code.apply(this, arguments));
          }
        }
      },
      


    }
    

  </script>  
  <script src="//unpkg.com/docsify-count/dist/countable.js"></script>
  <!-- <script src="//unpkg.com/docsify"></script> -->
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/docsify.min.js" data-ga="UUA-176075611-1"></script>
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/emoji.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/search.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/docsify-pagination/dist/docsify-pagination.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/zoom-image.min.js"></script>
  <!-- <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/gitalk.min.js"></script> -->
  <script src="//cdn.jsdelivr.net/npm/docsify-pagination/dist/docsify-pagination.min.js"></script>
<!-- 代码块样式优化-->
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-c.js"></script>
<!--<script src="//unpkg.com/prismjs/components/prism-cpp.js"></script>-->
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-cpp.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-css.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-docker.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-java.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-javascript.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-json.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-latex.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-sql.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-markdown.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-bash.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-php.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-scala.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-nginx.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-json.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-markdown.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/prism-python.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/js/prism-yaml.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/StaticRepo/src/js/prism-go.js"></script>
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/StaticRepo/src/js/prism-matlab.js"></script>
<!-- mouse click -->
  <script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/click_heart.js"></script>

  <!-- 添加页脚 -->
  <script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/docsify-footer-enh.min.js"></script>

<!-- 复制提醒 -->
<script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/sweetalert.min.js"></script>
<script>
  document.body.oncopy = function () {
   swal("复制成功",
           "若要转载或引用请务必保留原文链接，并申明来源。如果你觉得本仓库不错，那就来 GitHub 给个 Star 吧 😊   - by TianZD",
           "success"); };
</script>

  <!-- <script src="//cdn.jsdelivr.net/npm/docsify-katex@latest/dist/docsify-katex.js"></script> -->
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/ga.min.js"></script>
  <!-- 复制代码-->
  <script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/docsify-copy-code.min.js"></script>
    <!-- 回到顶部功能 -->
  <script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/jquery.js"></script>
  <script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/jquery.goup.js"></script>
  <script type="text/javascript">
    $(document).ready(function () {
      $.goup({
        trigger: 100,
        bottomOffset: 52,
        locationOffset: 25,
        //title: 'TOP',
        titleAsText: true
      });
    });
  </script>
  <script src="https://cdn.jsdelivr.net/npm/docsify-tabs@1"></script>  

  
  <!-- 支持 DOT 语言 -->
  <script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/viz.js"></script>
  <!-- 支持 LaTex 语言 -->
  <script src="//cdn.jsdelivr.net/npm/docsify-katex@latest/dist/docsify-katex.js"></script>

  <!-- 添加 PDF 页面展示功能 -->
  <!-- PDFObject.js is a required dependency of this plugin -->
  <!--<script src="//cdnjs.cloudflare.com/ajax/libs/pdfobject/2.1.1/pdfobject.min.js"></script>-->
  <script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/pdfobject.min.js"></script>
  <!-- This is the source code of the pdf embed plugin -->
  <script src="https://cdn.jsdelivr.net/gh/wugenqiang/NoteBook@master/plugin/docsify-pdf-embed.js"></script>
  <!-- alert插件 -->
  <script src="https://unpkg.com/docsify-plugin-flexible-alerts"></script>

  <!-- 添加网站运行时间统计 -->
  <script language=javascript>
    function siteTime() {
       window.setTimeout("siteTime()", 1000);
       var seconds = 1000;
       var minutes = seconds * 60;
       var hours = minutes * 60;
       var days = hours * 24;
       var years = days * 365;
       var today = new Date();
       var todayYear = today.getFullYear();
       var todayMonth = today.getMonth() + 1;
       var todayDate = today.getDate();
       var todayHour = today.getHours();
       var todayMinute = today.getMinutes();
       var todaySecond = today.getSeconds();
       /* Date.UTC() -- 返回date对象距世界标准时间(UTC)1970年1月1日午夜之间的毫秒数(时间戳)
       year - 作为date对象的年份，为4位年份值
       month - 0-11之间的整数，做为date对象的月份
       day - 1-31之间的整数，做为date对象的天数
       hours - 0(午夜24点)-23之间的整数，做为date对象的小时数
       minutes - 0-59之间的整数，做为date对象的分钟数
       seconds - 0-59之间的整数，做为date对象的秒数
       microseconds - 0-999之间的整数，做为date对象的毫秒数 */
       var t1 = Date.UTC(2019, 06, 21, 22, 22, 22); //北京时间2019-06-21 22:22:22 //计划考研的日子，6月20日毕业典礼结束后，人生需要继续努力，加油，看到这句话的朋友们！
       var t2 = Date.UTC(todayYear, todayMonth, todayDate, todayHour, todayMinute, todaySecond);
       var diff = t2 - t1;
       var diffYears = Math.floor(diff / years);
       var diffDays = Math.floor((diff / days) - diffYears * 365);
       var diffHours = Math.floor((diff - (diffYears * 365 + diffDays) * days) / hours);
       var diffMinutes = Math.floor((diff - (diffYears * 365 + diffDays) * days - diffHours * hours) / minutes);
       var diffSeconds = Math.floor((diff - (diffYears * 365 + diffDays) * days - diffHours * hours - diffMinutes * minutes) / seconds);
       document.getElementById("sitetime").innerHTML = " 本站已安全运行 " + diffYears + " 年 " + diffDays + " 天 " + diffHours + " 小时 " + diffMinutes + " 分 " + diffSeconds + " 秒 ";
    }
    siteTime();
</script>
<script src="https://eqcn.ajz.miesnfu.com/wp-content/plugins/wp-3d-pony/live2dw/lib/L2Dwidget.min.js"></script>
<script>
  L2Dwidget.init({
    "model": {
      //jsonpath控制显示那个小萝莉模型，
      //(切换模型需要改动)
      //https://unpkg.com/live2d-widget-model-模型名称/assets/模型名称.model.json
      // jsonPath: "https://unpkg.com/live2d-widget-model-koharu@1.0.5/assets/koharu.model.json",
      // jsonPath: "https://unpkg.com/live2d-widget-model-hijiki/assets/hijiki.model.json",
      // jsonPath: "https://unpkg.com/live2d-widget-model-tororo/assets/tororo.model.json",
      jsonPath: "https://unpkg.com/live2d-widget-model-wanko/assets/wanko.model.json",
      "scale": 1
    },
    "display": {
      "position": "right", //看板娘的表现位置
      "width": 70,  //小萝莉的宽度
      "height": 140, //小萝莉的高度
      "hOffset": 35,
      "vOffset": -20
    },
    "mobile": {
      "show": true,
      "scale": 0.5
    },
    "react": {
      "opacityDefault": 0.7,
      "opacityOnHover": 0.2
    }
  });
</script>
</body>

</html>
```

## Nginx部署准备

对于这类网站的部署，我们当然可以部署到`GitHub Pages`服务或者`Gitee Pages`服务上去

也直接将其部署到云服务器上去。

### 安装服务器软件

这里选用Nginx这个Web服务器来驱动网站，因为Nginx服务器除了本身非常轻量，稳定，不耗资源之外，而且性能还好，还特别能扛并发。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345781.png)



- 首先准备一个`nginx-1.17.10.tar.gz`的安装包，我们将其直接上传到云服务器的`root`⽬录下
- 在`/usr/local/`下创建`nginx`⽂件夹并进⼊

```shell
cd /usr/local/
mkdir nginx
cd nginx
```

- 将Nginx安装包解压到`/usr/local/nginx`中即可

```shell
tar zxvf /root/nginx-1.17.10.tar.gz -C ./
```

解压完之后，在`/usr/local/nginx`⽬录中会出现⼀个`nginx-1.17.10`的目录

- 预先安装额外的几个依赖

```shell
yum -y install pcre-devel
yum -y install openssl openssl-devel
```

- 接下来编译安装Nginx即可

```shell
cd nginx-1.17.10
./configure
make && make install
```

安装完成后， Nginx的可执行文件位置位于

```shell
/usr/local/nginx/sbin/nginx
```

其配置⽂件则位于：

```
/usr/local/nginx/conf/nginx.conf
```

- 启动Nginx

直接执⾏如下命令即可

```shell
/usr/local/nginx/sbin/nginx
```

- 如果想停⽌Nginx服务，可执⾏：

```shell
/usr/local/nginx/sbin/nginx -s stop
```

- 如果修改了配置⽂件后想重新加载Nginx，可执⾏：

```shell
/usr/local/nginx/sbin/nginx -s reload
```

### 上传网站到云服务器

比如，我们可以直接在`/usr/local/nginx/`目录中创建一个`www`文件夹，用于存放我们本地的项目文件夹。

然后我们可以直接将本地的项目文件夹`repository`上传到`www`目录里即可

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345234.png)

### Nginx配置和访问

接下来我们打开路径`/usr/local/nginx/conf/nginx.conf`下的Nginx服务器配置文件，修改其中最关键的一个`location /`下的`root`目录配置为项目文件夹的路径即可

```shell
location / {
    root   /usr/local/nginx/www/repository;
    index  index.html index.htm;
}
```

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345404.png)

然后我们直接在浏览器中访问云服务器的公网IP，就可以访问该知识库网站了：

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345961.jpeg)

最后再将域名和这个IP一绑定，就OK了

## nginx服务器添加密码访问

### 通过htpasswd命令生成用户名及对应密码数据库文件

```shell
htpasswd -c /htpasswd/passwd.db test
```

说明：此命令是使用Crypt算法生产一个加密文件passwd.db。

- test是用户名
- /htpasswd/passwd.db 是密码文件存放地址
- htpasswd apache的生产密码的插件

把 passwd.db文件设置为777权限

```shell
chmod 777 文件名
```

### nginx域名配置文件修改

```shell
  location / {
    root   html;
    index  index.html index.htm index index.jpg;
    auth_basic 'Restricted';            # 认证名称，随意填写
    auth_basic_user_file /htpasswd/passwd.db;      # 认证的密码文件，需要生产。
  }
```

如果没有安装apache，可以安装如下软件，就有htpasswd这个命令了
```shell
yum -y install httpd-tools
yum -y install httpd  #安装httpd
which htpasswd  #查看是否安装
rpm -qf /usr/bin/htpasswd  #查看是否安装
htpasswd -cb /usr/local/nginx/conf/htpasswd ceshi 123789  #生成密码文件
chmod 400 /usr/local/nginx/conf/htpasswd  #为了安全设置文件权限
```

然后重启nginx服务器即可实现nginx服务器添加密码

```shell
#3、检查语法并重启
/usr/local/nginx/sbin/nginx -t
/usr/local/nginx/sbin/nginx -s reload
```

## 定时git pull和重启nginx

前提：www中已经设置了git仓库，这里为www下的NoteBooks文件夹

- 拉取最新代码

```shell
git pull
```



- 在NoteBooks下新建自动化脚本

```shell
vim 111gitpull.sh
```

- 输入：

```shell
#!/bin/bash
cd /usr/local/nginx/www/NoteBooks
git pull
/usr/local/nginx/sbin/nginx -s reload
```

- 设置自动启动，这里每30分钟拉取一次

进入root用户

编辑cron脚本

```shell
crontab -e
```

输入：

```shell
*/30 * * * * /usr/local/nginx/www/NoteBooks/111gitpull.sh
```

- 将服务自动启动

```shell
vim /etc/rc.d/rc.local
```

这个脚本的末尾加上：

```shell
/sbin/service crond start
```



## 配置Gittalk

### 第一步：创建OAuth Application

这里假设你的[github](https://so.csdn.net/so/search?q=github&spm=1001.2101.3001.7020)仓库已经创建好，docsify也已经安装使用中。申请授权地址[点击这里](https://github.com/settings/applications/new)
几个参数解释：

- Application name：项目名，随便填
- Homepage URL：博客地址，也就是要访问的地址，我的是https://waldeincheng.github.io/Python-Notes
- Application description：应用描述，随便填，我的是waldeincheng’s blog
- Authorization callback URL:可以指向新的URL,或者跟上面的Homepage URL一样即可

完成后会生成相应的`clientID`和`clientSecret`

### 第二步：配置gittalk

docsify下有一个index.html配置文件，打开，添加上

```
  <link rel="stylesheet" href="//unpkg.com/gitalk/dist/gitalk.css">
	<script src="//unpkg.com/docsify/lib/plugins/gitalk.min.js"></script>
	<script src="//unpkg.com/gitalk/dist/gitalk.min.js"></script>
  <script>
	  const gitalk = new Gitalk({
		clientID: 'bec89b8d6a560d1fc883',
		clientSecret: 'd156a16ac84fee346d852d54c5bbd6b56a36469b',
		repo: 'Python-Notes',
		owner: 'WaldeinCheng',
		admin: ['WaldeinCheng'],
		// facebook-like distraction free mode
		distractionFreeMode: false
	  })
  </script>   
```

配置：

- <link rel="stylesheet" href="//unpkg.com/gitalk/dist/gitalk.css">, css文件放到index.html里的`\<head>`标签里
- `clientID`:刚刚获取的ID
- `clientSecret`:刚刚获取的授权密码
- `repo`:仓库名
- `owner`：github用户名
- `admin`：[‘github用户名’]

保存修改，git提交上去就行了，显示格式如图

## Windows自动启动Docsify

使用 `.bat` 的话，会显示一个短暂的黑窗口所以要想后台静默运行，需要用到 `.vbs` 脚本

### 创建vbs脚本

在docsify目录下新建一个vbs文件，内容如下

```vbscript
set ws=WScript.CreateObject("WScript.Shell")

ws.Run "docsify serve",0
```

该脚本含义为在当前目录下执行 `docsify serve` 命令

### 开启启动

windows 开机会自动调用下列两个文件夹下的可执行文件

用户目录的自启文件夹：

```text
C:\Users\用户名\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup
```

> 注意用户名，每个用户都不相同

所有用户都会执行的全局自启文件夹

```text
C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp
```

在脚本上右键创建快捷方式，然后将这个快捷方式放到上述两个文件夹任意一个即可