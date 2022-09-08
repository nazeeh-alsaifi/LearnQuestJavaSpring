package com.student.controller;

import java.util.Collection;

import javax.inject.Inject;

 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.StudentProperties;
import com.student.core.Student;
import com.student.service.StudentService;

@RequestMapping("/student")
@RestController
public class StudentController {
	
	@Inject 
	private StudentProperties studentProperties;
	@Inject
	private StudentService studentService;
	
	@GetMapping(path="/msg")
	public String getMessage() {
		return studentProperties.getGreeting();
	}
	
	@GetMapping
	public Collection<Student> getAll() {
		return studentService.getAllStudents();
	}

}
