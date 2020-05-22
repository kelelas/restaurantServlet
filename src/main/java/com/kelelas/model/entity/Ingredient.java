package com.kelelas.model.entity;


public class Ingredient {
    private int id;
    private String nameEng;
    private String nameUkr;
    private int amount;
    private int maxAmount;

    private Ingredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        Ingredient ingredient = new Ingredient();

        public Builder id(int id){
            ingredient.id = id;
            return this;
        }
        public Builder nameUkr(String nameUkr){
            ingredient.nameUkr = nameUkr;
            return this;
        }
        public Builder nameEng(String nameEng){
            ingredient.nameEng = nameEng;
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

        public Ingredient build(){
            return ingredient;
        }
    }
}
