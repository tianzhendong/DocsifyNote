---
title: SSM整合
tags: java
notebook: JAVA
---

[toc]

# 12、SSM整合

## 12.1、环境

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
<!--依赖：junit，数据库驱动，连接池，servlet，jsp，mybatis，mybatis-spring，spring-->
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



## 12.2、Mybatis层

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

## 12.3、Spring层

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
        <property name="user" value="${jdbc.username"/>
        <property name="password" value="${jdbc.password"/>
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

## 12.4、SpringMVC层

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

## 12.5、配置文件整合

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
    <import resource="applicationContext.xml"/>
    <import resource="spring-dao.xml"/>
    <import resource="spring-service.xml"/>
    <import resource="springmvc-servlet.xml"/>
</beans>
```

