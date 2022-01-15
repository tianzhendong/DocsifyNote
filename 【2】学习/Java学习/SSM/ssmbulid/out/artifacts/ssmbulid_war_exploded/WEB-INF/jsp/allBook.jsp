<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 12038
  Date: 2021/8/16
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍展示</title>
    <style>
        #a1{
            text-decoration: none;      /*取消下划线*/
        }
        td
        {
            white-space: nowrap;    /*设置表格宽度自动扩容*/
        }
    </style>
</head>
<body>
<table align="center" border="1" width="300" height="100" cellspacing="0"><!--设置表格剧中，边框线粗细，每个格子的长宽,单元格之间的间距为0-->
    <tr align="center">
        <th colspan="5" bgcolor="#00bfff">书籍列表</th><!--表头，自带加粗居中属性,colspan设置合并1行1列和2列的单元格，rowspan设置跨列合并-->
    </tr>
    <tr bgcolor="#ffe4c4">
        <td align="center"><b>书籍ID</b></td><!--设置字体居中，加粗-->
        <td align="center"><b>书籍名称</b></td>
        <td align="center"><b>书籍数量</b></td>
        <td align="center"><b>书籍描述</b></td>
        <td align="center"><b>操作</b></td>
    </tr>
    <c:forEach var="books" items="${list}">
        <tr align="center">
            <td>${books.bookID}</td>
            <td>${books.bookName}</td>
            <td>${books.bookCounts}</td>
            <td>${books.detail}</td>
            <td>
                &nbsp;<a href="${pageContext.request.contextPath}/book/toUpdateBook/${books.bookID}">修改</a>
                |
                <a href="${pageContext.request.contextPath}/book/deleteBook/${books.bookID}">删除</a>&nbsp;
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5" align="center"><a id="a1"  href="${pageContext.request.contextPath}/book/toAddBook">添加书籍</a></td>
    </tr>
</table>
</body>
</html>
