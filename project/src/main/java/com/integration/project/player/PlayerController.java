package com.integration.project.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    //Zwraca wszystkich graczy
    @GetMapping
    public List<Player> getAll() {return playerService.getPlayers();}

    //Zwraca gracza po id lub po nzawie
    @GetMapping("/by")
    public List<Player> getById(@RequestParam(name = "id", required = false) Integer id,
                                @RequestParam(name = "name", required = false) String name){

        if(id != null){
            return playerService.getPlayerById(id).stream().toList();
        }
        if(name != null){
            System.out.println(name);
            return playerService.getPlayersByName(name);
        }
        return List.of();
    }



}
