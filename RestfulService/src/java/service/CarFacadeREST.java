/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.restful.Car;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author daniel
 */
@Stateless
@Path("com.restful.car")
public class CarFacadeREST extends AbstractFacade<Car> {
    @PersistenceContext(unitName = "RestfulServicePU")
    private EntityManager em;

    public CarFacadeREST() {
        super(Car.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Car entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Car entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Car find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Car> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Car> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    
    @GET
    @Path("carMake/{make}")
    @Produces({"application/json"})
    public List<Car> findCarByMake(@PathParam("make") String make)
    {
        Query query = em.createNamedQuery("Car.findByMake");
        query.setParameter("make", make);
        
        return query.getResultList();
    }
    
    
    @GET
    @Path("carVIN/{vin}")
    @Produces("application/json")
    public Car findCarByVIN(@PathParam("vin") String vin)
    {
        Query query = em.createNamedQuery("Car.findByVin");
        query.setParameter("vin", vin);
        

        return (Car) query.getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
