package com.integration.project.team;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.integration.project.game.Game;
import com.integration.project.player.Player;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Team")
@Table(name = "team")
public class Team {
    @Id
    @SequenceGenerator(
            name = "team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "team_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String ban1;
    @Column(nullable = false)
    private String ban2;
    @Column(nullable = false)
    private String ban3;
    @Column(nullable = false)
    private String ban4;
    @Column(nullable = false)
    private String ban5;

    @Column(nullable = false)
    private Integer result;

    @Column(nullable = false)
    private Integer kills;
    @Column(nullable = false)
    private Integer deaths;
    @Column(nullable = false)
    private Integer assists;
    @Column(nullable = false)
    private Integer damageToChampions;
    @Column(nullable = false)
    private Integer visionScore;
    @Column(nullable = false)
    private Integer totalGold;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name = "game_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "game_team_fk"
            )
    )
    private Game game;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "team",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Player> players = new ArrayList<>();

    public Team() {
    }

    public Team(String name, String ban1, String ban2, String ban3, String ban4, String ban5, Integer result, Integer kills, Integer deaths, Integer assists, Integer damageToChampions, Integer visionScore, Integer totalGold) {
        this.name = name;
        this.ban1 = ban1;
        this.ban2 = ban2;
        this.ban3 = ban3;
        this.ban4 = ban4;
        this.ban5 = ban5;
        this.result = result;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.damageToChampions = damageToChampions;
        this.visionScore = visionScore;
        this.totalGold = totalGold;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBan1() {
        return ban1;
    }

    public void setBan1(String ban1) {
        this.ban1 = ban1;
    }

    public String getBan2() {
        return ban2;
    }

    public void setBan2(String ban2) {
        this.ban2 = ban2;
    }

    public String getBan3() {
        return ban3;
    }

    public void setBan3(String ban3) {
        this.ban3 = ban3;
    }

    public String getBan4() {
        return ban4;
    }

    public void setBan4(String ban4) {
        this.ban4 = ban4;
    }

    public String getBan5() {
        return ban5;
    }

    public void setBan5(String ban5) {
        this.ban5 = ban5;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
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

    public Integer getVisionScore() {
        return visionScore;
    }

    public void setVisionScore(Integer visionScore) {
        this.visionScore = visionScore;
    }

    public Integer getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(Integer totalGold) {
        this.totalGold = totalGold;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        if (!this.players.contains(player)) {
            this.players.add(player);
            player.setTeam(this);
        }
    }
}
