package com.gameproject.flash.controller;


import com.gameproject.flash.request.PostCreateRequest;
import com.gameproject.flash.request.PostEditRequest;
import com.gameproject.flash.request.PostSearchRequest;
import com.gameproject.flash.response.PostResponse;
import com.gameproject.flash.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
/*
      SSR -> jsp, thymeleaf, mystache, freemarker 방식
    -> html rendering 서버 렌더링 데이터를 내려줌

     SPA
     vue -> vue + SSR = nuxt
     -> javaascript + <-> API(JSON 응답) 통신 ** 방식 사용

     react -> react + SSR = next.js
 */

    private final PostService postService;


    @PostMapping("/posts")
//    public Map<String, String> post(@RequestBody @Valid PostCreateRequest request){
    public void post(@RequestBody @Valid PostCreateRequest request) {
            request.validate();
            postService.write(request);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> search(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String writtenBy
    ) {
        PostSearchRequest postSearchRequest = PostSearchRequest.builder()
                .page(page)
                .size(size)
                .title(title)
                .content(content)
                .writtenBy(writtenBy)
                .build();

        return ResponseEntity.ok(postService.search(postSearchRequest));
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {

        return postService.get(postId);
        // 응답 전용 클래스를 만드는 것이 좋다.
    }

    //조회 API
    // 여러개의 글 조회 API (1개의 글 Post을 가져오는 기능)
//    @GetMapping("/posts")
//    public List<PostResponse> getList(@ModelAttribute PostSearchRequest postSearch) {
//        return postService.getList(postSearch);
//    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEditRequest request){
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId){
        postService.delete(postId);
    }


}
