package com.gameproject.flash.repository;

import com.gameproject.flash.domian.Game;
import com.gameproject.flash.domian.QGame;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Game> searchByName(String name) {
        BooleanBuilder builder = new BooleanBuilder();

        if (name != null && !name.isEmpty()) {
            builder.and(QGame.game.name.containsIgnoreCase(name));
        }

        return jpaQueryFactory.selectFrom(QGame.game)
                .where(builder)
                .fetch();
    }
}
