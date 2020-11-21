package com.company.figures;

import java.awt.*;

public interface Item {
    int PIXEL_SIZE = 32;

    Image getImage();

    default boolean allowDrag() {
        return false;
    }

    default boolean allowDrop() {
        return false;
    }
}
