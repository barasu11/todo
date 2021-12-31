package com.example.todo.core.Database.Interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Database Interface
 */
public interface IDatabase {

    /**
     * Provide Initialization for database such as setting
     * up connection
     */
    void initialize();

    /**
     * Create Table
     *
     * @param tableName
     * @param fields
     */
    public void createTable(String tableName, Map<String, String> fields);

    /**
     * Insert Record into table
     *
     * @param tableName
     * @param values
     * @return
     */
    public int insert(String tableName, Map<String, String> values);

    /**
     * Retrieve information from table based on options
     *
     * @param tableName
     * @param options
     * @return
     */
    public List<Map<String, Object>> find(String tableName, Map<String, String> options);
}
