package com.assignment.finalproject.controller;

import com.assignment.finalproject.model.Course;
import com.assignment.finalproject.payload.request.CourseRequest;
import com.assignment.finalproject.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/courses")
@CrossOrigin(origins = "*")
public class CourseAdminController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseRequest req) {
        Course c = courseService.createCourse(req);
        return ResponseEntity.ok(c);
    }

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
}
