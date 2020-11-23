/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.User;
import factory.FacadeFactory;
import java.util.Scanner;
import tools.CustomerManager;
import tools.UserManager;

/**
 *
 * @author pupil
 */
public class SecManager {
    private Scanner scan = new Scanner(System.in);
    private UserManager userManager = new UserManager();
    private CustomerManager customerManager = new CustomerManager();
    
    public static enum role {CUSTOMER, MANAGER};
    
    public User checkInlogin(){
        do {            
           System.out.println("Ваш выбор:");
           System.out.println("0. Закрыть программу");
           System.out.println("1. Регистрация");
           System.out.println("2. Вход в систему");
           System.out.print("Введите номер задачи: ");
           String var = scan.nextLine(); 
            switch (var) {
                case "0":
                    System.out.println("Пока!");
                    System.exit(0);
                    break;
                case "1":
                    User user = userManager.createUser();
                    new FacadeFactory().getUserFacade().create(user);
                    break;
                case "2":
                    User checkInUser = userManager.getCheckInUser();
                    if(checkInUser == null) break;
                    return checkInUser;
                default:
                    System.out.println("Нет такой задачи.");
            }
        } while (true);
 
    }
}
