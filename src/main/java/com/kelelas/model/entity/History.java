package com.kelelas.model.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class History {
    private int id;
    private LocalDateTime date;
    private int price;
    private int userId;
    private int status;
    private List<Dish> dishes = new ArrayList<>();;

    private History() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public static class Builder{
        History history = new History();

        public Builder id(int id){
            history.id = id;
            return this;
        }
        public Builder date (LocalDateTime date){
            history.date = date;
            return this;
        }
        public Builder price(int price){
            history.price = price;
            return this;
        }
        public Builder userId(int userId){
            history.userId = userId;
            return this;
        }
        public Builder status(int status){
            history.status = status;
            return this;
        }
        public Builder dishes(List<Dish> dishes){
            history.dishes = dishes;
            return this;
        }

        public History build(){
            return history;
        }

    }
}
