//package com.gameproject.flash.service;
//
//import com.gameproject.flash.domian.Member;
//import com.gameproject.flash.exception.AlreadyExistsEmailException;
//import com.gameproject.flash.repository.MemberRepository;
//import com.gameproject.flash.request.Signup;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public void signup(Signup signup) {
//        Optional<Member> memberOptional = memberRepository.findByEmail(signup.getEmail());
//        if (memberOptional.isPresent()) {
//            throw new AlreadyExistsEmailException();
//        }
//
//        String encryptedPassword = passwordEncoder.encode(signup.getPassword());
//
//        var member = Member.builder()
//                .name(signup.getName())
//                .password(encryptedPassword)
//                .email(signup.getEmail())
//                .build();
//        memberRepository.save(member);
//    }
//}
