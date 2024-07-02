package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    AuthorityServiceImpl authorityService;

  //  @Autowired
  //  PasswordEncoder passwordEncoderUserDetails;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =userService.getUserByUsername(username);
        if(user ==null){

            throw new UsernameNotFoundException("User "+username+" Not Found!") ;
        }

        List<SimpleGrantedAuthority> authorities = authorityService.getSimpleGrantedAuthoritiesByUsername (username);

      //  String encryptedPassword =passwordEncoderUserDetails.encode(user.getPassword());
     //   return new org.springframework.security.core.userdetails.User(user.getUsername(), encryptedPassword, authorities);
   return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }
}
