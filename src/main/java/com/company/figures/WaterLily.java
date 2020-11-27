package com.company.figures;

import static com.company.Level.ItemFactory.getFileFromResource;

import java.awt.*;

public class WaterLily extends Container {

    private static Image image = getFileFromResource("/images/Seerose.png");

    public WaterLily(Frog content) {
        super(createImage(), content);
    }

    public WaterLily() {
        super(createImage());
    }


    private static Image createImage() {
        return image;
    }

    public boolean finished() {
        return true;
    }


    @Override
    public WaterLily copy() {
        return new WaterLily(getContent());
    }
}
