/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.facades;

import entity.Customer;
import entity.Purchase;
import factory.ConnectSingleton;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author pupil
 */
public class PurchaseFacade extends AbstractFacade<Purchase>{

    public PurchaseFacade() {
        super(Purchase.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        ConnectSingleton connect = ConnectSingleton.getInstance();
        return connect.getEntityManager();
    }

    public List<Purchase> findAll(Customer customer) {
        try {
            return getEntityManager().createQuery("SELECT pr FROM Purchase pr WHERE pr.customer=:customer")
                    .setParameter("customer", customer)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
}
