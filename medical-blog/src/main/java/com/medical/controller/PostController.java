package com.medical.controller;

import com.medical.dto.ApiResponse;
import com.medical.dto.PostDto;
import com.medical.mapper.CommentMapper;
import com.medical.mapper.PostMapper;
import com.medical.model.actors.Doctor;
import com.medical.model.blog.Post;
import com.medical.security.CurrentUser;
import com.medical.security.UserPrincipal;
import com.medical.service.ICommentService;
import com.medical.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IPostService service;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private PostMapper mapper;
    @Autowired
    private CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<ApiResponse> findAll() {
        final List<PostDto> postDtos = this.service.findAll().stream().map(this.mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, postDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable final Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, this.mapper.toDto(this.service.findById(id))));
    }

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody final PostDto postDto, @ApiIgnore @CurrentUser final UserPrincipal userPrincipal) {
        return this.saveOrUpdatePost(postDto, userPrincipal);
    }

    @PutMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> update(@Valid @RequestBody final PostDto postDto, @ApiIgnore @CurrentUser final UserPrincipal userPrincipal) {
        return this.saveOrUpdatePost(postDto, userPrincipal);
    }

    private ResponseEntity<ApiResponse> saveOrUpdatePost(final PostDto postDto, final UserPrincipal userPrincipal) {
        final Post post = this.mapper.toModel(postDto);
        post.setDoctor((Doctor) userPrincipal.getUser());
        return ResponseEntity.ok(new ApiResponse<>(true, this.mapper.toDto(this.service.save(post))));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse> delete(@PathVariable final Long id, @ApiIgnore @CurrentUser final UserPrincipal userPrincipal) {
        final Post post = this.service.findById(id);
        if (userPrincipal.getUser() instanceof Doctor && post.getDoctor().equals(userPrincipal.getUser())) {
            return ResponseEntity.ok(new ApiResponse<>(true, "Post has been deleted"));
        }
        return ResponseEntity.ok(new ApiResponse<>(false, "You cannot delete other's posts"));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<ApiResponse> findByIdWithComments(@PathVariable final Long id,
                                                            @RequestParam(defaultValue = "0") final Integer page,
                                                            @RequestParam(defaultValue = "10") final Integer size) {
        return ResponseEntity.ok(new ApiResponse<>(true,
                this.commentMapper.toDto(
                        this.commentService.findByPostId(id, PageRequest.of(page, size)))));
    }
}
