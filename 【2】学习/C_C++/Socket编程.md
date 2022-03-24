# socket技术详解（看清socket编程）

[www.cnblogs.com](https://www.cnblogs.com/fengff/p/10984251.html)

https://blog.csdn.net/weixin_39634961/article/details/80236161

socket编程是网络常用的编程，我们通过在网络中创建socket关键字来实现网络间的通信，通过收集大量的资料，通过这一章节，充分的了解socket编程，文章用引用了大量大神的分析，加上自己的理解，做个总结性的文章

## socket大致介绍

 socket编程是一门技术，它主要是在网络通信中经常用到

 既然是一门技术，由于现在是面向对象的编程，一些计算机行业的大神通过抽象的理念，在现实中通过反复的理论或者实际的推导，提出了抽象的一些通信协议，基于tcp/ip协议，提出大致的构想，一些泛型的程序大牛在这个协议的基础上，将这些抽象化的理念接口化，针对协议提出的每个理念，专门的编写制定的接口，与其协议一一对应，形成了现在的socket标准规范，然后将其接口封装成可以调用的接口，供开发者使用

 目前，开发者开发出了很多封装的类来完善socket编程，都是更加方便的实现刚开始socket通信的各个环节，所以我们首先必须了解socket的通信原理，只有从本质上理解socket的通信，才可能快速方便的理解socket的各个环节，才能从底层上真正的把握

## TCP/IP协议

 要理解socket必须的得理解tcp/ip，它们之间好比送信的线路和驿站的作用，比如要建议送信驿站，必须得了解送信的各个细节。

 TCP/IP协议不同于iso的7个分层，它是根据这7个分层，将其重新划分，好比打扫卫生，本来有扫帚，垃圾斗，抹布，涂料，盆栽等就好比OSI的标准几个分层，tcp/ip根据用途和功能，将扫帚，垃圾斗放到粗略整理层，抹布涂料放到中度整理层，盆栽放到最终效果层。这里TCP/IP也对OSI的网络模型层进行了划分：大致如下：

OSI模型：

![image-20220320151509611](https://gitee.com/tianzhendong/img/raw/master/images/202203240941729.png)

TCP/IP协议参考模型把所有的TCP/IP系列协议归类到四个抽象层中

应用层：TFTP，HTTP，SNMP，FTP，SMTP，DNS，Telnet 等等

传输层：TCP，UDP

网络层：IP，ICMP，OSPF，EIGRP，IGMP

数据链路层：SLIP，CSLIP，PPP，MTU

每一抽象层建立在低一层提供的服务上，并且为高一层提供服务，看起来大概是这样子的-

![image-20220320151522484](https://gitee.com/tianzhendong/img/raw/master/images/202203240941951.png)

通过上面的图形，由于底一层的需要向高一层的提供服务，我们大致的理解应用程序需要传输层的tcp和网络层的ip协议提供服务，但是我们这章要分析的socket它是在tcpip协议的那一部分呢，就好比，我们的通讯线路已经有明确的规定，我们的驿站要设计在哪个地方一样

## 回过头再来理解socket

 到目前为止，大致的了解了应用程序和tcpip协议的大致关系，我们只是知道socket编程是在tcp/IP上的网络编程，但是socket在上述的模型的什么位置呢。这个位置被一个天才的理论家或者是抽象的计算机大神提出并且安排出来

![image-20220320151533041](https://gitee.com/tianzhendong/img/raw/master/images/202203240942241.png)

我们可以发现socket就在应用程序的传输层和应用层之间，设计了一个socket抽象层，传输层的底一层的服务提供给socket抽象层，socket抽象层再提供给应用层，问题又来了，应用层和socket抽象层之间和传输层，网络层之间如何通讯的呢，了解这个之前，我们还是回到原点

 要想理解socket编程怎么通过socket关键词实现服务器和客户端通讯，必须得实现的了解tcp/ip是怎么通讯的，在这个的基础上在去理解socket的握手通讯

 在tcp/ip协议中，tcp通过三次握手建立起一个tcp的链接，大致如下

 第一次握手：客户端尝试连接服务器，向服务器发送syn包，syn=j，客户端进入SYN\_SEND状态等待服务器确认

 第二次握手：服务器接收客户端syn包并确认（ack=j+1），同时向客户端发送一个SYN包（syn=k），即SYN+ACK包，此时服务器进入SYN\_RECV状态

 第三次握手：客户端收到服务器的SYN+ACK包，向服务器发送确认包ACK(ack\=k+1），此包发送完毕，客户端和服务器进入ESTABLISHED状态，完成三次握手

 三次握手如下图：

![image-20220320151547615](https://gitee.com/tianzhendong/img/raw/master/images/202203240942948.png)

 根据tcp的三次握手，socket也定义了三次握手，也许是参考tcp的三次握手，一些计算机大神们画出了socket的三次握手的模型图

 模型图如下：

![image-20220320151603528](https://gitee.com/tianzhendong/img/raw/master/images/202203240942520.png)

 在上面图的基础上，如果我们得到上面的图形，需要我们自己开发一些接口，来满足上面的通讯的三次握手，问题就出来了，我们会需要开发哪些函数

## socket的一些接口函数原理

 通过上面的图，我们清楚，我们好比一些泛型的程序员，一些理论提供者提供给了我们上面的图形的理论，我们需要做的就是讲上面的图形的抽象化的东西具体化

 第一次握手：客户端需要发送一个syn j 包，试着去链接服务器端，于是客户端我们需要提供一个链接函数

 第二次握手：服务器端需要接收客户端发送过来的syn J+1 包，然后在发送ack包，所以我们需要有服务器端接受处理函数

 第三次握手：客户端的处理函数和服务器端的处理函数

 三次握手只是一个数据传输的过程，但是，我们传输前需要一些准备工作，比如将创建一个套接字，收集一些计算机的资源，将一些资源绑定套接字里面，以及接受和发送数据的函数等等，这些功能接口在一起构成了socket的编程

 下面大致的按照客户端和服务端将所需的函数详细的列举出来

![image-20220320151644780](https://gitee.com/tianzhendong/img/raw/master/images/202203240942432.png)

![image-20220320151652988](https://gitee.com/tianzhendong/img/raw/master/images/202203240942458.png)-

上面的两个图都概述了socket的通讯原理

## socket的一个例子，总结上述的问题

详细就不在说明，通过一段代码详细的解释

客户端的代码：

```c
#include <winsock2.h>                
#include <stdio.h>  
#pragma comment(lib,"ws2_32.lib")  
int main()  
{  
    //SOCKET前的一些检查，检查协议库的版本，为了避免别的版本的socket，并且通过  
    //WSAStartup启动对应的版本，WSAStartup的参数一个是版本信息，一个是一些详细的细节，注意高低位  
    //WSAStartup与WSACleanup对应  
    int err;  
    WORD versionRequired;  
    WSADATA wsaData;  
    versionRequired=MAKEWORD(1,1);       
    err=WSAStartup(versionRequired,&wsaData);//协议库的版本信息  
  
    //通过WSACleanup的返回值来确定socket协议是否启动  
    if (!err)  
    {  
        printf("客户端嵌套字已经打开!\n");  
    }  
    else  
    {  
        printf("客户端的嵌套字打开失败!\n");  
        return 0;//结束  
    }  
    //创建socket这个关键词，这里想一下那个图形中的socket抽象层  
    //注意socket这个函数，他三个参数定义了socket的所处的系统，socket的类型，以及一些其他信息  
    SOCKET clientSocket=socket(AF_INET,SOCK_STREAM,0);  
  
    //socket编程中，它定义了一个结构体SOCKADDR_IN来存计算机的一些信息，像socket的系统，  
    //端口号，ip地址等信息，这里存储的是服务器端的计算机的信息  
    SOCKADDR_IN clientsock_in;  
    clientsock_in.sin_addr.S_un.S_addr=inet_addr("127.0.0.1");  
    clientsock_in.sin_family=AF_INET;  
    clientsock_in.sin_port=htons(6000);  
  
    //前期定义了套接字，定义了服务器端的计算机的一些信息存储在clientsock_in中，  
    //准备工作完成后，然后开始将这个套接字链接到远程的计算机  
    //也就是第一次握手  
  
    connect(clientSocket,(SOCKADDR*)&clientsock_in,sizeof(SOCKADDR));//开始连接  
  
  
    char receiveBuf[100];  
  
    //解释socket里面的内容  
    recv(clientSocket,receiveBuf,101,0);  
    printf("%s\n",receiveBuf);  
  
    //发送socket数据  
    send(clientSocket,"hello,this is client",strlen("hello,this is client")+1,0);  
  
    //关闭套接字  
    closesocket(clientSocket);  
    //关闭服务  
    WSACleanup();  
    return 0;  
}  

```


对应的服务端的代码

```c
#include <winsock2.h>  
#include <stdio.h>  
#pragma comment(lib,"ws2_32.lib")  
int main()  
{  
       //创建套接字，socket前的一些检查工作，包括服务的启动  
       WORD myVersionRequest;  
       WSADATA wsaData;  
       myVersionRequest=MAKEWORD(1,1);  
       int err;  
       err=WSAStartup(myVersionRequest,&wsaData);  
       if (!err)  
       {  
              printf("已打开套接字\n");              
       }  
       else  
       {  
              //进一步绑定套接字  
              printf("嵌套字未打开!");  
              return 0;  
       }  
       SOCKET serSocket=socket(AF_INET,SOCK_STREAM,0);//创建了可识别套接字  
       //需要绑定的参数，主要是本地的socket的一些信息。  
       SOCKADDR_IN addr;  
       addr.sin_family=AF_INET;  
       addr.sin_addr.S_un.S_addr=htonl(INADDR_ANY);//ip地址  
       addr.sin_port=htons(6000);//绑定端口  
  
       bind(serSocket,(SOCKADDR*)&addr,sizeof(SOCKADDR));//绑定完成  
       listen(serSocket,5);//其中第二个参数代表能够接收的最多的连接数  
  
       SOCKADDR_IN clientsocket;  
       int len=sizeof(SOCKADDR);  
       while (1)  
       {  
          //第二次握手，通过accept来接受对方的套接字的信息  
              SOCKET serConn=accept(serSocket,(SOCKADDR*)&clientsocket,&len);//如果这里不是accept而是conection的话。。就会不断的监听  
              char sendBuf[100];  
              sprintf(sendBuf,"welcome %s to bejing",inet_ntoa(clientsocket.sin_addr));//找对对应的IP并且将这行字打印到那里  
              //发送信息  
          send(serConn,sendBuf,strlen(sendBuf)+1,0);  
              char receiveBuf[100];//接收  
              recv(serConn,receiveBuf,strlen(receiveBuf)+1,0);  
              printf("%s\n",receiveBuf);  
              closesocket(serConn);//关闭  
              WSACleanup();//释放资源的操作  
       }  
       return 0;  
}  
```



## 上面例子用到的知识点

（摘抄carter大神文章）：-

服务器端：

其过程是首先服务器方要先启动，并根据请求提供相应服务：

（1）打开一通信通道并告知本地主机，它愿意在某一公认地址上的某端口（如FTP的端口可能为21）接收客户请求；

（2）等待客户请求到达该端口；

（3）接收到客户端的服务请求时，处理该请求并发送应答信号。接收到并发服务请求，要激活一新进程来处理这个客户请求（如UNIX系统中用fork、exec）。新进程处理此客户请求，并不需要对其它请求作出应答。服务完成后，关闭此新进程与客户的通信链路，并终止。

（4）返回第（2）步，等待另一客户请求。

（5）关闭服务器

客户端：

（1）打开一通信通道，并连接到服务器所在主机的特定端口；

（2）向服务器发服务请求报文，等待并接收应答；继续提出请求......

（3）请求结束后关闭通信通道并终止。

从上面所描述过程可知：

（1）客户与服务器进程的作用是非对称的，因此代码不同。

（2）服务器进程一般是先启动的。只要系统运行，该服务进程一直存在，直到正常或强迫终止。

## 下面就介绍一些API函数：（摘抄carter大神文章）：

创建套接字──socket()

应用程序在使用套接字前，首先必须拥有一个套接字，系统调用socket()向应用程序提供创建套接字的手段，其调用格式如下：

```c
SOCKET PASCAL FAR socket(int af, int type, int protocol)  
```



该调用要接收三个参数：af、type、protocol。参数af指定通信发生的区域：AF\_UNIX、AF\_INET、AF\_NS等，而DOS、WINDOWS中仅支持AF\_INET，它是网际网区域。因此，地址族与协议族相同。参数type 描述要建立的套接字的类型。这里分三种：

（1）一是TCP流式套接字(SOCK\_STREAM)提供了一个面向连接、可靠的数据传输服务，数据无差错、无重复地发送，且按发送顺序接收。内设流量控制，避免数据流超限；数据被看作是字节流，无长度限制。文件传送协议（FTP）即使用流式套接字。

（2）二是数据报式套接字(SOCK\_DGRAM)提供了一个无连接服务。数据包以独立包形式被发送，不提供无错保证，数据可能丢失或重复，并且接收顺序混乱。网络文件系统（NFS）使用数据报式套接字。

（3）三是原始式套接字(SOCK\_RAW)该接口允许对较低层协议，如IP、ICMP直接访问。常用于检验新的协议实现或访问现有服务中配置的新设备。

参数protocol说明该套接字使用的特定协议，如果调用者不希望特别指定使用的协议，则置为0，使用默认的连接模式。根据这三个参数建立一个套接字，并将相应的资源分配给它，同时返回一个整型套接字号。因此，socket()系统调用实际上指定了相关五元组中的“协议”这一元。

指定本地地址──bind()

当一个套接字用socket()创建后，存在一个名字空间(地址族)，但它没有被命名。bind()将套接字地址（包括本地主机地址和本地端口地址）与所创建的套接字号联系起来，即将名字赋予套接字，以指定本地半相关。其调用格式如下：

```c
int PASCAL FAR bind(SOCKET s, const struct sockaddr FAR \* name, int namelen);
```



参数s是由socket()调用返回的并且未作连接的套接字描述符(套接字号)。参数name 是赋给套接字s的本地地址（名字），其长度可变，结构随通信域的不同而不同。namelen表明了name的长度。如果没有错误发生，bind()返回0。否则返回SOCKET\_ERROR。

建立套接字连接──connect()与accept()

这两个系统调用用于完成一个完整相关的建立，其中connect()用于建立连接。accept()用于使服务器等待来自某客户进程的实际连接。

connect()的调用格式如下：

```c
int PASCAL FAR connect(SOCKET s, const struct sockaddr FAR \* name, int namelen);
```



参数s是欲建立连接的本地套接字描述符。参数name指出说明对方套接字地址结构的指针。对方套接字地址长度由namelen说明。

如果没有错误发生，connect()返回0。否则返回值SOCKET\_ERROR。在面向连接的协议中，该调用导致本地系统和外部系统之间连接实际建立。

由于地址族总被包含在套接字地址结构的前两个字节中，并通过socket()调用与某个协议族相关。因此bind()和connect()无须协议作为参数。

accept()的调用格式如下：

```c
SOCKET PASCAL FAR accept(SOCKET s, struct sockaddr FAR* addr, int FAR* addrlen);
```



参数s为本地套接字描述符，在用做accept()调用的参数前应该先调用过listen()。addr 指向客户方套接字地址结构的指针，用来接收连接实体的地址。addr的确切格式由套接字创建时建立的地址族决定。addrlen 为客户方套接字地址的长度（字节数）。如果没有错误发生，accept()返回一个SOCKET类型的值，表示接收到的套接字的描述符。否则返回值INVALID\_SOCKET。

accept()用于面向连接服务器。参数addr和addrlen存放客户方的地址信息。调用前，参数addr 指向一个初始值为空的地址结构，而addrlen 的初始值为0；调用accept()后，服务器等待从编号为s的套接字上接受客户连接请求，而连接请求是由客户方的connect()调用发出的。当有连接请求到达时，accept()调用将请求连接队列上的第一个客户方套接字地址及长度放入addr 和addrlen，并创建一个与s有相同特性的新套接字号。新的套接字可用于处理服务器并发请求。

四个套接字系统调用，socket()、bind()、connect()、accept()，可以完成一个完全五元相关的建立。socket()指定五元组中的协议元，它的用法与是否为客户或服务器、是否面向连接无关。bind()指定五元组中的本地二元，即本地主机地址和端口号，其用法与是否面向连接有关：在服务器方，无论是否面向连接，均要调用bind()，若采用面向连接，则可以不调用bind()，而通过connect()自动完成。若采用无连接，客户方必须使用bind()以获得一个唯一的地址。

监听连接──listen()

此调用用于面向连接服务器，表明它愿意接收连接。listen()需在accept()之前调用，其调用格式如下：

```c
int PASCAL FAR listen(SOCKET s, int backlog);
```



参数s标识一个本地已建立、尚未连接的套接字号，服务器愿意从它上面接收请求。backlog表示请求连接队列的最大长度，用于限制排队请求的个数，目前允许的最大值为5。如果没有错误发生，listen()返回0。否则它返回SOCKET\_ERROR。

listen()在执行调用过程中可为没有调用过bind()的套接字s完成所必须的连接，并建立长度为backlog的请求连接队列。

调用listen()是服务器接收一个连接请求的四个步骤中的第三步。它在调用socket()分配一个流套接字，且调用bind()给s赋于一个名字之后调用，而且一定要在accept()之前调用。

数据传输──send()与recv()

当一个连接建立以后，就可以传输数据了。常用的系统调用有send()和recv()。

send()调用用于s指定的已连接的数据报或流套接字上发送输出数据，格式如下：

```c
int PASCAL FAR send(SOCKET s, const char FAR \*buf, int len, int flags);
```



参数s为已连接的本地套接字描述符。buf 指向存有发送数据的缓冲区的指针，其长度由len 指定。flags 指定传输控制方式，如是否发送带外数据等。如果没有错误发生，send()返回总共发送的字节数。否则它返回SOCKET\_ERROR。

recv()调用用于s指定的已连接的数据报或流套接字上接收输入数据，格式如下：

```c
int PASCAL FAR recv(SOCKET s, char FAR \*buf, int len, int flags);
```



参数s 为已连接的套接字描述符。buf指向接收输入数据缓冲区的指针，其长度由len 指定。flags 指定传输控制方式，如是否接收带外数据等。如果没有错误发生，recv()返回总共接收的字节数。如果连接被关闭，返回0。否则它返回SOCKET\_ERROR。

输入/输出多路复用──select()

select()调用用来检测一个或多个套接字的状态。对每一个套接字来说，这个调用可以请求读、写或错误状态方面的信息。请求给定状态的套接字集合由一个fd\_set结构指示。在返回时，此结构被更新，以反映那些满足特定条件的套接字的子集，同时， select()调用返回满足条件的套接字的数目，其调用格式如下：

```c
int PASCAL FAR select(int nfds, fd\_set FAR \* readfds, fd\_set FAR \* writefds, fd\_set FAR \* exceptfds, const struct timeval FAR \* timeout);
```



参数nfds指明被检查的套接字描述符的值域，此变量一般被忽略。

参数readfds指向要做读检测的套接字描述符集合的指针，调用者希望从中读取数据。参数writefds 指向要做写检测的套接字描述符集合的指针。exceptfds指向要检测是否出错的套接字描述符集合的指针。timeout指向select()函数等待的最大时间，如果设为NULL则为阻塞操作。select()返回包含在fd\_set结构中已准备好的套接字描述符的总数目，或者是发生错误则返回SOCKET\_ERROR。

关闭套接字──closesocket()

closesocket()关闭套接字s，并释放分配给该套接字的资源；如果s涉及一个打开的TCP连接，则该连接被释放。closesocket()的调用格式如下：

```c
BOOL PASCAL FAR closesocket(SOCKET s);
```



参数s待关闭的套接字描述符。如果没有错误发生，closesocket()返回0。否则返回值SOCKET\_ERROR。

[查看原网页: www.cnblogs.com](https://www.cnblogs.com/fengff/p/10984251.html)