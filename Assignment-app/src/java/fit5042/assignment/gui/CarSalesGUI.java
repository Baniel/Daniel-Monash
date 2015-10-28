/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.gui;

import fit5042.assignment.entities.Car;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author daniel
 */
public class CarSalesGUI extends JFrame implements CarSalesGUIInterface {

    private static final String[] TABLE_COLUMNS = {"Make", "Model Name"};

    private final JLabel modelNoLabel;
    private final JLabel modelNameLabel;
    private final JLabel makeLabel;
    private final JLabel typeLabel;

    private final JLabel displayVINLabel;
    private final JLabel displayModelNoLabel;
    private final JLabel displayModelNameLabel;
    private final JLabel displayMakeLabel;
    private final JLabel displayTypeLabel;
    private final JLabel displayDescriptionLabel;
    private final JLabel displayColourLabel;
    private final JLabel displayInstockLabel;

    private final JLabel displayPreviewUrlLabel;

    private final JTextField modelNoTextField;
    private final JTextField modelNameTextField;
    private final JTextField makeTextField;
    private final JTextField typeTextField;

    private final JTable carSalesTable;

    private final JButton searchButton;
    private final JButton viewButton;

    private final JPanel inputPanel;
    private final JPanel buttonPanel;
    private final JPanel displayPanel;
    private final JPanel dispalyCarPanel;

    public CarSalesGUI(ActionListener actionListener, ListSelectionListener listSelectionListener, MouseListener mouseListener) {
        super("Awesome CarSales");

        //Create Label
        this.modelNoLabel = new JLabel("Model No");
        this.modelNameLabel = new JLabel("Model Name");
        this.makeLabel = new JLabel("Make");
        this.typeLabel = new JLabel("Type");

        //Display Car Text Label
        this.displayVINLabel = new JLabel("");
        this.displayModelNoLabel = new JLabel("");
        this.displayModelNameLabel = new JLabel("");
        this.displayMakeLabel = new JLabel("");
        this.displayTypeLabel = new JLabel("");
        this.displayDescriptionLabel = new JLabel("");
        this.displayColourLabel = new JLabel("");
        this.displayInstockLabel = new JLabel("");

        //Display Car Picture
        this.displayPreviewUrlLabel = new JLabel("");

        //Create text fields
        this.modelNoTextField = new JTextField();
        this.modelNameTextField = new JTextField();
        this.makeTextField = new JTextField();
        this.typeTextField = new JTextField();

        //Create table
        this.carSalesTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.carSalesTable.getSelectionModel().addListSelectionListener(listSelectionListener);
        this.carSalesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel carSalesTableColumnModel = this.carSalesTable.getColumnModel();
        carSalesTableColumnModel.getColumn(0).setPreferredWidth(150);
        carSalesTableColumnModel.getColumn(1).setPreferredWidth(100);

        //Create JButton
        this.searchButton = new JButton("Search");
        this.viewButton = new JButton("View");

        //create Panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.displayPanel = new JPanel();
        this.dispalyCarPanel = new JPanel();

        //Create container
        Container container = this.getContentPane();

        //set Layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(4, 2));
        this.buttonPanel.setLayout(new GridLayout(1, 2));
        this.displayPanel.setLayout(new GridLayout(8, 1));
        this.dispalyCarPanel.setLayout((new GridLayout(1, 1)));
        
        
        //add action listeners
        this.searchButton.addActionListener(actionListener);
        this.viewButton.addActionListener(actionListener);

        //add Display Pannel
        this.displayPanel.add(this.displayVINLabel);
        this.displayPanel.add(this.displayModelNoLabel);
        this.displayPanel.add(this.displayModelNameLabel);
        this.displayPanel.add(this.displayMakeLabel);
        this.displayPanel.add(this.displayTypeLabel);
        this.displayPanel.add(this.displayDescriptionLabel);
        this.displayPanel.add(this.displayColourLabel);
        this.displayPanel.add(this.displayInstockLabel);

        //
        this.dispalyCarPanel.add(this.displayPreviewUrlLabel);

        //add components
        this.inputPanel.add(modelNoLabel);
        this.inputPanel.add(modelNoTextField);

        this.inputPanel.add(modelNameLabel);
        this.inputPanel.add(modelNameTextField);

        this.inputPanel.add(makeLabel);
        this.inputPanel.add(makeTextField);

        this.inputPanel.add(typeLabel);
        this.inputPanel.add(typeTextField);

        //add buttons to panel
        this.buttonPanel.add(this.searchButton);
        this.buttonPanel.add(this.viewButton);

        //add panels to content pane
        container.add(this.inputPanel, BorderLayout.NORTH);
        container.add(new JScrollPane(this.carSalesTable), BorderLayout.WEST);
        container.add(this.dispalyCarPanel, BorderLayout.EAST);
        container.add(this.displayPanel, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 870);
        this.setVisible(true);

    }

