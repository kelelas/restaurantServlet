package com.kelelas.controller.config;

import java.util.ResourceBundle;

public class PageConfig {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    private PageConfig() {
    }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
