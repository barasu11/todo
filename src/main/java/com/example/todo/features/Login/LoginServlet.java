package com.example.todo.features.Login;

import com.example.todo.core.BasicServiceLocator.BasicServiceLocator;
import com.example.todo.features.Shared.Model.User;

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
            LoginRepository repository = (LoginRepository) BasicServiceLocator.I.get().get(LoginRepository.class);
            User user = new User(username, password);
            boolean loggedIn = repository.login(user);
            if (loggedIn) {
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("loggedIn", true);
                response.sendRedirect("https://infor.com");
                return;
            } else {
                request.getSession().setAttribute("loggedIn", false);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/home/home.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
    }
}
