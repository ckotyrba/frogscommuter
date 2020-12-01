package com.company.gui;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class MouseListener implements java.awt.event.MouseListener, MouseInputListener {

    private final GameFieldDrawer spielFeld;

    public MouseListener(GameFieldDrawer spielFeld) {
        this.spielFeld = spielFeld;
    }

    @Override
    public void mouseReleased(MouseEvent currentMouse) {
        if (!GameFieldDrawer.waitForClick)
            spielFeld.dragReleased(currentMouse.getPoint());
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (!GameFieldDrawer.waitForClick) {
            spielFeld.updateDragPosition(e.getPoint());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!GameFieldDrawer.waitForClick)
            spielFeld.startDrag(e.getPoint());
        else
            spielFeld.resetLevel();
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
