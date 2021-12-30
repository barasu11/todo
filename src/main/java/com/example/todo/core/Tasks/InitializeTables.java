package com.example.todo.core.Tasks;

import com.example.todo.core.BasicServiceLocator.BasicServiceLocator;
import com.example.todo.core.Database.DatabaseProviders;
import com.example.todo.core.Database.Implementations.DatabaseService;
import com.example.todo.core.Database.Interfaces.IDatabaseConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.util.HashMap;


public class InitializeTables extends HttpServlet {
    @Override
    public void init() throws ServletException {
        DatabaseService service = (DatabaseService) BasicServiceLocator.I.get().get(DatabaseService.class);
        service.initialize();
        service.createTable("users", new HashMap<String, String>() {{
            put("username", "TEXT");
            put("password", "TEXT");
        }});
    }


}
