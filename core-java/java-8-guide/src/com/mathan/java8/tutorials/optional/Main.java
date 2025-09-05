package com.mathan.java8.tutorials.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Mathan");
        System.out.println(name.get());
        System.out.println(name.isPresent());


        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent());


        String str = null;

        Optional<String> opt = Optional.ofNullable(str);
        if (opt.isPresent()) {
            System.out.println(str.length());
        }

        System.out.println("-----------------ifPresent() and orElse()------------");
        name.ifPresent(n -> System.out.println("value is present: "+n));

        System.out.println(opt.orElse("Another string"));

        System.out.println("-------orElseGet() vs orElse()---------");

        opt.orElse(test());
        name.orElseGet(()->test());

        System.out.println("---------map()-----------");


        Optional<String> name2 = Optional.of("Java");

        Optional<String> upper = name2.map(String::toUpperCase);
        System.out.println(upper.get());


        Map<Integer, String> users = new HashMap<>();
        users.put(1, "Mathan");

        Optional<String> userOpt = Optional.ofNullable(users.get(2));
        System.out.println(userOpt.orElse("user not found"));

    }

    private static String test() {
        System.out.println("inside  test method...");
        return "Default Value";
    }

}
