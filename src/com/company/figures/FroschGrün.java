package com.company.figures;

import java.awt.*;

import javax.swing.*;

public class FroschGrün implements Item {

    private static final Image image = new ImageIcon("images/FroschGrün.png").getImage();

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean allowDrag() {
        System.out.println("frosch grün");
        return true;
    }
}
