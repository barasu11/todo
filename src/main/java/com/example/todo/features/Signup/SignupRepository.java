package com.example.todo.features.Signup;

import com.example.todo.constants.Constants;
import com.example.todo.core.Database.Interfaces.IDatabase;
import com.example.todo.features.Shared.Model.User;

import java.sql.SQLException;

public class SignupRepository {
    public SignupRepository(IDatabase database) {
        this.database = database;
    }

    private final IDatabase database;

    boolean register(User user) {
        return this.database.insert(Constants.DATABASE.USERS, user.toMap()) > 0;
    }
}
