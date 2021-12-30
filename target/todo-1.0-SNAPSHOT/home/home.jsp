<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todo List</title>
    <style>
        body {
            background-color: darkslategray;
            height: 100%;
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
    </style>
</head>

<body>
<h1>Create a Todo List with Ease</h1>
<form action="LoginServlet" method="POST">
    <div class="login-container">
        Username <input type="text" name="username" class="login-input-element"/>
        Password <input type="password" name="password" class="login-input-element"/>
        <input type="submit" value="Submit"/>
    </div>
</form>
<footer>You will need an account to create todo list</footer>
</body>
</html>