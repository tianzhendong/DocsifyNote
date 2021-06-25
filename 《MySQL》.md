---
title: 《MySQL》必知必会
tags: java
notebook: JAVA
---

[toc]

# 了解MySQL
## 数据库
保存有组织的数据的容器（通常是一个文件或者一组文件）

注：数据库不同于数据库软件，数据库软件应成为DBMS(数据库管理系统)

### 表

某种特定类型数据的结构化清单
### 列和数据类型
列：表中的一个字段。所有表都是由一个或多个列组成的。

数据类型（datatype） 所容许的数据的类型。每个表列都有相
应的数据类型，它限制（或容许）该列中存储的数据。

### 行
行（row） 表中的一个记录。

### 主键
主键（primary key） 一列（或一组列），其值能够唯一区分表
中每个行。

## SQL

### SQL简介
SQL（发音为字母S-Q-L或sequel）是结构化查询语言（Structured Query
Language）的缩写。 SQL是一种专门用来与数据库通信的语言

数据的所有存储、
检索、管理和处理实际上是由数据库软件——DBMS（ 数据库管理系统）
完成的。 MySQL是一种DBMS，即它是一种数据库软件。

### 客户机-服务器软件
DBMS可分为两类：一类为基于共享文件系统的DBMS，另一类为基
于客户机—服务器的DBMS。 前者（包括诸如Microsoft Access和FileMaker）用于桌面用途，通常不用于高端或更关键的应用。

MySQL、 Oracle以及Microsoft SQL Server等数据库是基于客户机—服
务器的数据库。客户机—服务器应用分为两个不同的部分。 服务器部分是
负责所有数据访问和处理的一个软件。这个软件运行在称为数据库服务
器的计算机上。
与数据文件打交道的只有服务器软件。关于数据、数据添加、删除
和数据更新的所有请求都由服务器软件完成。这些请求或更改来自运行
客户机软件的计算机。 客户机是与用户打交道的软件。例如，如果你请
求一个按字母顺序列出的产品表，则客户机软件通过网络提交该请求给
服务器软件。服务器软件处理这个请求，根据需要过滤、丢弃和排序数
据；然后把结果送回到你的客户机软件。

客户机和服务器软件可能安装在两台计算
机或一台计算机上。不管它们在不在相同的计算机上，为进行
所有数据库交互，客户机软件都要与服务器软件进行通信

# 使用MySQL

为了连接到MySQL，需要以下信息：
* 主机名（计算机名）——如果连接到本地MySQL服务器， 为localhost；
* 端口（如果使用默认端口3306之外的端口）；
* 一个合法的用户名；
* 用户口令（如果需要）。


```java
//使用root登录
mysql -u root -p;

//显示可用的数据库列表
show databases；

//选择数据库
use crashcourse;

//获得一个数据库内的表的列表
show tables;

//显示表列
show columns from customers;

//显示广泛的服务器状态信息
show status;

//分别用来显示创建特定数据库或表的MySQL语句
show create database;
show create table;

//显示授予用户（所有用户或特定用户）的安全权限
Show Grants;

//显示服务器错误或警告消息
show errors;
show warnings;
```

# 检索数据

```java
//检索单个列
SELECT prod_name
FROM products;

//检索多个列
SELECT prod_id, prod_name, prod_price
FROM products;

//检索所有列
SELECT *
FROM products;

//只返回列中不同（唯一）的vend_id行
SELECT DISTINCT vend_id
FROM products;

//返回第一行或前几行
SELECT prod_name
FROM products
LIMIT 5;

//返回从行5开始的5行,检索出来的第一行为行0而不是行1。因此， LIMIT 1, 1将检索出第二行而不是第一行。如果没有足够的行只返回它能返回的那么多行。
SELECT prod_name
FROM products
LIMIT 5,5;

//使用完全限定的名字来引用列（同时使用表名和列字）
SELECT products.prod_name
FROM crashcourse.products;
```
多条SQL语句必须以分号（；）分隔。 MySQL
如同多数DBMS一样，不需要在单条SQL语句后加分号。但特
定的DBMS可能必须在单条SQL语句后加上分号。当然，如果
愿意可以总是加上分号。事实上，即使不一定需要，但加上
分号肯定没有坏处。如果你使用的是mysql命令行，必须加上
分号来结束SQL语句。

SQL语句不区分大小写，因此
SELECT与select是相同的。同样，写成Select也没有关系。
许多SQL开发人员喜欢对所有SQL关键字使用大写，而对所有
列和表名使用小写，这样做使代码更易于阅读和调试。

在处理SQL语句时，其中所有空格都被忽略。 SQL
语句可以在一行上给出，也可以分成许多行。多数SQL开发人
员认为将SQL语句分成多行更容易阅读和调试

# 排序检索数据

子句（clause） SQL语句由子句构成，有些子句是必需的，而
有的是可选的。一个子句通常由一个关键字和所提供的数据组
成。子句的例子有SELECT语句的FROM子句。

为了明确地排序用SELECT语句检索出的数据，可使用ORDER BY子句。
ORDER BY子句取一个或多个列的名字，据此对输出进行排序。

```java
//指示MySQL对prod_name列以字母顺序排序数据
SELECT prod_name
FROM products
ORDER BY prod_name;

//检索3个列，并按其中两个列对结果进行排序——首先按价格，然后再按名称排序
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price, prod_name;//仅在多个行具有相同的prod_price值时才对产品按prod_name进行排序

//按价格以降序排序产品（最贵的排在最前面）
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price DESC;

//DESC关键字只应用到直接位于其前面的列名。
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price DESC, prod_name;
//在上例中，只对prod_price列指定DESC，对prod_name列不指定。因此，prod_price列以降序排序，而prod_name列（在每个价格内）仍然按标准的升序排序

//使用ORDER BY和LIMIT的组合，能够找出一个列中最高或最低的值
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price DESC
LIMIT 1;
```

与DESC相反的关键字是ASC（ASCENDING）， 在升序排序时可以指定它。
但实际上， ASC没有多大用处，因为升序是默认的（如果既不指定ASC也
不指定DESC，则假定为ASC）

在字典（ dictionary）排序顺序中，A被视为与a相同，这是MySQL
（和大多数数据库管理系统）的默认行为。但是，许多数据库
管理员能够在需要时改变这种行为（如果你的数据库包含大量
外语字符，可能必须这样做）。

# 过滤数据

