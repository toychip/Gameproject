//package com.gameproject.flash.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gameproject.flash.repository.MemberRepository;
//import com.gameproject.flash.request.Signup;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class AuthControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
////    @Autowired
////    private SessionRepository sessionRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void clean(){
//        memberRepository.deleteAll();
//    }
//
//    /* Spring 시큐리티 적용할것이므로 주석처리
//    @Test
//    @DisplayName("로그인 성공")
//    void test() throws Exception{
//        // given
//
//        Member member = memberRepository.save(Member.builder()
//                .name("임준형")
//                .email("dlawnsgud427@naber.com")
//                .password("1234")
//                .build());
//
//        Login loin = Login.builder()
//                .email("dlawnsgud427@naber.com")
//                .password("1234")
//                .build();
//
//        String json = objectMapper.writeValueAsString(loin);
//
//        // expected
//        mockMvc.perform(post("/auth/login")
//                                .contentType(APPLICATION_JSON)
//                                .content(json)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        Member loggedInUser = memberRepository.findById(member.getId())
//                .orElseThrow(RuntimeException::new);
//
//    }
//
//
//    @Test
//    @DisplayName("로그인 성공 후 세션 1개 생성")
//    @Transactional
//    void test2() throws Exception{
//        // given
//
//        Member member = memberRepository.save(Member.builder()
//                .name("임준형")
//                .email("dlawnsgud427@naber.com")
//                .password("1234")
//                .build());
//
//        Login loin = Login.builder()
//                .email("dlawnsgud427@naber.com")
//                .password("1234")
//                .build();
//
//        String json = objectMapper.writeValueAsString(loin);
//
//        // expected
//        mockMvc.perform(post("/auth/login")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        Member loggedInUser = memberRepository.findById(member.getId())
//                .orElseThrow(RuntimeException::new);
//
//        Assertions.assertEquals(1L, loggedInUser.getSessions().size());
//
//    }
//
//    @Test
//    @DisplayName("로그인 성공 후 세션 응답")
//    @Transactional
//    void test3() throws Exception{
//        // given
//
//        Member member = memberRepository.save(Member.builder()
//                .name("임준형")
//                .email("dlawnsgud427@naber.com")
//                .password("1234")
//                .build());
//
//        Login loin = Login.builder()
//                .email("dlawnsgud427@naber.com")
//                .password("1234")
//                .build();
//
//        String json = objectMapper.writeValueAsString(loin);
//
//        // expected
//        mockMvc.perform(post("/auth/login")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.accessToken", Matchers.notNullValue()))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("로그인 후 권한이 필요한 페이지 접속한다 /session")
//    void test4() throws Exception {
//        // given
//
//        Member member = Member.builder()
//                .name("임준형")
//                .email("dlawnsgud427@naber.com")
//                .password("1234")
//                .build();
//        Session session = member.addSession();
//        memberRepository.save(member);
//
//        // expected
//        mockMvc.perform(get("/session")
//                        .header("Authorization", session.getAccessToken())
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("로그인 후 검증되지 않은 세션값으로 권한이 필요한 페이지에 접속할 수 없다.")
//    void test5() throws Exception {
//        // given
//
//        Member member = Member.builder()
//                .name("임준형")
//                .email("dlawnsgud427@naber.com")
//                .password("1234")
//                .build();
//        Session session = member.addSession();
//        memberRepository.save(member);
//
//        // expected
//        mockMvc.perform(get("/session")
//                        .header("Authorization", session.getAccessToken())
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//     */
//
//    @Test
//    @DisplayName("회원가입")
//    void test6() throws Exception {
//        // given
//        Signup signup = Signup.builder()
//                .email("toytoy@naver.com")
//                .password("1234")
//                .name("임준형")
//                .build();
//
//        // expected
//        mockMvc.perform(post("/auth/signup")
//                        .content(objectMapper.writeValueAsString(signup))
//                        .contentType(APPLICATION_JSON))
//                .andDo(print());
//    }
//}