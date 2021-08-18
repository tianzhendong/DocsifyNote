<%--
  Created by IntelliJ IDEA.
  User: 12038
  Date: 2021/8/16
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加书籍页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/book/addBook" method="get">
    <table align="center" border="0">
        <tr>
            <td>书籍名称: </td>
            <td><input type="text" name="bookName" required></td>
        </tr>
        <tr>
            <td>书籍数量: </td>
            <td><input type="text" name="bookCounts" required></td>
        </tr>
        <tr>
            <td>书籍描述: </td>
            <td><input type="text" name="detail"></td>
        </tr>
        <tr align="center">
            <td><input type="reset" value="重置"></td>
            <td><input type="submit" value="添加"></td>
        </tr>
    </table>
</form>
</body>
</html>
