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

# 2、入门

## 第一个Mybatis程序

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

XML 配置文件中包含了**对 MyBatis 系统的核心设置**，包括**获取数据库连接实例的数据源（DataSource）以及决定事务作用域和控制方式的事务管理器**（TransactionManager）。

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
	<mappers>
    <mapper resource="com/tian/dao/UserMapper.xml"/>
	</mappers>
</configuration>
```

> **编写mybatis工具类**

* **从xml配置中获取 SqlSessionFactory 实例**

* **从 SqlSessionFactory 中获取 SqlSession**

每个基于 MyBatis 的应用都是以一个 **SqlSessionFactory 的实例为核心**的。SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。而 SqlSessionFactoryBuilder 则**可以从 XML 配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例**。

从 XML 文件中构建 SqlSessionFactory 的实例非常简单，建议使用类路径下的资源文件进行配置。 但也可以使用任意的输入流（InputStream）实例，比如用文件路径字符串或 file:// URL 构造的输入流。MyBatis 包含一个名叫 Resources 的工具类，它包含一些实用方法，使得从类路径或其它位置加载资源文件更加容易。

 **SqlSession中包括了操作数据库的方法**

```java
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
	//查询全部
	List<User> getUserList();

	//根据id查询
	User getUserById(int id);

	//insert插入
	int addUser(User user);

	//update修改
	int updateUser(User user);

	//删除用户
	int deleteUser(int id);
}
```

* **接口实现类Mapper**

以前采用的方式是创建一个**接口实现类，现在采用xml**配置文件的方式

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

    <!--根据id查询-->
    <select id="getUserById" parameterType="int" resultType="com.tian.pojo.User">
        select * from mybatis.user where id = #{id}
    </select>

    <!--insert插入-->
    <insert id="addUser" parameterType="com.tian.pojo.User">
        insert into mybatis.user (id, name, pwd)
        values (#{id}, #{name}, #{pwd});
    </insert>

    <!--修改update-->
    <update id="updateUser" parameterType="com.tian.pojo.User">
        update mybatis.user
        set name = #{name},
            pwd  = #{pwd}
        where id = #{id};
    </update>
    
    <!--删除-->
    <delete id="deleteUser" parameterType="int">
        delete
        from mybatis.user
        where id = #{id};
    </delete>

</mapper>
```

* **Junit测试**



```java
package com.tian.dao;

import com.tian.pojo.User;
import com.tian.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @program: MybatisStudy
 * @description: 测试
 * @author: TianZD
 * @create: 2021-08-06 22:56
 **/
public class UserDaoTest {
	@Test
	public void test(){
		//1. 获得SqlSession对象
		SqlSession sqlSession = MybatisUtils.getSqlSession();
		//2. 执行sql、获取结果、输出
		//方式1，getMapper
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		List<User> userList = userDao.getUserList();
		for (User user : userList) {
			System.out.println(user);
		}

		//3. 关闭SqlSession
		sqlSession.close();
	}

	@Test
	public void getUserById(){
	//	1. 获得SqlSession对象
		SqlSession sqlSession = MybatisUtils.getSqlSession();
	//	2. 执行sql
		UserDao mapper = sqlSession.getMapper(UserDao.class);
		User userById = mapper.getUserById(2);
		System.out.println(userById);
	//	3. 关闭
		sqlSession.close();
	}

	@Test
	public void addUserTest(){
	//	1.从配置类中获得SqlSession对象
		SqlSession sqlSession = MybatisUtils.getSqlSession();
	//	2.sql语句
		UserDao mapper = sqlSession.getMapper(UserDao.class);
		int tian4 = mapper.addUser(new User(4, "tian4", "123456"));
		if (tian4 > 0) {
			System.out.println("插入成功");
		}

	//	增删改需要提交事务
		sqlSession.commit();
	//	3. 关闭
		sqlSession.close();
	}

	@Test
	public void updateUserTest() {
	//	1.获取sqlsession对象
		SqlSession sqlSession = MybatisUtils.getSqlSession();
	//	2.sql
		UserDao mapper = sqlSession.getMapper(UserDao.class);
		int tian4 = mapper.updateUser(new User(4, "tian4", "123123"));
	//	增删改需要提交事务
		sqlSession.commit();
	//	3.关闭
		sqlSession.close();
	}

	@Test
	public void deleteUserTest() {
		//	1.获取sqlsession对象
		SqlSession sqlSession = MybatisUtils.getSqlSession();
		//	2.sql
		UserDao mapper = sqlSession.getMapper(UserDao.class);
		int i = mapper.deleteUser(4);
		//	增删改需要提交事务
		sqlSession.commit();
		//	3.关闭
		sqlSession.close();
	}
}
```

> **总结**

在写完上述以后，后续使用步骤：

1. 在UserDao接口中增加相应的方法
2. 在UserMapper.xml中增加相应的sql语句
3. 在测试方法中增加相应的测试方法

**注意**：增删改需要在关闭之前提交事务

```java
sqlSession.commit();
```

> **可能错误：**

