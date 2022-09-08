package com.student.dao;
 
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.student.core.Student;
@Named
public class StudentDaoImpl implements StudentDao {
 
	private Map<Long, Student> students;
	{
		students = new HashMap<>();
		students.put(1L, new Student(1L, "Eric", "Colbert", "English Literature", 145.00));
		students.put(2L, new Student(2L,"Mary", "Contrary", "Physiscs", 155.00));
		students.put(3L, new Student(3L,"Jason", "Stewart", "English Literature", 145.00));
		students.put(4L, new Student(4L,"Ester","Freeman", "English Literature", 145.00));
		students.put(5L, new Student(5L,"Ann","Mouvier", "French", 125.00));
		
	}
	@Override
	public Student getOne(long id) {
		return students.get(id);
	}
	@Override
	public Collection<Student> getAll() {
		return students.values();
	}

}
