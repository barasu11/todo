package com.example.todo.core.Tasks;

import com.example.todo.constants.Constants;
import com.example.todo.core.BasicServiceLocator.BasicServiceLocator;
import com.example.todo.core.Database.Implementations.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;


public class InitializeTables extends HttpServlet {
    /**
     * Initialize Tables
     * Creates Tables if it does not exist
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        DatabaseService service = (DatabaseService) BasicServiceLocator.I.get().retrieve(DatabaseService.class);
        service.initialize();
        //Create USERS Table
        service.createTable(Constants.DATABASE.TABLE_USERS, new HashMap<String, String>() {{
            put(Constants.DATABASE.USER_NAME, Constants.DATABASE.TEXT);
            put(Constants.DATABASE.PASSWORD, Constants.DATABASE.TEXT);
        }});

        //Create TODOs Table
        service.createTable(Constants.DATABASE.TABLE_TODOS, new HashMap<String, String>() {{
            put(Constants.DATABASE.ITEM, Constants.DATABASE.TEXT);
            put(Constants.DATABASE.USER_ID, Constants.DATABASE.INTEGER);
        }});
    }


}
