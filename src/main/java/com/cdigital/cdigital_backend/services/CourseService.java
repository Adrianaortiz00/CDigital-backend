package com.cdigital.cdigital_backend.services;

import java.util.List;
import java.util.Optional;

import com.cdigital.cdigital_backend.models.Courses;
import com.cdigital.cdigital_backend.repositories.CourseRepository;

public class CourseService {
    private CourseRepository courseRepository;

    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Courses> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public Courses saveCourse(Courses course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }
}
