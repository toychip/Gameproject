package com.gameproject.flash.service;

import com.gameproject.flash.domian.Member;
import com.gameproject.flash.domian.Post;
import com.gameproject.flash.domian.PostEditor;
import com.gameproject.flash.exception.PostNotFound;
import com.gameproject.flash.repository.MemberRepository;
import com.gameproject.flash.repository.PostRepository;
import com.gameproject.flash.request.PostCreateRequest;
import com.gameproject.flash.request.PostEditRequest;
import com.gameproject.flash.request.PostSearchRequest;
import com.gameproject.flash.response.AuthResponse;
import com.gameproject.flash.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public void write(PostCreateRequest postCreateRequest){
        // postCreateRequest -> Entity 형태로 바꿔주어야함. postCreate는 RequestDTO이기 때문
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // 현재 사용자의 email 얻기
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "을 찾을 수 없습니다."));

        Post post = Post.builder()
                .title(postCreateRequest.getTitle())
                .content(postCreateRequest.getContent())
                .member(member)
                .writtenBy(member.getName())
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
                .writtenBy(post.getWrittenBy())
                .writtenDateTime(post.getWrittenDateTime())
                .build();
    }

    public List<PostResponse> search(PostSearchRequest postSearchRequest) {
        return postRepository.search(postSearchRequest).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    public List<PostResponse> getList(PostSearchRequest postSearchRequest) {
//        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "id"));
        return postRepository.getList(postSearchRequest).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());}
    @Transactional
    public void edit(Long id, PostEditRequest postEditRequest){
        // 없는 id일경우
        Post post = postRepository.findById(id).orElseThrow(PostNotFound::new);

        Member currentMember = getCurrentMember();
        if (!post.getMember().getId().equals(currentMember.getId())) {
            throw new AccessDeniedException("게시물을 수정할 권한이 없습니다.");
        }
        PostEditor.PostEditorBuilder editorBuitor = post.toEditor();
        PostEditor postEditor = editorBuitor.title(postEditRequest.getTitle())
                .content(postEditRequest.getContent())
                .build();
        post.edit(postEditor, currentMember, currentMember.getName());
    }
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);
        // -> 존재하는 경우
        Member currentMember = getCurrentMember();
        if (!post.getMember().getId().equals(currentMember.getId())) {
            throw new AccessDeniedException("게시물을 삭제할 권한이 없습니다.");
        }
        postRepository.delete(post);}

    private Member getCurrentMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 현재 사용자의 email 얻기
        return memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "을 찾을 수 없습니다."));
    }

    public AuthResponse getCurrentMemberInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 현재 사용자의 email 얻기
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "을 찾을 수 없습니다."));
        return new AuthResponse(member.getEmail(), member.getName());
    }

}
