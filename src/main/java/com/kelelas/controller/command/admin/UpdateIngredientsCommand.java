package com.kelelas.controller.command.admin;

import com.kelelas.controller.command.Command;
import com.kelelas.controller.config.PageConfig;
import com.kelelas.model.service.AdminPageService;
import com.kelelas.model.service.IngredientService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateIngredientsCommand implements Command {
    IngredientService ingredientService;


    public UpdateIngredientsCommand() {
        this.ingredientService = new IngredientService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("ingredients", ingredientService.getLocaleIngredients(request));
        return PageConfig.getProperty("path.page.admin.updateIngredients");
    }
}
