package com.example.meetingMinutesdemo.controllers;

import com.example.meetingMinutesdemo.models.Authority;
import com.example.meetingMinutesdemo.services.AuthorityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/authorities")
@RestController

public class AuthorityController {

    @Autowired
    private AuthorityServiceImpl authorityService;

    @GetMapping
    public List<Authority> getAuthorities() {
        return authorityService.getAuthorities();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Authority getAuthorityById(@PathVariable Long id) {
        return authorityService.getAuthorityById(id);
    }


    @GetMapping
    @RequestMapping("username/{username}")
    public List<Authority> getAuthoritiesByusername(@PathVariable String username) {
        return authorityService.getAuthoritiesByUsername(username);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Authority createAuthority(@RequestBody final Authority authority) {
        return authorityService.createAuthority(authority);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteAuthorityById(@PathVariable Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        authorityService.deleteAuthorityById(id);
    }

    @PutMapping(value = "{id}")
    public Authority updateAuthority(@PathVariable Long id, @RequestBody Authority authority) {
        return  authorityService.updateAuthority(id,authority);
    }

}
