package com.assignment.finalproject.controller;

import com.assignment.finalproject.model.User;
import com.assignment.finalproject.payload.request.UpdateProfileRequest;
import com.assignment.finalproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Update profile (mobile, email, name, department)
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @Valid @RequestBody UpdateProfileRequest req,
            Authentication authentication) {

        String currentEmail = authentication.getName(); // JWT se aata hai
        User updated = userService.updateProfile(currentEmail, req);
        return ResponseEntity.ok(updated);
    }

    // ✅ Ranklist for students
    @GetMapping("/ranklist")
    public ResponseEntity<?> getRankList() {
        List<User> rankList = userService.getStudentRankList();
        return ResponseEntity.ok(rankList);
    }
}
