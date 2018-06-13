package Model;

import Controller.Controller;
import View.Graphic;
import View.MainFrame;

import java.util.List;

public class SortThread extends Thread {
    private int size;
    private int elements;
    private double time;
    private boolean alive;
    private Controller controller;
    private Graphic graphic;
    private List<PointCoor> list;

    public SortThread(Controller controller, int size, int elements) {
        this.controller = controller;
        this.size = size;
        this.elements = elements;
        alive = true;
    }

    public void killed() {
        alive = false;
    }

    private PointCoor readPoint(int el, double timeY) {
        PointCoor pointCoor = new PointCoor();
        pointCoor.setX(el);
        pointCoor.setY(timeY);

        return pointCoor;
    }

    @Override
    public void run() {
        int minLength = 2;
        alive = true;
        while (minLength < elements + 1 && alive) {
            time = 0;
            for (int i = 0; i < size; i++) {
                ShellSort shellSort = new ShellSort(minLength);
                double startTime = System.nanoTime();
                shellSort.sort();
                double finishTime = System.nanoTime();
                time += (finishTime - startTime) / (size * 1000);
                System.out.println(time + " время");
            }

            if (!alive) {
                return;
            }

            controller.addPoints(readPoint(minLength, time));
            minLength++;
        }
        controller.getMainFrame().viewTable(controller.getListOfPoints());
    }

}
