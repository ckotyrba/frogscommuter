package com.company.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FPSCalculator implements ActionListener {

    private int counter = 0;
    private long start = System.currentTimeMillis();
    private int fps = 0;


    @Override
    public void actionPerformed(ActionEvent e) {
        if (millisPassed() < 1000) {
            counter++;
        } else {
            fps = counter;
            resetCounters();
        }
    }

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
}
