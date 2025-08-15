package com.mathan.java8.tutorials.consumer.biconsumer;

import java.util.function.BiConsumer;

public class Main {
    public static void main(String[] args) {
        BiConsumer<String, Integer> print = (name, age) ->
                System.out.println("Name is " + name + " and age is " + age);

        print.accept("ABC", 20);
    }
}
