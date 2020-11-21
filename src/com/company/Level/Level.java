package com.company.Level;

import com.company.figures.Item;

public class Level {

    Item[][] field;

    public Level(int sizeX, int sizeY) {
        field = new Item[sizeY][sizeX];
    }

    public void setField(int x, int y, Item item) {
        field[y][x] = item;
    }

    public Item getItem(int x, int y) {
        return field[y][x];
    }

    public int getSizeX() {
        return field[1].length;
    }

    public int getSizeY() {
        return field.length;
    }
}
