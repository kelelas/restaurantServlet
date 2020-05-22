package com.kelelas.controller.command;

import com.kelelas.controller.config.PageConfig;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PageConfig.getProperty("path.page.redirect.logout");
    }
}
