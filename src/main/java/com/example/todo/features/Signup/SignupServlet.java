package com.example.todo.features.Signup;

import com.example.todo.constants.Constants;
import com.example.todo.core.BasicServiceLocator.BasicServiceLocator;
import com.example.todo.features.Shared.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignupServlet", value = "/signup/SignupServlet")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(Constants.INPUTFIELD.USER_NAME);
        String password = request.getParameter(Constants.INPUTFIELD.PASSWORD);

        if ((username != null && !username.trim().equals("")) && (password != null && !password.trim().equals(""))) {
            SignupRepository repository = (SignupRepository) BasicServiceLocator.I.get().get(SignupRepository.class);
            User user = new User(username, password);
            boolean registered = repository.register(user);
            if (registered) {
                request.getSession().setAttribute(Constants.REGISTERED, true);
                response.sendRedirect(request.getContextPath() + "/");
                return;
            } else {
                request.getSession().setAttribute(Constants.REGISTERED, false);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/signup/signup.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
