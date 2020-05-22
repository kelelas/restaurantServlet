package com.kelelas.controller.command.admin;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.ConstantsConfig;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.dto.HistoryDTO;
import com.kelelas.model.service.AdminPageService;
import com.kelelas.model.service.HistoryService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderListCommand implements Command {
    HistoryService historyService;
    AdminPageService adminPageService;
    private static final Logger logger = Logger.getLogger(OrderListCommand.class);

    public OrderListCommand() {
        this.historyService = new HistoryService();
        this.adminPageService = new AdminPageService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<HistoryDTO> items = historyService.getLocaleStoriesByStatus(
                request,
                ConstantsConfig.getIntProperty("status.waitingForConfirm"));

        if (!items.isEmpty())
            request.setAttribute("items", items);

       String id = request.getParameter("id");
        if (id!=null) {
            try {
                adminPageService.updateStoryById(id);
                return PageConfig.getProperty("path.page.redirect.admin.orderList");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return PageConfig.getProperty("path.page.admin.orderList");
    }
}
