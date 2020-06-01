package com.kelelas.controller.command.user.post;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.command.user.MenuCommand;
import com.kelelas.model.service.UserPageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddToOrderCommand implements Command {
    UserPageService userPageService;
    private static final Logger logger = Logger.getLogger(MenuCommand.class);

    public AddToOrderCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        this.userPageService = new UserPageService(request);
        String orderId = request.getParameter("orderId");
        try {
            userPageService.addDishToOrder(Integer.parseInt(orderId));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "redirect:/user/menu";
    }
}
