package com.company.figures;

import java.awt.*;

import javax.swing.*;

public class WaterLily extends Container {

    private static Image image = new ImageIcon("images/Seerose.png").getImage();

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
