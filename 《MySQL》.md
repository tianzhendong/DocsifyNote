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
SHOW databases;

//选择数据库
USE crashcourse;

//获得一个数据库内的表的列表
SHOW tables;

//显示表列
SHOW columns from customers;

//显示广泛的服务器状态信息
SHOW status;

//分别用来显示创建特定数据库或表的MySQL语句
SHOW create database;
SHOW create table;

//显示授予用户（所有用户或特定用户）的安全权限
SHOW Grants;

//显示服务器错误或警告消息
SHOW errors;
SHOW warnings;
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
在同时使用ORDER BY和WHERE子句时，应
该让ORDER BY位于WHERE之后， 否则将会产生错误

```JAVA
//在SELECT语句中，数据根据WHERE子句中指定的搜索条件进行过滤。
SELECT prod_price, prod_name
FROM products
WHERE prod_price = 2.50;

//在SELECT语句中，数据根据WHERE子句中指定的搜索条件进行过滤。
SELECT prod_price, prod_name
FROM products
WHERE prod_price != 2.50;//等同于WHERE prod_price <> 2.50;

//检索价格在5美元和10美元之间的所有产品
SELECT prod_price, prod_name
FROM products
WHERE prod_price BETWEEN 5 AND 10;

//返回没有价格（空prod_price字段，不是价格为0）的所有产品
SELECT prod_price, prod_name
FROM products
WHERE prod_price BETWEEN IS NULL;
```
# 组合WHERE子句

```java
//AND OR IN
//检索由供应商1003制造且价格小于等于10美元的所有产品的名称和价格
SELECT prod_id, prod_price, prod_name
FROM products
WHERE vend_id = 1003 AND prod_id <=10;

//检索由任一个指定供应商制造的所有产品的产品名和价格
SELECT prod_id, prod_price, prod_name
FROM products
WHERE vend_id = 1003 OR vend_id = 1002;

//IN和OR具有相同的功能
//检索供应商1002和1003制造的所有产品
SELECT prod_id, prod_price, prod_name
FROM products
WHERE vend_id in(1002,1003)
ORDER BY prod_name;

// SQL（像多数语言一样）在处理OR操作符前，优先处理AND操作符。组合使用时应使用圆括号明确地分组相应的操作符
SELECT prod_name, prod_price
FROM products
WHERE (vend_id = 1002 OR vend_id = 1003) AND prod_price>=10;

//NOT NOT否定跟在它之后的条件，
// 匹 配 1002 和 1003 之 外 供 应 商 的vend_id
//和!=  <>好像没有区别，只是not适用于复杂的语句
SELECT prod_id, prod_price, prod_name
FROM products
WHERE vend_id NOT IN (1002,1003)
ORDER BY prod_name;
```

# 用通配符进行过滤
前面介绍的所有操作符都是针对已知值进行过滤的。不管是匹配一
个还是多个值，测试大于还是小于已知值，或者检查某个范围的值，共
同点是过滤中使用的值都是已知的。但是，这种过滤方法并不是任何时
候都好用。例如，怎样搜索产品名中包含文本anvil的所有产品？用简单
的比较操作符肯定不行，必须使用通配符。利用通配符可创建比较特定
数据的搜索模式。在这个例子中，如果你想找出名称包含anvil的所有产
品，可构造一个通配符搜索模式，找出产品名中任何位置出现anvil的产
品。

通配符：（wildcard） 用来匹配值的一部分的特殊字符。

搜索模式（search pattern）由字面值、通配符或两者组合构成的搜索条件。

为在搜索子句中使用通配符，必须使用LIKE操作符。 LIKE指示MySQL，
后跟的搜索模式利用通配符匹配而不是直接相等匹配进行比较。

谓词 操作符何时不是操作符？答案是在它作为谓词（ predicate）时。从技术上说， LIKE是谓词而不是操作符。

