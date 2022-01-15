---
title: IO
tags: JAVA
notebook: JAVA
---

[TOC]

# IO

# 数据源

数据源Data Source，提供数据的原始媒介，如：数据库、文件、其他程序、内存、网络连接、IO设备等；

数据源分为：源设备、目标设备
1. 源设备：为程序提供数据，一般对应输入流
2. 目标设备：程序数据的目的地，一般对应输出流

# 流

流是一个抽象、动态的概念，是一连串连续动态的数据集合。

流：字节流、字符流

# 四大IO抽象类

InputStream/OutputStream是字节的输入输出流抽象父类

Reader/Writer是字符的IO流抽象父类

# InputStream

> 字节输入流的所有类的父类。根据节点的不同，派生了不同的节点流子类，数据的单位为字节byte（8bite）。

1. int read()：读取一个字节的数据，并作为int类型返回（0-255），如果未读出，则返回-1，返回-1表示读取结束；
2. void close()：关闭输入流对象，释放相关系统资源，用完一定要关闭。

# OutputStream
>表示字节输出流的所有类的父类，接受输出字节并发送到某个目的地；

1. void write(int n)：向某个目的地写入一个字节；
2. void close()：关闭输出流对象，释放相关系统资源。

# Reader
>读取字符流的抽象类，数据单位为字符

1. int read()：读取一个字符数据，作为int类型返回（0-65535之间的一个unicode值），如果未读出，则返回-1，返回-1表示读取结束；
2. void close()：关闭输入流对象，释放相关系统资源，用完一定要关闭。

# Writer
>用于输出的字符流抽象类，数据单位为字符

1. void write(int n)：向某个目的地写入一个字符；
2. void close()：关闭输出流对象，释放相关系统资源。

# 流的概念细分

按流的方向：
1. 输入流，以InputStream、Reader结尾的流
2. 输出流，以OutputStream、Writer结尾的流

按处理的数据单元：
1. 字节流：以字节为单位获取数据，一般命名上以Stream为结尾的流为字节流；
2. 字符流：以字符为单位获取数据，一般以Reader/Writer结尾的流

按处理对象不同：
1. 节点流：可以直接从数据源或者目的地读写数据
2. 处理流：不直接连接到数据源或者目的地，是“处理流的流”，通过对其他流的处理提高程序的性能，也叫包装流；

节点流处于IO操作的第一线，所有操作都必须通过他们进行；处理流可以对节点流进行包装，提高性能或者提高程序的灵活性

# IO流体系

1. InputStream/OutputStream:字节流的抽象类
2. Reader/Writer:字符流的抽象类
3. FileInputStream/FileOutputStream:节点流，以字节为单位直接操作”文件“
4. ByteArrayIuputStream/ByteArrayOutputStream：节点流，以字节为单位直接操作”字节数组对象“
5. ObjectInputStream/ObjectOutputStream：处理流，以字节为单位直接操作”对象“
6. DataInputStream/DataOutputStream：处理流，以字节为单位直接操作”基本数据类型与字符串类型“
7. FileReader/FileWriter：节点流,以字符为单位直接操作”文本文件“，注意，只能读写文本文件
8. BufferedReader/BufferedWriter：处理流，将Reader/Writer对象进行包装，增加缓存功能，提高读写效率
9. BufferedInputStream/BufferedOutputStream：处理流，将InputStream/OutputStream对象进行包装，增加缓存功能，提高读写效率
10. InputStreamReader/OutputStreamWriter：处理流，将字节流对象转化成字符流对象
11. PrintStream：处理流，将OutputStream进行包装，可以方便输出字符，更加灵活

# IO流第一个例子
```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * 读取E:\a.txt文件中的”tianzhendong“
 */
public class Test1 {
    public static void main(String[] args) {
        //实例化IO输入流对象
        FileInputStream fis1 = null;
        StringBuilder sb = new StringBuilder(   );
        try {
            fis1 = new FileInputStream("e:/a.txt");
            int temp = 0;
            while ((temp = (fis1.read())) != -1){   //read()每次读取一个字符，并返回int型的ASCII值
                System.out.println(temp);    //输出对应的ASCII
                sb.append((char)temp);
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis1 != null){
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

```

# File类

