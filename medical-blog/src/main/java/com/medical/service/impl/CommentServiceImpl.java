package com.medical.service.impl;

import com.medical.model.blog.Comment;
import com.medical.repository.CommentRepository;
import com.medical.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository repository;

    @Override
    public List<Comment> findByPostId(final Long id, final Pageable pageable) {
        return this.repository.findByPost_Id(id, pageable);
    }
}
