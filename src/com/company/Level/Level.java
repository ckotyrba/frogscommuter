package com.company.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.company.figures.Field;
import com.company.figures.Item;

public class Level {

    ArrayList<ArrayList<ArrayList<Item>>> field;

    public Level(int sizeX, int sizeY) {
        field = new ArrayList<>(sizeY);

        for (int y = 0; y < sizeY; y++) {
            ArrayList<ArrayList<Item>> fieldX = createEmptyArrayListOfSize(sizeX);
            field.add(fieldX);
        }
    }

    private ArrayList<ArrayList<Item>> createEmptyArrayListOfSize(int sizeX) {
        ArrayList<ArrayList<Item>> fieldX = new ArrayList<>(sizeX);
        for (int x = 0; x < sizeX; x++) {
            fieldX.add(new ArrayList<>());
        }
        return fieldX;
    }

    public void setField(int x, int y, Item item) {
        ArrayList<Item> items = field.get(y).get(x);
        Optional<Item> first = items.stream().filter(field -> field instanceof Field).findFirst();
        first.ifPresent(value -> ((Field) value).setContainer(item));
        items.add(item);
    }

    public List<Item> getItems(int x, int y) {
        if (x < 0 || y < 0 ||
                y >= field.size() || x >= field.get(y).size()) {
            return Collections.emptyList();
        }
        return field.get(y).get(x);
    }

    public int getSizeX() {
        return field.get(0).size();
    }

    public int getSizeY() {
        return field.size();
    }
}
