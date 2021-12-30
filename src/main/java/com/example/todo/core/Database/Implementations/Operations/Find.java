package com.example.todo.core.Database.Implementations.Operations;

import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Find {
    public Find(Connection connection, String tableName, Map<String, String> options) {
        this.connection = connection;
        this.tableName = tableName;
        this.options = options;
    }

    private final Connection connection;
    private final String tableName;
    private final Map<String, String> options;

    public List<Map<String, Object>> exec() throws SQLException {
        StringBuilder whereClause = new StringBuilder("");
        if (options != null && options.size() > 0) {
            int andCounter = 0;
            List<Map.Entry<String, String>> entries = options.entrySet().stream().collect(Collectors.toList());
            for (Map.Entry<String, String> entry : entries) {
                if ((entry.getValue() != null && !entry.getValue().trim().equals(""))
                        && (entry.getKey() != null && !entry.getKey().trim().equals(""))) {
                    if (andCounter > 0) whereClause.append("AND ");
                    whereClause.append(entry.getKey() + " = \"" + entry.getValue() + "\" ");
                    andCounter++;
                }
            }
//            entries.stream().forEach(entry -> {
//                if ((entry.getValue() != null && !entry.getValue().trim().equals(""))
//                        && (entry.getKey() != null && !entry.getKey().trim().equals(""))) {
//                    if (entries. > 0) whereClause.append("AND ");
//                    whereClause.append(entry.getKey() + " = \"" + entry.getValue() + "\" ");
//                    andCounter.getAndIncrement();
//                }
//            });
        }
        try (Statement st = this.connection.createStatement()) {
            String queryBuilder = "SELECT * FROM " + tableName + " " +
                    (
                            whereClause.toString().trim().equals("")
                                    ? ""
                                    : " WHERE " + whereClause
                    );
            ResultSet resultSet = st.executeQuery(queryBuilder);
            ResultSetMetaData md = resultSet.getMetaData();
            int count = md.getColumnCount();
            String[] colNames = new String[count + 1];
            for (int i = 1; i <= count; i++)
                colNames[i] = md.getColumnName(i);

            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= count; i++)
                    row.put(colNames[i], resultSet.getObject(i));
                list.add(row);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Unable to find records");
    }
}
