---
title: SSM整合
tags: java
notebook: JAVA
---

[toc]

# 1、SpringMVC-SSM整合

## 1.1、环境

> 环境

* IDE：IDEA 2021 1.1
* 项目管理：Maven ，apache-maven-3.8.1
* JAVA：JDK 16.01
* Web服务器：apache-tomcat-9.0.50
* 数据库：MySQL 8.0.26
* 数据库管理工具：Navicat premium 15

> 数据库环境

```sql
CREATE DATABASE `ssmbuild`;
USE `ssmbuild`;
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
    `bookID` INT(10) NOT NULL AUTO_INCREMENT COMMENT '书id',
    `bookName` VARCHAR(100) NOT NULL COMMENT '书名',
    `bookCounts` INT(11) NOT NULL COMMENT '数量',
    `detail` VARCHAR(200) NOT NULL COMMENT '描述',
    KEY `bookID` (`bookID`)
)ENGINE = INNODB DEFAULT CHARSET = utf8;

INSERT INTO `books`(`bookID`,`bookName`,`bookCounts`,`detail`)
VALUES (1,'java',1,'从入门到放弃'),
       (2,'MySQL',10,'从删库到跑路'),
       (3,'Linux',5,'从进门到坐牢');
```

> 基本环境搭建

* 新建maven工程
* 配置pom.xml
  * 导入依赖
  * 配置静态资源

```xml
<!--依赖：junit，数据库驱动，连接池，servlet，jsp，mybatis，mybatis-spring，spring,log4j-->
<dependencies>
    <!--junit-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <!--数据库驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.25</version>
    </dependency>
    <!--数据库连接池c3p0或者dpcp-->
    <dependency>
        <groupId>com.mchange</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.5.2</version>
    </dependency>
    <!--servlet  jsp-->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.2</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <!--mybatis-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.7</version>
    </dependency>
    <!--mybatis-spring-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.2</version>
    </dependency>
    <!--spring-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.9</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.3.9</version>
    </dependency>
    
    <!--log4j-->
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
</dependencies>


<!--静态资源导出问题-->
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```

> IDEA连接数据库

