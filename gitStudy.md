---
title: GitStudy
tags: git
notebook: git
---

[toc]

# Git学习
## 简介
Git是目前世界上最先进的分布式版本控制系统
> 集中式和分布式：
> 1. 集中式版本控制系统，版本库是集中存放在**中央服务器**的，而干活的时候，用的都是自己的电脑，所以要先从中央服务器取得最新的版本，然后开始干活，干完活了，再把自己的活推送给中央服务器。中央服务器就好比是一个图书馆，你要改一本书，必须先从图书馆借出来，然后回到家自己改，改完了，再放回图书馆。**集中式版本控制系统最大的毛病就是必须联网才能工作**
> 2. 分布式版本控制系统根本没有“中央服务器”，每个人的电脑上都是一个完整的版本库，这样，你工作的时候，就不需要联网了，因为版本库就在你自己的电脑上。既然每个人电脑上都有一个完整的版本库，那多个人如何协作呢？比方说你在自己电脑上改了文件A，你的同事也在他的电脑上改了文件A，这时，你们俩之间只需把各自的修改推送给对方，就可以互相看到对方的修改了。**集中式版本控制系统相比，分布式版本控制系统的安全性要高很多，因为每个人电脑里都有完整的版本库，某一个人的电脑坏掉了不要紧，随便从其他人那里复制一个就可以了。而集中式版本控制系统的中央服务器要是出了问题，所有人都没法干活了。**

## Git安装
### Linux安装Git
首先，你可以试着输入git，看看系统有没有安装Git：
```
$ git
The program 'git' is currently not installed. You can install it by typing:
sudo apt-get install git
```

### Macos安装Git
直接从AppStore安装Xcode，Xcode集成了Git，不过默认没有安装，你需要运行Xcode，选择菜单“Xcode”->“Preferences”，在弹出窗口中找到“Downloads”，选择“Command Line Tools”，点“Install”就可以完成安装了。
### 在Windows上安装Git
在Windows上使用Git，可以从Git官网直接下载安装程序，然后按默认选项安装即可。
安装完成后，在开始菜单里找到“Git”->“Git Bash”，蹦出一个类似命令行窗口的东西，就说明Git安装成功。

## 指令

```
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
```
# 将所有代码提交
git add .
# 编写提交备注
git commit -m "修改bug"
# 提交代码至远程分支
git push origin dev
```
2. 切换当前分支至主干（master）
```
# 切换分支
git checkout master 

# 如果多人开发建议执行如下命令,拉取最新的代码
git pull origin master
```
3. 合并（merge)分支代码
```
git merge dev
# merge完成后可执行如下命令，查看是否有冲突
git status
```
4. 提交代码至主干（master）
```
git push origin master
```
5. 最后切换回原开发分支

```
git checkout dev
```

## 删除分支
```
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