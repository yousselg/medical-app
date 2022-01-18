package com.medical.service;

import com.medical.model.blog.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {

    Post save(Post post);

    Post findById(Long id);

    List<Post> findAll();

    List<Post> findAll(Pageable pageable);

    void deleteById(Long id);

}
