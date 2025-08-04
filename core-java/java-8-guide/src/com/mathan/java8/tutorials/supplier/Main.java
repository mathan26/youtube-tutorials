package com.mathan.java8.tutorials.supplier;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Supplier<String> messageSupplier = () -> "Hello from Supplier";
        System.out.println(messageSupplier.get());

        Supplier<Integer> random = ()-> new Random().nextInt(100);
        System.out.println(random.get());

        Supplier<Double> randomSupplier = () -> Math.random();
        Stream.generate(randomSupplier).limit(5).forEach(System.out::println);


    }
}
