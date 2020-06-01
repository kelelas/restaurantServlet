package com.kelelas.controller.command.user.post;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.command.user.CartCommand;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.UserPageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteFromOrderCommand implements Command {
    UserPageService userPageService;
    private static final Logger logger = Logger.getLogger(CartCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        userPageService = new UserPageService(request);
        String orderId = request.getParameter("orderId");
        try {
            userPageService.deleteDishFromOrder(Integer.parseInt(orderId));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return PageConfig.getProperty("path.page.redirect.user.cart");
    }
}
