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



# 10、使用java的方式配置Spring

舍弃xml配置文件，使用config类实现

> 实体类

```java
package com.tian.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//这个注解的意思就是说明这个类被注册到了容器中
@Component
public class User {
   private String name;

   public String getName() {
      return name;
   }
   @Value("tianzhendong")
   public void setName(String name) {
      this.name = name;
   }
}
```

> 配置类

```java
package com.tian.config;

import com.tian.pojo.User;
import org.springframework.context.annotation.*;
//使其成为配置类，同beans.xml
//这个也会被spring容器托管，注册到容器中，因为他也是一个@component
@Configuration
@ComponentScan("com.tian")
//@Import(MyConfig1.class) 合并配置类,相当于xml中的import
public class MyConfig {
   //注册一个bean，相当于一个bean标签
   //方法的名字相当于id属性
   //返回值类型，相当于class属性
   //返回值就是要注入到bean中的对象
   @Bean(name = "user1")
   @Scope(value = "singleton")
   public User getUser() {
      return new User();
   }
}
```

> 测试类

```java
import com.tian.config.MyConfig;
import com.tian.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
   public static void main(String[] args) {
      ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
      User user = context.getBean("user1", User.class);
      System.out.println(user.getName());
   }
}
```

# 11、代理模式

**中介**

> 为什么学习代理模式

![image-20210808200209343](https://gitee.com/tianzhendong/img/raw/master//images/image-20210808200209343.png)

在业务增删改时，为了避免更改底层的代码

代理模式是SpringAop的底层

**SpringAOP的底层，面试重点**

> 分类

* 静态代理
* 动态代理



## 静态代理

租房：中介，房东，客户，出租房接口（中介和房东的）

>  角色分析

* 抽象角色：一般会使用接口或者抽象类解决
* 真实角色：被代理的角色，房东
* 代理角色：代理真实角色，中介，代理真实角色后，一般会做一些附加操作
* 客户：访问代理对象的角色

> 抽象接口

```java
package com.tian.demo1;

public interface Rent {
   public void rent();
}
```

> 实现类

```java
package com.tian.demo1;
public class LandLord implements Rent{
   @Override
   public void rent() {
      System.out.println("房子租出去了");
   }
}
```

> 代理

```java
package com.tian.demo1;
public class Proxy implements Rent{
   private Rent landLord;

   public Proxy(LandLord landLord) {
      this.landLord = landLord;
   }

   public Proxy() {
   }

   @Override
   public void rent() {
      landLord.rent();
   }
}
```

> 客户端

```java
package com.tian.demo1;
public class Client {
   public static void main(String[] args) {
      Proxy proxy = new Proxy(new LandLord());
      proxy.rent();
   }
}
```

> 优点

* 可以使真实角色的操作更加纯粹，不用去关注一些公共的业务
* 公共业务交给代理，实现了业务的分工
* 公共业务发生扩展时，方便集中管理

> 缺点

* *代理类和委托类实现了相同的接口，代理类通过委托类实现了相同的方法。这样就出现了大量的代码重复。如果接口增加一个方法，除了所有实现类需要实现这个方法外，所有代理类也需要实现此方法。增加了代码维护的复杂度。*
* *代理对象只服务于一种类型的对象，如果要服务多类型的对象。势必要为每一种对象都进行代理，静态代理在程序规模稍大时就无法胜任了。*

## 动态代理

每个代理类只能为一个接口服务，这样程序开发中必然会产生许多的代理类，为了弥补静态代理的缺点：一个真实角色就会产生一个代理，代码量翻倍，开发效率降低

> 概述

动态代理是在运行时，通过反射机制实现动态代理，并且能够代理各种类型的对象

* 角色和静态代理一样
* 动态代理的代理类是动态生成的，不是直接写好的

**分类**

* 基于接口的动态代理——JDK动态代理，这里使用这种
* 基于类——cglib
* java字节码——javasist

> JDK动态代理

在Java中要想实现动态代理机制，需要java.lang.reflect.InvocationHandler接口和 java.lang.reflect.Proxy 类的支持

* Proxy类：生成动态代理实例
* InvocationHandler接口：调用处理程序并返回一个结果

> InvocationHandler接口

每一个代理实例都有一个关联的调用处理程序，InvocationHandler是由代理实例的调用处理程序实现的接口

方法：invoke（object， object[])

