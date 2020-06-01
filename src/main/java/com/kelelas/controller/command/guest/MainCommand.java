package com.kelelas.controller.command.guest;


import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.PageConfig;

import javax.servlet.http.HttpServletRequest;

;

public class MainCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        return PageConfig.getProperty("path.page.main");
    }
}
