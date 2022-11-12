package com.student;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.loadbalancer.ConfigurationBasedServerList;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "service-two")
@RestController
public class Service01 {
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private EurekaClient discoveryClient;

	private static Logger logger = LoggerFactory.getLogger(Service01.class);

	public static void main(String[] args) {
		SpringApplication.run(Service01.class, args);
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
	private MountainDAO dao;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mountain get(@PathVariable("id") Long id) {
		Mountain c = dao.get(id);
		String url = "http://service-two/{id}";
		String str = this.restTemplate().getForObject(url, String.class, id);
		c.setFirstAscent(str);
		return c;
	}

	@RequestMapping(value = "/test/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String testNaming(@PathVariable("name") String name) {
		Optional<ServiceInstance> instance = Optional.ofNullable(loadBalancerClient.choose(name));
		return instance.isPresent() ? instance.get().getUri().toString() : "not found";
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}