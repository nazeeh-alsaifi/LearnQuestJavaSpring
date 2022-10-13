package com.student.repository;

 
import org.springframework.data.rest.core.config.Projection;

import com.student.core.Student;

@Projection(name = "person", types = Student.class)
public interface Person {
	String getFirstName();
	String getSurname();
	String getId();

}
