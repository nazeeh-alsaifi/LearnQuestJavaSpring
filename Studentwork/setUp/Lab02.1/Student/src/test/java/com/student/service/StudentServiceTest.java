package com.student.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.student.config.ApplicationConfig;
import com.student.core.Student;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=ApplicationConfig.class)
public class StudentServiceTest {

	// private ApplicationContext context;
	@Inject
	private StudentService service;

	@BeforeEach
	void setUp() {
		// this.context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		// this.service = this.context.getBean(StudentService.class);
		assertNotNull(this.service);
	}

	@Test
	void testGetOneStudent() {
		Student st1 = this.service.get(1);
		assertNotNull(st1);
		assertThat(st1.getFirstName(),equalTo("Eric"));
	}

	@Test
	void tesGetAll() {
		this.service.getAllStudents().forEach(s -> {
			System.out.println(s);
		});
		assertThat(this.service.getAllStudents().size(), equalTo(2));
	}
}
