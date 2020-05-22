package com.kelelas.model.service;

import com.kelelas.controller.config.ConstantsConfig;
import com.kelelas.model.entity.Dish;
import com.kelelas.model.entity.History;
import com.kelelas.model.entity.Ingredient;
import com.kelelas.model.exception.DBException;
import java.util.List;

public class AdminPageService {
    HistoryService historyService;
    DishService dishService;
    IngredientService ingredientService;
    BillService billService;


    public AdminPageService() {
        this.historyService = new HistoryService();
        this.dishService = new DishService();
        this.ingredientService = new IngredientService();
        this.billService = new BillService();
    }


    public void confirm(List<Dish> dishes) throws Exception {
        try {
            ingredientService.setAutoCommitFalse();
            for (Dish dish : dishes){
                for (Ingredient ingredient: dish.getIngredients()){
                    ingredient.setAmount(ingredient.getAmount()-1);
                    ingredientService.save(ingredient);
                }
            }
            ingredientService.commit();
        }catch (Exception e){
            ingredientService.rollback();
            throw new DBException(e);
        }

    }

    public void updateStoryById(String id) throws Exception {
        try {
            historyService.setAutoCommitFalse();
            billService.setAutoCommitFalse();
            History historyItem = historyService.getStoryById(Integer.parseInt(id));
            if (historyItem.getStatus() == ConstantsConfig.getIntProperty("status.waitingForConfirm")) {
                historyItem.setStatus(ConstantsConfig.getIntProperty("status.newOrder"));
                confirm(historyItem.getDishes());
                billService.saveNewBill(historyItem);
                historyService.update(historyItem);
            }
            historyService.commit();
            billService.commit();
        }catch (Exception e){
            historyService.rollback();
            billService.rollback();
            throw new DBException(e);
        }

    }
    public void updateIngredientById(String id) throws Exception {
        try {
            ingredientService.setAutoCommitFalse();
            Ingredient ingredient = ingredientService.getIngredientById(Integer.parseInt(id));
            ingredient.setAmount(ingredient.getMaxAmount());
            ingredientService.save(ingredient);
            ingredientService.commit();
        }catch (Exception e){
            ingredientService.rollback();
            throw new DBException(e);
        }

    }
}
