package com.student.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.student.core.Student;
import com.student.repository.StudentRepository;

@Named
public class StudentServiceImpl implements StudentService {

	// @PersistenceContext
	// private EntityManager em;

	@Inject
	private StudentRepository repo;

	@Override
	public Student get(long id) {
		return repo.getOne(id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		return repo.findAll();
	}

	@Override
	public Collection<Student> getStudentsByDepartment(String department) {
		return repo.findByDept(department);
	}

}
