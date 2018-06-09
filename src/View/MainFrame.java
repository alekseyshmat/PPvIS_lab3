package View;

import Controller.Controller;
import Model.Point;
import Model.ShellSort;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private JFrame frame;
    private JPanel pagePanel;
    private Controller controller;
    private Graphic graphic;

    private int row = 0;
    private int count_row;


    private JTable table;
    private JScrollPane tableScrollPane;

    private JButton buildButton;

    private JLabel labelLength;
    private JLabel labelQuantity;

    private JTextField textLength; // длина массива
    private JTextField textQuantity; // количество массивов

    private String[] titles = {
            "Количество элементов",
            "Время"
    };

    private DefaultTableModel tableModel;

    public MainFrame() {
        frame = new JFrame();
        controller = new Controller(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(900, 600));
        frame.setLocation(200, 50);
        frame.setTitle("Рисование графика");
        frame.setResizable(false);
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());
        frame.add(managePanel(), BorderLayout.SOUTH);
        frame.add(startTable(count_row), BorderLayout.WEST);

        graphic = new Graphic();
        frame.add(graphic,BorderLayout.CENTER);

    }

    private JPanel startTable(int row) {
        JPanel tablePanel = new JPanel();
        tableModel = new DefaultTableModel(row, titles.length);
        tableModel.setColumnIdentifiers(titles);

        table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(100, 300));
        table.setEnabled(false);

        tablePanel.add(tableScrollPane);

        tablePanel.repaint();

        return tablePanel;
    }

    private JPanel managePanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        labelQuantity = new JLabel("Количество тестов:");
        textQuantity = new JTextField();
        labelLength = new JLabel("Количество элементов:");
        textLength = new JTextField();

        textLength.setPreferredSize(new Dimension(30, 30));
        textQuantity.setPreferredSize(new Dimension(30, 30));
        buildButton = new JButton("Построить");

        controlPanel.add(labelQuantity);
        controlPanel.add(textQuantity);
        controlPanel.add(labelLength);
        controlPanel.add(textLength);
        controlPanel.add(buildButton);

        buildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = 0;
                int length = 0;
                try {
                    quantity = Integer.parseInt(textQuantity.getText());
                    length = Integer.parseInt(textLength.getText());
                    count_row = quantity * (length - 1);
                } catch (NumberFormatException ex) {
                    System.out.println("Неправильный ввод");
                }
                if (quantity > 0 && length > 0)
                    controller.startThread(quantity, length);
                else {
                    System.out.println("Неправильно");
                    return;
                }
            }
        });


        return controlPanel;
    }

    public void newPoint(Point point) {

    }
}
