---
title: 《MySQL》必知必会
tags: java
notebook: JAVA
---

[toc]

# 1、了解MySQL

## 数据库（DB,DataBase）
保存有组织的数据的容器（通常是一个文件或者一组文件）

注：数据库不同于数据库软件，数据库软件应成为DBMS(数据库管理系统)

数据库分类：

* 关系型数据库SQL：通过表和表之间，行和列之间的关系进行数据的存储
  * MySQL
  * Oracle
  * Sql Server
  * DB2
  * SQLlite
* 非关系型数据库NoSQL（Not Only SQL）：对象存储，通过对象的自身的属性决定
  * Redis
  * MongDB

### 表

某种特定类型数据的结构化清单
### 列和数据类型
列：表中的一个字段。所有表都是由一个或多个列组成的。

数据类型（datatype） 所容许的数据的类型。每个表列都有相应的数据类型，它限制（或容许）该列中存储的数据。

### 行
行（row） 表中的一个记录。

### 主键
主键（primary key） 一列（或一组列），其值能够唯一区分表中每个行。

## SQL

### SQL简介
SQL（发音为字母S-Q-L或sequel）是结构化查询语言（Structured QueryLanguage）的缩写。 SQL是一种专门用来与数据库通信的语言

数据的所有存储、检索、管理和处理实际上是由数据库软件——DBMS（ 数据库管理系统）完成的。 MySQL是一种DBMS，即它是一种数据库软件。

### 客户机-服务器软件
DBMS可分为两类：一类为基于共享文件系统的DBMS，另一类为基于客户机—服务器的DBMS。 前者（包括诸如Microsoft Access和FileMaker）用于桌面用途，通常不用于高端或更关键的应用。

MySQL、 Oracle以及Microsoft SQL Server等数据库是基于客户机—服务器的数据库。客户机—服务器应用分为两个不同的部分。 服务器部分是负责所有数据访问和处理的一个软件。这个软件运行在称为数据库服务器的计算机上。
与数据文件打交道的只有服务器软件。关于数据、数据添加、删除和数据更新的所有请求都由服务器软件完成。这些请求或更改来自运行客户机软件的计算机。 客户机是与用户打交道的软件。例如，如果你请求一个按字母顺序列出的产品表，则客户机软件通过网络提交该请求给服务器软件。服务器软件处理这个请求，根据需要过滤、丢弃和排序数据；然后把结果送回到你的客户机软件。

客户机和服务器软件可能安装在两台计算机或一台计算机上。不管它们在不在相同的计算机上，为进行所有数据库交互，客户机软件都要与服务器软件进行通信

## MySQL

MySQL是一个关系型数据库管理系统，属于Oracle旗下产品，关系数据库将数据保存在不同的表中

*  是最好的RDBMS（关系数据库管理系统）应用软件之一
* 开源
* 体积小、速度快，总体拥有成本低

# 2、使用MySQL

为了连接到MySQL，需要以下信息：

* 主机名（计算机名）——如果连接到本地MySQL服务器， 为localhost；
* 端口（如果使用默认端口3306之外的端口）；
* 一个合法的用户名；
* 用户口令（如果需要）。


```sql
--使用root登录
mysql -u root -p;

--显示可用的数据库列表
SHOW databases;

--选择数据库
USE crashcourse;

--获得一个数据库内的表的列表
SHOW tables;

--显示数据库中所有表的信息
describe student;

--创建一个数据库
create databases school;

--显示表列
SHOW columns from customers;

--显示广泛的服务器状态信息
SHOW status;

--分别用来显示创建特定数据库或表的MySQL语句
SHOW create database;
SHOW create table;

--显示授予用户（所有用户或特定用户）的安全权限
SHOW Grants;

--显示服务器错误或警告消息
SHOW errors;
SHOW warnings;

--推出连接
exit;
--表示单行注释
/*
多行注释
*/
```

# 3、操作数据库

操作数据库——>操作数据库中的表——>操作数据库中表的数据

## 操作数据库

```sql
--增加数据库school1（在不存在时
CREATE DATABASES IF NOT EXISTS school1;	

--删除数据库
DROP DATABASE IF EXISTS school1;		

--使用数据库
USE school;		

--查看所有的数据库
SHOW DATABASES;
```

## 操作数据库表

### 创建数据库表

```sql
--创建数据库表
CREATE TABLE [IF NOT EXISTS] `表名`(
	`字段名` 列类型 [属性] [索引] [注释],
    `字段名` 列类型 [属性] [索引] [注释],
    `字段名` 列类型 [属性] [索引] [注释]
)[表类型] [字符集设置] [注释]
--实例
CREATE TABLE `student` (
  `id` int NOT NULL COMMENT '学员id',
  `name` varchar(100) NOT NULL COMMENT '学员姓名',
  `age` int NOT NULL COMMENT '学员年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3

--查看创建数据表table的语句
SHOW CREATE TABLE student;

--显示表的结构
DESC student;
```

ENGINE=InnoDB 表示采用的数据库引擎

数据库引擎主要有：

* INNODB：安全性高，支持事务处理和多表多用户操作
* MYISAM：节约空间，速度快

|            | MYISAM | INNODB        |
| ---------- | ------ | ------------- |
| 事务支持   | 不支持 | 支持          |
| 数据行锁定 | 不支持 | 支持          |
| 外键       | 不支持 | 支持          |
| 全文索引   | 支持   | 不支持        |
| 表空间大小 | 较小   | 较大，约为2倍 |

所有的数据库文件都储存在data目录下，本质还是文件的存储

### 修改删除表

```sql
--修改表名
ALTER TABLE teacher RENAME AS teacher1;

--增加表的字段  表名 ADD 字段名 列属性
ALTER TABLE teacher1 ADD age INT(11);

--修改表的字段，重命名,修改约束
ALTER TABLE teacher1 MODIFY age VARCHAR(11);	--修改约束：列属性
ALTER TABLE teacher1 CHANGE age age1 INT(1);	--修改约束：重命名

--删除表的字段
ALTER TABLE teacher1 DROP age1;

--删除表
DROP TABLE IF EXISTS teacher1;
```

MODIFY和CHANGE区别：

* change用来字段重命名
* modify不用来字段重命名，只能修改字段类型和约束

# 4、MySQL数据管理

## 外键

### 物理外键

数据库级别的外键，不建议使用，数据库之间有关联，容易导致混乱

1. 创建表时添加外键

```sql
CREATE TABLE `student` (
  `id` int NOT NULL COMMENT '学员id',
  `name` varchar(100) NOT NULL COMMENT '学员姓名',
  `age` int NOT NULL COMMENT '学员年龄',
  `gradeid` int NOT NULL COMMENT `年级id`		--年级表的主键
  PRIMARY KEY (`id`),	--主键
  KEY `FK_gradeid` (`gradeid`),		--外键
  CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`)    --给外键添加约束
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3
```

2. 创建后添加外键

```sql
ALTER TABLE `student`
ADD CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade`(`gradeid`)
```

### 最佳实践

* 数据库就是单纯的表，只用来存储数据，只有行（数据）和列（字段）
* 使用程序去实现外键功能

# 5、DML语言

数据库操作语言

* insert添加
* update修改
* delete删除

## insert添加

```sql
--插入语句
--insert into `表名` (`字段`) values('value1'),('value2')...
INSERT INTO `student`('name')
VALUES ('zhansan'),
('lisi');

INSERT INTO `student`('name','sex')
VALUES ('zhansan','男'),
('lisi','男');
```

字段可以省略

### 插入完整的行

