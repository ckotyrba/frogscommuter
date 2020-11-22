package com.company.figures;

import java.awt.*;

public class Container implements Item {

    private final Image image;
    private Item container;

    public Container(Image image) {
        this.image = image;
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
