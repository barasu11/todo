package com.example.todo.features.Shared.Model;

import com.example.todo.constants.Constants;
import com.example.todo.core.Database.Interfaces.IDatabase;
import com.example.todo.core.Database.Interfaces.IModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class User implements IModel {
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private final String username;
    private final String password;
    private String _id;


    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }


    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.DATABASE.USER_NAME, this.username);
        map.put(Constants.DATABASE.PASSWORD, this.password);
        return map;
    }
}
