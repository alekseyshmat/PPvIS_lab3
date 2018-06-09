package View;

import java.awt.event.InputEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.awt.*;

public class Graphic extends JPanel {

    private Graphics2D graphics2D;
    private int height = 500;
    private int width = 750;

    private int xShift = 40;
    private int yShift = 20;
    private int numberOfCoordinates = 10;

    private String xName = "X";
    private String yName = "Y";
    private String zName = "0";

    private static final int BORDER = 30;
    private static final int BORDER_LEFT = 40;
    private static final int BORDER_UP = 2;
    private static final int BORDER_SEGMENT = 20;
    private static final int COUNT_OF_SEGMENTS_Y = 10;
    private static final int COUNT_OF_SEGMENTS_X = 10;

    private int segmentX, segmentY;

    private List list;

    public Graphic() {
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        segmentX = (getWidth() - BORDER_SEGMENT - BORDER_LEFT) / COUNT_OF_SEGMENTS_Y;
        segmentY = (getHeight() - BORDER_SEGMENT - BORDER) / COUNT_OF_SEGMENTS_X;

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
        graphics2D.drawString(zName, xShift - 10, height - yShift + 10);

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
    }
}
