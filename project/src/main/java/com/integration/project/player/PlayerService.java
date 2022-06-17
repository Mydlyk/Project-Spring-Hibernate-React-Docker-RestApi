package com.integration.project.player;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<Player> getPlayerById(Integer id) {
        return playerRepository.findById(id);
    }

    public List<Player> getPlayers(){return playerRepository.findAll();}

    public List<Player> getPlayersByName(String name){
        return playerRepository.findByNameEquals(name);
    }
}
