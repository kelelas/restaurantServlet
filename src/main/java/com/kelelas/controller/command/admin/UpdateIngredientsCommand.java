package com.kelelas.controller.command.admin;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.AdminPageService;
import com.kelelas.model.service.IngredientService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateIngredientsCommand implements Command {
    IngredientService ingredientService;
    AdminPageService adminPageService;
    private static final Logger logger = Logger.getLogger(UpdateIngredientsCommand.class);

    public UpdateIngredientsCommand() {
        this.ingredientService = new IngredientService();
        this.adminPageService = new AdminPageService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("ingredients", ingredientService.getLocaleIngredients(request));
        String id = request.getParameter("id");
        if (id!=null) {
            try {
                adminPageService.updateIngredientById(id);
                return PageConfig.getProperty("path.page.redirect.admin.updateIngredient");
            } catch (Exception e) {
                logger.error(e.getStackTrace());
            }
        }
        return PageConfig.getProperty("path.page.admin.updateIngredients");
    }
}
