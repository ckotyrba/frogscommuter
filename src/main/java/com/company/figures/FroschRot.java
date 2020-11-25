package com.company.figures;

import java.awt.*;

import javax.swing.*;

public class FroschRot implements Frog {

    private static final Image image = new ImageIcon("images/frog_red.png").getImage();

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean allowDrag() {
        return true;
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
