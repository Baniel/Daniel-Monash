/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SALE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Sale.GET_ALL_SALE, query = "SELECT s FROM Sale s"),
    @NamedQuery(name = "Sale.findBySaleid", query = "SELECT s FROM Sale s WHERE s.saleid = :saleid"),
    @NamedQuery(name = "Sale.findByDate", query = "SELECT s FROM Sale s WHERE s.date = :date"),
    @NamedQuery(name = "Sale.findByBill", query = "SELECT s FROM Sale s WHERE s.bill = :bill")})
public class Sale implements Serializable {

      public static final String GET_ALL_SALE = "Sale.findAll";
    
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALEID")
    private int saleid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DATE")
    private String date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BILL")
    private String bill;
    @JoinColumn(name = "VIN", referencedColumnName = "VIN")
    @ManyToOne
    private Car vin;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne(optional = false)
    private Users userid;

    public Sale() {
    }

    public Sale(int saleid) {
        this.saleid = saleid;
    }

    public Sale(int saleid, String date, String bill) {
        this.saleid = saleid;
        this.date = date;
        this.bill = bill;
    }
    
      public Sale(int saleid, String date, String bill,Car vin,Users userid) {
        this.saleid = saleid;
        this.date = date;
        this.bill = bill;
        this.vin = vin;
        this.userid = userid;
        
    }

    public int getSaleid() {
        return saleid;
    }

    public void setSaleid(int saleid) {
        this.saleid = saleid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public Car getVin() {
        return vin;
    }

    public void setVin(Car vin) {
        this.vin = vin;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }



    @Override
    public String toString() {
        return "fit5042.assignment.entities.Sale[ saleid=" + saleid + " ]";
    }
    
}
