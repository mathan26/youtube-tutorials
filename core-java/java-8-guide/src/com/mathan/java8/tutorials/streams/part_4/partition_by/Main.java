package com.mathan.java8.tutorials.streams.part_4.partition_by;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------Collectors.partitioningBy()-------------");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Map<Boolean, List<Integer>> partioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println(partioned);

        List<String> names = Arrays.asList("John", "Alice", "Bob", "David", "Anna");
        System.out.println(names.stream().collect(Collectors.partitioningBy(name -> name.length() > 3)));

    }
}
