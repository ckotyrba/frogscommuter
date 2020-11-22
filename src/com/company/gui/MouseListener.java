package com.company.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.event.MouseInputListener;

import com.company.figures.Item;
import com.company.gamelogic.GameController;

public class MouseListener implements java.awt.event.MouseListener, MouseInputListener {

    private Point dragPositionFrom;
    private final GameFieldDrawer spielFeld;
    private final GameController controller;

    public MouseListener(GameFieldDrawer spielFeld, GameController controller) {
        this.spielFeld = spielFeld;
        this.controller = controller;
    }

    @Override
    public void mouseReleased(MouseEvent currentMouse) {
        controller.dropSelectedItem(
                spielFeld.getPointOfSelectedItem(dragPositionFrom),
                spielFeld.getPointOfSelectedItem(currentMouse.getPoint()),
                spielFeld.draggedItem);
        spielFeld.setDraggedItem(null);
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        spielFeld.setMousePosition(e.getPoint());
    }


    @Override
    public void mousePressed(MouseEvent e) {
        List<Item> selectedItems = spielFeld.getSelectedItems(e.getPoint());
        for (Item selectedItem : selectedItems) {
            if (selectedItem.allowDrag()) {
                spielFeld.setDraggedItem(selectedItem);
                spielFeld.setMousePosition(e.getPoint());
                dragPositionFrom = e.getPoint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
