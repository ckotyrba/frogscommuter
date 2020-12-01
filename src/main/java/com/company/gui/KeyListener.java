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
        } else if (e.getKeyChar() == 'z') {
            gameFieldDrawer.getGameController().unDoLastJump();
        } else if (e.getKeyChar() == 'h') {
            gameFieldDrawer.getGameController().getAutoPlay().doNextMove();
            gameFieldDrawer.repaint();
//            autoPlay.startWithTimer(1000);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
