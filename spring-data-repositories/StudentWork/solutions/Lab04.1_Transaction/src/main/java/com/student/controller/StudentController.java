package com.student.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
 
 
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.core.Student;
import com.student.exception.MyException;
import com.student.repository.StudentRepository;
import com.student.service.StudentService;

 

@RequestMapping("/student")
@RestController
@CrossOrigin
public class StudentController {
	
	 
	@Inject
	private StudentService studentService;
	
	@Inject
	private StudentRepository studentRepository;
 
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getAll() {
		return studentService.getAllStudents();
	}
	
	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(@PathVariable("id") long id) {
		return studentService.get(id);
	}
	
	@GetMapping(path="/search/department", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getDepartments() {
		 return studentService.getAllStudents().stream().map(p-> p.getDept()).distinct().collect(Collectors.toList());
	}
	
	@GetMapping(path="/search/department/{department}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getStudentsByDepartment(@PathVariable ("department") String department) {
		 return studentService.getStudentsByDepartment(department);
	}
	
	@PostMapping
	@Transactional(rollbackFor = MyException.class)
	public ResponseEntity<String> add(@RequestBody Student student) throws MyException{
		studentRepository.save(student);
		if(student.getFees() > 200.00) {
			throw new MyException("Blow Up");
		}
		return ResponseEntity.accepted().header("location", "/student/"+ student.getId()).build();
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handle(Exception exception) {
		return ResponseEntity.badRequest().build();
	}
	 
}
