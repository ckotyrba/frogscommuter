package com.company.gui;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import com.company.figures.Item;

public class MouseListener implements java.awt.event.MouseListener, MouseInputListener {

    public Point mousePosition;
    public Item draggedItem;
    private SpielFeld spielFeld;

    public MouseListener(SpielFeld spielFeld) {
        this.spielFeld = spielFeld;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        draggedItem = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        draggedItem = spielFeld.getSelectedItem(e.getPoint());
    }

    public void drawDraggedIfNecessary() {

    }
}
