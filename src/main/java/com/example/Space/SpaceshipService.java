package com.example.Space;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class SpaceshipService {

    private ArrayList<Spaceship> starships;

    private ArrayList<CrewMember> generateJCrew() {
        String[] names = {"John","Jack","Jill","Jane","Joy","Judy","Jude","James", "Jake", "Jules", "Jessica"};
        ArrayList<CrewMember> crewGen = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            CrewMember j = new CrewMember(i+1,names[i]);
            crewGen.add(j);
        }
        return crewGen;
    }

    private ArrayList<CrewMember> generateLCrew() {
        String[] names = {"Azir","Annie","Mal'Zhar","Caitlin","Jynx","Gareth","Lux","Senna", "Lucian", "Varus", "Rakkan"};
        ArrayList<CrewMember> crewGen = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            CrewMember j = new CrewMember(i+1,names[i]);
            crewGen.add(j);
        }
        return crewGen;
    }
    public SpaceshipService(){
        ArrayList<CrewMember> jCrew = generateJCrew();
        ArrayList<CrewMember> lCrew = generateLCrew();
        this.starships = new ArrayList<>();
        Spaceship voyager = new Spaceship(15, "Voyager", Type.CORVETTE);
        Spaceship enterprise = new Spaceship(50, "Enterprise", Type.EXPLORATORY);
        voyager.setCrew(jCrew);
        enterprise.setCrew(lCrew);
        this.starships.add(voyager);
        this.starships.add(enterprise);
    }

    public Spaceship getSpaceship(String name){
        for (Spaceship s: starships) {
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    public List<CrewMember> getCrew(String name, boolean sort) {
        if(!sort) return getSpaceship(name).getCrew();
        getSpaceship(name).getCrew().sort(Comparator.comparing(CrewMember::getName));
        return getSpaceship(name).getCrew();
    }

    public CrewMember getCrewByID(String name, int id) {
        for (CrewMember c: getSpaceship(name).getCrew()) {
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public void addCrew(String name, CrewMember c) {
        if(getSpaceship(name).getCrew().size() < getSpaceship(name).getCrewSize()) {
            getSpaceship(name).addToCrew(c);
        }
    }

    public void upgradeSpaceship(String name, Map<String, String> upgrades){
        getSpaceship(name).setCrewSize(Integer.parseInt(upgrades.get("Crew Size")));
        getSpaceship(name).setArmorRating(Double.parseDouble(upgrades.get("Armor")));
        getSpaceship(name).setShieldRating(Double.parseDouble(upgrades.get("Shield")));
        getSpaceship(name).setType(upgrades.get("TypeOf"));
        getSpaceship(name).setWeaponsSRating(Double.parseDouble(upgrades.get("Weapons")));
    }

}
