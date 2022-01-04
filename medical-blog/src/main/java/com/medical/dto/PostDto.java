package com.medical.dto;

import com.medical.model.blog.Post;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
public class PostDto implements Serializable {
    private Long id;
    @Length(min = Post.MIN_TITLE_LENGTH)
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastModificationDateTime;
    private Collection<CommentDto> comments;
    private Long likes;
    private DoctorDto doctor;
}
