package com.example.Space;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spaceship")
public class SpaceshipController {
    private final SpaceshipService ss;
    public SpaceshipController(SpaceshipService ss){this.ss = ss;}

    @RequestMapping("/{name}")
    public Spaceship getSpaceship(@PathVariable String name){
        return ss.getSpaceship(name);
    }

    @RequestMapping("/{name}/crew")
    public List<CrewMember> getCrew(@PathVariable String name, @RequestParam (defaultValue = "false", required = false) boolean sort){
        return ss.getCrew(name, sort);
    }

    @RequestMapping("/{name}/crew/{id}")
    public CrewMember getCrewByID(@PathVariable String name, @PathVariable String id){
        try{
            return ss.getCrewByID(name, Integer.parseInt(id));
        }catch (NumberFormatException e){
            return null;
        }
    }

    @RequestMapping("/{name}/crew/{id}/{role}")
    public CrewMember setCrewRole(@PathVariable String name, @PathVariable Integer id, @PathVariable String role){
        try{
            ss.getCrewByID(name, id).setRole(role);
            return ss.getCrewByID(name, id);
        }catch (NumberFormatException e){
            return null;
        }
    }

    @RequestMapping("/{name}/crew/new")
    public List<CrewMember> addCrewMembers(@PathVariable String name, @RequestBody Map<String, List<CrewMember>> newCrew){
        for (CrewMember c: newCrew.get("newCrew")) {
            ss.addCrew(name, c);
        }
        return ss.getCrew(name, false);
    }

    @GetMapping("/current")
    public String getCurrent(@CookieValue(required = false, name = "current") String current) {
        if(current != null ){
            return "Your current spaceship has the id of " + current;
        }
        return "<empty>";
    }

    @RequestMapping("/{name}/upgrade")
    public Spaceship upgradeShip(@PathVariable String name, @RequestBody Map<String, String> upgrades){
        ss.upgradeSpaceship(name, upgrades);
        return ss.getSpaceship(name);
    }
}
