package com.student.service;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Value;

import com.student.core.Student;
import com.student.dao.StudentDao;

@Named(value = "studentService")
@Singleton
public class StudentServiceImpl implements StudentService {
	@Inject @Named(value = "studentDao")
	private StudentDao studentDao;
	@Value("2")
	private int numberOfStudents;

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

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

}
