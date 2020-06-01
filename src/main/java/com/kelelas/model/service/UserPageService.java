package com.kelelas.model.service;

import com.kelelas.controller.config.ConstantsConfig;
import com.kelelas.model.dto.DishDTO;
import com.kelelas.model.entity.*;
import com.kelelas.model.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserPageService {
    UserService userService;
    HistoryService historyService;
    CartService cartService;
    BillService billService;
    TransactionService transactionService;
    HttpServletRequest request;
    User user;
    int sum= ConstantsConfig.getIntProperty("number.defaultNumber");

//    public UserPageService(UserService userService, DishService dishService, HistoryService historyService, CartService cartService, BillService billService, HttpServletRequest request) {
//        this.userService = userService;
//        this.dishService = dishService;
//        this.historyService = historyService;
//        this.cartService = cartService;
//        this.billService = billService;
//        this.request = request;
//        user = (User) request.getSession().getAttribute("user");
//    }

    public UserPageService(HttpServletRequest request) {
        this.userService = new UserService();
        this.historyService = new HistoryService();
        this.cartService = new CartService();
        this.billService= new BillService();
        this.transactionService = new TransactionService();
        this.request = request;
        user = (User) request.getSession().getAttribute("user");
    }

    public void payForOrderById(String id) {
        try {
            transactionService.setAutoCommitFalse();

            Bill bill = billService.getBillById(Integer.parseInt(id));
                pay(bill.getPrice());
                bill.setStatus(ConstantsConfig.getIntProperty("status.finished"));
                billService.update(bill);
                historyService.save(convertBillToHistory(bill));

            transactionService.commit();

        } catch (Exception e) {
            transactionService.rollback();
            throw new DBException(e);
        }
    }

    public void refillUserBalance(){
        user.setBalance(user.getBalance() + ConstantsConfig.getIntProperty("balance.addMoneyToUserBalance"));
        userService.save(user);
    }





    public void addDishToOrder( Integer id) {
        cartService.addToCart(new Cart(user.getId(), id));
    }

    public void deleteDishFromOrder(Integer id){
       cartService.deleteFromCart(new Cart(user.getId(), id));
        sum();
    }

    public List<Dish> dishes()  {
        return cartService.getCart(user.getId());
    }

    public List<DishDTO> localDishes() {
        return cartService.getLocalCart(request);
    }

    public int sum() {
        sum = ConstantsConfig.getIntProperty("number.defaultNumber");
        localDishes().forEach((d)-> sum+=d.getPrice());
        return sum;
    }

    public void confirm(){
        if (user.getBalance()>=sum() ) {
            saveToStory();
            cartService.clearCart(user.getId());
        }
    }


    public void pay(int sum){
        user.setBalance(user.getBalance() - sum);
        userService.save(user);
    }

    public void saveToStory() {
        History historyItem = new History.Builder()
                .date(LocalDateTime.now())
                .price(sum())
                .userId(user.getId())
                .status(ConstantsConfig.getIntProperty("status.waitingForConfirm"))
                .dishes(dishes())
                .build();
        historyService.save(historyItem);
    }

    public History convertBillToHistory(Bill bill){
        return new History.Builder()
                .status(bill.getStatus())
                .dishes(new ArrayList<>(bill.getDishes()))
                .date(bill.getDate())
                .price(bill.getPrice())
                .userId(bill.getUserId())
                .build();
    }
}
