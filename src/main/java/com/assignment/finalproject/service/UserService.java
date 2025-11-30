package com.assignment.finalproject.service;

import com.assignment.finalproject.model.User;
import com.assignment.finalproject.model.UserType;
import com.assignment.finalproject.payload.request.SignupRequest;
import com.assignment.finalproject.payload.request.UpdateProfileRequest;
import com.assignment.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ SIGNUP
    public User registerNewUser(SignupRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        if (userRepository.existsByMobile(signUpRequest.getMobile())) {
            throw new RuntimeException("Mobile already in use");
        }

        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setMobile(signUpRequest.getMobile());
        user.setGender(signUpRequest.getGender());
        user.setDepartment(signUpRequest.getDepartment());

        // secure password
        user.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword()));

        user.setType(signUpRequest.getType() != null ? signUpRequest.getType() : UserType.STUDENT);
        user.setPoints(0);

        return userRepository.save(user);
    }

    // ✅ Spring Security ke liye
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        String role = "ROLE_" + user.getType().name(); // e.g. ROLE_ADMIN

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                authorities
        );
    }

    // ✅ AuthController use karega
    public User findByEmailEntity(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ✅ PROFILE UPDATE
    public User updateProfile(String currentEmail, UpdateProfileRequest req) {
        User user = userRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Duplicate email check (self ko allow)
        if (!user.getEmail().equals(req.getEmail()) &&
                userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        // Duplicate mobile check
        if (!user.getMobile().equals(req.getMobile()) &&
                userRepository.existsByMobile(req.getMobile())) {
            throw new RuntimeException("Mobile already in use");
        }

        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setMobile(req.getMobile());
        user.setDepartment(req.getDepartment());

        return userRepository.save(user);
    }

    // ✅ TEACHER – Update student points
    public User updateStudentPoints(Long studentId, int points) {
        User user = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (user.getType() != UserType.STUDENT) {
            throw new RuntimeException("Points can only be updated for students");
        }

        user.setPoints(points); // ya user.setPoints(user.getPoints() + points);
        return userRepository.save(user);
    }

    // ✅ RANKLIST
    public List<User> getStudentRankList() {
        return userRepository.findByTypeOrderByPointsDesc(UserType.STUDENT);
    }
}
