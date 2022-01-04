package com.medical.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto implements Serializable {
    private Long id;
    @NotEmpty
    private String body;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastModificationDateTime;
}
