package com.mathan.java8.tutorials.functional_interface;

public class Main {
    public static void main(String[] args) {
//        DemoInterface demoInterface = new DemoInterface() {
//            @Override
//            public void display() {
//                System.out.println("Interface implementation as a anonymous class");
//            }
//        };
//        demoInterface.display();


//        DemoInterface demoInterface1 = () -> {
//            System.out.println("Lambda implementation");
//        };
//
//        demoInterface1.display();


//        SumInterface sumInterface = ( a,  b) ->  a + b;
//
//        int ans = sumInterface.addTwoNumber(10, 20);
//        System.out.println(ans);


        Runnable runnable = ()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("Running in another thread");
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(" #### Running in another thread");
                }
            }
        });

        System.out.println("Main Thread");

        runnable.run();
        thread.start();
    }
}
