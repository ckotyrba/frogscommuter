package com.company.Level;

import com.company.figures.Container;
import com.company.figures.Item;

public class Level {

    Item background[][];
    Container containers[][];

    public Level(int sizeX, int sizeY) {
        background = new Item[sizeY][sizeX];
        containers = new Container[sizeY][sizeX];
    }

    public void setBackground(int x, int y, Item item) {
        background[y][x] = item;
    }

    public void setContainer(int x, int y, Container item) {
        containers[y][x] = item;
    }

    public Item getBackground(int x, int y) {
        return background[y][x];
    }

    public Container getContainer(int x, int y) {
        return containers[y][x];
    }

    public int getSizeX() {
        return background[0].length;
    }

    public int getSizeY() {
        return background.length;
    }

}
