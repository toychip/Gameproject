package com.gameproject.flash.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Signup {

    private String email;
    private String name;
    private String password;

    public Signup() {
    }

    public Signup(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
