/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import entity.facades.CustomerFacade;
import entity.facades.ProductFacade;
import entity.facades.PurchaseFacade;
import entity.facades.UserFacade;

/**
 *
 * @author pupil
 */
public class FacadeFactory {
    public static ProductFacade getProductFacade(){
        return new ProductFacade();
    }
    public static CustomerFacade getCustomerFacade(){
        return new CustomerFacade();
    }
    public static UserFacade getUserFacade(){
        return new UserFacade();
    }
    public static PurchaseFacade getPurchaseFacade(){
        return new PurchaseFacade();
    }
}
