---
title: Spring5
tags: java
notebook: JAVA
---

[toc]

# Spring

# 1、概述

> 概述

Spring框架是一个开放源代码的J2EE应用程序框架，由Rod Johnson发起，**是针对bean的生命周期进行管理的轻量级容器**（lightweight container）。 Spring解决了开发者在J2EE开发中遇到的许多常见的问题，提供了功能强大**IOC、AOP及Web MVC**等功能。Spring可以单独应用于构筑应用程序，也可以和Struts、Webwork、Tapestry等众多Web框架组合使用，并且可以与 Swing等桌面应用程序AP组合。因此， Spring不仅仅能应用于J2EE应用程序之中，也可以应用于桌面应用程序以及小应用程序之中。

> 理念

使现有的技术更加容易使用，本身是一个大杂烩，整合了现有的技术框架

> SSH和SSM

SSH：Struct2+Spring+Hibernate

SSM：SpringMVC+Spring+Mybatis

> 优点

* **开放源**代码的**免费**的J2EE应用程序框架（容器）
* **轻量级、非入侵式**的框架
* 是针对bean的生命周期进行管理的**轻量级**容器
* 提供了功能强大**IOC、AOP及Web MVC**等功能
* **支持事务的处理**
* Spring可以单独应用于构筑应用程序，也可以和Struts、Webwork、Tapestry等众多Web框架**组合使用**

**总结**：Spring就是一个**轻量级**的控制反转**IOC**和面向切面编程**AOP**的框架

> 缺点

* 配置十分繁琐，人称“配置地狱”

> 组成

