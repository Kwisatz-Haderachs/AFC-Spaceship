package com.example.Space;

public enum Role {
    PILOT("Pilot"),
    ENGINEER("Engineer"),
    MEDIC("Medic"),
    MARINE("Marine");

    public String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
         return role;
    }
    public void setRole(String role){
        this.role = role;
    }
}
