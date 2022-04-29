[toc]

# Linux定时执行脚本

## 创建

输入：

`sudo crontab -e`

输入：

```shell
5 * * * *每小时第5分钟执行
*/5 * * * *每5分钟执行
0 2 * * * 每天凌晨2点执行
```

cron是一个linux下的定时执行工具，可以在无需人工干预的情况下运行作业。由于Cron 是Linux的内置服务，但它不自动起来，可以用以下的方法启动、关闭这个服务：

```sh
/sbin/service crond start //启动服务
/sbin/service crond stop //关闭服务
/sbin/service crond restart //重启服务
/sbin/service crond reload //重新载入配置
```

你也可以将这个服务在系统启动的时候自动启动：

```sh
vim /etc/rc.d/rc.local
#这个脚本的末尾加上：
/sbin/service crond start
```

现在Cron这个服务已经在进程里面了，我们就可以用这个服务了，Cron服务提供以下几种接口供大家使用：

## 编辑

1、直接用crontab命令编辑

cron服务提供crontab命令来设定cron服务的，以下是这个命令的一些参数与说明：

`crontab -u `//设定某个用户的cron服务，一般root用户在执行这个命令的时候需要此参数

```sh
#列出某个用户cron服务的详细内容，如：root查看自己的cron设置：crontab -u root -l
crontab -l
#删除某个用户的cron服务，如：root想删除fred的cron设置：crontab -u fred -r
crontab -r
#编辑某个用户的cron服务，如：crontab -u root -e
crontab -e
#在编辑cron服务时，编辑的内容有一些格式和约定，输入：
#进入vi编辑模式，编辑的内容一定要符合下面的格式：*/1 * * * * ls >> /tmp/ls.txt
```

这个格式的前一部分是对时间的设定，后面一部分是要执行的命令，如果要执行的命令太多，可以把这些命令写到一个脚本里面，然后在这里直接调用这个脚本就可以了，调用的时候记得写出命令的完整路径。时间的设定我们有一定的约定，前面五个*号代表五个数字，数字的取值范围和含义如下：

```txt
分钟　（0-59）
小時　（0-23）
日期　（1-31）
月份　（1-12）
星期　（0-6）//0代表星期天
```



除了数字还有几个个特殊的符号就是"\*"、"/"和"-"、","

```txt
*代表所有的取值范围内的数字，
"/"代表每的意思,
"*/5"表示每5个单位，
"-"代表从某个数字到某个数字,
","分开几个离散的数字。
```



以下举几个例子说明问题：

- 每天早上6点

0 6 * * * echo "Good morning." >> /tmp/test.txt //注意单纯echo，从屏幕上看不到任何输出，因为cron把任何输出都email到root的信箱了。

- 每两个小时

0 */2 * * * echo "Have a break now." >> /tmp/test.txt

- 晚上11点到早上8点之间每两个小时，早上八点

0 23-7/2，8 * * * echo "Have a good dream：）" >> /tmp/test.txt

- 每个月的4号和每个礼拜的礼拜一到礼拜三的早上11点

0 11 4 * 1-3 command line

- 1月1日早上4点

0 4 1 1 * command line

每次编辑完某个用户的cron设置后，cron自动在/var/spool/cron下生成一个与此用户同名的文件，此用户的cron信息都记录在这个文件中，这个文件是不可以直接编辑的，只可以用crontab -e 来编辑。cron启动后每过一份钟读一次这个文件，检查是否要执行里面的命令。因此此文件修改后不需要重新启动cron服务。

2、编辑/etc/crontab 文件配置cron

cron服务每分钟不仅要读一次/var/spool/cron内的所有文件，还需要读一次/etc/crontab,因此我们配置这个文件也能运用 cron服务做一些事情。用crontab配置是针对某个用户的，而编辑/etc/crontab是针对系统的任务。此文件的文件格式是：

```shell
SHELL=/bin/bash
PATH=/sbin:/bin:/usr/sbin:/usr/bin
#如果出现错误，或者有数据输出，数据作为邮件发给这个帐号
MAILTO=root      
#使用者运行的路径,这里是根目录
HOME=/
```

   

## run-parts

