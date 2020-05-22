package com.kelelas.controller.config;

import java.util.ResourceBundle;

public class ConstantsConfig {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("constants");
    private ConstantsConfig() {
    }
    public static String getStringProperty(String key) {
        return resourceBundle.getString(key);
    }
    public static int getIntProperty(String key) {
        return Integer.parseInt(resourceBundle.getString(key));
    }
}
