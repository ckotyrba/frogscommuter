package com.company.gui;

import static com.company.figures.Item.PIXEL_SIZE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import com.company.Level.Level;
import com.company.Level.LevelLoader;
import com.company.figures.Item;

public class SpielFeld extends JPanel {

    private static final int ZOOM = 4;
    public static final int FPS = (int) (TimeUnit.SECONDS.toMillis(1) / 60);

    private final Level backgroundLevel;
    private final Level actorLevel;
    private final FPSCalculator fpsCalculator = new FPSCalculator();
    private final MouseListener mouseListener = new MouseListener(this);
    private Graphics graphics;

    public SpielFeld(File backgroundLevel, File actorLevel) {
        setBackground(new Color(82, 55, 201, 168));
        this.backgroundLevel = LevelLoader.parseLevel(backgroundLevel);
        this.actorLevel = LevelLoader.parseLevel(actorLevel);
        checkLevelSize();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        new Timer(FPS, this::timerEvent).start();
        new Timer(FPS, fpsCalculator).start();
    }

    private void checkLevelSize() {
        if (backgroundLevel.getSizeX() != actorLevel.getSizeX()) {
            throw new IllegalStateException("background sizeX: " + backgroundLevel.getSizeX() + "!= actorlevel sizeX: " + actorLevel.getSizeX());
        }
        if (backgroundLevel.getSizeY() != actorLevel.getSizeY()) {
            throw new IllegalStateException("background sizeY: " + backgroundLevel.getSizeY() + "!= actorlevel sizeY: " + actorLevel.getSizeY());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.graphics = g;
        for (int y = 0; y < backgroundLevel.getSizeY(); y++) {
            for (int x = 0; x < backgroundLevel.getSizeX(); x++) {
                drawImage(backgroundLevel.getItem(x, y).getImage(), x * getFieldSize(), y * getFieldSize());
                drawImage(actorLevel.getItem(x, y).getImage(), x * getFieldSize(), y * getFieldSize());
            }
        }
        mouseListener.drawDraggedIfNecessary();

        g.setColor(new Color(255, 0, 0));
        g.drawString("" + fpsCalculator.getFPS(), 100, 100);
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, getFieldSize(), getFieldSize(), null);
    }

    public void drawImageCentered(Image image, int x, int y) {
        graphics.drawImage(image, x - (getFieldSize() / 2), y - (getFieldSize() / 2), getFieldSize(), getFieldSize(), null);
    }

    private static int getFieldSize() {
        return PIXEL_SIZE * ZOOM;
    }

    @Override
    public int getWidth() {
        return backgroundLevel.getSizeX() * getFieldSize();
    }

    @Override
    public int getHeight() {
        return backgroundLevel.getSizeY() * getFieldSize();
    }

    public List<Item> getSelectedItem(Point point) {
        int x = (int) (point.getX() / SpielFeld.getFieldSize());
        int y = (int) (point.getY() / SpielFeld.getFieldSize());

        ArrayList<Item> result = new ArrayList<>();
        result.add(backgroundLevel.getItem(x, y));
        result.add(actorLevel.getItem(x, y));
        return result;
    }

    public void timerEvent(ActionEvent event) {
        repaint();
    }
}
