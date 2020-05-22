package com.kelelas.controller.command.admin;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.ConstantsConfig;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.HistoryService;

import javax.servlet.http.HttpServletRequest;

public class StatisticsCommand implements Command {
    HistoryService historyService;

    public StatisticsCommand() {
        historyService = new HistoryService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int page = ConstantsConfig.getIntProperty("page.defaultPage");
        int amountOfRecords=ConstantsConfig.getIntProperty("page.numberOfRecordsOnPage");
        if (request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("items", historyService.getLocaleStories( request, (page-1)*amountOfRecords, amountOfRecords));
        request.setAttribute("numberOfPage", historyService.numberOfRowsInTable()/amountOfRecords+1);
        request.setAttribute("currentPage", page);
        return PageConfig.getProperty("path.page.admin.statistics");
    }
}