```shell
#run-parts某个文件夹，根目录+文件夹+执行当前文件夹下面所有脚本
01 * * * * root run-parts /etc/cron.hourly //每小时执行/etc/cron.hourly内的脚本
02 4 * * * root run-parts /etc/cron.daily //每天执行/etc/cron.daily内的脚本
22 4 * * 0 root run-parts /etc/cron.weekly //每星期执行/etc/cron.weekly内的脚本
42 4 1 * * root run-parts /etc/cron.monthly //每月去执行/etc/cron.monthly内的脚本
```

大家注意"run-parts"这个参数了，如果去掉这个参数的话，后面就可以写要运行的某个脚本名，而不是文件夹名了。

--------------------------------------
基本格式 :

\*　\*　\*　\*　\*　command

分　时　 日　月　周　命令

第1列表示分钟1～59 每分钟用*或者 */1表示
第2列表示小时1～23（0表示0点）
第3列表示日期1～31
第4列表示月份1～12
第5列标识号星期0～6（0表示星期天）
第6列要运行的命令

crontab文件的一些例子：

30 21 * * * /usr/local/etc/rc.d/lighttpd restart
上面的例子表示每晚的21:30重启lighttpd 。

45 4 1,10,22 * * /usr/local/etc/rc.d/lighttpd restart
上面的例子表示每月1、10、22日的4 : 45重启lighttpd 。

10 1 * * 6,0 /usr/local/etc/rc.d/lighttpd restart
上面的例子表示每周六、周日的1 : 10重启lighttpd 。

0,30 18-23 * * * /usr/local/etc/rc.d/lighttpd restart
上面的例子表示在每天18 : 00至23 : 00之间每隔30分钟重启lighttpd 。

0 23 * * 6 /usr/local/etc/rc.d/lighttpd restart
上面的例子表示每星期六的11 : 00 pm重启lighttpd 。

\* */1 * * * /usr/local/etc/rc.d/lighttpd restart
每一小时重启lighttpd

\* 23-7/1 * * * /usr/local/etc/rc.d/lighttpd restart
晚上11点到早上7点之间，每隔一小时重启lighttpd

0 11 4 * mon-wed /usr/local/etc/rc.d/lighttpd restart
每月的4号与每周一到周三的11点重启lighttpd

0 4 1 jan * /usr/local/etc/rc.d/lighttpd restart
一月一号的4点重启lighttpd

=========================================
crontab -e

　　进入一个vi 编辑界面

　　在最后一行加上

　　*/30 * * * * netstat > /tmp/net.log

　　表示每隔30分就执行netstat命令，并把执行结果存入net.log中。

　　Crontab是一个很方便的在unix/linux系统上定时(循环)执行某个任务的程序

　　使用cron服务，用 service crond status 查看 cron服务状态，如果没有启动则 service crond start启动它，

　　cron服务是一个定时执行的服务，可以通过crontab 命令添加或者编辑需要定时执行的任务：

　　crontab -u //设定某个用户的cron服务，一般root用户在执行这个命令的时候需要此参数

　　crontab -l //列出某个用户cron服务的详细内容

　　crontab -r //删除没个用户的cron服务

　　crontab -e //编辑某个用户的cron服务

　　比如说root查看自己的cron设置：crontab -u root -l

　　再例如，root想删除fred的cron设置：crontab -u fred -r

　　在编辑cron服务时，编辑的内容有一些格式和约定，输入：crontab -u root -e

　　进入vi编辑模式，编辑的内容一定要符合下面的格式：*/1 * * * * ls >> /tmp/ls.txt

　　编辑/etc/crontab文件，在末尾加上一行： 30 5 * * * root init 6 这样就将系统配置为了每天早上5点30自动重新启动。

　　需要将crond设置为系统启动后自动启动的服务，可以在/etc/rc.d/rc.local 中，在末尾加上

　　service crond start

　　如果还需要在系统启动十加载其他服务，可以继续加上其他服务的启动命令。

　　比如： service mysqld start

　　基本用法:

　　1. crontab -l

　　列出当前的crontab任务

　　2. crontab -d

　　删除当前的crontab任务

　　3. crontab -e (solaris5.8上面是 crontab -r)

　　编辑一个crontab任务,ctrl_D结束

　　4. crontab filename

　　以filename做为crontab的任务列表文件并载入

　　crontab file的格式:

　　crontab 文件中的行由 6 个字段组成，不同字段间用空格或 tab 键分隔。前 5 个字段指定命令要运行的时间

