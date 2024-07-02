package com.example.meetingMinutesdemo.security.oauth2;


import com.example.meetingMinutesdemo.exception.OAuth2AuthenticationProcessingException;
import com.example.meetingMinutesdemo.models.AuthProvider;
import com.example.meetingMinutesdemo.models.User;
import com.example.meetingMinutesdemo.repositories.UserRepository;
import com.example.meetingMinutesdemo.security.UserPrincipal;
import com.example.meetingMinutesdemo.security.oauth2.user.OAuth2UserInfo;
import com.example.meetingMinutesdemo.security.oauth2.user.OAuth2UserInfoFactory;
import com.example.meetingMinutesdemo.services.AuthorityService;
import com.example.meetingMinutesdemo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/****
 * customOAuth2UserService retrieves the details of the authenticated user
 * this method is called after an access token is obtained from the OAuth2 provider.
 */
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityService authorityService;

        //The oauth2 callback is handled by Spring. It automatically exchanges the code for the access_token and calls the CustomOAuth2UserService.loadUser()
        //This method is called after an access token is obtained from the OAuth2 provider
        //In this method, we first fetch the user’s details from the OAuth2 provider.

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        String email=oAuth2UserInfo.getEmail();

        if(StringUtils.isEmpty(email)) {

            if(oAuth2UserInfo.getAttributes().get("email")!=null) {
                email =oAuth2UserInfo.getAttributes().get("email").toString();
            }
          else  if(oAuth2UserInfo.getAttributes().get("login")!=null) {
                email =oAuth2UserInfo.getAttributes().get("login").toString();
            }

            else throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmailOrUsername(email,email);
        User user;
        if(userOptional.isPresent()) {


                user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        if(user.getProvider().toString().equalsIgnoreCase("local"))
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getProvider().toString().equalsIgnoreCase("github")){

            if( !StringUtils.isEmpty(oAuth2UserInfo.getAttributes().get("login").toString()))
                user.setUsername(oAuth2UserInfo.getAttributes().get("login").toString());
        }

      authorityService.createDefaultAuthorityForUser(user);
        return userService.createUser(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userService.updateUser( existingUser.getId(), existingUser);
    }

}
