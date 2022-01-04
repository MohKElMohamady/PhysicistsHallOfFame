package com.frommstein.prizeinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class PrizeInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrizeInfoServiceApplication.class, args);
	}

}
