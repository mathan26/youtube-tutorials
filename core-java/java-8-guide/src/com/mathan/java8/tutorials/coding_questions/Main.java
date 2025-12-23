package com.mathan.java8.tutorials.coding_questions;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        Find second highest number in a list
        List<Integer> list = Arrays.asList(10, 20, 30, 40, 50, 50);

        Integer secondHighestSum = list.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().orElse(0);
        System.out.println(secondHighestSum);

//        Count frequency of each character in a string

        String str = "javajava";

        Map<Character, Long> result = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        System.out.println(result);

//        Find duplicate elements in a list

        List<Integer> list2 = Arrays.asList(1, 2, 3, 2, 4, 3, 5);
        Set<Integer> duplicates =  list2.stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e->e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println(duplicates);

//        Find first non-repeated character

        String input = "swiss";

        Character firstNonrepeated =  input.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e->e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("firstNonrepeated "+firstNonrepeated);


        System.out.println("Sort list of strings by length");

        List<String> list3 = Arrays.asList("java", "spring", "boot", "microservices");


        list3.sort(Comparator.comparingInt(String::length));
        System.out.println(list3);


        System.out.println("Employee class based questions");
        List<Employee> employeeList= EmployeeData.getEmployees();
        System.out.println(employeeList);

        System.out.println("Find highest paid employee");
        Employee employee = employeeList.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
        System.out.println(employee);

        System.out.println("Group employees by department");

       Map<String, List<Employee>> byDept =  employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(byDept);


        System.out.println("Average salary per department");
        Map<String, Double> avgSalary  =  employeeList.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.averagingDouble(Employee::getSalary)
                        )
                );
        System.out.println(avgSalary);

        System.out.println("Employee joined after 2020");
        employeeList.stream()
                .filter(e->e.getJoiningDate().isAfter(LocalDate.of(2020,1,1)))
                .forEach(System.out::println);
    }


}
