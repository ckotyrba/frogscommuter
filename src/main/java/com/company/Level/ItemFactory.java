package com.company.Level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ItemFactory {

    public static BufferedImage getFileFromResource(String fileName) {
        try {
            return ImageIO.read(ItemFactory.class.getResource(fileName));
        } catch (IOException e) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
    }
}
