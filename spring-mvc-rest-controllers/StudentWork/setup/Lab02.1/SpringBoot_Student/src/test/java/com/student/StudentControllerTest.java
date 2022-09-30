package com.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.student.core.Student;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;
import java.util.List;

public class StudentControllerTest {

	private HttpHeaders headers;
	private String URL;

	@BeforeEach
	void setUp() {
		URL = "http://localhost:8081/collage/student";
		headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
		headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
	}

	@Test
	void testAdd() {
		Student student = new Student(0, "Susan", "Doubtfire", "French", 75.00);

		ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(this.URL,
				new HttpEntity<Student>(student, this.headers), String.class);
		String locationUri = responseEntity.getHeaders().get("location").get(0);

		String newEntityUrl = "http://localhost:8081" + locationUri;
		ResponseEntity<Student> newResponseEntity = new RestTemplate().getForEntity(newEntityUrl, Student.class);
		Student newStudent = newResponseEntity.getBody();

		assertThat(student.getFirstName(), equalTo(newStudent.getFirstName()));
		assertThat(student.getDept(), equalTo(newStudent.getDept()));
		assertThat(student.getSurname(), equalTo(newStudent.getSurname()));
		assertThat(student.getFees(), equalTo(newStudent.getFees()));

	}

	@Test
	void testAddNegative() {
		Student student = new Student(0, null, "Doubtfire", "French", 75.00);

		Assertions.assertThrows(HttpClientErrorException.class, () -> {
			ResponseEntity<?> responseEntity = new RestTemplate().postForEntity(URL,
					new HttpEntity<Student>(student, this.headers), String.class);
			assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
		});

	}

	@Test
	void testGetAll() {
		ResponseEntity<Collection<Student>> responseEntity = new RestTemplate().exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<Collection<Student>>() {
				});
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

		Collection<Student> body = responseEntity.getBody();
		if (body != null)
			body.stream().forEach(s -> System.out.println(s));
	}

	@Test
	void testGetOnePathParam() {
		ResponseEntity<Student> responseEntity = new RestTemplate().getForEntity(URL+ "/{id}", Student.class, 1);
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
		System.out.println(responseEntity.getBody());
	}

	@Test
	void testGetOneRequestParam() {
		ResponseEntity<Student> responseEntity = new RestTemplate().getForEntity(URL + "/single?id={id}", Student.class, 1);
		assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
		System.out.println(responseEntity.getBody());
	}

}
