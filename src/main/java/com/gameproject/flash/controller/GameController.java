package com.gameproject.flash.controller;

import com.gameproject.flash.domian.Game;
import com.gameproject.flash.response.GameResponse;
import com.gameproject.flash.response.PostResponse;
import com.gameproject.flash.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.name;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/")
    public List<Game> getAllGames(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return gameService.searchGames(name);
        } else {
            return gameService.getAllGames();
        }
    }

    @GetMapping("/game/{id}")
    public GameResponse get(@PathVariable Integer id){
        return gameService.get(id);
    }
}
