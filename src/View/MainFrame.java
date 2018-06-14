package View;

import Controller.*;
import Model.PointCoor;
import Model.PointDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame {
    private JPanel tablePanel;
    private Controller controller;
    private Graphic graphic;
    private int count_row;

    private JTable table;
    private JScrollPane tableScrollPane;

    private JTextField textLength; // длина массива
    private JTextField textQuantity; // количество массивов
    private JLabel labelSize;

    private String[] titles = {
            "Количество",
            "Время"
    };

    private int width, height;
    private int zoom;

    private DefaultTableModel tableModel;

    public MainFrame() {
        PointDB pointDB = new PointDB();
        controller = new Controller(pointDB, this);
        run();
    }

    private void run() {
        JFrame frame = new JFrame();
        JPanel pagePanel = new JPanel();
        JPanel graphicPanel;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(950, 600));
        frame.setLocation(200, 50);
        frame.setTitle("Построение графика");
        frame.setResizable(false);

        pagePanel.setLayout(new BorderLayout());
        pagePanel.add(managePanel(), BorderLayout.SOUTH);
        pagePanel.add(startTable(), BorderLayout.WEST);

        graphic = new Graphic(controller, 0);
        graphicPanel = graphic.getPanel();
        JScrollPane scrollGraphic = new JScrollPane(graphicPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        MouseController mouseController = new MouseController(scrollGraphic);
        graphicPanel.addMouseListener(mouseController);
        graphicPanel.addMouseMotionListener(mouseController);

        pagePanel.add(scrollGraphic);

        frame.add(pagePanel);
        frame.setVisible(true);
    }

    private JPanel startTable() {
        count_row = 0;
        tablePanel = new JPanel();
        tableModel = new DefaultTableModel(count_row, titles.length);
        tableModel.setColumnIdentifiers(titles);

        table = new JTable(tableModel);
        tableScrollPane = new JScrollPane(table);
        table.setEnabled(false);

        TableColumn column0;
        column0 = table.getColumnModel().getColumn(0);
        column0.setMaxWidth(80);

        TableColumn column1;
        column1 = table.getColumnModel().getColumn(1);
        column1.setMaxWidth(75);

        tableScrollPane.setPreferredSize(new Dimension(155, 515));

        tablePanel.add(tableScrollPane);
        tablePanel.repaint();

        return tablePanel;
    }

    private JPanel managePanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JLabel labelQuantity = new JLabel("Количество тестов:");
        textQuantity = new JTextField();
        JLabel labelLength = new JLabel("Количество элементов:");
        textLength = new JTextField();

        textLength.setPreferredSize(new Dimension(30, 30));
        textQuantity.setPreferredSize(new Dimension(30, 30));
        JButton buildButton = new JButton("Построить");
        labelSize = new JLabel("Масштаб: 0%");

        controlPanel.add(labelQuantity);
        controlPanel.add(textQuantity);
        controlPanel.add(labelLength);
        controlPanel.add(textLength);
        controlPanel.add(buildButton);
        controlPanel.add(labelSize);

        buildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphic.clear();
                if (controller.getSortThread() != null)
                    controller.killed();
                int quantity = 0;
                int length = 0;
                try {
                    quantity = Integer.parseInt(textQuantity.getText());
                    length = Integer.parseInt(textLength.getText());
                    count_row = (length - 1);
                } catch (NumberFormatException ex) {
                    System.out.println("Неправильный ввод");
                }
                if (quantity < 2 || length < 3) {
                    System.out.println("Введите большие значения");
                    return;
                }
                tableModel.setRowCount(count_row);
                graphic.setLengthArr(length);
                controller.startThread(quantity, length);
            }
        });

        return controlPanel;
    }

    public void viewTable(List<PointCoor> pointDBList) {
        int row = 0;
        tablePanel.add(tableScrollPane);
        List<PointCoor> points = pointDBList;
        if (points.size() == 0)
            return;
        for (int index = 0; index < count_row; index++) {
            if (index < points.size()) {
                PointCoor record = points.get(index);
                table.getModel().setValueAt(record.getX(), row, 0);
                table.getModel().setValueAt(record.getY(), row, 1);
            }
            row++;
        }
        tablePanel.repaint();
    }

    public void zooming(int width, int height, int zoom) {
        this.width = width;
        this.height = height;
        this.zoom = zoom;
        labelSize.setText("Масштаб: " + this.zoom + " %");
    }
}
