package com.company.gui;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    private GameFieldDrawer gameFieldDrawer;

    public KeyListener(GameFieldDrawer gameFieldDrawer) {
        this.gameFieldDrawer = gameFieldDrawer;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        if (e.getKeyChar() == 'r') {
            gameFieldDrawer.resetLevel();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
