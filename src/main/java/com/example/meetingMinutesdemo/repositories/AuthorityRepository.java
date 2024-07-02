package com.example.meetingMinutesdemo.repositories;

import com.example.meetingMinutesdemo.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    List<Authority> getAuthoritiesByUsername(String username);
//    Optional<Authority> getAuthorityByUsernameAndRole_id(String username,Long role_id);
//    Optional<Authority> getAuthoritiesByUsernameAndAuthority(String username,String authority);
//    void deleteAuthorityByUsernameAndRole_id(String username,Long role_id);
//    void deleteAuthorityByUsernameAndAuthority(String username,String authority);
//    void deleteAllByUsername(String username);
//    Authority findAuthoritiesByUsernameAndRole_id(String username,Long role_id);
//    Authority findAuthorityByUsernameAndAuthority(String username,String authority);

}