通过file类操作文件
```java
import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        //实例化File对象
        File file = new File("e:/b.txt");
        System.out.println(file.createNewFile());
        //查看对象是否存在
        System.out.println(file.exists());
        //查看对象是否是隐藏文件
        System.out.println(file.isHidden());
        //查看绝对路径
        System.out.println(file.getAbsoluteFile());
        //查看相对路径
        System.out.println(file.getPath());
        //查看名字
        System.out.println(file.getName());
        //查看路径加名字
        System.out.println(file);
        //查看长度
        System.out.println(file.length());
        //删除文件
        System.out.println(file.delete());
    }
}
/**
true
true
false
e:\b.txt
e:\b.txt
b.txt
e:\b.txt
0
true
*/
```

## 通过file操作目录

```java
import java.io.File;
import java.io.IOException;

public class DirectoryDeom {
    public static void main(String[] args) throws IOException {
        //实例化对象，mkdirs可以创建多级目录
        File file = new File("e:/test");
        System.out.println(file.mkdir());
        //判断是否是目录
        System.out.println(file.isDirectory());
        //获取父级目录
        System.out.println(file.getParentFile());
        //查看包含的文件和目录的路径名
        File file1 = new File("e:/");
        String [] list = file1.list();
        for(String s :list){
            System.out.println(s);
        }
        //返回一个file数组，表示目录中的文件的绝对路径，和list不同的是，文件名带路径
        File[] files = file1.listFiles();
        for(File f : files){
            System.out.println(f);;
        }
    }
}

```

# 文件字节流
* FileInputStream通过字节的方式读取文件，适合读取所有类型的文件（图像、视频、文本文件等）。Java也提供了FileReader专门读取文件文件；
* FileOutputStream通过字节的方式写数据到文件中，适合所有类型的文件。java也提供了FileWriter专门写入文本文件。

## 例子

```java
package com.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamDemo {
    public static void main(String[] args) throws IOException {
        //程序开始时间
        long start = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //实例化
            fis = new FileInputStream("e:/灵笼.png");
            fos = new FileOutputStream("e:/灵笼2.png");
            int temp = 0;
            while ((temp = fis.read())!=-1){
                //输出
                //System.out.println(temp);
                //复制到fos
                fos.write(temp);
            }
            //将数据从内存写入到磁盘
            fos.flush();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //关闭fis和fos
            if(fis != null){
                fis.close();
            }
            if(fos != null){
                fos.close();
            }
        }
        //程序结束时间
        long end = System.currentTimeMillis();
        System.out.println("运行时间为："+ (end - start)+"ms");
    }
}
/*
*运行时间为：102ms
*/
```
## 通过缓冲区提高读写效率

### 方式一
通过创建一个指定长度的字节数组作为缓冲区，以此来提高IO流的读写效率。该方式适合读取较大图片时的缓冲区定义。注意：缓冲区的长度一定是2的整数幂。一般情况下，**1024**长度较为合适
```java
package com.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamBuffDemo {
    public static void main(String[] args) throws IOException {
        //程序开始时间
        long start = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //实例化
            fis = new FileInputStream("e:/灵笼.png");
            fos = new FileOutputStream("e:/灵笼2.png");
            int temp = 0;
            //创建一个缓冲区，提高读写效率
            byte[] buff = new byte[1024];

            while ((temp = fis.read(buff))!=-1){
                //输出
                //System.out.println(temp);
                //复制到fos
                fos.write(buff,0, temp);
            }
            //将数据从内存写入到磁盘
            fos.flush();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //关闭fis和fos
            if(fis != null){
                fis.close();
            }
            if(fos != null){
                fos.close();
            }
        }
        //程序结束时间
        long end = System.currentTimeMillis();
        System.out.println("运行时间为："+ (end - start)+"ms");
    }
}
/*
*运行时间为：13ms
*/
```
### 方式二
也是通过创建一个字节数组作为缓冲区，但是长度直接通过available()返回当前文件的预估长度，通过一次读写操作中完成。注意，文件过大时，占用的内存较大。大文件不推荐；
```java
package com.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStreamBuff2Demo {
    public static void main(String[] args) throws IOException {
        //程序开始时间
        long start = System.currentTimeMillis();

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //实例化
            fis = new FileInputStream("e:/灵笼.png");
            fos = new FileOutputStream("e:/灵笼2.png");

            //创建一个缓冲区，提高读写效率
            byte[] buff = new byte[fis.available()];
            fis.read(buff);
            //一次性复制，不需要循环
            fos.write(buff);
            //将数据从内存写入到磁盘
            fos.flush();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //关闭fis和fos
            if(fis != null){
                fis.close();
            }
            if(fos != null){
                fos.close();
            }
        }
        //程序结束时间
        long end = System.currentTimeMillis();
        System.out.println("运行时间为："+ (end - start)+"ms");
    }
}
```

