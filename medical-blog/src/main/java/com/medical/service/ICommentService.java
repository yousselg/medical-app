package com.medical.service;

import com.medical.model.blog.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICommentService {

    List<Comment> findByPostId(Long id, Pageable pageable);

}
