package com.mathan.java8.tutorials.method_reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class Helper {
    public static void sayHello(String name) {
        System.out.println("Hello " + name);
    }
    public void printMessage(String msg) {
        System.out.println("Message: " + msg);
    }
}

class Student {
    String name = "Unknown";

    Student() {
        System.out.println("Student object created!");
    }
}

public class Main {
    public static void main(String[] args) {
//        Consumer<String> lamda = n -> Helper.sayHello(n);
//
//        lamda.accept("Mathan");

//       static  method reference

        Consumer<String> staticMethodReference = Helper::sayHello;
        staticMethodReference.accept("Kumar");


        //Instance Method Reference (Specific Object)
        Helper helper = new Helper();

        Consumer<String> instanceMethodReference = helper::printMessage;
        instanceMethodReference.accept("This message is from instance method reference");

//      Instance Method (Arbitrary Object of Type)
        List<String> names = Arrays.asList("banana", "apple", "cherry");
        names.sort((a,b)->a.compareToIgnoreCase(b));
        System.out.println("Sorted with Lambda: " + names);

        //reset
        names = Arrays.asList("banana", "apple", "cherry");

        names.sort(String::compareToIgnoreCase);
        System.out.println("method reference Lambda: " + names);

//        Constructor Reference

        // Lambda
        Supplier<Student> lambda = () -> new Student();
        Student s1 = lambda.get();

        // Constructor reference way
        Supplier<Student> methodRef = Student::new;
        Student s2 = methodRef.get();

    }
}
