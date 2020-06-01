package com.kelelas.controller.command.user.post;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.command.user.BillCommand;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.UserPageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PayForOrderCommand implements Command {
    UserPageService userPageService;
    private static final Logger logger = Logger.getLogger(BillCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        userPageService = new UserPageService(request);
        String orderId= request.getParameter("orderId");
        try {
            userPageService.payForOrderById(orderId);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
        return PageConfig.getProperty("path.page.redirect.user.bill");
    }
}