```java
//百分号（ %）通配符
//在搜索串中， %表示任何字符出现任意次数。
//检索任意以jet起头的词。 %告诉MySQL接受jet之后的任意字符，不管它有多少字符。
SELECT prod_id, prod_name
FROM products
WHERE prod_name LIKE 'jet%';

//匹配任何位置包含文本anvil的值，而不论它之前或之后出现什么字符。
SELECT prod_id, prod_name
FROM products
WHERE prod_name LIKE '%anvil%';

//找出以s起头以e结尾的所有产品
SELECT prod_id, prod_name
FROM products
WHERE prod_name LIKE 's%e';

//下划线（ _）通配符
//下划线的用途与%一样，但下划线只匹配单个字符而不是多个字符。_总是匹配一个字符，不能多也不能少
```

注意：通配
符搜索的处理一般要比前面讨论的其他搜索所花时间更长。
* 不要过度使用通配符。如果其他操作符能达到相同的目的，应该
使用其他操作符。
* 在确实需要使用通配符时，除非绝对有必要，否则不要把它们用
在搜索模式的开始处。把通配符置于搜索模式的开始处，搜索起
来是最慢的。
* 仔细注意通配符的位置。如果放错地方，可能不会返回想要的数据。

# 正则表达式
MySQL中的正则表达式匹配（自版本
3.23.4后）不区分大小写（即，大写和小写都匹配）。为区分大
小写，可使用BINARY关键字，如WHERE prod_name REGEXP
BINARY 'JetPack .000'。


LIKE匹配整个列。如果被匹配的文本在列值
中出现， LIKE将不会找到它，相应的行也不被返回（除非使用
通配符）。而REGEXP在列值内进行匹配，如果被匹配的文本在
列值中出现， REGEXP将会找到它，相应的行将被返回。这是一
个非常重要的差别。
```java
//REGEXP
//检索列prod_name包含文本1000的所有行
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '1000';

//检索列prod_name包含文本000的所有行, .是正则表达式语言中一个特殊的字符。它表示匹配任意一个字符，因此， 1000和2000都匹配
且返回
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '.000';

//搜索两个串之一（或者为这个串，或者为另一个串），使用|
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '1000|2000';

//匹配特定的字符
//正则表达式[123] Ton为[1|2|3] Ton的缩写，也可以使用后者。但是，需要用[]来定义OR语句查找什么。
// /使用了正则表达式[123] Ton。 [123]定义一组字符，它的意思是匹配1或2或3，因此， 1 ton和2 ton都匹配且返回（没有3 ton）
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '[123] Ton';

//集合可用来定义要匹配的一个或多个字符
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '[1-3] Ton';

//为了匹配特殊字符，必须用\\为前导。 \\-表示查找-， \\.表示查找.
SELECT prod_id, prod_name
FROM products
WHERE prod_name REGEXP '\\.';
```
## 匹配字符类：

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

## 匹配多个示例
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
// \\(\\)使用\\匹配特殊字符
//sticks后跟？，使的s可有可无，可以出现0次或者1次，因此stick也会被匹配出来。

