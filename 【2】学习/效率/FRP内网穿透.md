## frp服务端和客户端配置

[blog.csdn.net](https://blog.csdn.net/weixin_44373340/article/details/109803722)

### 服务器端配置

frp的服务器端，一般命名为frps，配置文件是frps.ini，首先需要下载frp，可以在(https://[github](https://so.csdn.net/so/search?q=github&spm=1001.2101.3001.7020).com/fatedier/frp/releases)下载最新版的frp。（此处以 frp\_0.32.0\_linux\_amd64 为例）.

```shell
Wget https://github.com/fatedier/frp/releases/download/v0.32.0/frp_0.32.0_linux_amd64.tar.gz
tar -zxvf frp_0.32.0_linux_amd64.tar.gz
cd frp_0.32.0_linux_amd64
```


​        

进入frp\_0.32.0\_linux\_amd64文件夹，打开frps.ini进行服务器端配置

```shell
vim frps.ini   
```


​        

如果出现无法写入的错误请使用

```shell
sudo vim frps.ini
```


​        

在frps.ini文件中配置如下：

```shell
[common]
bind_port = 7000 #绑定的端口，需要与客户端中 server_port 参数保持一致
vhost_http_port = 80 #虚拟主机运行在本机的端口，如果 vps 有服务占用了端口，应当更换
dashboard_port = dashboard_port_number #frp 后台服务页面的端口，如果设置 8000，便可通过 http://yourip:8000 来访问 frps 的后台页面
dashboard_user = dashboard_user_name #：frp 后台服务页面的管理员用户名
dashboard_pwd = dashboard_pwd_value #frp 后台服务页面的管理员密码
privilege_token = privilege_token_value #自定义值，必须与客户端中的 privilege_token 保持一致
```


​        

配置完成之后，便可以通过如下命令启动 frps：

```shell
./frps -c ./frps.ini #启动服务端frp
```


​        

### 客户端配置

客户端一般命名为frpc，配置文件是frpc.ini，首先需要下载frp，可以在https://github.com/fatedier/frp/releases下载最新版的frp。（此处以 frp\_0.32.0\_linux\_amd64 为例）

```shell
wget https://github.com/fatedier/frp/releases/download/v0.32.0/frp_0.32.0_linux_amd64.tar.gz
tar -zxvf frp_0.32.0_linux_amd64.tar.gz
cd frp_0.32.0_linux_amd64
```


​        

进入frp\_0.32.0\_linux\_amd64文件夹，打开frps.ini进行服务器端配置

```shell
vim frpc.ini   #无法写入添加sudo权限
```


​        

在frpc.ini文件中配置如下：

```shell
[common]
server_addr = your_server_ip #服务器端的 ip
server_port = 7000 #服务器端的端口，即 bind_port
privilege_token = privilege_token_value #同服务器端的 privilege_token 保持一致
[ssh]
type = tcp
local_ip = 127.0.0.1
local_port = 22
remote_port = remote_port_number #远程端口，即 ssh 连接树莓派时的端口
```


​        

配置完成之后，便可以通过如下命令启动 frps：

```sh
./frpc -c ./frpc.ini #启动客户端frp
```


​        

### frp自启动配置

服务端自启动：

```sh
sudo vim /lib/systemd/system/frps.service #进入frps.service文件编写
#写入下列命令
[Unit]
Description=fraps service
After=network.target network-online.target syslog.target
Wants=network.target network-online.target
 
[Service]
Type=simple
ExecStart=/your/path/frps -c /your/path/frps.ini #启动服务的命令（此处写你的frps的实际安装目录）
 #路径为实际安装路径，比如frp在usr用户的frp下，应该配置为ExecStart=/home/usr/frp/frps -c /home/usr/frp/frps.ini
[Install]
WantedBy=multi-user.target
```


​        

然后启动 frps

```sh
sudo systemctl start frps
```


​        

再打开自启动

```sh
sudo systemctl enable frps
```


​        

此外

```sh
sudo systemctl restart frps #重启
sudo systemctl stop frps #停止
sudo systemctl status frps #查看应用日志
```


​        

客户端自启动配置类似。将frps改为frpc

### 参考链接

https://www.jianshu.com/p/a921e85280ed-
https://blog.csdn.net/sinat\_29963957/article/details/83591264?depth\_1-utm\_source=distribute.pc\_relevant.none-task&utm\_source=distribute.pc\_relevant.none-task

[查看原网页: blog.csdn.net](https://blog.csdn.net/weixin_44373340/article/details/109803722)





## FRP+远程桌面

服务端系统：CentOS Linux release 7.6.1810 (Core)

1.服务端配置：

2.在被远程的电脑安装客户端：

　　下载：https://files.cnblogs.com/files/chenjw-note/frp\_0.21.0\_windows\_386.zip

　　解压：

　　修改配置文件

本地端口：```3389```



 使用命令行运行程序：

进入对应程序目录：cd C:\\Users\\chenjianwen\\Desktop\\frp\_远程桌面使用流程\-芒果\\frp\_0.21.0\_windows\_386

执行命令：.\\frpc.exe -c frpc.ini

看到如图则成功安装，这个黑色框框不能关掉了哦

![image-20220211102656844](https://gitee.com/tianzhendong/img/raw/master//images/202202111026940.png)

开启远程设置：

![image-20220211102710275](https://gitee.com/tianzhendong/img/raw/master//images/202202111027471.png)

**以上，仅执行一次部署完即可**

\==================================================

 使用家里电脑的远程桌面工具连接公司办公电脑

![image-20220211102720319](https://gitee.com/tianzhendong/img/raw/master//images/202202111027397.png)

开始连接远程桌面，输入框输入：118.89.23.85:上面根据工号定义的端口

![image-20220211102631951](https://gitee.com/tianzhendong/img/raw/master//images/202202111026039.png)

[查看原网页: www.cnblogs.com](https://www.cnblogs.com/chenjw-note/p/12659786.html)

