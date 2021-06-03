---
title: FileClass
tags: java
notebook: JAVA
---

# File

File用来表示文件和文件夹

```
package JavaStudy;

import java.awt.desktop.SystemEventListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * File类
 */

public class TestFile {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir")); //获取当前项目路径
        File f1 = new File("e:/movies/1.mp4");  //文件
        File f2 = new File("e:/movies.mp4"); //文件夹
        File f3 = new File("a.txt");    //相对路径，默认放在user.dir目录下
        f2.createNewFile(); //创建文件夹f2
        f3.createNewFile(); //创建文件
        //f1.createNewFile();
        System.out.println(f2.exists());    //文件是否存在
        System.out.println(f2.isDirectory());    //是否是目录
        System.out.println(f2.isFile());    //是否是文件夹
        System.out.println(new Date(f2.lastModified()));    //最后修改时间
        System.out.println(f2.length());    //文件大小
        System.out.println(f2.getName());    //文件名字
        System.out.println(f2.getPath());    //文件路径

    }
}

```