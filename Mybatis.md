---
title: Mybatis
tags: java
notebook: JAVA
---

[toc]

# Mybatis

# 1、简介

> **Mybatis**

* MyBatis 是一款优秀的**持久层**框架
* 它支持自定义 SQL、存储过程以及高级映射
* **MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作**
* MyBatis 可以通过简单的 **XML 或注解**来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

获取mybatis

```xml
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>
```

> **持久化**

数据持久化

* 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
* 内存：断电即失
* 数据库、io文件可以进行持久化

为什么需要持久化：有一些对象不能丢失

>  **持久层**

Dao层、Service层、Controller层

# 2、第一个Mybatis程序

**思路：**搭建环境==》导入Mybatis==》编写代码==》测试

> **搭建环境**

1. **搭建数据库**

```sql
CREATE DATABASE mybatis;
CREATE TABLE `user`(
	`id` INT(20) NOT NULL,
    `name` VARCHAR(30) DEFAULT NULL,
    `pwd` VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY(`id`)
)ENGINE=INNODB DEFAULT CHARSET=UTF8;
```

2. **新建maven项目**
   1. 普通maven项目
   2. 删除src目录，使其变为父工程
   3. 导入依赖

```xml
<!--导入依赖-->
<dependencies>
    <!--mysql驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.25</version>
    </dependency>
    <!--mybatis-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.7</version>
    </dependency>
    <!--junit-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

> **创建模块**

由于在上述步骤中配置了父工程，并且父工程已经导入了依赖，因此所有的子模块不需要再次引入依赖

子模块的xml配置文件中多了<**parent**>

```xml
<parent>
    <artifactId>MybatisStudy</artifactId>
    <groupId>org.example</groupId>
    <version>1.0-SNAPSHOT</version>
</parent>
```

> **编写mybatis核心配置文件，在resource目录下创建mybaits-config.xml**

XML 配置文件中包含了对 MyBatis 系统的核心设置，包括获取数据库连接实例的数据源（DataSource）以及决定事务作用域和控制方式的事务管理器（TransactionManager）。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
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

</configuration>
```

> **编写mybatis工具类**

* **从xml配置中获取 SqlSessionFactory 实例**

* **从 SqlSessionFactory 中获取 SqlSession**

每个基于 MyBatis 的应用都是以一个 **SqlSessionFactory 的实例为核心**的。SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。而 SqlSessionFactoryBuilder 则**可以从 XML 配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例**。

从 XML 文件中构建 SqlSessionFactory 的实例非常简单，建议使用类路径下的资源文件进行配置。 但也可以使用任意的输入流（InputStream）实例，比如用文件路径字符串或 file:// URL 构造的输入流。MyBatis 包含一个名叫 Resources 的工具类，它包含一些实用方法，使得从类路径或其它位置加载资源文件更加容易。

 **SqlSession中包括了操作数据库的方法**

```java
package com.tian.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;

/**
 * @program: MybatisStudy
 * @description: mybatis配置类
 * @author: TianZD
 * @create: 2021-08-06 21:44
 **/
public class MybatisUtils {
   private static SqlSessionFactory sqlSessionFactory = null;
   static {
      try {
         /*以下三句话是固定的
         用来从xml配置中获取sqlSessionFactory对象
         * 1. 加载配置文件，maven中可以直接读取到resource中的配置文件
         * 2. 获取输入流实例
         * 3. 从XML配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例*/
         String resource = "mybatis-config.xml";
         InputStream inputStream = Resources.getResourceAsStream(resource);
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   /**
   * @Description: 从 SqlSessionFactory对象 中获取 SqlSession实例
   * @Author: TianZD
   * @Date: 2021/8/6 22:01
   * @Param: [] 
   * @Return: org.apache.ibatis.session.SqlSession
   */
   public static SqlSession getSqlSession() {
      return sqlSessionFactory.openSession();
   }

}
```

> **编写代码**

* **实体类**

```java
package com.tian.pojo;

/**
 * @program: MybatisStudy
 * @description: 实体类
 * @author: TianZD
 * @create: 2021-08-06 22:04
 **/
public class User {
	private int id;
	private String name;
	private String pwd;

	public User() {
	}

	public User(int id, String name, String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pwd='" + pwd + '\'' +
				'}';
	}
}
```

* **Dao接口**

```java
package com.tian.dao;

import com.tian.pojo.User;

import java.util.List;

/**
 * @program: MybatisStudy
 * @description: Dao接口，后面用mapper代替，两者等价
 * @author: TianZD
 * @create: 2021-08-06 22:07
 **/
public interface UserDao {
   List<User> getUserList();
}
```

* **接口实现类**

以前采用的方式是创建一个接口实现类，现在采用xml配置文件的方式

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.tian.dao.UserDao">
    <!--查询语句
    id对应Dao/Mapper接口中的方法
    第二个属性使用resultType或者resultMap
    resultType对应sql语句返回的结果类型，这里对应User对象，使用全限定名-->
    <select id="getUserList" resultType="com.tian.pojo.User">
        select * from mybatis.user
    </select>

</mapper>
```

* **Junit测试**

输入：

```bash
User{id=1, name='tian1', pwd='123456'}
User{id=2, name='tian2', pwd='1234567'}
User{id=3, name='tian3', pwd='1234567'}
```



**可能错误：**

* org.apache.ibatis.binding.BindingException: Type interface com.tian.dao.UserDao is not known to the MapperRegistry.

在mybatis.config.xml文件中没有配置mapper.xml

增加如下：

注意，路径用斜杠隔开

```xml
<mappers>
    <mapper resource="com/tian/dao/UserMapper.xml"/>
</mappers>
```

* 错误2：

Cause: org.apache.ibatis.builder.BuilderException: Error parsing SQL Mapper Configuration. Cause: java.io.IOException: Could not find resource com/tian/dao/UserMapper.xml

资源过滤原因

maven由于约定大于配置，可能遇到配置文件无法被导出或者生效的问题，解决方案：

在父工程pop.xml中配置resource，防治资源导出失败

```xml
<build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
```

# 3、生命周期

> **SqlSessionFactoryBuilder**

这个类可以被实例化、使用和丢弃，**一旦创建了 SqlSessionFactory，就不再需要它了**。 因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）。 你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，但最好还是不要一直保留着它，以保证所有的 XML 解析资源可以被释放给更重要的事情。

> **SqlSessionFactory**

SqlSessionFactory **一旦被创建就应该在应用的运行期间一直存在**，没有任何理由丢弃它或重新创建另一个实例。 使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏习惯”。因此 SqlSessionFactory 的最佳作用域是应用作用域。 有很多方法可以做到，**最简单的就是使用单例模式或者静态单例模式。**

> **SqlSession**

每个线程都应该有它自己的 SqlSession 实例。**SqlSession 的实例不是线程安全的，因此是不能被共享的**，所以它的最佳的作用域是请求或方法作用域。 绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。 也绝不能将 SqlSession 实例的引用放在任何类型的托管作用域中，比如 Servlet 框架中的 HttpSession。 如果你现在正在使用一种 Web 框架，考虑将 SqlSession 放在一个和 HTTP 请求相似的作用域中。 换句话说，**每次收到 HTTP 请求，就可以打开一个 SqlSession，返回一个响应后，就关闭它**。 这个关闭操作很重要，为了确保每次都能执行关闭操作，你应该把这个关闭操作放到 finally 块中。 下面的示例就是一个确保 SqlSession 关闭的标准模式：

```java
try (SqlSession session = sqlSessionFactory.openSession()) {
  // 你的应用逻辑代码
}
```

