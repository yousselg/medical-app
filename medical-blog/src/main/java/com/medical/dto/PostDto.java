package com.medical.dto;

import com.medical.model.blog.Post;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class PostDto implements Serializable {
    private Long id;
    @Length(min = Post.MIN_TITLE_LENGTH)
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
    private String featuredImage;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastModificationDateTime;
    private Set<CategoryDto> categories;
    private Set<TagDto> tags;
    private Long likes;
    private UserDto doctor;
}
