package com.mathan.java8.tutorials.consumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printUpper = (str) -> System.out.println(str.toUpperCase());
        printUpper.accept("hello world");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        names.stream().forEach(name->System.out.println("Hello "+name));

    }
}
