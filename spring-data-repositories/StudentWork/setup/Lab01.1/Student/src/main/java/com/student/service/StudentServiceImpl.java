package com.student.service;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.student.core.Student;

@Named
public class StudentServiceImpl implements StudentService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Student get(long id) {
		return em.find(Student.class, id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		return em.createQuery("SELECT student FROM Student student", Student.class).getResultList();
	}

}
