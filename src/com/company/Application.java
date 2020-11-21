package com.company;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import com.company.gui.SpielFeld;

public class Application extends JFrame {

    public Application() {

        initUI();
    }

    private void initUI() {
        SpielFeld spielFeld = new SpielFeld(new File("levels/level1.background"), new File("levels/level1.actors"));
        spielFeld.setBackground(new Color(0, 0, 0));
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