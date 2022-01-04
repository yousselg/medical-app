package com.medical.model.actors;

import com.medical.model.blog.Post;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.List;

@Entity
//@Table(name = "Doctors")
public class Doctor extends SimpleUser {

    @OneToMany(mappedBy = "doctor")
    private Collection<Post> posts;

    @Override
    public Collection<Role> getRoles() {
        return List.of(Role.ROLE_DOCTOR, Role.ROLE_USER);
    }
}
