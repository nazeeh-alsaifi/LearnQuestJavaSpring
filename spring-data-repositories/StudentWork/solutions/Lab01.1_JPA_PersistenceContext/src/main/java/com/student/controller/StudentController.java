package com.student.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.core.Student;
import com.student.service.StudentService;

 

@RequestMapping("/student")
@RestController
@CrossOrigin
public class StudentController {
	
	 
	@Inject
	private StudentService studentService;
 
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getAll() {
		return studentService.getAllStudents();
	}
	
	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(@PathVariable("id") long id) {
		return studentService.get(id);
	}
	
	 
}
