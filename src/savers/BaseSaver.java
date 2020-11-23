/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savers;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jktvr19gusarovaclothes.App;

/**
 *
 * @author pupil
 */
public class BaseSaver implements StorageManagerInterface{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19GusarovaClothesPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    @Override
    public void save(List arrayList, String fileName) {
        tx.begin();
            for (int i = 0; i < arrayList.size(); i++) {
                if(Product.class.equals(arrayList.get(i).getClass())){
                    List<Product> listProducts = (List<Product>) arrayList;
                    if(listProducts.get(i).getId() == null){
                        em.persist(listProducts.get(i));
                    }
                }
                if(Customer.class.equals(arrayList.get(i).getClass())){
                    List<Customer> listCustomers = (List<Customer>) arrayList;
                    Customer customer = listCustomers.get(i);
                    if(customer.getId() == null){
                        em.persist(customer);
                    }
                }
                if(User.class.equals(arrayList.get(i).getClass())){
                    List<User> listUsers = (List<User>) arrayList;
                    User user = listUsers.get(i);
                    if(user.getId() == null){
                        em.persist(user);
                    }
                }
                if(Purchase.class.equals(arrayList.get(i).getClass())){
                    List<Purchase> listPurchases = (List<Purchase>) arrayList;
                    if(listPurchases.get(i).getId() == null){
                        em.persist(listPurchases.get(i));
                    }else{
                        em.merge(listPurchases.get(i));
                    }
                }
            }
        tx.commit();
    }

    @Override
    public List load(String fileName) {
       if(App.storageFile.PRODUCTS.toString().equals(fileName)){
            List<Product> listProducts = em.createQuery("SELECT p FROM Product p")
                    .getResultList();
            return listProducts;
        }
        if(App.storageFile.CUSTOMERS.toString().equals(fileName)){
            List<Customer> listCustomers = em.createQuery("SELECT c FROM Customer c")
                    .getResultList();
            return listCustomers;
        }
        if(App.storageFile.USERS.toString().equals(fileName)){
            List<User> listUsers = em.createQuery("SELECT u FROM User u")
                    .getResultList();
            return listUsers;
        }
        if(App.storageFile.PURCHASES.toString().equals(fileName)){
            List<Purchase> listPurchases = em.createQuery("SELECT pr FROM Purchase pr")
                    .getResultList();
            return listPurchases;
        }
        return new ArrayList(); 
    }
    
    
}
