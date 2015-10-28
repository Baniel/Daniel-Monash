/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.repository;

import fit5042.assignment.entities.Users;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author daniel
 */

@Remote
public interface CustomerRepository {
    
    public Users searchCustomerByCombination(int userID,String lastName,String firstName,String type,String Email);
    
    public int getByUserID(String uername);
    
    public Users getUserByUserID(int userid);
    
    public Users getUserByUserName(String username);
    
    //CURD
    public void addUser(Users user) throws Exception;
    
    public void removerUser(int userid);
    
    public void editUser(Users user) throws Exception;
    
        public List<Users> getAllUsers() throws Exception;
   
}