![查看源图像](https://gitee.com/tianzhendong/img/raw/master//images/403a0003482fc287bac5)

Spring框架主要由七部分组成，分别是 Spring Core、 Spring AOP、 Spring ORM、 Spring DAO、Spring Context、 Spring Web和 Spring Web MVC。

> 扩展

现代化的java开发，就是基于spring的开发

![image-20210808065217942](https://gitee.com/tianzhendong/img/raw/master//images/image-20210808065217942.png)

* Spring Boot

快速开发的脚手架，基于springboot可以快速开发单个微服务

约定大于配置

* Spring Cloud

基于Spring Boot实现

现在大多数公司都在使用springboot进行快速开发

学习springboot需要完全掌握spring 和springmvc

# 2、IOC理论推导

## 原来方式

> 基础代码

1. 编写Dao层UserDao接口

```java
public interface UserDao {
   void getUser();
}
```

2. 编写Dao层UserDaoImpl实现类和UserDaoMysqlImpl实现类

```java
public class UserDaoImpl implements UserDao {
   @Override
   public void getUser() {
      System.out.println("默认获取用户数据");
   }
}
```

```java
public class UserDaoMysqlImpl implements UserDao{
   @Override
   public void getUser() {
      System.out.println("Mysql获取用户数据");
   }
}
```

3. 编写Service层UserService接口

```java
public interface UserService {
   void getUser();
}
```

4. 编写Service层UserServiceImpl实现类

```java
public class UserServiceImpl implements UserService {
   //创建dao层对应接口的实现类对象，从而调用dao层的方法
   //弊端：新增或者改变需求时，dao层实现类增加或改变，需要更改这里的代码
   private UserDao userDao = new UserDaoImpl();
   @Override
   public void getUser() {
      //调用dao层对应方法
      userDao.getUser();
   }
}
```

5. 编写用户代码MyTest类

```java
public class MyTest {
   public static void main(String[] args) {
      //用户实际调用的是业务层，不接触dao层
      UserService userService = new UserServiceImpl();
      userService.getUser();
   }
}
```

> 分析

用户实际调用的是业务层，不接触dao层，通过创建`userservice`接口引用指向一个`UserServiceImpl`实现类对象，调用`UserServiceImpl`中的`getUser()`方法。由于`UserServiceImpl`中创建dao层对应接口的实现类对象，从而调用dao层的`getUser()`方法

**弊端**：

在我们之前的业务中，用户的需求可能会影响我们原来的代码，需要根据用户的需求去修改源代码，如果程序代码量大，修改的成本十分昂贵

* 新增或者改变需求时，dao层实现类增加或改变，需要更改这里的代码，比如要调用`UserDaoMysqlImpl`中的方法，需要改变UserServiceImpl中的代码



## 改进-IOC原型

> 代码

1. 改变Service层UserServiceImpl实现类

```java
public class UserServiceImpl implements UserService {
   private UserDao userDao;
   //通过set进行动态实现值的注入
   public void setUserDao(UserDao userDao) {
      this.userDao = userDao;
   }
   @Override
   public void getUser() {
      //调用dao层对应方法
      userDao.getUser();
   }
}
```

2. 改变用户代码MyTest类

```java
public class MyTest {
   public static void main(String[] args) {
      //用户实际调用的是业务层，不接触dao层
      UserService userService = new UserServiceImpl();
      // 实现UserDaoImpl类中的方法
      ((UserServiceImpl) userService).setUserDao(new UserDaoImpl());
      userService.getUser();
      //实现UserDaoMysqlImpl方法
      ((UserServiceImpl) userService).setUserDao(new UserDaoMysqlImpl());
      userService.getUser();
   }
}
```

> 分析

在新增或者改变需求时，只需要更改MyTest类中的代码

使用一个set接口实现，实现了革命性的变化

```java
   private UserDao userDao;
   //通过set进行动态实现值的注入
   public void setUserDao(UserDao userDao) {
      this.userDao = userDao;
   }
```

* 之前程序是主动创建对象，控制权在程序员手上，用户访问业务层，由程序员代码决定用户调用什么
* 使用**set注入**后，程序不具有主动性，通过把**接口给用户**，用户访问业务层，主动权在用户，由用户选择调用什么，程序变成了**被动接收对象**（来自用户）

**这种思想即控制反转IOC的原型，从本质上解决了问题，程序员不需要再管理对象的创建了，系统的耦合性大大降低，可以更加专注在业务的实现上**

## IOC本质

**控制反转是一种通过描述（XML或者注解）并通过第三方去生产或获取特定对象的方式，在Spring中实现控制反转的是IOC容器，实现方式是依赖注入**

* 控制反转IOC（inversion of control）是一种设计思想，DI（依赖注入）是实现IOC的一种方式。

* 在没有IOC的程序中，使用面向对象编程，对象的创建和对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制

* 控制反转后，对象的创建转移给了第三方
* 控制反转就是获得依赖对象的方式反转了

IOC是Spring框架的核心内容，可以使用XML配置，也可以使用注解，新版本的Spring也可以零配置实现IOC。采用XML方式配置Bean的时候，Bean的的定义信息和实现是分离的，采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的

**Spring容器在初始化时先读取配置文件，根据配置文件或元数据创建于组织对象存入容器中，程序使用时再从IOC容器中取出需要的对象**

# 3、HelloSpring

## 代码

> 创建实体类Hello.class

该类为后续要生成的对象的类，**必须要有set方法（依赖注入要用）**

```java
public class Hello {
   private String str;

   public String getStr() {
      return str;
   }

   public void setStr(String str) {
      this.str = str;
   }

   public Hello() {
   }

   public Hello(String str) {
      this.str = str;
   }

   public void helloSpring() {
      System.out.println("hello " + str);
   }
}
```

> Spring配置文件Beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--使用Spring创建对象
                类名  变量名 = new 类
    正常创建对象：Hello hello = new Hello();
    bean = 对象
    property相当于给对象中的属性设置值
    id = 变量名
    class = new 的对象
    -->
    <bean id="hello" class="com.tian.pojo.Hello">
        <property name="str" value="Spring"/>
    </bean>

</beans>
```

> 业务代码MyTest.class

```java
import com.tian.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
   public static void main(String[] args) {
      //获取Spring的上下文对象,固定语句
      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
      //对象都在Spring中进行管理，要使用的化直接去里面取
      Hello hello = (Hello) context.getBean("hello");
      hello.helloSpring();
   }
}
```

## 分析

对象由spring进行创建，对象的属性由Spring容器设置，这个过程就叫控制反转IOC，一句话说就是对象由Spring来创建、管理、装配

* **控制**：传统应用程序的对象由程序本身控制创建，使用SPring后，对象由Spring进行创建

* **反转**：程序本身不创建对象，变为被动的接收对象

* **依赖注入**：利用set方法进行注入，对象必须要有set方法



# 4、IOC创建对象的方式

在xml中使用<**bean**>后，不管有没有使用，都会创建

>  使用无参构造创建对象，**默认**

必须有无参构造函数

```xml
<bean id="hello" class="com.tian.pojo.Hello">
    <property name="str" value="Spring"/>
</bean>
```



> 有参构造函数——参数名

**重点**

```xml
<bean id="hello" class="com.tian.pojo.Hello">
    <constructor-arg name="str" value="直接通过参数名"/>
</bean>
```



> 通过有参构造函数——参数下标

```xml
<bean id="hello" class="com.tian.pojo.Hello">
    <constructor-arg index="0" value="参数下标"/>
</bean>
```

> 有参构造函数——参数类型

不建议使用：**当类中有两个或以上属性类型一样时，会导致错误**

```xml
<bean id="hello" class="com.tian.pojo.Hello">
   <constructor-arg type="java.lang.String" value="有参类型"/>
</bean>
```

> 总结

**在配置文件加载的时候，容器中管理的对象就已经初始化了**



# 5、Spring配置

> 别名alias

起一个小名，两个名字hello和hello2都能用来创建对象

```xml
<alias name="hello" alias="hello2"/>
```

> Bean的配置

```xml
<!--
    id:bean的唯一标识符，也就是相当于对象名
    class：bean对象对应的全限定名：包名+类型
    name:也是别名，而且name可以取多个别名，可以用空格、分号;、逗号,分隔
    -->
<bean id="hello" class="com.tian.pojo.Hello" name="hello3，hello4">
    <constructor-arg name="str" value="直接通过参数名"/>
</bean>
```

> import

一般用于多个团队开发，可以将多个配置文件导入合成一个

项目中有多个人开发，三个人负责不同的类的开发，不同的类需要注册在不同的bean中，可以利用import将所有的bean.xml合成一个总的。

```xml
<import resource="Beans.xml"></import>
```



# 6、DI依赖注入

## 构造器注入

见`4、IOC创建对象的方式`

## set方式注入【重点】

>  依赖注入：set注入

* 依赖：bean对象的创建依赖于容器
* 注入：bean对象中的所有属性由容器注入

> 环境搭建

1. 复杂类型

```java
package com.tian.pojo;
public class Address {
   private String address;
//get set tostring省略
}
```

2. 真实类型

```java
public class Student {
   private String name;
   private Address address;
   private String[] books;
   private List<String> hobbies;
   private Map<String, String> card;
   private Set<String> games;
   private String wife;
   private Properties info;
   //省略了get 和set tostring
}
```

3. 配置文件

```xml

<bean id="student" class="com.tian.pojo.Student">
    <!--第一种注入,普通值注入，value=   -->
    <property name="name" value="tian"></property>
</bean>
```



3. 测试代码

```java
public class MyTest {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      Student student = (Student) context.getBean("student");
      System.out.println(student.getName());
   }
}
```

> 注入方式

* 普通值注入value

* bean注入ref
* 数组注入
* list
* map
* properties
* null

```xml
	<bean id="address" class="com.tian.pojo.Address">
        <property name="address" value="上海"/>
    </bean>
    <bean id="student" class="com.tian.pojo.Student">
        <!--普通值注入，value=   -->
        <property name="name" value="tian"></property>
        <!--bean注入，ref =    -->
        <property name="address" ref="address"/>
        <!--数组注入 <array><value>-->
        <property name="books">
            <array>
                <value>book1</value>
                <value>book2</value>
                <value>book3</value>
            </array>
        </property>
        <!--list注入,<list><value>-->
        <property name="hobbies">
            <list>
                <value>听歌</value>
                <value>电影</value>
            </list>
        </property>
        <!--map注入，<map><entry>-->
        <property name="card">
            <map>
                <entry key="key1" value="val1"/>
                <entry key="key2" value="val2"/>
            </map>
        </property>
        <!--set注入,<set><value>-->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>COD</value>
            </set>
        </property>
        <!--null注入，<null>
        空字符串的话，直接name，value=“”即可-->
        <property name="wife">
            <null></null>
        </property>
        <!--properties注入-->
        <property name="info">
            <props>
                <prop key="driver">com.mysql.driver</prop>
                <prop key="url">ual</prop>
                <prop key="name">root</prop>
                <prop key="password">123456</prop>
            </props>
        </property>
    </bean>
