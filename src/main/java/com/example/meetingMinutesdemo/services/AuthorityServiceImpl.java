package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.exception.meeting.MeetingNotFoundException;
import com.example.meetingMinutesdemo.models.Authority;
import com.example.meetingMinutesdemo.models.User;
import com.example.meetingMinutesdemo.repositories.AuthorityRepository;
import com.example.meetingMinutesdemo.security.jwt.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    public List<Authority> getAuthorities() {

        return authorityRepository.findAll();
    }

    /***
     *  Gets list of the Authorities for a user
     * @param username a unique  username
     * @return List of Authorities
     */
    @Override
    public List<Authority> getAuthoritiesByUsername(String username) {

        return authorityRepository.getAuthoritiesByUsername(username);
    }

    @Override
    public List<GrantedAuthority> getGrantedAuthoritiesByUsername(String username) {
        List<Authority> authorities = authorityRepository.getAuthoritiesByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Authority authority :authorities ){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
         List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<GrantedAuthority>();
          return     grantedAuthoritiesList = grantedAuthorities.stream().collect(Collectors.toList());
    }


    @Override
    public List<SimpleGrantedAuthority> getSimpleGrantedAuthoritiesByUsername(String username) {
        List<Authority> authorities = authorityRepository.getAuthoritiesByUsername(username);
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Authority authority :authorities ){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities  = grantedAuthorities.stream().collect(Collectors.toList());
        return simpleGrantedAuthorities;
    }
    @Override
    public Authority getAuthorityById(Long authorityId) {
        return authorityRepository.findById(authorityId).orElse(null);
    }

    @Override
    public Authority getAuthorityByUsernameAndRoleId(String username, Long roleId)
    {
        return null;
    }

    @Override
    public Authority getAuthorityByUsernameAndAuthorityName(String username, String authorityName)
    {
        return null;
    }

    @Override
    public Authority createAuthority(Authority authority) {
        return authorityRepository.saveAndFlush(authority);
    }

    @Override
    public Authority createDefaultAuthorityForUser(User user) {
        Authority authority = new Authority();
        if( !StringUtils.isEmpty(user.getUsername()))
          authority.setUsername(user.getUsername());
        else if ( !StringUtils.isEmpty(user.getEmail()))
            authority.setUsername(user.getEmail());
        else throw new CustomException("An erro occured while creating authoirity for the user.username and email are null", HttpStatus.INTERNAL_SERVER_ERROR);

      //  authority.setAuthority_id(1L);
        authority.setRole_id(4L);
        authority.setAuthority("ROLE_USER");
        authorityRepository.saveAndFlush(authority);
        return authority;
    }

    @Override
    public void deleteAuthorityByUsernameAndRoleId(String username, Long roleId) {


    }

    @Override
    public void deleteAuthorityByUsernameAndAuthorityName(String username, String authorityName) {


    }

    @Override
    public void deleteAuthoritiesByUsername(String username) {


    }

    @Override
    public void deleteAuthorityById(Long authority_id) {
        authorityRepository.deleteById(authority_id);
    }

    @Override
    public Authority updateAuthority(Long id, Authority authority) {
        Authority existingAuthority =null;
        Optional<Authority> authorityOptional =   authorityRepository.findById(id);

        if( authorityOptional.isPresent()){
            existingAuthority = authorityOptional.get();
        }
        else
            throw new MeetingNotFoundException();
        BeanUtils.copyProperties(authority, existingAuthority, "meeting_id");
        return authorityRepository.saveAndFlush(existingAuthority);
    }

    @Override
    public Authority updateAuthorityByUsernameAndRoleId(String username, Long roleId, Authority authority) {

        return null;
    }

    @Override
    public Authority updateAuthorityByUsernameAndAuthorityName(String username, String authorityName, Authority authority) {
        return null;
    }
    @Override
    public List<String> getUserRolesByUsername(String username) {
        List<GrantedAuthority>  simpleGrantedAuthorities =getGrantedAuthoritiesByUsername(username);

        ArrayList<String> authsList = new ArrayList<String>(simpleGrantedAuthorities.size());

        for (GrantedAuthority authority : simpleGrantedAuthorities) {
            authsList.add(authority.getAuthority());
        }
        return authsList;
    }

}
