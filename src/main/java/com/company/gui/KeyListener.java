package com.company.gui;

import java.awt.event.KeyEvent;

import com.company.gamelogic.AutoPlay;
import com.company.gamelogic.GameController;

public class KeyListener implements java.awt.event.KeyListener {

    private final GameController gameController;
    private GameFieldDrawer gameFieldDrawer;
    private AutoPlay autoPlay;

    public KeyListener(GameFieldDrawer gameFieldDrawer, GameController gameController) {
        this.gameFieldDrawer = gameFieldDrawer;
        this.gameController = gameController;
        this.autoPlay = new AutoPlay(gameController);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        if (e.getKeyChar() == 'r') {
            gameFieldDrawer.resetLevel();
        } else if (e.getKeyChar() == 'z') {
            gameController.unDoLastJump();
            gameFieldDrawer.repaint();
        } else if (e.getKeyChar() == 'h') {
            autoPlay.doNextMove();
            gameFieldDrawer.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
