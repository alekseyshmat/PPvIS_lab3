package View;

import Model.PointCoor;
import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private int coorX = 0;
    private double coorY = 0;

    private int length;

    private int zoom;
    private List<PointCoor> list;

    private int segmentX, segmentY;

    public Graphic(Controller controller, int zoom) {
        list = new ArrayList<>();
        this.zoom = zoom;
        this.controller = controller;
        zooming(width, height);

        segmentX = (getWidth() - 2 * xShift) / numberOfCoordinates;
        segmentY = (getHeight() - 2 * yShift) / numberOfCoordinates;

        addMouseWheelListener(e -> {
            if (e.getModifiers() == InputEvent.CTRL_MASK) {
                if (e.getWheelRotation() < 0 && this.zoom < 100) {
                    this.zoom++;
                    zooming(getWidth() + 30, getHeight() + 30);
                    segmentX = (getWidth() - 2 * xShift) / numberOfCoordinates;
                    segmentY = (getHeight() - 2 * yShift) / numberOfCoordinates;
                    repaint();
                } else if (e.getWheelRotation() > 0 && this.zoom > 0) {
                    this.zoom--;
                    zooming(getWidth() - 30, getHeight() - 30);
                    segmentX = (getWidth() - 2 * xShift) / numberOfCoordinates;
                    segmentY = (getHeight() - 2 * yShift) / numberOfCoordinates;
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

        graphics2D.drawLine(xShift, yShift, xShift, getHeight() - yShift);
        graphics2D.drawLine(xShift, yShift, xShift + 10, yShift + 15);
        graphics2D.drawLine(xShift, yShift, xShift - 10, yShift + 15);

        graphics2D.drawLine(xShift, getHeight() - yShift, getWidth() - xShift, getHeight() - yShift);
        graphics2D.drawLine(getWidth() - xShift, getHeight() - yShift, getWidth() - xShift - 15, getHeight() - yShift - 10);
        graphics2D.drawLine(getWidth() - xShift, getHeight() - yShift, getWidth() - xShift - 15, getHeight() - yShift + 10);

        String yName = "Y";
        graphics2D.drawString(yName, xShift - 20, yShift + 15);
        String xName = "X";
        graphics2D.drawString(xName, getWidth() - xShift - 5, getHeight());
        String zName = "0";
        graphics2D.drawString(zName, xShift, getHeight() - yShift + 15);

        graphics2D.setStroke(new BasicStroke(0.5f));
        for (int indexX = 1; indexX < numberOfCoordinates; indexX++) {
            graphics2D.drawLine(
                    xShift + indexX * segmentX,
                    yShift,
                    xShift + indexX * segmentX,
                    getHeight() - yShift
            );
            repaint();
        }

        for (int indexY = 1; indexY < numberOfCoordinates; indexY++) {
            graphics2D.drawLine(
                    xShift,
                    yShift + indexY * segmentY,
                    getWidth() - xShift,
                    yShift + indexY * segmentY
            );
            repaint();
        }

        if (controller.getSize() != 0) {
            for (int size = 1; size < controller.getSize(); size++) {

                int x1 = controller.getListOfPoints().get(size - 1).getX();
                int x2 = controller.getListOfPoints().get(size).getX();

                double y = controller.getListOfPoints().get(size).getY();

                if (y > coorY) {
                    coorY = y;
                    addCoordinatesY();
                } else {
                    double y1 = controller.getListOfPoints().get(size - 1).getY();
                    double y2 = controller.getListOfPoints().get(size).getY();
                    drawPoint(x1, x2, y1, y2, graphics2D);
                }
            }
        }

        addCoordinatesX();
        addCoordinatesY();
    }

    private void drawPoint(int x1, int x2, double y1, double y2, Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(new BasicStroke(2.0f));
        graphics2D.setColor(Color.GREEN);
        graphics2D.draw(new Line2D.Double(
                xShift + x1 * (segmentX * numberOfCoordinates) / coorX,
                getHeight() - yShift - y1 * (segmentY * numberOfCoordinates) / coorY,
                xShift + x2 * (segmentX * numberOfCoordinates) / coorX,
                getHeight() - yShift - y2 * (segmentY * numberOfCoordinates) / coorY

        ));
        graphics2D.setColor(Color.RED);
        graphics2D.draw(new Ellipse2D.Double(
                xShift + x1 * (segmentX * numberOfCoordinates) / coorX - 3,
                getHeight() - yShift - y1 * (segmentY * numberOfCoordinates) / coorY - 3,
                7,
                7
        ));
        graphics2D.draw(new Ellipse2D.Double(
                xShift + x2 * (segmentX * numberOfCoordinates) / coorX - 3,
                getHeight() - yShift - y2 * (segmentY * numberOfCoordinates) / coorY - 3,
                7,
                7
        ));
    }

    public JPanel getPanel() {
        return this;
    }

    private void addCoordinatesX() {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(1.0f));
        for (int indexX = 1; indexX < numberOfCoordinates; indexX++) {
            graphics2D.drawString(
                    indexX * coorX / numberOfCoordinates + "",
                    xShift + indexX * segmentX,
                    getHeight() - yShift + 15
            );
        }
    }

    private void addCoordinatesY() {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(1.0f));
        for (int indexY = 0; indexY < numberOfCoordinates; indexY++) {
            graphics2D.drawString(
                    (double) Math.round(indexY * coorY * 100 / numberOfCoordinates) / 100 + "",
                    xShift - 40,
                    getHeight() - yShift - indexY * segmentY
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
        controller.zooming(width, height, zoom);
        repaint();
    }

    private void update() {
        addCoordinatesX();
        addCoordinatesY();
        repaint();
    }

    public void clear() {
        controller.clearData();
        coorY = 0;
        update();
    }
}
