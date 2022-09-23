package com.student;

import org.springframework.boot.context.properties.ConfigurationProperties;

//  this added in customization 2 lab4
@ConfigurationProperties("student")
public class StudentProperties {
    private String greetings;

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }

}
