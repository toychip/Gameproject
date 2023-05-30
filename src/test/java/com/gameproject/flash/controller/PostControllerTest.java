//package com.gameproject.flash.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.gameproject.flash.request.PostCreateRequest;
//import com.gameproject.flash.domian.Post;
//import com.gameproject.flash.repository.PostRepository;
//import com.gameproject.flash.request.PostEditRequest;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.http.MediaType.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@WithMockUser(roles = "USER")
//class PostControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void clean(){
//        postRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("글 작성 요청시 '/posts 요청시' title 값은 필수다.")
//    void test2() throws Exception{
//        // given
//        PostCreateRequest request = PostCreateRequest.builder()
////                .title("제목입니다.")
//                .content("내용입니다.")
//                .build();
//        String json = objectMapper.writeValueAsString(request);
//
//        // expected
//        mockMvc.perform(post("/posts")
//                                .contentType(APPLICATION_JSON)
////                {"title:""}
////                {"title:null}도 검증을 해줄지? ? - 해줌
//                        .content(json)
//                )
//                .andExpect(status().isBadRequest())
////                .andExpect(MockMvcResultMatchers.content().string("Hello World"))
//                .andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST.toString()))
//                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
//                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력하세요."))
////                                              json의 타이틀이 오른쪽 value 값으로 내려오느냐?
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("글 작성 요청시 '/posts' 요청시 DB에 값이 저장된다.")
//    void test3() throws Exception{
//        //given
//        PostCreateRequest requestPrev = new PostCreateRequest("제목입니다.", "내용입니다.");
//        PostCreateRequest request = PostCreateRequest.builder()
//                .title("제목입니다.")
//                .content("내용입니다.")
//                .build();
//
//
////        ObjectMapper objectMapper = new ObjectMapper();  Spring에서 bean에 기본적으로 적용시켜줌
//        String json = objectMapper.writeValueAsString(request);
//        System.out.println(json);
//        // when
//        mockMvc.perform(post("/posts")
//                                .contentType(APPLICATION_JSON)
////                                .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\"}")
//                                .content(json)
//                )
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        //then
//        assertEquals(1L, postRepository.count());
//        Post post = postRepository.findAll().get(0);
//        assertEquals("제목입니다.", post.getTitle());
//        assertEquals("내용입니다.", post.getContent());
//    }
//
//    @Test
//    @DisplayName("글 1개 조회")
//    void test4() throws Exception {
//        //given
//
//        // 클라이언트 요구 사항
//        // json응답에서 title값 길이를 최대 10글자로 해주세요.
//
//        // Post Entity와 PostResponse class 같은 형식이기 때문에 그대로 썼음
//
//        Post post = Post.builder()
//                .title("123456789012345")
//                .content("bar")
//                .build();
//        postRepository.save(post);
//        //expected(when + then)
//        mockMvc.perform(get("/posts/{postId}", post.getId())
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(post.getId()))
//                .andExpect(jsonPath("$.title").value("1234567890"))
//                .andExpect(jsonPath("$.content").value("bar"))
//                .andDo(print());
//        //then
//    }
//
//    @Test
//    @DisplayName("글 여러개 조회")
//    void test5() throws Exception {
//        // given
//        List<Post> requestPosts = IntStream.range(0, 20)
//                .mapToObj(i -> Post.builder()
//                        .title("test title " + i + " 번째")
//                        .content("test content " + i + " 번째")
//                        .build())
//                .collect(Collectors.toList());
//
//        postRepository.saveAll(requestPosts);
//
//        // expected
//        mockMvc.perform(get("/posts?page=1&size=10")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()", is(10)))
//                .andExpect(jsonPath("$[0].title").value("test title 19 번째"))
//                .andExpect(jsonPath("$[0].content").value("test content 19 번째"))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("글 페이징처리")
//    void test6() throws Exception{
//        //given
//        List<Post> resultPosts = IntStream.range(0, 20)
//                .mapToObj(i -> {
//                    return Post.builder()
//                            .title("test title " + i + " 번째")
//                            .content("test content " + i + " 번째")
//                            .build();
//                })
//                .collect(Collectors.toList());
//        postRepository.saveAll(resultPosts);
//
//        mockMvc.perform(get("/posts?page=1&size=10")
////        mockMvc.perform(get("/posts?page=1&sort=title,desc")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()", is(10)))
//                .andExpect(jsonPath("$[0].title").value("test title 19 번째"))
//                .andExpect(jsonPath("$[0].content").value("test content 19 번째"))
//                .andDo(print());
//        //then
//    }
//
//    @Test
//    @DisplayName("페이지를 0으로 요청시 첫 페이지 가져오기")
//    void test7() throws Exception{
//        //given
//        List<Post> resultPosts = IntStream.range(0, 20)
//                .mapToObj(i -> Post.builder()
//                            .title("test title " + i + " 번째")
//                            .content("test content " + i + " 번째")
//                            .build())
//                                    .collect(Collectors.toList());
//
//        postRepository.saveAll(resultPosts);
//
//        //expected
//        mockMvc.perform(get("/posts?page=0&size=10")
////        mockMvc.perform(get("/posts?page=1&sort=title,desc")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()", is(10)))
//                .andExpect(jsonPath("$[0].title").value("test title 19 번째"))
//                .andExpect(jsonPath("$[0].content").value("test content 19 번째"))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("글 제목 수정")
//    void test8() throws Exception{
//        //given
//        Post post = Post.builder()
//                .title("임준형")
//                .content("이것은 내용이지롱")
//                .build();
//
//        postRepository.save(post);
//        PostEditRequest postEdit = PostEditRequest.builder()
//                .title("임주희")
//                .content("이것은 내용이지롱")
//                .build();
//
//        //expected
//        mockMvc.perform(patch("/posts/{postId}", post.getId())      // PATCH /posts/{postId}
//                        .contentType(APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(postEdit)))
//                .andExpect(status().isOk())
//                .andDo(print());
//        Assertions.assertEquals("임주희", postEdit.getTitle());
//        Assertions.assertEquals("이것은 내용이지롱", postEdit.getContent());
//    }
//    @Test
//    @DisplayName("글 내용 수정")
//    void test8_2() throws Exception{
//        //given
//        Post post = Post.builder()
//                .title("임준형")
//                .content("이것은 내용이지롱")
//                .build();
//
//        postRepository.save(post);
//        PostEditRequest postEdit = PostEditRequest.builder()
//                .title("임준형")
//                .content("저것은 내용이지롱")
//                .build();
//
//        //expected
//        mockMvc.perform(patch("/posts/{postId}", post.getId())      // PATCH /posts/{postId}
//                        .contentType(APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(postEdit)))
//                .andExpect(status().isOk())
//                .andDo(print());
//        Assertions.assertEquals("임준형", postEdit.getTitle());
//        Assertions.assertEquals("저것은 내용이지롱", postEdit.getContent());
//    }
//
//    @Test
//    @DisplayName("게시글 삭제")
//    void test9() throws Exception{
//
//        //given
//        Post post = Post.builder()
//                .title("임준형")
//                .content("이것은 내용이지롱")
//                .build();
//
//        postRepository.save(post);
//
//        //expected
//
//        mockMvc.perform(delete("/posts/{postId}", post.getId())
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 게시글 조회")
//    void test10() throws Exception{
//        mockMvc.perform(delete("/posts/{postId}", 1L)
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 게시글 수정")
//    void test11() throws Exception{
//
//        PostEditRequest postEdit = PostEditRequest.builder()
//                .title("임준형")
//                .content("저것은 내용이지롱")
//                .build();
//
//        mockMvc.perform(patch("/posts/{postId}", 1L)
//                        .contentType(APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(postEdit)))
//                .andExpect(status().isNotFound())
//                .andDo(print());
//
//
//    }
//
//    @Test
//    @DisplayName("게시글 작성시 제목에 '바보'는 포함될 수 없다.")
//    void test12() throws Exception{
//        //given
//        PostCreateRequest request = PostCreateRequest.builder()
//                .title("나는 바보입니다.")
//                .content("내용입니다.")
//                .build();
//
//
////        ObjectMapper objectMapper = new ObjectMapper();  Spring에서 bean에 기본적으로 적용시켜줌
//        String json = objectMapper.writeValueAsString(request);
//        System.out.println(json);
//        // when
//        mockMvc.perform(post("/posts")
//                        .contentType(APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//
//    }
//
//
//}
//
//// API 문서 생성
//// 클라이언트 입장 어떤 API 있는지 모름
//
//// Spring RestDocs
//// 장점: 운영코드에 영향이 없음.
//// - 코드 수정 -> 문서를 수정 x -> 코드(기능)이랑 문서랑 달라짐.
//// Test케이스 실행 -> 문서를 자동으로 생성해줌(restDocs)