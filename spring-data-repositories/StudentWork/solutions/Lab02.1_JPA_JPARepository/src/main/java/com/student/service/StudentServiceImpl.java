package com.student.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;


import com.student.core.Student;
import com.student.repository.StudentRepository;
 
@Named
public class StudentServiceImpl implements StudentService {
 
	@Inject
	private StudentRepository studentRepository;
 
	 
	@Override
	public Student get(long id) {
		return studentRepository.getOne(id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		 return studentRepository.findAll();
 	}

	@Override
	public Collection<Student> getStudentsByDepartment(String department) {
		return studentRepository.findByDept(department);
	}

 
}
