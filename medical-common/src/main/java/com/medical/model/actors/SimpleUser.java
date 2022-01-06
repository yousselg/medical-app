package com.medical.model.actors;

import javax.persistence.Entity;
import java.util.Set;

@Entity
//@Table(name = "SimpleUsers")
public class SimpleUser extends AbstractUser {

    @Override
    public Set<Role> getRoles() {
        return Set.of(Role.ROLE_USER);
    }
}
