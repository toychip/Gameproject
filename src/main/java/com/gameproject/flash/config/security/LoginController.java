package com.gameproject.flash.config.security;

import com.gameproject.flash.response.AuthResponse;
import com.gameproject.flash.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class LoginController {

    private final PostService postService;

    @GetMapping("/mypage")
    public AuthResponse getMemberInfo() {
        return postService.getCurrentMemberInfo();
    }
}
