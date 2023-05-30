package com.gameproject.flash.repository;

import com.gameproject.flash.domian.Post;
import com.gameproject.flash.request.PostSearchRequest;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearchRequest postSearchRequest);
    List<Post> search(PostSearchRequest postSearchRequest);
}
