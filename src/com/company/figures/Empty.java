package com.company.figures;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Empty implements Item {
    @Override
    public Image getImage() {
        BufferedImage bufferedImage = new BufferedImage(PIXEL_SIZE, PIXEL_SIZE, BufferedImage.TYPE_INT_RGB);
//        Graphics graphics = bufferedImage.getGraphics();
//        graphics.setColor(new Color(255, 255, 255, 255));
//        graphics.fillRect(0,0,PIXEL_SIZE,PIXEL_SIZE);
        return bufferedImage;
    }
}
