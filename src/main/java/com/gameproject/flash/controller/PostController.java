package com.gameproject.flash.controller;


import com.gameproject.flash.domian.Post;
import com.gameproject.flash.request.PostCreate;
import com.gameproject.flash.request.PostEdit;
import com.gameproject.flash.request.PostSearch;
import com.gameproject.flash.response.PostResponse;
import com.gameproject.flash.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/postsNn")    // 처음에 작성한, 글을 가져오는 것
//    public Map<String, String> post(@RequestBody @Valid PostCreate request){
    public Post postNn(@RequestBody @Valid PostCreate request) {


        return postService.writeNn(request);
//        return Map.of();
    }

    @PostMapping("/postsPk")    // 글 pk로 가져오기
    public Map postId(@RequestBody @Valid PostCreate request) {
//        저장한 데이터의 primary_id -> response로 응답하기
//        Client에서는 수신한 id를 글 조회 API를 통해서 데이터를 수신받음
        Long postId = postService.writePk(request);
        return Map.of("postId", postId);
    }

    @PostMapping("/posts")
//    public Map<String, String> post(@RequestBody @Valid PostCreate request){
    public void post(@RequestBody @Valid PostCreate request) {
            request.validate();
            postService.write(request);
    }


    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {

        return postService.get(postId);
        // 응답 전용 클래스를 만드는 것이 좋다.
    }

    //조회 API
    // 여러개의 글 조회 API (1개의 글 Post을 가져오는 기능)
    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request){
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId){
        postService.delete(postId);
    }
}
























