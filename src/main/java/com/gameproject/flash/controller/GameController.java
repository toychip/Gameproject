package com.gameproject.flash.controller;

import com.gameproject.flash.domian.Game;
import com.gameproject.flash.response.GameResponse;
import com.gameproject.flash.response.PostResponse;
import com.gameproject.flash.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/game/{id}")
    public GameResponse get(@PathVariable Integer id){
        return gameService.get(id);
    }
}