//匹配连在一起的4位数字
SELECT prod_name
FROM products
WHERE prod_name REGEXP '[[:digit:]]{4}';
//[:digit:]匹配任意数字，因而它为数字的一个集合。 {4}确切地要求它前面的字符（任意数字）出现4次，所以[[:digit:]]{4}匹配连在一起的任意4位数字。
//等同于下面：
SELECT prod_name
FROM products
WHERE prod_name REGEXP '[0-9][0-9][0-9][0-9]';
```

## 定位符
特定位置的文本需要使用定位符

|元 字 符| 说 明|
|-|-|
|^| 文本的开始|
|$ |文本的结尾|
|[[:<:]] |词的开始|
|[[:>:]] |词的结尾|

 LIKE和REGEXP
的不同在于， LIKE匹配整个串而REGEXP匹配子串。利用定位
符，通过用^开始每个表达式，用$结束每个表达式，可以使
REGEXP的作用与LIKE一样。

```java
//找出以一个数（包括以小数点开始的数）开始的所有产品
SELECT prod_name
FROM products
WHERE prod_name REGEXP '^[0-9\\.]';
//^匹配串的开始。因此， ^[0-9\\.]只在.或任意数字为串中第一个字符时才匹配它们
```


# 创建计算字段

存储在表中的数据都不是应用程序所需要的。
我们需要直接从数据库中检索出转换、计算或格式化过的数据；而不是
检索出数据，然后再在客户机应用程序或报告程序中重新格式化。

>字段（field） 基本上与列（ column） 的意思相同，经常互换使
用，不过数据库列一般称为列，而术语字段通常用在计算字段的
连接上。
## 拼接字段
拼接（concatenate） 将值联结到一起构成单个值。在MySQL的SELECT语句中，可使用
Concat()函数来拼接两个列。

>多数DBMS使用+或||来实现拼接，MySQL则使用Concat()函数来实现。当把SQL语句转换成
MySQL语句时一定要把这个区别铭记在心。
```java
//Concat()拼接串，即把多个串连接起来形成一个较长的串。Concat()需要一个或多个指定的串，各个串之间用逗号分隔。
SELECT Concat(vend_name, '(', vend_country, ')')
FROM vendors
ORDER BY vend_name;
```
输出：

![image-20210726210622468](https://gitee.com/tianzhendong/img/raw/master//images/image-20210726210622468.png)


```java
//删除数据右侧多余的空格来整理数据，这可以使用MySQL的RTrim()函数来完成
SELECT Concat(Rtrim(vend_name), '(', Rtrim(vend_country), ')')
FROM vendors
ORDER BY vend_name;
```
>Trim函数 MySQL除了支持RTrim()（正如刚才所见，它去掉
串右边的空格），还支持LTrim()（去掉串左边的空格）以及
Trim()（去掉串左右两边的空格）
## 别名
上述拼接过后实际上它没有名字，它只是一个值。如
果仅在SQL查询工具中查看一下结果，这样没有什么不好。但是，一个未
命名的列不能用于客户机应用中，因为客户机没有办法引用它。

为了解决这个问题， SQL支持列别名。 别名（ alias） 是一个字段或值
的替换名。别名用AS关键字赋予。
```java
SELECT Concat(Rtrim(vend_name), '(', Rtrim(vend_country), ')') AS vend_title
FROM vendors
ORDER BY vend_name;
```

## 执行算数计算
```java
//汇总物品的价格（单价乘以订购数量）
SELECT prod_id, quantity, item_price, quantity*item_price AS expanded_price
FROM orderitems
where order_num = 20005;
```
输出：

![image-20210726210649949](https://gitee.com/tianzhendong/img/raw/master//images/image-20210726210649949.png)

# 使用数据处理函数

与其他大多数计算机语言一样， SQL支持利用函数来处理数据。函数
一般是在数据上执行的，它给数据的转换和处理提供了方便。

## 使用函数
大多数SQL实现支持以下类型的函数。
* 用于处理文本串（如删除或填充值，转换值为大写或小写）的文本函数。
* 用于在数值数据上进行算术操作（如返回绝对值，进行代数运算）的数值函数。
* 用于处理日期和时间值并从这些值中提取特定成分（例如，返回
两个日期之差，检查日期有效性等）的日期和时间函数。
* 返回DBMS正使用的特殊信息（如返回用户登录信息，检查版本
细节）的系统函数。

## 文本处理函数
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

SOUNDEX是一个将任何文
本串转换为描述其语音表示的字母数字模式的算法。 SOUNDEX考虑了类似
的发音字符和音节，使得能对串进行发音比较而不是字母比较。

## 日期和时间处理函数
日期和时间采用相应的数据类型和特殊的格式存储，以便能快速和
有效地排序或过滤，并且节省物理存储空间。

一般，应用程序不使用用来存储日期和时间的格式，因此日期和时
间函数总是被用来读取、统计和处理这些值。
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


无论你什么时候指定一个日期，不管是插入或更新表值还是用WHERE子句进行过滤，日期必须为
格式yyyy-mm-dd。因此， 2005年9月1日，给出为2005-09-01。

```java
SELECT cust_id ,order_num 
FROM orders 
WHERE DATE(order_date) ='2005-09-01';

