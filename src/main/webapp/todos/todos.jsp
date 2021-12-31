<%--
  Created by IntelliJ IDEA.
  User: barasu
  Date: 12/30/2021
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Todos List</title>
</head>
<body>

<c:forEach items="${todoItems}" var="item">
    ${item.get("item")}</br>
</c:forEach>

<form action="TodoServlet" method="GET">
    <input value="Get List" type="submit"/>
</form>

<form action="TodoServlet" method="POST">
    Add Item <input name="todoItem" type="text">
    </br>
    <input value="Add" type="submit"/>
</form>
</body>
</html>
