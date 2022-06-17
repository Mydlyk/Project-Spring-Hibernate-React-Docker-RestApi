//package com.integration.project.game;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Configuration
//public class GameConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(GameRepository gameRepository){
//        return args -> {
//            Game game1 = new Game("LEC", 2022, LocalDateTime.now(), 1234);
//            Game game2 = new Game("LEC", 2022, LocalDateTime.now(), 1234);
//            Game game3 = new Game("LEC", 2022, LocalDateTime.now(), 1234);
//
//            gameRepository.saveAll(List.of(game1, game2, game3));
//        };
//    }
//}
