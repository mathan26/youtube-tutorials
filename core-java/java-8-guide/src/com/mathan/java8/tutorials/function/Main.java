package com.mathan.java8.tutorials.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Function<Integer, Integer> squre = (num) -> num * num;

        Function<String, Integer> lengthofString = (str) -> str.length();

        System.out.println(squre.apply(10));
        System.out.println(lengthofString.apply("Apple"));


        Function<Integer, Integer> multiply = x -> x * 2;
        Function<Integer, Integer> add = x -> x + 3;

        Function<Integer, Integer> combined = multiply.compose(add);
        System.out.println(combined.apply(10));


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Function<Integer, Integer> doubler = x -> x * 2;

        List<Integer> result = numbers.stream().map(doubler).collect(Collectors.toList());
        System.out.println(result);
    }
}
