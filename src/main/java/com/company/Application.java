package com.company;

import java.awt.*;

import javax.swing.*;

import com.company.Level.Level;
import com.company.Level.LevelLoader;
import com.company.gui.GameFieldDrawer;
import com.company.gui.KeyListener;
import com.company.gui.MouseListener;

public class Application extends JFrame {

    private GameFieldDrawer spielFeld;


    public Application() {
        initUI();
    }

    private void initUI() {
        Level level = LevelLoader.getLevel1();

        spielFeld = new GameFieldDrawer(level, this);
        MouseListener mouseListener = new MouseListener(spielFeld);
        spielFeld.addMouseListener(mouseListener);
        spielFeld.addMouseMotionListener(mouseListener);
        KeyListener keyListener = new KeyListener(spielFeld, spielFeld.getGameController());
        addKeyListener(keyListener);
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