package com.company.figures;

import java.awt.*;

import javax.swing.*;

public class Bridge extends Container {

    private static Image image = new ImageIcon("images/bridge.png").getImage();

    public Bridge() {
        super(image);
    }

}