```sql
INSERT INTO customers(cust_name,
    cust_contact,
    cust_email,
    cust_address,
    cust_city,
    cust_state,
    cust_zip,
    cust_country)
VALUES('Pep E.LaPew',
    NULL,
    NULL,
    '100 Main Street',
    'Los Angeles',
    'CA',
    '90046',
    'USA');
```

### 插入多个列

如果插入的顺序相同，那么可以在VALUES后再加一个括号，把另外的数据加上

### 插入检索出的数据

INSERT SELECT

```sql
--使用INSERT SELECT从custnew中将所有数据导入customers
INSERT INTO customers(cust_name,
                      cust_contact,
                      cust_email,
                      cust_address, 
                      cust_city,  
                      cust_state,    
                      cust_zip,    
                      cust_country)
SELECT cust_name,
		cust_contact,
        cust_email, 
        cust_address,  
        cust_city,    
        cust_state,  
        cust_zip,  
        cust_country
FROM custnew;
```

## update修改

```sql
--修改单个属性
UPDATE `student` 		--修改的表
SET `name`='tianzhendong'	--修改的内容
WHERE id=1;		--修改的具体行，不指定条件会修改所有的行

--修改多个属性
UPDATE `student` 		
SET `name`='tianzhendong',`email`='xxxx@qq.com'		--修改的内容
WHERE id=1;	

--更新10005客户的邮件地址
UPDATE customers
SET cust_email='elmer@fudd.com'
WHERE cust_id = 10005;

--更新多个列
UPDATE customers
SET cust_email='120@fudd.com',
    cust_name = 'tian'
WHERE cust_id = 10005;

--删除某个列的值，可设置其为NULL
UPDATE customers
SET cust_email=NULL
WHERE cust_id = 10005;
```

## 删除

### delete

```sql
--删除数据（避免这样写，会全部删除）
DELETE FROM `student`;

--删除指定数据
DELETE FROM `student`
WHERE id = 1;
```

### truncate

可使用TRUNCATE TABLE语句，它完成相同的工作，但速度更快（ TRUNCATE实际是删除原来的表并重新创建一个表，而不是逐行删除表中的数据）。

```sql
--清空student表，表的结构和索引约束不会变
TRUNCATE `student`;
```

### 区别

* 相同点：都能删除数据，都不会删除表结构

* 不同：
  * truncate**重新设置自增列，计数器归零**，delete不会影响自增
  * truncate不会影响事务

# 6、DQL查询数据

## 检索数据

```sql
--检索单个列
SELECT prod_name
FROM products;

--检索多个列
SELECT prod_id, prod_name, prod_price
FROM products;

--检索所有列
SELECT *
FROM products;

--只返回列中不同（唯一）的vend_id行
SELECT DISTINCT vend_id
FROM products;

--返回第一行或前几行
SELECT prod_name
FROM products
LIMIT 5;

--返回从行5开始的5行,检索出来的第一行为行0而不是行1。因此， LIMIT 1, 1将检索出第二行而不是第一行。如果没有足够的行只返回它能返回的那么多行。
SELECT prod_name
FROM products
LIMIT 5,5;

--使用完全限定的名字来引用列（同时使用表名和列字）
SELECT products.prod_name
FROM crashcourse.products;
```
多条SQL语句必须以分号（；）分隔。 MySQL如同多数DBMS一样，不需要在单条SQL语句后加分号。但特定的DBMS可能必须在单条SQL语句后加上分号。当然，如果愿意可以总是加上分号。事实上，即使不一定需要，但加上分号肯定没有坏处。如果你使用的是mysql命令行，必须加上分号来结束SQL语句。

SQL语句不区分大小写，因此SELECT与select是相同的。同样，写成Select也没有关系。
许多SQL开发人员喜欢对所有SQL关键字使用大写，而对所有列和表名使用小写，这样做使代码更易于阅读和调试。

在处理SQL语句时，其中所有空格都被忽略。 SQL语句可以在一行上给出，也可以分成许多行。多数SQL开发人
员认为将SQL语句分成多行更容易阅读和调试

## 排序检索数据

子句（clause） SQL语句由子句构成，有些子句是必需的，而有的是可选的。一个子句通常由一个关键字和所提供的数据组成。子句的例子有SELECT语句的FROM子句。

为了明确地排序用SELECT语句检索出的数据，可使用ORDER BY子句。
ORDER BY子句取一个或多个列的名字，据此对输出进行排序。

```java
--指示MySQL对prod_name列以字母顺序排序数据
SELECT prod_name
FROM products
ORDER BY prod_name;

--检索3个列，并按其中两个列对结果进行排序——首先按价格，然后再按名称排序
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price, prod_name;--仅在多个行具有相同的prod_price值时才对产品按prod_name进行排序

--按价格以降序排序产品（最贵的排在最前面）
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price DESC;

--DESC关键字只应用到直接位于其前面的列名。
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price DESC, prod_name;
--在上例中，只对prod_price列指定DESC，对prod_name列不指定。因此，prod_price列以降序排序，而prod_name列（在每个价格内）仍然按标准的升序排序

--使用ORDER BY和LIMIT的组合，能够找出一个列中最高或最低的值
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price DESC
LIMIT 1;
```

与DESC相反的关键字是ASC（ASCENDING）， 在升序排序时可以指定它。
但实际上， ASC没有多大用处，因为升序是默认的（如果既不指定ASC也不指定DESC，则假定为ASC）

在字典（ dictionary）排序顺序中，A被视为与a相同，这是MySQL（和大多数数据库管理系统）的默认行为。但是，许多数据库管理员能够在需要时改变这种行为（如果你的数据库包含大量外语字符，可能必须这样做）。

## 过滤数据
在同时使用ORDER BY和WHERE子句时，应该让ORDER BY位于WHERE之后， 否则将会产生错误

```JAVA
--在SELECT语句中，数据根据WHERE子句中指定的搜索条件进行过滤。
SELECT prod_price, prod_name
FROM products
WHERE prod_price = 2.50;

--在SELECT语句中，数据根据WHERE子句中指定的搜索条件进行过滤。
SELECT prod_price, prod_name
FROM products
WHERE prod_price != 2.50;--等同于WHERE prod_price <> 2.50;

--检索价格在5美元和10美元之间的所有产品
SELECT prod_price, prod_name
FROM products
WHERE prod_price BETWEEN 5 AND 10;

--返回没有价格（空prod_price字段，不是价格为0）的所有产品
SELECT prod_price, prod_name
FROM products
WHERE prod_price BETWEEN IS NULL;
```
## 组合WHERE子句

```java
--AND OR IN
--检索由供应商1003制造且价格小于等于10美元的所有产品的名称和价格
SELECT prod_id, prod_price, prod_name
FROM products
WHERE vend_id = 1003 AND prod_id <=10;

--检索由任一个指定供应商制造的所有产品的产品名和价格
SELECT prod_id, prod_price, prod_name
FROM products
WHERE vend_id = 1003 OR vend_id = 1002;

--IN和OR具有相同的功能
--检索供应商1002和1003制造的所有产品
SELECT prod_id, prod_price, prod_name
FROM products
WHERE vend_id in(1002,1003)
ORDER BY prod_name;

-- SQL（像多数语言一样）在处理OR操作符前，优先处理AND操作符。组合使用时应使用圆括号明确地分组相应的操作符
SELECT prod_name, prod_price
FROM products
WHERE (vend_id = 1002 OR vend_id = 1003) AND prod_price>=10;

--NOT NOT否定跟在它之后的条件，
-- 匹 配 1002 和 1003 之 外 供 应 商 的vend_id
--和!=  <>好像没有区别，只是not适用于复杂的语句
SELECT prod_id, prod_price, prod_name
FROM products
WHERE vend_id NOT IN (1002,1003)
ORDER BY prod_name;
```

