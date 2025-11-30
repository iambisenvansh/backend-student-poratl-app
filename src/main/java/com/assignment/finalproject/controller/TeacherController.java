package com.assignment.finalproject.controller;

import com.assignment.finalproject.model.User;
import com.assignment.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin(origins = "*")
public class TeacherController {

    @Autowired
    private UserService userService;

    // ✅ Teacher add/update student points
    @PostMapping("/students/{id}/points")
    public ResponseEntity<?> updatePoints(
            @PathVariable Long id,
            @RequestParam int points) {

        User updated = userService.updateStudentPoints(id, points);
        return ResponseEntity.ok(updated);
    }
}
