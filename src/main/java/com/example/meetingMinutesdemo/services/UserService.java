package com.example.meetingMinutesdemo.services;

import com.example.meetingMinutesdemo.models.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

public interface UserService {

    public  abstract Principal login();
    public abstract void logout();
    public List<User> getUsers();
    public abstract User createUser(User user);
    public abstract User  updateUser(Long id, User user);
    public abstract void  deleteUserById(Long id);
    public abstract  User getUserById(Long id);
    public abstract  User getUserByUsername(String username);
    public abstract  User getUserByEmail(String email);
    /*******Some changes********/
    public abstract String signin(String username, String password);
    public abstract  String signup(User user);
    public abstract void delete(String username);
    public abstract  User search(String username);
    public abstract User whoami(HttpServletRequest req);
    public abstract String refresh(String username);
}