## 用通配符进行过滤
前面介绍的所有操作符都是针对已知值进行过滤的。不管是匹配一个还是多个值，测试大于还是小于已知值，或者检查某个范围的值，共同点是过滤中使用的值都是已知的。但是，这种过滤方法并不是任何时候都好用。例如，怎样搜索产品名中包含文本anvil的所有产品？用简单的比较操作符肯定不行，必须使用通配符。利用通配符可创建比较特定数据的搜索模式。在这个例子中，如果你想找出名称包含anvil的所有产品，可构造一个通配符搜索模式，找出产品名中任何位置出现anvil的产品。

通配符：（wildcard） 用来匹配值的一部分的特殊字符。

搜索模式（search pattern）由字面值、通配符或两者组合构成的搜索条件。

为在搜索子句中使用通配符，必须使用LIKE操作符。 LIKE指示MySQL，后跟的搜索模式利用通配符匹配而不是直接相等匹配进行比较。

谓词 操作符何时不是操作符？答案是在它作为谓词（ predicate）时。从技术上说， LIKE是谓词而不是操作符。

```java
--百分号（ %）通配符
--在搜索串中， %表示任何字符出现任意次数。
--检索任意以jet起头的词。 %告诉MySQL接受jet之后的任意字符，不管它有多少字符。
SELECT prod_id, prod_name
FROM products
WHERE prod_name LIKE 'jet%';

--匹配任何位置包含文本anvil的值，而不论它之前或之后出现什么字符。
SELECT prod_id, prod_name
FROM products
WHERE prod_name LIKE '%anvil%';

--找出以s起头以e结尾的所有产品
SELECT prod_id, prod_name
FROM products
WHERE prod_name LIKE 's%e';

--下划线（ _）通配符
--下划线的用途与%一样，但下划线只匹配单个字符而不是多个字符。_总是匹配一个字符，不能多也不能少
```

注意：通配
符搜索的处理一般要比前面讨论的其他搜索所花时间更长。不要过度使用通配符。如果其他操作符能达到相同的目的，应该使用其他操作符。
* 在确实需要使用通配符时，除非绝对有必要，否则不要把它们用在搜索模式的开始处。把通配符置于搜索模式的开始处，搜索起来是最慢的。
* 仔细注意通配符的位置。如果放错地方，可能不会返回想要的数据。

## 正则表达式
MySQL中的正则表达式匹配（自版本3.23.4后）不区分大小写（即，大写和小写都匹配）。为区分大
小写，可使用BINARY关键字，如WHERE prod_name REGEXPBINARY 'JetPack .000'。

LIKE匹配整个列。如果被匹配的文本在列值中出现， LIKE将不会找到它，相应的行也不被返回（除非使用
通配符）。而REGEXP在列值内进行匹配，如果被匹配的文本在列值中出现， REGEXP将会找到它，相应的行将被返回。这是一个非常重要的差别。

```java
--REGEXP
--检索列prod_name包含文本1000的所有行
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '1000';

--检索列prod_name包含文本000的所有行, .是正则表达式语言中一个特殊的字符。它表示匹配任意一个字符，因此， 1000和2000都匹配
且返回
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '.000';

--搜索两个串之一（或者为这个串，或者为另一个串），使用|
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '1000|2000';

--匹配特定的字符
--正则表达式[123] Ton为[1|2|3] Ton的缩写，也可以使用后者。但是，需要用[]来定义OR语句查找什么。
-- /使用了正则表达式[123] Ton。 [123]定义一组字符，它的意思是匹配1或2或3，因此， 1 ton和2 ton都匹配且返回（没有3 ton）
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '[123] Ton';

--集合可用来定义要匹配的一个或多个字符
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '[1-3] Ton';

--为了匹配特殊字符，必须用\\为前导。 \\-表示查找-， \\.表示查找.
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '\\.';
```
### 匹配字符类：

|类|说明|
|-|-|
|[:alnum:]| 任意字母和数字（同[a-zA-Z0-9]）|
|[:alpha:] |任意字符（同[a-zA-Z]）|
|[:blank:] |空格和制表（同[\\t]）|
|[:cntrl:] |ASCII控制字符（ ASCII 0到31和127）|
|[:digit:] |任意数字（同[0-9]）|
|[:graph:] |与[:print:]相同，但不包括空格|
|[:lower:] |任意小写字母（同[a-z]）|
|[:print:] |任意可打印字符|
|[:punct:] |既不在[:alnum:]又不在[:cntrl:]中的任意字符|
|[:space:] |包括空格在内的任意空白字符（同[\\f\\n\\r\\t\\v]）|
|[:upper:] |任意大写字母（同[A-Z]）
|[:xdigit:] |任意十六进制数字（同[a-fA-F0-9]）|

### 匹配多个示例
可以用正则表达式重复元字符来完成
|元字符|说明|
|-|-|
|* |0个或多个匹配|
|+ |1个或多个匹配（等于{1,}）|
|? |0个或1个匹配（等于{0,1}）|
|{n} |指定数目的匹配|
|{n,} |不少于指定数目的匹配|
|{n,m}| 匹配数目的范围（ m不超过255）|

```java
SELECT prod_name
FROM products
WHERE prod_name REGEXP '\\([0-9] sticks?\\)';
-- \\(\\)使用\\匹配特殊字符
--sticks后跟？，使的s可有可无，可以出现0次或者1次，因此stick也会被匹配出来。

--匹配连在一起的4位数字
SELECT prod_name
FROM products
WHERE prod_name REGEXP '[[:digit:]]{4}';
--[:digit:]匹配任意数字，因而它为数字的一个集合。 {4}确切地要求它前面的字符（任意数字）出现4次，所以[[:digit:]]{4}匹配连在一起的任意4位数字。
--等同于下面：
SELECT prod_name
FROM products
WHERE prod_name REGEXP '[0-9][0-9][0-9][0-9]';
```

### 定位符
特定位置的文本需要使用定位符

|元 字 符| 说 明|
|-|-|
|^| 文本的开始|
|$ |文本的结尾|
|[[:<:]] |词的开始|
|[[:>:]] |词的结尾|

 LIKE和REGEXP的不同在于， LIKE匹配整个串而REGEXP匹配子串。利用定位符，通过用^开始每个表达式，用$结束每个表达式，可以使REGEXP的作用与LIKE一样。

```java
--找出以一个数（包括以小数点开始的数）开始的所有产品
SELECT prod_name
FROM products
WHERE prod_name REGEXP '^[0-9\\.]';
--^匹配串的开始。因此， ^[0-9\\.]只在.或任意数字为串中第一个字符时才匹配它们
```


## 创建计算字段

存储在表中的数据都不是应用程序所需要的。我们需要直接从数据库中检索出转换、计算或格式化过的数据；而不是检索出数据，然后再在客户机应用程序或报告程序中重新格式化。

>字段（field） 基本上与列（ column） 的意思相同，经常互换使用，不过数据库列一般称为列，而术语字段通常用在计算字段的连接上。
### 拼接字段
拼接（concatenate） 将值联结到一起构成单个值。在MySQL的SELECT语句中，可使用Concat()函数来拼接两个列。

