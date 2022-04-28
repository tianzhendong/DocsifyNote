# SSH免密连接(转载)

[toc]

转自[VS code 连接远程服务器 免密登录设置](https://blog.csdn.net/weixin_38665694/article/details/115692316)

## 客户端（连接端）生成密钥

一般是windows

打开命令行

输入：

```bash
ssh-keygen -t rsa -b 4096
```

连续点击三次回车，生成密钥（如果已经生成，跳过）

![在这里插入图片描述](https://gitee.com/tianzhendong/img/raw/master/images/202204191700953.png)

2、进入C:\User\用户名/.ssh此处为C:\User\20202/.ssh目录可以看到生成了id_rsa和id_rsa.pub两个文件

![image-20220419170136752](https://gitee.com/tianzhendong/img/raw/master/images/202204191701807.png)

3、用记事本打开id_rsa.pub，并复制里面的内容

## 服务器被连接端

一般为linux

1、通过命令编辑authorized_keys文件。

```sh
vim ~/.ssh/authorized_keys 
```

2、在win10系统中，用记事本打开id_rsa.pub，并复制里面的内容到authorized_keys文件中。

3、切换到这个.ssh目录， 并ls 确保生成authorized_keys文件。

```sh
cd ~/.ssh 
```

4、修改authorized_keys文件权限。

```sh
sudo chmod 600 authorized_keys 
```

5、修改~/.ssh文件夹权限。

```sh
sudo chmod 700 ~/.ssh 
```

6、重启SSH便大功告成

```sh
sudo service sshd restart
```


