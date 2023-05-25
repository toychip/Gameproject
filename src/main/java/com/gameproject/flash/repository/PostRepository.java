package com.gameproject.flash.repository;

import com.gameproject.flash.domian.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{

//    @EntityGraph(attributePaths = {"category", "member"})
    Optional<Post> findById(Long postId);
}
