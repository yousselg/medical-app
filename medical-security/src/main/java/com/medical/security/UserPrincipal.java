package com.medical.security;

import com.medical.model.actors.AbstractUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserPrincipal implements OAuth2User, UserDetails {

    private final Long id;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final AbstractUser abstractUser;
    private Map<String, Object> attributes;

    public UserPrincipal(final Long id, final String email, final String password, final Collection<? extends GrantedAuthority> authorities, final AbstractUser abstractUser) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.abstractUser = abstractUser;
    }

    public static UserPrincipal create(final AbstractUser user) {

        final List<GrantedAuthority> authorities = user.getRoles().stream().
                map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user
        );
    }

    public static UserPrincipal create(final AbstractUser user, final Map<String, Object> attributes) {
        final UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(final Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(this.id);
    }

    public AbstractUser getUser() {
        return this.abstractUser;
    }
}
