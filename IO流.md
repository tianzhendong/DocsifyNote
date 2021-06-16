---
title: IO
tags: JAVA
notebook: JAVA
---

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




