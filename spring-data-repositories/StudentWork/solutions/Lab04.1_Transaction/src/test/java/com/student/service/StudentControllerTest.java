package com.student.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.student.StudentApplication;
import com.student.core.Course;
import com.student.core.Student;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = StudentApplication.class	)
public class StudentControllerTest {
	
	@Inject 
	private TestRestTemplate testRestTemplate;
	
	@Test
	void testSingle() {
		Student student =  testRestTemplate.getForObject("/student/{id}", Student.class, 1);
		 assertThat(student.getFirstName(), equalTo("Eric"));
	     assertThat(student.getSurname(), equalTo("Colbert"));
	}
	
	@Test
	void testAll() {
		ResponseEntity<Collection<Student>> response =   testRestTemplate.exchange("/student", HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Student>>() {});
		response.getBody().forEach(p-> {
				System.out.printf("%-10s %-10s%n",p.getFirstName(), p.getSurname());
		  });
	}
	
	@Test
	void testInDepartment() {
		ResponseEntity<Collection<Student>> response =   testRestTemplate.exchange("/student/search/department/{department}", HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Student>>() {}, "Chemistry");
		response.getBody().forEach(p-> {
				System.out.printf("%-10s %-10s%n",p.getFirstName(), p.getSurname());
		  });
	}
	
	@Test
	void testPost() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
		Student student = new Student();
		student.setDept("History");
		student.setFirstName("Fred");
		student.setSurname("Flintstone");
		student.setFees(201.00);
		Course course = new Course();
		course.setLocation("University of Miami");
		course.setStudent(student);
		course.setTitle("History of Carthage");
		student.getCourses().add(course);
		ResponseEntity<String> response = testRestTemplate.postForEntity("/student",new HttpEntity(student, headers), String.class);
		String url = response.getHeaders().get("location").get(0);
		Student attendee =  testRestTemplate.getForObject(url, Student.class);
		System.out.println(attendee);
		
	}
	
	

}
