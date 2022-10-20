package com.student.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		Student student = service.get(1L);
		assertThat(student.getFirstName(), equalTo("Eric"));
		assertThat(student.getSurname(), equalTo("Colbert"));
	}

	@Test
	void testService() {
		Collection<Student> students = service.getAllStudents();
		students.forEach(p -> {
			System.out.printf("%-10s %-10s%n", p.getFirstName(), p.getSurname());
		});
	}

	@Test
	void testGetStudentsByDepratment(){
		Collection<Student> students = service.getStudentsByDepartment("Chemistry");
		Boolean allSameDepartment = students.stream().allMatch(s -> s.getDept().equals("Chemistry") );

		students.forEach(p -> {
			System.out.printf("%-10s%n", p.getDept());
		});
		assertThat("all students same departments",allSameDepartment);
	}
}
