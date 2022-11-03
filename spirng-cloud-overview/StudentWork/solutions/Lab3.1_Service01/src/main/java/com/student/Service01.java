package com.student;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

 

 
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RibbonClient(name = "service-two")
public class Service01 {
	public static void main(String[] args) {
          SpringApplication.run(Service01.class, args);
    }
 
	@RequestMapping("/")
    public String index() {
         return "This is  a Spring Boot application";
    }

	@Inject
	private MountainDAO dao;
	 

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mountain get(@PathVariable("id") Long id) {
		Mountain c = dao.get(id);
		String url = "http://service-two/{id}";
	    String str = this.restTemplate().getForObject(url, String.class, id);
        c.setFirstAscent(str);
		return c;
	}
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
	    return new RestTemplate();
	}

	 
}