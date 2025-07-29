package com.mathan.java8.tutorials.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<Integer> isEven = (num) -> num % 2==0;
        Predicate<Integer> isOdd = isEven.negate();

        System.out.println(isEven.test(4));
        System.out.println(isEven.test(5));


        Predicate<String> isEmpty = str -> str.isEmpty();
        System.out.println(isEmpty.test(""));

        List<Integer> nums = Arrays.asList(1,2,3,4,5,6);
        nums.stream().filter(n->n%2 ==0).forEach(System.out::println);

        Predicate<String> notEmpty = s -> !s.isEmpty();
        Predicate<String> hasA = s -> s.contains("a");
        System.out.println("predicate chaining");
        Predicate<String> valid = notEmpty.or(hasA);
        System.out.println(valid.test("xyz"));


    }
}
