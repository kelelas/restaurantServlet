package com.kelelas.model.entity;


import java.util.ArrayList;
import java.util.List;

public class Dish {
    private int id;
    private String image;
    private String nameEng;
    private String nameUkr;
    private int price;
    private List<Ingredient> ingredients = new ArrayList<>();;


    private Dish() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public static class Builder{
        Dish dish = new Dish();

        public Builder id(int id){
            dish.id = id;
            return this;
        }
        public Builder nameUkr(String nameUkr){
            dish.nameUkr = nameUkr;
            return this;
        }
        public Builder nameEng(String nameEng){
            dish.nameEng = nameEng;
            return this;
        }
        public Builder image(String image){
            dish.image = image;
            return this;
        }
        public Builder price(int price){
            dish.price = price;
            return this;
        }
        public Builder ingredients(List<Ingredient> ingredients){
            dish.ingredients = ingredients;
            return this;
        }
        public Dish build(){
            return dish;
        }
    }
}
