package com.medical.config.dataloader;

import com.medical.dto.SocialProvider;
import com.medical.model.actors.Role;
import com.medical.model.actors.User;
import com.medical.repository.RoleRepository;
import com.medical.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class UserRoleDataLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User createUserIfNotFound(final String email, final Set<Role> roles) {
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
    public Role createRoleIfNotFound(final String name) {
        Role role = this.roleRepository.findByName(name);
        if (role == null) {
            role = this.roleRepository.save(new Role(name));
        }
        return role;
    }
}
