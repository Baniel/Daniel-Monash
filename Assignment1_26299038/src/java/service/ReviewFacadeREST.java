/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
import netbeans.saas.Review;

/**
 *
 * @author daniel
 */
@Stateless
@Path("netbeans.saas.review")
public class ReviewFacadeREST extends AbstractFacade<Review> {
    @PersistenceContext(unitName = "Assignment1PU")
    private EntityManager em;

    public ReviewFacadeREST() {
        super(Review.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Review entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Short id, Review entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Short id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Review find(@PathParam("id") Short id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Review> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Review> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    
    
     //Get Average Score
    @GET
    @Path("movieName/{movieName}")
    @Produces("text/plain")
    public String findByAvg(@PathParam("movieName") String movieName)
    {
        Query query = em.createNamedQuery("Review.findByAvg");
        query.setParameter("movieName", movieName);
        String avgScore = String.valueOf(query.getSingleResult());
        return avgScore;
    }
    
    
    //Get the total number of users who entered the reviews for that movie
    @GET
    @Path("count/{count}")
    @Produces("text/plain")
    public String findByTotalUser(@PathParam("count") String movieName)
    {
        Query query = em.createNamedQuery("Review.fingByTotalUser");
        query.setParameter("movieName", movieName);
        String totalUser = String.valueOf(query.getSingleResult());
        return  totalUser;
    }
    
    //Get the User Comment
    @GET
    @Path("comment/{comment}")
    @Produces("text/plain")
    public String findByUserComment(@PathParam("comment") String movieName)
    {
        Query query = em.createNamedQuery("Review.findByUserComment");
        query.setParameter("movieName", movieName);
        String userComment = String.valueOf(query.getResultList().get(0));
        return userComment;
    }
    
    //Get the User date
    @GET
    @Path("date/{date}")
    @Produces("text/plain")
    public String findByUserDate(@PathParam("date") String movieName)
    {
        Query query = em.createNamedQuery("Review.findByUserDate");
        query.setParameter("movieName", movieName);
        String userDate = String.valueOf(query.getResultList().get(0).toString());
        return userDate;
    }
    
    //Get the User Name
    @GET
    @Path("UserName/{name}")
    @Produces("text/plain")
    public String findByReviewUserName(@PathParam("name") String movieName)
    {
        Query query = em.createNamedQuery("Review.findByReviewUserName");
        query.setParameter("movieName", movieName);
        String userName = String.valueOf(query.getResultList().get(0));
        return userName;
    }
    
  
    
    @GET
    @Path("UserLocation/{location}")
    @Produces("text/plain")
    public String findByReviewUserLocation(@PathParam("location") String movieName)
    {
        Query query =em.createNamedQuery("Review.findByReviewUserLocation");
        query.setParameter("movieName", movieName);
        String userLocation = String.valueOf(query.getResultList().get(0));
        return userLocation;
    }
}
