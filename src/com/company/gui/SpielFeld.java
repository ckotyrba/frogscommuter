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

    private final Level level;
    private final FPSCalculator fpsCalculator = new FPSCalculator();
    private final MouseListener mouseListener = new MouseListener(this);
    private Graphics graphics;

    public SpielFeld(File level, File actorLevel) {
        setBackground(new Color(82, 55, 201, 168));
        this.level = LevelLoader.parseLevel(level, actorLevel);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        new Timer(FPS, this::timerEvent).start();
        new Timer(FPS, fpsCalculator).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.graphics = g;
        for (int y = 0; y < level.getSizeY(); y++) {
            for (int x = 0; x < level.getSizeX(); x++) {
                for (Item item : level.getItems(x, y)) {
                    drawImage(item.getImage(), x * getFieldSize(), y * getFieldSize());
                }
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
        return level.getSizeX() * getFieldSize();
    }

    @Override
    public int getHeight() {
        return level.getSizeY() * getFieldSize();
    }

    public List<Item> getSelectedItems(Point point) {
        int x = (int) (point.getX() / SpielFeld.getFieldSize());
        int y = (int) (point.getY() / SpielFeld.getFieldSize());

        return level.getItems(x, y);
    }

    void dropSelectedItem(Point point, Item itemToDrop) {
        List<Item> selectedItems = getSelectedItems(point);
        for (Item selectedItem : new ArrayList<>(selectedItems)) {
            if (selectedItem.allowDrop()) {
                selectedItems.add(itemToDrop);
            }
        }
    }

    public void timerEvent(ActionEvent event) {
        repaint();
    }
}
