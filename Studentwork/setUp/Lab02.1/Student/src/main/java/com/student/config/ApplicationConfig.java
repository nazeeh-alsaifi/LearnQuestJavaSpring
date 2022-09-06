package com.student.config;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.student.dao.StudentDao;
import com.student.dao.StudentDaoImpl;
import com.student.service.StudentService;
import com.student.service.StudentServiceImpl;
import com.student.service.StudentServiceProxy;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class ApplicationConfig {

    @Value("${max}")
    private int numberOfStudents;

    @Bean("studentDao")
    StudentDao getStudentDao() {
        return new StudentDaoImpl();
    }

    @Bean("studentService")
    // @ConditionalOnProperty(value = "env", havingValue = "prod", matchIfMissing = true)
    @Conditional(StudentConditional_Prod.class)
    StudentService getStudentService() {
        StudentServiceImpl service = new StudentServiceImpl();
        service.setStudentDao(getStudentDao());
        service.setNumberOfStudents(this.numberOfStudents);
        return service;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "studentService")
    // @ConditionalOnProperty(value = "env", havingValue = "test", matchIfMissing = true)
    @Conditional(StudentConditional_Test.class)
    StudentService serviceTest() {
        StudentServiceImpl service = new StudentServiceImpl();
        service.setStudentDao(getStudentDao());
        service.setNumberOfStudents(this.numberOfStudents);
        return new StudentServiceProxy(service);
    }

}
