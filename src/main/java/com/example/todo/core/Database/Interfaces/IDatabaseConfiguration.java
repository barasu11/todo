package com.example.todo.core.Database.Interfaces;

import com.example.todo.core.Database.DatabaseProviders;

/**
 * Database configuration Interface
 */
public interface IDatabaseConfiguration {

    /**
     * Returns database name
     *
     * @return
     */
    String getDatabaseName();


    /**
     * Returns Database URI
     *
     * @return
     */
    String getDatabaseUri();

    /**
     * Returns database user name
     *
     * @return
     */
    String getDatabaseUserName();

    /**
     * Returns database password
     *
     * @return
     */
    String getDatabasePassword();

    /**
     * Returns database provider
     *
     * @return
     */
    DatabaseProviders getDatabaseProvider();
}
