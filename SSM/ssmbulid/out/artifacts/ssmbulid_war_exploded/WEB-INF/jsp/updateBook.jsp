<%--
  Created by IntelliJ IDEA.
  User: 12038
  Date: 2021/8/16
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
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
