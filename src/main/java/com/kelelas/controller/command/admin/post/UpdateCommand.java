package com.kelelas.controller.command.admin.post;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.command.admin.UpdateIngredientsCommand;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.AdminPageService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateCommand implements Command {
    AdminPageService adminPageService;
    private static final Logger logger = Logger.getLogger(UpdateIngredientsCommand.class);

    public UpdateCommand() {
        this.adminPageService = new AdminPageService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String ingredientId = request.getParameter("ingredientId");
        try {
            adminPageService.updateIngredientById(ingredientId);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
        return PageConfig.getProperty("path.page.redirect.admin.updateIngredient");
    }
}
