package com.mathan.java8.tutorials.unaryoperator;

import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        UnaryOperator<Integer> square = (num) -> num * num;

        System.out.println(square.apply(10));
    }
}