```java
//Object proxy:被代理的对象
//Method method:要调用的方法
//Object[] args:方法调用时所需要参数
public interface InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
```



> Proxy类

有个静态方法，可以创建动态代理类的实例

```java
//CLassLoader loader:类的加载器
//Class<?> interfaces:得到全部的接口
//InvocationHandler h:得到InvocationHandler接口的子类的实例
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException
```

> 动态创建代理对象的类

* 动态代理类只能代理接口（不支持抽象类），代理类都需要实现InvocationHandler类，实现invoke方法
* invoke方法就是**调用被代理接口的所有方法时需要调用的**，该invoke方法返回的值是被代理接口的一个实现类

```java
//动态代理类只能代理接口（不支持抽象类），代理类都需要实现InvocationHandler类，实现invoke方法。
//invoke方法就是调用被代理接口的所有方法时需要调用的，该invoke方法返回的值是被代理接口的一个实现类   
public class LogHandler implements InvocationHandler {
	// 目标对象
	private Object targetObject;
	//绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。     
	public Object newProxyInstance(Object targetObject){
		this.targetObject=targetObject;
		//该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例  
		//第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
		//第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
		//第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
		//根据传入的目标返回一个代理对象
		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
				targetObject.getClass().getInterfaces(),this);
	}
	@Override
	//关联的这个实现类的方法被调用时将被执行
	/*InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，args表示方法的参数*/
    //invoke方法中实现了代理类要扩展的公共功能。
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        //代理扩展逻辑,实现代理类要扩展的公共功能
        System.out.println("proxy do");
 
        return method.invoke(targetObject, args);
    } 
}
```

> 优点

* 可以使真实角色的操作更加纯粹，不用去关注一些公共的业务
* 公共业务交给代理，实现了业务的分工
* 公共业务发生扩展时，方便集中管理
* 一个动态代理类代理的是一个接口，一般就是对应一个业务
* 一个动态代理类可以代理多个类，只要是实现了同一个接口即可

# 12、AOP

## 简介

> 什么是AOP

**面向切面编程**，AOP是能够让我们在不影响原有功能的前提下，为软件**横向扩展**功能。 那么横向扩展怎么理解呢，我们在WEB项目开发中，通常都遵守三层原则，包括控制层（Controller）->业务层（Service）->数据层（dao）,那么从这个结构下来的为纵向，它具**体的某一层就是我们所说的横向**。我们的AOP就是可以作用于这某一个横向模块当中的所有方法。

