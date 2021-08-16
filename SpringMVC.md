---
title: SpringMVC
tags: java
notebook: JAVA
---

[toc]

# SpringMVC

ssm：Spring+SpringMVC+Mybatis

# 1、MVC

[**参考**](https://www.cnblogs.com/secretmrj/p/15124075.html)

## 1.1、MVC概述

MVC是一种**设计模式**，用于应用程序的**分层开发**

MVC为：

* **Model模型**：提供要展示的数据，可以带有逻辑，包含**数据和行为**，通常将数据和行为**分离**
  * **数据**：**DAO层**
  * **行为**：**Service层**，服务层
* **View视图**：**jsp**，负责模型包含的数据的可视化，即模型的展示，代表用户界面
* **Controller控制器**：**servlet**，接收视图层的用户请求，委托给模型进行处理，将处理完毕返回的数据返回给视图，相当于一个调度员

最典型的MVC模式：**JSP+servlet+javaBean**

## 1.2、Model1时代

早期Web开发中，采用Model1模型，分为视图层和模型层。

* **视图层**：负责展示模型、接收请求并调用业务逻辑方法；
* **模型层**：提供要展示的数据；

![image-20210814101309684](https://gitee.com/tianzhendong/img/raw/master//images/image-20210814101309684.png)

- **优点：**架构简单；
- **缺点**：模型层职责不单一，既展示数据，又接收请求并处理。不利于维护！

## 1.3、Model2时代

将项目分为 *M-V-C* 三层，即目前使用的 *MVC* 模式。

1. 用户输入，发起请求；
2. 控制层（*Servlet*）接收请求数据；
3. 控制层（*Servlet*）调用模型层（*Service*）中相应的业务逻辑方法；
4. 模型层（*Service*）处理业务，与数据库交互，将数据返回到控制层（*Servlet*）；
5. 控制层（*Servlet*）将数据返回给视图层，由视图层渲染页面；
6. 将页面响应到前端。

![img](https://gitee.com/tianzhendong/img/raw/master//images/2410011-20210810172325515-518774607.png)

**优点：职责分明，利于维护**

- **Model**
  - 处理业务逻辑（*Service* 层）；
  - 保存数据的状态（*DAO* 层）；
- **Controller**
  - 获取请求；
  - 调用业务逻辑；
  - 响应页面（跳转指定页面）；
- **View**
  - 显示页面

# 2、Servlet

[**参考**](https://www.cnblogs.com/secretmrj/p/15124075.html)

> 新建Maven无模板父工程

删除src，作为父工程

> 添加依赖

- SpringMVC
- JUnit
- Servlet、JSP

```xml
<!--导入依赖-->
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.9</version>
    </dependency>
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
```

> 新建Module，添加webapp支持

新建Module时选择普通Maven项目

新建完成后右键-添加框架支持，勾选web

![img](https://gitee.com/tianzhendong/img/raw/master//images/2410011-20210810172316459-56721522.png)

> 编写代码

* 登陆页面

```jsp
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="app">
    <form action="login" method="post">
        <label>
            用户名：<input type="text" name="username">
        </label>
        <input type="submit">
    </form>
</div>
</body>
</html>
```

* servlet类

```java
package com.tian.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class LoginServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // 1.获取前端参数
      String username = req.getParameter("username");
      if ("admin".equals(username)) {
         req.setAttribute("msg", "管理员");
      } else {
         req.setAttribute("msg", "普通用户");
      }
      // 2.调用业务层

      // 3.视图转发或者重定向
      req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}
```

* servlet注册

```xml
<servlet>
    <servlet-name>loginServlet</servlet-name>
    <servlet-class>com.tian.servlet.LoginServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>loginServlet</servlet-name>
    <url-pattern>/login</url-pattern>
</servlet-mapping>
```

* 成功页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <h1>登陆成功：${msg}</h1>
</body>
</html>
```

# 3、SpringMVC入门

[**参考**](https://www.cnblogs.com/secretmrj/p/15124075.html)

[*SpringMVC* 官方文档](https://docs.spring.io/spring-framework/docs/5.3.8/reference/html/web.html)

## 3.1、简介

*Spring Web MVC*，简称 *SpringMVC*。

- 是 *Spring Framework* 的一部分，是基于 *Java Servlet API* 实现 *MVC* 的轻量级 *Web* 框架。
- 围绕 *DispatcherServlet* 设计，将请求分发到不同的处理器。

**特点：**

1. **轻量级**，简洁灵活，简单易学；
2. 高效 , **基于请求响应**的MVC框架；
3. 与 *Spring* 兼容性好，无缝结合；
4. **约定大于配置**；
5. **功能强大：RESTful、数据验证、格式化、本地化、主题等**；

## 3.2、中心控制器

- *SpringMVC* 围绕 *DispatcherServlet* 设计，*DispatcherServlet* 的作用是将请求分发到不同的处理器。
- *Spring 2.5 + Java 5* 以上版本，可以采用**基于注解**的 *Controller* 声明。
- 从继承图可以看出，*DispatcherServlet* 本质上就是 *Servlet*：

![img](https://gitee.com/tianzhendong/img/raw/master//images/2410011-20210811234044433-1099197835.png)

## 3.3、SpringMVC执行原理

- **前端控制器**：即 *DispatcherServlet*
- **实际控制器**：即实际处理请求的 *Controller*
- **模型层**：即 *Model* 层，包括 *Service* 层和 *DAO* 层；
- **视图层**：即 *View* 层。

> 原理图

![img](https://img2020.cnblogs.com/blog/2410011/202108/2410011-20210811234037991-2080442507.png)

1. **用户**：发起请求；
2. **前端控制器**：拦截请求，根据请求参数生成代理请求，将请求调度给对应的**实际控制器**；
3. **控制器**：处理请求，调用**模型层**中相应的业务逻辑方法；
4. **模型层**：*Service* 层处理业务，调用 *DAO* 层与数据库交互，创建并返回数据模型；
5. **控制器**：将 *ModelAndView* 返回给**前端控制器**；
6. **前端控制器**：传递 *Model* 到**视图层**；
7. **视图层**：渲染视图并返回**前端控制器**；
8. **前端控制器**：响应给用户。

> 底层源码关系

![image-20210815161309751](https://gitee.com/tianzhendong/img/raw/master//images/image-20210815161309751.png)

* 实线部分不用程序员做，程序员只需要做虚线部分

 ![image-20210815161440258](https://gitee.com/tianzhendong/img/raw/master//images/image-20210815161440258.png)

![image-20210815161525228](https://gitee.com/tianzhendong/img/raw/master//images/image-20210815161525228.png)

## 3.4、HelloSpringMVC-基于xml配置

> 1. 新建Module，添加web支持

> 2. 导入SpringMVC依赖

> 3. 在web.xml中配置DispatchServlet（前端控制器，请求分发器）

这个是SpringMVC的核心

- 关联配置文件

- 启动优先级

- ```
  <url-pattern>
  ```

  - `/`：匹配所有请求，不包括 *JSP*；
  - `/*`：匹配所有请求及 *JSP*
  - 如果填写了`/*`，*Controller* 处理业务后返回的 JSP页面会再次被拦截请求，会无限嵌套。

alt+ins键，选择servlet，搜索dispatchservlet

```xml
<!--配置DispatchServlet：请求分发器、前端控制器-->
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
            <!--只能用/，不能用/*-->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

**注意**：

* /：只匹配所有的请求，不会匹配jsp页面
* /*：匹配所有的请求，包括jsp页面

> 4. 编写springmvc-servlet.xml

- 处理器映射
- 处理器适配器
- 内部资源视图解析器
- 后续：将 *Controller* 注册到 *IOC* 容器中：相当于原来在 *web.xml* 中注册 *Servlet*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 处理器映射 -->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
    <!-- 处理器适配器 -->
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
    <!-- 内部资源视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 前缀，必须有斜杠 -->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"/>
    </bean>
    <!-- 相当于原来在 web.xml 中注册 Servlet -->
    <bean id="/hello" class="com.tian.controller.HelloController"/>
</beans>
```

> 5. Controller

HelloController.class

最后返回给视图解析器，进行hello名字拼接，拼接成/WEB-INF/jsp/hello.jsp

然后跳转得到hello.jsp页面

```java
public class HelloController implements Controller {
   @Override
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ModelAndView modelAndView = new ModelAndView();

      // 业务代码
      String result = "HelloSpringMVC";
      modelAndView.addObject("msg", result);

      //视图跳转
      modelAndView.setViewName("hello");

      return modelAndView;
   }
}
```

> 6. 跳转页面hello.jsp

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 12038
  Date: 2021/8/15
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${msg}</h1>
</body>
</html>
```

> 过程分析

1. **用户**：发起请求；
2. web.xml中：
   1. **前端控制器**：拦截请求，根据请求参数生成代理请求，根据关联的配置文件springmvc-servlet.xml，将请求调度给对应的**实际控制器**
   2. 处理器映射器映射到hello
   3. 处理器适配器找到com.tian.controller.HelloController进行处理
3. **控制器**：处理请求，调用**模型层**中相应的业务逻辑方法HelloController.class；
4. **模型层**：*Service* 层处理业务，调用 *DAO* 层与数据库交互，创建并返回数据模型ModeAndView对象；
5. **控制器**：将 *ModelAndView* 返回给**前端控制器**；
6. **前端控制器**：传递 *Model* 到**视图层**；
7. **视图层**：渲染视图并返回**前端控制器**；
8. **前端控制器**：响应给用户。

![img](https://gitee.com/tianzhendong/img/raw/master//images/2410011-20210811234023521-520736861.png)

> 易错

1. 注册 ***DispatcherServlet\* 的 \*url-pattern\*** 不能是 `/*`；
2. ***springmvc-servlet.xml*** 中：
   - **视图解析器**的前缀的结尾必须有`/`：如`/WEB-INF/jsp/`；
   - *Controller* 的注册 *Bean* 必须以 `/` 开头，如`/hello`；
3. 以上操作均无误，仍报 *404* 或 *500*：
   - 创建的 *web* 模板的Maven项目，理论上不会报错；
   - 创建不带模板的 *Maven* 项目 + 手动添加 *web* 框架支持，可能会报错；
   - 需要手动在项目结构中添加 lib 目录：

![img](https://gitee.com/tianzhendong/img/raw/master//images/2410011-20210811234014733-995517311.png)

# 4、基于注解开发SpringMVC

在 *SpringMVC* 实际开发中，我们通常都会采用注解开发。

> 1. 新建Module，添加web支持

> 2. 导入SpringMVC依赖：Spring框架核心库、SpringMVC、servlet、JSTL等

> 3. 在web.xml中配置DispatchServlet（前端控制器，请求分发器）

这个是SpringMVC的核心

- 关联配置文件

- 启动优先级

- ```
  <url-pattern>
  ```

  - `/`：匹配所有请求，不包括 *JSP*；
  - `/*`：匹配所有请求及 *JSP*
  - 如果填写了`/*`，*Controller* 处理业务后返回的 JSP页面会再次被拦截请求，会无限嵌套。

alt+ins键，选择servlet，搜索dispatchservlet

```xml
<!--配置DispatchServlet：请求分发器、前端控制器-->
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
            <!--只能用/，不能用/*-->
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

**注意**：

* /：只匹配所有的请求，不会匹配jsp页面
* /*：匹配所有的请求，包括jsp页面

> 4. springmvc-servlet.xml

与`基于配置的SpringMVC`的配置相比：

- 添加注解支持，不再需要手动注册 *Controller*
- 内部资源视图解析器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
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

> 5. Controller

HelloController.class

最后返回给视图解析器，进行hello名字拼接，拼接成/WEB-INF/jsp/hello.jsp

然后跳转得到hello.jsp页面

```java
@Controller
@RequestMapping("/hello")       //配置总地址，可以有可无
public class HelloController {

   /**
    * 使用 @RequestMapping注解来处理映射关系，后接请求路径（如"/hello"）
    * 方式一：使用 ModelAndView来封装模型和视图，返回 ModelAndView
    */
   @RequestMapping("/hello")
   public ModelAndView hello(ModelAndView mv) {
      // 可以调用Service层方法，获取模型对象

      // 添加数据模型
      mv.addObject("msg", "Hello Annotation：ModelAndView");
      // 封装要渲染、跳转的视图
      mv.setViewName("hello");

      return mv;
   }

   /**
    * 使用 @RequestMapping注解来处理映射关系，后接请求路径（如"/hello"）
    * 方式二：使用 Model来封装模型，返回 ViewName
    */
   @RequestMapping("/hello1")      //真实访问地址：localhost:8080/项目名/hello/hello1
   public String hello(Model model) {
      // 可以调用Service层方法，获取模型对象

      // 添加数据模型
      model.addAttribute("msg", "Hello Annotation：Model");
      // 返回视图
      return "hello"; //会被视图解析器处理
   }
}
```

> 6. 跳转页面

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${msg}</h1>
</body>
</html>
```

> 总结

1. 新建项目
2. 导入jar包
3. 编写web.xml，注册DispatchServlet
4. 编写Springmvc配置文件
5. **创建对应的控制类controller**
6. **完善前端视图和controller之间的对应**
7. 测试运行

**后续开发只需要进行5和6**

> 注解和配置对比

### 基于配置 VS 基于注解

1. *web.xml* 完全相同；

2. *springmvc-servlet.xml*

   - **基于配置**：处理器映射、处理器适配器、内部资源视图解析器；
     - 处理器映射：默认配置，不需要显式配置；
     - 处理器适配器：默认配置，不需要显式配置；
     - 内部资源视图解析器：必须显式配置；
     - 将 *Controller* 注册到 *IOC* 容器中：相当于原来在 *web.xml* 中注册 *Servlet*。

   - **基于注解**：[*Spring* 注解开发](https://www.cnblogs.com/secretmrj/p/15091525.html)
     - 扫描包：扫描指定包下的 *Bean* ，被扫描的 *Bean* 中包含的类级别的注解才会生效，*Bean* 才会被注册到容器中；
     - 过滤静态资源：如 *CSS*、*JS*、*HTML*、*MP3*、*MP4*...
     - 注解驱动：在 *Spring* 中一般使用 *@RequestMapping* 注解来处理映射关系，使用该注解需要注册`处理器映射`和`处理器适配器`，*annotation-driven* 自动注入以上两个实例；
     - 内部资源视图解析器。

3. ***Controller***

   - **基于配置**：
     - 继承 *Controller* 接口，重写 *handleRequest* 方法；
     - 通过 *ModelAndView* 封装模型和视图，返回 *ModelAndView*
     - 需要在 *springmvc-servlet.xml* 中注册 *Bean* 并映射请求路径；
     - 如果有多个请求，通过获取参数判断方法名实现 *Controller* 复用，或编写多个 *Controller*。
   - **基于注解**
     - 使用 *@Controller* 注解来注册 *Bean*，自定义方法；
     - 通过 *ModelAndView* 封装模型和视图，返回 *ModelAndView*。或通过 *Model* 封装模型，返回视图名；
     - 在方法上使用`@RequestMapping("/xxx")`来映射请求路径
     - 如果有多个请求，自定义编写多个方法。

# 5、控制器Controller

* 控制器提供访问应用程序的行为，通过接口定义或注解两种方式实现
* 负责解析用户的请求并将其转换为一个模型

## 5.1、实现Controller接口

缺点是一个控制器中只有一个方法，如果要有多个方法需要定义多个controller，比较麻烦

```java
//实现接口的类能获得控制器功能
public interface Controller {
   @Nullable
    //处理请求并返回一个模型与视图对象
   ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
```

> 编写实现类

```java
public class HelloController implements Controller {
   @Override
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ModelAndView modelAndView = new ModelAndView();
      // 业务代码
      String result = "HelloSpringMVC";
      modelAndView.addObject("msg", result);
      //视图跳转
      modelAndView.setViewName("hello");
      return modelAndView;
   }
}
```

> 配置文件

* 视图解析器
* 注册servlet

配置文件中处理器映射和处理器适配器部分可以去掉，spring会自动加

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 处理器映射 -->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
    <!-- 处理器适配器 -->
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
    <!-- 内部资源视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 前缀，必须有斜杠 -->
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"/>
    </bean>
    <!-- 相当于原来在 web.xml 中注册 Servlet -->
    <bean id="/hello" class="com.tian.controller.HelloController"/>
</beans>
```

## 5.2、使用注解@Controller

> 配置文件

* 添加包扫描
* 视图解析器

```xml
<!-- 扫描包 -->
<context:component-scan base-package="com.tian.controller"/>
<!-- 视图解析器 -->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
      id="internalResourceViewResolver">
    <!-- 前缀 -->
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <!-- 后缀 -->
    <property name="suffix" value=".jsp"/>
</bean>
```

> 增加一个controller类，使用注解i实现

```java
@Controller
@RequestMapping("/hello")       //配置总地址，可以有可无
public class HelloController {

   /**
    * 使用 @RequestMapping注解来处理映射关系，后接请求路径（如"/hello"）
    * 方式一：使用 ModelAndView来封装模型和视图，返回 ModelAndView
    */
   @RequestMapping("/hello")		
   public ModelAndView hello(ModelAndView mv) {
      // 可以调用Service层方法，获取模型对象

      // 添加数据模型
      mv.addObject("msg", "Hello Annotation：ModelAndView");
      // 封装要渲染、跳转的视图
      mv.setViewName("hello");

      return mv;
   }

   /**
    * 使用 @RequestMapping注解来处理映射关系，后接请求路径（如"/hello"）
    * 方式二：使用 Model来封装模型，返回 ViewName
    */
   @RequestMapping("/hello1")      //真实访问地址：localhost:8080/项目名/hello/hello1
   public String hello(Model model) {
      // 可以调用Service层方法，获取模型对象

      // 添加数据模型
      model.addAttribute("msg", "Hello Annotation：Model");
      // 返回视图
      return "hello"; //会被视图解析器处理
   }
}
```

# 6、RestFul风格

## 6.1、概念

*RESTful* 是一个资源定位及资源操作的风格。不是标准也不是协议，只是一种风格。

基于这个风格设计的软件可以更简洁，更有层次，更易于实现缓存等机制。

## 6.2、功能

- **资源**：互联网所有的事物都可以被抽象为资源
- **资源操作**：使用不同方法操作资源，*POST*（添加）、*DELETE*（删除）、*PUT*（更新）、*GET*（查询）；

**传统方法操作资源**：通过不同的请求**参数**来实现不同的功能，请求地址不同。

- 添加：`http://127.0.0.1/item/insertItem.action`
- 删除：`http://127.0.0.1/item/deleteItem.action?id=1`
- 更新：`http://127.0.0.1/item/updateItem.action`
- 查询：`http://127.0.0.1/item/getItem.action?id=1`

**RESTful风格操作资源**：通过不同的**请求方式**来实现不同的功能，请求地址相同。

- 添加：`http://127.0.0.1/item`
- 删除：`http://127.0.0.1/item/1`
- 更新：`http://127.0.0.1/item`
- 查询：`http://127.0.0.1/item/1`

## 6.3、实现

使用 *@PathVariable* 注解，将参数值绑定到 *URI* 模板变量上。

```java
@Controller
public class RestfulController {

    @RequestMapping("/addition/{a}/{b}")
    public String addition(@PathVariable int a,@PathVariable int b, Model model) {
        int result = a + b;
        model.addAttribute("result", "结果：" + result);

        return "test";
    }
}
```

使用 *method* 属性约束请求的类型，指定类型的请求才可访问。

```java
@RequestMapping(value = "/multiplication/{a}/{b}", method = RequestMethod.POST)
public String multiplication(@PathVariable int a, @PathVariable int b, Model model) {
    int result = a * b;
    model.addAttribute("result", "结果：" + result);

    return "test";
}
```

也可以使用 *@RequestMapping* 的衍生注解：*@PostMapping*

```java
@PostMapping("/multiplication/{a}/{b}")
public String multiplication(@PathVariable int a, @PathVariable int b, Model model) {
    int result = a * b;
    model.addAttribute("result", "结果：" + result);

    return "test";
}
```

## 6.4、请求类型

*@RequestMapping* 注解能够处理 *HTTP* 请求的方法，包括 *GET*, *PUT*, *POST*, *DELETE* , *PATCH* 等等。

- **所有的地址栏请求默认都会是 HTTP GET 类型的。**
- ***@RequestMapping\* 注解有以下几个变体，是组合注解**
  - *@GetMapping*
  - *@PostMapping*
  - *@PutMapping*
  - *@DeleteMapping*
  - *@PatchMapping*
  - 以上注解相当于：*@RequestMapping(method =RequestMethod.XXX)*

## 6.5、优点

1. **使路径变得更加简洁：**
   - **传统方式**：`http://localhost:8080/springmvc_03/multiplication?a=7&b=3`；
   - **RESTful**：`http://localhost:8080/springmvc_03/multiplication/7/3`；
2. 获得参数更加方便，框架会自动进行类型转换；
3. 使用 *@PathVariable* 注解来绑定 URI 模板变量，路径变量的类型可以**约束请求参数**。如果类型不一致，则访问不到对应的请求方法；

# 7、转发和重定向

## 7.1、SpringMVC使用视图解析器

通过 *ModelAndView* 对象，根据 ***view\* 名称**和**视图解析器**，映射到指定页面。

- 使用视图解析器的方式属于**请求转发**，地址栏 *URL* 不会发生改变。
- 实际页面 *URL* ：（视图解析器前缀） + *viewName* +（视图解析器后缀）

> 视图解析器

```xml
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
      id="internalResourceViewResolver">
    <!-- 前缀 -->
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <!-- 后缀 -->
    <property name="suffix" value=".jsp"/>
</bean>
```

> Controller

```java
z@RequestMapping("/h1")
public String hello(Model model) {

    model.addAttribute("msg", "Hello Annotation：Model");
	// viewName
    return "hello";
}
```

**结果：**访问`http://localhost:8080/springmvc_03/h1`，地址栏 *URL* 不变。

请求转发到`http://localhost:8080/springmvc_03/WEB-INF/jsp/hello.jsp`。

## 7.2、SpringMVC不使用视图解析器

### 请求转发

`forward:`表示请求转发，后接转发路径。

可以省略，即直接返回 `/index.jsp`，默认为请求转发。

```java
@RequestMapping("/mvc/t1")
public String test1(Model model){

    model.addAttribute("msg","SpringMvcController请求转发");

    return "forward:/index.jsp";
}

@RequestMapping("/mvc/t2")
public String test2(Model model){

    model.addAttribute("msg","SpringMvcController请求转发");

    return "/index.jsp";
}
```

**结果：**访问`http://localhost:8080/springmvc_03/mvc/t1`，地址栏 *URL* 不变。

请求转发到`http://localhost:8080/springmvc_03/index.jsp`。

### 请求重定向

`forward:`表示请求重定向。

```java
@RequestMapping("/mvc/t3")
public String test3() {
    return "redirect:/index.jsp";
}
```

**结果：**访问`http://localhost:8080/springmvc_03/mvc/t3`，地址栏 *URL* 改变。

重定向到`http://localhost:8080/springmvc_03/index.jsp`。

## 7.3、使用ServletAPI

通过HttpServletResponse进行输出、重定向、转发

原生 Servlet API 实现，不需要视图解析器。

- *HttpServletRequest*：请求转发
- *HttpServletResponse*：请求重定向、页面打印、。

### 请求转发

> Controller

```java
@RequestMapping("/api/t1")
public void test1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 携带参数
    req.setAttribute("result", "ServletApiController：请求转发");
    // 请求转发
    req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req, resp);
}
```

**结果：**访问`http://localhost:8080/springmvc_03/api/t1`，地址栏 *URL* 不变。

请求转发到`http://localhost:8080/springmvc_03/WEB-INF/jsp/test.jsp`。

### 请求重定向

> Controller

```java
@RequestMapping("/api/t2")
public void test2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    // 请求重定向
    resp.sendRedirect(req.getContextPath() + "/index.jsp");
}
```

**结果：**访问`http://localhost:8080/springmvc_03/api/t2`，地址栏 *URL* 改变。

请求重定向到`http://localhost:8080/springmvc_03/index.jsp`。

### 页面打印

> Controller

```java
@RequestMapping("/api/t1")
public void test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.getWriter().println("ServletApiController被访问了");
}
```

**结果：**页面打印内容。

![img](https://gitee.com/tianzhendong/img/raw/master//images/2410011-20210813142733609-72060371.png)

# 8、数据接收和回显

## 8.1、处理提交数据

获取的请求参数，可能是一个字段（域），也可能是一个对象。

- **提交的域名称**和**处理方法的参数名**是否一致，决定了参数的获取方式；
- **提交的域名称**和**实体类的属性名**是否一致，决定了参数的获取方式。

### 提交的域名称和处理方法的参数名一致

**提交的域名称**和**处理方法的参数名**一致，会将域名称自动映射到处理方法的参数。

```java
@RequestMapping("/user/t1")
public String test1(Model model, String name) {

    // 1、获取请求参数
    System.out.println("请求参数：" + name);
    // 2、数据回显
    model.addAttribute("msg", name);
    // 3、设置视图
    return "test";
}
```

提交数据：

```java
http://localhost:8080/springmvc_03/user/t1?name=tian
```

### 提交的域名称和处理方法的参数名不一致

使用 *@RequestParam* 注解，设置提交的域名称。

```java
@RequestMapping("/user/t2")
public String test2(Model model, @RequestParam("username") String name) {
    // 1、获取请求参数
    System.out.println("请求参数：" + name);
    // 2、数据回显
    model.addAttribute("msg", name);
    // 3、设置视图
    return "test";
}
```

提交数据：

```java
http://localhost:8080/springmvc_03/user/t1?username=tian
```

### 提交对象

处理方法使用实体类接收请求参数，**提交的域名称**和**实体类的属性名**必须一致，才能自动映射。

> 实体类：User

```java
private int id;
private String name;
private int age;
```

> Controller

```java
@PostMapping("/user/t3")
public String test3(Model model, User user) {
    // 获取请求参数
    System.out.println("请求参数：" + user);
    // 设置视图
    return "test";
}
```

> 表单：用于提交参数

**域名称**和与**实体类的属性名**必须一致！

```jsp
<form action="user/t3" method="post">
    <label> ID：<input type="text" name="id"> </label><br>
    <label> 姓名：<input type="text" name="name"> </label><br>
    <label> 年龄：<input type="text" name="age"> </label><br>
    <input type="submit">
</form>
```

## 8.2、数据显示到前端

将数据显示到前端，有三种方式：

- ***ModelAndView***：既可以存储数据模型，又可以设置返回视图；
- ***Model***：用于存储数据模型；
- ***ModelMap***：继承 *LinkedMap*，具有 *LinkedMap* 的方法和特性。

```java
@RequestMapping("/data/t1")
public ModelAndView test1() {
    ModelAndView mv = new ModelAndView();
    // 数据模型
    mv.addObject("msg", "DataController ModelAndView");
    // 设置视图
    mv.setViewName("test");

    return mv;
}

@RequestMapping("/data/t2")
public String test2(Model model){
    // 数据模型
    model.addAttribute("msg","DataController Model");
    // 返回视图
    return "test";
}

@RequestMapping("/data/t3")
public String test3(ModelMap modelMap){
    // 数据模型
    modelMap.addAttribute("msg","DataController ModelMap");
    // 返回视图
    return "test";
}
```

# 9、乱码问题

在 *web* 开发中经常会遇到页面乱码问题，编写一个 *Filter* 过滤器解决乱码问题。

> CharacterEncodingFilter

```java
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {}
}
```

> web.xml：注册Filter

**注意**：

- `/`：匹配所有的请求，不包括 *JSP*
- `/*`：匹配所有的请求和 *JSP*

```xml
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>indi.tian.filter.CharacterEncodingFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

# 10、JSON

*JSON*（*JavaScript Object Notation*）：*JavaScript* 对象标记。

前后端分离时代：

* 后端部署后端，提供接口，提供数据
* 前端独立部署，负责渲染后端的数据

json用于数据交换

## 10.1、简介

> 什么是 *JSON*

- 是轻量级的**文本数据交换格式**；
- **独立于编程语言**；
- 使用 *JavaScript* 语法来描述数据对象（使用文本表示 *JS* 对象）；
- 具有自我描述性，易于理解。

## 10.2、JSON vs XML

*JSON* 和 *XML* 都用于接收 *web* 服务端的数据，通常使用字符串。

### 对比

**相同点**

- 具有自我描述性；
- 具有层级结构；
- 可以被大多数编程语言使用；
- 可通过 *JavaScript* 解析；
- 可使用 *AJAX* 传输；

**不同点**

- *JSON* 不需要结束标签；
- *JSON* 更加简短；
- *JSON* 读写速度更快；
- *JSON* 可以使用数组；

### JSON优于XML

- *JSON* 比 *XML* 更容易解析；
- *JSON* 可以使用现有的 *JavaScript* 对象解析；
- 对于AJAX来说，JSON 比XML数据加载更快、更简单：
  - 使用 *XML*
    - 获取 *XML* 文档；
    - 使用 *XML DOM* 来循环遍历文档；
    - 将数据解析并存储在变量中；
  - 使用 *JSON*
    - 获取 *JSON* 字符串；
    - 解析 *JSON* 字符串；

## 10.3、语法

*JSON* 语法是 *JavaScript* 语法的子集。

### JSON语法规则

- 数据用 *K-V* 键值对表示；
- 数据之间用逗号分隔；
- 大括号`{}`保存对象；
- 中括号`[]`保存数组。

```json
{
    "name" : "tian",
    "age"  : "17",
    "hobby": ["java","music","basketball"]
}
```

### JSON数据类型

*JSON* 值可以是：

- 数字：整数或浮点数；
- 字符串：写在双引号中；
- 逻辑值：*true* 或 *false*；
- 对象：写在大括号`{}`中；
- 数组：写在中括号`[]`中；
- 空值：*null*

### JSON对象

用大括号`{}`表示 *JSON* 对象，*JSON* 对象可以包含多个 *K-V* 键值对。

- *Key* 必须是字符串，*Value* 可以是合法的 *JSON* 数据类型（数字, 字符串, 布尔值, 对象, 数组或 null）；
- *Key* 和 *Value* 中使用冒号`:`分割；
- 多个 *K-V* 键值对之间使用逗号`,`分割。

**访问 *JSON* 对象属性**

通过点号`.`或中括号`[]`访问 *JSON* 对象，可以进行以下操作；

- 获取、修改属性值;
- 使用 *delete* 关键字删除属性。

```javascript
var person = {
    "name" : "tian",
    "age"  : "17",
    "hobby": ["java","music","basketball"]
}

var obj1 = person.name;
var obj2 = person["name"];

person.age = 10;
person["age"] = 10;

delete person.hobby;
delete person[hobby];
```

### JSON数组

用中括号`[]`表示 *JSON* 数组。

- 数组值必须是合法的 *JSON* 数据类型（数字, 字符串, 布尔值, 对象, 数组或 *null*）；
- 多个数组值之间使用逗号`,`分割。

**访问 *JSON* 数组值**

通过索引值（下标从0开始）访问数组，可以进行以下操作；

- 获取、修改属性值;
- 使用 *delete* 关键字删除属性。

```javascript
var person = {
    "name" : "tian",
    "age"  : "17",
    "hobby": ["java","music","basketball"]
}

var obj = person.hobby[0];

person.hobby[0] = "spring";

delete person.hobby[0];
```

### JSON文件

- *JSON* 文件的 **文件** 类型：`.json`
- *JSON* 文本的 ***MIME*** 类型：`application/json`

## 10.4、JSON和JS对象转换

*JSON* 通常用于与服务端交换数据。

- 从服务器**接收**数据：将 *JSON* 字符串解析为 *JavaScript* 对象；
- 向服务器**发送**数据：将 *JavaScript* 对象 转换为 *JSON* 字符串。

### parse

使用 ***JSON.parse()*** 方法将 *JSON* 字符串转换为 *JavaScript* 对象：即字符串 → 对象。

**JSON解析实例**

- 假设从服务器接收到以下数据

  ```json
  { "name":"tian", "age":"17" }
  ```

- 使用 ***JSON.parse()*** 方法解析数据，将其转化为 *JavaScript* 对象

  ```javascript
  var obj = JSON.parse('{ "name":"tian", "age":"17" }');
  ```

### stringify

使用 ***JSON.stringify()*** 方法将 *JavaScript* 对象转换为 *JSON* 字符串：即对象→ 字符串。

**JSON对象转换实例**

- 假设要向服务器发送以下数据

  ```json
  var obj = { "name":"tian", "age":"17" }
  ```

- 使用 ***JSON.parse()*** 方法解析数据，将其转化为 *JavaScript* 对象

  ```javascript
  var myJson = JSON.stringify(obj);
  ```

## 10.5、测试

- **编写一个 JS 对象**

  ```javascript
  let person = {
      "name": "tian",
      "age": 17,
      "hobby": ["java", "music", "basketball"]
  }
  console.log(person)
  ```

  ![img](https://img2020.cnblogs.com/blog/2410011/202108/2410011-20210814235708591-1441934659.png)

- **将 *JS* 对象转换为 *JSON* 字符串**

  ```javascript
  let myJson = JSON.stringify(person)
  console.log(myJson)
  ```

  ![img](https://img2020.cnblogs.com/blog/2410011/202108/2410011-20210814235716548-1342417125.png)

- **将 *JSON* 字符串转换为 *JS* 对象**

  ```javascript
  let obj = JSON.parse(myJson)
  console.log(obj)
  ```

  ![img](https://img2020.cnblogs.com/blog/2410011/202108/2410011-20210814235711715-2034007843.png)

# 11、Java中使用JSON

*Java* 中常用的 *JSON* 类库： *jackson*、*fastjson*、*Gson*、 *JSON-lib* 等。

## 11.1、Jackson

### 环境搭建

1. 新建 *module*，添加 *web* 框架支持：在项目结构中手动添加 *lib* 目录；

2. [导入依赖](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)

   ```xml
   <dependency>
       <groupId>com.fasterxml.jackson.core</groupId>
       <artifactId>jackson-databind</artifactId>
       <version>2.12.4</version>
   </dependency>
   ```

3. *web.xml*：注册 *DispatcherServlet*

   - 关联配置文件；
   - 启动优先级；

4. *springmvc-servlet.xml*：添加注解支持、内部资源视图解析器。

5. 实体类：*User*

   ```java
   private int id;
   private String name;
   private int age;
   ```

### @ResponseBody

*@ResponseBody* 注解的作用：将处理方法返回的对象写到页面中。

1. 将 *Controller* 方法返回的对象，转换为指定的格式（通常是 *JSON* 或 *XML*）；
2. 写入到 *response* 对象的 *body* 区。

**举例**

```java
@Controller
public class UserController {
    
    @ResponseBody
    @RequestMapping("/user/t1")
    public String test1() {
        // 创建User
        User user = new User(7, "tian", 17);
        // 返回User字符串
        return user.toString();
    }
}
```

**结果**

![img](https://img2020.cnblogs.com/blog/2410011/202108/2410011-20210814235731564-1720834877.png)

### @RestController

*@RestController* 相当于 *@ResponseBody* 和 *@Controller* 合在一起的作用。

- *Controller* 中的所有方法都会返回 *return* 中的内容；
- 视图解析器 *InternalResourceViewResolver* 不起作用，无法返回视图（ *JSP* 或 *HTML*）。

**举例**

```java
@RestController
public class UserRestController {
    
    @RequestMapping("/ur/t1")
    public String test1() {
        // 创建User
        User user = new User(7, "tian", 17);
        // 返回User字符串
        return user.toString();
    }
}
```

**结果**

![img](https://img2020.cnblogs.com/blog/2410011/202108/2410011-20210814235738832-446123181.png)

### ObjectMapper

要使用 *Jackson* 处理 *JSON* 数据，需要一个 *ObjectMapper* 对象。

```java
ObjectMapper mapper = new ObjectMapper();
Object obj = new Object();

// 转换为字符串
String objJson = mapper.writeValueAsString(obj);
// 转换为字节流
byte[] objBytes = mapper.writeValueAsBytes(obj);
// 转换为文件
mapper.writeValue(new File("obj.json"), user);

// 读取字符串解析对象
User obj1 = mapper.readValue(objJson, Object.class);
// 读取字节流解析对象
User obj2 = mapper.readValue(objBytes, Object.class);
// 读取文件解析对象
User obj3 = mapper.readValue(new File("obj.json"), Object.class);
```

- 除了对 **Java 类**进行转换，还可以对**集合**和**日期类**进行转换。

### 解决乱码问题

乱码问题有两种解决方案。

1. ***produces\* 属性**

```java
@RequestMapping(value = "/user/j1", produces = "application/json;charset=utf-8")
```

- 在处理方法的 *@RequestMapping* 注解使用 *produces* 属性；
- 如果多个方法要使用，则需要在每个处理方法上写。

1. **配置文件（推荐）**

   ```xml
   <mvc:annotation-driven>
       <mvc:message-converters register-defaults="true">
           <bean class="org.springframework.http.converter.StringHttpMessageConverter">
               <constructor-arg value="UTF-8"/>
           </bean>
           <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
               <property name="objectMapper">
                   <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                       <property name="failOnEmptyBeans" value="false"/>
                   </bean>
               </property>
           </bean>
       </mvc:message-converters>
   </mvc:annotation-driven>
   ```

   - 在配置文件中统一配置；
   - 统一配置后，就可以在全局使用。

### 测试

使用 *@ResponseBody* 和 *@RestController* 可以达到一样的效果，以下测试采用第一种注解。

>  Java类

- j1：将 ***User*** 对象转换为 ***JSON\* 字符串**。

```java
@ResponseBody
@RequestMapping("/user/j1")
public String json1() throws JsonProcessingException {
    // 创建ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    // 创建User
    User user = new User(7,"路人甲",10);
    // 返回JSON字符串
    return mapper.writeValueAsString(user);
}
```

>  集合

- j2：将 ***List*** 集合转换为 ***JSON\* 字符串**；
- j3：将 ***Map*** 集合转换为 ***JSON\* 字符串**。

```java
@ResponseBody
@RequestMapping("/user/j2")
public String json2() throws JsonProcessingException {
    // 创建ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    // 创建List集合
    List<User> list = new ArrayList<>();

    User user1 = new User(3, "张三", 20);
    User user2 = new User(4, "李四", 17);
    User user3 = new User(5, "王五", 30);
    list.add(user1);
    list.add(user2);
    list.add(user3);

    return mapper.writeValueAsString(list);
}

@ResponseBody
@RequestMapping("/user/j3")
public String json3() throws JsonProcessingException {
    // 创建ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    // 创建List集合
    HashMap<String, Object> map = new HashMap<>();

    map.put("name", "tian");
    map.put("age", 17);

    return mapper.writeValueAsString(map);
}
```

>  日期

- j4：将**日期**对象转换为**时间戳**；
- j5：可以自定义日期格式。

```java
@ResponseBody
@RequestMapping("/user/j4")
public String json4() throws JsonProcessingException {
    // 创建ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    // 创建Date
    Date date = new Date();
    // 返回JSON
    return mapper.writeValueAsString(date);
}

@ResponseBody
@RequestMapping("/user/j5")
public String json5() throws JsonProcessingException {
    // 创建ObjectMapper
    ObjectMapper mapper = new ObjectMapper();
    // 自定义日期格式
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    mapper.setDateFormat(sdf);
    // 创建Date
    Date date = new Date();
    // 返回JSON
    return mapper.writeValueAsString(date);
}
```

**j1-j5测试结果**

![img](https://img2020.cnblogs.com/blog/2410011/202108/2410011-20210814235753848-1261533064.png)

>  工具类

**Jackson 的使用步骤如下：**

1. 创建 *ObjectMapper* 对象；
2. 创建对象
   - 对象类型：*pojo*、日期类、集合；
   - 如果是日期类，可以自定义日期格式；
3. 将对象转换为 *JSON* 字符串。

**将以上重复的代码抽取，封装成工具类。**

```java
package indi.tian.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.Date;


public class JsonUtils {
    /**
     * ObjectMapper
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 对象转换后的JSON字符串
     */
    private static String OBJ_JSON;

    /**
     * 获取pojo、集合类的JSON字符串
     */
    public static String getJson(Object obj) {
        // 转换为JSON字符串
        try {
            OBJ_JSON = MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return OBJ_JSON;
    }

    /**
     * 获取默认日期格式的JSON字符串
     */
    public static String getDateJson(Date date) {
        return getDateJson(date, "yyyy-MM-dd hh-mm-ss");
    }

    /**
     * 获取指定日期格式的JSON字符串
     */
    public static String getDateJson(Date date, String dateFormat) {
        // 自定义日期格式
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        MAPPER.setDateFormat(sdf);
        // 转换为JSON字符串
        try {
            OBJ_JSON = MAPPER.writeValueAsString(date);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return OBJ_JSON;
    }
}
```

## 11.2、fastjson

*fastjson* 是阿里开发的 *Java* 类库，可以方便地实现 *Java* 对象和 *JSON* 格式之间的转换。

### 环境搭建

1. 新建 *module*，添加 *web* 框架支持：在项目结构中手动添加 *lib* 目录；

2. [导入依赖](https://mvnrepository.com/artifact/com.alibaba/fastjson)

   ```xml
   <dependency>
       <groupId>com.alibaba</groupId>
       <artifactId>fastjson</artifactId>
       <version>1.2.78</version>
   </dependency>
   ```

3. *web.xml*：注册 *DispatcherServlet*

   - 关联配置文件；
   - 启动优先级；

4. *springmvc-servlet.xml*：添加注解支持、内部资源视图解析器。

5. 实体类：User

   ```java
   private int id;
   private String name;
   private int age;
   ```

### JSON

*JSON* 类似 *Jackson* 中的 *ObjectMapper*，用于处理 *JSON* 数据。

不同的是，*fastJson* 无需 *new* 对象，提供静态方法，类似 *Jackson* 中的工具类。

```java
Object obj = new Object();

// 转换为字符串
String objJson = JSON.toJSONString(obj);
// 转换为字节流
byte[] objBytes = JSON.toJSONBytes(obj);

// 读取字符串解析对象
Object obj1 = JSON.parse(objJson);
// 读取字节流解析对象
Object obj2 = JSON.parse(objBytes);
```

### 测试

>  Java类

- j1：将 ***User*** 对象转换为 ***JSON\* 字符串**。

```java
@ResponseBody
@RequestMapping("/uf/j1")
public String json1() {
    User user = new User(7, "路人甲", 10);

    return JSON.toJSONString(user);
}
```

> 集合

- j2：将 ***List*** 集合转换为 ***JSON\* 字符串**；
- j3：将 ***Map*** 集合转换为 ***JSON\* 字符串**。

```java
@ResponseBody
@RequestMapping("/uf/j2")
public String json2() {
    // 创建List集合
    List<User> list = new ArrayList<>();

    User user1 = new User(3, "张三", 20);
    User user2 = new User(4, "李四", 17);
    User user3 = new User(5, "王五", 30);
    list.add(user1);
    list.add(user2);
    list.add(user3);

    return JSON.toJSONString(list);
}

@ResponseBody
@RequestMapping("/uf/j3")
public String json3() {
    // 创建List集合
    HashMap<String, Object> map = new HashMap<>();
    map.put("name", "tian");
    map.put("age", 17);

    return JSON.toJSONString(map);
}
```

> 日期

- j4：将**日期**对象转换为**时间戳**；
- j5：可以自定义日期格式，*fastjson* 提供了默认日期格式。

```java
@ResponseBody
@RequestMapping("/uf/j4")
public String json4() {

    Date date = new Date();
    return JSON.toJSONString(date);
}

@ResponseBody
@RequestMapping("/uf/j5")
public String json5() {

    Date date = new Date();
    return JSON.toJSONStringWithDateFormat(date, JSON.DEFFAULT_DATE_FORMAT);
}
```

**j1-j5测试结果**

![img](https://img2020.cnblogs.com/blog/2410011/202108/2410011-20210814235801778-754485332.png)

如果一切正常，但是报 *500*。检查项目结构的 lib 目录中是否有相关 *jar* 包。

