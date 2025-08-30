package com.mathan.java8.tutorials.streams.part_2;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        map
        System.out.println("----------map-----------------");

        List<String> names = Arrays.asList("john", "peter", "alice");
        //lazy
        Stream<String> stream = names.stream().map(s -> {
            System.out.println("test");
            return s.toUpperCase();
        });
        stream.forEach(System.out::println);
//        stream.forEach(System.out::println); stream closed error


        //filter
        List<Integer> nums = Arrays.asList(10, 25, 30, 45, 60);
        System.out.println("----------filter-----------------");
        nums.stream().filter(a -> a % 2 == 0).forEach(System.out::println);

        System.out.println("----------flatmap-----------------");

        List<List<String>> nested = Arrays.asList(Arrays.asList("A", "B"), Arrays.asList("C", "D"), Arrays.asList("E", "F"));
        System.out.println("before output:" + nested);
        List<String> result = nested.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println("after stream output" + result);

        System.out.println("----------distinct-----------------");
        List<Integer> nums2 = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        nums2.stream().distinct().forEach(System.out::println);

        System.out.println("----------sorted-----------------");
        List<String> cities = Arrays.asList("London", "Delhi", "New York", "Paris");
        cities.stream().sorted().forEach(System.out::println);

        System.out.println("----------limit-----------------");

        List<Integer> nums3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        nums3.stream().limit(5).forEach(System.out::println);

        System.out.println("----------skip-----------------");
        List<Integer> nums4 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        nums4.stream().skip(2).forEach(System.out::println);

        System.out.println("----------peek-----------------");
        List<Integer> nums5 = Arrays.asList(1, 2, 3, 4, 5);

        nums5.stream()
                .peek(n -> System.out.println("Before filter: " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("After filter: " + n))
                .map(n -> n * n)
                .peek(n -> System.out.println("After map: " + n))
                .forEach(System.out::println);
    }
}
