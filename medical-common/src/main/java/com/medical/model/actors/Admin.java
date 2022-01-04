package com.medical.model.actors;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;

@Entity
//@Table(name = "Admins")
public class Admin extends Doctor {

    @Override
    public Collection<Role> getRoles() {
        return List.of(Role.ROLE_ADMIN, Role.ROLE_DOCTOR, Role.ROLE_USER);
    }
}
