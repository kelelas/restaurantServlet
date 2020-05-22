package com.kelelas.model.dto;


import java.util.ArrayList;
import java.util.List;

public class DishDTO {
    private int id;
    private String name;
    private int price;
    private String image;
    List<IngredientDTO> ingredients = new ArrayList<>();

    private DishDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public static class Builder{
        DishDTO dish = new DishDTO();

        public Builder id(int id){
            dish.id = id;
            return this;
        }
        public Builder name(String name){
            dish.name = name;
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
        public Builder ingredients(List<IngredientDTO> ingredients){
            dish.ingredients = ingredients;
            return this;
        }
        public DishDTO build(){
            return dish;
        }
    }
}
