package com.example.todo.features.Todo;

import com.example.todo.constants.Constants;
import com.example.todo.core.BasicServiceLocator.BasicServiceLocator;
import com.example.todo.features.Shared.Model.User;
import com.example.todo.features.Todo.Model.TodoModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TodoServlet", value = "/TodoServlet")
public class TodoServlet extends HttpServlet {

    List<Map<String, String>> items = new LinkedList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(Constants.ATTRIBUTES.USER);
        TodoRepository repository = (TodoRepository) BasicServiceLocator.I.get().retrieve(TodoRepository.class);
        items = repository.get(user.getId());
        request.getSession().setAttribute(Constants.ATTRIBUTES.TODO_ITEMS, items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/todos/todos.jsp");
        dispatcher.forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todoItem = request.getParameter(Constants.ATTRIBUTES.TODO_ITEM);
        User user = (User) request.getSession().getAttribute(Constants.ATTRIBUTES.USER);
        if (todoItem != null && !todoItem.trim().equals("")) {
            TodoRepository repository = (TodoRepository) BasicServiceLocator.I.get().retrieve(TodoRepository.class);
            TodoModel item = new TodoModel(todoItem);
            item.setUserId(user.getId());
            repository.add(item);
            items.add(item.toMap());
            request.getSession().setAttribute(Constants.ATTRIBUTES.TODO_ITEMS, items);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/todos/todos.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }
}
