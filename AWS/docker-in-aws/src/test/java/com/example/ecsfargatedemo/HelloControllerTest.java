package com.example.ecsfargatedemo;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void returnsHelloMessage() throws Exception {
    mockMvc.perform(get("/api/hello"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message", is("Hello from Spring Boot on ECS Fargate")))
        .andExpect(jsonPath("$.application", is("ecs-fargate-demo")));
  }
}
