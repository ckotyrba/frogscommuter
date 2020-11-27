package com.company.figures;

import java.awt.*;

import com.company.Level.ItemFactory;

public class Bridge extends Container {

    private static Image image = ItemFactory.getFileFromResource("/images/bridge.png");

    public Bridge() {
        super(image);
    }

}
