package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testGreeting() {
        App app = new App();
        assertEquals("Hello from Java CI/CD Pipeline on AWS!", app.getGreeting());
    }
}