//检索出2005年9月下的所有订单
//方法1
SELECT cust_id ,order_num 
FROM orders 
WHERE DATE(order_date) BETWEEN '2005-09-01' AND '2005-09-30';
//方法2
SELECT cust_id ,order_num 
FROM orders 
WHERE Year(order_date)=2005 AND MONTH(order_date)=9;
```
## 数值处理函数
数值处理函数仅处理数值数据。这些函数一般主要用于代数、三角
或几何运算，因此没有串或日期—时间处理函数的使用那么频繁。

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

# 汇总数据
## 聚集函数
* 确定表中行数（或者满足某个条件或包含某个特定值的行数）。
* 获得表中行组的和。
* 找出表列（或所有行或某些特定的行）的最大值、最小值和平均值

聚集函数（ aggregate function） 运行在行组上，计算和返回单
个值的函数。

|函 数| 说 明|
|-|-|
|AVG() |返回某列的平均值|
|COUNT()| 返回某列的行数|
|MAX()| 返回某列的最大值|
|MIN() |返回某列的最小值|
|SUM()| 返回某列值之和|

### AVG()
AVG()只能用来确定特定数值列的平均值，而
且列名必须作为函数参数给出。为了获得多个列的平均值，
必须使用多个AVG()函数。


```java
//AVG()通过对表中行数计数并计算特定列值之和，求得该列的平均值。 AVG()可用来返回所有列的平均值，也可以用来返回特定列或行的平均值
//返回products表中所有产品的平均价格
SELECT AVG(prod_price) AS avg_price
FROM products;
//确定特定列或行的平均值。 下面的例子返回特定供应商所提供产品的平均价格
SELECT AVG(prod_price) AS avg_price
FROM products
WHERE vend_id = 1003;
```

### COUNT()
COUNT()函数进行计数。 可利用COUNT()确定表中行的数目或符合特
定条件的行的数目。

COUNT()函数有两种使用方式。
* 使用COUNT(*)对表中行的数目进行计数， 不管表列中包含的是空
值（ NULL）还是非空值。
* 使用COUNT(column)对特定列中具有值的行进行计数，忽略
NULL值

如果指定列名，则指定列的值为空的行被COUNT()
函数忽略，但如果COUNT()函数中用的是星号（ *），则不忽
略。

```JAVA
//返回customers表中客户的总数
SELECT COUNT(*) AS num_cust
FROM customers;

//对具有电子邮件地址的客户计数
SELECT COUNT(cust_email) AS num_cust
FROM customers;
```
### MAX()函数\MIN()函数
MAX()返回指定列中的最大值。 MAX()要求指定列名

MIN()的功能正好与MAX()功能相反，它返回指定列的最小值

>对非数值数据使用MAX()函数\MIN()函数,虽然MAX()函数\MIN()函数一般用来找出最大最小的
数值或日期值，但MySQL允许将它用来返回任意列中的最大最小
值，包括返回文本列中的最大最小值。在用于文本数据时，如果数
据按相应的列排序，则MAX()函数\MIN()函数返回最后一行\第一行。

MAX()函数\MIN()函数忽略列值为NULL的行。

```java
//返回products表中最贵的物品的价格
SELECT MAX(prod_price) AS max_price
FROM products;

//返回products表中最便宜物品的价格
SELECT MIN(prod_price) AS min_price
FROM products;
```
### SUM()函数
SUM()用来返回指定列值的和（总计）。\
```java
//检索所订购物品的总数（所有quantity值之和）
SELECT SUM(quantity) AS items_ordered
FROM orderitems
WHERE order_num = 20005;

