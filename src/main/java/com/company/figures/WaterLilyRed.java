package com.company.figures;

import static com.company.Level.ItemFactory.getFileFromResource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterLilyRed extends Container {

    public WaterLilyRed(Frog content) {
        super(createImage(), content);
    }

    public WaterLilyRed() {
        super(createImage());
    }

    private static Image createImage() {
        Image lily = getFileFromResource("/images/Seerose.png");
        Image flag = getFileFromResource("/images/Fahnerot.png");
        BufferedImage bufferedImage = new BufferedImage(PIXEL_SIZE, PIXEL_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
        bufferedImage.getGraphics().drawImage(lily, 0, 0, null);
        bufferedImage.getGraphics().drawImage(flag, 0, 0, null);
        return bufferedImage;
    }

    public boolean finished() {
        return getContent()!=null && getContent() instanceof FroschRot;
    }


    @Override
    public WaterLilyRed copy() {
        return new WaterLilyRed(getContent());
    }
}
