//package com.gameproject.flash.service;
//
//import com.gameproject.flash.request.PostCreate;
//import com.gameproject.flash.domian.Post;
//import com.gameproject.flash.exception.PostNotFound;
//import com.gameproject.flash.repository.PostRepository;
//import com.gameproject.flash.request.PostEdit;
//import com.gameproject.flash.request.PostSearch;
//import com.gameproject.flash.response.PostResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class PostServiceTest {
//
//    @Autowired
//    private PostRepository postRepository;
//    @Autowired
//    private PostService postService;
//
//    @BeforeEach
//    void clean(){
//        postRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("글 작성")
//    void test1() {
//        //given
//        PostCreate postCreate = PostCreate.builder()
//                .title("제목입니다.")
//                .content("내용입니다.")
//                .build();
//        //when
//        postService.write(postCreate);
//        //then
//        assertEquals(1L, postRepository.count());
//        Post post = postRepository.findAll().get(0);
//        assertEquals("제목입니다.", post.getTitle());
//        assertEquals("내용입니다.", post.getContent());
//    }
//
//    @Test
//    @DisplayName("글 1개 조회")
//    void test2(){
//        //given
//        Post requestPost = Post.builder()
//                .title("foo")
//                .content("bar")
//                .build();
//        postRepository.save(requestPost);
//
//        //when
//        PostResponse response = postService.get(requestPost.getId());
//
//        //then
//        assertNotNull(response);
//        assertEquals(1L, postRepository.count());
//        assertEquals("foo", response.getTitle());
//        assertEquals("bar", response.getContent());
//    }
//
//
//
//    @Test
//    @DisplayName("글 여러개 조회")
//    void test3(){
//        //given
//        List<Post> resultPosts = IntStream.range(0, 20)
//                .mapToObj(i -> {
//                    return Post.builder()
//                            .title("test title " + i + " 번째")
//                            .content("test content " + i + " 번째")
//                            .build();
//                })
//                        .collect(Collectors.toList());
//        postRepository.saveAll(resultPosts);
//
//        // Pageable pageableRequest = PageRequest.of(0, 10);//, Sort.Direction.DESC, "id");
//        PostSearch postSearch = PostSearch.builder()
//                .page(1)
////                .size(10) - 한페이지당 여러개 보기
//                .build();
//
//        //when
//        List<PostResponse> posts = postService.getList(postSearch);
//        //then
//        assertEquals(10,posts.size());  // 한 페이지당 사이즈
//        assertEquals("test title 19 번째",posts.get(0).getTitle());
//    }
//
//    @Test
//    @DisplayName("글 제목 수정")
//    void test4(){
//        //give
//        Post post = Post.builder()
//                            .title("임준형")
//                            .content("이것은 내용이지롱")
//                            .build();
//
//    postRepository.save(post);
//
//        PostEdit postEdit = PostEdit.builder()
//                .title(null)   // 이름을 바꿨으므로 내용은 안 보내서 null로 들어온 경우
//                .content("수정된 내용이지롱")
//                .build();
//
//        //when
//        postService.edit(post.getId(), postEdit);
//
//        //then
//        Post changedPost = postRepository.findById(post.getId())
//                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));
//        assertEquals("임준형", changedPost.getTitle());
//        assertEquals("수정된 내용이지롱", changedPost.getContent());
//    }
//
//    @Test
//    @DisplayName("글 내용 수정")
//    void test5() {
//        //give
//        Post post = Post.builder()
//                .title("임준형")
//                .content("이것은 내용이지롱")
//                .build();
//
//        postRepository.save(post);
//
//        PostEdit postEdit = PostEdit.builder()
//                .title("임준형")
//                .content("저것은 내용이지롱")
//                .build();
//
//        //when
//        postService.edit(post.getId(), postEdit);
//
//        //then
//        Post changedPost = postRepository.findById(post.getId())
//                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));
//                assertEquals("저것은 내용이지롱", changedPost.getContent());
//    }
//
//    @Test
//    @DisplayName("게시글 삭제")
//    void test6(){
//        //give
//        Post post = Post.builder()
//                .title("임준형")
//                .content("이것은 내용이지롱")
//                .build();
//
//        postRepository.save(post);
//
//        //when
//        postService.delete(post.getId());
//
//        assertEquals(0, postRepository.count());
//    }
//
//    @Test
//    @DisplayName("게시글 삭제 - 존재하지 않는 글 오류발생해야함")
//    void test7(){
//        //give
//        Post post = Post.builder()
//                .title("임준형")
//                .content("이것은 내용이지롱")
//                .build();
//
//        postRepository.save(post);
//
//        //expected
//
//        assertThrows(PostNotFound.class, () -> {
//            postService.delete(post.getId() + 1L);
//        });
//    }
//
//    @Test
//    @DisplayName("글 1개 조회 - 존재하지 않는 글 오류발생해야함")
//    void test8(){
//        //given
//        Post post = Post.builder()
//                .title("임준형")
//                .content("초콜릿")
//                .build();
//        postRepository.save(post);
//
//        //expected
//        assertThrows(PostNotFound.class, () -> {
//            postService.get(post.getId() + 1L);
//        });
//        //then
//    }
//
//    @Test
//    @DisplayName("글 내용 수정 - 존재하지 않는 글 오류발생해야함")
//    void test9() {
//        //give
//        Post post = Post.builder()
//                .title("임준형")
//                .content("이것은 내용이지롱")
//                .build();
//
//        postRepository.save(post);
//
//        PostEdit postEdit = PostEdit.builder()
//                .title("임준형")
//                .content("저것은 내용이지롱")
//                .build();
//
//        //expected
//
//        assertThrows(PostNotFound.class, () -> {
//            postService.edit(post.getId() + 1, postEdit);
//        });
//    }
//
//
//
//
//}
