package com.example.todo.core.Database.Interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDatabase {
    void initialize();

    public void createTable(String tableName, Map<String, String> fields);

    public int insert(String tableName, Map<String, String> values);

    public List<Map<String, Object>> find(String tableName, Map<String, String> options);
}
