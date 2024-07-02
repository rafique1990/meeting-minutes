package com.example.meetingMinutesdemo.security;



import com.example.meetingMinutesdemo.exception.ResourceNotFoundException;
import com.example.meetingMinutesdemo.models.User;
import com.example.meetingMinutesdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * If the OAuth2 callback is successful and it contains the authorization code,Spring Security will exchange the authorization_code for an access_token
 * and invoke the customOAuth2UserService specified in the above SecurityConfig.
 * The customOAuth2UserService retrieves the details of the authenticated user and
 * creates a new entry in the database or updates the existing entry with the same email.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailOrUsername)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrUsername(emailOrUsername,emailOrUsername)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + emailOrUsername)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}