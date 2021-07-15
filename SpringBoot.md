---
title: SpringBoot
tags: java
notebook: JAVA
---

# 时代背景

## 微服务
* 微服务时一种架构风格
* 一个应用拆分为一组小型服务
* 每个服务运行在自己的进程内，可就是可以独立部署和升级
* 服务之间使用轻量级HTTP交互
* 服务围绕业务功能拆分
* 可以由全自动部署机制独立部署
* 去中心化，服务自治，服务可以使用不同的语言、不同的存储技术

## 分布式

困难：
* 远程调用
* 服务发现
* 负载均衡
* 服务容错
* 配置管理
* 服务监控
* 链路追踪
* 日志管理
* 任务调度
* 。。。

分布式解决：
* SPringBoot+SpringCloud

## 云原生

# HelloWord 

## 创建maven工程

## 引入依赖
```xml
<!--引入springboot的父项目-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.2</version>
    </parent>
<!--需要开发web时引入web-->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```

## 创建主程序

```java
package com.tian.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* 主程序，引导SpringBOot运行
* 用@SpringBootApplication注明
* */
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }
}
```

## 编写业务
正常编写
```java
package com.tian.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//
//@Controller
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String handle01(){
        return "Hello, Spring Boot 2!";
    }
}
```

## 测试
运行main方法

## 简化配置
在resources文件夹下新增applicaion properties文件

## 简化部署
引入插件
```xml

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.5.2</version>
            </plugin>
        </plugins>
    </build>
```
把项目达成jar包，直接在目标服务器执行



# 依赖管理

## 父项目做依赖管理

需要在xml中导入boot的父项目
```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.2</version>
    </parent>
```

**他的父项目如下，几乎声明了所有开发中常用的依赖的版本号**
```xml
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.5.2</version>
  </parent>
```

## 修改版本号
可以在xml配置文件中自定义修改版本，如修改mysql版本

## 场景启动器
```java
spring-boot-starter-*  //*表示某种场景
```
只要引入对应场景starter，这个场景的所有常规需要的依赖都会自动引入

## 自动版本仲裁
引入依赖都可以不写版本号

# 自动配置

## 自动配好Tomcat

## 自动配好SpringMVC

## 自动配好web常用功能

## 默认的包结构
* 主程序所在包及其下面的所有子包里面的组件都会被默认扫描进来

* 无需配置以前的包扫描

## 各种配置拥有默认值

## 按需加载所有自动配置项
只会配置引入的场景的自动配置

# 底层注解-@Configuration

```java
package com.tian.springboot.config;

import com.tian.springboot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* 1. 配置类里面使用@Bean标注在方法上给容器注册组件，默认单实例
* 2. 配置类MyConfig本身也是组件
* 3.
* */
@Configuration(proxyBeanMethods = true) //告诉SpringBoot这是一个配置类  == xml配置文件
/*
* proxyBeanMethods：代理bean的方法
* 默认为true，为full模式，单例模式proxyBeanMethods = true
* proxyBeanMethods = false lite轻量模式，组件之间没有依赖，多例模式
* */
public class MyConfig {

    @Bean   //给容器中添加组件。
    //方法名作为组件的id，返回类型是组件（类）类型，返回的值就是组件在容器中的实例（对象）
    public User user01(){
        return new User("tian", 10);
    }

    @Bean("user")   //给容器中添加组件。
    //"user"作为组件的id，
    public User user02(){
        return new User("tian", 10);
    }
}
```


# @import

@import({User.class, DBHelper.class}) //给容器中自动创建出这两个类型的组件，默认组件的名字是全类名


