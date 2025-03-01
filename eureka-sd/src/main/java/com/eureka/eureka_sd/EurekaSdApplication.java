package com.eureka.eureka_sd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaSdApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaSdApplication.class, args);
	}

}
