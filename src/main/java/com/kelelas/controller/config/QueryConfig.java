package com.kelelas.controller.config;

import java.util.ResourceBundle;

public class QueryConfig {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("query");
    private QueryConfig() {
    }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
