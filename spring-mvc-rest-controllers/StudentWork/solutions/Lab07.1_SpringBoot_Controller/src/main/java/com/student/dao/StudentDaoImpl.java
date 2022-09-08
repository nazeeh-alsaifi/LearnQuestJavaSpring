package com.student.dao;
 
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.inject.Named;

import com.student.core.College;
import com.student.core.Student;
@Named
public class StudentDaoImpl implements StudentDao {
 
	private Map<Long, Student> students;
	private NavigableMap<Long,College>colleges;
	{
		students = new HashMap<>();
		students.put(1L, new Student(1L, "Eric", "Colbert", "English Literature", 145.00));
		students.put(2L, new Student(2L,"Mary", "Contrary", "Physics", 155.00));
		students.put(3L, new Student(3L,"Jason", "Stewart", "English Literature", 145.00));
		students.put(4L, new Student(4L,"Ester","Freeman", "English Literature", 145.00));
		students.put(5L, new Student(5L,"Ann","Mouvier", "French", 125.00));
		
		colleges = new TreeMap<Long, College>();
		colleges.put(2L, new College("Texas State University", "601 University Dr", "San Marcos", "Texas"));
		colleges.put(4L, new College("University of South Florida", "4202 E Fowler Ave", "Tampa", "Florida"));
		colleges.put(6L, new College("Boston College", "140 Commonwealth Avenue", "Chestnut Hill", "Massachusetts"));
		colleges.put(Long.MAX_VALUE, new College("Tulane", "6823 St Charles Ave", "New Orleans", "Louisiana"));
	}
	@Override
	public Student getOne(long id) {
		Student student =  students.get(id);
		student.setCollege(colleges.ceilingEntry(student.getId()).getValue());
		return student;
	}
	@Override
	public Collection<Student> getAll() {
		Collection<Student> studentList = students.values().stream()
				.map(p-> {
					p.setCollege(colleges.ceilingEntry(p.getId()).getValue());
					return p;
				}).collect(Collectors.toList());
		return studentList;
	}
	@Override
	public void add(Student student) {
		long id = students.keySet().stream().count();
		id++;
		student.setId(id);
		students.put(id, student);	
	}
	
	
 
}
