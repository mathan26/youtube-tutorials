package com.mathan.java8.tutorials.streams.part_5_parallestrems;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
//        IntStream.rangeClosed(1, 10).forEach(n-> System.out.println("number is "+n));
//
//        IntStream.rangeClosed(1,10).parallel().forEach(n-> System.out.println("number is "+n));

        int max  = 100_000_000;
        // Sequential
        long t0 = System.nanoTime();
        long sumSeq = IntStream.rangeClosed(1, max).sum();
        long t1 = System.nanoTime();
        System.out.println("Sequential sum = " + sumSeq +
                " took " + TimeUnit.NANOSECONDS.toMillis(t1 - t0) + " ms");

        // Parallel
        long t2 = System.nanoTime();
        long sumPar = IntStream.rangeClosed(1, max).parallel().sum();
        long t3 = System.nanoTime();
        System.out.println("Parallel sum = " + sumPar +
                " took " + TimeUnit.NANOSECONDS.toMillis(t3 - t2) + " ms");
    }
}
