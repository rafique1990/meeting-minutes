package com.example.meetingMinutesdemo.services;



import com.example.meetingMinutesdemo.models.UserRole;

import java.util.List;

public interface UserRoleService {

    public abstract List<UserRole> getUserRoleList();
    public abstract  UserRole getUserRoleById(Long id);
    public abstract  UserRole createUserRole(final UserRole userRole);
    public abstract  void   deleteUserRoleById(Long id);
    public abstract  UserRole updateUserRole(Long id, UserRole userRole);
}
