package com.example.todo.features.Login;

import com.example.todo.core.Database.Interfaces.IDatabase;
import com.example.todo.features.Login.Model.User;

import java.sql.SQLException;

public class UserRepository {
    public UserRepository(IDatabase database) {
        this.database = database;
    }

    private final IDatabase database;
    private boolean loggedIn = false;

    boolean register(User user) throws SQLException {
        return this.database.insert("users", user.toMap()) > 0;
    }

    boolean login(User user) {
        loggedIn = this.database.find("users", user.toMap()).size() > 0;
        return this.loggedIn;
    }

    boolean isLoggedIn() {
        return this.loggedIn;
    }
}
