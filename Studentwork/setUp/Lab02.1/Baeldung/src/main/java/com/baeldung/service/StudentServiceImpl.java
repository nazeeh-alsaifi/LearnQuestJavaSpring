package com.baeldung.service;

import java.util.Collection;
import java.util.stream.Collectors;

import com.baeldung.core.Student;
import com.baeldung.dao.StudentDao;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	private int numberOfStudents;
	
	public StudentServiceImpl(StudentDao studentDao, int numberOfStudents) {
		this.studentDao = studentDao;
		this.numberOfStudents = numberOfStudents;
	}

	@Override
	public Student get(long id) {
		return studentDao.getOne(id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		return studentDao.getAll().stream().limit(this.numberOfStudents).collect(Collectors.toList());
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

}
