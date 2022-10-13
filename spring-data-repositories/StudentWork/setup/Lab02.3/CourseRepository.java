package com.student.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.student.core.Course;
 
 
public interface CourseRepository extends JpaRepository<Course, Long>{
	
 

}
