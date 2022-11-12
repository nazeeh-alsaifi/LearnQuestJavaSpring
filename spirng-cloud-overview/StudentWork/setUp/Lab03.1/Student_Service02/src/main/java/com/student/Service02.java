package com.student;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class Service02 {
	@Autowired
	private EurekaClient discoveryClient;

	private static Logger logger = LoggerFactory.getLogger(Service02.class);

	public static void main(String[] args) {
		SpringApplication.run(Service02.class, args);
	}

	@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public String index() {
		List<Application> applications = discoveryClient.getApplications().getRegisteredApplications();

		for (Application application : applications) {
			List<InstanceInfo> applicationsInstances = application.getInstances();
			for (InstanceInfo applicationsInstance : applicationsInstances) {
				String vip = applicationsInstance.getVIPAddress();
				String name = applicationsInstance.getAppName();
				String url = applicationsInstance.getHomePageUrl();
				logger.warn(name + ": " + url + ":" + vip);
			}
		}
		return "This is  a Spring Boot application";
	}

	@Inject
	private FirstAscentDAO dao;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String get(@PathVariable("id") Long id) {
		return dao.get(id);
	}

}