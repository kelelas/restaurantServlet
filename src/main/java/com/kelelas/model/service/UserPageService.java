package com.kelelas.model.service;

import com.kelelas.controller.config.ConstantsConfig;
import com.kelelas.model.dto.DishDTO;
import com.kelelas.model.entity.*;
import com.kelelas.model.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public class UserPageService {
    UserService userService;
    DishService dishService;
    HistoryService historyService;
    CartService cartService;
    BillService billService;
    HttpServletRequest request;
    User user;
    int sum= ConstantsConfig.getIntProperty("number.defaultNumber");

    public UserPageService(HttpServletRequest request) {
        this.userService = new UserService();
        this.dishService = new DishService();
        this.historyService = new HistoryService();
        this.cartService = new CartService();
        this.billService= new BillService();
        this.request = request;
        user = (User) request.getSession().getAttribute("user");
    }

    public void payForOrderById(String id) {
        try {
            billService.setAutoCommitFalse();
            historyService.setAutoCommitFalse();
            Bill bill = billService.getBillById(Integer.parseInt(id));
            if (bill.getStatus() == ConstantsConfig.getIntProperty("status.waitingForPay")) {
                pay(bill.getPrice());
                bill.setStatus(ConstantsConfig.getIntProperty("status.finished"));
                billService.update(bill);
                historyService.save(convertBillToHistory(bill));
            }
            billService.commit();
            historyService.commit();
        } catch (Exception e) {
            billService.rollback();
            historyService.rollback();
            throw new DBException(e);
        }
        // historyItem = storiesService.getStoryById(Long.parseLong(id)).isPresent() ? storiesService.getStoryById(Long.parseLong(id)).get() : log.info("{Почтовый адрес уже существует}");;

    }

    public void refillUserBalance(){
        user.setBalance(user.getBalance() + ConstantsConfig.getIntProperty("balance.addMoneyToUserBalance"));
        userService.save(user);
    }





    public void addDishToOrder( Integer id) {
        cartService.addToCart(new Cart(user.getId(), id));
        sum();
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
        for (DishDTO dish : localDishes()){
            sum += dish.getPrice();
        }
        request.setAttribute("sum", sum);
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
                .status(1)
                .dishes(dishes())
                .build();
        historyService.save(historyItem);
    }

    public History convertBillToHistory(Bill bill){
        return new History.Builder()
                .status(bill.getStatus())
                .dishes(bill.getDishes())
                .date(bill.getDate())
                .price(bill.getPrice())
                .userId(bill.getUserId())
                .id(bill.getId())
                .build();
    }
}
