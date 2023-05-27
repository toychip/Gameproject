package com.gameproject.flash.response;

import com.gameproject.flash.domian.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;

    private final String writtenBy;

    private final String writtenDateTime; // LocalDateTime에서 String으로 변경

    public PostResponse(Post post) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writtenBy = post.getWrittenBy();
        this.writtenDateTime = post.getWrittenDateTime().format(formatter); // LocalDateTime을 String으로 변환
    }

    @Builder
    public PostResponse(Long id, String title, String content, String writtenBy, LocalDateTime writtenDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
        this.writtenBy = writtenBy;
        this.writtenDateTime = writtenDateTime.format(formatter); // LocalDateTime을 String으로 변환
    }
}
