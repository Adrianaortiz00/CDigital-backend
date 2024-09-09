package com.cdigital.cdigital_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdigital.cdigital_backend.models.Courses;
import com.cdigital.cdigital_backend.models.User;
import com.cdigital.cdigital_backend.repositories.CourseRepository;
import com.cdigital.cdigital_backend.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseService {

    
    private CourseRepository courseRepository;

    
    private UserRepository userRepository;

    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    public Courses createCourse(String title, String description, String videoUrl, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Courses newCourse = new Courses(title, description, user, videoUrl);
        return courseRepository.save(newCourse);
    }

    @Transactional
    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public Courses updateCourse(int courseId, String title, String description, String videoUrl) {
        Courses course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        course.setTitle(title);
        course.setDescription(description);
        course.setVideo(videoUrl);
        return courseRepository.save(course);
    }
}
