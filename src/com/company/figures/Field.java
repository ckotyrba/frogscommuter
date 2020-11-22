package com.company.figures;

import java.awt.*;
import java.io.File;

import javax.swing.*;

public class Field implements Item {

    private Image image;

    private Item container;

    public Field(File imageFile) {
        image = new ImageIcon(imageFile.getAbsolutePath()).getImage();
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean allowDrop() {
        return container == null;
    }

    public void setContainer(Item container) {
        this.container = container;
    }
}
