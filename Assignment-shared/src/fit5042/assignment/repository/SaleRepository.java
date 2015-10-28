/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.repository;

import fit5042.assignment.entities.Sale;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author daniel
 */
@Remote
public interface SaleRepository {
    

      public void addSale(Sale sale) throws Exception;
      
      
       public List<Sale> getAllSale() throws Exception;
       
       
       
}
