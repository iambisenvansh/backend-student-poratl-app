package com.assignment.finalproject.controller;

import com.assignment.finalproject.model.Course;
import com.assignment.finalproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CoursePublicController {

    @Autowired
    private CourseService courseService;

    // All authenticated users (students/teachers) can view courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
