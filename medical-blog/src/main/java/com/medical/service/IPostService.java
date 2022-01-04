package com.medical.service;

import com.medical.model.blog.Comment;
import com.medical.model.blog.Post;

import java.util.List;

public interface IPostService {

    Post save(Post post);

    Post findById(Long id);

    List<Post> findAll();

    void deleteById(Long id);

    Post addComment(Long postId, Comment comment);


}
