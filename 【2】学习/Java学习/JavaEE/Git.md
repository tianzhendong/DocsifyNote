---
title: GitStudy
tags: git
notebook: git
---

[toc]

# Git学习
# 1、版本控制
## 版本控制工具

* Git
* SVN(Subversion)
* CVS(Concurrent Version System)
* VSS(Micorosoft Visual SourceSafe)
* TFS(Team Foundation Server)
* Visual Studio Online

Git是目前世界上最先进的分布式**版本控制**系统

## 分类

### 本地版本控制

记录文件每次的更新，可以对每一个版本做一个快照，或是记录补丁文件，适合个人使用，如RCS

![image-20210804192335392](https://gitee.com/tianzhendong/img/raw/master//images/image-20210804192335392.png)

### 集中版本控制

代表是SVN 

版本库是集中存放在**中央服务器**的，而干活的时候，用的都是自己的电脑，所以要先从中央服务器取得最新的版本，然后开始干活，干完活了，再把自己的活推送给中央服务器。中央服务器就好比是一个图书馆，你要改一本书，必须先从图书馆借出来，然后回到家自己改，改完了，再放回图书馆。**集中式版本控制系统最大的毛病就是必须联网才能工作**

![image-20210804192519737](https://gitee.com/tianzhendong/img/raw/master//images/image-20210804192519737.png)

### 分布式版本控制

代表GIT

每个人拥有全部代码，有安全隐患

分布式版本控制系统根本没有“中央服务器”，每个人的电脑上都是一个完整的版本库，这样，你工作的时候，就不需要联网了，因为版本库就在你自己的电脑上。既然每个人电脑上都有一个完整的版本库，那多个人如何协作呢？比方说你在自己电脑上改了文件A，你的同事也在他的电脑上改了文件A，这时，你们俩之间只需把各自的修改推送给对方，就可以互相看到对方的修改了。**集中式版本控制系统相比，分布式版本控制系统的安全性要高很多，因为每个人电脑里都有完整的版本库，某一个人的电脑坏掉了不要紧，随便从其他人那里复制一个就可以了。而集中式版本控制系统的中央服务器要是出了问题，所有人都没法干活了。**

![image-20210804192644016](https://gitee.com/tianzhendong/img/raw/master//images/image-20210804192644016.png)



# 2、Git安装
## Linux安装Git
首先，你可以试着输入git，看看系统有没有安装Git：
```
$ git
The program 'git' is currently not installed. You can install it by typing:
sudo apt-get install git
```

## Macos安装Git
直接从AppStore安装Xcode，Xcode集成了Git，不过默认没有安装，你需要运行Xcode，选择菜单“Xcode”->“Preferences”，在弹出窗口中找到“Downloads”，选择“Command Line Tools”，点“Install”就可以完成安装了。
## 在Windows上安装Git
在Windows上使用Git，可以从Git官网直接下载安装程序，然后按默认选项安装即可。
安装完成后，在开始菜单里找到“Git”->“Git Bash”，蹦出一个类似命令行窗口的东西，就说明Git安装成功。

## 启动Git

**GIt Bash：**Unix与Linux风格的命令行，使用最多，推荐对坐

**Git CMD：**Windows风格的命令行

**Git GUI：**图形界面的Git，不建议使用

# 3、基本linux命令

* cd：改变目录
* cd .. ：回到上一个目录
* pwd：查看当前目录
* clear：清屏
* ls（ll）：列出 当前目录所有文件
* touch：新建一个文件
* rm：删除一个文件
* mkdir：新建一个目录
* rm -r：删除一个文件夹
  * rm -rf /：递归删除所有文件
* mv：移动文件mv a.html src 把a.html移动到src文件夹下
* reset：重新初始化终端/清平
* history：查看命令历史
* help：帮助
* exit：推出
* #：注释

# 4、Git配置

所有的配置文件都保存在本地

## 查看配置

```bash
git config -l	#查看配置
git config --system -l	#查看系统配置
git config --global -l	#查看（当前用户）全局配置
```

## 配置用户名和密码

```bash
#配置用户名、邮箱
git config --global user.name "tianzhendong"
git config --global user.email 1203886034@qq.com
```

# 5、Git原理

## 工作区域

* git本地有三个工作区域：
  * 工作目录（working directory）：工作区域，平时存放代码的地方
  * 暂存区（stage/index）：用于临时存放你的改动，事实上只是一个文件，保存即将提交到文件列表信息
  * 资源库（repository或者git directory）：本地仓库，安全存放数据的位置，里面有你提交的所有版本的数据，其中HEAD指向最新放入仓库的版本

* 远程的git仓库（Remote directory）：托管代码的服务器

![image-20210804204342702](https://gitee.com/tianzhendong/img/raw/master//images/image-20210804204342702.png)





![image-20210804205024206](https://gitee.com/tianzhendong/img/raw/master//images/image-20210804205024206.png)



## 工作流程

1. 在工作目录中添加、修改文件
2. 将需要进行版本管理的文件放入暂存区
3. 将暂存区的文件提交到git仓库
4. 提交到远程

git管理的文件三种状态：已修改（modified）、已暂存（staged）、已提交（committed）

![image-20210804205250289](https://gitee.com/tianzhendong/img/raw/master//images/image-20210804205250289.png)

# 6、Git使用

![image-20210804205421101](https://gitee.com/tianzhendong/img/raw/master//images/image-20210804205421101.png)

## 创建本地仓库

```bash
#方法1：创建全新的仓库
git init	#初始化本地库
#方法2：克隆远程仓库到本地
git clone [url]	#克隆远程仓库到本地
```

## Git文件操作

文件四种状态：

* **Untracked：**未跟踪，文件在文件夹中，但是没有加入到git库
  * 通过**git add .**状态变为**staged**
* **Unmodify：**已入库，未修改
  * 如果被修改，变为**Modified**
  * 如果使用**git rm**移出版本库则成为**Untracked**
* **Modified：**已修改
  * **git add**：进入暂存**staged**状态
  * **git checkout**：丢弃修改，返回到**unmodify**状态，git checkout即从库中取出文件，覆盖当前修改
* **Staged：**暂存状态
  * **git commit**：修改同步到库中，随后文件变成**unmodify**状态
  * **git reset HEAD filename**：取消暂存，变为**Modified**

### 基本操作

```bash
#查看状态
git status
#查看指定文件状态
git status [filename]
#添加到暂存区
git add .
#提交暂存区内容到本地仓库
git commit -m "注释内容"	#-m表示提交信息
```

### 忽略文件

有时候不需要把某些文件纳入版本控制中，比如数据库文件、临时文件、设计文件等

在主目录下简历".gitignore"文件，此文件有如下规则：

1. 文件中的空行或以#开头的行将会被忽略
2. 可以是用linux通配符，例如：*表示任意多个字符，？表示一个字符，[]表示可选字符范围，{}代表可选的字符串集
3. 如果名称的最前面有一个！，表示例外规则，将不会被忽略
4. 如果名称前面有一个/，表示要忽略的文件在此目录下，子目录下的文件不忽略
5. 如果名称的最后面有一个/，表示要忽略的是此目录下的所有文件

```bash
#为注释
*.txt	#忽略所有.txt结尾的文件
！lib.txt	#lib.txt除外
/temp	#仅忽略项目根目录下的TODO文件，不包括其他目录temp
build/	#忽略build/目录下的所有文件
doc/*.txt	#忽略doc/notes.txt,但不包括doc/server/arch.txt
```

## 使用码云Gitee

### 设置本机绑定SSH公钥实现免密登陆

```bash
# C:\users\Administrator目录
#生成公钥
ssh-keygen
```

把在.ssh目录下的id_rsa.pub文件中的内容复制到gitee公钥设置中即可

## IDEA中集成Git

1. 新建项目，绑定git
   1. 将远程的git文件目录拷贝到项目中即可
2. 修改文件，使用git

# 7、Git分支

* **master**：主分支
* **dev**：开发用

master主分支应该非常稳定，用来发布新版本，一般情况下不允许在上面工作，工作一般情况下在新建的dev分支上工作，工作完后，比如要分布，或者说dev分支代码稳定后可以合并到主分支master上来

三种分支合并情况可以见该链接：https://blog.csdn.net/qq_42780289/article/details/97945300

```bash
# 列出所有本地分支
git branch
# 列出所有远程分支
git branch -r
# 新建一个分支，但是并未切换
git branch [branch_name]
# 新建一个分支，并切换至该分支
git checkout -b [branch]
# 合并指定分支到当前分支
git merge [branch]
# 删除分支
git branch -d [branch]
# 删除远程分支
git push origin --delete [branch]
```

# 8、部分指令

## 指令

```bash
git init  //初始化本地库   
git add readme.txt //将文件添加到仓库
git commit -m "first commit" //把文件提交到仓库
git status //查看仓库当前状态
git diff readme.txt //查看该文件的不同
git log // 查看每次更改内容
git reset --hard HEAD^ //回退到上一个版本，HEAD表示当前版本，HEAD^上一个版本，几个^号 表示上几个版本；
rm readme.txt //删除文件
git rm readme.txt//从库中删除文件
git commit -m "remove the readme.txt"
git remote add origin git@github.com:michaelliao/learngit.git //关联远程库
git push -u origin master //将本地库的内容推送到远程
git remote -v //查看远程库信息
git remote rm origin //接触本地与远程的绑定关系
git clone git@github.com:michaelliao/gitskills.git //从远程库克隆
```
## 合并其他分支代码至master分支
下面以dev分支为例来讲解。
1. 当前分支所有代码提交
先将dev分支上所有有代码提交至git上，提交的命令一般就是这几个，先复习下：
```bash
# 将所有代码提交
git add .
# 编写提交备注
git commit -m "修改bug"
# 提交代码至远程分支
git push origin dev
```
2. 切换当前分支至主干（master）
```bash
# 切换分支
git checkout master 

# 如果多人开发建议执行如下命令,拉取最新的代码
git pull origin master
```
3. 合并（merge)分支代码
```bash
git merge dev
# merge完成后可执行如下命令，查看是否有冲突
git status
```
4. 提交代码至主干（master）
```bash
git push origin master
```
5. 最后切换回原开发分支

```bash
git checkout dev
```

## 删除分支
```bash
// delete branch locally
git branch -d localBranchName

// delete branch remotely
git push origin --delete remoteBranchName
```

## 重命名文件

第一种方法：使用mv命令

``mv readme README.md``

这个时候，如果使用git status查看工作区的状态，Git会提示，readme文件被删除,README.md文件未被跟踪。git add进行提交到暂存区的时候，需要把这个两个文件一起提交，即：

``git add readme README.md``

第二中方法：直接使用Git的 git mv命令。
``
git mv readme README.md``

此时，我们不需要再使用git add 命令把两个文件一起提交，直接使用git commit即可。
也就是说，git mv命令比linux的mv命令，省去了git add提交文件到暂存区这个步骤。



## 【Git】pull遇到错误：error: Your local changes to the following files would be overwritten by merge:

首先取决于你是否想要保存本地修改。（是 /否）

### 是

别急我们有如下三部曲

```bash
git stash  
git pull origin master  
git stash pop  
```


- git stash的时候会把你本地快照，然后git pull 就不会阻止你了，pull完之后这时你的代码并没有保留你的修改。惊了！ 别急，我们之前好像做了什么？

STASH
这时候执行git stash pop你去本地看会发现发生冲突的本地修改还在，这时候你该commit push啥的就悉听尊便了。

### 否

既然不想保留本地的修改，那好办。直接将本地的状态恢复到上一个commit id 。然后用远程的代码直接覆盖本地就好了。

```bash
git reset --hard 
git pull origin master
```



------------------------------------------------
版权声明：本文为CSDN博主「转身雪人」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/nakiri_arisu/article/details/80259531



# Git 标签

标签用于标记某一提交点，唯一绑定一个固定的commitId，相当于为这次提交记录指定一个别名，方便提取文件。
可以为重要的版本打上标签，标签可以是一个对象，也可以是一个简单的指针，但是指针不会移动。

## 创建标签

`git tag <tag_name>`   #为当前分支指向的commit记录创建标签

 `git tag <tag_name> <hash_val>`   #为指定的commitId创建标签

 `git tag -a <tag_name> -m "msg" <hash_val>`   #创建标签同时添加说明信息

## 查看标签

`git tag`   #查看所有标签名称

 `git show <tag_name>`   #查看标签的详细信息(包含commit的信息)

 `git tag -ln [tag_name]`  #显示标签名及其描述信息

## 远程推送标签

`git push <remote_name> <tag_name>`  #将标签推送到远程服务器

 `git push <remote_name> --tags`  #将本地的全部tag推送到远程服务器

## 删除标签

`git tag -d <tag_name>` #删除本地的标签

`git push <remote_name> :refs/tags/<tag_name>` #删除远程标签

## 标签内容提取

```
git archive --format=zip --output=src/xxx.zip <tag_name>` #提取为zip格式，src可以是相对路径，也可以是绝对路径
 **示例：**在d盘下生成包含0.8标签内容的压缩包
 `git archive --format=zip --output=d:/v0.8.zip v0.8
```

## 切换标签

如果我们不想直接提取出标签的代码，而是希望在指定标签下继续进行开发，此时可以切换到标签。

`git checkout <tag_name>` #切换到指定标签

**示例：**切换到v0.8标签进行开发，此时提示我们处于`detached HEAD state`(分离头指针状态)，即说明HEAD指针没有指向具体的分支，查看HEAD指针它直接指向了一个commit对象，此时进行开发操作没有任何意义。

如果想要退出`detached HEAD state`，很简单只需要切换回指定分支就可以了，如`git checkout master`

 如果想要在当前tag下继续开发，可以新建一个分支并让HEAD指向分支就可以了。



