package com.gameproject.flash.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {

    private String email;
    private String name;
    private String password;

    public SignupRequest() {
    }

    public SignupRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
