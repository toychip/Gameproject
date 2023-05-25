package com.gameproject.flash.response;

import com.gameproject.flash.domian.Game;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameResponse {

    private Integer id;
    private String name;
    private String imageUrl;
    private String swfUrl;

    public GameResponse(Game game){
        this.id = game.getId();
        this.name = game.getName();
        this.imageUrl = game.getImageUrl();
        this.swfUrl = game.getSwfUrl();
    }

    @Builder
    public GameResponse(String name, String imageUrl, String swfUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.swfUrl = swfUrl;
    }
}
