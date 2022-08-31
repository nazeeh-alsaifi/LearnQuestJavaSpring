package com.student.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import com.student.service.StudentService;
import com.student.service.StudentServiceImpl;

@Configuration
public class ApplicationConfig {


    @Bean("studentService")
    StudentService getStudentService(@Value("2")int numberOfStudents){
        StudentServiceImpl service = new StudentServiceImpl();
        service.setStudentDao(getStudentDao());
        service.setNumberOfStudents(numberOfStudents);
        return service;
    }

    @Bean
    StudentDao getStudentDao(){
        return new StudentDaoImpl();
    }
    
}