* org.apache.ibatis.binding.BindingException: Type interface com.tian.dao.UserDao is not known to the MapperRegistry.

在mybatis.config.xml文件中没有配置mapper.xml

增加如下：

**注意，路径用斜杠隔开**

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



## 生命周期和作用域

作用域和生命周期类别是至关重要的，因为错误的使用会导致非常严重的**并发问题**

![image-20210807175425111](https://gitee.com/tianzhendong/img/raw/master//images/image-20210807175425111.png)

> **SqlSessionFactoryBuilder**

* **一旦创建了 SqlSessionFactory，就不再需要它了**
* **最佳作用域是方法作用域（也就是局部方法变量）**

这个类可以被实例化、使用和丢弃，**一旦创建了 SqlSessionFactory，就不再需要它了**。 因此 SqlSessionFactoryBuilder 实例的**最佳作用域是方法作用域（**也就是**局部方法变量**）。 你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，但最好还是不要一直保留着它，以保证所有的 XML 解析资源可以被释放给更重要的事情。

> **SqlSessionFactory**

* **说白了可以想象为：数据库连接池**
* **一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例**
* **最佳作用域是应用作用域**
* **最简单的就是使用单例模式或者静态单例模式**

SqlSessionFactory **一旦被创建就应该在应用的运行期间一直存在**，没有任何理由丢弃它或重新创建另一个实例。 使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏习惯”。因此 SqlSessionFactory 的**最佳作用域是应用作用域**。 有很多方法可以做到，**最简单的就是使用单例模式或者静态单例模式。**

> **SqlSession**

* **连接到连接池的一个请求**
* **最佳的作用域是请求或方法作用域**
* **用完之后需要关闭，否则造成资源被占用**

每个线程都应该有它自己的 SqlSession 实例。**SqlSession 的实例不是线程安全的，因此是不能被共享的**，所以它的**最佳的作用域是请求或方法作用域**。 绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。 也绝不能将 SqlSession 实例的引用放在任何类型的托管作用域中，比如 Servlet 框架中的 HttpSession。 如果你现在正在使用一种 Web 框架，考虑将 SqlSession 放在一个和 HTTP 请求相似的作用域中。 换句话说，**每次收到 HTTP 请求，就可以打开一个 SqlSession，返回一个响应后，就关闭它**。 这个关闭操作很重要，为了确保每次都能执行关闭操作，你应该把这个关闭操作放到 finally 块中。 下面的示例就是一个确保 SqlSession 关闭的标准模式：

```java
try (SqlSession session = sqlSessionFactory.openSession()) {
  // 你的应用逻辑代码
}
```

![image-20210807175744673](https://gitee.com/tianzhendong/img/raw/master//images/image-20210807175744673.png)

每一个Mapper代表一个具体的业务



## CRUD

> **namespace**

namespace中的包名要和Dao/Mapper接口的包名保持一致

```xml
<mapper namespace="com.tian.dao.UserDao">
```

绑定时的包名用.不能用/

> **select**

选择、查询语句

1. 编写接口

```java
   //查询全部
   List<User> getUserList();

   //根据id查询
   User getUserById(int id);
```

2. 编写对应的mapper中的sql语句

```xml
<!--查询语句
id对应Dao/Mapper接口中的方法
第二个属性使用resultType或者resultMap
resultType对应sql语句返回的结果类型，这里对应User对象，使用全限定名-->
<select id="getUserList" resultType="com.tian.pojo.User">
    select * from mybatis.user
</select>

<!--根据id查询-->
<select id="getUserById" parameterType="int" resultType="com.tian.pojo.User">
    select * from mybatis.user where id = #{id}
</select>
```

3. 编写测试

```java
@Test
public void test(){
   //1. 获得SqlSession对象
   SqlSession sqlSession = MybatisUtils.getSqlSession();
   //2. 执行sql、获取结果、输出
   //方式1，getMapper
   UserDao userDao = sqlSession.getMapper(UserDao.class);
   List<User> userList = userDao.getUserList();
   for (User user : userList) {
      System.out.println(user);
   }

   //3. 关闭SqlSession
   sqlSession.close();
}

@Test
public void getUserById(){
// 1. 获得SqlSession对象
   SqlSession sqlSession = MybatisUtils.getSqlSession();
// 2. 执行sql
   UserDao mapper = sqlSession.getMapper(UserDao.class);
   User userById = mapper.getUserById(2);
   System.out.println(userById);
// 3. 关闭
   sqlSession.close();
}
```

> **insert**

增删改需要提交事务

1. 编写接口
2. mapper中的sql语句

```xml
<!--insert插入-->
<insert id="addUser" parameterType="com.tian.pojo.User">
    insert into mybatis.user (id, name, pwd)
    values (#{id}, #{name}, #{pwd});
</insert>
```

3. 测试：增删改需要提交事务

```java
//  增删改需要提交事务
   sqlSession.commit();
```

> **update**

1. 编写接口
2. sql语句

```xml
<!--修改update-->
<update id="updateUser" parameterType="com.tian.pojo.User">
    update mybatis.user
    set name = #{name},
        pwd  = #{pwd}
    where id = #{id};
</update>
```

3. 测试：需要提交事务

> **Delete**

1. 接口
2. sql语句

```xml
<!--删除-->
<detete id="deleteUser" parameterType="int">
    delete
    from mybatis.user
    where id = #{id};
</delete>
```

3. 测试：需要提交事务



## 使用Map传参

假如我们的实体类或者数据库中的表、字段或者参数过多，我们应当考虑使用Map

使用User对象时，假如需要修改密码，当字段过多时，sql语句中还需要把其他字段给加上，很麻烦

使用map时：

1. 接口

```java
int updateUser2(Map<String, Object> map);
```

2. sql语句

sql语句中传入的参数类型为map

具体传入的参数不需要和数据库以及实体类中的对应，在map.put()中进行对应即可

```xml
<!--使用map-->
<update id="updateUser2" parameterType="map">
    update mybatis.user
    set pwd = #{userPwd}
    where id = #{userId};
</update>
```

3. 测试

```java
@Test
public void updateUser2Test() {
   // 1.获取sqlsession对象
   SqlSession sqlSession = MybatisUtils.getSqlSession();
   // 2.sql
   UserDao mapper = sqlSession.getMapper(UserDao.class);

   // map
   Map<String, Object> map = new HashMap<>();
   map.put("userId", 3);
   map.put("userPwd", "000000");

   int i = mapper.updateUser2(map);

   // 增删改需要提交事务
   sqlSession.commit();
   // 3.关闭
   sqlSession.close();
}
```

## 模糊查询

模糊查询需要防止sql注入

1. java代码执行的时候，传入通配符% %

```java
List<User> u = mapper.getUserLike("%tian%");
```

2. mapper中的sql语句使用where 字段 like 加%，会导致sql注入

```xml
select * from mybatis.user where name like "%"#{value}"%"
```



# 3、配置解析

## 核心配置文件mybatis-config.xml

配置文件包含了会深深影响Mybatis行为的设置和属性信息

* **属性properties**
* **设置settings**
* **类型别名typeAliases**
* **环境配置environments**
  * environment（环境变量）
    - transactionManager（事务管理器）
    - dataSource（数据源）
* **映射器mappers**
* 了解
  * 类型处理器typeHandlers
  * 对象工厂objectFactory
  * 插件plugins
  * 数据库厂商标识databaseProvider

## 环境配置environments

```xml
	<environments default="development">
        <!--通过id选择默认的事务管理器，默认jdbc，还有一个是MANAGED-->
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!--配置数据源，UNPOOLED|POOLED|JNDI，默认采用的数据源，POOLED-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF8&amp;serverTimezone=GMT"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>
```

**MyBatis 可以配置成适应多种环境，通过id选择使用哪一个**

**每个 SqlSessionFactory 实例只能选择一种环境**



* **事务管理器**

在 MyBatis 中有两种类型的事务管理器（也就是 type="[**JDBC|MANAGED**]"），**默认使用JDBC：**

* **数据源**

有三种内建的数据源类型（也就是 type="[UNPOOLED|POOLED|JNDI]"），**默认使用pooled**

**UNPOOLED**– 这个数据源的实现会每次请求时打开和关闭连接。虽然有点慢，但对那些数据库连接可用性要求不高的简单应用程序来说，是一个很好的选择。

**POOLED**– 这种数据源的实现利用“池”的概念将 JDBC 连接对象组织起来，避免了创建新的连接实例时所必需的初始化和认证时间。 这种处理方式很流行，能使并发 Web 应用快速响应请求。

连接数据库，数据库连接池：dbcp、c3p0、druid，用完会回收

**JNDI** – 这个数据源实现是为了能在如 EJB 或应用服务器这类容器中使用，容器可以集中或在外部配置数据源，然后放置一个 JNDI 上下文的数据源引用

## 属性properties

**通过properties属性涉嫌引用配置文件**

属性都是可以外部配置且可以动态替换的，既可以在典型的java属性文件中配置，也可可以通过properties元素的子元素来传递，【db.properties】

**通过外部配置db.properties**

1. 编写配置文件：db.properties:

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=false&useUnicode=true&characterEncoding=UTF-8
username=root
password=123456
```

2. xml中引入配置文件

在xml中，所有的标签都可以规定其顺序，properties=》settings=》typerAliases。。properties只能放在最上面

* 中间可以设置属性，设置用户名和密码
* 如果两个设置的属性中和db.properties中有同一个字段，优先使用外部的db.properties

```xml
<!--引入外部配置文件-->
<properties resource="db.properties">
</properties>
```

3. 在整个配置文件中用来替换需要动态配置的属性值

```xml
<dataSource type="POOLED">
    <property name="driver" value="${driver}"/>
    <property name="url" value="${url}"/>
    <property name="username" value="${username}"/>
    <property name="password" value="${password}"/>
</dataSource>
```

## 类型别名typerAliases

类型别名可为 Java 类型设置一个**缩写名字**。 它仅用于 XML 配置，意在**降低冗余的全限定类名**书写。

```xml
<!--别名-->
<typeAliases>
    <typeAlias type="com.tian.pojo.User" alias="User"></typeAlias>
</typeAliases>
```

也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean，比如：

在没有注解的情况下，会使用 Bean 的首字母小写的非限定类名来作为它的别名。 比如 `domain.blog.Author` 的别名为 `author`

```xml
    <typeAliases>
        <package name="com.tian.pojo"/>
    </typeAliases>
```

使用`com.tian.pojo.User`时只需要用`user`即可

**在实体类比较少的时候，使用第一种，实体类多的时候使用第二种，第一种可以自定义别名，第二种可以通过在实体类上增加注解来自定义别名，如下:**

```java
@Alias("newName")
public class User{
    ...
}
```

## 设置settings

这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为



|    cacheEnabled    | 全局性地开启或关闭所有映射器配置文件中已配置的任何缓存。     | true \| false                                                | true   |
| :----------------: | ------------------------------------------------------------ | ------------------------------------------------------------ | ------ |
| lazyLoadingEnabled | 延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置 `fetchType` 属性来覆盖该项的开关状态。 | true \| false                                                | false  |
|      logImpl       | 指定 MyBatis 所用日志的具体实现，未指定时将自动查找。        | SLF4J \| LOG4J \| LOG4J2 \| JDK_LOGGING \| COMMONS_LOGGING \| STDOUT_LOGGING \| NO_LOGGING | 未设置 |

一个配置完整的 settings 元素的示例如下：

```xml
<settings>
  <setting name="cacheEnabled" value="true"/>
  <setting name="lazyLoadingEnabled" value="true"/>
  <setting name="multipleResultSetsEnabled" value="true"/>
  <setting name="useColumnLabel" value="true"/>
  <setting name="useGeneratedKeys" value="false"/>
  <setting name="autoMappingBehavior" value="PARTIAL"/>
  <setting name="autoMappingUnknownColumnBehavior" value="WARNING"/>
  <setting name="defaultExecutorType" value="SIMPLE"/>
  <setting name="defaultStatementTimeout" value="25"/>
  <setting name="defaultFetchSize" value="100"/>
  <setting name="safeRowBoundsEnabled" value="false"/>
  <setting name="mapUnderscoreToCamelCase" value="false"/>
  <setting name="localCacheScope" value="SESSION"/>
  <setting name="jdbcTypeForNull" value="OTHER"/>
  <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
</settings>
```

## 其他配置

* 类型处理器typeHandlers
* 对象工厂objectFactory
* 插件plugins
  * mybatis-generator-core
  * mybatis-plus：一个增强工具，简化mybatis
  * 通用mapper
* 数据库厂商标识databaseProvider

## 映射器mappers

MapperRegistry：注册绑定我们的Mapper文件

MyBatis 的行为已经由上述元素配置完了，我们现在就要来定义 SQL 映射语句了。 但首先，我们**需要告诉 MyBatis 到哪里去找到这些语句**。 在自动查找资源方面，Java 并没有提供一个很好的解决方案，所以最好的办法是直接告诉 MyBatis 到哪里去找映射文件。 你可以使用相对于类路径的资源引用，或完全限定资源定位符（包括 `file:///` 形式的 URL），或类名和包名等。

**maven中resources文件夹下的文件在编译后，都放在了根目录下**

> **方式1 ：推荐使用**

```xml
<!-- 使用相对于类路径的资源引用 -->
    <mappers>
        <mapper resource="com/tian/dao/UserMapper.xml"/>
    </mappers>
```

> **方式2：使用class文件**

**注意**：

* 接口和他的Mapper配置文件必须同名
* 接口和他的Mapper配置文件必须在同一个包下（Mapper配置文件可以在maven的resources目录下）

```xml
<!-- 使用映射器接口实现类的完全限定类名 -->
    <mappers>
        <mapper class="com.tian.dao.UserMapper"/>
    </mappers>
```

> **方式3**：使用扫描包进行绑定注入

**注意：**

* 接口和他的Mapper配置文件必须同名
* 接口和他的Mapper配置文件必须在同一个包下

```xml
<!-- 将包内的映射器接口实现全部注册为映射器 -->
    <mappers>
        <package name="com.tian.dao"/>
    </mappers>
```

# 4、解决属性名和字段名不一致

> **问题**

如：数据库中的字段名为pwd，实体类中的属性名为password

User:

```java
public class User {
   private int id;
   private String name;
   private String password;
    ...
}
```

Mapper:

```xml
<select id="getUserById" parameterType="int" resultType="com.tian.pojo.User">
    select * from mybatis.user where id = #{id}
</select>
```

test:

```java
@Test
public void getUserByIdTes() {
   SqlSession sqlSession = MybatisUtils.getSqlSession();
   UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   User userById = mapper.getUserById(2);
   System.out.println(userById);
}
```

查询得到输出：

`User{id=2, name='tian2', password='null'}`

分析：

由于数据库中的字段为pwd，在测试查询时，传入的参数为password，导致查询不到

> **解决方法：**

* 起别名

在mapper中修改sql语句：

```xml
<select id="getUserById" parameterType="int" resultType="com.tian.pojo.User">
    select id,name,pwd as password
    from mybatis.user
    where id = #{id};
</select>
```

* resultMap

## resultMap

> 结果集映射

* `resultMap`元素是Mybatis中最重要最强大的元素
* 设计思想是：对于简单的语句根本不需要配置显式的结果集映射，对于复杂的语句，只需要描述他们的关系就可以了

> 简单的结果集映射

```xml
<!--结果集映射-->
<resultMap id="UserMap" type="User">
    <!--column：数据库中的字段，property：实体类中的属性-->
    <result column="pwd" property="password"/>
</resultMap>
<!--根据id查询-->
<select id="getUserById" resultMap="UserMap">
    select * from mybatis.user where id = #{id}
</select>
```

> 复杂

一对多，多对一



# 5、日志

## 日志工厂

> **介绍**

如果一个数据库操作出现了异常，需要排错，需要日志

曾经用：sout、debug

现在：日志工厂

mybatis提供的：

* SLF4J 

* **LOG4J** 
* LOG4J2 
* JDK_LOGGING
* COMMONS_LOGGING
* **STDOUT_LOGGING**
* NO_LOGGING

具体使用哪一个，在mybatis-config.xml中的settings中设置，默认不使用

![image-20210807195224236](https://gitee.com/tianzhendong/img/raw/master//images/image-20210807195224236.png)

> **标准的日志工厂实现**

使用`STDOUT_LOGGING`不需要导包

* 在mybatis-config.xml中配置日志

```xml
<settings>
    <!--name必须为logImpl，I为大写，value需要使用mybatis提供的哪些，不能有空格-->
            <!--标准的日志工厂实现-->
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

* 配置后输出：

```bash
Logging initialized using 'class org.apache.ibatis.logging.stdout.StdOutImpl' adapter.
Class not found: org.jboss.vfs.VFS
JBoss 6 VFS API is not available in this environment.
Class not found: org.jboss.vfs.VirtualFile
VFS implementation org.apache.ibatis.io.JBoss6VFS is not valid in this environment.
Using VFS adapter org.apache.ibatis.io.DefaultVFS
Find JAR URL: file:/C:/javaCode/MybatisStudy/Mybatis-02/target/classes/com/tian/pojo
Not a JAR: file:/C:/javaCode/MybatisStudy/Mybatis-02/target/classes/com/tian/pojo
Reader entry: User.class
Listing file:/C:/javaCode/MybatisStudy/Mybatis-02/target/classes/com/tian/pojo
Find JAR URL: file:/C:/javaCode/MybatisStudy/Mybatis-02/target/classes/com/tian/pojo/User.class
Not a JAR: file:/C:/javaCode/MybatisStudy/Mybatis-02/target/classes/com/tian/pojo/User.class
Reader entry: ����   < :
Find JAR URL: file:/C:/javaCode/MybatisStudy/Mybatis-01/target/classes/com/tian/pojo
Not a JAR: file:/C:/javaCode/MybatisStudy/Mybatis-01/target/classes/com/tian/pojo
Reader entry: User.class
Listing file:/C:/javaCode/MybatisStudy/Mybatis-01/target/classes/com/tian/pojo
Find JAR URL: file:/C:/javaCode/MybatisStudy/Mybatis-01/target/classes/com/tian/pojo/User.class
Not a JAR: file:/C:/javaCode/MybatisStudy/Mybatis-01/target/classes/com/tian/pojo/User.class
Reader entry: ����   < :
Checking to see if class com.tian.pojo.User matches criteria [is assignable to Object]
Checking to see if class com.tian.pojo.User matches criteria [is assignable to Object]
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
PooledDataSource forcefully closed/removed all connections.
Opening JDBC Connection
Created connection 1446983876.
Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@563f38c4]
==>  Preparing: select * from mybatis.user where id = ?
==> Parameters: 2(Integer)
<==    Columns: id, name, pwd
<==        Row: 2, tian2, 1234567
<==      Total: 1
User{id=2, name='tian2', password='1234567'}
```



## Log4j

**注**：需要导包

> **什么是log4j**

* Log4j是Apache的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是控制台、文件、GUI组件
* 我们也可以控制每一条日志的输出格式
* 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程
* 可以通过一个配置文件来灵活地进行配置，而不需要修改应用的代码。

> **使用log4j**

1. 导包

```xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

2. 新增配置文件`log4j.properties`

网上找即可

```properties
#将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
log4j.rootLogger=DEBUG,console,file

#控制台输出的相关设置
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=[%c]-%m%n

#文件输出的相关设置
log4j.appender.file = org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/tian.log
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n

#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```

3. 配置log4j为日志的实现

```XML
<settings>
    <!--name必须为logImpl，I为大写，value需要使用mybatis提供的哪些，不能有空格-->
    <setting name="logImpl" value="LOG4J"/>
</settings>
```

4. **log4j使用**

* 在要使用log4j的类中，导入包`import org.apache.log4j.Logger`
* 获取日志对象，参数为当前类的class

```java
static Logger logger = Logger.getLogger(UserDaoTest.class);
```

* 编写测试代码

日志级别：info\debug\error

```java
package com.tian.dao;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @program: MybatisStudy
 * @description: 测试
 * @author: TianZD
 * @create: 2021-08-06 22:56
 **/
public class UserDaoTest {
	static Logger logger = Logger.getLogger(UserDaoTest.class);
	@Test
	public void testLog4j() {
		//不同的级别
		logger.info("info:进入了testLog4j方法");
		logger.debug("debug:进入了testLog4j方法");
		logger.error("error:进入了testLog4j方法");
	}
}
```

* 输出

在控制台和配置的输出文件中会输出以下：

```bash
[com.tian.dao.UserDaoTest]-info:进入了testLog4j方法
[com.tian.dao.UserDaoTest]-debug:进入了testLog4j方法
[com.tian.dao.UserDaoTest]-error:进入了testLog4j方法
```

# 6、分页

> **为什么要分页**

减少数据的处理量

> **limit分页**

```sql
SELECT * FROM user LIMIT startIndex,pageSize;
```

> 使用mybatis分页

核心sql

1. 接口

```java
//  分页查询
List<User> getUserByLimit(Map<String, Integer> map);
```

2. Mapper.xml

```xml
<!--结果集映射-->
<resultMap id="UserMap" type="User">
    <!--column：数据库中的字段，property：实体类中的属性-->
    <result column="pwd" property="password"/>
</resultMap>
<!--分页查询-->
<select id="getUserByLimit" resultMap="UserMap">
    select *
    from mybatis.user
    limit #{startIndex},#{pageSize};
</select>
```

3. 测试

```java
package com.tian.dao;

import com.tian.pojo.User;
import com.tian.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: MybatisStudy
 * @description: 测试
 * @author: TianZD
 * @create: 2021-08-06 22:56
 **/
public class UserDaoTest {
   static Logger logger = Logger.getLogger(UserDaoTest.class);
   @Test
   public void TestLimit() {
      //1. 获取sqlsession对象
      SqlSession sqlSession = MybatisUtils.getSqlSession();
      //2. sql， 获取mapper
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      //2.1 map
      Map<String, Integer> map = new HashMap<>();
      map.put("startIndex", 1);
      map.put("pageSize", 2);
      //2.2 sql
      List<User> userByLimit = mapper.getUserByLimit(map);
      for (User user : userByLimit) {
         logger.info(user);
      }
      //3. 关闭
      sqlSession.close();
   }
}
```

# 7、使用注解开发（mybatis不推荐）

## 面向接口编程

> **三个面向**

**面向过程**：考虑问题时，以一个具体的流程为单位，考虑它的实现

**面向对象**：考虑问题时，以对象为单位，考虑他的属性和方法

**面向接口**：更多体现的是对系统整体的架构

> **理解**

**接口**：应是定义（规范、约束）与实现（名实分离的原则）的分离，本身反映了系统设计人员对系统的抽象理解

**面向过程编程（`Procedure Oriented`、简称`PO`）** 和 **面向对象编程（`Object Oriented`、简称`OO`）** 我们一定听过，然而实际企业级开发里受用更多的一种编程思想那就是：**面向接口编程（`Interface-Oriented`）**！

接口这个概念我们一定不陌生，实际生活中**最常见的例子就是**：插座！

我们只需要事先定义好插座的**接口标准**，各大插座厂商只要按这个接口标准生产，管你什么牌子、内部什么电路结构，这些均和用户无关，用户拿来就可以用；而且即使插座坏了，只要换一个符合接口标准的新插座，一切照样工作！

同理，实际代码设计也是这样！

我们在设计一个软件的代码架构时，我们都希望**事先约定**好各个功能的**接口**（即：约定好接口签名和方法），实际开发时我们只需要实现这个接口就能完成具体的功能！后续即使项目变化、功能升级，程序员只需要按照接口约定重新实现一下，就可以达到系统升级和扩展的目的！

正好，Java中天生就有`interface`这个语法，这简直是为面向接口编程而生的！

> **优点**

* 代码的灵活解耦
* 代码的扩展性
* 提高复用
* 分层开发中，上层不用管具体实现，大家遵守共同的标准，使得开发变得容易，规范性更好

## mybatis中的注解开发

**mybatis中不推荐使用注解**，其他框架推荐

使用注解来映射简单语句会使**代码显得更加简洁**，但对于**稍微复杂一点的语句，Java 注解不仅力不从心**，还会让你本就复杂的 SQL 语句更加混乱不堪。 因此，如果你需要做一些很复杂的操作，最好用 XML 来映射语句。

用来替代mapper.xml配置文件

1. **mybatis.config.xml**中绑定接口

```xml
    <!--需要绑定接口，注解替代了mapper配置文件，不再绑定配置文件，需要绑定接口-->
<mappers>
    <mapper class="com.tian.dao.UserMapper"/>
</mappers>
```

2. **UserMapper.interface**

```java
public interface UserMapper {
    //注解
   @Select("select id,name,pwd as password from mybatis.user")
   List<User> getUsers();
}
```

3. **test.java， 没有变化**

```java
public class UserDaoTest {
   @Test
   public void test() {
      SqlSession sqlSession = MybatisUtils.getSqlSession();
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      List<User> users = mapper.getUsers();
      for (User user : users) {
         System.out.println(user);
      }
      sqlSession.close();
   }
}
```

> **本质**

**反射机制实现**

> **底层**

**动态代理**

# 8、Mybatis执行流程

![image-20210807224915524](https://gitee.com/tianzhendong/img/raw/master//images/image-20210807224915524.png)

# 9、多对一、一对多

**关联**：多个学生，关联一个老师，多对一

**集合**：一个老师，有很多学生，一对多

。。。

# 10、动态SQL

> **理解**

Mybatis的强大特性之一就是动态sql，使用动态sql可以拜托不同条件下拼接sql语句的痛苦

**动态sql就是根据不同的条件，生成不同的sql语句**

如果你之前用过 JSTL 或任何基于类 XML 语言的文本处理器，你对动态 SQL 元素可能会感觉似曾相识。在 MyBatis 之前的版本中，需要花时间了解大量的元素。借助功能强大的基于 OGNL 的表达式，MyBatis 3 替换了之前的大部分元素，大大精简了元素种类，现在要学习的元素种类比原来的一半还要少。

- if
- choose (when, otherwise)
- trim (where, set)
- foreach



## 搭建环境

> **创建sql表**

```sql
CREATE TABLE `blog`(
    `id` varchar(50) NOT NULL COMMENT '博客id',
    `title` varchar(100) not null comment '博客标题',
    `author` varchar(30) not null comment '博客作者',
    `create_time` datetime not null comment '创建时间',
    `views` int(30) not null comment '浏览量'
)engine = InnoDB default charset=utf8
```

> **创建一个基础工程**

1. 导包

2. 编写配置文件mybatis-config.xml和db.properties

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--引入外部配置文件-->
    <properties resource="db.properties">
    </properties>
    <settings>
        <!--开启将数据库字段转换成驼峰命名，如create_time变为createTime-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>    
    <!--别名-->
    <typeAliases>
        <package name="com.tian.pojo"/>
    </typeAliases>
    <environments default="development">
        <!--通过id选择默认的事务管理器，默认jdbc，还有一个是MANAGED-->
        <environment id="development">
            <transactionManager type="JDBC"/>
            <!--配置数据源，UNPOOLED|POOLED|JNDI，默认采用的数据源，POOLED-->
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper class="com.tian.dao.BlogMapper"/>
    </mappers>
</configuration>
```

**mybatisUtils类省略**

**编写IDUtils类，用于生成随机id**

```java
import java.util.UUID;
public class IDUtils {
   public static String getId() {
      return UUID.randomUUID().toString().replaceAll("-", "");
   }
}
```







3. 编写实体类

```java
@Data
public class Blog {
	private String id;
	private String title;
	private String author;
	//data的属性名和字段名不一致，字段名为create_time
	private Date createTime;
	private int views;
}
```

4. 编写实体类对应Mapper接口和Mapper.xml文件

```java
public interface BlogMapper {
   // 插入数据
   int addBlog(Blog blog);
}
```

```xml
<mapper namespace="com.tian.dao.BlogMapper">
    <insert id="addBlog" parameterType="blog">
        insert into mybatis.blog(id, title, author, create_time, views)
        values (#{id},#{title},#{author},#{createTime},#{views});
    </insert>
</mapper>
```

**生成的数据库如下：**

![image-20210808003003987](https://gitee.com/tianzhendong/img/raw/master//images/image-20210808003003987.png)



## 动态sql-IF

1. **BlogMapper.interface**

```java
	//查询博客
	List<Blog> queryBlogIF(Map map);
```

2. **BlogMapper.xml**

```xml
    <select id="queryBlogIF" parameterType="map" resultType="blog">
        <!--作用：当查询语句参数中没有title和author时，输出全部的，有titile或者author时根据
        传入的值进行过滤-->
        select * from mybatis.blog where 1=1
        <if test="title!=null">
            and title = #{title}
        </if>
        <if test="author!=null">
            and author = #{author}
        </if>
    </select>
```

where属性：*where* 元素只会在子元素返回任何内容的情况下才插入 “WHERE” 子句。而且，若子句的开头为 “AND” 或 “OR”，*where* 元素也会将它们去除。

```xml
select * from mybatis.blog
<where>
    <if test="title!=null">
        and title = #{title}
    </if>
    <if test="author!=null">
        and author = #{author}
    </if>
</where>
```

3. **test**

**传入空值，查询全部**

```java
@Test
public void queryBlogIFTest() {
   SqlSession sqlSession = MybatisUtils.getSqlSession();
   BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
   HashMap map = new HashMap();
       //传入空值，查询全部
   List<Blog> blogs = mapper.queryBlogIF(map);
   for (Blog blog : blogs) {
      System.out.println(blog);
   }
   sqlSession.close();
}
```

**传入值，过滤查询**

```java
public void queryBlogIFTest() {
   SqlSession sqlSession = MybatisUtils.getSqlSession();
   BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

   HashMap map = new HashMap();
   //赋值，过滤
   map.put("title", "blog1");
   List<Blog> blogs = mapper.queryBlogIF(map);
   for (Blog blog : blogs) {
      System.out.println(blog);
   }
   sqlSession.close();
}
```

## 动态sql-choose（when,otherwise）

从多个条件中选择一个使用，有点像 Java 中的 switch 语句

传入了 “title” 就按 “title” 查找，传入了 “author” 就按 “author” 查找的情形。若两者都没有传入，就根据views进行查找

```xml
 	<select id="queryBlogIF" parameterType="map" resultType="blog">
        select * from mybatis.blog
        <where>
            <choose>
                <when test="title != null">
                    title = #{title}
                </when>
                <when test="author!=null">
                    and author = #{author}
                </when>
                <otherwise>
                    and views = #{views}
                </otherwise>                
            </choose>
        </where>
    </select>
```



## set元素

用于动态更新语句的类似解决方案叫做 *set*。*set* 元素可以用于动态包含需要更新的列，忽略其它不更新的列。比如

```xml
<update id="updateAuthorIfNecessary">
  update Author
    <set>
      <if test="username != null">username=#{username},</if>
      <if test="password != null">password=#{password},</if>
      <if test="email != null">email=#{email},</if>
      <if test="bio != null">bio=#{bio}</if>
    </set>
  where id=#{id}
</update>
```

*set* 元素会动态地在行首插入 SET 关键字，并会删掉额外的逗号（这些逗号是在使用条件语句给列赋值时引入的）。



## sql片段

抽取sql部分片段，方便重复代码复用

```xml
<!--1. 抽取sql片段-->
<sql id = "if-title-author">
    <if test="title!=null">
        and title = #{title}
    </if>
    <if test="author!=null">
        and author = #{author}
    </if>
</sql>

<select id="queryBlogIF" parameterType="map" resultType="blog">
	select * from mybatis.blog
	<where>
        <!--2. 引入sql片段-->
    	<include refid = "if-title-author"></include>
	</where>
```



## 动态sql-Foreach

动态 SQL 的另一个常见使用场景是对集合进行遍历（尤其是在构建 IN 条件语句的时候）。比如：

```xml
<select id="selectBlogIn" parameterType="map" resultType="blog">
  SELECT *
  FROM mybatis.blog
  WHERE ID in
  <where>
     <foreach item="id" collection="ids"
     	 open="and (" separator="or" close=")">
         id = #{item}
  	</foreach>
  </where>
</select>
```

# 11、缓存

## 简介

> **什么是缓存**

放在内存中的临时数据

一次查询的结果暂时放在内存中，再次查询相同数据的时候直接从缓存取，不用再走数据库了，从而提高查询雄安率，解决了高并发系统的性能问题

> **为什么使用缓存**

减少和数据库的交互次数，减小系统开销，提高系统效率

> **什么样的数据能使用缓存**

经常查询并且不经常改变的数据



## Mybatis缓存

> mybatis缓存介绍

mybatis默认定义了两级缓存：一级缓存和二级缓存

* 默认下，只有一级缓存开启，（sqlsession级别的缓存，也叫本地缓存，在sqlsession创建和关闭之间的部分）
* 二级需要手动开启和配置，基于namespace级别的缓存
* 为了提高扩展性，mybatis定义了缓存接口cache，可通过实现cache接口自定义二级缓存



> **一级缓存**

默认开启，本地缓存

sqlsession级别的缓存，在sqlsession创建和关闭之间，使用代码查询统一数据多次，只会和数据库交互一次

> **二级缓存**

* 全局缓存
* 基于namespace级别的缓存，一个名称空间，对应一个二级缓存
* 工作机制
  * 一个会话查询一条数据，数据被放在当前会话的一级缓存中
  * 会话关闭后，对应的一级缓存就没了，但是我们想要的是会话关闭了，一级缓存中的数据保存到二级缓存中
  * 新的会话查询数据，可以从二级缓存中获取内容
  * 不同的mapper查出的数据会放在自己对应的缓存中



**步骤**：

1. 开启全局缓存

mybatis-config.xml

```xml
    <settings>
        <!--开启全局缓存-->
        <setting name="cacheEnable" value="true"/>
    </settings>
```

2. 在mapper.xml中加入`<cache/>`标签

使用：在mapper.xml中加标签

```xml
<cache/>
```

高级配置：

```xml
<cache
  eviction="FIFO"
  flushInterval="60000"
  size="512"
  readOnly="true"/>
```

这个更高级的配置创建了一个 FIFO 缓存，每隔 60 秒刷新，最多可以存储结果对象或列表的 512 个引用，而且返回的对象被认为是只读的，因此对它们进行修改可能会在不同线程中的调用者产生冲突。

## Mybatis缓存原理

![](https://gitee.com/tianzhendong/img/raw/master//images/image-20210808041200380.png)

