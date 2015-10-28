/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restful;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "CAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.findByVin", query = "SELECT c FROM Car c WHERE c.vin = :vin"),
    @NamedQuery(name = "Car.findByModelno", query = "SELECT c FROM Car c WHERE c.modelno = :modelno"),
    @NamedQuery(name = "Car.findByModelname", query = "SELECT c FROM Car c WHERE c.modelname = :modelname"),
    @NamedQuery(name = "Car.findByMake", query = "SELECT c FROM Car c WHERE c.make = :make"),
    @NamedQuery(name = "Car.findByType", query = "SELECT c FROM Car c WHERE c.type = :type"),
    @NamedQuery(name = "Car.findByThumbnail", query = "SELECT c FROM Car c WHERE c.thumbnail = :thumbnail"),
    @NamedQuery(name = "Car.findByDescription", query = "SELECT c FROM Car c WHERE c.description = :description"),
    @NamedQuery(name = "Car.findByColour", query = "SELECT c FROM Car c WHERE c.colour = :colour"),
    @NamedQuery(name = "Car.findByInstock", query = "SELECT c FROM Car c WHERE c.instock = :instock"),
    @NamedQuery(name = "Car.findByPrice", query = "SELECT c FROM Car c WHERE c.price = :price"),
    @NamedQuery(name = "Car.findByPreviewurl", query = "SELECT c FROM Car c WHERE c.previewurl = :previewurl")})
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "VIN")
    private String vin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "MODELNO")
    private String modelno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MODELNAME")
    private String modelname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MAKE")
    private String make;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "THUMBNAIL")
    private String thumbnail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COLOUR")
    private String colour;
    @Size(max = 500)
    @Column(name = "INSTOCK")
    private String instock;
    @Size(max = 20)
    @Column(name = "PRICE")
    private String price;
    @Size(max = 1000)
    @Column(name = "PREVIEWURL")
    private String previewurl;

    public Car() {
    }

    public Car(String vin) {
        this.vin = vin;
    }

    public Car(String vin, String modelno, String modelname, String make, String type, String thumbnail, String description, String colour) {
        this.vin = vin;
        this.modelno = modelno;
        this.modelname = modelname;
        this.make = make;
        this.type = type;
        this.thumbnail = thumbnail;
        this.description = description;
        this.colour = colour;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModelno() {
        return modelno;
    }

    public void setModelno(String modelno) {
        this.modelno = modelno;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPreviewurl() {
        return previewurl;
    }

    public void setPreviewurl(String previewurl) {
        this.previewurl = previewurl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vin != null ? vin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.vin == null && other.vin != null) || (this.vin != null && !this.vin.equals(other.vin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  vin + "," + modelno;
    }
    
}
