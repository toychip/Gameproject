package com.gameproject.flash.repository;

import com.gameproject.flash.domian.QPost;
import com.gameproject.flash.request.PostSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.gameproject.flash.domian.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearchRequest postSearchRequest) {
        int size = (postSearchRequest.getSize() == null) ? 10 : postSearchRequest.getSize();
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(size)

                .offset(postSearchRequest.getOffset())
                .orderBy(QPost.post.id.desc())
                .fetch();
    }

    @Override
    public List<Post> search(PostSearchRequest postSearchRequest) {
        int size = (postSearchRequest.getSize() == null) ? 10 : postSearchRequest.getSize();

        BooleanBuilder builder = new BooleanBuilder();

        if (postSearchRequest.getTitle() != null) {
            builder.and(QPost.post.title.containsIgnoreCase(postSearchRequest.getTitle()));
        }

        if (postSearchRequest.getContent() != null) {
            builder.and(QPost.post.content.contains(postSearchRequest.getContent()));
        }

        if (postSearchRequest.getWrittenBy() != null) {
            builder.and(QPost.post.writtenBy.containsIgnoreCase(postSearchRequest.getWrittenBy()));
        }

        return jpaQueryFactory.selectFrom(QPost.post)
                .where(builder)
                .limit(size)
                .offset(postSearchRequest.getOffset())
                .orderBy(QPost.post.id.desc())
                .fetch();
    }
}
