package com.assignment.finalproject.repository;

import com.assignment.finalproject.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByCode(String code);
}
