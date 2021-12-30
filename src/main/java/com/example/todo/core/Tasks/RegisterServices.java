package com.example.todo.core.Tasks;

import com.example.todo.core.BasicServiceLocator.BasicServiceLocator;
import com.example.todo.core.Database.DatabaseProviders;
import com.example.todo.core.Database.Implementations.DatabaseService;
import com.example.todo.core.Database.Interfaces.IDatabase;
import com.example.todo.core.Database.Interfaces.IDatabaseConfiguration;
import com.example.todo.features.Login.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class RegisterServices extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        BasicServiceLocator serviceLocator = BasicServiceLocator.I.get();

        ///Registering Services to make it globally available
        serviceLocator.add(DatabaseService.I.apply(new IDatabaseConfiguration() {
            @Override
            public String getDatabaseName() {
                return "todos";
            }

            @Override
            public String getDatabaseUri() {
                return "C:\\Examples\\todo\\src\\main\\resources\\";
            }

            @Override
            public String getDatabaseUserName() {
                return "";
            }

            @Override
            public String getDatabasePassword() {
                return "";
            }

            @Override
            public DatabaseProviders getDatabaseProvider() {
                return DatabaseProviders.SQLITE;
            }
        }));

        serviceLocator.add(new UserRepository((IDatabase) serviceLocator.get(DatabaseService.class)));
    }
}
