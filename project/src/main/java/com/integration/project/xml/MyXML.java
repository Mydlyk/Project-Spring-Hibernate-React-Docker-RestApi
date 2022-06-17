package com.integration.project.xml;

import com.integration.project.game.Game;
import com.integration.project.game.GameService;
import com.integration.project.player.Player;
import com.integration.project.team.Team;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MyXML {
    public static void exportData(GameService gameService){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("games");
            doc.appendChild(rootElement);

            List<Game> games = gameService.getGames();
            for (Game game_data : games){
                Element game = doc.createElement("game");
                game.setAttribute("league", game_data.getLeague());
                game.setAttribute("year", String.valueOf(game_data.getYear()));
                game.setAttribute("date", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(game_data.getDate()));
                game.setAttribute("gamelength", String.valueOf(game_data.getGamelength()));
                rootElement.appendChild(game);

                Element teams = doc.createElement("teams");
                game.appendChild(teams);

                for(int i = 1; i<=game_data.getTeams().size(); i++){
                    Team team_data = game_data.getTeams().get(i - 1);
                    Element team = doc.createElement("team");

                    team.setAttribute("number", String.valueOf(i));
                    team.setAttribute("name",team_data.getName());
                    team.setAttribute("result", String.valueOf(team_data.getResult()));
                    team.setAttribute("kills",String.valueOf(team_data.getKills()));
                    team.setAttribute("deaths",String.valueOf(team_data.getDeaths()));
                    team.setAttribute("assists",String.valueOf(team_data.getAssists()));
                    team.setAttribute("damagetochampions",String.valueOf(team_data.getDamageToChampions()));
                    team.setAttribute("visionscore",String.valueOf(team_data.getVisionScore()));
                    team.setAttribute("totalgold",String.valueOf(team_data.getTotalGold()));
                    teams.appendChild(team);

                    Element bans = doc.createElement("bans");
                    team.appendChild(bans);

                    Element ban = doc.createElement("ban");
                    ban.setTextContent(team_data.getBan1());
                    bans.appendChild(ban);
                    ban = doc.createElement("ban");
                    ban.setTextContent(team_data.getBan2());
                    bans.appendChild(ban);
                    ban = doc.createElement("ban");
                    ban.setTextContent(team_data.getBan3());
                    bans.appendChild(ban);
                    ban = doc.createElement("ban");
                    ban.setTextContent(team_data.getBan4());
                    bans.appendChild(ban);
                    ban = doc.createElement("ban");
                    ban.setTextContent(team_data.getBan5());
                    bans.appendChild(ban);

                    Element players = doc.createElement("players");
                    team.appendChild(players);
                    for(int j = 1; j <= team_data.getPlayers().size(); j++){
                        Player player_data = team_data.getPlayers().get(j-1);

                        Element player = doc.createElement("player");
                        player.setAttribute("number", String.valueOf(j));
                        player.setAttribute("position", player_data.getPosition());
                        player.setAttribute("player", player_data.getName());
                        player.setAttribute("champions", player_data.getChampion());

                        player.setAttribute("kills",String.valueOf(player_data.getKills()));
                        player.setAttribute("deaths",String.valueOf(player_data.getDeaths()));
                        player.setAttribute("assists",String.valueOf(player_data.getAssists()));
                        player.setAttribute("damagetochampions",String.valueOf(player_data.getDamageToChampions()));
                        players.appendChild(player);
                    }
                }
            }

            try (FileOutputStream output = new FileOutputStream("data/lol_esport_data-export.xml")) {
                writeXml(doc, output);
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }


    }

    private static void writeXml(Document doc, OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

    public static void importData(GameService gameService) {
        try{
            File inputFile = new File("data/lol_esport_data-export.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            gameService.deleteAll();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            NodeList games = doc.getElementsByTagName("game");

            for (int i=0; i<games.getLength(); i++){
                Node node = games.item(i);
                Element element = (Element) node;


                Game adding = new Game(
                        element.getAttribute("league"),
                        Integer.parseInt(element.getAttribute("year")),
                        LocalDateTime.parse((CharSequence)element.getAttribute("date"), formatter),
                        Integer.parseInt(element.getAttribute("gamelength"))
                );
                addTeam(adding, element);
                gameService.save(adding);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void addTeam(Game game, Element element){
        for(int i = 0; i< element.getElementsByTagName("team").getLength(); i++){
            Element team = (Element) element.getElementsByTagName("team").item(i);
            Team adding = new Team(
                    team.getAttribute("name"),
                    team.getElementsByTagName("ban").item(0).getTextContent(),
                    team.getElementsByTagName("ban").item(1).getTextContent(),
                    team.getElementsByTagName("ban").item(2).getTextContent(),
                    team.getElementsByTagName("ban").item(3).getTextContent(),
                    team.getElementsByTagName("ban").item(4).getTextContent(),
                    Integer.parseInt(team.getAttribute("result")),
                    Integer.parseInt(team.getAttribute("kills")),
                    Integer.parseInt(team.getAttribute("deaths")),
                    Integer.parseInt(team.getAttribute("assists")),
                    Integer.parseInt(team.getAttribute("damagetochampions")),
                    Integer.parseInt(team.getAttribute("visionscore")),
                    Integer.parseInt(team.getAttribute("totalgold"))
            );
            addPlayers(adding, team);
            game.addTeam(adding);
        }
    }

    private static void addPlayers(Team team, Element element){
        for(int i = 0; i< element.getElementsByTagName("player").getLength(); i++) {
            Element player = (Element) element.getElementsByTagName("player").item(i);
            Player adding = new Player(
                    player.getAttribute("position"),
                    player.getAttribute("player"),
                    player.getAttribute("champions"),
                    Integer.parseInt(player.getAttribute("kills")),
                    Integer.parseInt(player.getAttribute("deaths")),
                    Integer.parseInt(player.getAttribute("assists")),
                    Integer.parseInt(player.getAttribute("damagetochampions"))
            );
            team.addPlayer(adding);
        }
    }
}
