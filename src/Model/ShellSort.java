package Model;

import Controller.Controller;

import java.util.List;

public class ShellSort implements Runnable {
    private Randoming randoming;
    private Controller controller;

    private boolean alive = false;
    private int size;
    private int elements;
    private int minLength = 2;
    private double time = 0;

    public ShellSort(Controller controller) {
        this.controller = controller;
        randoming = new Randoming();
//        sort();
    }

    public void start() {
        alive = true;
        minLength = 2;
    }

    public void start(int size, int elements) {
        this.size = size;
        this.elements = elements;
        start();
    }

    private void sort() {
        List<Integer> arr = randoming.getList();
        listSort(arr);

        System.out.println("Итоговый 2 ");
        for (int el : arr) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

    private void listSort(List<Integer> arr) {
        if(!alive)
            return;
        int k;
        for (int i = arr.size() / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.size(); j++) {
                int tmp = arr.get(j);
                for (k = j; k >= i && arr.get(k - i) > tmp; k -= i) {
                    arr.set(k, arr.get(k - i));
                }
                arr.set(k, tmp);
            }
        }
//        sort();
    }

    public void killed() {
        alive = false;
    }

    @Override
    public void run() {
        while (minLength < elements + 1 && alive) {
            for (int i = 0; i < size; i++) {
                double startTime = System.nanoTime();
                randoming.randomize(minLength);
                List list = randoming.getList();
                listSort(list);
                double finishTime = System.nanoTime();
                time += (finishTime - startTime) / 1000;

                System.out.println(time + " время");
            }
            if(!alive)
                return;
            minLength++;
        }
    }
}



