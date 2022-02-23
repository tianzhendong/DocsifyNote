# Docsify

[toc]

[codesheep微信文章](https://mp.weixin.qq.com/s?__biz=MzU4ODI1MjA3NQ==&mid=2247510629&idx=1&sn=48e481b9f176ebeb2d68342af3f33317&chksm=fddd7ca1caaaf5b73efe05dcb5b87c570344708e2a7bddffb5c8bd08638b631e595a4d2b646c&mpshare=1&scene=1&srcid=0223V1apWEBHMQrFHzWwPQiN&sharer_sharetime=1645581220908&sharer_shareid=9615bd3fd5ee967a5d2b03b78f23d393#rd)

## 框架对比

其实做这种网站，完全不用自己手写，现成的工具太多了，这地方随便举几个典型例子吧。

### Hexo

这是一个大家用得非常广泛的静态博客，两年前咱们这里就已经演示过它的使用以及部署上线。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232332738.png)

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

ocsify唯一需要的一个前置工具就是`npm`工具，我想这个大家应该都安装了吧。

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
repository/README.md          =>  http://domain.com
repository/test.md            =>  http://domain.com/test
repository/content/README.md  =>  http://domain.com/content/
repository/content/bigdata.md =>  http://domain.com/content/bigdata
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

对我自己来讲，其实这种知识库网站，我不需要花里胡哨的界面/配色，我只要干净/卫生/实用/方便就可以了。

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

在 docsify 之后添加 docsify-tabs 插件`index.html`。

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

使用标题 + 粗体标记定义选项卡集中的选项卡。

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



### 首页html文件



```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Document</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="description" content="Description">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
  <link rel="icon" href="./logo.ico">
  <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify@4/lib/themes/vue.css">
  <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/gitalk/dist/gitalk.css">
  <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/katex@latest/dist/katex.min.css" />
  
</head>
<body>
  <!-- 定义加载时候的动作 -->
  <div id="app"></div>
  <script src="//cdn.jsdelivr.net/npm/docsify-edit-on-github"></script>
  <script src="//cdn.jsdelivr.net/npm/gitalk/dist/gitalk.min.js"></script>
  <script>
      window.$docsify = {
          auto2top: true,
          // 项目名称
          name: 'Tian',
          // 仓库地址，点击右上角的Github章鱼猫头像会跳转到此地址
          repo: 'https://gitee.com/tianzhendong/NoteBooks',
          // 侧边栏支持，默认加载的是项目根目录下的_sidebar.md文件
          loadSidebar: true,
          // 导航栏支持，默认加载的是项目根目录下的_navbar.md文件
          loadNavbar: true,
          // 封面支持，默认加载的是项目根目录下的_coverpage.md文件
          coverpage: true,
          // 最大支持渲染的标题层级
          maxLevel: 5,
          // 自定义侧边栏后默认不会再生成目录，设置生成目录的最大层级（建议配置为2-4）
          subMaxLevel: 4,
          // 小屏设备下合并导航栏到侧边栏
          mergeNavbar: true,
      }

    //   const gitalk = new Gitalk({
    //   clientID: '1cfda275cc20ae8e6bbf',
    //   clientSecret: '2a5da775b123ad08006fa97c34173895a256eca5',
    //   repo: 'Blog',
    //   id: location.pathname,
    //   owner: 'CodecWang',
    //   admin: ['CodecWang'],
    //   distractionFreeMode: false
    // });
  </script>
  <script>
      // 搜索配置(url：https://docsify.js.org/#/zh-cn/plugins?id=%e5%85%a8%e6%96%87%e6%90%9c%e7%b4%a2-search)
      window.$docsify = {
          search: {
              maxAge: 86400000,// 过期时间，单位毫秒，默认一天
              paths: auto,// 注意：仅适用于 paths: 'auto' 模式
              placeholder: '搜索',
              // 支持本地化
              placeholder: {
                  '/zh-cn/': '搜索',
                  '/': 'Type to search'
              },
              noData: '找不到结果',
              depth: 4,
              hideOtherSidebarContent: false,
              namespace: 'Docsify-Guide',
          }
      }

  </script>
  <script>
    // tabs
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
  </script>
  <style>
    :root {
      --docsifytabs-border-color: #f0f3f1;
      --docsifytabs-tab-highlight-color: rgb(17, 155, 58);
    }
  </style>
  <!-- <script>
//字数统计
    window.$docsify = {
      count:{
        countable:true,
        fontsize:'0.9em',
        color:'rgb(90,90,90)',
        language:'chinese'
      }
    }
  </script> -->
  <script src="//unpkg.com/docsify-count/dist/countable.js"></script>
  <!-- docsify的js依赖 -->
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/docsify.min.js"></script>
  <!-- emoji表情支持 -->
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/emoji.min.js"></script>
  <!-- 图片放大缩小支持 -->
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/zoom-image.min.js"></script>
  <!-- 搜索功能支持 -->
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/search.min.js"></script>
  <!--在所有的代码块上添加一个简单的Click to copy按钮来允许用户从你的文档中轻易地复制代码-->
  <script src="//cdn.jsdelivr.net/npm/docsify-copy-code/dist/docsify-copy-code.min.js"></script>
  <!-- 代码高亮 -->
  <script src="//cdn.jsdelivr.net/npm/prismjs@1/components/prism-python.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/prismjs@1/components/prism-c.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/prismjs@1/components/prism-bash.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/prismjs@1/components/prism-java.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/prismjs@1/components/prism-properties.min.js"></script>

  
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/gitalk.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/docsify-pagination/dist/docsify-pagination.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/docsify-katex@latest/dist/docsify-katex.js"></script>
  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/ga.min.js"></script>
  <script src="//cdn.jsdelivr.net/npm/docsify-copy-code"></script>

  <!-- docsify (latest v4.x.x)-->
<!-- <script src="https://cdn.jsdelivr.net/npm/docsify@4"></script> -->

<!-- docsify-tabs (latest v1.x.x) -->
<script src="https://cdn.jsdelivr.net/npm/docsify-tabs@1"></script>

  
</body>
</html>
```

