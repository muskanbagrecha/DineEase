package com.mb.DineEase.controller;

import com.mb.DineEase.factory.UserFactory;
import com.mb.DineEase.model.user.User;
import com.mb.DineEase.request.LoginRequest;
import com.mb.DineEase.response.AuthResponse;
import com.mb.DineEase.respository.UserRepository;
import com.mb.DineEase.securityconfig.JwtUtil;
import com.mb.DineEase.service.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerUserDetails userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserFactory userFactory;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String role = (String) userMap.get("role");

        User userExists = userRepository.findUserByEmail(email).orElse(null);
        if (userExists != null) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        try {
            User user = userFactory.createUser(role, email, encodedPassword);
            userRepository.save(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = jwtUtil.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token, "Registered successfully", role);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (AuthenticationException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token, "Logged in successfully", userDetails.getAuthorities().iterator().next().getAuthority());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