![image-20210815222657080](https://gitee.com/tianzhendong/img/raw/master//images/image-20210815222657080.png)

> 建立项目包结构

* dao
* pojo
* controller
* service

> 建立核心配置文件

* spring：applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context-4.0.xsd">

</beans>
```

* mybatis：mybatis-config.xml和database.properties

mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    
    <settings>
        <!--配置log4j，name必须为logImpl，I为大写，value需要使用mybatis提供的哪些，不能有空格-->
        <setting name="logImpl" value="LOG4J"/>
    </settings>
    <!--配置数据源，交给spring了-->

    <!--配置别名-->
    <typeAliases>
        <package name="com.tian.pojo"/>
    </typeAliases>

</configuration>
```

database.properties

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
#如果使用mysql8.0以上，需要增加时区设置
jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
jdbc.username=root
jdbc.password=123456
```

* log4j.properties

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

## 1.2、Mybatis层

主要是dao层和service层，底层相关，MVC的Model层，数据和业务



> pojo层

```java
public class Books {
   private int bookID;
   private String bookName;
   private int bookCounts;
   private String detail;
//get、set、toString、construct
}
```

> dao层

* 接口

```java
public interface BookMapper {
   //add
   int addBook(Books books);

   //delete
	int deleteBook(@Param("bookID") int id);

   //update
   int updateBook(Books books);

   //select one
	Books selectBookById(@Param("bookID") int id);

   //select all
   List<Books> selectBookAll();
}
```

* Mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.tian.dao.BookMapper">
    <insert id="addBook" parameterType="books">
        insert into ssmbuild.books (bookName, bookCounts, detail)
        values (#{bookName},#{bookCounts},#{detail});
    </insert>

    <delete id="deleteBook" parameterType="int">
        delete
        from ssmbuild.books
        where bookID = #{bookID};
    </delete>

    <update id="updateBook" parameterType="books">
        update ssmbuild.books
        set bookName = #{bookName}, bookCounts = #{bookCounts}, detail = #{detail}
        where bookID = #{bookID};
    </update>

    <select id="selectBookById" resultType="books">
        select *
        from ssmbuild.books
        where bookID = #{bookID};
    </select>

    <select id="selectBookAll" resultType="books">
        select *
        from ssmbuild.books;
    </select>

</mapper>
```

* 绑定mapper.xml到mybatis-config.xml配置文件中

```xml
<mappers>
    <mapper class="com.tian.dao.BookMapper"/>
    <!--        <mapper resource="com/tian/dao/BookMapper.xml"/>-->
    <!--        <package name="com.tian.dao"/>-->
</mappers>
```

> service层

* BookService接口

```java
public interface BookService {
   //add
   int addBook(Books books);

   //delete
   int deleteBook(int id);

   //update
   int updateBook(Books books);

   //select one
   Books selectBookById(int id);

   //select all
   List<Books> selectBookAll();
}
```



* 接口实现类

```java
public class BookServiceImpl implements BookService{
   //业务层调用dao层：组合dao层
   private BookMapper bookMapper;

   public void setBookMapper(BookMapper bookMapper) {
      this.bookMapper = bookMapper;
   }

   @Override
   public int addBook(Books books) {
      return bookMapper.addBook(books);
   }

   @Override
   public int deleteBook(int id) {
      return bookMapper.deleteBook(id);
   }

   @Override
   public int updateBook(Books books) {
      return bookMapper.updateBook(books);
   }

   @Override
   public Books selectBookById(int id) {
      return bookMapper.selectBookById(id);
   }

   @Override
   public List<Books> selectBookAll() {
      return bookMapper.selectBookAll();
   }
}
```

## 1.3、Spring层

> dao层

spring-dao.xml

* 关联数据库配置文件
* 连接池
* sqlSessionFactory
* sqlSession

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <!--关联数据库配置文件-->
    <context:property-placeholder location="classpath:database.properties"/>

    <!--连接池
        dbcp:半自动化操作，不能自动连接
        c3p0：自动化链接（自动化加载配置文件，并且可以自动设置到对象中）
        druid
        hikari-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <!--c3p0连接池的私有属性-->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!--关闭连接后不自动commit-->
        <property name="autoCommitOnClose" value="false"/>
        <!--获取连接超时时间-->
        <property name="checkoutTimeout" value="100000"/>
        <!--当前连接失败重试次数-->
        <property name="acquireRetryAttempts" value="2"/>
    </bean>

    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--绑定mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
    </bean>

    <!--采用方法3
    方法1：dao编写BookMapper实现类，添加SqlSessionTemplate属性
    方法2：dao编写BookMapper实现类，同时继承SqlSessionDaoSupport，通过get方法可以直接获的sqlSession
    方法3：配置dao扫描包，动态的实现了dao接口可以注入到spring容器中-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--注入sqlsession,由于属性为string，用value-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!--要扫描的dao包-->
        <property name="basePackage" value="com.tian.dao"/>
    </bean>
</beans>
```

> service

spring-service.xml

* 扫描service下的包
* 将业务类注入到spring，可以通过配置或者注解实现
* 声明式事务配置
* AOP事务支持

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <!--扫描service下的包-->
    <context:component-scan base-package="com.tian.service"/>

    <!--将业务类注入到spring，可以通过配置或者注解实现
    注解：类上@Service  属性上@Autowired-->
    <bean id="BookServiceImpl" class="com.tian.service.BookServiceImpl">
        <property name="bookMapper" ref="bookMapper"/>
    </bean>

    <!--声明式事务配置-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--注入数据源-->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--AOP事务支持-->
</beans>
```

## 1.4、SpringMVC层

> 增加web支持

> web.xml

* dispatchservlet
* 乱码过滤

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--DispatchServlet-->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 关联配置文件 -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc-servlet.xml</param-value>
        </init-param>
        <!-- 启动优先级：越小越优先启动 -->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--乱码过滤-->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!--session-->
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>

</web-app>
```

> springmvc-servlet.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context-4.0.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 扫描包 -->
    <context:component-scan base-package="com.tian.controller"/>
    <!-- 过滤静态资源 -->
    <mvc:default-servlet-handler/>
    <!-- 注解驱动 -->
    <mvc:annotation-driven/>

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
          id="internalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```

## 1.5、配置文件整合

applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <import resource="spring-dao.xml"/>
    <import resource="spring-service.xml"/>
    <import resource="springmvc-servlet.xml"/>
</beans>
```

# 2、实际业务实现

## 2.1、查询书籍功能

### 查询

将controller和web交互

> controller

*BookController.class*

```java
@Controller
@RequestMapping("/book")
public class BookController {
   //controller层调用service层
   @Autowired
   @Qualifier("BookServiceImpl")
   private BookService bookService;

   //查询全部书籍，并返回书籍展示页面
   public String selectAllBook(Model model) {
      List<Books> books = bookService.selectBookAll();
      model.addAttribute("list", books);
      return "allBook";
   }

}
```

> jsp

* allBook.jsp
* index.jsp，设置由首页跳转

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
</head>
<body>
<h1>书籍展示</h1>
</body>
</html>
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$
  <h3>
    <a href="${pageContext.request.contextPath}/book/allBook">进入书籍展示页面</a>
  </h3>
  </body>
</html>
```

### 错误

```bash
org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.tian.service.BookService' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true), @org.springframework.beans.factory.annotation.Qualifier("BookServiceImpl")}
```

bean不存在

> 解决

web.xml中需要引入applicationContxt.xml而不是springmvc-config.xml

### 美化

> 首页

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
    <style>
      a {
        text-decoration: none;
        color: black;
        font-size: 18px;
      }
      h3 {
        width: 180px;
        height: 38px;
        margin: 100px auto;
        text-align: center;
        line-height: 38px;
        background: deepskyblue;
        border-radius: 5px;
      }
    </style>
  </head>
  <body>
  <h3>
    <a href="${pageContext.request.contextPath}/book/allBook">进入书籍展示页面</a>
  </h3>
  </body>
</html>
```

![](https://gitee.com/tianzhendong/img/raw/master//images/image-20210816033535794.png)

> 查询页

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
</head>
<body>
<table align="center" border="1" width="300" height="100" cellspacing="0"><!--设置表格剧中，边框线粗细，每个格子的长宽,单元格之间的间距为0-->
    <tr align="center">
        <th colspan="4" bgcolor="#00bfff">书籍列表</th><!--表头，自带加粗居中属性,colspan设置合并1行1列和2列的单元格，rowspan设置跨列合并-->
    </tr>
    <tr bgcolor="#ffe4c4">
        <td align="center"><b>书籍ID</b></td><!--设置字体居中，加粗-->
        <td align="center"><b>书籍名称</b></td>
        <td align="center"><b>书籍数量</b></td>
        <td align="center"><b>书籍描述</b></td>
    </tr>
    <c:forEach var="books" items="${list}">
        <tr align="center" bgcolor="#a9a9a9">
            <td>${books.bookID}</td>
            <td>${books.bookName}</td>
            <td>${books.bookCounts}</td>
            <td>${books.detail}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
```

![image-20210816201951940](https://gitee.com/tianzhendong/img/raw/master//images/image-20210816201951940.png)

## 2.2、添加书籍功能

> 待跳转页面

addBook.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加书籍页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/book/addBook" method="post">
    <table align="center" border="0">
        <tr>
            <td>书籍名称: </td>
            <td><input type="text" name="书籍名称" required></td>
        </tr>
        <tr>
            <td>书籍数量: </td>
            <td><input type="text" name="书籍数量" required></td>
        </tr>
        <tr>
            <td>书籍描述: </td>
            <td><input type="text" name="书籍描述"></td>
        </tr>
        <tr align="center">
            <td><input type="reset" value="重置"></td>
            <td><input type="submit" value="添加"></td>
        </tr>
    </table>
</form>
</body>
</html>
```

![image-20210816205546620](https://gitee.com/tianzhendong/img/raw/master//images/image-20210816205546620.png)

> controller

```java
//  跳转到添加书籍界面
@RequestMapping("/toAddBook")
public String toAddBook() {
   return "addBook";
}

//添加书籍请求
@RequestMapping("/addBook")
public String addBook(Books book) {
    bookService.addBook(book);
    return "redirect:/book/allBook";
}
```

> 跳转按钮

allBook.jsp

```xml
<style>
    #a1{
    text-decoration: none;
    }
</style>

<tr>
    <td colspan="4" align="center"><a id="a1"  href="${pageContext.request.contextPath}/book/toAddBook">添加书籍</a></td>
</tr>
```

![image-20210816210610577](https://gitee.com/tianzhendong/img/raw/master//images/image-20210816210610577.png)

## 2.3、修改、删除书籍

>  按钮

allBook.jsp

```jsp
<td>
    &nbsp;<a href="${pageContext.request.contextPath}/book/toUpdateBook/${books.bookID}">修改</a>
    |
    <a href="${pageContext.request.contextPath}/book/deleteBook/${books.bookID}">删除</a>&nbsp;
</td>
```

![image-20210816221451118](https://gitee.com/tianzhendong/img/raw/master//images/image-20210816221451118.png)

> controller

```java
	//跳转到修改请求页面
	@RequestMapping("/toUpdateBook/{bookId}")
	public String toUpdateBook(@PathVariable("bookId") int id, Model model) {
		Books books = bookService.selectBookById(id);
		model.addAttribute("bookSelected", books);
		return "updateBook";
	}

	//修改书籍
	@RequestMapping("/updateBook")
	public String updateBook(Books books) {
		bookService.updateBook(books);
		return "redirect:/book/allBook";
	}

	//删除书籍
	@RequestMapping("/deleteBook/{bookId}")
	public String deleteBook(@PathVariable("bookId") int id) {
		bookService.deleteBook(id);
		return "redirect:/book/allBook";
	}
}
```

> 待跳转页面

updateBook.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/book/updateBook" method="post">
    <%--隐藏于传递不需要用户修改的bookID--%>
    <input type="hidden" name="bookID" value="${bookSelected.bookID}">
    <table align="center" border="0">
        <tr>
            <td>书籍名称: </td>
            <td><input type="text" name="bookName" value="${bookSelected.bookName}" required></td>
        </tr>
        <tr>
            <td>书籍数量: </td>
            <td><input type="text" name="bookCounts" value="${bookSelected.bookCounts}" required></td>
        </tr>
        <tr>
            <td>书籍描述: </td>
            <td><input type="text" name="detail" value="${bookSelected.detail}"></td>
        </tr>
        <tr align="center">
            <td><input type="reset" value="重置"></td>
            <td><input type="submit" value="修改"></td>
        </tr>
    </table>
</form>
</body>
</html>
```

![image-20210816223906448](https://gitee.com/tianzhendong/img/raw/master//images/image-20210816223906448.png)



