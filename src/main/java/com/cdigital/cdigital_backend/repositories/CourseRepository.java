package com.cdigital.cdigital_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdigital.cdigital_backend.models.Courses;

public interface CourseRepository extends JpaRepository<Courses, Integer> {
}
