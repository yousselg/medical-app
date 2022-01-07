package com.medical.service;

import com.medical.dto.LocalUser;
import com.medical.dto.SignUpRequest;
import com.medical.dto.SocialProvider;
import com.medical.exception.OAuth2AuthenticationProcessingException;
import com.medical.exception.UserAlreadyExistAuthenticationException;
import com.medical.model.actors.Role;
import com.medical.model.actors.User;
import com.medical.repository.RoleRepository;
import com.medical.repository.UserRepository;
import com.medical.security.oauth2.user.OAuth2UserInfo;
import com.medical.security.oauth2.user.OAuth2UserInfoFactory;
import com.medical.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

/**
 * @author Chinna
 * @since 26/3/18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(value = "transactionManager")
    public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null && this.userRepository.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
        } else if (this.userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = this.buildUser(signUpRequest);
        final LocalDateTime now = LocalDateTime.now();
        user.setCreatedDateTime(now);
        user.setModifiedDateTime(now);
        user = this.userRepository.save(user);
        this.userRepository.flush();
        return user;
    }

    private User buildUser(final SignUpRequest formDTO) {
        final User user = new User();
        user.setDisplayName(formDTO.getDisplayName());
        user.setEmail(formDTO.getEmail());
        user.setPassword(this.passwordEncoder.encode(formDTO.getPassword()));
        final HashSet<Role> roles = new HashSet<>();
        roles.add(this.roleRepository.findByName(Role.ROLE_USER));
        user.setRoles(roles);
        user.setProvider(formDTO.getSocialProvider().getProviderType());
        user.setEnabled(true);
        user.setProviderUserId(formDTO.getProviderUserId());
        return user;
    }

    @Override
    public User findUserByEmail(final String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public LocalUser processUserRegistration(final String registrationId, final Map<String, Object> attributes, final OidcIdToken idToken, final OidcUserInfo userInfo) {
        final OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        final SignUpRequest userDetails = this.toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = this.findUserByEmail(oAuth2UserInfo.getEmail());
        if (user != null) {
            if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
            }
            user = this.updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = this.registerNewUser(userDetails);
        }

        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    private User updateExistingUser(final User existingUser, final OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setDisplayName(oAuth2UserInfo.getName());
        return this.userRepository.save(existingUser);
    }

    private SignUpRequest toUserRegistrationObject(final String registrationId, final OAuth2UserInfo oAuth2UserInfo) {
        return SignUpRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
                .addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
    }

    @Override
    public Optional<User> findUserById(final Long id) {
        return this.userRepository.findById(id);
    }
}
