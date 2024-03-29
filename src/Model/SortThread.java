package Model;

import Controller.Controller;

public class SortThread extends Thread {
    private int size;
    private int elements;
    private boolean alive;
    private Controller controller;

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
        while (minLength <= elements && alive) {
            double time = 0;
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
