package com.integration.project.game;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.integration.project.team.Team;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Game")
@Table(name = "game")
public class Game {

    @Id
    @SequenceGenerator(
            name = "game_sequence",
            sequenceName = "game_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "game_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @Column(
            nullable = false
    )
    private String league;

    @Column(
            nullable = false
    )
    private Integer year;

    @Column(
            nullable = false
    )
    private LocalDateTime date;

    @Column(
            nullable = false
    )
    private int gamelength;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "game",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Team> teams = new ArrayList<>();

    public Game() {
    }

    public Game(String league, Integer year, LocalDateTime date, int gamelength) {
        this.league = league;
        this.year = year;
        this.date = date;
        this.gamelength = gamelength;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getGamelength() {
        return gamelength;
    }

    public void setGamelength(int gamelength) {
        this.gamelength = gamelength;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


    public void addTeam(Team team) {
        if (!this.teams.contains(team)) {
            this.teams.add(team);
            team.setGame(this);
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", league='" + league + '\'' +
                ", year=" + year +
                ", date=" + date +
                ", gamelength=" + gamelength +
                ", teams=" + teams +
                '}';
    }
}
