package View;

import Model.PointCoor;
import Model.PointDB;
import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Graphic extends JPanel {
    private Controller controller;

    private Graphics2D graphics2D;
    private int height = 500;
    private int width = 750;

    private int xShift = 40;
    private int yShift = 20;
    private int numberOfCoordinates = 10;

    private double coorX = 0;
    private double coorY = 0;

    private int length;
    private double lastX;
    private double lastY;

    private double prevX = -1;
    private double prevY = -1;

    private int zoom;
    private List<PointCoor> list;

    private int zoomX;
    private int segmentX, segmentY;

    public Graphic(Controller controller, int zoom, int zoomX) {
//        setPreferredSize(new Dimension(width, height));
        list = new ArrayList<>();
        this.zoom = zoom;
        this.zoomX = zoomX;
        this.controller = controller;
        zooming(width, height);
//        segmentX = (getWidth() - 2 * xShift) / numberOfCoordinates;
//        segmentY = (getHeight() - 2 * yShift) / numberOfCoordinates;

        addMouseWheelListener(e -> {
            if (e.getModifiers() == InputEvent.CTRL_MASK) {
                if (e.getWheelRotation() < 0 && this.zoom < 100) {
                    this.zoom++;
                    zooming(getWidth() + 30, getHeight() + 30);
                    repaint();
                } else if (e.getWheelRotation() > 0 && this.zoom > 1) {
                    this.zoom--;
                    zooming(getWidth() - 30, getHeight() - 30);
                    repaint();
                }
            } else if (e.getModifiers() == InputEvent.SHIFT_MASK) {
                if (e.getWheelRotation() < 0 && this.zoomX < 100) {
                    this.zoomX++;
                    zooming(getWidth() + 100, getHeight());
                    segmentX = xShift + (width - 2 * xShift) / numberOfCoordinates;
                    repaint();
                } else if (e.getWheelRotation() > 0 && this.zoomX > 1) {
                    this.zoomX--;
                    zooming(getWidth() - 100, getHeight());
                    segmentX = (getWidth() - 2 * xShift) / numberOfCoordinates;
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

        String yName = "Y";
        graphics2D.drawString(yName, xShift - 20, yShift + 15);
        String xName = "X";
        graphics2D.drawString(xName, width - xShift - 5, height);
        String zName = "0";
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
/*
        if (controller.getSize() != 0) {
            for (int size = 1; size < controller.getSize(); size++) {
                int x = list.get(list.indexOf(size - 1)).getX();
                double y = list.get(list.indexOf(size - 1)).getY();
                try {
                    drawPoint(x, y, graphics2D);
                } catch (Exception e) {
                    System.out.println("Sorry!");
                }
            }
        }
  */
        addCoordinatesX();
        addCoordinatesY();
    }

    private void drawPoint(int x, double y, Graphics2D graphics2D) {

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        x = xShift + (x - 1);
        y = getHeight() - xShift;

        graphics2D.setColor(Color.BLUE);
        graphics2D.setStroke(new BasicStroke(2.0f));

        graphics2D.draw(new Line2D.Double(lastX, lastY, x, y));
        graphics2D.fill(new Ellipse2D.Double(x - 2, y - 2, 5, 5));

        lastX = x;
        lastY = y;
    }

    public JPanel getPanel() {
        return this;
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

    public void setLengthArr(int length) {
        this.length = length;
        if (length % numberOfCoordinates == 0) {
            coorX = length;
        } else {
            coorX = numberOfCoordinates * (length / numberOfCoordinates + 1);
        }
        addCoordinatesX();
        repaint();
    }

    private void zooming(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        repaint();
        controller.zooming(width, height, zoom, zoomX);
        repaint();
    }

    public void setIncrement(int sign) {
        zoom += sign * 5;
        zooming(getWidth() + sign * 100, getHeight() + sign * 100);
        repaint();
    }

    public void rPaint(){
        repaint();
    }
}
