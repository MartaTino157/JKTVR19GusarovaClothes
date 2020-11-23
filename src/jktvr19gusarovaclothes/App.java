/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19gusarovaclothes;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import entity.User;
import java.util.List;
import savers.BaseSaver;
import security.SecManager;
import savers.StorageManagerInterface;
import ui.UserInterface;

/**
 *
 * @author pupil
 */
public class App {
    
    public static enum storageFile{PRODUCTS,CUSTOMERS,PURCHASES,USERS};
//    private StorageManagerInterface storageManager = new FileSaver();
    private StorageManagerInterface storageManager = new BaseSaver();
    
    public static User loggedInUser;
    
    public App() {
        System.out.println("Сохраняем данные в базу");
    }
    
    public void run() {
        System.out.println(" --- Добро пожаловать в магазин одежды! --- ");
        SecManager secManager = new SecManager();
        App.loggedInUser = secManager.checkInlogin();
        UserInterface userInterface = new UserInterface();
        if(App.loggedInUser == null){
            System.out.println("У вас нет права доступа");
            return; 
        }
        if(SecManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            userInterface.printManagerUI();
        }else if(SecManager.role.CUSTOMER.toString().equals(App.loggedInUser.getRole())){
            userInterface.printCustomerUI();
        }
    }
    
}
