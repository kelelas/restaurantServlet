package com.kelelas.controller.command.user;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.UserPageService;

import javax.servlet.http.HttpServletRequest;

public class RefillCommand implements Command {
    UserPageService userPageService;
    @Override
    public String execute(HttpServletRequest request) {
        userPageService = new UserPageService(request);
        String ok = request.getParameter("ok");
        if (ok!=null) {
            userPageService.refillUserBalance();
        }
        return PageConfig.getProperty("path.page.user.refill");
    }
}
