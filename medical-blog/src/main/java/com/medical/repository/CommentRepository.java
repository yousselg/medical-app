package com.medical.repository;

import com.medical.model.blog.Comment;
import com.medical.model.blog.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    List<Comment> findByPost(Post post, Pageable pageable);

    List<Comment> findByPost_Id(Long id, Pageable pageable);
}