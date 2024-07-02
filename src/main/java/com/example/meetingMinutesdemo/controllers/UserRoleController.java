package com.example.meetingMinutesdemo.controllers;
import com.example.meetingMinutesdemo.models.UserRole;
import com.example.meetingMinutesdemo.services.UserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/roles")
@RestController


public class UserRoleController {
    @Autowired
    private UserRoleServiceImpl userRoleService;

    @GetMapping
    public List<UserRole> getUserRoles() {
        return userRoleService.getUserRoleList();
    }

    @GetMapping
    @RequestMapping("{id}")
    public UserRole getUserRoleById(@PathVariable Long id) {
        return userRoleService.getUserRoleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRole createUserRole(@RequestBody final UserRole userRole) {
        return userRoleService.createUserRole(userRole);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteUserRoleById(@PathVariable Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        userRoleService.deleteUserRoleById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public UserRole updateUserRole(@PathVariable Long id, @RequestBody UserRole userRole) {
        return  userRoleService.updateUserRole(id,userRole);
    }
}
