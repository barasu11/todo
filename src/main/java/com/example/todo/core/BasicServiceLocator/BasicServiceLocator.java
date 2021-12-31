package com.example.todo.core.BasicServiceLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


/**
 * Service Locator
 * This class provides object's of requested class on the fly
 */
public class BasicServiceLocator {

    private BasicServiceLocator() {
        this.services = new ArrayList<>();
    }

    private static BasicServiceLocator serviceLocator = null;

    private List<Object> services;

    public static Supplier<BasicServiceLocator> I = () -> {
        if (serviceLocator == null) {
            serviceLocator = new BasicServiceLocator();
        }
        return serviceLocator;
    };

    /**
     * Registers a service
     * @param obj
     */
    public void register(Object obj) {
        if (obj != null)
            services.add(obj);
    }

    /**
     * Retrieves a service
     * @param c
     * @return
     */
    public Object retrieve(Class c) {
        for (Object service : services) {
            if (service.getClass() == c) {
                return service;
            }
        }
        return null;
    }
}
