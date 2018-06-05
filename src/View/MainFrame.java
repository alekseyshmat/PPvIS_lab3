package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private JFrame frame;
    private MainTable mainTable;
    private Controller controller;
    private Graphic graphic;

    private int count = 5;
    private JTable table;
    private JScrollPane tableScrollPane;

    private JPanel pagePanel;

    private JButton buildButton;
    private JButton clearButton;

    private JLabel labelLength;
    private JLabel labelQuantity;

    private JTextField textLength; // длина массива
    private JTextField textQuantity; // количество массивов

    private String[] titles = {
            "n",
            "t"
    };
    private DefaultTableModel tableModel;

    public MainFrame() {
        frame = new JFrame();
        graphic = new Graphic();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 400));
        frame.setLocation(200, 50);
        frame.setTitle("Рисование графика");
        frame.setResizable(false);
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());
        frame.add(managePanel(), BorderLayout.SOUTH);
        frame.add(startTable(), BorderLayout.WEST);

    }

    private JPanel startTable() {
        JPanel tablePanel = new JPanel();
        tableModel = new DefaultTableModel(20, titles.length);
        tableModel.setColumnIdentifiers(titles);

        table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(100,300));
        table.setEnabled(false);

        tablePanel.add(tableScrollPane);

        return tablePanel;
    }


    private JPanel managePanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        labelQuantity = new JLabel("Количество маccивов:");
        textQuantity = new JTextField();
        textQuantity.setPreferredSize(new Dimension(30, 30));
        buildButton = new JButton("Построить");

        controlPanel.add(labelQuantity);
        controlPanel.add(textQuantity);
        controlPanel.add(buildButton);

        buildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                startTable(Integer.parseInt(textQuantity.getText()));
//                pagePanel.repaint();
            }
        });
        return controlPanel;
    }
}
