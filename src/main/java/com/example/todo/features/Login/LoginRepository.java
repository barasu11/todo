package com.example.todo.features.Login;

import com.example.todo.core.Database.Interfaces.IDatabase;
import com.example.todo.features.Shared.Model.User;

import java.sql.SQLException;

public class LoginRepository {
    public LoginRepository(IDatabase database) {
        this.database = database;
    }

    private final IDatabase database;
    private boolean loggedIn = false;


    boolean login(User user) {
        loggedIn = this.database.find("users", user.toMap()).size() > 0;
        return this.loggedIn;
    }

    boolean isLoggedIn() {
        return this.loggedIn;
    }
}
