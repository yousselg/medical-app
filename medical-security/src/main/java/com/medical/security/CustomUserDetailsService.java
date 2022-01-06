package com.medical.security;


import com.medical.exception.ResourceNotFoundException;
import com.medical.model.actors.AbstractUser;
import com.medical.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {
        final AbstractUser user = this.userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(final Long id) {
        final AbstractUser user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id.toString())
        );

        return UserPrincipal.create(user);
    }
}