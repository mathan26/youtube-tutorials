package com.mathan.java8.tutorials.streams.part_3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        forEach
        System.out.println("-----------forEach----------------");
        List<String> names = Arrays.asList("John", "Jane", "Jack");
        names.stream().forEach(name -> System.out.println(name));

        System.out.println("-----------reduction----------------");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        int sum = nums.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Sum is " + sum);
        int max = nums.stream().reduce(Integer::max).orElse(-1);
        System.out.println("Max " + max);

        System.out.println("-----------Collect----------------");

        List<String> uppercaseName = names.stream().map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Upper case names " + uppercaseName);

        Set<String> uppercaseNameSet = names.stream()
                .collect(Collectors.toSet());

        String joined = names.stream().collect(Collectors.joining("# "));
        System.out.println("Join Example " + joined);

        System.out.println("-----------Finding and matching----------------");

        Optional<String> first = names.stream().findFirst();
        System.out.println("First value: " + first.get());

        List<Integer> nums2 = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println("====anyMatch--------------");
        boolean hasEven = nums2.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("AnyMatch :" + hasEven);
        boolean isAllEven = nums2.stream().allMatch(n -> n % 2 == 0);
        System.out.println("AllMatch :" + isAllEven);

        System.out.println("--------------Counting and Statistics--------");
        long count = Stream.of(1, 2, 3, 4, 5, 6, 7).count();
        System.out.println("Count is :" + count);

        List<Integer> nums3 = Arrays.asList(10, 20, 5, 30);
        int min = nums3.stream().min(Integer::compare).get();
        int max2 = nums3.stream().max(Integer::compare).get();
        System.out.println("Min is " + min + " Max is " + max2);

        IntSummaryStatistics statistics = nums3.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("statistics: "+statistics);

    }
}
