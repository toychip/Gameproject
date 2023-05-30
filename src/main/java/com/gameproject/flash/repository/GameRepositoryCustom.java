package com.gameproject.flash.repository;

import com.gameproject.flash.domian.Game;

import java.util.List;

public interface GameRepositoryCustom {
    List<Game> searchByName(String name);
}
