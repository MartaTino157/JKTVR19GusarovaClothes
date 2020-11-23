/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Customer;
import entity.User;
import entity.facades.CustomerFacade;
import entity.facades.UserFacade;
import factory.FacadeFactory;
import java.util.List;
import java.util.Scanner;
import security.SecManager;

/**
 *
 * @author pupil
 */
public class UserManager {
    private Scanner scan = new Scanner(System.in);
    private CustomerFacade customerFacade = FacadeFactory.getCustomerFacade();
    private UserFacade userFacade = FacadeFactory.getUserFacade();
    
    public User createUser(){
        CustomerManager customerManager = new CustomerManager();
        Customer customer = customerManager.createCustomer();
        User user = new User();
        System.out.println(" --- Добавить пользователя --- ");
        System.out.printf("Логин : ");
        user.setLogin(scan.nextLine());
        System.out.printf("Введите пароль: ");
        user.setPassword(scan.nextLine());
        int numRole;
        do {            
            System.out.println("Список ролей: ");
            for (int i = 0; i < SecManager.role.values().length; i++) {
                System.out.printf("%d. %s%n"
                    ,i+1
                    ,SecManager.role.values()[i].toString()
                );
            }
            System.out.printf("Введите номер роли: ");
            String numRoleStr = scan.nextLine();
            try {
                numRole = Integer.parseInt(numRoleStr);
                break;
            } catch (Exception e) {
                System.out.println("Вводите цифрами!");
            }
        } while (true);
        user.setRole(SecManager.role.values()[numRole-1].toString());
        user.setCustomer(customer);
        userFacade.create(user);
        return user;
    }
    
    public User getCheckInUser() {
        System.out.println("-----Вход в систему------");
        System.out.print("Login: ");
        String login = scan.nextLine();
        System.out.print("Password: ");
        String password = scan.nextLine();
        List<User> listUsers = userFacade.findAll();
        if(listUsers == null){
            System.out.println("У вас нет права входа! Зарегистрируйтесь");
            return null;
        }
        for (int i = 0; i < listUsers.size(); i++) {
            if(listUsers.get(i) != null && listUsers.get(i).getLogin().equals(login)){
                for (int j = 0; j < 2; j++) {
                    if(listUsers.get(i).getPassword().equals(password)){
                        return listUsers.get(i);
                    }else{
                        System.out.println("Попробуй еще раз");
                        password = scan.nextLine();
                    }
                }
                System.out.println("======================");
                System.out.println("У вас нет права входа!");
                System.out.println("======================");
                return null;
            }
        }
        System.out.println("========================================");
        System.out.println("У вас нет права входа! Зарегистрируйтесь");
        System.out.println("========================================");
        return null;
    }
}
