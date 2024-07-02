package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.Authority;
import com.example.meetingMinutesdemo.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface AuthorityService {

    public abstract List<Authority> getAuthorities();
    public abstract  List<String> getUserRolesByUsername(String username);
    public abstract  List<Authority> getAuthoritiesByUsername(String username);
    public abstract  List<GrantedAuthority> getGrantedAuthoritiesByUsername(String username);
    public List<SimpleGrantedAuthority> getSimpleGrantedAuthoritiesByUsername(String username);
    public abstract  Authority getAuthorityById(Long authorityId);
    public abstract  Authority getAuthorityByUsernameAndRoleId(String username,Long roleId);
    public abstract  Authority getAuthorityByUsernameAndAuthorityName(String username,String authorityName);
    public abstract Authority createAuthority(final Authority authority);
    public abstract Authority createDefaultAuthorityForUser(final User user);
    public abstract  void    deleteAuthorityByUsernameAndRoleId(String username ,Long roleId);
    public abstract  void    deleteAuthorityByUsernameAndAuthorityName(String username ,String authorityName);
    public abstract  void    deleteAuthoritiesByUsername(String username);
    public abstract  void    deleteAuthorityById(Long authority_id);
    public abstract  Authority updateAuthority(Long id,Authority authority);
    public abstract  Authority updateAuthorityByUsernameAndRoleId(String username, Long roleId,Authority authority);
    public abstract  Authority updateAuthorityByUsernameAndAuthorityName(String username,String authorityName ,Authority authority);
}
