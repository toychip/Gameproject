package com.gameproject.flash.service;

import com.gameproject.flash.domian.Member;
import com.gameproject.flash.exception.AlreadyExistsEmailException;
import com.gameproject.flash.repository.MemberRepository;
import com.gameproject.flash.request.Signup;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class AuthServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthService authService;

    @BeforeEach
    void clean(){
        memberRepository.deleteAll();
    }

//    @Test
//    @DisplayName("회원가입 성공")
//    void test1(){
//        //given
//        PasswordEncoder encoder = new PasswordEncoder();
//        Signup signup = Signup.builder()
//                .email("manager@naver.com")
//                .password("1234@@")
//                .name("임준형")
//                .build();
//        //when
//        authService.signup(signup);
//        //then
//        assertEquals(1, memberRepository.count());
//
//        Member member = memberRepository.findAll().iterator().next();
//
//
//        assertEquals("manager@naver.com", member.getEmail());
////        assertNotNull(member.getPassword());
////        assertEquals("1234", member.getPassword());
//        assertTrue(encoder.matches("1234@@", member.getPassword()));
//        System.out.println(member.getPassword());
//        assertEquals("임준형", member.getName());
//    }

    @Test
    @DisplayName("회원가입시 중복된 이메일")
    void test2(){
        //given
        Member prevMember = Member.builder()
                .email("toytoy@naver.com")
                .password("1234")
                .name("누구")
                .build();
        memberRepository.save(prevMember);

        Signup signup = Signup.builder()
                .email("toytoy@naver.com")
                .password("1234")
                .name("임준형")
                .build();
        //expected
        assertThrows(AlreadyExistsEmailException.class, () -> authService.signup(signup));
        //then

    }

    /*
    @Test
    @DisplayName("암호화 후 로그인 성공")
    void test3(){
        //given
        PasswordEncoder encoder = new PasswordEncoder();
        String encrpytPassword = encoder.encrypt("1234");

        Member member = Member.builder()
                .email("toytoy@naver.com")
                .password(encrpytPassword)
                .name("임준형")
                .build();
        memberRepository.save(member);


        Login login = Login.builder()
                .email("toytoy@naver.com")
                .password("1234")
                .build();

        //when
        Long memberId = authService.signin(login);

        //then
        assertNotNull(memberId);
    }

    @Test
    @DisplayName("암호화 후 로그인시도 비밀번호 틀림")
    void test4(){
        //given
        PasswordEncoder encoder = new PasswordEncoder();
        String encrpytPassword = encoder.encrypt("1234");

        Member member = Member.builder()
                .email("toytoy@naver.com")
                .password(encrpytPassword)
                .name("임준형")
                .build();
        memberRepository.save(member);


        Login login = Login.builder()
                .email("toytoy@naver.com")
                .password("5678")
                .build();

        //when
        assertThrows(InvalidSigninInformation.class,
                () -> authService.signin(login));

        //then
    }

     */
}