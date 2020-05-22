package com.kelelas.model.dto;

public class NewUser {
    private String nameUkr;
    private String nameEng;
    private String email;
    private String password;


    private NewUser() {
    }

    public String getNameUkr() {
        return nameUkr;
    }

    public void setNameUkr(String nameUkr) {
        this.nameUkr = nameUkr;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
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

    public static class Builder{
        private NewUser user;

        public Builder() {
            user = new NewUser();
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

        public NewUser build(){
            return user;
        }

    }
}
