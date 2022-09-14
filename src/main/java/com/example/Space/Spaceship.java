package com.example.Space;

import java.util.ArrayList;
import java.util.List;

public class Spaceship {
    private int crewSize;
    private String name;
    private Type type;
    private List<CrewMember> crew;

    private double weaponsRating;
    private double shieldRating;
    private double armorRating;

    public double getWeaponsRating() {
        return weaponsRating;
    }

    public void setWeaponsSRating(double weaponsRating) {
        this.weaponsRating = weaponsRating;
    }

    public double getShieldRating() {
        return shieldRating;
    }

    public void setShieldRating(double shieldRating) {
        this.shieldRating = shieldRating;
    }

    public double getArmorRating() {
        return armorRating;
    }

    public void setArmorRating(double armorRating) {
        this.armorRating = armorRating;
    }

    public Spaceship(){}

    public Spaceship(int crewSize, String name, Type type){
        this.crewSize = crewSize;
        this.name = name;
        this.type = type;
        this.crew = new ArrayList<>();
    }

    public List<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewMember> crew) {
        if(crew.size() <= crewSize){
            this.crew = crew;
        }
        System.out.println("Crew is too large!");
    }

    public void addToCrew(CrewMember crewMember){
        if(crew.size() < crewSize)
            crew.add(crewMember);
        System.out.printf("%s can't be added, at capacity!", crewMember.getName());
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(String type) {
        switch (type) {
            case ("Destroyer") -> this.type = Type.DESTROYER;
            case ("Cruiser") -> this.type = Type.CRUISER;
            default -> this.type = Type.CORVETTE;
        }
    }
}
