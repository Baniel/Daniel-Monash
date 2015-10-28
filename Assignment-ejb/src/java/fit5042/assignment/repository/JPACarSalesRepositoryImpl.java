/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.repository;

import fit5042.assignment.entities.Car;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author daniel
 */

@Stateless
public class JPACarSalesRepositoryImpl implements CarSalesRepository {

    @PersistenceContext(unitName="Assignment-ejbPU")
    private EntityManager entityManager;
    
    
    private String VIN;
    private String modelNo;
    private String modelName;
    private String make;
    private String type;
    private String thumbnial;
    private String description;
    private String previewURL;
    private String colour;
    private String instock;
    
    
  
    
    
    

    @Override
    public void addCar(Car car) throws Exception {
        entityManager.persist(car);
    }

  

    @Override
    public void editCar(Car car) throws Exception {
        entityManager.merge(car);
    }

   

    @Override
    public List<Car> getAllCars() throws Exception {
        return entityManager.createNamedQuery(Car.GET_ALL_CAR).getResultList();
    }

    @Override
    public Car searchCarByVIN(String vin) throws Exception {

        Car car = entityManager.find(Car.class, vin);
        return car;
        
    }

    @Override
    public void removeCar(String vin) throws Exception {
        
        Car car = this.searchCarByVIN(vin);
        if (car != null) {
            entityManager.remove(car);
        }

    }

    @Override
    public List<Car> SearchCarByModelNo(String modelNo) throws Exception {

        Query query = entityManager.createNamedQuery(Car.FIND_BY_MODEL_NO);
        query.setParameter("modelno", modelNo);
        
        return query.getResultList();
    
    }

    @Override
    public List<Car> SearchCarByMake(String make) throws Exception {
        
//        Query query = entityManager.createNamedQuery(Car.FIND_BY_MAKE);
//        query.setParameter("make", make);
//        
//        return query.getResultList();
        
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Car.class);
        Root<Car> c = query.from(Car.class);
        
        query.select(c).where(builder.equal(c.get("make").as(String.class), make));
        
        return entityManager.createQuery(query).getResultList();
    
    }
    
    
    @Override
    public String SearchCarByMake() throws Exception {
     
        
        Query query = entityManager.createNamedQuery(Car.FIND_BY_MAKE);
        query.setParameter("make",this.make);
        
        
        for(int i = 0 ; i < query.getResultList().size();i++)
        {
           
        }
        
        //return "Daniel";
        
        return query.getResultList().toString();
    
    }
    
    
    
    
    
    

    @Override
    public List<Car> SearchCarByModelName(String modelName) throws Exception {
        Query query = entityManager.createNamedQuery(Car.FIND_BY_MODEL_NAME);
        query.setParameter("modelname", modelName);
        
        return query.getResultList();
    
    }

    @Override
    public List<Car> SearchCarByType(String type) throws Exception {
        Query query = entityManager.createNamedQuery(Car.FIND_BY_MODEL_TYPE);
        query.setParameter("type", type);
        
        return query.getResultList();
                
    }

    @Override
    public Car SearchSingleCarByModelName(String modelName) throws Exception {
        Query query = entityManager.createNamedQuery(Car.FIND_BY_MODEL_NAME);
        query.setParameter("modelname", modelName);
        
        return (Car) query.getSingleResult();
        
        
        
    
    }

    @Override
    public  String getMake() {
         return make;
    }

    @Override
    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    @Override
    public String getVIN() {
        return VIN;
    }

    @Override
    public void setModelNO(String modelNO) {
        this.modelNo = modelNO;
    }

    @Override
    public String getModelNo() {
        return modelNo;
    }

    @Override
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public void setThumbnial(String thumbnial) {
        this.thumbnial = thumbnial;
    }

    @Override
    public String getThumbnial() {
        return thumbnial;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setColour(String colour) {

        this.colour = colour;
    }

    @Override
    public String getColour() {
        return colour;
    }

     @Override
    public void setInstock(String instock) {
        this.instock = instock;
    }

    @Override
    public String getInstock() {
        return instock;
    }
 
   

    @Override
    public String toResult() throws Exception {

        
        Query query = entityManager.createNamedQuery(Car.FIND_BY_MAKE);
        query.setParameter("make",this.make);
        
        int index = 0;
        query.getResultList().get(index);
        
        return "<td>" + make  + "</td>" +
              "<td>" + type  + "</td>" ;
//                "<td>" + make  + "</td>" +
//                "<td>" + make  + "</td>" +
//                "<td>" + make  + "</td>" +
//                "<td>" + make  + "</td>" +
//                "<td>" + make  + "</td>" +
//                "<td>" + make  + "</td>" +
//                "<td>" + make  + "</td>" ;
    }

   
    

}
