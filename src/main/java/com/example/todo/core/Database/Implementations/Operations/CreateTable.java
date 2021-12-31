package com.example.todo.core.Database.Implementations.Operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * Creates Table
 */
public class CreateTable {
    /**
     * Accepts Connection, table name and fields
     *
     * @param connection
     * @param tableName
     * @param fields
     */
    public CreateTable(Connection connection, String tableName, Map<String, String> fields) {
        this.tableName = tableName;
        this.fields = fields;
        this.connection = connection;
    }

    private String tableName;
    private Map<String, String> fields;
    private Connection connection;

    public void exec() throws SQLException {
        if (this.connection == null) {
            System.out.println("Fatal: Database not connected.....");
            throw new RuntimeException("Cannot create table");
        }
        try (Statement st = this.connection.createStatement()) {
            StringBuilder builder = new StringBuilder();
            fields.entrySet().stream().forEach(entry -> {
                builder
                        .append(entry.getKey())
                        .append(" ")
                        .append(entry.getValue())
                        .append(", ");
            });
            builder.append("_id INTEGER PRIMARY KEY NOT NULL");
            String cleanedQuery = builder.toString();
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + cleanedQuery + ")";
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Unable to create table " + tableName);
        }
    }
}
