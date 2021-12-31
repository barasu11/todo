package com.example.todo.core.Database.Interfaces;

import java.util.Map;

/**
 * Basic model interface¶
 */
public interface IModel {
    /**
     * Converts a model into java map
     *
     * @return
     */
    Map<String, String> toMap();
}
