package com.gameproject.flash.repository;

import com.gameproject.flash.domian.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> , GameRepositoryCustom{
}
