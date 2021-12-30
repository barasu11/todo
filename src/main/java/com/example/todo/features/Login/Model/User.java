package com.example.todo.features.Login.Model;

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

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("username", this.username);
        map.put("password", this.password);
        return map;
    }
}
