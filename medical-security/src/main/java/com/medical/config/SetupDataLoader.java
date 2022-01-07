package com.medical.config;

import com.medical.dto.SocialProvider;
import com.medical.model.actors.Role;
import com.medical.model.actors.User;
import com.medical.repository.RoleRepository;
import com.medical.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (this.alreadySetup) {
            return;
        }
        // Create initial roles
        final Role userRole = this.createRoleIfNotFound(Role.ROLE_USER);
        final Role adminRole = this.createRoleIfNotFound(Role.ROLE_ADMIN);
        final Role modRole = this.createRoleIfNotFound(Role.ROLE_DOCTOR);
        this.createUserIfNotFound("admin@javachinna.com", Set.of(userRole, adminRole, modRole));
        this.alreadySetup = true;
    }

    @Transactional
    private final User createUserIfNotFound(final String email, final Set<Role> roles) {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setDisplayName("Admin");
            user.setEmail(email);
            user.setPassword(this.passwordEncoder.encode("admin@"));
            user.setRoles(roles);
            user.setProvider(SocialProvider.LOCAL.getProviderType());
            user.setEnabled(true);
            final LocalDateTime now = LocalDateTime.now();
            user.setCreatedDateTime(now);
            user.setModifiedDateTime(now);
            user = this.userRepository.save(user);
        }
        return user;
    }

    @Transactional
    private final Role createRoleIfNotFound(final String name) {
        Role role = this.roleRepository.findByName(name);
        if (role == null) {
            role = this.roleRepository.save(new Role(name));
        }
        return role;
    }
}