## 通过字节缓冲流提高读取效率

>java缓冲流本身并不具有IO流的读写功能，只是在别的流上加上缓冲功能提高效率，因此缓冲流是一种处理流/包装流；

当对文件或者其他数据源进行频繁的读写操作时，效率低，这是如果使用缓冲流可以提高效率；缓冲流是先将数据缓存起来，当缓存区满以后或者手动刷新时再一次性的读取到程序或写入目的地；

BufferedInputStream和BufferedOutputStream这两个是缓冲字节流通过内部缓存数组来提高操作流的效率。

**缓冲区中，默认的byte数组长度为8192**


```java

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStreamBuffer3Demo {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream("e:/灵笼.png");
            bis = new BufferedInputStream(fis);
            fos = new FileOutputStream("e:/灵笼11.png");
            bos = new BufferedOutputStream(fos);
            int temp = 0;
            while ((temp = bis.read())!= -1){
                bos.write(temp);
            }
            bos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //关闭时，先关处理流/包装流
                bos.close();
                fos.close();
                bis.close();
                fis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间："+ (endTime - startTime)+"ms");
    }
}
```

# 转换流
InputStreamReader/OutputStreamWriter实现将字节流转化成字符流

## 通过转换流实现键盘输入到屏幕输出
```java
package com.IO;

import java.io.*;

public class ConvertStream {
    public static void main(String[] args) {
        //InputStream is = System.in;
        BufferedReader bf = null;
        BufferedWriter bw = null;
        try {
            //将字节流对象System.in转换为字符流对象，实现一次采集一行数据
            bf = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
            while (true){
                bw.write("please input:");
                bw.flush();
                String input = bf.readLine();
                if(input.equals("exit"))
                    break;
                //实现屏幕输出，等同于System.out.println(input);
                bw.write("您输入的是："+input);
                bw.newLine();
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
```

# 字符输出流
java中提供了用于字符输出的流对象PrintWriter，**具有自动行刷新缓冲字符输出流**，不需要在flush，特点是可以按行写出字符串，并可通过println()实现自动换行

# 字节数组流
ByteArrayInputStream和ByteArrayOutputStream经常用在需要流和数组之间转换的情况

## 字节数组输入流
FileInputStream把文件当作数据源；ByteArrayInputStream把内存中的“字节数组对象”当作数据源。

```java
package com.IO;

import java.io.ByteArrayInputStream;

public class ByteArrayInputStreamDemo {
    public static void main(String[] args) {
        //创建字节数组，作为数据源
        byte [] bytes = "tianzhendong".getBytes();

        ByteArrayInputStream bais = null;
        StringBuilder sb = new StringBuilder();
        try {
            bais = new ByteArrayInputStream(bytes);
            int temp = 0;
            while ((temp = bais.read())!= -1){
                sb.append((char)temp);
            }
            System.out.println(sb.toString());
        } finally {
            try {
                bais.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

## 字节数组输出流
将流中的数据写入到字节数组中

```java
package com.IO;

import java.io.ByteArrayOutputStream;

