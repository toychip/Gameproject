//package com.gameproject.flash.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gameproject.flash.request.PostCreate;
//import com.gameproject.flash.domian.Post;
//import com.gameproject.flash.repository.PostRepository;
//import com.gameproject.flash.request.Signup;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.snippet.Attributes;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@AutoConfigureRestDocs(uriScheme = "https", uriHost = "ttt.apimanual.com", uriPort = 443)
//@ExtendWith(RestDocumentationExtension.class)
//@WithMockUser(roles = "USER")
//public class PostControllerDocTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    @DisplayName("글 단건 조회 - docsVer")
//    void test1() throws Exception {
//        // given
//        Post post = Post.builder()
//                .title("제목")
//                .content("내용")
//                .build();
//        postRepository.save(post);
//
//
//        // expected
//        mockMvc.perform(get("/posts/{postId}", post.getId())
//                        .accept(APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("post-inquiry",
//                        pathParameters(
//                                parameterWithName("postId").description("게시글 ID")
//                        ),
//                        responseFields(
//                                fieldWithPath("id").description("게시글 ID"),
//                                fieldWithPath("title").description("제목"),
//                                fieldWithPath("content").description("내용")
//                        )
//                ));
//    }
//
//    @Test
//    @DisplayName("글 등록 - docsVer")
//    void test2() throws Exception{
//
//        PostCreate request = PostCreate.builder()
//                .title("ttt 제목")
//                .content("내용입니다.")
//                .build();
//
//        String json = objectMapper.writeValueAsString(request);
//
//        mockMvc.perform(RestDocumentationRequestBuilders.post("/posts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(json)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("post-create",
//                        requestFields(
//                                fieldWithPath("title").description("제목")
//                                        .attributes(Attributes.key("constraint").value("올바른 제목을 입력해주세요.")),
//                                fieldWithPath("content").description("내용")
//                        )
//                ));
//    }
//
//    @Test
//    @DisplayName("회원 가입 - docsVer")
//    void test3() throws Exception{
//
//        Signup signup = Signup.builder()
//                .email("toytoy1234@naver.com")
//                .password("1234")
//                .name("임준형")
//                .build();
//
//        String json = objectMapper.writeValueAsString(signup);
//
//        mockMvc.perform(RestDocumentationRequestBuilders.post("/auth/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(json)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("member-join",
//                        requestFields(
//                                fieldWithPath("email").description("toytoy1234@naver.com")
//                                        .attributes(Attributes.key("constraint").value("아이디는 필수값입니다.")),
//                                fieldWithPath("password").description("1234")
//                                        .attributes(Attributes.key("constraint").value("비밀번호는 필수값입니다.")),
//                                fieldWithPath("name").description("임준형")
//                                        .attributes(Attributes.key("constraint").value("회원 이름은 필수값입니다."))
//                        )
//                ));
//    }
//
//}