　　分钟 (0-59)

　　小时 (0-23)

　　日期 (1-31)

　　月份 (1-12)

　　星期几(0-6，其中 0 代表星期日)

　　第 6 个字段是一个要在适当时间执行的字符串

　　例子:

　　#MIN HOUR DAY MONTH DAYOFWEEK COMMAND

　　#每天早上6点10分

　　10 6 * * * date

　　#每两个小时

　　0 */2 * * * date (solaris 5.8似乎不支持此种写法)

　　#晚上11点到早上8点之间每两个小时，早上8点

　　0 23-7/2，8 * * * date

　　#每个月的4号和每个礼拜的礼拜一到礼拜三的早上11点

　　0 11 4 * mon-wed date

　　#1月份日早上4点

　　0 4 1 jan * date

　　补充：在使用crontab的时候，要特别注意的是运行脚本中能够访问到的环境变量和当前测试环境中的环境变量未必一致，一个比较保险的做法是在运行的脚本程序中自行设置环境变量(export)

　　(1)先建一个文件crond.txt如下， 每天早上5点36分重新启动

　　36 5 * * * reboot

　　(2)上传到/opt目录

　　(3)运行命令

　　crontab /opt/crond.txt

　　crontab -l

　　让配置文件生效：如果让配置文件生效，还得重新启动cron，切记，既然每个用户下的cron配置文件修改后。也要重新启动cron服务器。

　　在Fedora 和RedHat中，我们应该用;

　　[root@localhost ~]# /etc/init.d/crond restart

　　如果让crond 在开机时运行，应该改变其运行级别;

　　[root@localhost ~]# chkconfig --levels 35 crond on

　　service crond status 查看 cron服务状态，如果没有启动则 service crond start启动它， cron服务是一个定时执行的服务，可以通过crontab 命令添加或者编辑需要定时执行的任务

　　Crontab文件的每一行由六个域(minutes、hours、day of month、month、day of week、 command)组 成，域之间用空格或Tab分开，其中：

　　minutes： 分钟域，值的范围是0到59

　　hours： 小时域，值的范围是0到23

　　day of month： 日期，值的范围是1到31

　　month： 月份，值的范围是1到12

　　day of week： 星期，值的范围是0到6，星期日值为0

　　command： 所要运行的命令

　　如果一个域是*，表明命令可以在该域所有可能的取值范围内执行。

　　如果一个域是由连字符隔开的两个数字，表明命令可以在两个数字之间的范围内执行(包括两个数字 本身)。

　　如果一个域是由逗号隔开的一系列值组成的，表明命令可以在这些值组成的范围内执行。

　　如果日期域和星期域都有值，则这两个域都有效。

　　编写一个文件，用以启动自动备份进程。

　　cd /opt

　　touch reboot.txt

　　在reboot.txt中添加一下内容:

　　0 4 * * * reboot

　　crontab /opt/reboot.txt

　　用crontab -e编辑定时操作，例如加入下行命令：

　　用crontab -l命令来查看

　　注意:需要启动服务(添加在rc.local中)

　　重启crond任务

　　/etc/init.d/cron restart (Ubuntu下)

　　第一种 在Fedora或Redhat 等以RPM包管理的系统中;

　　[root@localhost ~]# /etc/init.d/crond start

　　[root@localhost ~]# /etc/init.d/crond stop

　　[root@localhost ~]# /etc/init.d/crond restart

　　/etc/rc.d/init.d/crond restart

　　命令简介

　　crontab-操作每个用户的守护程序和该执行的时间表。

　　部分参数说明

　　crontab file [-u user]-用指定的文件替代目前的crontab。

　　crontab-[-u user]-用标准输入替代目前的crontab.

　　crontab-1[user]-列出用户目前的crontab.

　　crontab-e[user]-编辑用户目前的crontab.

　　crontab-d[user]-删除用户目前的crontab.

　　crontab-c dir- 指定crontab的目录。

　　crontab文件的格式：M H D m d cmd.

　　M: 分钟(0-59)。

　　H：小时(0-23)。

　　D：天(1-31)。

　　m: 月(1-12)。

　　d: 一星期内的天(0~6，0为星期天)。

　　cmd要运行的程序，程序被送入sh执行，这个shell只有USER,HOME,SHELL这三个环境变量。

