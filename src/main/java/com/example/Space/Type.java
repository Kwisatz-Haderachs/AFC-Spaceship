package com.example.Space;

public enum Type {
    CRUISER("Cruiser"),
    DESTROYER("Destroyer"),
    CORVETTE("Corvette"),
    EXPLORATORY("Exploratory");

    public String type;

    Type(String type){
        this.type = type;
    }

    public String getRole(){
        return type;
    }
    public void setRole(String type){
        this.type = type;
    }
}
