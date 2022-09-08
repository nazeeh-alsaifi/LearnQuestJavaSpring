package com.student.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import com.student.core.Student;
import com.student.dao.StudentDao;
@Named
public class StudentServiceImpl implements StudentService {
	@Inject
	private StudentDao studentDao;
	 
 
	@Override
	public Student get(long id) {
		return studentDao.getOne(id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		return studentDao.getAll();
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	 

}