>多数DBMS使用+或||来实现拼接，MySQL则使用Concat()函数来实现。当把SQL语句转换成MySQL语句时一定要把这个区别铭记在心。
```java
--Concat()拼接串，即把多个串连接起来形成一个较长的串。Concat()需要一个或多个指定的串，各个串之间用逗号分隔。
SELECT Concat(vend_name, '(', vend_country, ')')
FROM vendors
ORDER BY vend_name;
```
输出：

![image-20210726210622468](https:--gitee.com/tianzhendong/img/raw/master--images/image-20210726210622468.png)


```java
--删除数据右侧多余的空格来整理数据，这可以使用MySQL的RTrim()函数来完成
SELECT Concat(Rtrim(vend_name), '(', Rtrim(vend_country), ')')
FROM vendors
ORDER BY vend_name;
```
>Trim函数 MySQL除了支持RTrim()（正如刚才所见，它去掉串右边的空格），还支持LTrim()（去掉串左边的空格）以及Trim()（去掉串左右两边的空格）
### 别名
上述拼接过后实际上它没有名字，它只是一个值。如果仅在SQL查询工具中查看一下结果，这样没有什么不好。但是，一个未命名的列不能用于客户机应用中，因为客户机没有办法引用它。

为了解决这个问题， SQL支持列别名。 别名（ alias） 是一个字段或值的替换名。别名用AS关键字赋予。
```java
SELECT Concat(Rtrim(vend_name), '(', Rtrim(vend_country), ')') AS vend_title
FROM vendors
ORDER BY vend_name;
```

### 执行算数计算
```java
--汇总物品的价格（单价乘以订购数量）
SELECT prod_id, quantity, item_price, quantity*item_price AS expanded_price
FROM orderitems
where order_num = 20005;
```
输出：

![image-20210726210649949](https:--gitee.com/tianzhendong/img/raw/master--images/image-20210726210649949.png)

## 使用数据处理函数

与其他大多数计算机语言一样， SQL支持利用函数来处理数据。函数一般是在数据上执行的，它给数据的转换和处理提供了方便。

### 使用函数
大多数SQL实现支持以下类型的函数。
* 用于处理文本串（如删除或填充值，转换值为大写或小写）的文本函数。
* 用于在数值数据上进行算术操作（如返回绝对值，进行代数运算）的数值函数。
* 用于处理日期和时间值并从这些值中提取特定成分（例如，返回两个日期之差，检查日期有效性等）的日期和时间函数。
* 返回DBMS正使用的特殊信息（如返回用户登录信息，检查版本细节）的系统函数。

### 文本处理函数
|函 数| 说 明|
|-|-|
|Left() |返回串左边的字符|
|Length()| 返回串的长度|
|Locate() |找出串的一个子串|
|Lower() |将串转换为小写|
|LTrim()| 去掉串左边的空格|
|Right() |返回串右边的字符|
|RTrim(a)| 去掉串右边的空格|
|Soundex()| 返回串的SOUNDEX值|
|SubString()| 返回子串的字符|
|Upper(a) |将串转换为大写|

SOUNDEX是一个将任何文本串转换为描述其语音表示的字母数字模式的算法。 SOUNDEX考虑了类似的发音字符和音节，使得能对串进行发音比较而不是字母比较。

### 日期和时间处理函数
日期和时间采用相应的数据类型和特殊的格式存储，以便能快速和有效地排序或过滤，并且节省物理存储空间。

一般，应用程序不使用用来存储日期和时间的格式，因此日期和时间函数总是被用来读取、统计和处理这些值。
|函 数| 说 明|
|-|-|
|AddDate() |增加一个日期（天、周等）|
|AddTime() |增加一个时间（时、分等）|
|CurDate() |返回当前日期
|CurTime()| 返回当前时间
|Date()| 返回日期时间的日期部分|
|DateDiff() |计算两个日期之差
|Date_Add()| 高度灵活的日期运算函数|
|Date_Format() |返回一个格式化的日期或时间串|
|Day()| 返回一个日期的天数部分|
|DayOfWeek() |对于一个日期，返回对应的星期几|
|Hour()| 返回一个时间的小时部分|
|Minute()| 返回一个时间的分钟部分|
|Month()| 返回一个日期的月份部分|
|Now() |返回当前日期和时间
|Second()| 返回一个时间的秒部分|
|Time()| 返回一个日期时间的时间部分|
|Year()| 返回一个日期的年份部分|


无论你什么时候指定一个日期，不管是插入或更新表值还是用WHERE子句进行过滤，日期必须为格式yyyy-mm-dd。因此， 2005年9月1日，给出为2005-09-01。

```java
SELECT cust_id ,order_num 
FROM orders 
WHERE DATE(order_date) ='2005-09-01';

--检索出2005年9月下的所有订单
--方法1
SELECT cust_id ,order_num 
FROM orders 
WHERE DATE(order_date) BETWEEN '2005-09-01' AND '2005-09-30';
--方法2
SELECT cust_id ,order_num 
FROM orders 
WHERE Year(order_date)=2005 AND MONTH(order_date)=9;
```
### 数值处理函数
数值处理函数仅处理数值数据。这些函数一般主要用于代数、三角或几何运算，因此没有串或日期—时间处理函数的使用那么频繁。

|函 数 |说 明|
|-|-|
|Abs()| 返回一个数的绝对值|
|Cos() |返回一个角度的余弦|
|Exp() |返回一个数的指数值|
|Mod()| 返回除操作的余数|
|Pi() |返回圆周率|
|Rand()| 返回一个随机数|
|Sin() |返回一个角度的正弦|
|Sqrt() |返回一个数的平方根|
|Tan()| 返回一个角度的正切|

## 汇总数据
### 聚集函数
* 确定表中行数（或者满足某个条件或包含某个特定值的行数）。
* 获得表中行组的和。
* 找出表列（或所有行或某些特定的行）的最大值、最小值和平均值

聚集函数（ aggregate function） 运行在行组上，计算和返回单个值的函数。

|函 数| 说 明|
|-|-|
|AVG() |返回某列的平均值|
|COUNT()| 返回某列的行数|
|MAX()| 返回某列的最大值|
|MIN() |返回某列的最小值|
|SUM()| 返回某列值之和|

#### AVG()

AVG()只能用来确定特定数值列的平均值，而且列名必须作为函数参数给出。为了获得多个列的平均值，必须使用多个AVG()函数。


```java
--AVG()通过对表中行数计数并计算特定列值之和，求得该列的平均值。 AVG()可用来返回所有列的平均值，也可以用来返回特定列或行的平均值
--返回products表中所有产品的平均价格
SELECT AVG(prod_price) AS avg_price
FROM products;
--确定特定列或行的平均值。 下面的例子返回特定供应商所提供产品的平均价格
SELECT AVG(prod_price) AS avg_price
FROM products
WHERE vend_id = 1003;
```

#### COUNT()
COUNT()函数进行计数。 可利用COUNT()确定表中行的数目或符合特定条件的行的数目。

COUNT()函数有两种使用方式。
* 使用COUNT(*)对表中行的数目进行计数， 不管表列中包含的是空值（ NULL）还是非空值。
* 使用COUNT(column)对特定列中具有值的行进行计数，忽略NULL值

如果指定列名，则指定列的值为空的行被COUNT()函数忽略，但如果COUNT()函数中用的是星号（ *），则不忽
略。

```JAVA
--返回customers表中客户的总数
SELECT COUNT(*) AS num_cust
FROM customers;

--对具有电子邮件地址的客户计数
SELECT COUNT(cust_email) AS num_cust
FROM customers;
```
#### MAX()函数\MIN()函数
MAX()返回指定列中的最大值。 MAX()要求指定列名

