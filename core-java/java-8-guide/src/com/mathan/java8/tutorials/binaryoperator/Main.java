package com.mathan.java8.tutorials.binaryoperator;

import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        BinaryOperator<Integer> findMin = (a, b)-> Math.min(a, b);

        System.out.println(findMin.apply(10, 20));
    }
}
