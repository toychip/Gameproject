package com.gameproject.flash.repository;

import com.gameproject.flash.domian.QPost;
import com.querydsl.core.BooleanBuilder;
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
        int size = (postSearch.getSize() == null) ? 10 : postSearch.getSize();
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(size)

                .offset(postSearch.getOffset())
                .orderBy(QPost.post.id.desc())
                .fetch();
    }

    @Override
    public List<Post> search(PostSearch postSearch) {
        int size = (postSearch.getSize() == null) ? 10 : postSearch.getSize();

        BooleanBuilder builder = new BooleanBuilder();

        if (postSearch.getTitle() != null) {
            builder.and(QPost.post.title.containsIgnoreCase(postSearch.getTitle()));
        }

        if (postSearch.getContent() != null) {
            builder.and(QPost.post.content.containsIgnoreCase(postSearch.getContent()));
        }

        if (postSearch.getWrittenBy() != null) {
            builder.and(QPost.post.writtenBy.containsIgnoreCase(postSearch.getWrittenBy()));
        }

        return jpaQueryFactory.selectFrom(QPost.post)
                .where(builder)
                .limit(size)
                .offset(postSearch.getOffset())
                .orderBy(QPost.post.id.desc())
                .fetch();
    }
}
