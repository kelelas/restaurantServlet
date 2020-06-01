package com.kelelas.model.service;

import com.kelelas.controller.config.ConstantsConfig;
import com.kelelas.model.entity.Dish;
import com.kelelas.model.entity.History;
import com.kelelas.model.entity.Ingredient;
import com.kelelas.model.exception.DBException;

import java.util.List;

public class AdminPageService {
    HistoryService historyService;
    IngredientService ingredientService;
    BillService billService;
    TransactionService transactionService;


    public AdminPageService() {
        this.historyService = new HistoryService();
        this.ingredientService = new IngredientService();
        this.billService = new BillService();
        this.transactionService = new TransactionService();
    }

//    public AdminPageService(HistoryService historyService, DishService dishService, IngredientService ingredientService, BillService billService) {
//        this.historyService = historyService;
//        this.dishService = dishService;
//        this.ingredientService = ingredientService;
//        this.billService = billService;
//    }

    public void confirm(List<Dish> dishes) {
        try {
            dishes.stream()
                    .map(Dish::getIngredients)
                    .forEach((i)
                            -> i.forEach((j)
                            ->{j.setAmount(j.getAmount()-1);ingredientService.save(j);}));
        }catch (Exception e){
            throw new DBException(e);
        }
    }

    public void updateStoryById(String id)  {
        try {
            transactionService.setAutoCommitFalse();

            History historyItem = historyService.getStoryById(Integer.parseInt(id));
                historyItem.setStatus(ConstantsConfig.getIntProperty("status.newOrder"));
                confirm(historyItem.getDishes());
                billService.saveNewBill(historyItem);
                historyService.update(historyItem);

            transactionService.commit();

        }catch (Exception e){
            transactionService.rollback();
            throw new DBException(e);
        }

    }
    public void updateIngredientById(String id){
        try {
            transactionService.setAutoCommitFalse();
            Ingredient ingredient = ingredientService.getIngredientById(Integer.parseInt(id));
            ingredient.setAmount(ingredient.getMaxAmount());
            ingredientService.save(ingredient);
            transactionService.commit();
        }catch (Exception e){
            transactionService.rollback();
            throw new DBException(e);
        }

    }
}
