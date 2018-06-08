import Controller.Controller;
import Model.ShellSort;
import View.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    private MainFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               new MainFrame();

            }
        });
    }
}
