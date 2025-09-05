package com.mathan.java8.tutorials.streams.part_4.grouping_by;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "David", "Anna");

        Map<Character, List<String>> result = names.stream().collect(Collectors.groupingBy(name -> name.charAt(0)));
        System.out.println(result);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println(numbers.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "Even" : "Odd")));

        List<Employee> employees = Arrays.asList(
                new Employee("John", "IT"),
                new Employee("Alice", "HR"),
                new Employee("Bob", "IT"),
                new Employee("David", "Finance"),
                new Employee("Anna", "HR")
        );
        Map<String, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(e -> e.dept));
        System.out.println(grouped);

        System.out.println("----Multi Level Grouping------------------");


        List<Student> students = Arrays.asList(
                new Student("John", "A", "Male"),
                new Student("Alice", "A", "Female"),
                new Student("Bob", "B", "Male"),
                new Student("David", "A", "Male"),
                new Student("Anna", "B", "Female")
        );

        System.out.println(students.stream()
                .collect(Collectors.groupingBy(s -> s.grade,
                        Collectors.groupingBy(s -> s.gender)
                )));
        //Map<String, Map<String, List<Student>>> grouped


//        //Downstream Collectors

        Map<String, Long> countByDept = employees.stream()
                .collect(Collectors.groupingBy(e -> e.dept, Collectors.counting()));
        System.out.println(countByDept);

    }
}
