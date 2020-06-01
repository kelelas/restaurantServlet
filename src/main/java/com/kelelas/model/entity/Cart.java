package com.kelelas.model.entity;


public class Cart {
    private int id;
    private int userId;
    private int dishId;

    public Cart(int userId, int dishId) {
        this.userId = userId;
        this.dishId = dishId;
    }

    public Cart(int id, int userId, int dishId) {
        this.id=id;
        this.userId = userId;
        this.dishId = dishId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
