package com.kelelas.controller.command.admin.post;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.command.admin.OrderListCommand;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.AdminPageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ConfirmOrderCommand implements Command {
    AdminPageService adminPageService;
    private static final Logger logger = Logger.getLogger(OrderListCommand.class);

    public ConfirmOrderCommand() {
        this.adminPageService = new AdminPageService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
            try {
                adminPageService.updateStoryById(orderId);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        return PageConfig.getProperty("path.page.redirect.admin.orderList");
    }
}
