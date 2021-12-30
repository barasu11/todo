package com.example.todo.core.BasicServiceLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public void add(Object obj) {
        if (obj != null)
            services.add(obj);
    }

    public Object get(Class c) {
        for (Object service : services) {
            if (service.getClass() == c) {
                return service;
            }
        }
        return null;
    }
}
