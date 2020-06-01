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

    public BillCommand() {
        this.billService = new BillService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("items", billService.getLocaleBillsByStatusAndUserId(request,
                ConstantsConfig.getIntProperty("status.waitingForPay"), (Integer) request.getSession().getAttribute("userId")));
        return PageConfig.getProperty("path.page.user.bill");
    }
}
