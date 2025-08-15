package com.mathan.java8.tutorials.predicate.bipredicate;

import java.util.Map;
import java.util.function.BiPredicate;

public class Main {
    public static void main(String[] args) {
        BiPredicate<Integer, Integer> bothEven = (a, b)-> a %2 ==0 && b %2==0;

        System.out.println(bothEven.test(2,4));
        System.out.println(bothEven.test(3,4));

        Map<String, Integer> people = Map.of(
                "Alex", 20,
                "Bob", 30,
                "Alice", 25
        );

        BiPredicate<String, Integer> condition =
                (name, age) -> name.startsWith("A") && age > 19;

        people.entrySet().stream()
                .filter(e -> condition.test(e.getKey(), e.getValue()))
                .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

    }
}
