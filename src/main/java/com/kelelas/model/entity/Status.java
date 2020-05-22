package com.kelelas.model.entity;



public class Status {
    private int id;
    private String nameEng;
    private String nameUkr;

    private Status() {
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
    public static class Builder{
        Status status = new Status();

        public Builder id(int id){
            status.id = id;
            return this;
        }
        public Builder nameUkr(String nameUkr){
            status.nameUkr = nameUkr;
            return this;
        }
        public Builder nameEng(String nameEng){
            status.nameEng = nameEng;
            return this;
        }

        public Status build(){
            return status;
        }
    }
}
