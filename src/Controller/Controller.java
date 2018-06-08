package Controller;

import Model.ShellSort;
import Model.SortThread;
import View.MainFrame;

public class Controller {
    private MainFrame mainFrame;

    private ShellSort shellSort;
    private Thread thread;

    public Controller(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.shellSort = new ShellSort(this);
    }

    public void startThread(int size, int elements) {
        if (thread != null) {
            shellSort.killed();
        }
        shellSort.start(size, elements);
        thread = new Thread(shellSort);
        thread.start();
    }

}
