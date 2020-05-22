package com.kelelas.controller.command.user;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.HistoryService;

import javax.servlet.http.HttpServletRequest;

public class HistoryCommand implements Command {
    HistoryService historyService;

    public HistoryCommand() {
        historyService = new HistoryService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("stories", historyService.getLocaleStoriesByUserId(request,(Integer) request.getSession().getAttribute("userId")));
        return PageConfig.getProperty("path.page.user.history");
    }
}
