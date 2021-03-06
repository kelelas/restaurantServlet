package com.kelelas.controller.command.user;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.UserPageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CartCommand implements Command {
    UserPageService userPageService;
    private static final Logger logger = Logger.getLogger(CartCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        userPageService = new UserPageService(request);
        try {
            request.setAttribute("order", userPageService.localDishes());
            request.setAttribute("sum", userPageService.sum());

        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return PageConfig.getProperty("path.page.user.cart");
    }
}
