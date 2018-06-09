package View;

import java.awt.event.InputEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLACK;

public class Graphic extends JPanel {

    private Graphics2D graphics2D;
    private int height = 500;
    private int width = 750;

    private int xShift = 40;
    private int yShift = 20;
    private int numberOfCoordinates = 10;

    private int coorX = 0;
    private double coorY = 0;

    private String xName = "X";
    private String yName = "Y";
    private String zName = "0";

    private int zoom;
    private List list;

    public Graphic() {
        setPreferredSize(new Dimension(width, height));

        addMouseWheelListener(e -> {
            if (e.getModifiers() == InputEvent.CTRL_MASK) {
                if (e.getWheelRotation() < 0 && this.zoom < 100) {
                    this.zoom++;
                    zooming(getWidth() + 100, getHeight());
                    repaint();
                } else if (e.getWheelRotation() > 0 && this.zoom > 1) {
                    this.zoom--;
                    zooming(getWidth() - 100, getHeight());
                    repaint();
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;

        graphics2D.setStroke(new BasicStroke(3.0f));

        graphics2D.drawLine(xShift, yShift, xShift, height - yShift);
        graphics2D.drawLine(xShift, yShift, xShift + 10, yShift + 15);
        graphics2D.drawLine(xShift, yShift, xShift - 10, yShift + 15);

        graphics2D.drawLine(xShift, height - yShift, width - xShift, height - yShift);
        graphics2D.drawLine(width - xShift, height - yShift, width - xShift - 15, height - yShift - 10);
        graphics2D.drawLine(width - xShift, height - yShift, width - xShift - 15, height - yShift + 10);

        graphics2D.drawString(yName, xShift - 20, yShift + 15);
        graphics2D.drawString(xName, width - xShift - 5, height);
        graphics2D.drawString(zName, xShift, height - yShift + 15);

        graphics2D.setStroke(new BasicStroke(0.5f));
        for (int indexX = 1; indexX < numberOfCoordinates; indexX++) {
            graphics2D.drawLine(
                    xShift + indexX * (width - 2 * xShift) / numberOfCoordinates,
                    yShift,
                    xShift + indexX * (width - 2 * xShift) / numberOfCoordinates,
                    height - yShift
            );
        }

        for (int indexY = 1; indexY < numberOfCoordinates; indexY++) {
            graphics2D.drawLine(
                    xShift,
                    yShift + indexY * (height - 2 * yShift) / numberOfCoordinates,
                    width - xShift,
                    yShift + indexY * (height - 2 * yShift) / numberOfCoordinates
            );
        }
        addCoordinatesX();
        addCoordinatesY();
    }

    private void addCoordinatesX() {
        graphics2D.setStroke(new BasicStroke(1.0f));
        for (int indexX = 1; indexX < numberOfCoordinates; indexX++) {
            graphics2D.drawString(
                    indexX * coorX / numberOfCoordinates + "",
                    xShift + indexX * (width - 2 * xShift) / numberOfCoordinates,
                    height - yShift + 15
            );
        }
    }

    private void addCoordinatesY() {
        graphics2D.setStroke(new BasicStroke(1.0f));
        for (int indexY = 0; indexY < numberOfCoordinates; indexY++) {
            graphics2D.drawString(
                    (double) Math.round((double) indexY * coorY * 100 / numberOfCoordinates) / 100 + "",
                    xShift - 25,
                    height - yShift - indexY * (height - 2 * yShift) / numberOfCoordinates
            );
        }
    }

    private void addPoint() {

    }

    private void zooming(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        repaint();

    }
}
