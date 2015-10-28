/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment;

import fit5042.assignment.entities.Car;
import fit5042.assignment.gui.CarSalesGUI;
import fit5042.assignment.gui.CarSalesGUIInterface;
import fit5042.assignment.repository.CarSalesRepository;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author daniel
 */
public class CarSales implements ActionListener, ListSelectionListener,MouseListener {
    
    @EJB
    private static CarSalesRepository carSalesRepository;
    
    private String name;
    
    private CarSalesGUIInterface gui;
    
    public CarSales(String name) throws Exception {
        this.name = name;
    }
    
    public void initView() {
        
        this.gui = new CarSalesGUI(this, this,this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        if (event.getSource() == gui.getSearchButton()) {
            this.searchCar();
        } else if (event.getSource() == gui.getViewButton()) {
            this.displayAllCars();
        } 
        else {
            System.exit(0);
        }
        
    }
    
    int selectRow = -1;
    
    @Override
    public void valueChanged(ListSelectionEvent event) {
        
        if ((event.getSource() == this.gui.getCarSalesTable().getSelectionModel()) && (!event.getValueIsAdjusting())) {
            
            int selectRow = gui.getCarSalesTable().getSelectedRow();
          
            
            if(selectRow != -1)
        {
            String make = gui.getCarSalesTable().getValueAt(selectRow, 0).toString();
            String modelName = gui.getCarSalesTable().getValueAt(selectRow, 1).toString();
        
         try {
                Car car = carSalesRepository.SearchSingleCarByModelName(modelName);
                
                gui.getDisplayVINLabel().setText("   VIN: " + car.getVin());
                gui.getDisplayModelNoLabel().setText("   Model No: " + car.getModelno());
                gui.getDisplayModelNameLabel().setText("   Model Name: " + modelName);
                gui.getDisplayMakeLabel().setText("   Make: " + car.getMake());
                gui.getDisplayTypeLabel().setText("   Type: " + car.getType());
                gui.getDisplayDescriptionLabel().setText("   Description: " + car.getDescription());
                gui.getDisplayColourLabel().setText("   Colour: " + car.getColour());
                gui.getDisplayInstockLabel().setText("   Instock: " + car.getInstock());
                
                URL url = new URL(car.getPreviewurl());
                BufferedImage image = ImageIO.read(url);
                ImageIcon icon = new ImageIcon(image);
                gui.getDisplayPreviewUrlLabel().setIcon(icon);
                
                make = null;
                modelName = null;
                
                
            } catch (Exception ex) {
                
                gui.displayMessageInDialog(ex.getMessage());
            }
        }
            
        }
        
    }
    
        
       
        
    
    
    
    
    public void displayAllCars() {
        
        gui.clearDisplayLabel();
        
        try {
            List<Car> cars = carSalesRepository.getAllCars();
            
            if (cars != null) {
                this.gui.displayCarDetails(cars);
            }
            
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to retrieve properties: " + ex.getMessage());
        }
    }
    
    public void searchCar() {
        
        gui.clearDisplayLabel();
        
        String make = this.gui.getMake();
        String modelNo = this.gui.getModelNo();
        String modelName = this.gui.getModelName();
        String type = this.gui.getCarType();
        
        System.out.println("Make: " + make);
        System.out.println("ModelNo: " + modelNo);
        System.out.println("ModelName: " + modelName);
        System.out.println("Type: " + type);
        
        if (modelNo != null && !modelNo.isEmpty()) {
            System.out.println("model no error");
            this.searchCarByModelNo(modelNo);
        }
        
        if (make != null && !make.isEmpty()) {
            System.out.println("make error");
            this.searchCarByMake(make);
        }
        
        if (modelName != null && !modelName.isEmpty()) {
            System.out.println("model name error");
            this.searchCarByModelName(modelName);
        }
        
        if (type != null && !type.isEmpty()) {
            System.out.println("model type error");
            this.searchCarByType(type);
        }
        
    }
    
    public void searchCarByVIN(String VIN) {
        
        try {
            Car car = carSalesRepository.searchCarByVIN(VIN);
            
            if (car != null) {
                this.gui.displayCarDetails(car);
            } else {
                this.gui.displayMessageInDialog("No matched Car found");
                this.gui.clearCarSalesTable();
            }
            
        } catch (Exception ex) {
            
            this.gui.displayMessageInDialog("Failed to search property by VIN :" + ex.getMessage());
            this.gui.clearCarSalesTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
    public void searchCarByModelNo(String modelNo) {
        
        try {
            List<Car> cars = carSalesRepository.SearchCarByModelNo(modelNo);
            
            if (cars != null) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched Car found");
                this.gui.clearCarSalesTable();
            }
            
        } catch (Exception ex) {
            
            this.gui.displayMessageInDialog("Failed to search property by VIN :" + ex.getMessage());
            this.gui.clearCarSalesTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
    public void searchCarByMake(String make) {
        
        try {
            List<Car> cars = carSalesRepository.SearchCarByMake(make);
            
            if (cars != null) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched Car found");
                this.gui.clearCarSalesTable();
            }
            
        } catch (Exception ex) {
            
            this.gui.displayMessageInDialog("Failed to search property by VIN :" + ex.getMessage());
            this.gui.clearCarSalesTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
    public void searchCarByModelName(String modelName) {
        
        try {
            List<Car> cars = carSalesRepository.SearchCarByModelName(modelName);
            
            if (cars != null) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched Car found");
                this.gui.clearCarSalesTable();
            }
            
        } catch (Exception ex) {
            
            this.gui.displayMessageInDialog("Failed to search property by VIN :" + ex.getMessage());
            this.gui.clearCarSalesTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
    public void searchCarByType(String type) {
        
        try {
            List<Car> cars = carSalesRepository.SearchCarByType(type);
            
            if (cars != null) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched Car found");
                this.gui.clearCarSalesTable();
            }
            
        } catch (Exception ex) {
            
            this.gui.displayMessageInDialog("Failed to search property by VIN :" + ex.getMessage());
            this.gui.clearCarSalesTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
    public static void main(String[] args) {
        
        try {
            
            final CarSales car = new CarSales("Awesome CarSales");
            car.initView();
            
        } catch (Exception ex) {
            System.out.println("Holy Crap!!! " + ex.getMessage());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

            gui.getCarSalesTable().clearSelection();
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
   
}
