package com.integration.project.game;

import com.integration.project.json.MyJSON;
import com.integration.project.xml.MyXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //Zwraca wszyskie gry - w przyszłosci ograniczyc projectem
    @GetMapping
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Game> getAll(){
        return gameService.getGames();
    }


    //Zwraca nulla jesli nie ma i opbiekt jesli jest
    @GetMapping("/by")
    public Optional<Game> getById(@RequestParam(name = "id", required = false) Integer id){
        return id != null?gameService.getGameById(id):Optional.empty();
    }

    //to moje do testów
    @GetMapping("test")
    public String testowanie(){
        return "testowanie";
    }

    //tutaj jest importowanie jsona do bazy - uwaga trochę trwa
    @Transactional
    @GetMapping("/json/import")
    public String jsonImport(){
        MyJSON.importData(gameService);
        return "Zaimportowano";
    }

    //Tutaj eksportowanie do pliku json
    @GetMapping("/json/export")
    public String jsonExport(){
        MyJSON.exportData(gameService);
        return "Wyeksportowano";
    }


    /////////////////////////////////////////////////////////////
    // XMLOWE COSIE

    //Eksport danych z bazy do pliku xml
    @GetMapping("/xml/export")
    public String xmlExport(){
        MyXML.exportData(gameService);
        return "Wyeksportowano";
    }

    //Import z pliku xml do bazy
    @Transactional
    @GetMapping("/xml/import")
    public String xmlImport() {
        MyXML.importData(gameService);
        return "Zaimportowano";
    }
}