```

## 扩展C\P命名空间注入

这个的使用要在bean.xml文件的头目录里面加上两行语句

```xml
xmlns:p="http://www.springframework.org/schema/p"      xmlns:c="http://www.springframework.org/schema/c"

<!--p命名空间注入，可以直接设置注入属性的值：property-->
<bean id="address1" class="com.baidu.pojo.Address" p:name="陕西省"/>
<!--c命名空间注入，通过构造器注入：construct-args-->
<bean id="address2" class="com.baidu.pojo.Address" c:name="宝鸡市"/>
```



# 7、Bean的作用域scope

> 单例模式`scope="singleton"`

默认就是单例，也可以手动设置

```xml
<bean id="address" class="com.tian.pojo.Address" scope="singleton">
    <property name="address" value="上海"/>
</bean>
```

> 原型模式`scope="prototype"`

每次从容器中get的时候，都会产生一个新对象

```xml
<bean id="address" class="com.tian.pojo.Address" scope="prototype">
    <property name="address" value="上海"/>
</bean>
```

> 其余的request、session、application

只能在web开发中使用

# 8、Bean的自动装配

自动装配：Spring会在上下文中自动寻找、装配属性

装配方式：

* 在xml中显式的配置
* 在java中显式的配置
* 隐式的自动装配Bean【重要】

## 搭建环境

一个人有两个从宠物，dog和cat

xml配置

```xml
<bean id="cat" class="com.tian.pojo.Cat"/>
<bean id="dog" class="com.tian.pojo.Dog"/>
<bean id="person" class="com.tian.pojo.Person">
    <property name="name" value="tian"/>
    <property name="cat" ref="cat"/>
    <property name="dog" ref="dog"/>
