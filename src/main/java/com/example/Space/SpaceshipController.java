package com.example.Space;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spaceship")
public class SpaceshipController {
    private final SpaceshipService ss;
    public SpaceshipController(SpaceshipService ss){this.ss = ss;}

    @RequestMapping("/")
    public Spaceship getSpaceship(){
        return ss.getSpaceship();
    }

    @RequestMapping("/crew")
    public List<CrewMember> getCrew(@RequestParam (defaultValue = "false", required = false) boolean sort){
        return ss.getCrew(sort);
    }

    @RequestMapping("/crew/{id}")
    public CrewMember getCrewByID(@PathVariable String id){
        int i;
        try{
            i = Integer.parseInt(id);
            return ss.getCrewByID(i);
        }catch (NumberFormatException e){
            return null;
        }
    }

    @RequestMapping("/crew/{id}/{role}")
    public CrewMember setCrewRole(@PathVariable Integer id, @PathVariable String role){
        try{
            ss.getCrewByID(id).setRole(role);
            return ss.getCrewByID(id);
        }catch (NumberFormatException e){
            return null;
        }
    }

    @RequestMapping("/crew/new")
    public List<CrewMember> addCrewMembers(@RequestBody Map<String, List<CrewMember>> newCrew){
        for (CrewMember c: newCrew.get("newCrew")) {
            ss.addCrew(c);
        }
        return ss.getCrew(false);
    }

    @GetMapping("/current")
    public String getCurrent(@CookieValue(required = false, name = "current") String current) {
        if(current != null ){
            return "Your current spaceship has the id of " + current;
        }
        return "<empty>";
    }

    @RequestMapping("/upgrade")
    public Spaceship upgradeShip(@RequestBody Map<String, String> upgrades){
        ss.upgradeSpaceship(upgrades);
        return ss.getSpaceship();
    }
}
