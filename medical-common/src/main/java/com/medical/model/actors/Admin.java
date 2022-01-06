package com.medical.model.actors;

import javax.persistence.Entity;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
//@Table(name = "Admins")
public class Admin extends Doctor {

    @Override
    public Set<Role> getRoles() {
        return Stream.concat(super.getRoles().stream(), Stream.of(Role.ROLE_ADMIN))
                .collect(Collectors.toSet());
    }
}
