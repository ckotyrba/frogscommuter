package com.company;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import com.company.Level.Level;
import com.company.Level.LevelLoader;
import com.company.gui.GameFieldDrawer;

public class Application extends JFrame {

    private GameFieldDrawer spielFeld;


    public Application() {
        initUI();
    }

    private void initUI() {
        Level level = LevelLoader.parseLevel(new File("levels/level1.actors1"), new File("levels/level1.actors2"));
        Level background = LevelLoader.parseLevel(new File("levels/level1.background"));
        spielFeld = new GameFieldDrawer(level, background);
//        MouseListener mouseListener = new MouseListener(spielFeld, new GameController(level, new ArrayList<>()));
//        spielFeld.addMouseListener(mouseListener);
//        spielFeld.addMouseMotionListener(mouseListener);
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