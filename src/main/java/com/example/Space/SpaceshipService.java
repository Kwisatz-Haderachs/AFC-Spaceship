package com.example.Space;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class SpaceshipService {
    private ArrayList<CrewMember> crew;
    private Spaceship voyager;

    private ArrayList<CrewMember> generateStaticCrew() {
        String[] names = {"John","Jack","Jill","Jane","Joy","Judy","Jude","James", "Jake", "Jules", "Jessica"};
        ArrayList<CrewMember> crewGen = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            CrewMember j = new CrewMember(i+1,names[i]);
            crewGen.add(j);
        }
        return crewGen;
    }

    public SpaceshipService(){
        this.crew = generateStaticCrew();
        voyager = new Spaceship(15, "Voyager", Type.CORVETTE);
        voyager.setCrew(crew);
    }

    public List<CrewMember> getCrew(boolean sort) {
        if(!sort) return voyager.getCrew();
        voyager.getCrew().sort(Comparator.comparing(CrewMember::getName));
        return voyager.getCrew();
    }

    public CrewMember getCrewByID(int id) {
        for (CrewMember c: voyager.getCrew()) {
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public void addCrew(CrewMember c) {
        voyager.addToCrew(c);
    }

    public Spaceship getSpaceship(){
        return voyager;
    }

    public void upgradeSpaceship(Map<String, String> upgrades){
        voyager.setCrewSize(Integer.parseInt(upgrades.get("Crew Size")));
        voyager.setArmorRating(Double.parseDouble(upgrades.get("Armor")));
        voyager.setShieldRating(Double.parseDouble(upgrades.get("Shield")));
        voyager.setType(upgrades.get("TypeOf"));
        voyager.setWeaponsSRating(Double.parseDouble(upgrades.get("Weapons")));
    }

}
