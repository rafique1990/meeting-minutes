//package com.example.meetingMinutesdemo.controllers;
//
//
//import com.example.meetingMinutesdemo.exception.BadRequestException;
//import com.example.meetingMinutesdemo.models.AuthProvider;
//import com.example.meetingMinutesdemo.models.User;
//import com.example.meetingMinutesdemo.payload.ApiResponse;
//import com.example.meetingMinutesdemo.payload.AuthResponse;
//import com.example.meetingMinutesdemo.payload.LoginRequest;
//import com.example.meetingMinutesdemo.payload.SignUpRequest;
//import com.example.meetingMinutesdemo.repositories.UserRepository;
//import com.example.meetingMinutesdemo.security.TokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import javax.validation.Valid;
//import java.net.URI;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Qualifier("org.springframework.security.authenticationManager")
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private TokenProvider tokenProvider;
//
//    @GetMapping
//    @RequestMapping("/home")
//    public String home(){
//        return  "<h1>Home Page</h1>";
//    }
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getEmail(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String token = tokenProvider.createToken(authentication);
//        return ResponseEntity.ok(new AuthResponse(token));
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//            throw new BadRequestException("Email address already in use.");
//        }
//
//        // Creating user's account
//        User user = new User();
//        user.setName(signUpRequest.getName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(signUpRequest.getPassword());
////        user.setProvider(AuthProvider.local);
//        user.setProvider(AuthProvider.local);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        User result = userRepository.save(user);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/user/me")
//                .buildAndExpand(result.getId()).toUri();
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "User registered successfully@"));
//    }
//
//}
