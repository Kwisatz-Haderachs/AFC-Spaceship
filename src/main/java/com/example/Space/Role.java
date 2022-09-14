package com.example.Space;

public enum Role {
    ENGINEER("Engineer", 0),
    PILOT("Pilot", 1),
    MEDIC("Medic", 2),
    MARINE("Marine", 3);

    public String role;
    public int index;

    Role(String role, int i){
        this.role = role;
        this.index = i;
    }

    public String getRole(){
         return role;
    }
    public void setRole(String role){
        this.role = role;
    }
    public String getIndex() {return role;}
}
