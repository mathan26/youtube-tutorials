package com.tech.mathan.spring_log_monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tech.mathan.spring_log_monitoring")
public class SpringLogMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLogMonitoringApplication.class, args);
	}

}
