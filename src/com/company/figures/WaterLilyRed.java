package com.company.figures;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class WaterLilyRed extends Container {

    public WaterLilyRed() {
        super(createImage());
    }

    private static Image createImage() {
        Image lily = new ImageIcon("images/Seerose.png").getImage();
        Image flag = new ImageIcon("images/Fahne rot.png").getImage();
        BufferedImage bufferedImage = new BufferedImage(PIXEL_SIZE, PIXEL_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
        bufferedImage.getGraphics().drawImage(lily, 0, 0, null);
        bufferedImage.getGraphics().drawImage(flag, 0, 0, null);
        return bufferedImage;
    }
}
