package com.example.todo.features.Todo;

import com.example.todo.constants.Constants;
import com.example.todo.core.Database.Interfaces.IDatabase;
import com.example.todo.features.Todo.Model.TodoModel;

import java.util.*;

public class TodoRepository {
    public TodoRepository(IDatabase database) {
        this.database = database;
    }

    private final IDatabase database;

    void add(TodoModel item) {
        this.database.insert(Constants.DATABASE.TABLE_TODOS, item.toMap());
    }

    List<Map<String, String>> get(String userId) {
        List<Map<String, String>> todoList = new LinkedList<>();
        List<Map<String, Object>> list = this.database.find(Constants.DATABASE.TABLE_TODOS, new HashMap<String, String>() {{
            put(Constants.DATABASE.USER_ID, userId);
        }});
        for (Map<String, Object> item : list) {
            TodoModel todoItem = new TodoModel(item.get(Constants.DATABASE.ITEM).toString());
            todoItem.setId(item.get(Constants.DATABASE.ID).toString());
            todoList.add(todoItem.toMap());

        }
        return todoList;
    }
}
