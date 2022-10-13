package com.student.service;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.student.core.Student;
 
@Named
public class StudentServiceImpl implements StudentService {
 
	//TODO
	 
	@Override
	public Student get(long id) {
		//TODO
		return null;
	}

	@Override
	public Collection<Student> getAllStudents() {
 		//TODO
		return null;
 	}

 
}
