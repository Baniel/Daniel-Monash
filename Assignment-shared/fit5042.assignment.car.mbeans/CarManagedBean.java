/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.carSales.mbeans;

import fit5042.assignment.repository.CarSalesRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author daniel
 */
@ManagedBean
@Named(value = "carManagedBean")
@SessionScoped

public class CarManagedBean implements Serializable {

    private String VIN;
    private String modelNo;
    private String modelName;
    private String make;
    private String type;
    private String thumbnail;
    private String description;
    private String previewURL;
    private String colour;
    private String instock;
    
     @EJB
    private static CarSalesRepository carSalesRepository;
    
    
    
    /**
     * Creates a new instance of CarManagedBean
     */
    public CarManagedBean() {
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getInstock() {
        return instock;
    }

    public void setInstock(String instock) {
        this.instock = instock;
    }

    public static CarSalesRepository getCarSalesRepository() {
        return carSalesRepository;
    }

    public static void setCarSalesRepository(CarSalesRepository carSalesRepository) {
        CarManagedBean.carSalesRepository = carSalesRepository;
    }
    
    
   
    
    
    
    
}
