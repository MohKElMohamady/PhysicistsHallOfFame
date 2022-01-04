package com.frommstein.physicistsdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhysicistsDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhysicistsDataServiceApplication.class, args);
	}

}
