package com.medical.model.actors;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;

@Entity
//@Table(name = "SimpleUsers")
public class SimpleUser extends AbstractUser {

    @Override
    public Collection<Role> getRoles() {
        return List.of(Role.ROLE_USER);
    }
}