//返回订单中所有物品价钱之和
SELECT SUM(quantity*item_price) AS total_price
FROM orderitems
WHERE order_num = 20005;
```

## 聚集不同的值

以上5个聚集函数都可以如下使用：
* 对所有的行执行计算，指定ALL参数或不给参数（因为ALL是默认
行为）；
* 只包含不同的值，指定DISTINCT参数

>如果指定列名，则DISTINCT只能用于COUNT()。DISTINCT
不能用于COUNT(*)，因此不允许使用COUNT（ DISTINCT），
否则会产生错误。类似地， DISTINCT必须使用列名，不能用
于计算或表达式
```java
//返回特定供应商1003提供的产品的平均价格(相同的产品只会计算一次)
SELECT AVG( DISTINCT prod_price) AS avg_price
FROM products
WHERE vend_id = 1003;
```
## 组合聚集函数
目前为止的所有聚集函数例子都只涉及单个函数。但实际上SELECT
语句可根据需要包含多个聚集函数。

```JAVA
SELECT COUNT(*) AS num_items,
    MIN(prod_price) AS price_min,
    Max(prod_price) AS price_max,
    AVG(prod_price) AS price_avg,
    SUM(prod_price) AS price_sum
FROM products;
```
# 分组数据

```java
//返回vend_id=1005的数量
SELECT COUNT(*) AS num_prods 
FROM products
WHERE vend_id = 1005;

//创建数据分组,GROUP BY子句指示MySQL按vend_id排序并分组数据。
SELECT vend_id, COUNT(*) AS num_prods 
FROM products
GROUP BY vend_id;

//过滤数量大于等于3的。
//where只能过滤行，不能过滤分组、
//HAVING 用于过滤分组
SELECT vend_id, COUNT(*) AS num_prods 
FROM products
GROUP BY vend_id
HAVING COUNT(*)>=3;

//分组和排序
//GROUP BY子句用来按订单号（order_num列）分组数据，以便SUM(*)函数能够返回总计订单价格。 HAVING子句过滤数据，使得只返回总计订单价格大于等于50的订单。最后， 用ORDERBY子句排序输出
SELECT order_num, SUM(quantity*item_price) AS ordertotal
FROM orderitems
GROUP BY order_num
HAVING SUM(quantity*item_price)>=50
ORDER BY ordertotal;
```
# 使用子查询
## 子查询
查询（query） 任何SQL语句都是查询。但此术语一般指SELECT
语句

SELECT语句是SQL的查询。迄今为止我们所看到的所有SELECT语句
都是简单查询，即从单个数据库表中检索数据的单条语句。

SQL还允许创建子查询（ subquery） ，即嵌套在其他查询中的查询。

## 利用子查询进行过滤
```JAVA
//检索包含物品TNT2的所有订单的编号
SELECT order_num
FROM orderitems
WHERE prod_id = 'TNT2';
//检索具有前一步骤列出的订单编号的所有客户的ID。
SELECT cust_id
FROM orders
WHERE order_num IN(20005,20007);

//把第一个查询（返回订单号的那一个）变为子查询组合两个查询。
SELECT cust_id
FROM orders
WHERE order_num IN(SELECT order_num
                    FROM orderitems
                    WHERE prod_id = 'TNT2');

```

# 联结表

外键（foreign key）：外键为某个表中的一列，它包含另一个表的主键值，定义了两个表之间的关系。

可伸缩性（scale） 能够适应不断增加的工作量而不失败。设计良好的数据库或应用程序称之为可伸缩性好（ scale well） 。

联结是一种机制，用来在一条SELECT
语句中关联表，因此称之为联结。使用特殊的语法，可以联结多个表返
回一组输出，联结在运行时关联表中正确的行。

## 创建联结
联结的创建非常简单，规定要联结的所有表以及它们如何关联即可。
```JAVA
//使用where联结
SELECT vend_name , prod_name, prod_price
FROM vendors, products
WHERE vendors.vend_id = products.vend_id
ORDER BY vend_name, prod_name;

