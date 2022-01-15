---
title: SpringBoot
tags: java
notebook: JAVA
---
[toc]

# 1.基础入门篇

# 1.1时代背景

## 1.1.1微服务
* 微服务时一种架构风格
* 一个应用拆分为一组小型服务
* 每个服务运行在自己的进程内，可就是可以独立部署和升级
* 服务之间使用轻量级HTTP交互
* 服务围绕业务功能拆分
* 可以由全自动部署机制独立部署
* 去中心化，服务自治，服务可以使用不同的语言、不同的存储技术

## 1.1.2分布式

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

## 1.1.3云原生

# 1.2.HelloWord 

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
在resources文件夹下新增applicaion.properties文件

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



# 1.3.依赖管理

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

# 1.4.底层注解-@Configuration

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


# 1.5.@import

@import({User.class, DBHelper.class}) //给容器中自动创建出这两个类型的组件，默认组件的名字是全类名


# 条件装配-ConditionalOnBean

条件注解

```java
//配置类上
@ConditionalOnBean(name = "tom")  //当容器中有tom组件时，下面的代码才生效
```

# 1.6.配置绑定


将resources下的配置文件中的信息和car类绑定

配置文件如下：
```xml
mycar.brand = "BYD"
mycar.price = "10000"
```
## @Component + @ConfigurationProperties
在car组件中绑定car属性
```java
@Component  //注册到容器中，只有先注册到容器中，才可以使用SpringBoot中的功能
@ConfigurationProperties(perfix = "mycar")//绑定，将

public class Car{
    private String brand;
    private Integer price;
    .....
}
```
## @EnableConfigurationProperties + @ConfigurationProperties
1. 在配置类上使用@EnableConfigurationProperties(car.class)
```java
@EnableConfigurationProperties(car.class)
//作用：1. 开启car配置绑定功能  
//2. 把car这个组件自动注册到容器中
public class MyConfig{
    ...
}
```

1. 在car组件中绑定car属性@ConfigurationProperties(perfix = "mycar")//绑定，


# 1.7.自动配置入门
@SpringBootApplication 等同于下面三个加一起

1. @SpringBootConfiguration：代表当前是一个配置类
2. @EnableAutoConfiguration：最重要
3. @ComponentScan()：指定扫描那些内容

## 自动配置-@EnableAutoConfiguration
```java
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
```

### @AutoConfigurationPackage
自动配置包
```java
@Import({Registrar.class})//给容器中导入组件，利用registrar给容器中导入一系列组件
//将MainApplication所在包下的所有组件导入进来
public @interface AutoConfigurationPackage
```

### Import({AutoConfigurationImportSelector.class})
导入127个自动配置类

## 自动配置-按需开启自动配置项

虽然@EnableAutoConfiguration会默认全部加载127个场景的自动配置，但是会根据@Conditional条件装配规则进行按需配置

## 自动配置-总结
* SpringBoot先加载所有的自动配置类 XXXXXAutoConfiguration
* 每个自动配置类按照条件进行生效，默认都会绑定配置文件指定的值
* 生效的配置类会给容器中装配很多组件
* 只要容器中有这些组件，相当于这些功能就有了
* 定制化配置
* * 用户直接自己@Bean替换底层的组件
* * 用户去看这个组件时获取的配置文件什么值就去修改

## 最佳实践
* 引入场景依赖
* 查看自动配置了哪些（选做）
  * 自己分析，引入场景对应的自动配置一般都生效了
  * 配置文件中添加“debug=true”，开启自动配置报告，Negative（不生效）、positive（生效）
* 。。。

# 1.8.开发小技巧

## Lombok
简化javaBean

1. 引入lombok依赖

```xml
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
```
2. 安装插件，在idea-settings-plugins搜索lombok，点击install，重启idea
3. 在组件前加@Data，加上后会在编译时（源代码上不会加，看不到）自动生成setter、getter方法
4. 加@ToString会在编译时（源代码上不会加，看不到）自动生成Tostring方法
5. 加@NoArgsConstructor和@AllArgsConstructor会在编译时（源代码上不会加，看不到）自动生成无参和有参构造器
6. @EqualsAndHashCode 重写equals和hashcode
7. 
```java
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Car {
    private String brand;
    private Integer price;
}
```



# 2、核心功能

# 2.1、properties
同之前的介绍

# 2.2、yaml
暂时略

# 3、Web开发

# 3.1、简单功能分析
## 3.1.1、静态资源访问

### 静态资源目录
类路径resources文件夹下，以下文件夹的文件可以直接在localhost:8080/文件名  进行直接访问
* static
* public
* resources
* META-INF/resources
只要静态资源放在上述目录下，可以通过localhost:8080/文件名进行访问

原理：静态映射

有请求后，首先根据controller进行处理，不能处理后交给静态资源处理器，根据静态资源进行处理，静态资源处理不了后会提示404

### 静态资源访问前缀
默认无前缀
#### 在application.properties中添加：
```xml
spring.mvc.static-path-pattern=/res/**
```
在访问时需要根据路径：localhost:8080/res/访问的名称)

res好像也可以用其他的替代，只要访问时对应即可

#### 在yaml文件
```yaml
spring:
    mvc:
        static-path-pattern: /res/**
```
### 改变静态资源目录
改变目录，使得只有haha文件夹下的才能被访问
```yaml
spring:
  mvc:
    static-path-pattern: /res/**
  web:
    resources:
      static-locations: classpath:/haha/
```

## 3.1.2、欢迎页支持

* 静态资源路径下index.html
  * 可以配置静态资源路径
  * 不可以配置访问前缀，会导致index.html不能被默认访问
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!--新增欢迎页-->
<h1>tianzhendong</h1>
<!--新增欢迎页-->
</body>
</html>
```
* controller能处理/index

# 3.2 请求处理




# 略去web开发部分内容

# 4、数据访问









