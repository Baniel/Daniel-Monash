/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

import fit5042.assignment.mbeans.CarManagedBeans;
import fit5042.assignment.repository.CarSalesRepository;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("greeting")
public class helloWorld {

    @Context
    private UriInfo context;
    
    @EJB
    private CarSalesRepository carSalesRepository;
    
    private CarManagedBeans carManagedBean;
    
    private WebServiceBean myWS;

    /**
     * Creates a new instance of helloWorld
     */
    public helloWorld() {
    }

    /**
     * Retrieves representation of an instance of helloworld.helloWorld
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() throws Exception {
        //TODO return proper representation object
     return "<html><body><center>" +
             "<h1> Make :" + carSalesRepository.getMake() +"</h1>";
             
             
             
             
    //throw new UnsupportedOperationException();
    }
    
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void setPostMake(@FormParam("make") String content)
    {
        carSalesRepository.setMake(content);
    
    }
    

    /**
     * PUT method for updating or creating an instance of helloWorld
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
        
    }
}