//使用INNER JOIN联结，推荐
SELECT vend_name , prod_name, prod_price
FROM vendors INNER JOIN products
ON vendors.vend_id = products.vend_id
ORDER BY vend_name, prod_name;
```
## 联结多个表
SQL对一条SELECT语句中可以联结的表的数目没有限制。创建联结
的基本规则也相同。首先列出所有表，然后定义表之间的关系
```JAVA
SELECT prod_name, vend_name , prod_price, quantity
FROM orderitems,products,vendors, 
WHERE products.vend_id=vendors.vend_id 
AND orderitems.prod_id = products.prod_id 
AND order_num =20005
ORDER BY vend_name, prod_name;
```

# 创建高级联结
## 使用别名

```JAVA
// 使用别名
SELECT cust_name, cust_contact
FROM customers AS c,orders AS o, orderitems AS oi
WHERE c.cust_id = o.cust_id
    AND oi.order_num = o.order_num
    AND prod_id = 'TNT2';

//自联结
//在同一个表中搜索
SELECT p1.prod_id, p1.prod_name
FROM products AS p1, products AS p2
WHERE p1.vend_id = p2.vend_id
    AND p2.prod_id = 'DTNTR';
```


# 组合查询

```java
SELECT vend_id, prod_id ,prod_price
FROM products
WHERE prod_price<=5
UNION 
SELECT vend_id, prod_id ,prod_price
FROM products
WHERE vend_id IN(1001,1002);

//等同于
SELECT vend_id, prod_id ,prod_price
FROM products
WHERE prod_price<=5 OR vend_id IN (1001,1002);
```

UNION从查询结果集中自动去除了重复的行（换句话说，它的行为与
单条SELECT语句中使用多个WHERE子句条件一样）。

使用UNION ALL， MySQL不取消重复的行。

在用UNION组合查询时，只
能使用一条ORDER BY子句，它必须出现在最后一条SELECT语句之后。

# 全文本搜索
## 启用全文本搜索支持
一般在创建表时启用全文本搜索。 CREATE TABLE语句（第21章中介
绍）接受FULLTEXT子句，它给出被索引列的一个逗号分隔的列表。
## 进行全文本搜索
使用两个函数Match()和Against()执行全文本搜索，
其中Match()指定被搜索的列， Against()指定要使用的搜索表达式。
```java
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('rabbit');

//等同于
SELECT note_text
FROM productnotes
WHERE note_text LIKE '%rabbit%';

SELECT note_text
FROM productnotes
WHERE note_text REGEXP 'rabbit';
```
区别：

全文本搜索会根据匹配程度进行自动排序

## 使用查询扩展
找出所有提到anvils的注释。只有一个注释包含词anvils，
但你还想找出可能与你的搜索有关的所有其他行，即使它们不包含词anvils。

在使用查询扩展时， MySQL对数据和
索引进行两遍扫描来完成搜索：

```java
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('rabbit' WITH QUERY EXPANSION);
```

## 布尔文本搜索
```java
//查询有heavy但没有rope的行
SELECT note_text
FROM productnotes
WHERE Match(note_text) Against('heavy -rope*' IN BOOLEAN MODE);
```

# 插入数据
INSERT
## 插入完整的行
```java
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
## 插入多个列

如果插入的顺序相同，那么可以在VALUES后再加一个括号，把另外的数据加上
## 插入检索出的数据
INSERT SELECT

```java
//使用INSERT SELECT从custnew中将所有数据导入customers
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
# 更新和删除数据
## 更新数据UPDATE
```java
//更新10005客户的邮件地址
UPDATE customers
SET cust_email='elmer@fudd.com'
WHERE cust_id = 10005;

//更新多个列
UPDATE customers
SET cust_email='120@fudd.com',
    cust_name = 'tian'
WHERE cust_id = 10005;

//删除某个列的值，可设置其为NULL
UPDATE customers
SET cust_email=NULL
WHERE cust_id = 10005;
```

## 删除数据DELETE

```java
//删除 一行
//如果省略WHERE子句，它将删除表中每个客户
DELETE FROM customers
WHERE cust_id = 10006;

```

如果想从表中删除所有行，不要使用DELETE。
可使用TRUNCATE TABLE语句，它完成相同的工作，但速度更
快（ TRUNCATE实际是删除原来的表并重新创建一个表，而不
是逐行删除表中的数据）。
