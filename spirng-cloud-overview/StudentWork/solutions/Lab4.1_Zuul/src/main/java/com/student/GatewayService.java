package com.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.student.filters.SimpleFilter;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayService {

  public static void main(String[] args) {
    SpringApplication.run(GatewayService.class, args);
  }

 
  
}
