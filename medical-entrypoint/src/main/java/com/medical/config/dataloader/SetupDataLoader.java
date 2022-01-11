package com.medical.config.dataloader;

import com.medical.model.actors.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@Order
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRoleDataLoader userRoleDataLoader;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (this.alreadySetup) {
            return;
        }
        // Create initial roles
        final Role userRole = this.userRoleDataLoader.createRoleIfNotFound(Role.ROLE_USER);
        final Role adminRole = this.userRoleDataLoader.createRoleIfNotFound(Role.ROLE_ADMIN);
        final Role modRole = this.userRoleDataLoader.createRoleIfNotFound(Role.ROLE_DOCTOR);
        this.userRoleDataLoader.createUserIfNotFound("admin@medical.com", Set.of(userRole, adminRole, modRole));
        this.userRoleDataLoader.createUserIfNotFound("doctor@medical.com", Set.of(userRole, modRole));
        this.userRoleDataLoader.createUserIfNotFound("user@medical.com", Set.of(userRole));

        this.alreadySetup = true;
    }


}