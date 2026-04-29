package com.example.ecsfargatedemo;

import java.time.Instant;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private final String applicationName;

  public HelloController(@Value("${spring.application.name}") String applicationName) {
    this.applicationName = applicationName;
  }

  @GetMapping("/api/hello")
  public Map<String, Object> hello() {
    return Map.of(
        "message", "Hello from Spring Boot on ECS Fargate",
        "application", applicationName,
        "timestamp", Instant.now().toString());
  }
}
