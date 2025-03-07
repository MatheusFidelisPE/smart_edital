package com.llm.llmcommunication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LlmcommunicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LlmcommunicationApplication.class, args);
	}

}
