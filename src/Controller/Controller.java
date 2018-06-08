package Controller;

import Model.Point;
import Model.ShellSort;
import Model.SortThread;
import View.MainFrame;

public class Controller {
    private MainFrame mainFrame;
    private SortThread sortThread;
    private ShellSort shellSort;
    private Thread thread;

    public Controller(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void startThread(int size, int elements) {
        sortThread = new SortThread(this, size, elements);
        sortThread.start();
    }

    public SortThread getSortThread() {
        return sortThread;
    }

    public void killed() {
        sortThread.killed();
    }

    public void newPoint(Point point) {
        mainFrame.newPoint(point);
    }

}
