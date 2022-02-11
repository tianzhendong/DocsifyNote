# 【Linux教程】Ubuntu Linux 更换源教程

[blog.csdn.net](https://blog.csdn.net/weixin_43876206/article/details/100924378)

> 因为Ubuntu自带的源已经老旧，下载速度慢，而且不稳定-
> 本教程告诉大家如何更换Ubuntu源到国内几个比较好的源

更换源步骤如下：

1.  备份源列表

    `sudo cp /etc/apt/sources.list /etc/apt/sources.list.bak`
    
    
    ​    

2.  命令行打开sources.list文件

    `sudo gedit /etc/apt/sources.list`
    
    
    ​    

3. 修改sources,list文件【本例更改为阿里镜像源】

   #  阿里镜像源

   ```bash
   deb http://mirrors.aliyun.com/ubuntu/ bionic main restricted universe multiverse
   deb-src http://mirrors.aliyun.com/ubuntu/ bionic main restricted universe multiverse
   
   deb http://mirrors.aliyun.com/ubuntu/ bionic-security main restricted universe multiverse
   deb-src http://mirrors.aliyun.com/ubuntu/ bionic-security main restricted universe multiverse
   
   deb http://mirrors.aliyun.com/ubuntu/ bionic-updates main restricted universe multiverse
   deb-src http://mirrors.aliyun.com/ubuntu/ bionic-updates main restricted universe multiverse
   
   deb http://mirrors.aliyun.com/ubuntu/ bionic-proposed main restricted universe multiverse
   deb-src http://mirrors.aliyun.com/ubuntu/ bionic-proposed main restricted universe multiverse
   
   deb http://mirrors.aliyun.com/ubuntu/ bionic-backports main restricted universe multiverse
   deb-src http://mirrors.aliyun.com/ubuntu/ bionic-backports main restricted universe multiverse
   ```


   ​    

4.  更新并升级

    `sudo apt-get update && sudo apt-get upgrade`
    
    
    ​    

5.  等待更新并升级完成即可

【注意】更新过程中可能会有询问是否下载包的提示，输入y，按回车即可。

【注意！注意！注意】更新时一定要把Linux的网络连接检查是否已经连接上网络，不然会出错，我之前试一直不成功，困了好久，才发现是虚拟机的网络没有连接。。。

更新好后就可以用命令行进行安装软件或者插件了

> 如何用命令行安装gcc 和 g++ 参考文章：-
> [Linux下安装gcc 和 g++运行C程序](https://blog.csdn.net/weixin_43876206/article/details/100923785)

最后再分享几个国内比较好的镜像源：

    #  中科大镜像源
    deb https://mirrors.ustc.edu.cn/ubuntu/ bionic main restricted universe multiverse
    deb-src https://mirrors.ustc.edu.cn/ubuntu/ bionic main restricted universe multiverse
    deb https://mirrors.ustc.edu.cn/ubuntu/ bionic-updates main restricted universe multiverse
    deb-src https://mirrors.ustc.edu.cn/ubuntu/ bionic-updates main restricted universe multiverse
    deb https://mirrors.ustc.edu.cn/ubuntu/ bionic-backports main restricted universe multiverse
    deb-src https://mirrors.ustc.edu.cn/ubuntu/ bionic-backports main restricted universe multiverse
    deb https://mirrors.ustc.edu.cn/ubuntu/ bionic-security main restricted universe multiverse
    deb-src https://mirrors.ustc.edu.cn/ubuntu/ bionic-security main restricted universe multiverse
    deb https://mirrors.ustc.edu.cn/ubuntu/ bionic-proposed main restricted universe multiverse
    deb-src https://mirrors.ustc.edu.cn/ubuntu/ bionic-proposed main restricted universe multiverse


​                

    # 清华镜像源
    deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic main restricted universe multiverse
    deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic main restricted universe multiverse
    deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic-updates main restricted universe multiverse
    deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic-updates main restricted universe multiverse
    deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic-backports main restricted universe multiverse
    deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic-backports main restricted universe multiverse
    deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic-security main restricted universe multiverse
    deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic-security main restricted universe multiverse
    deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic-proposed main restricted universe multiverse
    deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ bionic-proposed main restricted universe multiverse


​        
​        

    # 163镜像源
    deb http://mirrors.163.com/ubuntu/ bionic main restricted universe multiverse
    deb http://mirrors.163.com/ubuntu/ bionic-security main restricted universe multiverse
    deb http://mirrors.163.com/ubuntu/ bionic-updates main restricted universe multiverse
    deb http://mirrors.163.com/ubuntu/ bionic-proposed main restricted universe multiverse
    deb http://mirrors.163.com/ubuntu/ bionic-backports main restricted universe multiverse
    deb-src http://mirrors.163.com/ubuntu/ bionic main restricted universe multiverse
    deb-src http://mirrors.163.com/ubuntu/ bionic-security main restricted universe multiverse
    deb-src http://mirrors.163.com/ubuntu/ bionic-updates main restricted universe multiverse
    deb-src http://mirrors.163.com/ubuntu/ bionic-proposed main restricted universe multiverse
    deb-src http://mirrors.163.com/ubuntu/ bionic-backports main restricted universe multiverse


​       

[查看原网页: blog.csdn.net](https://blog.csdn.net/weixin_43876206/article/details/100924378)