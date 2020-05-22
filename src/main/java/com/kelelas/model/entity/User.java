package com.kelelas.model.entity;


public class User {
    private int id;
    private String nameUkr;
    private String nameEng;
    private String email;
    private String password;
    private boolean isActive;
    private RoleType role;
    private int balance;

    private User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String name_ukr) {
        this.nameUkr = name_ukr;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String name_eng) {
        this.nameEng = name_eng;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static class Builder{
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder id(int id){
            user.id = id;
            return this;
        }
        public Builder nameUkr(String nameUkr){
            user.nameUkr = nameUkr;
            return this;
        }
        public Builder nameEng(String nameEng){
            user.nameEng = nameEng;
            return this;
        }
        public Builder email(String email){
            user.email = email;
            return this;
        }
        public Builder password(String password){
            user.password = password;
            return this;
        }
        public Builder isActive(boolean isActive){
            user.isActive = isActive;
            return this;
        }
        public Builder role(RoleType role){
            user.role = role;
            return this;
        }
        public Builder balance(int balance){
            user.balance = balance;
            return this;
        }
        public User build(){
            return user;
        }

    }
}
