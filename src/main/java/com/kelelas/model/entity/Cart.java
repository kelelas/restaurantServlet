package com.kelelas.model.entity;


public class Cart {
    private int userId;
    private int dishId;

    public Cart(int userId, int dishId) {
        this.userId = userId;
        this.dishId = dishId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
}
