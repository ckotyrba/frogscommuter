package com.company.figures;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class WaterLilyGreen extends Container {

    public WaterLilyGreen(Frog content) {
        super(createImage(), content);
    }

    public WaterLilyGreen() {
        super(createImage());
    }

    private static Image createImage() {
        Image lily = new ImageIcon("images/Seerose.png").getImage();
        Image flag = new ImageIcon("images/Fahne grün.png").getImage();
        BufferedImage bufferedImage = new BufferedImage(PIXEL_SIZE, PIXEL_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
        bufferedImage.getGraphics().drawImage(lily, 0, 0, null);
        bufferedImage.getGraphics().drawImage(flag, 0, 0, null);
        return bufferedImage;
    }

    public boolean finished() {
        return getContent()!=null && getContent() instanceof FroschGrün;
    }
}
