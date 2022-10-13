package com.student.core;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {
  /*
   * TABLE STUDENT
   (
   STUDENTID     BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   FIRSTNAME    VARCHAR(40) NOT NULL,
   LASTNAME    VARCHAR(10) NOT NULL,
   DEPARTMENT  VARCHAR(10) NOT NULL,
   FEES        DECIMAL(10,2),
   VERSION     BIGINT,
   CONSTRAINT  PK_STUDENT  PRIMARY KEY(STUDENTID)
   );
   */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="STUDENTID")
	private long id;
	@Column(name="FIRSTNAME")
	private String firstName;
	@Column(name="LASTNAME")
	private String surname;
	@Column(name="DEPARTMENT")
	private String dept;
	@Column(name="FEES")
	private Double fees;
	@JsonManagedReference
	@OneToMany(mappedBy="student", orphanRemoval=true, cascade = CascadeType.ALL)
	private Collection<Course> courses = new ArrayList<Course>();

	
	public Student() {
		super();
	}
	public Student(String firstName, String surname, String dept, Double fees) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.dept = dept;
		this.fees = fees;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", dept=" + dept + ", fees="
				+ fees + "]";
	}
	
	/*
	 * Methods to interact with Collection of Courses
	 */
	public Collection<Course> getCourses() {
		return courses;
	}
	public void add(Course course) {
		courses.add(course);
	}

}
