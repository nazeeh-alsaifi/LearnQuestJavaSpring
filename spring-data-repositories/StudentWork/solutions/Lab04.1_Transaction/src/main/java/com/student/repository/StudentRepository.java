package com.student.repository;

import java.util.Collection;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.student.core.Student;
@RepositoryRestResource(collectionResourceRel = "student", path = "enrollments", excerptProjection = Person.class)
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	//Collection<Student> findByDept(String department);
	@Query("SELECT student FROM Student student where student.dept = :dept")
	Collection<Student> getByDept(@Param("dept") String department);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
	Student save(Student entity);

}
