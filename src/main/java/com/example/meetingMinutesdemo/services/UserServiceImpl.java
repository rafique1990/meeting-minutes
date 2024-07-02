package com.example.meetingMinutesdemo.services;
import com.example.meetingMinutesdemo.models.AuthProvider;
import com.example.meetingMinutesdemo.models.User;
import com.example.meetingMinutesdemo.repositories.UserRepository;
import com.example.meetingMinutesdemo.security.jwt.CustomException;
import com.example.meetingMinutesdemo.security.jwt.JwtTokenProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserRepository userRepository;


//    @Autowired
//    private PasswordEncoder passwordEncoderUserService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Principal login() {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser =null;
        Optional<User> userOptional =   userRepository.findById(id);

        if( userOptional.isPresent()){
            existingUser = userOptional.get();
        }
        else
     {
         /** TO DO :Though  Some exception here*/
     }
        BeanUtils.copyProperties(user, existingUser, "id");
        return userRepository.saveAndFlush(existingUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User getUserByUsername(String username) {

        Optional<User> user =userRepository.getUserByUsername(username);

        if(user.isPresent())
            return user.get();
        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        Optional<User> user =userRepository.getUserByEmail(email);

        if(user.isPresent())
            return user.get();
        return null;
    }

    /***********************/
    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
//         user.setPassword(passwordEncoderUserService.encode(user.getPassword()));
           user.setProvider(AuthProvider.local);
            userRepository.saveAndFlush(user);
            String access_token =jwtTokenProvider.createToken(user.getUsername());
            System.out.println("access_token");
            System.out.println(access_token);

            return access_token;
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username);
    }

}