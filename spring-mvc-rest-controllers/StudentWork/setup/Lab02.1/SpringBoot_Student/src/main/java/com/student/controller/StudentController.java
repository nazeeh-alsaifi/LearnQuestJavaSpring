package com.student.controller;

import java.io.Console;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String getMessage(@RequestHeader("user-agent") String agent) {
		// return message;
		return properties.getGreetings() + " " + agent;
	}

	// we can get rid of the produces argument because its the default value
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getAll() {
		return service.getAllStudents();
	}

	@GetMapping("/{id}")
	public Student getStudent(@PathVariable("id") long id) {
		return service.get(id);
	}

	@GetMapping("/single")
	public Student getSingleStudent(@RequestParam("id") Optional<Long> optional) {
		return service.get(optional.orElse(1l));
	}

	@GetMapping("/search/{department}")
	public Collection<Student> getStudentsPerDepartment(@PathVariable("department") String department,
			@RequestParam("name") Optional<String> optionalLastNameLike) {
		return service.getAllStudentsInDepartment(department, optionalLastNameLike.orElse(""));
	}

	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		System.out.println(student);
		service.add(student);
		if (student.getId() > 0) {
			URI addStudent = URI.create("/collage/student/" + student.getId());
			return ResponseEntity.accepted().location(addStudent).build();
		}
		return ResponseEntity.badRequest().build();
	}
}
