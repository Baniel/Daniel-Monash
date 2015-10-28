/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.assignment.repository;

import fit5042.assignment.entities.Car;
import fit5042.assignment.entities.Sale;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author daniel
 */
@Stateless
public class JPASaleRepositoryImpl implements SaleRepository {

    
    
    @PersistenceContext(unitName="Assignment-ejbPU")
    private EntityManager entityManager;
    
    
    
    @Override
    public void addSale(Sale sale) throws Exception {
          entityManager.persist(sale);   
    }

    @Override
    public List<Sale> getAllSale() throws Exception {

        return entityManager.createNamedQuery(Sale.GET_ALL_SALE).getResultList();
    }


    
}