![](https://gitee.com/tianzhendong/img/raw/master//images/20210808215619.png)



> 和oop区别

AOP和OOP的区别：AOP是OOP的补充，当我们需要为多个对象引入一个公共行为，比如日志，操作记录等，就需要在每个对象中引用公共行为，这样程序就产生了大量的重复代码，使用AOP可以完美解决这个问题。

> AOP在Spring中的作用

**提供声明式事务，允许用户自定义切面**

- 横切关注点：跨越应用程序多个模块的方法或功能，即：与我们业务逻辑无关的，但我们需要关注的部分，如日志，安全，缓存，事务等
- 切面：拦截器类，其中会定义切点以及通知
- 切点：具体拦截的某个业务点
- 通知：切面当中的方法，声明通知方法在目标业务层的执行位置，通知类型如下：
  - 前置通知：@Before 在目标业务方法执行之前执行
  - 后置通知：@After 在目标业务方法执行之后执行
  - 返回通知：@AfterReturning 在目标业务方法返回结果之后执行
  - 异常通知：@AfterThrowing 在目标业务方法抛出异常之后
  - 环绕通知：@Around 功能强大，可代替以上四种通知，还可以控制目标业务方法是否执行以及何时执行

## 方式1：使用Spring的API接口实现AOP

> 导包：注意

```xml
<dependencies>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.7</version>
    </dependency>
</dependencies>
```

> 接口

```java
public interface UserService {
   public void add();
   public void delete();
   public void update();
   public void selete();
}
```

> 接口实现类

```java
public class UserServiceImpl implements UserService{
   @Override
   public void add() {
      System.out.println("add");
   }

   @Override
   public void delete() {
      System.out.println("delete");
   }

   @Override
   public void update() {
      System.out.println("update");
   }

   @Override
   public void selete() {
      System.out.println("selete");
   }
}
```

> 增强方法log：注意

```java
package com.tian.log;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Component
public class Log implements MethodBeforeAdvice {

   @Override
   //method:要执行的目标对象的方法
   //objects: 参数
   //target：目标对象
   public void before(Method method, Object[] args, Object target) throws Throwable {
      System.out.println("目标对象为：" + target.getClass().getName());
      System.out.println("执行的方法为：" + method.getName());
      for (int i = 0; i < args.length; i++) {
         System.out.println("第"+i+"个参数为："+args[i]);
      }
   }
}
```

> xml配置：注意

```xml
<!--注册bean-->
<bean id="userService" class="com.tian.service.UserServiceImpl"/>
<bean id="log" class="com.tian.log.Log"/>
<!--方式1，使用原生API接口-->
<!--配置aop，需要导入aop约束-->
<aop:config>
    <!--切入点, expression是表达式,execution(要执行的位置 * * * *)-->
    <aop:pointcut id="pointcut" expression="execution(* com.tian.service.UserServiceImpl.*(..))"/>
    <!--执行环绕增加-->
    <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
</aop:config>
```

> 测试：注意

```java
import com.tian.service.UserService;
import com.tian.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MyTest {
   public static void main(String[] args) {
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
      //动态代理代理的是接口
      UserService userService = context.getBean("userService", UserService.class);
      userService.add();
   }
}
```

## 方式2：自定义切入类

> 切入类

```java
public class DiyPointCut {

    public void before() {
        System.out.println("---------方法执行前---------");
    }

    public void after() {
        System.out.println("---------方法执行后---------");
    }
}
```

> 配置

```xml
<bean id="userservice" class="com.service.UserServiceImpl"/>
<bean id="diy" class="com.diy.DiyPointCut"></bean>
<aop:config> <!--第二种方式：使用AOP的标签实现-->
    <aop:aspect ref="diy">
        <aop:pointcut id="diyPonitcut" expression="execution(* com.service.UserServiceImpl.*(..))"/>
         <!-- pointcut-ref关联的切入点 , method切入的方法  -->
        <aop:before pointcut-ref="diyPonitcut" method="before"/>
        <aop:after pointcut-ref="diyPonitcut" method="after"/>
    </aop:aspect>
</aop:config>
```

## 方式3：注解

> 增强类

```java
//声明该类是一个切面
@Aspect
public class AnnotationPointCut {

    //声明前置方法
    @Before("execution(* com.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("这是使用注解的前置增强");
    }

    //声明后置方法
    @After("execution(* com.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("使用注解的后置增强");
    }


    //环绕增强的优先级更高
    @Around("execution(* com.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
        System.out.println("签名:" + jp.getSignature());
        //执行目标方法proceed
        Object proceed = jp.proceed();
        System.out.println("环绕后");
        System.out.println("proceed对象:"+proceed);
    }
}

```

> 配置

```xml
    <bean id="userservice" class="com.service.UserServiceImpl"/>
    <bean id="annotationPointcut" class="com.diy.AnnotationPointCut"/>
    <aop:aspectj-autoproxy/>
```

# 13、整合Mybatis

## 步骤：

1. 导入jar包
   - junit单元测试
   - mybatis
   - mysql
   - spring
   - aop
   - mybatis-spring（新包，用于整合）

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.25</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.7</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.9</version>
    </dependency>
    <!--spring操作数据库-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.3.9</version>
    </dependency>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.7</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.2</version>
    </dependency>
</dependencies>
```

2. 编写配置文件

3. 测试

## 回忆mybatis

数据：

![image-20210810222253418](https://gitee.com/tianzhendong/img/raw/master//images/image-20210810222253418.png)

1. 编写实体类

```java
package com.tian.pojo;

/**
 * @program: SpringStudy
 * @description:
 * @author: TianZD
 * @create: 2021-08-10 21:00
 **/
public class User {
   private int id;
   private String name;
   private String password;

   @Override
   public String toString() {
      return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            '}';
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public User() {
   }

   public User(int id, String name, String password) {
      this.id = id;
      this.name = name;
      this.password = password;
   }
}
```

2. 编写核心配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <package name="com.tian"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSl=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=GMT"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="com/tian/dao/UserMapper.xml"/>
    </mappers>
</configuration>
```

3. 编写核心配置类

```java
package com.tian.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: SpringStudy
 * @description:
 * @author: TianZD
 * @create: 2021-08-10 21:07
 **/
public class MybatisUtils {
   public static SqlSessionFactory sqlSessionFactory;
   static {
      try {
         String resources = "mybatis-config.xml";
         InputStream inputStream = Resources.getResourceAsStream(resources);
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   public static SqlSession getSqlSession() {
      return sqlSessionFactory.openSession();
   }
}
```

4. 编写接口

```java
package com.tian.dao;

import com.tian.pojo.User;

import javax.sound.midi.VoiceStatus;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringStudy
 * @description:
 * @author: TianZD
 * @create: 2021-08-10 21:18
 **/
public interface UserMapper {

   public int insert(User user);

   public int delete(int id);

   public int update(User user);

   public User select(int id);
}
```

5. 编写Mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.tian.dao.UserMapper">
    <resultMap id="map" type="User">
        <result column="pwd" property="password"/>
    </resultMap>

    <select id="select" resultType="user">
        select *
        from mybatis.user;
    </select>

    <delete id="delete" parameterType="int">
        delete
        from mybatis.user
        where id = #{id};
    </delete>

    <update id="update" parameterType="map">
        update mybatis.user
        set name = #{name},pwd = #{password}
        where id =#{id};
    </update>

    <insert id="insert" parameterType="map">
        insert into mybatis.user (id, name, pwd)
        values (#{id},#{name},#{password});
    </insert>




</mapper>
```

6. 测试

## Mybatis-Spring

http://mybatis.org/spring/zh/

* 不变内容：

实体类User.class

UserMapper.interface接口

* 编写数据源

```xml
<!--dataSource-->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url"
              value="jdbc:mysql://localhost:3306/mybatis?useSSl=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=GMT"/>
    <property name="username" value="root"/>
    <property name="password" value="123456"/>
</bean>
```

* SqlSessionFactory

```xml
<!--sqlSessionFactory-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <!--绑定mybatis-config.xml-->
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath:com/tian/dao/UserMapper.xml"/>
    <property name="typeAliasesPackage" value="com.tian"/>
</bean>
```

* SqlSessionTemplate

```xml
<!--SqlSessionTemplate-->
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <!--只能通过构造函数注入-->
    <constructor-arg index="0" ref="sqlSessionFactory"/>
</bean>
```

* 给接口加实现类

```java
package com.tian.dao;

import com.tian.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * @program: SpringStudy
 * @description:
 * @author: TianZD
 * @create: 2021-08-10 22:44
 **/
public class UserMapperImpl implements UserMapper{
   UserMapper userMapper = null;
   private SqlSessionTemplate sqlSessionTemplate;
   public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
      this.sqlSessionTemplate = sqlSessionTemplate;
   }


   @Override
   public int insert(User user) {
      userMapper = sqlSessionTemplate.getMapper(UserMapper.class);
      return userMapper.insert(user);
   }

   @Override
   public int delete(int id) {
      userMapper = sqlSessionTemplate.getMapper(UserMapper.class);
      return userMapper.delete(id);
   }

   @Override
   public int update(User user) {
      userMapper = sqlSessionTemplate.getMapper(UserMapper.class);
      return userMapper.update(user);
   }

   @Override
   public User select(int id) {
      userMapper = sqlSessionTemplate.getMapper(UserMapper.class);
      return userMapper.select(id);
   }
}
```

* 将实现类注入到spring中

```xml
<bean id="userMapperImpl" class="com.tian.dao.UserMapperImpl">
    <property name="sqlSessionTemplate" ref="sqlSession"/>
</bean>
```

* 测试

```xml
import com.tian.dao.UserMapper;
import com.tian.pojo.User;
import com.tian.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SpringStudy
 * @description:
 * @author: TianZD
 * @create: 2021-08-10 21:33
 **/
public class MyTest {
   @Test
   public void userMapperTest() {
      ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
      UserMapper userMapper = context.getBean("userMapperImpl", UserMapper.class);
      System.out.println(userMapper.select(2));
   }
}
```





## 进一步简化：SqlSessionDaoSupport



`SqlSessionDaoSupport` 是一个抽象的支持类，用来为你提供 `SqlSession`。调用 `getSqlSession()` 方法你会得到一个 `SqlSessionTemplate`，之后可以用于执行 SQL 方法

```java
package com.tian.dao;

import com.tian.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * @program: SpringStudy
 * @description:
 * @author: TianZD
 * @create: 2021-08-10 23:17
 **/
public class UserMapperImpl_2 extends SqlSessionDaoSupport implements UserMapper{
   SqlSession sqlSession = getSqlSession();
   UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

   @Override
   public int insert(User user) {
      return userMapper.insert(user);
   }

   @Override
   public int delete(int id) {
      return userMapper.delete(id);
   }

   @Override
   public int update(User user) {
      return userMapper.update(user);
   }

   @Override
   public User select(int id) {
      return userMapper.select(id);
   }

}
```

注册：略去了sqlsessiontemplate注册

需要注册userMapperimpl2，SqlSessionDaoSupport` 需要通过属性设置一个 `sqlSessionFactory` 或 `SqlSessionTemplate

```xml
<!--dataSource-->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url"
              value="jdbc:mysql://localhost:3306/mybatis?useSSl=true&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=GMT"/>
    <property name="username" value="root"/>
    <property name="password" value="123456"/>
</bean>
<!--sqlSessionFactory-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <!--绑定mybatis-config.xml-->
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath:com/tian/dao/UserMapper.xml"/>
    <property name="typeAliasesPackage" value="com.tian"/>
</bean>

<bean id="userMapperImpl2" class="com.tian.dao.UserMapperImpl_2">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
</bean>
```



# 14、声明式事务

事务分为：

* 声明式事务：AOP实现
* 编程式事务



## 使用声明式事务

> 配置声明式事务

要开启 Spring 的事务处理功能，在 Spring 的配置文件中创建一个 `DataSourceTransactionManager` 对象：

```xml
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <constructor-arg name="dataSource" ref="dataSource" />
</bean>
```



> aop实现事务

```xml
<!--结合AOP实现事务的注入-->
<!--配置事务通知-->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <!--给哪些方法配置事务-->
    <!--配置事务的传播特性：new propagation-->
    <tx:attributes>
        <tx:method name="update" propagation="REQUIRED"/>
        <tx:method name="delete" propagation="REQUIRED"/>
        <tx:method name="insert" propagation="REQUIRED"/>
        <tx:method name="select" read-only="true"/>
    </tx:attributes>
</tx:advice>

<!--配置事务切入-->
<aop:config>
    <aop:pointcut id="txPointcut" expression="execution(* com.tian.dao.*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>
</aop:config>
```

> 事务的传播特性

事务传播特性，就是多个事务方法调用时如何定义方法间事务的传播。Spring 定义了 7 种传播行为：

- propagation_requierd：如果当前没有事务，就新建一个事务，如果已存在一个事务中，加入到这个事务中，这是Spring默认的选择。
- propagation_supports：支持当前事务，如果没有当前事务，就以非事务方法执行。
- propagation_mandatory：使用当前事务，如果没有当前事务，就抛出异常。
- propagation_required_new：新建事务，如果当前存在事务，把当前事务挂起。
- propagation_not_supported：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
- propagation_never：以非事务方式执行操作，如果当前事务存在则抛出异常。
- propagation_nested：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与propagation_required类似的操作。