MIN()的功能正好与MAX()功能相反，它返回指定列的最小值

>对非数值数据使用MAX()函数\MIN()函数,虽然MAX()函数\MIN()函数一般用来找出最大最小的数值或日期值，但MySQL允许将它用来返回任意列中的最大最小值，包括返回文本列中的最大最小值。在用于文本数据时，如果数据按相应的列排序，则MAX()函数\MIN()函数返回最后一行\第一行。

MAX()函数\MIN()函数忽略列值为NULL的行。

```java
--返回products表中最贵的物品的价格
SELECT MAX(prod_price) AS max_price
FROM products;

--返回products表中最便宜物品的价格
SELECT MIN(prod_price) AS min_price
FROM products;
```
#### SUM()函数
SUM()用来返回指定列值的和（总计）。\
```java
--检索所订购物品的总数（所有quantity值之和）
SELECT SUM(quantity) AS items_ordered
FROM orderitems
WHERE order_num = 20005;

--返回订单中所有物品价钱之和
SELECT SUM(quantity*item_price) AS total_price
FROM orderitems
WHERE order_num = 20005;
```

### 聚集不同的值

以上5个聚集函数都可以如下使用：
* 对所有的行执行计算，指定ALL参数或不给参数（因为ALL是默认行为）；
* 只包含不同的值，指定DISTINCT参数

>如果指定列名，则DISTINCT只能用于COUNT()。DISTINCT不能用于COUNT(*)，因此不允许使用COUNT（ DISTINCT），否则会产生错误。类似地， DISTINCT必须使用列名，不能用于计算或表达式
```java
--返回特定供应商1003提供的产品的平均价格(相同的产品只会计算一次)
SELECT AVG( DISTINCT prod_price) AS avg_price
FROM products
WHERE vend_id = 1003;
```
### 组合聚集函数
目前为止的所有聚集函数例子都只涉及单个函数。但实际上SELECT语句可根据需要包含多个聚集函数。

```JAVA
SELECT COUNT(*) AS num_items,
    MIN(prod_price) AS price_min,
    Max(prod_price) AS price_max,
    AVG(prod_price) AS price_avg,
    SUM(prod_price) AS price_sum
FROM products;
```
## 分组数据

```java
--返回vend_id=1005的数量
SELECT COUNT(*) AS num_prods 
FROM products
WHERE vend_id = 1005;

--创建数据分组,GROUP BY子句指示MySQL按vend_id排序并分组数据。
SELECT vend_id, COUNT(*) AS num_prods 
FROM products
GROUP BY vend_id;

--过滤数量大于等于3的。
--where只能过滤行，不能过滤分组、
--HAVING 用于过滤分组
SELECT vend_id, COUNT(*) AS num_prods 
FROM products
GROUP BY vend_id
HAVING COUNT(*)>=3;

--分组和排序
--GROUP BY子句用来按订单号（order_num列）分组数据，以便SUM(*)函数能够返回总计订单价格。 HAVING子句过滤数据，使得只返回总计订单价格大于等于50的订单。最后， 用ORDERBY子句排序输出
SELECT order_num, SUM(quantity*item_price) AS ordertotal
FROM orderitems
GROUP BY order_num
HAVING SUM(quantity*item_price)>=50
ORDER BY ordertotal;
```
## 使用子查询
### 子查询
查询（query） 任何SQL语句都是查询。但此术语一般指SELECT语句

SELECT语句是SQL的查询。迄今为止我们所看到的所有SELECT语句都是简单查询，即从单个数据库表中检索数据的单条语句。

SQL还允许创建子查询（ subquery） ，即嵌套在其他查询中的查询。

### 利用子查询进行过滤
```JAVA
--检索包含物品TNT2的所有订单的编号
SELECT order_num
FROM orderitems
WHERE prod_id = 'TNT2';
--检索具有前一步骤列出的订单编号的所有客户的ID。
SELECT cust_id
FROM orders
WHERE order_num IN(20005,20007);

--把第一个查询（返回订单号的那一个）变为子查询组合两个查询。
SELECT cust_id
FROM orders
WHERE order_num IN(SELECT order_num
                    FROM orderitems
                    WHERE prod_id = 'TNT2');

```

## 联结表

外键（foreign key）：外键为某个表中的一列，它包含另一个表的主键值，定义了两个表之间的关系。

可伸缩性（scale） 能够适应不断增加的工作量而不失败。设计良好的数据库或应用程序称之为可伸缩性好（ scale well） 。

联结是一种机制，用来在一条SELECT语句中关联表，因此称之为联结。使用特殊的语法，可以联结多个表返
回一组输出，联结在运行时关联表中正确的行。

### 创建联结
联结的创建非常简单，规定要联结的所有表以及它们如何关联即可。
```JAVA
--使用where联结
SELECT vend_name , prod_name, prod_price
FROM vendors, products
WHERE vendors.vend_id = products.vend_id
ORDER BY vend_name, prod_name;

--使用INNER JOIN联结，推荐
SELECT vend_name , prod_name, prod_price
FROM vendors INNER JOIN products
ON vendors.vend_id = products.vend_id
ORDER BY vend_name, prod_name;
```
### 联结多个表
SQL对一条SELECT语句中可以联结的表的数目没有限制。创建联结的基本规则也相同。首先列出所有表，然后定义表之间的关系
```JAVA
SELECT prod_name, vend_name , prod_price, quantity
FROM orderitems,products,vendors, 
WHERE products.vend_id=vendors.vend_id 
AND orderitems.prod_id = products.prod_id 
AND order_num =20005
ORDER BY vend_name, prod_name;
```

## 创建高级联结
### 使用别名

```JAVA
-- 使用别名
SELECT cust_name, cust_contact
FROM customers AS c,orders AS o, orderitems AS oi
WHERE c.cust_id = o.cust_id
    AND oi.order_num = o.order_num
    AND prod_id = 'TNT2';

--自联结
--在同一个表中搜索
SELECT p1.prod_id, p1.prod_name
FROM products AS p1, products AS p2
WHERE p1.vend_id = p2.vend_id
    AND p2.prod_id = 'DTNTR';
```


## 组合查询

```java
SELECT vend_id, prod_id ,prod_price
FROM products
WHERE prod_price<=5
UNION 
SELECT vend_id, prod_id ,prod_price
FROM products
WHERE vend_id IN(1001,1002);

--等同于
SELECT vend_id, prod_id ,prod_price
FROM products
WHERE prod_price<=5 OR vend_id IN (1001,1002);
```

UNION从查询结果集中自动去除了重复的行（换句话说，它的行为与单条SELECT语句中使用多个WHERE子句条件一样）。

使用UNION ALL， MySQL不取消重复的行。

在用UNION组合查询时，只能使用一条ORDER BY子句，它必须出现在最后一条SELECT语句之后。

## 全文本搜索
### 启用全文本搜索支持
一般在创建表时启用全文本搜索。 CREATE TABLE语句（第21章中介绍）接受FULLTEXT子句，它给出被索引列的一个逗号分隔的列表。
### 进行全文本搜索
使用两个函数Match()和Against()执行全文本搜索，其中Match()指定被搜索的列， Against()指定要使用的搜索表达式。
```java
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('rabbit');

--等同于
SELECT note_text
FROM productnotes
WHERE note_text LIKE '%rabbit%';

SELECT note_text
FROM productnotes
WHERE note_text REGEXP 'rabbit';
```
区别：

全文本搜索会根据匹配程度进行自动排序

