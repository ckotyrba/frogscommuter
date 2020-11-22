package com.company.gamelogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.company.Level.Level;
import com.company.figures.FroschGrün;
import com.company.figures.FroschRot;
import com.company.figures.Item;

public class GameController {

    private final Level level;

    public GameController(Level level) {
        this.level = level;
    }

    public void dropSelectedItem(Point from, Point to, Item itemToDrop) {
        List<Item> selectedItems = level.getItems(to.x, to.y);
        if (containsFrog(selectedItems)) {
            return;
        }
        for (Item selectedItem : new ArrayList<>(selectedItems)) {
            if (selectedItem.allowDrop()) {
                selectedItems.add(itemToDrop);
                level.getItems(from.x, from.y).remove(itemToDrop);
                return;
            }
        }
    }

    private boolean containsFrog(List<Item> selectedItems) {
        for (Item selectedItem : selectedItems) {
            if (selectedItem instanceof FroschGrün || selectedItem instanceof FroschRot) {
                return true;
            }
        }
        return false;
    }

}
