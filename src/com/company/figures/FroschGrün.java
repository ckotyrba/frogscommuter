package com.company.figures;

import java.awt.*;

import javax.swing.*;

public class FroschGrün implements Item {

    private static final Image image = new ImageIcon("images/frog_green.png").getImage();

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean allowDrag() {
        return true;
    }
}