</bean>
```

## byName、byType自动装配

> byName

byname：会自动在容器上下文中查找和自己对象中set方法后面中的值对应的**bean id**，如果dog改成dog222，则不会成功

```xml
<bean id="cat" class="com.tian.pojo.Cat"/>
<bean id="dog" class="com.tian.pojo.Dog"/>
<!--byname：会自动在容器上下文中查找和自己对象中set方法后面中的值对应的bean id-->
<bean id="person" class="com.tian.pojo.Person" autowire="byName">
    <property name="name" value="tian"/>
</bean>
```

>  byType

byType：会自动在容器上下文中查找和自己对象属性**类型相同**的bean

如果dog改成dog222 ，依然会成功，甚至注册dog时不需要id属性`<bean class="com.tian.pojo.Dog"/>`

**注意，如果dog注册了多个对象，即同一个类型有两个对象，则bytype不可以使用**

```xml
<bean id="cat" class="com.tian.pojo.Cat"/>
<bean id="dog222" class="com.tian.pojo.Dog"/>
<!--byType：会自动在容器上下文中查找和自己对象属性类型相同的bean-->
<bean id="person" class="com.tian.pojo.Person" autowire="byType">
    <property name="name" value="tian"/>
</bean>
```

> 总结

* byname：需要保证所有bean的id唯一，并且bean需要和自动注入的属性的set方法的值一致
* byType：需要保证所有bean的class唯一，并且bean需要和自动注入的属性类型一致

## 使用注解实现自动装配

Spring2.5开始支持注解

>  使用注解：

* 导入约束：context约束`xmlns:context="http://www.springframework.org/schema/context"`
* 配置注解的支持：`    <context:annotation-config/>`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

* xml配置

```xml
<bean id="cat" class="com.tian.pojo.Cat"/>
<bean id="dog" class="com.tian.pojo.Dog"/>
<bean id="person" class="com.tian.pojo.Person"/>
```

> @Autowired

* 默认为**byType**方式

* 直接在属性上使用或者在set方法上使用
* 可以不需要set方法

```java
public class Person {
   @Autowired
   private Cat cat;
   @Autowired
   private Dog dog;
   private String name;
}
```

* 扩展：autowired有一个属性，required，默认为true，如果显式的定义了其为false，说明这个对象允许为null

```java
 @Autowired(required = false)
```

> @Qualifier

Autowired默认为byType方式，如果IOC容器中同一个类型注册了多个对象，那么会出现问题，需要配置Qualifier使用，指定具体的对象

```xml
<bean id="cat1" class="com.tian.pojo.Cat"/>
<bean id="cat2" class="com.tian.pojo.Cat"/>
```



```java
public class Person {
   @Autowired
   @Qualifier(value = "cat1")
   private Cat cat;
}
```

> @Resource

Resource是java自带的注解

通过**byName方式**

```java
public class Person {
	//@Autowired
	//@Qualifier(value = "cat1")
	@Resource(name="cat1")
	private Cat cat;
}
```

# 9、使用注解开发

## Bean

1. 导包，必须有aop的包，`pop.xml`

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.9</version>
    </dependency>
</dependencies>
```

2. 导入context约束，增加注解支持，`applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    <!--指定要扫描的包，该包下的注解就会生效-->
    <context:component-scan base-package="com.tian.pojo"/>
    <!--注解支持-->
    <context:annotation-config/>
</beans>
```

3. 实体类@Component

**在实体类上使用`@Component`注解等价于`<bean id="user" class="com.tian.pojo.User"/>`，id默认为类的名字小写**

```java
//等价于<bean id="user" class="com.tian.pojo.User"/>
//	id默认为类的名字小写
@Component
public class User {
	private String name;
	//get set    
}

```

## 属性的注入@Value

适用于简单的，复杂的还是通过配置文件

```java
//等价于<bean id="user" class="com.tian.pojo.User"/>
//	id默认为类的名字小写
@Component
public class User {
    @Value("tianzd")
	private String name;
	//get set    
}
```

## 衍生的注解

@Component有几个衍生注解，在web开发中会按照MVC三层架构开发

* @Component 用于pojo层
* @Service 用于service业务层
* @Controller 用于Controller层
* @Repository 用于Dao层

四个功能一样，都是代表将某个类注册到spring中，装配Bean

## 自动装配注解

@Autowired

@Resource

## 作用域注解@Scope

在某个类上标注，修改称单例或者多例模式

## 小结

>  xml与注解

* xml更加万能，适用于任何场景，维护简单方便

* 注解不是自己的类使用不了，维护复杂

> 最佳实践

* xml用来管理Bean
* 注解只负责完成属性注入@Value

