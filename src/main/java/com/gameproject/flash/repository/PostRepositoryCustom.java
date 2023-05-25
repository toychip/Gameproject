package com.gameproject.flash.repository;

import com.gameproject.flash.domian.Post;
import com.gameproject.flash.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