    @Override
    public JButton getSearchButton() {
        return searchButton;
    }

    @Override
    public JButton getViewButton() {
        return viewButton;
    }

    @Override
    public JTable getCarSalesTable() {
        return carSalesTable;
    }

    @Override
    public void clearInput() {
        this.clearTextFields();

    }

    @Override
    public void clearCarSalesTable() {
        int numberOfRow = this.carSalesTable.getModel().getRowCount();

        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.carSalesTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index--) {
                tableModel.removeRow(index);
            }
        }
    }

    @Override
    public void clearTextFields() {
        this.makeTextField.setText("");
        this.modelNameTextField.setText("");
        this.modelNoTextField.setText("");
        this.typeTextField.setText("");
    }

    @Override
    public void displayCarDetails(java.util.List<Car> cars) {
        this.clearCarSalesTable();
        this.clearInput();

        for (Car car : cars) {

            ((DefaultTableModel) this.carSalesTable.getModel()).addRow(new Object[]{
                car.getMake(),
                car.getModelname(),});
        }

    }

    @Override
    public void displayCarDetails(Car car) {

        this.clearCarSalesTable();
        this.clearInput();

        ((DefaultTableModel) this.carSalesTable.getModel()).addRow(new Object[]{
            car.getModelname(),
            car.getMake(),});

    }

    @Override
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public String getModelNo() {

        String modelNo = this.modelNoTextField.getText();

        if (modelNo != "") {
            return modelNo;
        } else {
            return null;
        }
    }

    @Override
    public String getMake() {

        String make = this.makeTextField.getText();

        if (make != "") {
            return make;
        } else {
            return null;
        }

    }

    @Override
    public String getModelName() {

        String model = this.modelNameTextField.getText();

        if (model != "") {
            return model;
        } else {
            return null;
        }
    }

    @Override
    public String getCarType() {

        String type = this.typeTextField.getText();

        if (type != "") {
            return type;
        } else {
            return null;
        }

    }

    public static Image getImage(String thumbnail) {

        ImageIcon image = new ImageIcon(thumbnail);

        return image.getImage();

    }

    @Override
    public JLabel getDisplayVINLabel() {
        return displayVINLabel;
    }

    @Override
    public JLabel getDisplayModelNoLabel() {
        return displayModelNoLabel;
    }

    @Override
    public JLabel getDisplayModelNameLabel() {
        return displayModelNameLabel;
    }

    @Override
    public JLabel getDisplayMakeLabel() {
        return displayMakeLabel;
    }

    @Override
    public JLabel getDisplayTypeLabel() {
        return displayTypeLabel;
    }

    @Override
    public JLabel getDisplayDescriptionLabel() {
        return displayDescriptionLabel;
    }

    @Override
    public JLabel getDisplayColourLabel() {
        return displayColourLabel;
    }

    @Override
    public JLabel getDisplayInstockLabel() {
        return displayInstockLabel;
    }

    @Override
    public JLabel getDisplayPreviewUrlLabel() {
        return displayPreviewUrlLabel;
    }

    @Override
    public void clearDisplayLabel() {

        this.displayVINLabel.setText("");
        this.displayModelNoLabel.setText("");
        this.displayModelNameLabel.setText("");
        this.displayTypeLabel.setText("");
        this.displayDescriptionLabel.setText("");
        this.displayPreviewUrlLabel.setIcon(null);
        this.displayColourLabel.setText("");
        this.displayInstockLabel.setText("");

    }


}