### 使用查询扩展
找出所有提到anvils的注释。只有一个注释包含词anvils，但你还想找出可能与你的搜索有关的所有其他行，即使它们不包含词anvils。

在使用查询扩展时， MySQL对数据和索引进行两遍扫描来完成搜索：

```java
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('rabbit' WITH QUERY EXPANSION);
```

### 布尔文本搜索
```java
--查询有heavy但没有rope的行
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('heavy -rope*' IN BOOLEAN MODE);
```

# 7、事务

要么都成功，要么都失败

A给B转账，步骤1：A钱减少500，步骤2：B钱增加500

## ACID

参考：https://blog.csdn.net/dengjili/article/details/82468576

ACID，数据库事务正确执行的四个基本要素的缩写，是指数据库管理系统（DBMS）在写入或更新资料的过程中，为保证事务（transaction）是正确可靠的，所必须具备的四个特性：

* **原子性**（atomicity，或称不可分割性）：两个步骤1和2要么都成功，要么都失败，
* **一致性**（consistency）：最终一致性，事务前后的数据完整性要保证一致，转账前后A和B最终总钱数不变
* **隔离性**（isolation，又称独立性）：针对多个用户同时操作，排除其他事务对本次事务的影响
  * 同时C在给B转账，之间不会有影响
* **持久性**（durability）：事务一旦提交则不可逆，被持久化到数据库中，事务结束后的数据不随着外界原因导致数据丢失
  * 转账事务提交前断电，重启后，恢复到原状，数据状态为转账前的
  * 转账事务提交后断电，重启后，数据状态为转账后的

隔离性导致的问题：**事务的隔离级别：**

* **脏读：**一个事务读取了另外一个事务未提交的数据
* **不可重复读：**在一个事务内读取表中的某一行数据，多次读取结果不同。（这个不一定是错误，只是某些场合不对）
* **幻读（虚读）：**是指在一个事务内读取到了别的事务插入的数据，导致前后读取数量总量不一致。（一般是行影响，如下图所示：多了一行）

## 执行事务

mysql是默认开启事务自动提交的

```sql
--mysql是默认开启事务自动提交的
SET autocommit =0;	--关闭
SET autocommit =1;	--开启
```

事务的完整过程：

```sql
--1.关闭自动提交
SET autocommit =0;	--关闭自动提交

--2.标记一个事务的开始，从这个开始后的sql都在同一个事务内
START TRANSACTION;
INSERT XX

--3.1提交：持久化（成功！）
COMMIT;

--3.2回滚：回到原来的样子（失败）
ROLLBACK;

--4.事务结束
SET autocommit =1;	--开启自动提交

--了解
SAVEPOINT 保存点名	--设置一个事务的保存点
ROLLBACK TO SAVEPOINT 保存点名	--回滚到保存点
RELEASE SAVEPOINT 保存点名		--撤销保存点
```

# 8、索引

> 索引index是帮助MySQL高效获取数据的数据结构
>
> 提取句子主干，就可以得到索引的本质：索引是数据结构，MySQL中常用的索引结构（索引底层的数据结构）有：B-TREE ，B+TREE ，HASH 等

**优点**：

- 索引大大减小了服务器需要扫描的数据量

- 索引可以帮助服务器避免排序和临时表

- 索引可以将随机IO变成顺序IO

- 索引对于InnoDB（对索引支持行级锁）非常重要，因为它可以让查询锁更少的元组。在MySQL5.1和更新的版本中，InnoDB可以在服务器端过滤掉行后就释放锁，但在早期的MySQL版本中，InnoDB直到事务提交时才会解锁。对不需要的元组的加锁，会增加锁的开销，降低并发性。 InnoDB仅对需要访问的元组加锁，而索引能够减少InnoDB访问的元组数。但是只有在存储引擎层过滤掉那些不需要的数据才能达到这种目的。一旦索引不允许InnoDB那样做（即索引达不到过滤的目的），MySQL服务器只能对InnoDB返回的数据进行WHERE操作，此时，已经无法避免对那些元组加锁了。如果查询不能使用索引，MySQL会进行全表扫描，并锁住每一个元组，不管是否真正需要。

- - 关于InnoDB、索引和锁：InnoDB在二级索引上使用共享锁（读锁），但访问主键索引需要排他锁（写锁）



**缺点**

- 虽然索引大大提高了查询速度，同时却会降低更新表的速度，如对表进行INSERT、UPDATE和DELETE。因为更新表时，MySQL不仅要保存数据，还要保存索引文件。
- 建立索引会占用磁盘空间的索引文件。一般情况这个问题不太严重，但如果你在一个大表上创建了多种组合索引，索引文件的会膨胀很快。
- 如果某个数据列包含许多重复的内容，为它建立索引就没有太大的实际效果。
- 对于非常小的表，大部分情况下简单的全表扫描更高效；

索引只是提高效率的一个因素，如果你的MySQL有大数据量的表，就需要花时间研究建立最优秀的索引，或优化查询语句。

因此应该只为最经常查询和最经常排序的数据列建立索引。

MySQL里同一个数据表里的索引总数限制为16个。

## 索引的分类

```sql
--显示所有索引信息
SHOW INDEX FROM student;
```



* 主键索引*PRIMARY KEY*

唯一的标识，一张表只能有一个主键索引，不允许重复、不允许为 NULL；

```sql
 ALTER TABLE TableName ADD PRIMARY KEY(column_list);
```

* 唯一索引*UNIQUE KEY

避免重复的列出现，允许为 NULL 值，一张表可有多个唯一索引，索引列的值必须唯一，但允许有空值。如果是组合索引，则列值的组合必须唯一

```sql
ALTER TABLE TableName ADD UNIQUE (column_list); 
```

* 常规索引*KEY/INDEX*

一张表可以创建多个普通索引，一个普通索引可以包含多个字段，允许数据重复，允许 NULL 值插入；

```sql
ALTER TABLE TableName ADD INDEX IndexName(`字段名`(length));
```

* 全文索引*FullText*
  * 在特定的数据库引擎下才有，MylSAIM
  * 快速定位数据,它查找的是文本中的关键词，主要用于全文检索。

## 索引原则

* 索引不是越多越好
* 不要对进程变动数据加索引
* 小数据量的表不需要加索引
* 索引一般加在常用来查询的字段上

# 9、权限管理

## 用户管理

```sql
--创建用户
CREATE USER username IDENTIFIED BY 'password';

--修改用户密码
USE mysql	--使用mysql数据库
ALTER USER 'test'@'localhost' IDENTIFIED WITH MYSQL_NATIVE_PASSWORD BY '新密码';	--%代替localhost表示所有ip

--用户名重命名
RENAME USER oldname TO newname;

--用户授权
GRANT ALL PRIVILEGES ON *.* TO username;--全部的表，所有权限，除了给别人授权

--查看指定用户权限
SHOW GRANTS FOR username;

--撤销权限  revoke哪些权限， 在哪个表， 给谁
REVOKE ALL PRIVILEGES ON *.* FROM username;

--删除用户
DROP USER username;
```

# 10、数据库备份

* 直接拷贝物理文件
* 在可视化工具中导出
* 使用命令行mysqludmp

**导出**

```bash
mysqldump -h 主机 -u 用户名 -p 密码 数据库 表1 表2 表3 >物理磁盘位置/文件名
mysqldump -hlocalhost -uroot -p123456 school student >D:/a.sql
```

**导入**

```sql
--首先登陆数据库
source 物理磁盘位置/文件名.sql
```

# 11、规范数据库设计

## 为什么需要设计

当数据库比较复杂时，需要进行数据库设计

