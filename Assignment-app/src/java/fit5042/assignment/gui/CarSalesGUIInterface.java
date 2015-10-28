/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.gui;

import fit5042.assignment.entities.Car;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author daniel
 */
public interface CarSalesGUIInterface {

    public JButton getSearchButton();

    public JButton getViewButton();
    

    public JTable getCarSalesTable();

    //Search Function
    public String getMake();

    public String getModelNo();

    public String getModelName();

    public String getCarType();

    public void displayCarDetails(Car car);

    public void displayCarDetails(List<Car> cars);

    public void clearInput();

    public void clearCarSalesTable();

    public void clearTextFields();
    
    public void clearDisplayLabel();

    public void displayMessageInDialog(String message);

    public JLabel getDisplayVINLabel();

    public JLabel getDisplayModelNoLabel();

    public JLabel getDisplayModelNameLabel();

    public JLabel getDisplayMakeLabel();

    public JLabel getDisplayTypeLabel();

    public JLabel getDisplayDescriptionLabel();

    public JLabel getDisplayColourLabel();

    public JLabel getDisplayInstockLabel();

    public JLabel getDisplayPreviewUrlLabel();
}
