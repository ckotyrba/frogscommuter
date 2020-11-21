package com.company.gui;

import static com.company.figures.Item.PIXEL_SIZE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import com.company.Level.Level;
import com.company.figures.Item;

public class SpielFeld extends JPanel {

    private static final int ZOOM = 4;
    public static final int FPS = (int) (TimeUnit.SECONDS.toMillis(1) / 60);

    private final Level level;
    private final FPSCalculator fpsCalculator = new FPSCalculator();
    private final MouseListener mouseListener = new MouseListener(this);
    private Graphics graphics;

    public SpielFeld(Level level) {
        this.level = level;
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
                drawImage(level.getItem(x, y).getImage(), x, y);
            }
        }
        mouseListener.drawDraggedIfNecessary();
        if (mouseListener.draggedItem != null && mouseListener.mousePosition != null) {
            drawImage(mouseListener.draggedItem.getImage(), mouseListener.mousePosition.x, mouseListener.mousePosition.y);
        }

        g.setColor(new Color(255, 0, 0));
        g.drawString("" + fpsCalculator.getFPS(), 100, 100);
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x * getFieldSize(), y * getFieldSize(), getFieldSize(), getFieldSize(), null);
    }

    public static int getFieldSize() {
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

    public Item getSelectedItem(Point point) {
        int x = (int) (point.getX() / SpielFeld.getFieldSize());
        int y = (int) (point.getY() / SpielFeld.getFieldSize());

        return level.getItem(x, y);
    }

    public void timerEvent(ActionEvent event) {
        repaint();
    }
}
