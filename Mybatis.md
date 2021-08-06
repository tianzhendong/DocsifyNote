---
title: Mybatis
tags: java
notebook: JAVA
---

[toc]

[toc]

# Mybatis

# 1、简介

## Mybatis

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

## 持久化

数据持久化

* 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
* 内存：断电即失
* 数据库、io文件可以进行持久化

为什么需要持久化：有一些对象不能丢失

## 持久层

Dao层、Service层、Controller层

