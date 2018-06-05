import View.MainFrame;

import javax.swing.*;

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
