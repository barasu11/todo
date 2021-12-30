package com.example.todo.core.Database.Interfaces;

import com.example.todo.core.Database.DatabaseProviders;

public interface IDatabaseConfiguration {
    String getDatabaseName();

    String getDatabaseUri();

    String getDatabaseUserName();

    String getDatabasePassword();

    DatabaseProviders getDatabaseProvider();
}
