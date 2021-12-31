package com.example.todo.features.Todo.Model;

import com.example.todo.constants.Constants;
import com.example.todo.core.Database.Interfaces.IModel;

import java.util.HashMap;
import java.util.Map;

public class TodoModel implements IModel {
    public TodoModel(String item) {
        this.item = item;
    }

    private final String item;
    private String _id;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getId() {
        return this._id;
    }

    public void setId(String _id) {
        this._id = _id;
    }


    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.DATABASE.ITEM, this.item);
        map.put(Constants.DATABASE.USER_ID, this.userId);
        return map;
    }
}
