package com.example.todo.features.Login;

import com.example.todo.constants.Constants;
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
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(Constants.ATTRIBUTES.USER_NAME);
        String password = request.getParameter(Constants.ATTRIBUTES.PASSWORD);

        if ((username != null && !username.trim().equals("")) && (password != null && !password.trim().equals(""))) {
            LoginRepository repository = (LoginRepository) BasicServiceLocator.I.get().retrieve(LoginRepository.class);
            User user = new User(username, password);
            boolean loggedIn = repository.login(user);
            if (loggedIn) {
                request.getSession().setAttribute(Constants.ATTRIBUTES.USER, user);
                request.getSession().setAttribute(Constants.ATTRIBUTES.LOGGED_IN, true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/todos/todos.jsp");
                dispatcher.forward(request, response);
            } else {
                request.getSession().setAttribute(Constants.ATTRIBUTES.LOGGED_IN, false);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/home/home.jsp");
                dispatcher.forward(request, response);
            }
            return;
        }
    }
}
