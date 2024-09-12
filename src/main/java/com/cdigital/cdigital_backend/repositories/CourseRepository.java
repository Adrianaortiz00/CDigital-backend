package com.cdigital.cdigital_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdigital.cdigital_backend.models.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Integer> {
}
