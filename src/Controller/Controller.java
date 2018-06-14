package Controller;

import Model.PointCoor;
import Model.PointDB;
import Model.SortThread;
import View.MainFrame;


import java.util.List;

public class Controller {
    private MainFrame mainFrame;
    private SortThread sortThread;
    private PointDB pointDB;

    public Controller(PointDB pointDB, MainFrame mainFrame) {
        this.pointDB = pointDB;
        this.mainFrame = mainFrame;
    }

    public void startThread(int size, int elements) {
        sortThread = new SortThread(this, size, elements);
        sortThread.start();
    }

    public SortThread getSortThread() {
        return sortThread;
    }

    public List<PointCoor> getListOfPoints() {
        return pointDB.getRecordOfPoints();
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void killed() {
        sortThread.killed();
    }

    public void addPoints(PointCoor pointCoor) {
        pointDB.add(pointCoor);
    }

    public void clearData() {
        pointDB.deleteRecords();
    }

    public int getSize() {
        return pointDB.getSize();
    }

    public void zooming(int width, int height, int zoom, int zoomX) {
        mainFrame.zooming(width, height, zoom, zoomX);
    }
}
