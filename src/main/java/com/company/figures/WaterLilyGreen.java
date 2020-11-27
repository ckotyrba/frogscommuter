package com.company.figures;

import static com.company.Level.ItemFactory.getFileFromResource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterLilyGreen extends Container {

    public WaterLilyGreen(Frog content) {
        super(createImage(), content);
    }

    public WaterLilyGreen() {
        super(createImage());
    }

    private static Image createImage() {
        Image lily = getFileFromResource("/images/Seerose.png");
        Image flag = getFileFromResource("/images/Fahnegreen.png");
        BufferedImage bufferedImage = new BufferedImage(PIXEL_SIZE, PIXEL_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
        bufferedImage.getGraphics().drawImage(lily, 0, 0, null);
        bufferedImage.getGraphics().drawImage(flag, 0, 0, null);
        return bufferedImage;
    }

    public boolean finished() {
        return getContent() != null && getContent() instanceof FroschGrün;
    }

    @Override
    public WaterLilyGreen copy() {
        return new WaterLilyGreen(getContent());
    }
}
