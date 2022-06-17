package com.integration.project.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    public List<Game> getGames(){
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Integer id){
        return gameRepository.findById(id);
    }

    public void save(Game game){
        gameRepository.save(game);
    }

    public void deleteAll(){ gameRepository.deleteAll(); }

    public List<ProjectGame> getGamesProjected() {return gameRepository.findAllProjectedBy();}
}
