package com.example.todo.core.Database.Implementations.Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

public class Insert {
    public Insert(Connection connection, String tableName, Map<String, String> values) {
        this.connection = connection;
        this.tableName = tableName;
        this.values = values;
    }

    private final String tableName;
    private final Map<String, String> values;
    private final Connection connection;

    public int exec() throws SQLException {
        StringBuilder col = new StringBuilder();
        StringBuilder val = new StringBuilder();
        values.entrySet().stream().map(entry -> {
            return entry;
        }).forEach(entry -> { //create table(col1 dataType, col2 dataType)
            col
                    .append(entry.getKey())
                    .append(" ")
                    .append(", ");
            val
                    .append("?")
                    .append(" ")
                    .append(", ");
        });
        try (PreparedStatement insertPrepStatement = this.connection.prepareStatement("INSERT INTO " + tableName
                + " (" + col.toString().replaceAll("[,][\\s]$", "") + ")"
                + " VALUES(" + val.toString().replaceAll("[,][\\s]$", "") + ")")) {
            ResultSetMetaData md = this.connection.createStatement().executeQuery("SELECT * FROM " + tableName).getMetaData();
            int tCols = md.getColumnCount();
            for (int i = 1; i <= tCols; i++) {
                String colName = md.getColumnName(i);
                if (colName.equalsIgnoreCase("_id")) continue;
                int colType = md.getColumnType(i);
                if (colType == 1) {
                    insertPrepStatement.setString(i, values.get(colName));
                } else if (colType == 4) {
                    insertPrepStatement.setInt(i, Integer.parseInt(values.get(colName)));
                }
            }
            int count = insertPrepStatement.executeUpdate();
            System.out.println("Inserted " + count + " records");
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Unable to insert record");
    }
}
