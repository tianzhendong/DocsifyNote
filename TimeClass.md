---
title: TimeClass
tags: java
notebook: Java
---

# 用long变量的值表示时间。

```
long now = System.currentTimeMillis()    //获取现在时间，用距离基准时间的毫秒数表示

Date d1 = new Date()    //获取当前时间，年月日表示
d1.getTime()    //获取当前时间对应的毫秒数


```

# DateFormat类

把时间对象转化成指定格式的字符串，是一个抽象类，一般使用其子类SimpleDateFormat类

```
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试时间对象和字符串相互转换
 * 使用DateFormat、SimpleDATe Format
 */

public class TestDateFormat {
    public static void main(String[] args) throws ParseException {
        //定义日期的格式，可以自己定义
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //SimpleDateFormat df = new SimpleDateFormat("今年第几周,第几天");

        //将字符串转为时间对象
        Date d1 = df.parse("2021-06-03 19:40:00");
        System.out.println(d1.getTime());   //d1.getTime()获取时间对应的毫秒数

        //将Date对象转化成字符串
        Date d2 = new Date(1000L*3600*23);
        String str1 = df.format(d2);
        System.out.println(str1);

    }
}
```


# Calendar类

Calendar是一个抽象类，GregorianCalendar类是其子类，使用的是公历；

月份的表示，0表示一月。  周日用1表示，周六用7表示。

```
package JavaStudy;

import java.awt.desktop.SystemEventListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 测试Calendar类和GregorianCalendar类
 */

public class TestCalendar {
    public static void main(String[] args) {
        //生成一个日期，2021年6月3日
        GregorianCalendar calendar = new GregorianCalendar(2021,5,3,19,59,00) ;

        int year = calendar.get(Calendar.YEAR); //print:2021
        int month = calendar.get(Calendar.MONTH);   //print : 5
        int day = calendar.get(Calendar.DAY_OF_MONTH);  //print:3
        int day2 = calendar.get(Calendar.DATE); //calendar.date = calendar.day_of_mouth
        int date = calendar.get(Calendar.DAY_OF_WEEK);  //print 4
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        System.out.println(day2);
        System.out.println(date);

        //set date
        GregorianCalendar calendar2 = new GregorianCalendar(    );

        calendar2.set(Calendar.YEAR,2021);   //set year = 2021
        calendar2.set(Calendar.MONTH,5);    //set monty = 5
        calendar2.add(Calendar.MONTH, -4 ); //set month = month -4


        //日历和时间转换
        Date d= calendar.getTime(); // calendar to date
        GregorianCalendar calendar4 = new GregorianCalendar();
        calendar4.setTime(new Date());  //date to calendar
    }
}
```
