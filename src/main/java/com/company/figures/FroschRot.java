package com.company.figures;

import static com.company.Level.ItemFactory.getFileFromResource;

import java.awt.*;

public class FroschRot implements Frog {

    private static final Image image = getFileFromResource("/images/frog_red.png");

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean jumpPossible(int indexFrom, int indexTo) {
        return indexFrom > indexTo;
    }

    @Override
    public boolean jumpPossible(int indexFrom, int indexTo, Frog contentMiddle) {
        return jumpPossible(indexFrom,indexTo) && contentMiddle instanceof FroschGrün;
    }
}
