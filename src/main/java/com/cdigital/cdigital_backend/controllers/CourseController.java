package com.cdigital.cdigital_backend.controllers;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdigital.cdigital_backend.models.Courses;
import com.cdigital.cdigital_backend.services.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    
    private CourseService courseService;

    @GetMapping
    public List<Courses> getCourses() {
        return courseService.getAllCourses();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Courses createCourse(@RequestParam String title, @RequestParam String description, @RequestParam String videoUrl, @RequestParam int userId) {
        return courseService.createCourse(title, description, videoUrl, userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Courses updateCourse(@PathVariable int id, @RequestParam String title, @RequestParam String description, @RequestParam String videoUrl) {
        return courseService.updateCourse(id, title, description, videoUrl);
    }
}
