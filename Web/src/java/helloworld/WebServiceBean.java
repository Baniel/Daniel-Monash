/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import fit5042.assignment.entities.Car;
import fit5042.assignment.repository.CarSalesRepository;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author daniel
 */
@ManagedBean
@SessionScoped
public class WebServiceBean implements Serializable {

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
    private String price;
    
     @EJB
    private  CarSalesRepository carSalesRepository;
    
    
    /**
     * Creates a new instance of WebServiceBean
     */
    public WebServiceBean() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

   public List<Car> searchCarByMake() throws Exception
    {
      List<Car> cars = new ArrayList<Car>();
      
      
      try{
            cars = carSalesRepository.SearchCarByMake(this.make);
            
           
      }catch (Exception ex) {
        
          return null;
      }
      
        
        return cars;
        
    }
   
   
   setMakeWebService myWS;
   
   public void setMakeWebService(){
       
       myWS = new setMakeWebService();
       
       myWS.setPostMake2(getMake());
       
   }

    static class setMakeWebService {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/Web/webresources";

        public setMakeWebService() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("greeting");
        }

        public String getHtml() throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
        }

        public void putHtml(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.TEXT_HTML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_HTML));
        }

        public void setPostMake() throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null);
        }
        
        public void setPostMake2(String make) throws ClientErrorException{
            Form form = new Form();
            form.param("VIN", make);
            
            webTarget.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        }

        public void close() {
            client.close();
        }
    }
   
    
    
    
    
    
}
