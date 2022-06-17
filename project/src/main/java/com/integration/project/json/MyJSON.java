package com.integration.project.json;

import com.integration.project.game.Game;
import com.integration.project.game.GameService;
import com.integration.project.player.Player;
import com.integration.project.team.Team;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class MyJSON {

    public static void importData(GameService gameService){

        try {
            JSONArray jsonDocument = (JSONArray)JSONValue.parse(new FileReader("data/lol_esport_data.json"));
            Set<String> keys = getKeys((JSONObject) jsonDocument.get(0));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            gameService.deleteAll();
            for(Object o: jsonDocument){
                if(o instanceof JSONObject){
                    JSONObject wiersz = (JSONObject) o;
                    if(hasEmpty(wiersz, keys))
                        continue;


                    Game adding = new Game(
                            (String) wiersz.get("league"),
                            Integer.parseInt(wiersz.get("year").toString()),
                            LocalDateTime.parse((CharSequence) wiersz.get("date"), formatter),
                            Integer.parseInt(wiersz.get("gamelength").toString())
                    );
                    addTeams(adding, wiersz);
                    gameService.save(adding);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static Set getKeys(JSONObject object){
        return object.keySet();
    }

    private static boolean hasEmpty(JSONObject object, Set<String> keySet){
        for(String s: keySet){
            if(object.get(s)=="" || object.get(s)==null)
                return true;
        }
        return false;
    }

    private static void addTeams(Game game, JSONObject wiersz){
        for(int i=1; i<=2; i++){
            Team adding = new Team(
                    (String) wiersz.get("t"+i+"_playerid"),
                    (String) wiersz.get("t"+i+"_ban1"),
                    (String) wiersz.get("t"+i+"_ban2"),
                    (String) wiersz.get("t"+i+"_ban3"),
                    (String) wiersz.get("t"+i+"_ban4"),
                    (String) wiersz.get("t"+i+"_ban5"),
                    Integer.parseInt(wiersz.get("t"+i+"_result").toString()),
                    Integer.parseInt(wiersz.get("t"+i+"_kills").toString()),
                    Integer.parseInt(wiersz.get("t"+i+"_deaths").toString()),
                    Integer.parseInt(wiersz.get("t"+i+"_assists").toString()),
                    Integer.parseInt(wiersz.get("t"+i+"_damagetochampions").toString()),
                    Integer.parseInt(wiersz.get("t"+i+"_visionscore").toString()),
                    Integer.parseInt(wiersz.get("t"+i+"_totalgold").toString())
            );
            addPlayers(adding, wiersz, i);
            game.addTeam(adding);
        }
    }

    private static void addPlayers(Team team, JSONObject wiersz, int team_num){
        for(int i=1; i<=5; i++){
            Player adding = new Player(
                    wiersz.get("t"+team_num+"p"+i+"_position").toString(),
                    wiersz.get("t"+team_num+"p"+i+"_player").toString(),
                    wiersz.get("t"+team_num+"p"+i+"_champion").toString(),
                    Integer.parseInt(wiersz.get("t"+team_num+"p"+i+"_kills").toString()),
                    Integer.parseInt(wiersz.get("t"+team_num+"p"+i+"_deaths").toString()),
                    Integer.parseInt(wiersz.get("t"+team_num+"p"+i+"_assists").toString()),
                    Integer.parseInt(wiersz.get("t"+team_num+"p"+i+"_damagetochampions").toString())
            );
            team.addPlayer(adding);
        }
    }

    public static void exportData(GameService gameService){
        //JSONArray export = new JSONArray();

        try {
            FileWriter fileWriter = new FileWriter("data/lol_esport_data-export.json");
            fileWriter.write("[\n");

            List<Game> games = gameService.getGames();
            for (Game game : games) {
                JSONObject row = new JSONObject();
                row.put("league", game.getLeague());
                row.put("year", game.getYear());
                row.put("date", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(game.getDate()));
                row.put("gamelength", game.getGamelength());

                for (int i = 1; i <= game.getTeams().size(); i++) {
                    Team team = game.getTeams().get(i - 1);
                    row.put("t" + i + "_playerid", team.getName());
                    row.put("t" + i + "_ban1", team.getBan1());
                    row.put("t" + i + "_ban2", team.getBan2());
                    row.put("t" + i + "_ban3", team.getBan3());
                    row.put("t" + i + "_ban4", team.getBan4());
                    row.put("t" + i + "_ban5", team.getBan5());

                    row.put("t" + i + "_result", team.getResult());
                    row.put("t" + i + "_kills", team.getKills());
                    row.put("t" + i + "_deaths", team.getDeaths());
                    row.put("t" + i + "_assists", team.getAssists());
                    row.put("t" + i + "_damagetochampions", team.getDamageToChampions());
                    row.put("t" + i + "_visionscore", team.getVisionScore());
                    row.put("t" + i + "_totalgold", team.getTotalGold());

                    for (int j = 1; j <= team.getPlayers().size(); j++) {
                        Player player = team.getPlayers().get(j - 1);
                        row.put("t" + i + "p" + j + "_position", player.getPosition());
                        row.put("t" + i + "p" + j + "_player", player.getName());
                        row.put("t" + i + "p" + j + "_champion", player.getChampion());
                        row.put("t" + i + "p" + j + "_kills", player.getKills());
                        row.put("t" + i + "p" + j + "_deaths", player.getDeaths());
                        row.put("t" + i + "p" + j + "_assists", player.getAssists());
                        row.put("t" + i + "p" + j + "_damagetochampions", player.getDamageToChampions());
                    }//Dodawanie graczy
                }//Dodawanie druzyn
                //export.add(row);
                JSONValue.writeJSONString(row, fileWriter);
                fileWriter.write(",\n");

            }//Dodawanie wiersza
            fileWriter.write("]");
            fileWriter.flush();
            fileWriter.close();


        }catch (Exception e){
            e.printStackTrace();
        }


//      JSONValue.writeJSONString(export, new FileWriter("data/lol_esport_data-export.json"));
//      Nie dziala z jakiegos powodu i ucina czesc
    }

}
