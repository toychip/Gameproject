package com.gameproject.flash.service;

import com.gameproject.flash.domian.Post;
import com.gameproject.flash.domian.PostEditor;
import com.gameproject.flash.exception.PostNotFound;
import com.gameproject.flash.repository.PostRepository;
import com.gameproject.flash.request.PostCreate;
import com.gameproject.flash.request.PostEdit;
import com.gameproject.flash.request.PostSearch;
import com.gameproject.flash.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public void write(PostCreate postCreate){
        // postCreate -> Entity 형태로 바꿔주어야함. postCreate는 RequestDTO이기 때문
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        postRepository.save(post);
    }
    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
    // 여러개의 게시글 조회
//    public List<PostResponse> getList(Pageable pageable) {
////        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "id"));
//
//        return postRepository.getList(1).stream()
//                .map(post -> new PostResponse(post))
//                .collect(Collectors.toList());
//    }
    public List<PostResponse> getList(PostSearch postSearch) {
//        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "id"));
        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());}
    @Transactional

    public void edit(Long id, PostEdit postEdit){
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);
        PostEditor.PostEditorBuilder editorBuitor = post.toEditor();
        PostEditor postEditor = editorBuitor.title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();
        post.edit(postEditor);
//            이러한 불편한 상황들 때문에 postEditor를 사용함
//        post.edit(postEdit.getTitle() != null ? postEdit.getTitle() : post.getTitle(),
//                postEdit.getContent() != null ? postEdit.getContent() : post.getContent());
//        patch할때 body에 수정 정보를 내려 줄 것이면 아래와 같게
//        return new PostResponse(post);
    }
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);
        // -> 존재하는 경우
        postRepository.delete(post);}

}
