package com.student;

 

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 

 
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Service03 {
	public static void main(String[] args) {
          SpringApplication.run(Service03.class, args);
    }
 
	@RequestMapping("/")
    public String index() {
         return "This is  a Spring Boot application";
    }

	@Inject
	private FirstAscentDAO dao;

	@GetMapping(value = "/{id}")
	public String get(@PathVariable("id") Long id) {
		return dao.get(id);
	}
	 
}