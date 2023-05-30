package com.gameproject.flash.controller;

import com.gameproject.flash.response.AuthResponse;
import com.gameproject.flash.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class MyPageController {

    private final PostService postService;

    @GetMapping("/mypage")
    public AuthResponse getMemberInfo() {
        return postService.getCurrentMemberInfo();
    }
}
