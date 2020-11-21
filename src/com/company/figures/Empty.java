package com.company.figures;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;

public class Empty implements Item {

    private Image image;

    public Empty() {
    }

    public Empty(File imageFile) {
        this.image = new ImageIcon(imageFile.getAbsolutePath()).getImage();
    }

    @Override
    public Image getImage() {
        if (image == null) {
            return new BufferedImage(Item.PIXEL_SIZE, Item.PIXEL_SIZE, 2);
        }
        return image;
    }
}
