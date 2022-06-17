package com.integration.project.team;

import com.integration.project.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    //Zwraca wszystkie drużyny
    @GetMapping
    public List<Team> getAll(){
        return teamService.getTeams();
    }

    //Zwraca drużynę po id lub po nazwie
    @GetMapping("/by")
    public List<Team> getById(@RequestParam(name = "id", required = false) Integer id,
                              @RequestParam(name = "name", required = false) String name){

        if(id != null){
            return teamService.getTeamById(id).stream().toList();
        }
        if(name != null){
            System.out.println(name);
            return teamService.getTeamByName(name);
        }
        return List.of();
    }

}
