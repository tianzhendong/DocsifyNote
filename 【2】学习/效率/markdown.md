[toc]

# Markdown

## Markdown索引

### 索引文件夹

[效率](【2】学习/效率)

### 索引文件

[markdown](【2】学习\效率\markdown.md)

### 图片超链接

````html
<p align="left">
    <a href="https://mp.weixin.qq.com/s/ePhaYezFblgt0NgbvtWqww" target="_blank">
        <img src="https://cdn.jsdelivr.net/gh/justacoder99/r2coding@master/img/localsoftware.2x9vakmja2g.png" width=""/>
    </a>
</p>
```
````

<p align="left">
    <a href="https://mp.weixin.qq.com/s/ePhaYezFblgt0NgbvtWqww" target="_blank">
        <img src="https://cdn.jsdelivr.net/gh/justacoder99/r2coding@master/img/localsoftware.2x9vakmja2g.png" width=""/>
    </a>
</p>

## Markdown插入图片

```markdown
![图片描述关键词](图片链接地址)
```

**[PicGo + Gitee(码云)实现免费markdown图床](https://link.zhihu.com/?target=https%3A//www.jianshu.com/p/b69950a49ae2)**

## Markdown插入音频

### 使用audio 标签

```markdown
<audio id="audio" controls="" preload="none">
      <source id="mp3" src="音频地址">
</audio>
```

<audio id="audio" controls="" preload="none">
      <source id="mp3" src="https://i.y.qq.com/v8/playsong.html?songid=332928560&songtype=0#webchat_redirect">
</audio>



### 使用iframe标签

我们以网易云音乐为例 1.首先在网易云音乐播放界面，点击生成外链播放器

![image-20220211111954460](https://gitee.com/tianzhendong/img/raw/master//images/202202111119569.png)



![image-20220211112012621](https://gitee.com/tianzhendong/img/raw/master//images/202202111120804.png)

<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=660 height=86 src="//music.163.com/outchain/player?type=2&id=1362124362&auto=1&height=66" ></iframe>

<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=330 height=86 src="//music.163.com/outchain/player?type=2&id=1858139145&auto=1&height=66"></iframe>

## Markdown插入视频

### html中的iframe标签

```html
<iframe 
src="视频或者网页路径" 
scrolling="no" 
border="0" 
frameborder="no" 
framespacing="0" 
allowfullscreen="true" 
height=600 
width=800> 
</iframe>
<!-- 相当于是子网页 -->
<!-- B站分享链接提供 -->
```

<iframe src="//player.bilibili.com/player.html?aid=87731293&bvid=BV1E7411b7w4&cid=149892227&page=1&as_wide=1&high_quality=1&danmaku=1" allowfullscreen="true" width="100%" height="450" scrolling="no" frameborder="0" sandbox="allow-top-navigation allow-same-origin allow-forms allow-scripts"></iframe>

### html中的video标签

```html
<!-- mp4格式 -->
<video id="video" controls="" preload="none" poster="封面">
      <source id="mp4" src="mp4格式视频" type="video/mp4">
</videos>

<!-- webm格式 -->
<video id="video" controls="" preload="none" poster="封面">
      <source id="webm" src="webm格式视频" type="video/webm">
</videos>

<!-- ovg格式 -->
<video id="video" controls="" preload="none" poster="封面">
      <source id="ogv" src="ogv格式视频" type="video/ogv">
