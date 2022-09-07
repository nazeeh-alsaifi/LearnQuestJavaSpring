package com.baeldung.service;

import java.util.Collection;

import com.baeldung.core.Student;

public interface StudentService {
	
	Student get(long id);
	Collection<Student> getAllStudents();

}
