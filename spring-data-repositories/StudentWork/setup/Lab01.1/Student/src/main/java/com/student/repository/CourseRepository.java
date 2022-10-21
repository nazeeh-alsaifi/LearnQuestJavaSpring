package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.student.core.Course;

@RepositoryRestResource(collectionResourceRel = "course",path = "courses")
public interface CourseRepository extends JpaRepository<Course, Long> {

}
