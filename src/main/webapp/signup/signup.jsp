<%--
  Created by IntelliJ IDEA.
  User: barasu
  Date: 12/30/2021
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup For Todo</title>
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

        .signup-container {
            flex: 1;
        }

        .signup-input-element {
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

        .signup-button {
            padding: 5px;
            height: 3%;
            width: 8%;
        }

        .footer-info {
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Create an account to start accessing Todo</h1>
<div class="signup">
    <form action="SignupServlet" method="POST">
        <%
            Object loggedIn = request.getSession().getAttribute("loggedIn");
            if (loggedIn != null && !(boolean) loggedIn) {
                out.println("<div style=\"color: red\">Unable to Login</div>");
            }
        %>
        <div class="signup-container">
            Username <input type="text" name="username" class="signup-input-element"/>
            Password <input type="password" name="password" class="signup-input-element"/>
            <input type="submit" class="signup-button" value="Signup"/>
        </div>
    </form>
</div>
<footer>
    <div class="footer-info">You will need an account to create todo list</div>
</footer>
</body>
</html>
