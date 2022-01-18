package com.medical.util;

import com.medical.dto.LocalUser;
import com.medical.dto.SocialProvider;
import com.medical.dto.UserDto;
import com.medical.model.actors.Role;
import com.medical.model.actors.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yousselg
 */
public class GeneralUtils {

    public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
        final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (final Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public static SocialProvider toSocialProvider(final String providerId) {
        for (final SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.getProviderType().equals(providerId)) {
                return socialProvider;
            }
        }
        return SocialProvider.LOCAL;
    }

    public static UserDto buildUserInfo(final LocalUser localUser) {
        final Set<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toSet());
        final User user = localUser.getUser();
        return new UserDto(user.getId(), user.getEmail(), user.getDisplayName(), user.getCreatedDateTime(), user.getModifiedDateTime(), roles);
    }
}
