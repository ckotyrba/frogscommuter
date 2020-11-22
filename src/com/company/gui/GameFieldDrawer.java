package com.company.gui;

import static com.company.figures.Item.PIXEL_SIZE;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.*;

import com.company.Level.Level;
import com.company.figures.Item;

public class GameFieldDrawer extends JPanel {

    private static final int ZOOM = 4;

    private final Level level;
    private final FPSCalculator fpsCalculator = new FPSCalculator();
    public Point mousePosition;
    public Item draggedItem;

    private Graphics graphics;

    public GameFieldDrawer(Level level) {
        setBackground(new Color(82, 55, 201, 168));
        this.level = level;
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
        drawDraggedIfNecessary();

        g.setColor(new Color(255, 0, 0));
        g.drawString("" + fpsCalculator.getFPS(), 100, 100);
        fpsCalculator.update();
    }

    private void drawDraggedIfNecessary() {
        if (draggedItem != null && mousePosition != null) {
            drawImageCentered(draggedItem.getImage(), mousePosition);
        }
    }

    private void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, getFieldSize(), getFieldSize(), null);
    }

    private void drawImageCentered(Image image, Point point) {
        if (!atLoastOneItemAllowDrop(getSelectedItems(point))) {
            BufferedImage bufferedImage = new BufferedImage(PIXEL_SIZE, PIXEL_SIZE, 2);
            Graphics graphics = bufferedImage.getGraphics();

            graphics.drawImage(image, 0, 0, null);
            graphics.setColor(new Color(255, 0, 0));
            graphics.drawLine(0, 0, PIXEL_SIZE, PIXEL_SIZE);
            image = bufferedImage;
        }
        graphics.drawImage(image, point.x - (getFieldSize() / 2), point.y - (getFieldSize() / 2), getFieldSize(), getFieldSize(), null);
    }

    private boolean atLoastOneItemAllowDrop(List<Item> selectedItems) {
        for (Item selectedItem : selectedItems) {
            if (selectedItem.allowDrop()) {
                return true;
            }
        }
        return false;
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
        int x = getXOfSelectedItem(point);
        int y = getYOfSelectedItem(point);

        return level.getItems(x, y);
    }

    public Point getPointOfSelectedItem(Point point) {
        return new Point(getXOfSelectedItem(point), getYOfSelectedItem(point));
    }

    private int getXOfSelectedItem(Point point) {
        return (int) (point.getX() / GameFieldDrawer.getFieldSize());
    }

    private int getYOfSelectedItem(Point point) {
        return (int) (point.getY() / GameFieldDrawer.getFieldSize());
    }


    public void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    public void setDraggedItem(Item draggedItem) {
        this.draggedItem = draggedItem;
    }
}