public class ByteArrayOutputStreamDemo {
    public static void main(String[] args) {
        ByteArrayOutputStream bos = null;
        try {
            StringBuilder sb = new StringBuilder();
            bos = new ByteArrayOutputStream();
            //添加字符
            bos.write('t');
            bos.write('i');
            bos.write('a');
            bos.write('n');
            //获取字符数组
            byte [] arr = bos.toByteArray();
            //添加到字符串中
            for(int i : arr){
                sb.append((char) i);
            }
            System.out.println(sb.toString());
        } finally {
            try {
                bos.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

# 数据流
>数据流将基本数据类型与字符串类型作为数据源，从而允许程序以与机器无关的方式从底层输入输出流中操作java基本数据类型与字符串类型

DataInputStream和DataOutputStream提供了可以存取与机器无关的所有java基础类型的方法

```java
package com.IO;

import java.io.*;

public class DataIOStreamDemo {
    public static void main(String[] args) {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("e:/a.txt")));
            dis = new DataInputStream(new BufferedInputStream(new FileInputStream("e:/a.txt")));
            //写入
            dos.writeBoolean(true); //写入boolean类型
            dos.writeChar('t'); //写入char类型
            dos.writeInt(10);   //写入int
            dos.writeUTF("tian");   //写入string
            dos.flush();
            //读取，顺序必须和写入一致
            System.out.println("boolean:"+dis.readBoolean());
            System.out.println("char:"+dis.readChar());
            System.out.println("int:"+dis.readInt());
            System.out.println("string:"+dis.readUTF());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                dis.close();
                dos.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

# 对象流
对象的本质可是用来组织和存储数据的，本身也是数据

## 序列化和反序列化
当两个进程远程通信时，彼此可以发送各种类型的数据。无论是何种类型的数据，都会以二进制序列的形式在网络上传送

当发送java对象时，发送方需要把java对象转化为字节序列，接收方需要把字节序列恢复为java对象，成为序列化和反序列化

对象序列化的作用
1. 持久性：把对象的字节序列永久地保存到硬盘上，通常存放到一个文件中
2. 网络通信：在网络上传送对象的字节序列，如：服务器之间的数据通信、对象传递

### 序列化设计的类和接口

ObjectOutputStream代表对象输出流，他的writeObject（）方法可将对象进行序列化，把得到的字节序列写到一个目标输出流中

ObjectInputStream代表对象输入流，readObject（）方法从一个源输入流中读取字节序列，再把他们反序列化为一个对象，并返回

注：**只有实现了Serializable接口的类的对象才能被序列化，Serializable接口是一个空接口，只起到标记作用**


## 操作基本数据类型
对象流中不仅可以实现对基本数据类型进行读写操作，还能对java对象进行读写操作

读写基本数据类型时用法同DataInputStream和DataOutputStream

## 操作对象

将内存中的java对象通过序列化的方式写入磁盘的文件中，被序列化的对象必须要实现serializable序列化接口，复制会抛出异常

### 创建对象
```java
package com.IO;

import java.io.Serializable;

public class Users implements Serializable {
    private int userid;
    private String username;
    private int userage;

    public Users(int userid, String username, int userage) {
        this.userid = userid;
        this.username = username;
        this.userage = userage;
    }

    public Users() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserage() {
        return userage;
    }

    public void setUserage(int userage) {
        this.userage = userage;
    }
}
```

### 序列化对象
```java
package com.IO;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamDemo {
    public static void main(String[] args) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("e:/b"));
            Users u = new Users(1,"tianzhendong",21);
            oos.writeObject(u);
            oos.flush();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(oos!=null){
                    oos.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```
### 反序列化对象
```java
package com.IO;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectInputStreamDemo {
    public static void main(String[] args) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("e:/b"));
            Users u1 = (Users) ois.readObject();
            System.out.println(u1.getUserid()+"\t"+u1.getUsername()+"\t"+u1.getUserage());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if(ois != null){
                    ois.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```
# 随机访问流
RandomAccessFile作用：
1. 实现对一个文件做读和写的操作
2. 可以访问文件的任意位置，不像其他流只能按照先后顺序读取

在开发客户端软件时，经常用到

核心方法：
1. RandomAccessFile（String name , String mode）;name 用来确定文件；mode取r只读或者rw读写，通过mode确定流对文件的访问权限
2. seek（long a ），用来定位流对象读写文件的位置，a确定读写位置距离文件开头的字节个数
3. 个体FilePointer（）获得流的当前读写位置

# File类在IO中的作用

当以文件作为数据源或者目标时，除了可以使用字符串作为文件以及位置的指定以外，也可以使用File类指定

# Apache IO包
Apache-commons工具包中提供了IOUtils/FileUtils，可以方便的对文件和目录进行操作。

## 下载和添加commons包

https://commons.apache.org/proper/commons-io/download_io.cgi

下载后在项目设置、libraries中添加