package com.medical.service.impl;

import com.medical.exception.ResourceNotFoundException;
import com.medical.model.blog.Comment;
import com.medical.model.blog.Post;
import com.medical.repository.PostRepository;
import com.medical.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostRepository repository;

    @Override
    public Post save(final Post post) {
        return this.repository.save(post);
    }

    @Override
    public Post findById(final Long id) {
        return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
    }

    @Override
    public List<Post> findAll() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(final Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Post addComment(final Long postId, final Comment comment) {
        final Post post = this.repository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));
        post.getComments().add(comment);
        comment.setPost(post);
        return this.repository.save(post);
    }
}
