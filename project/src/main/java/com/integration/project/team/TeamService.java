package com.integration.project.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getTeams(){return teamRepository.findAll();}

    public Optional<Team> getTeamById(Integer id){
        return teamRepository.findById(id);
    }

    public List<Team> getTeamByName(String name){return teamRepository.findByNameEquals(name);}
}