</videos>
```



## 如何优雅地在Markdown中输入数学公式

[来自CSDN文章](https://blog.csdn.net/m0_37167788/article/details/78809779?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.pc_relevant_default&utm_relevant_index=2)

### 基础部分

#### 公式标记

在Markdown中，有两种输入公式的方法：一是行内公式（inline），用一对美元符号`$`包裹。二是整行公式（displayed），用一对紧挨的两个美元符号`$$`包裹。

这是一个行内公式$E=mc^2$，写法是：`$E=mc^2$`。

这是一个整行公式：

写法是：
$$
\sum_{i=0}^n i^2 = \frac{(n^2+n)(2n+1)}{6}
$$

```
$$
\sum_{i=0}^n i^2 = \frac{(n^2+n)(2n+1)}{6}
$$   
```

#### 希腊字母

| 名称    | 大写       | Tex      | 小写       | Tex      |
| ------- | ---------- | -------- | ---------- | -------- |
| alpha   | $A$        | A        | $\alpha$   | \alpha   |
| beta    | $B$        | B        | $beat$     | \beat    |
| gamma   | $\Gamma$   | \Gamma   | $\gamma$   | \gamma   |
| delta   | $\Delta$   | \Delta   | $\delta$   | \delta   |
| epsilon | $E$        | E        | $\epsilon$ | \epsilon |
| zeta    | $Z$        | Z        | $\zeta$    | \zeta    |
| eta     | $H$        | H        | $\eta$     | \eta     |
| theta   | $\Theta$   | \Theta   | $\theta$   | \theta   |
| iota    | $I$        | I        | $\iota$    | \iota    |
| kappa   | $K$        | K        | $\kappa$   | \kappa   |
| lambda  | $\Lambda$  | \Lambda  | $\lambda$  | \lambda  |
| mu      | $M$        | M        | $\mu$      | \mu      |
| nu      | $N$        | N        | $\nu$      | \nu      |
| xi      | $\Xi$      | \Xi      | $\xi$      | \xi      |
| omicron | $O$        | O        | $\omicron$ | \omicron |
| pi      | $\Pi$      | \Pi      | $\pi$      | \pi      |
| rho     | $P$        | P        | $\rho$     | \rho     |
| sigma   | $\Sigma$   | \Sigma   | $\sigma$   | \sigma   |
| tau     | $T$        | T        | $\tau$     | \tau     |
| upsilon | $\Upsilon$ | \Upsilon | $\upsilon$ | \upsilon |
| phi     | $\Phi$     | \Phi     | $\phi$     | \phi     |
| chi     | $X$        | X        | $\chi$     | \chi     |
| psi     | $\Psi$     | \Psi     | $\psi$     | \psi     |
| omega   | $\Omega$   | \Omega   | $\omega$   | \omega   |

#### 上标与下标

上标和下标分别使用`^`和`_`来表示。例如`x_i^2`：$x_i^2$，`\log_2 x`：$\log_2 x$。

默认情况下，**上下标符号仅仅对下一个组起作用**。一个组即单个字符或者使用`{}`包裹起来的内容。也就是说，如果使用`10^10`会得到$10^10$，而`10^{10}`才是$10^{10}$。同时，大括号还能消除二义性，如`x^5^6`会显示错误，必须使用大括号来界定`^`的结合性，如`{x^5}^6`${x^5}^6$或者`x^{5^6}`：$x^{5^6}$。注意区分`x_i^2`：$x_i^2$和`x_{i_2}`：$x_{i_2}$。

另外，如果要在左右两边都有上下标，可以用`\sideset`来表示，如`\sideset{^1_2}{^3_4}\bigotimes`：$\sideset{^1_2}{^3_4}\bigotimes$。

#### 括号

- **小括号与方括号：**使用原始的`()`和`[]`即可。如`(2+3)[4+4]`：$(2+3)[4+4]$。
- **大括号：**由于大括号`{}`被用来分组，因此需要使用`\{`和`\}`表示大括号，也可以使用`\lbrace`和`\rbrace`来表示。如`\{a*b\}`或者`\lbrace a*b \rbrace`，都会显示为$\lbrace a*b \rbrace$。
- **尖括号：**使用`\langle`和`\rangle`分别表示左尖括号和右尖括号。如`\langle x \rangle`：$\langle x \rangle$。
- **上取整：**使用`\lceil`和`\rceil`表示。如`\lceil x \rceil`：$\lceil x \rceil$。
- **下取整：**使用`\lfloor`和`\rfloor`表示。如`\lfloor x \rfloor`：$\lfloor x \rfloor$。

需要注意的是，原始括号并不会随着公式大小缩放。如`(\frac12)`：。可以使用`\left( ...\right)`来自适应的调整括号。如`\left( \frac12 \right)`：$\left( \frac12 \right)$。可以明显看出，后一组公式中的括号是经过缩放的。

#### 求和与积分

`\sum`用来表示求和符号，其下标表示求和下限，上标表示上线。如`\sum_1^n`：$\sum_1^n$。

`\int`用来表示积分符号，同样地，其上下标表示积分的上下限。如`\int_1^\infty`：$\int_1^\infty$。

与此类似的符号还有，`\prod`：$\prod$，`\bigcup`：$\bigcup$，`\bigcap`：$\bigcap$，`\iint`：$\iint$。

#### 分式与根式

分式有两种表示方法。第一种，使用`\frac ab`，其中`\frac`作用于气候的两个组a和b，结果为$\frac ab$。如果分子或分母不是单个字符，需要使用`{}`来分组。第二种，使用`\over`来分隔一个组的前后两部分，如`{a+1\over b+1}`：${a+1\over b+1}$。

根式使用`\sqrt[a]b`来表示。其中，方括号内的值用来表示开几次方，省略方括号则表示开方，如`\sqrt[4]{\frac xy}`：$\sqrt[4]{\frac xy}$，`\sqrt{x^3}`：$\sqrt{x^3}$。

####  字体

- 使用`\it`显示意大利体（公式默认字体）：$ACDEFGHIJKLMnopqrstuvwxyz$。
- 使用`\mathbb`或`\Bbb`显示黑板粗体（黑板黑体），如`\mathbb{CHNQRZ}`：$\mathbb{CHNQRZ}$。
- 使用`\mathbf`或`\bf`示黑体：$\bf ABCDEFGHIJKLMnopqrstuvwxyzABCDEFGHIJKLMnopqrstuvwxyz$。
- 使用`\mathtt`或`\tt`显示打印机字体：$\tt{ABCDEFGHIJKLMnopqrstuvwxyz}$

### MarkDown符号大全

![这里写图片描述](https://gitee.com/tianzhendong/img/raw/master//images/20171219090613513)

![img](https://gitee.com/tianzhendong/img/raw/master//images/20171219090626403)

![img](https://gitee.com/tianzhendong/img/raw/master//images/20171219090708939)

![这里写图片描述](https://gitee.com/tianzhendong/img/raw/master//images/20171219090742617)

![这里写图片描述](https://gitee.com/tianzhendong/img/raw/master//images/20171219090751456)

