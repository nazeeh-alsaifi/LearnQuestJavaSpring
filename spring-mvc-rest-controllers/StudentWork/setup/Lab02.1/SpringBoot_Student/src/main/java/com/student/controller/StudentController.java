package com.student.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.StudentProperties;
import com.student.core.Student;
import com.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Value("${student.greetings}")
	private String message;

	@Inject
	private StudentService service;

	@Inject
	private StudentProperties properties;

	@GetMapping(path = "msg")
	public String getMessage() {
		// return message;
		return properties.getGreetings();
	}

	//we can get rid of the produces argument because its the default value
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getAll(){
		return service.getAllStudents();
	}

}
