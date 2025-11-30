package com.assignment.finalproject.service;

import com.assignment.finalproject.model.Course;
import com.assignment.finalproject.payload.request.CourseRequest;
import com.assignment.finalproject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(CourseRequest req) {
        if (courseRepository.existsByCode(req.getCode())) {
            throw new RuntimeException("Course code already exists");
        }

        Course c = new Course();
        c.setCode(req.getCode());
        c.setName(req.getName());
        c.setDescription(req.getDescription());
        return courseRepository.save(c);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
