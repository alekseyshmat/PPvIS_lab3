package Model;

import Controller.Controller;

import java.util.List;

public class SortThread extends Thread {
    private int size;
    private int elements;
    private boolean alive;
    private double time = 0;
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

    public double getTime() {
        return time;
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
                time += (finishTime - startTime) / 1000;
                System.out.println(time + " время");
            }
            if (!alive)
                return;
            minLength++;
        }
    }

}
