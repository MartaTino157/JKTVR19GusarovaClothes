/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.facades;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author pupil
 */
public abstract class AbstractFacade<F> {
    private Class<F> entityClass;
    public AbstractFacade(Class<F> entityClass) {
        this.entityClass = entityClass;
    }
    protected abstract EntityManager getEntityManager();
    public void create(F entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
    }
    public  List<F> findAll(){
       try {
            return getEntityManager().createQuery("SELECT en FROM "+entityClass.getName()+" en")
                    .getResultList();
        } catch (Exception e) {
            return null;
        } 
    }
    public F find(Long id) {
        try {
            return (F) getEntityManager().createQuery("SELECT en FROM "+entityClass.getName()+" en WHERE en.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    public void edit(F entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();
    }
    
    
}
