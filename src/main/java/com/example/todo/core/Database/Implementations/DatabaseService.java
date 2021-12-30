package com.example.todo.core.Database.Implementations;

import com.example.todo.core.Database.DatabaseProviders;
import com.example.todo.core.Database.Implementations.Operations.CreateTable;
import com.example.todo.core.Database.Implementations.Operations.Find;
import com.example.todo.core.Database.Implementations.Operations.Insert;
import com.example.todo.core.Database.Interfaces.IDatabase;
import com.example.todo.core.Database.Interfaces.IDatabaseConfiguration;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DatabaseService implements IDatabase {


    private DatabaseService(String databaseName, String databaseUri, DatabaseProviders provider) {
        this.databaseName = databaseName;
        this.databaseUri = databaseUri;
        this.databaseProvider = provider;
    }

    private static DatabaseService _db = null;

    private final String databaseName;
    private final String databaseUri;
    private final DatabaseProviders databaseProvider;

    private Connection connection;

    public static Function<IDatabaseConfiguration, DatabaseService> I = (IDatabaseConfiguration configuration) -> {
        if (_db == null) {
            _db = new DatabaseService(configuration.getDatabaseName(),
                    configuration.getDatabaseUri(),
                    configuration.getDatabaseProvider());
        }
        return _db;
    };


    @Override
    public void initialize() {
        String source = "jdbc:" + this.databaseProvider.toString().toLowerCase() + ":" + this.databaseUri + this.resolveTableName();
        try {
            this.connection = DriverManager.getConnection(source);
            System.out.println("Connection established successfully.....");
        } catch (SQLException e) {
            System.out.println("Unable to establish connection");
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    @Override
    public void createTable(String tableName, Map<String, String> fields) {
        try {
            new CreateTable(connection, tableName, fields).exec();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Unable to create table " + tableName);
        }
        return;
    }

    @Override
    public int insert(String tableName, Map<String, String> values) {
        try {
            return new Insert(this.connection, tableName, values).exec();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Unable to insert record into " + tableName);
        }
    }

    @Override
    public List<Map<String, Object>> find(String tableName, Map<String, String> options) {
        try {
            return new Find(this.connection, tableName, options).exec();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Unable to find record in " + tableName);
        }
    }

    private String resolveTableName() {
        if (this.databaseProvider != null) {
            return this.databaseProvider.name().trim().equalsIgnoreCase(DatabaseProviders.SQLITE.toString())
                    ? this.databaseName + ".db"
                    : this.databaseName;
        }
        throw new RuntimeException("Table Name cannot be empty");
    }
}
