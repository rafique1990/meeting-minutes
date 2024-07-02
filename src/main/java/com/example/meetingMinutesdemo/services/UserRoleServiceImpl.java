package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.UserRole;
import com.example.meetingMinutesdemo.repositories.UserRoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**TO DO
 * USE GNERICS FOR CREATING BASIC CRUD OPERATIONS
 * **/
@Service
public class UserRoleServiceImpl implements  UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> getUserRoleList() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole getUserRoleById(Long id) {
        return userRoleRepository.getOne(id);
    }

    @Override
    public UserRole createUserRole(UserRole userRole) {
        return userRoleRepository.saveAndFlush(userRole);
    }

    @Override
    public void deleteUserRoleById(Long id) {
        userRoleRepository.deleteById(id);
    }

    @Override
    public UserRole updateUserRole(Long id, UserRole userRole) {
        UserRole existingUserRole =null;
        Optional<UserRole> userRoleOptional =   userRoleRepository.findById(id);

        if( userRoleOptional.isPresent()){
            existingUserRole = userRoleOptional.get();
        }
        else
        {

            /**TO DO : Through custom exception here*/
        }
        BeanUtils.copyProperties(userRole, existingUserRole, "role_id");
        return userRoleRepository.saveAndFlush(existingUserRole);
    }
}
