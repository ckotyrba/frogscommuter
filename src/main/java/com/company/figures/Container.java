package com.company.figures;

import java.awt.*;

public class Container implements Item {

    private final Image image;
    private Frog content;

    public Container(Image image, Frog content) {
        this.image = image;
        this.content = content;
    }

    public Container(Image image) {
        this.image = image;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public Frog getContent() {
        return content;
    }

    public void setContent(Frog content) {
        this.content = content;
    }

    public boolean finished() {
        return true;
    }

    public Container copy() {
        return new Container(image, content);
    }
}
