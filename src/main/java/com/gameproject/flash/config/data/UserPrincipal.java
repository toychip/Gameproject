package com.gameproject.flash.config.data;

import com.gameproject.flash.domian.Member;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserPrincipal extends User {

    private final Long userId;

    public UserPrincipal(Member user){
        super(user.getEmail(), user.getPassword(),
                List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN")));
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }
}
