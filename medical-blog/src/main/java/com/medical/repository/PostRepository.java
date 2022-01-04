package com.medical.repository;

import com.medical.model.actors.Doctor;
import com.medical.model.blog.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    List<Post> findByDoctor(Doctor doctor, Pageable pageable);

    List<Post> findByDoctor_Id(Long id, Pageable pageable);
}