package com.tech.with.mathan.sqs_spring_boot_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqsSpringBootDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(SqsSpringBootDemoApplication.class);

	public static void main(String[] args) {
		log.info("Starting SqsSpringBootDemoApplication");
		SpringApplication.run(SqsSpringBootDemoApplication.class, args);
	}

}