糟糕的设计：

* 数据冗余，浪费空间
* 数据增删麻烦，会产生异常
* 程序性能差



良好的设计：

* 节省内存空间
* 保证数据库的完整性
* 方便开发系统
* 避免使用物理外键



软件开发中，关于数据库的设计：

* 分析需求：分析业务和需要处理的数据库需求
* 概要设计：设计关系图E-R图



设计数据库步骤（个人博客）：

1. 收集信息，分析需求
   1. 用户表（用户登陆注销、个人信息，写博客，创建分类）
   2. 分类表（文章分类，谁创建的）
   3. 文章表（文章的信息）
   4. 评论表（评论信息）
   5. 友链表（友情链接信息）
   6. 自定义表（可以不需要）（系统信息，某个关键的字，或者一些主题）key：value
2. 标识实体（把需求落实到每个字段）
3. 标识实体之间的关系
   1. 写博客：user->blog
   2. 创建分类：user->category
   3. 关注：user->user
   4. 评论：user->user->blog

## 三大范式

为什么需要数据规范化：

* 信息重复
* 更新异常
* 插入异常
  * 无法正常显示信息
* 删除异常
  * 丢失有效的信息

**三大范式：**规范数据库的设计

* **第一范式（1NF）**
  * 要求数据库表中的每一列都是不可分割的原子数据项
* **第二范式（2NF）**
  * 前提：满足第一范式
  * 每张表只描述一件事情
* **第三范式（3NF）**
  * 前提：满足第一范式和第二范式
  * 确保数据表中的每一列数据都和主键直接相关，而不能间接相关

**规范性和性能的问题**：

* 关联查询的表不能超过三个
* 考虑商业化的需求和目标，数据库的性能更加重要
* 在规范性能的问题的时候，需要适当的考虑一下规范性
* 故意给某些表增加一些冗余字段（从多表查询变为单表查询）
* 故意增加一些计算列（从大数据量降低为小数据量的查询）

# 12、JDBC

## 概述

### 数据库驱动

应用程序通过数据库驱动和数据库链接、打交道。不同的数据库有不同的驱动

**应用程序——>MySQL驱动、Oracle驱动——>数据库**

### JDBC

> 架构中，没有什么是加一层解决不了的

SUN公司为了简化开发人员的（对数据库的统一）操作，提供了一个java操作数据库的规范，即JDBC

这些规范的实现由具体的厂商去做，对于开发人员，只需要掌握JDBC接口的操作即可

**应用程序——>JDBC——>MySQL驱动、Oracle驱动——>数据库**

## 第一个JDBC程序

1. **maven引入jdbc驱动，配置pom.xml**

数据库版本为8.0.26

```xml
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.26</version>
        </dependency>
```

2. **编写java程序**
   1. 加载驱动
   2. 连接数据库Drivermanager
   3. 获得执行sql对象Statement
   4. 获得返回的结果集ResultSet
   5. 释放连接

```java
package com.jdbc;

import java.sql.*;

/**
 * @author TianZhendong
 * @date 2021/8/5
 */
public class JdbcTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动    固定写法
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.用户信息和url
        /*school:连接的数据库
        useUnicode=true&characterEncoding=utf8&useSSL=true:支持中文编码&字符集utf8&使用安全连接*/
        String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "123456";

        //3.连接成功，数据库对象Connection代表数据库对象
        Connection connection = DriverManager.getConnection(url, username, password);

        //4.执行sql对象   statement执行sql的对象
        Statement statement = connection.createStatement();

        //5.执行sql对象去执行sql，可能存在结果，查看连接结果
        //sql语句
        String sql = "SELECT * FROM student";
        //返回的结果集，结果集中封装了查询出来的全部结果
        ResultSet resultSet = statement.executeQuery(sql);
        //输出结果
        while (resultSet.next()) {
            System.out.println("id = " + resultSet.getObject("id"));
            System.out.println("name = " + resultSet.getObject("name"));
            System.out.println("age = " + resultSet.getObject("age"));
            System.out.println("==============");
        }

        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
```

## JDBC对象解释

### Connection

```java
//3.连接成功，数据库对象Connection代表数据库对象
Connection connection = DriverManager.getConnection(url, username, password);
connection.rollback();  //事务回滚
connection.commit();    //事务提交
connection.setAutoCommit(true);   //设置事务自动提交
```



### Statement

执行sql的对象的类

```java
Statement statement = connection.createStatement();		//获取statement对象
ResultSet resultSet = statement.executeQuery(sql);  //查询操作，返回ResultSet结果集
boolean execute = statement.execute(sql);//执行任何sql
int i = statement.executeUpdate(sql);//更新、插入、删除，返回一个受影响的行数
```

### ResultSet

查询的结果集

```java
ResultSet resultSet = statement.executeQuery(sql);  //查询操作，返回ResultSet结果集

resultSet.getObject();  //返回结果，在不知道列的类型的情况下使用
resultSet.getString();
resultSet.getInt();
....
```

遍历操作

```java
resultSet.beforeFirst();    //移动到最前面
resultSet.afterLast();      //移动到最后面
resultSet.next();           //移动到下一个
resultSet.previous();       //移动到前一个
resultSet.absolute(row);    //移动到指定行
```

### 释放资源

```java
//6.释放连接
resultSet.close();
statement.close();
connection.close();
```

## 采用配置文件方式

**新增db.properties文件**

```properties
driver = com.mysql.cj.jdbc.Driver
url = jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=utf8&useSSL=true
username = root
password = 123456
```

**jdbc配置类：**

```java
package com.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author TianZhendong
 * @date 2021/8/5
 */

public class JdbcUtils {
   private static String driver=null;
   private static String url=null;
   private static String username=null;
   private static String password=null;
   static{
      try{
         //db.properties"为配置文件名
         InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
         Properties properties = new Properties();
         properties.load(in);
         driver=properties.getProperty("driver");
         url=properties.getProperty("url");
         username=properties.getProperty("username");
         password=properties.getProperty("password");
         //驱动加载
         Class.forName(driver);
      } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   /**
   * @Description: 获取连接
   * @Author: TianZD
   * @Date: 2021/8/5 19:15
   * @Param: []
   * @Return: java.sql.Connection
   */
   public static Connection getConnection() throws SQLException {
      return DriverManager.getConnection(url, username, password);
   }

   /**
   * @Description: 释放资源
   * @Author: TianZD
   * @Date: 2021/8/5 19:14
   * @Param: [conn, st, rs]
   * @Return: void
   */
   public static void release(Connection conn, Statement st, ResultSet rs) throws SQLException {
      if(rs!=null){
         rs.close();
      }
      if(st!=null){
         st.close();
      }
      if(conn!=null){
         conn.close();
      }
   }
}
```

java：

```java
package com.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.jdbc.JdbcUtils;

/**
 * @program: JDBCDemo
 * @description: test
 * @author: TianZD
 * @create: 2021-08-05 19:22
 **/
public class InsertTest {
   public static void main(String[] args) {
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try {
         //获取数据库连接
         connection = JdbcUtils.getConnection();
         //获得sql的执行对象
         statement = connection.createStatement();
         String sql = "INSERT INTO student(id, name, age)" +
               " VALUES(3, 'stu3', '25')";
         int i = statement.executeUpdate(sql);
         if (i > 0) {
            System.out.println("插入成功！");
         }
      } catch (SQLException throwable) {
         throwable.printStackTrace();
      } finally {
         JdbcUtils.release(connection,statement, null);
      }

   }
}
```

## SQL注入

