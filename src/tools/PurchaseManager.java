/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import savers.FileSaver;
import entity.Customer;
import entity.Product;
import entity.Purchase;
import entity.facades.CustomerFacade;
import entity.facades.ProductFacade;
import entity.facades.PurchaseFacade;
import factory.FacadeFactory;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import jktvr19gusarovaclothes.App;
import security.SecManager;

/**
 *
 * @author pupil
 */
public class PurchaseManager {
    private Scanner scan = new Scanner(System.in);
    private CustomerManager customerManager = new CustomerManager();
    private ProductManager productManager = new ProductManager();
    
    private ProductFacade productFacade = FacadeFactory.getProductFacade();
    private CustomerFacade customerFacade = FacadeFactory.getCustomerFacade();
    private PurchaseFacade purchaseFacade = FacadeFactory.getPurchaseFacade();
    
    public void makeDeal(){
        System.out.println(" ***** СПИСОК ТОВАРОВ ***** ");
        Long productNum;
        do {            
            if(!productManager.printListProducts()){
            return;
            }
            System.out.printf("Выберите номер товара: ");
                String productNumStr = scan.nextLine();
            try {
                productNum = Long.parseLong(productNumStr);
                break;
            } catch (Exception e) {
                System.out.println("Выберите номер из указанного выше списка");
                productNumStr = scan.nextLine();
            }
        } while (true);
        Product product = productFacade.find(productNum);
        Customer customer = null;
        if(SecManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            Long customerNum;
            do {            
                System.out.println(" ***** СПИСОК КЛИЕНТОВ ***** ");
                customerManager.printListCustomers();
                System.out.printf("Выберите номер клиента: ");
                String productNumStr = scan.nextLine();
                try {
                    customerNum = Long.parseLong(productNumStr);
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного выше списка");
                }
            } while (true);
            customer = customerFacade.find(customerNum);
        }else if (SecManager.role.CUSTOMER.toString().equals(App.loggedInUser.getRole())){
            customer = App.loggedInUser.getCustomer();
        }
        Calendar calendar = new GregorianCalendar();
        
        double residual;
        residual = customer.getBalance() - product.getPrice();
        if (residual < 0){
            System.out.println("Недостаточно средств для покупки");
            System.out.println("Баланс: "+customer.getBalance()+"€");
            return;
        }else{
            customer.setBalance(residual);
            Purchase purchase =  new Purchase(customer, product, calendar.getTime());
            purchaseFacade.create(purchase);
        }
    }
   
    public boolean printListPurchases(){
        if(SecManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            List<Purchase> listPurchases = purchaseFacade.findAll();
            if(listPurchases == null){
                System.out.println("Журнал покупок пуст");
                return false;
            }
            for (int i = 0; i < listPurchases.size(); i++) {
                System.out.printf("%d. Клиент %s %s купил \"%s\"%n"
                ,listPurchases.get(i).getId()
                ,listPurchases.get(i).getCustomer().getFirstName()
                ,listPurchases.get(i).getCustomer().getLastName()
                ,listPurchases.get(i).getProduct().getName()
                );
            }
        }else if (SecManager.role.CUSTOMER.toString().equals(App.loggedInUser.getRole())){
            List<Purchase> listPurchases = purchaseFacade.findAll(App.loggedInUser.getCustomer());
            if(listPurchases == null){
                System.out.println("Журнал покупок пуст");
                return false;
            }
            for (int i = 0; i < listPurchases.size(); i++) {
                System.out.printf("%d. Клиент %s %s купил \"%s\"%n"
                ,listPurchases.get(i).getId()
                ,listPurchases.get(i).getCustomer().getFirstName()
                ,listPurchases.get(i).getCustomer().getLastName()
                ,listPurchases.get(i).getProduct().getName()
                );
            }
        }
    return true;
    }
}
    

