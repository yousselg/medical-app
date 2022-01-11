package com.medical.repository;

import com.medical.model.actors.User;
import com.medical.model.blog.Category;
import com.medical.model.blog.Post;
import com.medical.model.blog.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    List<Post> findByDoctor(User doctor, Pageable pageable);

    List<Post> findByDoctor_Id(Long id, Pageable pageable);

    List<Post> findByCategories(Category category, Pageable pageable);

    List<Post> findByCategories_Id(Long id, Pageable pageable);

    List<Post> findByTags(Tag tag, Pageable pageable);

    List<Post> findByTags_Id(Long id, Pageable pageable);
}