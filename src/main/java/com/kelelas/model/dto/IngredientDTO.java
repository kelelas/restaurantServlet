package com.kelelas.model.dto;


public class IngredientDTO {
    private int id;
    private String name;
    private int amount;
    private int maxAmount;

    private IngredientDTO() {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }
    public static class Builder{
        IngredientDTO ingredient = new IngredientDTO();

        public Builder id(int id){
            ingredient.id = id;
            return this;
        }
        public Builder name(String name){
            ingredient.name = name;
            return this;
        }

        public Builder amount(int amount){
            ingredient.amount = amount;
            return this;
        }

        public Builder maxAmount(int maxAmount){
            ingredient.maxAmount = maxAmount;
            return this;
        }

        public IngredientDTO build(){
            return ingredient;
        }
    }
}
