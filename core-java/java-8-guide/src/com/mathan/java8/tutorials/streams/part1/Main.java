package com.mathan.java8.tutorials.streams.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evenNumbers = new ArrayList<>();
        for (Integer n : numbers) {
            if (n % 2 == 0) {
                evenNumbers.add(n);
            }
        }
        System.out.println(evenNumbers);

        List<Integer> result = numbers.stream().filter(n -> n % 2 == 0).toList();
        System.out.println("From Streams:"+ result);
    }
}
