package com.example.meetingMinutesdemo.controllers;
import com.example.meetingMinutesdemo.exception.BadRequestException;
import com.example.meetingMinutesdemo.exception.ResourceNotFoundException;
import com.example.meetingMinutesdemo.models.AuthProvider;
import com.example.meetingMinutesdemo.models.User;
import com.example.meetingMinutesdemo.payload.ApiResponse;
import com.example.meetingMinutesdemo.payload.AuthResponse;
import com.example.meetingMinutesdemo.payload.LoginRequest;
import com.example.meetingMinutesdemo.repositories.UserRepository;
import com.example.meetingMinutesdemo.security.CurrentUser;
import com.example.meetingMinutesdemo.security.TokenProvider;
import com.example.meetingMinutesdemo.security.UserPrincipal;
import com.example.meetingMinutesdemo.services.UserServiceImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody final User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable Long id) {
        //check for children records e.g cascading if there is any foreign key relationship between entities.
        userService.deleteUserById(id);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @PutMapping(value = "{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return  userService.updateUser(id,user);
    }
    @GetMapping
    @RequestMapping("/")
    public ResponseEntity<Object> user(Principal principal) {
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }


    /*******************************/
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

        if(!StringUtils.isEmpty(user.getEmail()) && userRepository.existsByEmail(user.getEmail() )) {
            throw new BadRequestException("Email address already in use.");
        }
       else if(!StringUtils.isEmpty(user.getUsername()) && userRepository.existsByUsername(user.getUsername() )) {
            throw new BadRequestException("Email address already in use.");
        }
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userRepository.saveAndFlush(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ApiParam("username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User search(@ApiParam("username") @PathVariable String username) {
        return  userService.search(username);
    }

//    @GetMapping(value = "/me")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
//    public User whoami(HttpServletRequest request) {
//        return userService.whoami(request);
//    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')  or hasRole('ROLE_USER')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }
}
