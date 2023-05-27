package com.gameproject.flash.controller;


import com.gameproject.flash.service.AuthService;
import com.gameproject.flash.request.Signup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public void signup(@RequestBody Signup signup){
        authService.signup(signup);
    }



}
