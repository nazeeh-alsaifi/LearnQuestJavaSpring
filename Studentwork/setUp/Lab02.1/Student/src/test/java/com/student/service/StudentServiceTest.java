package com.student.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StudentServiceTest {

	private ApplicationContext context;
	private StudentService service;

	@BeforeEach
	void setUp() {
		this.context = new ClassPathXmlApplicationContext("beans.xml");
		this.service = this.context.getBean("studentService", StudentServiceImpl.class);
		assertNotNull(this.service);

	}

	@Test
	void testGetOneStudent() {
		assertNotNull(((StudentServiceImpl) this.service).getStudentDao());
		// assertNotNull(this.service.get(1));
		// TODO
	}

	@Test
	void tesGetAll() {
		assertThat(this.service.getAllStudents().size(), equalTo(2));
		this.service.getAllStudents().forEach(s -> {
			System.out.println(s);
		});
	}
}
