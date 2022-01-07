package com.medical.model.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * The persistent class for the user database table.
 */
@Entity
@Table(schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 65981149772133526L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String providerUserId;

    @Column(unique = true)
    private String email;

    @Column
    private boolean enabled;

    @Column
    private String displayName;

    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdDateTime;

    @Column
    protected LocalDateTime modifiedDateTime;

    private String password;

    private String provider;

    // bi-directional many-to-many association to Role
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

}