SQL 注入（SQL Injection）是发生在 Web 程序中数据库层的安全漏洞，是网站存在最多也是最简单的漏洞。主要原因是**程序对用户输入数据的合法性没有判断和处理**，导致攻击者可以在 Web 应用程序中事先定义好的 **SQL 语句中添加额外的 SQL 语句**，在管理员不知情的情况下实现非法操作，以此来实现欺骗数据库服务器执行非授权的任意查询，从而进一步获取到数据信息。



简而言之，**SQL 注入就是在用户输入的字符串中加入 SQL 语句**，如果在设计不良的程序中忽略了检查，那么这些注入进去的 SQL 语句就会被数据库服务器误认为是正常的 SQL 语句而运行，攻击者就可以执行计划外的命令或访问未被授权的数据。

## PreparedStatement对象

可以防止SQL注入

本质是：把传递进来的参数当作字符

具体实现见DBCP部分代码

## 数据库连接池

数据库连接——执行完毕——释放

连接——释放很耗费资源

**池化技术：准备一些预先的资源，过来就连接预先准备好的**

* 最小连接数：根据常用连接数确定

* 最大连接数：业务最高承载上限

* 排队等待

* 等待超时

编写连接池，实现一个接口DataSource

> 开源数据源实现

* DBCP
* C3P0
* Druid阿里巴巴

使用这些数据库连接池后，在项目开发中就不需要编写连接数据库代码了

### DBCP

maven引入依赖：

```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.26</version>
    </dependency>
    <dependency>
        <groupId>commons-dbcp</groupId>
        <artifactId>commons-dbcp</artifactId>
        <version>1.4</version>
    </dependency>
</dependencies>
```

编写dbcp.properties

```properties
#连接设置  该部分格式不能变
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/school
username=root
password=123456

#下面的都可以不要
#<!-- 初始化连接 -->
initialSize=10

#最大连接数量
maxActive=50

#<!-- 最大空闲连接 -->
maxIdle=20

#<!-- 最小空闲连接 -->
minIdle=5

#<!-- 超时等待时间以毫秒为单位 6000毫秒/1000等于60秒 -->
maxWait=60000


#JDBC驱动建立连接时附带的连接属性属性的格式必须为这样：[属性名=property;]
#注意："user" 与 "password" 两个属性会被明确地传递，因此这里不需要包含他们。
connectionProperties=useUnicode=true;characterEncoding=gbk

#指定由连接池所创建的连接的自动提交（auto-commit）状态。
defaultAutoCommit=true

#driver default 指定由连接池所创建的连接的事务级别（TransactionIsolation）。
#可用值为下列之一：（详情可见javadoc。）NONE,READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
defaultTransactionIsolation=READ_UNCOMMITTED
```

编写配置类

```java
package com.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @program: JDBCDemo
 * @description: test
 * @author: TianZD
 * @create: 2021-08-05 23:17
 **/
public class JdbcUtils_Dbcp {
   private static DataSource dataSource = null;
   static{
      try{
         //dbcp.properties为配置文件名
         InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");
         Properties properties = new Properties();
         properties.load(in);

         //创建数据源  工厂模式
         dataSource = BasicDataSourceFactory.createDataSource(properties);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * @Description: 从数据源中获取连接
    * @Author: TianZD
    * @Date: 2021/8/5 19:15
    * @Param: []
    * @Return: java.sql.Connection
    */
   public static Connection getConnection() throws SQLException {
      return dataSource.getConnection();
   }

   /**
    * @Description: 释放资源
    * @Author: TianZD
    * @Date: 2021/8/5 19:14
    * @Param: [conn, st, rs]
    * @Return: void
    */
   public static void release(Connection conn, Statement st, ResultSet rs) {
      if(rs!=null){
         try {
            rs.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if(st!=null){
         try {
            st.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if(conn!=null){
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
}
```

测试

```java
package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @program: JDBCDemo
 * @description:
 * @author: TianZD
 * @create: 2021-08-05 23:23
 **/
public class TestDbcp {
   public static void main(String[] args) {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         connection = JdbcUtils_Dbcp.getConnection();
         //sql语句，其中value值先不赋予

         String sql = "insert into student(id, `name`, `age`) values(?,?,?)";

         //预先编译sql，先写sql，但不执行
         preparedStatement = connection.prepareStatement(sql);

         //手动value赋值
         preparedStatement.setInt(1, 3);
         preparedStatement.setString(2, "tian");
         preparedStatement.setInt(3, 25);

         //执行
         int i = preparedStatement.executeUpdate();
         if (i > 0) {
            System.out.println("插入成功！");
         }
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      } finally {
         JdbcUtils_Dbcp.release(connection, preparedStatement, null);
      }
   }
}
```

### C3P0

maven引入依赖

```xml
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.26</version>
        </dependency>
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.2</version>
        </dependency>
    </dependencies>
```

**配置文件，名字必须为c3p0-config**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<c3p0-config>
    <!--默认配置-->
    <default-config>
        <property name="initialPoolSize">10</property>
        <property name="maxIdleTime">30</property>
        <property name="maxPoolSize">100</property>
        <property name="minPoolSize">10</property>
        <property name="maxStatements">200</property>
    </default-config>

    <!--配置连接池mysql-->
    <named-config name="MySQL">
        <property name="driverClass">com.mysql.cj.jdbc.Driver</property>
        <property name="jdbcUrl">jdbc:mysql://localhost:3306/school</property>
        <property name="user">root</property>
        <property name="password">123456</property>
        <property name="initialPoolSize">10</property>
        <property name="maxIdleTime">30</property>
        <property name="maxPoolSize">100</property>
        <property name="minPoolSize">10</property>
        <property name="maxStatements">200</property>
    </named-config>

</c3p0-config>
```

配置类

```java
package com.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @program: JDBCDemo
 * @description:
 * @author: TianZD
 * @create: 2021-08-05 23:41
 **/
public class JdbcUtils_C3P0 {
   private static DataSource dataSource = null;
   static{
      try{
         //创建数据源  工厂模式
         dataSource = new ComboPooledDataSource("MySQL");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * @Description: 从数据源中获取连接
    * @Author: TianZD
    * @Date: 2021/8/5 19:15
    * @Param: []
    * @Return: java.sql.Connection
    */
   public static Connection getConnection() throws SQLException {
      return dataSource.getConnection();
   }

   /**
    * @Description: 释放资源
    * @Author: TianZD
    * @Date: 2021/8/5 19:14
    * @Param: [conn, st, rs]
    * @Return: void
    */
   public static void release(Connection conn, Statement st, ResultSet rs) {
      if(rs!=null){
         try {
            rs.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if(st!=null){
         try {
            st.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if(conn!=null){
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
}
```

测试

```java
package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @program: JDBCDemo
 * @description:
 * @author: TianZD
 * @create: 2021-08-05 23:45
 **/
public class TestC3P0 {
   public static void main(String[] args) {
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         connection = JdbcUtils_C3P0.getConnection();
         //sql语句，其中value值先不赋予

         String sql = "insert into student(id, `name`, `age`) values(?,?,?)";

         //预先编译sql，先写sql，但不执行
         preparedStatement = connection.prepareStatement(sql);

         //手动value赋值
         preparedStatement.setInt(1, 4);
         preparedStatement.setString(2, "tianzhendong");
         preparedStatement.setInt(3, 28);

         //执行
         int i = preparedStatement.executeUpdate();
         if (i > 0) {
            System.out.println("插入成功！");
         }
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      } finally {
         JdbcUtils_C3P0.release(connection, preparedStatement, null);
      }
   }
}
```







