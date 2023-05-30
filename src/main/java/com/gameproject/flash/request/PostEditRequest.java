package com.gameproject.flash.request;

import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Getter
@ToString
@Setter
@NoArgsConstructor
public class PostEditRequest {

    @NotBlank(message = "타이틀을 입력하세요.")
    private String title;

    @NotBlank(message = "콘텐츠를 입력해주세요.")
    private String content;

    @Builder
    public PostEditRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
