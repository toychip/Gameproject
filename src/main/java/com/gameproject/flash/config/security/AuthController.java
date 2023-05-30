package com.gameproject.flash.config.security;


import com.gameproject.flash.config.security.AuthenticationRequest;
import com.gameproject.flash.config.security.AuthenticationResponse;
import com.gameproject.flash.config.security.AuthenticationService;
import com.gameproject.flash.config.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

//    private final AuthService authService;
    private final AuthenticationService service;


    @PostMapping("/auth/signup")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }


}
