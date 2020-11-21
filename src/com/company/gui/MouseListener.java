package com.company.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.event.MouseInputListener;

import com.company.figures.Item;

public class MouseListener implements java.awt.event.MouseListener, MouseInputListener {

    public Point mousePosition;
    public Item draggedItem;
    private Point dragPositionFrom;
    private SpielFeld spielFeld;

    public MouseListener(SpielFeld spielFeld) {
        this.spielFeld = spielFeld;
    }

    @Override
    public void mouseReleased(MouseEvent currentMouse) {
        spielFeld.dropSelectedItem(dragPositionFrom, currentMouse.getPoint(), draggedItem);
        draggedItem = null;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition = e.getPoint();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        List<Item> selectedItems = spielFeld.getSelectedItems(e.getPoint());
        for (Item selectedItem : selectedItems) {
            if (selectedItem.allowDrag()) {
                draggedItem = selectedItem;
                mousePosition = e.getPoint();
                dragPositionFrom = e.getPoint();
            }
        }
    }

    public void drawDraggedIfNecessary() {
        if (draggedItem != null && mousePosition != null) {
            spielFeld.drawImageCentered(draggedItem.getImage(), mousePosition.x, mousePosition.y);
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
