package com.student.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.core.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Collection<Student> findByDept(String department);

}
