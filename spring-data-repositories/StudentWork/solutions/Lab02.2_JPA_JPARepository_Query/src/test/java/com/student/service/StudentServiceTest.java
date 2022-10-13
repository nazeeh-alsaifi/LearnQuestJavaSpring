package com.student.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.student.core.Student;

@SpringBootTest
public class StudentServiceTest {
	
	@Inject 
	private StudentService service;
	
	@Test
	void testServiceSingle() {
		Student student =  service.get(1L);
		 assertThat(student.getFirstName(), equalTo("Eric"));
	     assertThat(student.getSurname(), equalTo("Colbert"));
	}
	
	@Test
	void testService() {
		Collection<Student> students =  service.getAllStudents();
		students.forEach(p-> {
				System.out.printf("%-10s %-10s%n",p.getFirstName(), p.getSurname());
		  });
	}

}
