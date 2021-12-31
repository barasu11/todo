package com.example.todo.features.Login;

import com.example.todo.constants.Constants;
import com.example.todo.core.Database.Interfaces.IDatabase;
import com.example.todo.features.Shared.Model.User;

import java.util.List;
import java.util.Map;

public class LoginRepository {
    public LoginRepository(IDatabase database) {
        this.database = database;
    }

    private final IDatabase database;
    private boolean loggedIn = false;


    boolean login(User user) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.database.find(Constants.DATABASE.TABLE_USERS, user.toMap());
        if (list.size() > 0) {
            Map<String, Object> userMap = list.get(0);
            user.setId(userMap.get(Constants.DATABASE.ID).toString());
            loggedIn = list.size() > 0;
        }
        return this.loggedIn;
    }

    boolean isLoggedIn() {
        return this.loggedIn;
    }
}
