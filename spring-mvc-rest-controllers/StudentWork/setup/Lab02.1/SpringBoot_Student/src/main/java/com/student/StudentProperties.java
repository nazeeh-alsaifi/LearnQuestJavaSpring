package com.student;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
