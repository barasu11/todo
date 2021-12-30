<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
    <style>
        html {
            height: 100%;
        }

        body {
            background: rgb(9, 102, 121);
            background: linear-gradient(180deg, rgba(9, 102, 121, 1) 0%, rgba(0, 255, 235, 1) 100%);
            height: 100%;
            background-repeat: no-repeat;
            font-family: 'Arial';
        }

        .login-container {
            flex: 1;
        }

        .login-input-element {
            padding: 5px;
            margin: 10px;
        }

        h1 {
            color: white;
        }

        p {
            color: red;
        }

        footer {
            position: fixed;
            left: 0;
            bottom: 0;
            right: 0;
            width: 100%;
            background-color: rgba(255, 255, 255, 0.7);

        }

        .login-button {
            padding: 5px;
            height: 1%;
            width: 5%;
        }

        .footer-info {
            text-align: center;
        }
    </style>
</head>

<body>
<h1>Create a Todo List with Ease</h1>
<div class="login">
    <form action="LoginServlet" method="POST">
        <%
            Object loggedIn = request.getSession().getAttribute("loggedIn");
            if (loggedIn != null && !(boolean) loggedIn) {
                out.println("<div style=\"color: red\">Unable to Login</div>");
            }
        %>
        <div class="login-container">
            Username <input type="text" name="username" class="login-input-element"/>
            Password <input type="password" name="password" class="login-input-element"/>
            <input type="submit" class="login-button" value="Login"/>
        </div>
    </form>
    <form>
        Need an account? <a href="${pageContext.request.contextPath}/signup/signup.jsp">Click here</a>
    </form>
</div>
<footer>
    <div class="footer-info">You will need an account to create todo list</div>
</footer>
</body>
</html>