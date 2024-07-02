package com.example.meetingMinutesdemo.security.oauth2.user;



import com.example.meetingMinutesdemo.exception.OAuth2AuthenticationProcessingException;
import com.example.meetingMinutesdemo.models.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {
//TO DO
    //Refactor this Factory class.
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
