package com.student.controller;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String getMessage(@RequestHeader("user-agent") String userAgent) {
		return studentProperties.getGreeting() + " using " + userAgent;
	}
	
	@GetMapping
	public Collection<Student> getAll() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable("id") long id) {
		return studentService.get(id);
	}
	@GetMapping("/single")
	public Student getSingleStudent(@RequestParam("id") Optional<Long> optional) {
		return studentService.get(optional.orElse(1l));
	}
    //// http://localhost:8081/college/student/search/English%20Literature?name=t
	@GetMapping("/search/{department}")
	public Collection<Student> getStudentsPerDepartment(@PathVariable("department") String department, @RequestParam("name") Optional<String> optional) {
		return studentService.getAllStudentsInDepartment(department, optional.orElse(""));
	}
}
