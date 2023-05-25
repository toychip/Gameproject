package com.gameproject.flash.domian;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @Email
    private String email;
    private String password;
    private LocalDateTime createdAt;


    // Spring Security 적용할것이므로 주석처리
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
//    private List<Session> sessions = new ArrayList<>();


//    public Session addSession(){
//        Session session = Session.builder()
//                .member(this)
//                .build();
//        sessions.add(session);
//        return session;
//    }

    @Builder
    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }
}
