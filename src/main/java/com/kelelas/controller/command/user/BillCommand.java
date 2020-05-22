package com.kelelas.controller.command.user;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.ConstantsConfig;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.BillService;
import com.kelelas.model.service.UserPageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class BillCommand implements Command {
    BillService billService;
    UserPageService userPageService;
    private static final Logger logger = Logger.getLogger(BillCommand.class);

    public BillCommand() {
        this.billService = new BillService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        userPageService = new UserPageService(request);
        request.setAttribute("items", billService.getLocaleBillsByStatusAndUserId(request,
                ConstantsConfig.getIntProperty("status.waitingForPay"), (Integer) request.getSession().getAttribute("userId")));
        String id = request.getParameter("id");
        if (id!=null){
            try {
                userPageService.payForOrderById(id);
            } catch (Exception e) {
                logger.error(e.getStackTrace());
            }
            return PageConfig.getProperty("path.page.redirect.user.bill");
        }
        return PageConfig.getProperty("path.page.user.bill");
    }
}
