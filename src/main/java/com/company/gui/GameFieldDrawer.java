package com.company.gui;

import static com.company.figures.Item.PIXEL_SIZE;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import com.company.Level.Level;
import com.company.figures.Container;
import com.company.gamelogic.GameController;

public class GameFieldDrawer extends JPanel {

    private static final int ZOOM = 4;

    private final Level level;
    private final FPSCalculator fpsCalculator = new FPSCalculator();
    private Point mousePosition;
    private Container draggedFrom;

    private final Image backgroundlevel = null;
    private GameController gameController;

    public GameFieldDrawer(Level level) {
        this.level = level;
        this.gameController = new GameController(level.getLogicalOrder());
        //        this.backgroundlevel = createBackgroundImage(background);
    }

    private Image createBackgroundImage(Level background) {
        BufferedImage bufferedImage = new BufferedImage(background.getSizeX() * getFieldSize(), background.getSizeY() * getFieldSize(),
                TYPE_INT_ARGB);
        if (level != null) {
            drawLevel(bufferedImage.getGraphics(), background);
        }
        return bufferedImage;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        setBackground(new Color(19, 114, 255, 168));
        super.paintComponent(g);
        g.drawImage(backgroundlevel, 0, 0, null);
        drawLevel(g, level);
        drawDraggedIfNecessary(g);

        g.setColor(new Color(255, 0, 0));
        fpsCalculator.update();
    }

    private void drawLevel(Graphics g, Level levelToDraw) {
        for (int y = 0; y < levelToDraw.getSizeY(); y++) {
            for (int x = 0; x < levelToDraw.getSizeX(); x++) {
                Container container = levelToDraw.getContainer(x, y);
                drawImage(g, container.getImage(), x * getFieldSize(), y * getFieldSize());
                if (container.getContent() != null) {
                    drawImage(g, container.getContent().getImage(), x * getFieldSize(), y * getFieldSize());
                }
            }
        }
    }

    private void drawDraggedIfNecessary(Graphics g) {
        if (draggedFrom != null && mousePosition != null) {
            Image image = draggedFrom.getContent().getImage();
            if (!gameController.jumpAllowed(draggedFrom, getSelectedContainer(mousePosition))) {
                BufferedImage bufferedImage = new BufferedImage(PIXEL_SIZE, PIXEL_SIZE, 2);
                Graphics graphics = bufferedImage.getGraphics();

                graphics.drawImage(image, 0, 0, null);
                graphics.setColor(new Color(255, 0, 0));
                graphics.drawLine(0, 0, PIXEL_SIZE, PIXEL_SIZE);
                image = bufferedImage;
            }
            g.drawImage(image, mousePosition.x - (getFieldSize() / 2), mousePosition.y - (getFieldSize() / 2), getFieldSize(), getFieldSize(), null);
        }
    }

    private void drawImage(Graphics g, Image image, int x, int y) {
        g.drawImage(image, x, y, getFieldSize(), getFieldSize(), null);
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

    public Container getSelectedContainer(Point absolutePoint) {
        return level.getContainer(getXOfSelectedItem(absolutePoint), getYOfSelectedItem(absolutePoint));
    }

    private int getXOfSelectedItem(Point point) {
        return (int) (point.getX() / GameFieldDrawer.getFieldSize());
    }

    private int getYOfSelectedItem(Point point) {
        return (int) (point.getY() / GameFieldDrawer.getFieldSize());
    }


    public void updateDragPosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    public void dragReleased(Point currentMousePosition) {
        gameController.doJumpIfPossible(draggedFrom, getSelectedContainer(currentMousePosition));
        this.draggedFrom = null;
        repaint();
    }

    public void startDrag(Point currentMousePosition) {
        this.draggedFrom = getSelectedContainer(currentMousePosition);
    }
}