　　下面是一个例子文件：

　　#MIN HOUR DAY MONTH DAYOFWEEK COMMAND

　　#每天早上6点

　　106* * * date

　　#每两个小时

　　0*/2* * * date

　　#晚上11点到早上8点之间每两个小时，早上部点

　　0 23-7/2，8* * * date

　　#每个月的4号和每个礼拜的礼拜一到礼拜三的早上11点

　　0 11 4* mon-wed date

　　#1月份日早上4点

　　0 4 1 jan* date

　　范例

　　lark:~>crontab-1 列出用户目前的crontab.

　　#MIN HOUR DAY MONTH DAYOFWEEK COMMAND

　　10 6* * * date

　　0*/2* * * date

　　0 23-7/2,8 * * * date

　　lark:~>

使用权限 : root用户和crontab文件的所有者

语法 :

crontab [-e [UserName]|-l [UserName]|-r [UserName]|-v [UserName]|File ]

重启：sudo /etc/init.d/cron restart

说明 :

crontab 是用来让使用者在固定时间或固定间隔执行程式之用，换句话说，也就是类似使用者的时程表。-u user 是指设定指定 user 的时程表，这个前提是你必须要有其权限(比如说是 root)才能够指定他人的时程表。如果不使用 -u user 的话，就是表示设定自己的时程表。

参数 :

-e [UserName]: 执行文字编辑器来设定时程表，内定的文字编辑器是 VI，如果你想用别的文字编辑器，则请先设定 VISUAL 环境变数来指定使用那个文字编辑器(比如说 setenv VISUAL joe)

-r [UserName]: 删除目前的时程表

-l [UserName]: 列出目前的时程表

-v [UserName]:列出用户cron作业的状态

时程表的格式如下 :

f1 f2 f3 f4 f5 program

其中 f1 是表示分钟，f2 表示小时，f3 表示一个月份中的第几日，f4 表示月份，f5 表示一个星期中的第几天。

program 表示要执行的程式，就是在终端写入的命令。

当 f1 为 * 时表示每分钟都要执行 program，f2 为 * 时表示每小时都要执行程式，其余类推

当 f1 为 a-b 时表示从第 a 分钟到第 b 分钟这段时间内要执行，f2 为 a-b 时表示从第 a 到第 b 小时都要执行，其余类推

当 f1 为 \*/n 时表示每 n 分钟个时间间隔执行一次，f2 为 \*/n 表示每 n 小时个时间间隔执行一次，其余类推

当 f1 为 a, b, c,... 时表示第 a, b, c,... 分钟要执行，f2 为 a, b, c,... 时表示第 a, b, c...个小时要执行，其余类推

使用者也可以将所有的设定先存放在档案 file 中，用 crontab file 的方式来设定时程表。

由于unix版本不一样，所以部分语法有差别，例如在hp unix aix 中设定间隔执行如果采用*/n 方式将出现语法错误，在这类unix中 ，间隔执行只能以列举方式，详请见例子。

使用方法:

用VI编辑一个文件 cronfile，然后在这个文件中输入格式良好的时程表。编辑完成后，保存并退出。

在命令行输入

$: crontab cronfile

这样就将cronfile文件提交给c r o n进程，同时，新创建cronfile的一个副本已经被放在/ v a r / s p o o l / c r o n目录中，文件名就是用户名。

例子 :

每月每天每小时的第 0 分钟执行一次 /bin/ls :

0 * * * * /bin/ls

在 12 月内, 每天的早上 6 点到 12 点中，每隔 20 分钟执行一次 /usr/bin/backup :

*/20 6-12 * 12 * /usr/bin/backup

周一到周五每天下午 5:00 寄一封信给 alex@domain.name :

0 17 * * 1-5 mail -s "hi" alex@domain.name < /tmp/maildata

每月每天的午夜 0 点 20 分, 2 点 20 分, 4 点 20 分....执行 echo "haha"

20 0-23/2 * * * echo "haha"

晚上11点到早上8点之间每两个小时，早上8点

0 23-7/2，8 * * * date

> 在hp unix,中，每20分钟执行一次，表示为：0,20,40 * * * * 而不能采用*/n方式，否则出现语法错误

版权声明：本文为CSDN博主「编程爱好者熊浪」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/xionglangs/article/details/52913351