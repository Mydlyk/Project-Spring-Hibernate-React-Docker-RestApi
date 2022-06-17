package com.integration.project.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.integration.project.team.Team;

import javax.persistence.*;

@Entity(name = "Player")
@Table(name = "player")
public class Player {
    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Integer id;

    @Column(
            nullable = false
    )
    private String position;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    private String champion;


    @Column(
            nullable = false
    )
    private Integer kills;

    @Column(
            nullable = false
    )
    private Integer deaths;

    @Column(
            nullable = false
    )
    private Integer assists;

    @Column(
            nullable = false
    )
    private Integer damageToChampions;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name = "team_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "player_team_fk"
            )
    )
    private Team team;

    public Player() {
    }

    public Player(String position, String name, String champion, Integer kills, Integer deaths, Integer assists, Integer damageToChampions) {
        this.position = position;
        this.name = name;
        this.champion = champion;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.damageToChampions = damageToChampions;
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getDamageToChampions() {
        return damageToChampions;
    }

    public void setDamageToChampions(Integer damageToChampions) {
        this.damageToChampions = damageToChampions;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
