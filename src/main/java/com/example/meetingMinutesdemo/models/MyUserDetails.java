//package com.example.meetingMinutesdemo.models;
//
//import com.example.meetingMinutesdemo.services.AuthorityServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class MyUserDetails implements UserDetails {
//
//    @Autowired
//    AuthorityServiceImpl authorityService;
//
//    private Long user_id;
//    private String first_name;
//    private String last_name;
//    private String email;
//    private Date dob;
//    private Gender gender;
//    private Long role_id;
//    private String username;
//    private String  password ;
//    private Boolean  enabled   ;
//    List<GrantedAuthority> authorities;
//
//
//    public MyUserDetails() {
//    }
//
//    public MyUserDetails(User user) {
//
//        this.user_id =user.getId();
//        this.first_name =user.getFirst_name();
//        this.last_name =user.getLast_name();
//        this.email =user.getEmail();
//        this.dob=user.getDob();
//   //     this.gender=user.getGender();
//      //  this.role_id=user.getRole_id();
//        this.username =user.getUsername();
//        this.password =user.getPassword() ;
//        this.enabled =user.getEnabled();
//
////        this.authorities =authorityService.getAuthoritiesByUsername(username)
////                .stream()
////                .map( authority ->  SimpleGrantedAuthority::new)
////                .collect(Collectors.toList());
//
//        this.authorities =    authorityService.getAuthoritiesByUsername(username)
//                .stream()
//                .map( el -> new SimpleGrantedAuthority(el.getAuthority()) ).collect(Collectors.toList());
//
//      //  this.authorities.add( new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//}
