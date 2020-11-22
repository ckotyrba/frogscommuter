package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import com.company.Level.Level;
import com.company.Level.LevelLoader;
import com.company.gamelogic.GameController;
import com.company.gui.GameFieldDrawer;
import com.company.gui.MouseListener;

public class Application extends JFrame {

    public static final int FPS = (int) (TimeUnit.SECONDS.toMillis(1) / 60);
    private GameFieldDrawer spielFeld;


    public Application() {
        initUI();
        new Timer(FPS, this::gameLoop).start();
    }

    private void gameLoop(ActionEvent event) {
        spielFeld.repaint();

    }

    private void initUI() {
        Level level = LevelLoader.parseLevel(new File("levels/level1.actors"));
        Level background = LevelLoader.parseLevel(new File("levels/level1.background"));
        spielFeld = new GameFieldDrawer(level, background);
        MouseListener mouseListener = new MouseListener(spielFeld, new GameController(level));
        spielFeld.addMouseListener(mouseListener);
        spielFeld.addMouseMotionListener(mouseListener);
        add(spielFeld);

        getContentPane().setPreferredSize(new Dimension(spielFeld.getWidth(), spielFeld.getHeight()));
        pack();

        setTitle("Commuter Frogs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}