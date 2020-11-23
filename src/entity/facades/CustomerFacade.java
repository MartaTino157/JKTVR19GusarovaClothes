/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.facades;

import entity.Customer;
import factory.ConnectSingleton;
import javax.persistence.EntityManager;

/**
 *
 * @author pupil
 */
public class CustomerFacade extends AbstractFacade<Customer>{

    public CustomerFacade() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        ConnectSingleton connect = ConnectSingleton.getInstance();
        return connect.getEntityManager();
    }
    
    
}