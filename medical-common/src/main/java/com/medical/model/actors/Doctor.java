package com.medical.model.actors;

import com.medical.model.blog.Post;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
//@Table(name = "Doctors")
public class Doctor extends SimpleUser {

    @OneToMany(mappedBy = "doctor")
    private Collection<Post> posts;

    @Override
    public Set<Role> getRoles() {
        return Stream.concat(super.getRoles().stream(), Stream.of(Role.ROLE_DOCTOR))
                .collect(Collectors.toSet());
    }
}
