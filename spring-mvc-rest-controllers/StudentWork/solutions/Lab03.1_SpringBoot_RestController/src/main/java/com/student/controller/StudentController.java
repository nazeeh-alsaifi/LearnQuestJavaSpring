package com.student.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.core.Student;
import com.student.service.StudentService;

@RequestMapping("/student")
@RestController
public class StudentController {
	
	@Value("${message}")
	private String message;
	@Inject
	private StudentService studentService;
	
	@GetMapping("/msg")
	public String getMessage() {
		return message;
	}
	
	@GetMapping
	public Collection<Student> getAll() {
		return studentService.getAllStudents();
	}

}
