package com.example.Space;

import java.util.random.RandomGenerator;

public class CrewMember {
    private int id;
    private String name;
    private Role role;

    public CrewMember(){}
    public CrewMember(int id, String name) {
        this.name = name;
        this.id = id;
        this.role = assignRole(id);


    }
    private Role assignRole(int id){
        int index = id % 3;
        Role r;
        switch (index){
            case(0)-> r = Role.ENGINEER;
            case(1)-> r = Role.PILOT;
            case(2)-> r = Role.MEDIC;
            default -> r= Role.MARINE;
        }
        return r;
    }

    public CrewMember(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        switch (role) {
            case ("Pilot") -> this.role = Role.PILOT;
            case ("Engineer") -> this.role = Role.ENGINEER;
            case ("Medic") -> this.role = Role.MEDIC;
            default -> this.role = Role.MARINE;
        }
    }
}
