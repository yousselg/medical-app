package com.medical.model.blog;

import com.medical.model.actors.User;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "posts")
public class Post {

    public static final int MIN_TITLE_LENGTH = 7;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Length(min = MIN_TITLE_LENGTH)
    @NotEmpty
    @Column(nullable = false)
    private String title;

    @NotEmpty
    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDateTime;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime lastModificationDateTime;

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Collection<Comment> comments;

    private Long likes;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private User doctor;

    @ManyToMany
    @JoinTable(name = "post_categories",
            joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")})
    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "post_tags",
            joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")})
    private Set<Tag> tags = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.creationDateTime = LocalDateTime.now();
        this.lastModificationDateTime = LocalDateTime.now();
        if (this.likes == null) {
            this.likes = 0L;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModificationDateTime = LocalDateTime.now();
    }

}