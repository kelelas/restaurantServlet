package com.kelelas.controller.command.user;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.DishService;
import com.kelelas.model.service.UserPageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class MenuCommand implements Command {
    DishService dishService;
    UserPageService userPageService;

    public MenuCommand() {
        dishService = new DishService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        userPageService = new UserPageService(request);
        request.setAttribute("dishes", dishService.getLocaleDishes(request));
        return PageConfig.getProperty("path.page.user.menu");
    }
}
