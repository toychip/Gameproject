package com.gameproject.flash.repository;

import com.gameproject.flash.domian.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.gameproject.flash.domian.Post;
import com.gameproject.flash.request.PostSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(postSearch.getSize())
//                .offset((long)(page - 1) * 10)
                .offset(postSearch.getOffset())
                .orderBy(QPost.post.id.desc())
                .fetch();
    }
}