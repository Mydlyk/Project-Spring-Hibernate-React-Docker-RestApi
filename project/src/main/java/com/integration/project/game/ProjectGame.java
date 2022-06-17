package com.integration.project.game;

import com.integration.project.team.Team;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectGame {
    Integer getId();
    String getLeague();
    Integer getYear();
    LocalDateTime getDate();
    Integer getGamelength();

    List<TeamSummary> getTeams();

    interface TeamSummary{
        String getName();
    }

}
