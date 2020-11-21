package com.company.figures;

import java.awt.*;

import javax.swing.*;

public class FroschRot implements Item {


    private static final Image image = new ImageIcon("images/froschrot.png").getImage();

    @Override
    public Image getImage() {
        return image;
    }

}
