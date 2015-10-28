/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.repository;

import fit5042.assignment.entities.Users;
import java.util.List;
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
public class JPACustomerRepositoryImpl implements CustomerRepository {

    
    private int userID;
    private String userName;
    
    @PersistenceContext(unitName="Assignment-ejbPU")
    private EntityManager entityManager;

    @Override
    public Users searchCustomerByCombination(int userID, String lastName, String firstName, String type, String Email) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Users.class);
        Root<Users> u = query.from(Users.class);
        
        query.select(u).where(
                builder.equal(u.get("userid").as(String.class), userID),
                builder.equal(u.get("lastname").as(String.class), lastName),
                builder.equal(u.get("firstname").as(String.class), firstName),
                builder.equal(u.get("type").as(String.class), type),
                builder.equal(u.get("email").as(String.class),Email),
                builder.notEqual(u.get("type").as(String.class), "salesperson")
        );
        
        return (Users) entityManager.createQuery(query).getSingleResult();
    
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    

    @Override
    public int getByUserID(String username) {

         CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Users.class);
        Root<Users> u = query.from(Users.class);
        
        query.select(u.get("userid").as(Integer.class)).where(
                builder.equal(u.get("username").as(String.class), username));
        
        return  (int) entityManager.createQuery(query).getSingleResult();
    
    
    }
    
    
    
    

    @Override
    public Users getUserByUserID(int userid) {

     Users user = entityManager.find(Users.class, userid);
        return user;
    }

    @Override
    public Users getUserByUserName(String username) {

//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery query = builder.createQuery(Users.class);
//        Root<Users> u = query.from(Users.class);
//        
//        query.select(u).where(builder.equal(u.get("username").as(String.class), username));
//        
        
        Query query = entityManager.createNamedQuery(Users.GET_USER_BY_USERNAME);
        query.setParameter("username",username);
        
        return  (Users) query.getSingleResult();
    }

    @Override
    public void addUser(Users user) throws Exception {
        entityManager.persist(user);
    }

    @Override
    public void removerUser(int userid) {

        Users user = this.getUserByUserID(userid);
        if(user != null){
            entityManager.remove(user);
        }
    }

    @Override
    public void editUser(Users user) throws Exception {
        entityManager.merge(user);
    }

    @Override
    public List<Users> getAllUsers() throws Exception {
     return entityManager.createNamedQuery(Users.GET_ALL_USERS).getResultList();
    
    }
    
 
}
