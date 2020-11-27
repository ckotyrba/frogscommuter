package com.company.figures;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Empty implements Item {

    private Image image;

    public Empty() {
    }

    public Empty(Image image) {
        this.image = image;
    }

    @Override
    public Image getImage() {
        if (image == null) {
            return new BufferedImage(Item.PIXEL_SIZE, Item.PIXEL_SIZE, 2);
        }
        return image;
    }
}
