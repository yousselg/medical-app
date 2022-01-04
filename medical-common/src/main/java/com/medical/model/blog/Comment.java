package com.medical.model.blog;

import com.medical.model.actors.AbstractUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotEmpty
    private String body;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDateTime;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime lastModificationDateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Post post;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private AbstractUser user;

    @PrePersist
    public void prePersist() {
        this.creationDateTime = LocalDateTime.now();
        this.lastModificationDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModificationDateTime = LocalDateTime.now();
    }
}
