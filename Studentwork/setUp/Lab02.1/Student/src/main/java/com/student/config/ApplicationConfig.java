package com.student.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import com.student.service.StudentService;
import com.student.service.StudentServiceImpl;

@Configuration
@PropertySource(value="classpath:application.properties")
public class ApplicationConfig {

    @Bean("studentDao")
    StudentDao getStudentDao() {
        return new StudentDaoImpl();
    }

    @Bean("studentService")
    StudentService getStudentService(@Value("${max}") int numberOfStudents) {
        StudentServiceImpl service = new StudentServiceImpl();
        service.setStudentDao(getStudentDao());
        service.setNumberOfStudents(numberOfStudents);
        return service;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
