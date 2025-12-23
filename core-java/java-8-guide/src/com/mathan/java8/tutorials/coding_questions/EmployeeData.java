package com.mathan.java8.tutorials.coding_questions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class EmployeeData {
    public static List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(1, "Mathan", "IT", 80000, LocalDate.of(2020, 1, 10)),
                new Employee(2, "Arun", "HR", 60000, LocalDate.of(2019, 3, 15)),
                new Employee(3, "Priya", "IT", 90000, LocalDate.of(2021, 6, 20)),
                new Employee(4, "Ravi", "Finance", 75000, LocalDate.of(2018, 8, 25)),
                new Employee(5, "Anu", "HR", 65000, LocalDate.of(2022, 2, 5))
        );
    }
}
