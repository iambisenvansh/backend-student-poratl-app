package com.assignment.finalproject.controller;

import com.assignment.finalproject.jwt.JwtUtils;
import com.assignment.finalproject.model.User;
import com.assignment.finalproject.payload.request.LoginRequest;
import com.assignment.finalproject.payload.request.SignupRequest;
import com.assignment.finalproject.payload.response.JwtResponse;
import com.assignment.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")   // front-end ke liye open
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    // --------------- SIGNUP ---------------
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        try {
            User user = userService.registerNewUser(signUpRequest);
            String jwt = jwtUtils.generateJwtToken(user);

            return ResponseEntity.ok(
                    new JwtResponse(
                            jwt,
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getType()
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: " + e.getMessage());
        }
    }

    // --------------- LOGIN ---------------
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // yahan tak aa gaya matlab credentials sahi hain
        User user = userService.findByEmailEntity(loginRequest.getEmail());
        String jwt = jwtUtils.generateJwtToken(user);

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getType()
                )
        );
    }
}
