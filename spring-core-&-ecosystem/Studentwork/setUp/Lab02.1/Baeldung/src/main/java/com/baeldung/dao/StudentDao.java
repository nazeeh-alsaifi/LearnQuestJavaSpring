package com.baeldung.dao;

import java.util.Collection;

import com.baeldung.core.Student;

public interface StudentDao {
	
	Student getOne(long id);
	Collection<Student> getAll();
}
