package com.gameproject.flash.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostSearchRequest {

    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 10;

    private String title; // 검색할 제목
    private String content; // 검색할 내용
    private String writtenBy; // 작성자를 검색할 키워드

    @Builder
    public PostSearchRequest(Integer page, Integer size, String title, String content, String writtenBy) {
        this.page = (page == null) ? 1 : page;
        this.size = (size == null) ? 10 : size;
        this.title = title;
        this.content = content;
        this.writtenBy = writtenBy;
    }

    public long getOffset(){
        return (long) (Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }
}
