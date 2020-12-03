package com.company;

public class Measurement {

    public static void measure(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        System.out.println("duration: " + (System.currentTimeMillis() - start));
    }
}