## 部署准备

对于这类网站的部署，我们当然可以部署到`GitHub Pages`服务或者`Gitee Pages`服务上去，这个其实在2019年初聊博客搭建的视频里，就已经演示过了。

而这一次呢，我们直接将其部署到云服务器上去。

这里我们准备一台最低配的丐版云服务器即可：

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345342.jpeg)

比如这篇文章用到的就是一个1核1G的低配云服务器。

### 安装服务器软件

这里我们就选用Nginx这个Web服务器来驱动网站，因为Nginx服务器除了本身非常轻量，稳定，不耗资源之外，而且性能还好，还特别能扛并发。

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345781.png)

关于Nginx服务器的部署安装其实我在之前《服务器编程环境和软件设施部署》那一篇文章里就已经聊过了，当时我还写了一个《PDF版本的安装部署手册》：

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345579.png)

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345983.png)

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345238.png)



所以我们对照着这个手册操作一下即可，非常简单。

- 首先准备一个`nginx-1.17.10.tar.gz`的安装包，我们将其直接上传到云服务器的`root`⽬录下
- 在`/usr/local/`下创建`nginx`⽂件夹并进⼊

```
cd /usr/local/
mkdir nginx
cd nginx
```

- 将Nginx安装包解压到`/usr/local/nginx`中即可

```
[root@localhost nginx]# tar zxvf /root/nginx-1.17.10.tar.gz -C ./
```

解压完之后，在`/usr/local/nginx`⽬录中会出现⼀个`nginx-1.17.10`的目录

- 预先安装额外的几个依赖

```
yum -y install pcre-devel
yum -y install openssl openssl-devel
```

- 接下来编译安装Nginx即可

```
cd nginx-1.17.10
./configure
make && make install
```

安装完成后， Nginx的可执⾏⽂件位置位于

```
/usr/local/nginx/sbin/nginx
```

其配置⽂件则位于：

```
/usr/local/nginx/conf/nginx.conf
```

- 启动Nginx

直接执⾏如下命令即可

```
[root@localhost sbin]# /usr/local/nginx/sbin/nginx
```

- 如果想停⽌Nginx服务，可执⾏：

```
/usr/local/nginx/sbin/nginx -s stop
```

- 如果修改了配置⽂件后想重新加载Nginx，可执⾏：

```
/usr/local/nginx/sbin/nginx -s reload
```

### 上传网站到云服务器

比如，我们可以直接在`/usr/local/nginx/`目录中创建一个`www`文件夹，用于存放我们本地的项目文件夹。

然后我们可以直接将本地的项目文件夹`repository`上传到`www`目录里即可

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345234.png)

### Nginx配置和访问

接下来我们打开路径`/usr/local/nginx/conf/nginx.conf`下的Nginx服务器配置文件，修改其中最关键的一个`location /`下的`root`目录配置为项目文件夹的路径即可

```
location / {
    root   /usr/local/nginx/www/repository;
    index  index.html index.htm;
}
```

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345404.png)

然后我们直接在浏览器中访问云服务器的公网IP，就可以访问该知识库网站了：

![图片](https://gitee.com/tianzhendong/img/raw/master//images/202202232345961.jpeg)

最后再将域名和这个IP一绑定，就OK了，所以整个过程就是这么简单。

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