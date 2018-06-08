package Model;

import Controller.Controller;

public class SortThread extends Thread {
    private int size;
    private boolean alive;
    public double time = 0;
    private Controller controller;

    public SortThread(Controller controller, int size) {
        this.controller = controller;
        this.size = size;
        alive = true;
    }

    public void terminate() {
        alive = false;
    }

    public double getTime(){
        return time;
    }

    @Override
    public void run() {
        alive = true;






    }

}
