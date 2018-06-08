package View;

import Model.Point;
import javafx.scene.chart.XYChart;

import javax.swing.*;
import java.awt.*;

public class Graphic extends ScrollPane {
    private Graphics2D graphics2D;
    private Graphics graphics;
    private double minWidth = 700;
    private double minHeigt = 700;
    private double heigt = 700;
    private double width = 700;
    private int maxX = 0;
    private int maxY = 0;
    private double shift = 25;
    private int mums = 5;

    private String xName;
    private String yName;
    private List list;

    public Graphic(String xName, String yName) {
        this.xName = xName;
        this.yName = yName;


    }

    private void drawAxis(){
       graphics2D.setStroke();
       graphics2D.stro
    }
}
