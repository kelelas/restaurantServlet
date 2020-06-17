package com.kelelas.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDTO {
    private int id;
    private String date;
    private int price;
    private String status;
    private String userName;
    private List<DishDTO> dishes = new ArrayList<>();

    private BillDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDTO> dishes) {
        this.dishes = dishes;
    }
    public static class Builder{
        BillDTO bill = new BillDTO();

        public Builder id(int id){
            bill.id = id;
            return this;
        }
        public Builder date(String date){
            bill.date = date;
            return this;
        }

        public Builder price(int price){
            bill.price = price;
            return this;
        }
        public Builder status(String status){
            bill.status = status;
            return this;
        }
        public Builder userName(String userName){
            bill.userName = userName;
            return this;
        }
        public Builder dishes(List<DishDTO> dishes){
            bill.dishes = dishes;
            return this;
        }
        public BillDTO build(){
            return bill;
        }
    }
}
