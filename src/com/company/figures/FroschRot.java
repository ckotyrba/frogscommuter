package com.company.figures;

import java.awt.*;

import javax.swing.*;

public class FroschRot implements Item {

    private static final Image image = new ImageIcon("images/frog_red.png").getImage();

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean allowDrag() {
        return true;
    }
}
