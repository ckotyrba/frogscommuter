package com.company.gui;

public class FPSCalculator {

    private int counter = 0;
    private long start = System.currentTimeMillis();
    private int fps = 0;

    private void resetCounters() {
        counter = 0;
        start = System.currentTimeMillis();
    }

    private long millisPassed() {
        return System.currentTimeMillis() - start;
    }

    public int getFPS() {
        return fps;
    }

    public void update() {
        if (millisPassed() < 1000) {
            counter++;
        } else {
            fps = counter;
            resetCounters();
        }
    }
}
