---
title: Spring
tags: java
notebook: JAVA
---

# Spring框架概述

1. Spring是轻量级的开源的javaee框架
2. Spring可以解决企业应用开发的复杂性
3. Spring两个核心：IOC和Aop

IOC：控制反转，把创建对象的过程交给Spring进行管理

Aop：面向切面，不修改源代码进行功能增强

特点：
1. 方便解耦，简化开发
2. Aop编程支持
3. 方便程序的测试
4. 方便集成各种优秀的框架
5. 方便进行事务操作
6. 方便进行API开发难度

# IOC
## 概述
**控制反转**，把对象创建和对象之间的调用过程交给Spring进行管理

**目的**：降低耦合度

底层原理：XML解析、反射

IOC思想基于IOC容器完成，IOC容器底层就是对象工厂

## 实现方式
Spring提供IOC实现的两种方式：
1. BeanFactory：IOC容器基本实现，是Spring内部的使用接口，不提供开发人员使用，加载配置文件时不会创建对象，在获取对象、使用对象时才会创建对象
2. ApplicationContext：BeanFactory的子接口，一般由开发人员使用，加载配置文件时就会创建对象

# Bean管理
Bean管理指的是：创建对象、注入属性

实现方式：
1. 基于xml配置文件方式实现
2. 基于注解方式

# 基于xml配置文件方式
1. 创建对象
```java
//创建对象，快捷键：alt＋ins
<bean id="user" class="com.company.Users"></bean>
```
创建对象时默认执行无参构造函数

2. 注入属性

注入方式：set方法注入、有参构造函数注入
```java
//注入属性
//通过set方法注入，需要在类中定义set方法
    <bean id="user" class="com.company.Users">
        <property name="name" value="tian"></property>
    </bean>
//通过有参构造函数，需要定义有参和无参构造函数
    <bean id="user" class="com.company.Users">
        <constructor-arg name = "oname" value="tian"></constructor-arg>
    </bean>
```
DI是ioc的一种具体实现，表示依赖注入、注入属性，必须在创建对象的基础上进行

## IOC操作bean管理（bean作用域）

在Spring里面，默认情况下，bean是单实例对象

通过bean标签里面的scope属性设置singleton（默认值，单实例）、prototype（多实例）

设置为singleton时，在加载Spring配置文件的时候创建单实例对象；

prototype 在调用getBean方法时创建多实例对象

## IOC操作Bean管理（xml自动装配）
自动装配：根据指定装配规则（属性名称或者属性类型），Spring自动将匹配的属性值进行注入

Bean标签中增加autowire属性
1. byName：根据属性名称，注入值bean的id值和类型属性名称一样
2. byType：根据属性类型注入，如果一个类型生成了不同的对象，则会报错

## IOC操作Bean管理（引入外部属性文件）

# 注解方式

注解：注解是代码特殊标记，格式：@注解名称（属性名称= 属性值，属性名称=属性值。。。）

注解目的：简化xml配置

注解作用在：类、方法、属性

## Spring针对Bean管理中提供的注解

1. @Component
2. @Service
3. @Controller
4. @Repository

四个注解功能是一样的，都可以用来创建bean实例

## 基于注解的对象创建
1. 使用注解需要先引入aop依赖包
2. 开启组件扫描
```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.company.Spring5"></context:component-scan>

</beans>
```

## 基于注解的属性注入
* @Autowired：根据类型注入
* @Qualifier(value = "userDaoImpl")：根据名称注入，根据autowired一起使用
* @ Resource：既可以根据名称，也可以根据类型，不是Spring中的注解，是javax的注解

```java
//@Resource  //根据类型注入
@Resource(name = "userDaoImpl")  //根据名称注解
```
* @Value：注入普通类型属性
```java
//不需要set方法
@Value(value = "tian")
private String name;
```


```java
//UserDao接口
package com.company.Spring5.Dao;

public interface UserDao {
    public void add();
}

//UserDaoImpl实现类1

package com.company.Spring5.Dao;

import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao{
    @Override
    public void add() {
        System.out.println("=========add UserDao1==========");
    }
}

//实现类2
package com.company.Spring5.Dao;

import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl2 implements UserDao{
    @Override
    public void add() {
        System.out.println("=========add UserDao2==========");
    }
}


//UserService类
package com.company.Spring5.Service;

import com.company.Spring5.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //创建Dao对象
    @Autowired  //根据类型自动注入
    @Qualifier(value = "userDaoImpl")  //因为有多个实现类，不能只通过autowired类型注入，需要加入对应的名字
    private UserDao UserDao;


    public void add(){
        System.out.println("=======add UserService========");
        UserDao.add();
    }

}

//test
package com.company.Spring5.TestSpringDemo;

import com.company.Spring5.Service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
    @Test
    public void testService(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean1.xml");

        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }

}
```

## 完全注解开发
1. 创建配置类

创建config文件夹，创建config类
```java
package com.company.Spring5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  //作为配置类，替代xml文件
@ComponentScan(basePackages = "com.company.Spring5")  //扫描
public class ConfigSpring {
}

```
2. 修改测试类
```java

//test
package com.company.Spring5.TestSpringDemo;

import com.company.Spring5.Service.UserService;
import com.company.Spring5.config.ConfigSpring;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
    @Test
    public void testService(){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ConfigSpring.class); //修改这里

        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }

}

```

# Aop

## 概述
oop：面向对象编程

AOP:面向切面编程，利用aop可对业务罗技的各个部分进行隔离，使各个部分之间耦合度降低，提高程序的可重用性，同时提高开发效率

**解释：不修改源代码，在主干功能中增加新功能**

## 底层原理

**底层使用动态代理**，两种情况：
1. 有接口情况，使用JDK动态代理
创建接口实现类的代理对象，增强类的方法

2. 没有接口情况，使用CGLIB动态代理
创建子类的代理对象，增强类的方法

## 使用JDK动态代理

1. 使用Proxy类里面的方法创建代理对象
2. 调用newProxyInstance方法

方法有三个参数
1. 类加载器
2. 增强方法所在的类，这个类实现的接口，支持多个接口
3. 实现这个接口的InvocationHandler，创建代理对象，写增强方法


## AOP术语

* 连接点：类里面可以被增强的方法
* 切入点：实际真正增强的方法
* 通知（增强）：实际增强的逻辑部分为通知（增强），有多种类型
* 1. 前置通知：前
* 2. 后置通知：后
* 3. 环绕通知：前后
* 4. 异常通知：异常
* 5. 最终通知：finally
* 切面：把通知应用到切入点的过程，是一个动作

## AOP操作（准备）
Spring框架一般都是基于AspectJ实现AOP操作

AspectJ不是Spring的组成部分，独立AOP框架，一般把AspectJ和Spring一起使用，进行AOp操作

实现方式：XML或注解

## 切入点表达式
作用：知道对哪个类的哪个方法进行增强

## AOP操作（注解）


 


