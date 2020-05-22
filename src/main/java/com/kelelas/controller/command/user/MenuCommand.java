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
    private static final Logger logger = Logger.getLogger(MenuCommand.class);

    public MenuCommand() {
        dishService = new DishService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        userPageService = new UserPageService(request);
        request.setAttribute("dishes", dishService.getLocaleDishes(request));
        String id = request.getParameter("id");
        if (id!=null) {
            try {
                userPageService.addDishToOrder(Integer.parseInt(id));
            } catch (Exception e) {
                logger.error(e.getStackTrace());
            }
        }
        return PageConfig.getProperty("path.page.user.menu");
    }
}
