package com.example.todo.core.Tasks;

import com.example.todo.constants.Constants;
import com.example.todo.core.BasicServiceLocator.BasicServiceLocator;
import com.example.todo.core.Database.DatabaseProviders;
import com.example.todo.core.Database.Implementations.DatabaseService;
import com.example.todo.core.Database.Interfaces.IDatabase;
import com.example.todo.core.Database.Interfaces.IDatabaseConfiguration;
import com.example.todo.features.Login.LoginRepository;
import com.example.todo.features.Signup.SignupRepository;
import com.example.todo.features.Todo.TodoRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * This class is responsible for registering service
 * to be accessible
 */
public class RegisterServices extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        BasicServiceLocator serviceLocator = BasicServiceLocator.I.get();

        ///Registering Services to make it globally available

        //Registering Database
        serviceLocator.register(DatabaseService.I.apply(new IDatabaseConfiguration() {
            @Override
            public String getDatabaseName() {
                return Constants.DATABASE.DATABASE_NAME;
            }

            @Override
            public String getDatabaseUri() {
                return Constants.DATABASE.DATABASE_PATH;
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

        //Registering Login Repository
        serviceLocator.register(new LoginRepository((IDatabase) serviceLocator.retrieve(DatabaseService.class)));

        //Registering Signup Repository
        serviceLocator.register(new SignupRepository((IDatabase) serviceLocator.retrieve(DatabaseService.class)));

        //Registering Todo Repository
        serviceLocator.register(new TodoRepository((IDatabase) serviceLocator.retrieve(DatabaseService.class)));
    }
}
