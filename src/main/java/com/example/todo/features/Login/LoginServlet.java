package com.example.todo.features.Login;

import com.example.todo.core.BasicServiceLocator.BasicServiceLocator;
import com.example.todo.features.Login.Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * username = request.getParams("username")
         * password = request.getParams("username")
         *
         * validate against database and return a view based on it
         */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ((username != null && !username.trim().equals("")) && (password != null && !password.trim().equals(""))) {
            UserRepository repository = (UserRepository) BasicServiceLocator.I.get().get(UserRepository.class);
            User user = new User(username, password);
            boolean loggedIn = repository.login(user);
            if (loggedIn) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect("https://infor.com");
                return;
            } else {
                response.sendRedirect("https://google.com");
                return;
            }
        }
    }
}
