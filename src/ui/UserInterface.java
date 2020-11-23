/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import entity.User;
import java.util.List;
import java.util.Scanner;
import tools.CustomerManager;
import tools.ProductManager;
import tools.PurchaseManager;
import tools.UserManager;

/**
 *
 * @author pupil
 */
public class UserInterface {
    private Scanner scan = new Scanner(System.in);
    private ProductManager productManager = new ProductManager();
    private CustomerManager customerManager = new CustomerManager();
    private PurchaseManager purchaseManager = new PurchaseManager();
    
    public void printManagerUI(){
        boolean repeat = true;
        do {            
            System.out.println("=============================================");
            System.out.println("Список операций: ");
            System.out.println("0. Покинуть магазин");
            System.out.println("1. Добавить товар");
            System.out.println("2. Просмотреть список товаров");
            System.out.println("3. Добавить покупателя");
            System.out.println("4. Просмотреть список покупателей");
            System.out.println("5. Купить товар");
            System.out.println("6. Просмотреть список покупок");
            System.out.println("=============================================");
            System.out.printf("Выберите номер операции: ");
            String var = scan.nextLine();
            switch (var) {
                case "0":
                    System.out.println(" --- ВЫХОД ИЗ МАГАЗИНА --- ");
                    repeat = false;
                    break;
                case "1":
                    System.out.println(" --- ДОБАВЛЕНИЕ ТОВАРА --- ");
                    Product product = productManager.createProduct();
                    break;
                case "2":
                    System.out.println(" --- СПИСОК ТОВАРОВ --- ");
                    productManager.printListProducts();
                    break;
                case "3":
                    System.out.println(" --- ДОБАВЛЕНИЕ ПОКУПАТЕЛЯ --- ");
                    UserManager userManager = new UserManager();
                    User user = userManager.createUser();
                    break;
                case "4":
                    System.out.println(" --- СПИСОК ПОКУПАТЕЛЕЙ --- ");
                    customerManager.printListCustomers();
                    break;
                case "5":
                    System.out.println(" --- СДЕЛАТЬ ПОКУПКУ --- ");
                    purchaseManager.makeDeal();
                    break;
                case "6":
                    System.out.println(" --- СПИСОК ПОКУПОК --- ");
                    purchaseManager.printListPurchases();
                    break;
                default:
                    System.out.println("Нет такой задачи!");
            }
        } while (repeat);
    }
    public void printCustomerUI(){
        boolean repeat = true;
        do {            
            System.out.println("=============================================");
            System.out.println("Список операций: ");
            System.out.println("0. Покинуть магазин");
            System.out.println("1. Просмотреть список товаров");
            System.out.println("2. Купить товар");
            System.out.println("3. Просмотреть журнал покупок");
            System.out.println("=============================================");
            System.out.printf("Выберите номер операции: ");
            String var = scan.nextLine();
            switch (var) {
                case "0":
                    System.out.println(" --- ВЫХОД ИЗ МАГАЗИНА --- ");
                    repeat = false;
                    break;
                case "1":
                    System.out.println(" --- СПИСОК ТОВАРОВ --- ");
                    productManager.printListProducts();
                    break;
                case "2":
                    System.out.println(" --- СДЕЛАТЬ ПОКУПКУ --- ");
                    purchaseManager.makeDeal();
                    break;
                case "3":
                    System.out.println(" --- ЖУРНАЛ ПОКУПОК --- ");
                    purchaseManager.printListPurchases();
                    break;
                default:
                    System.out.println("Нет такой задачи!");
            }
        } while (repeat);
    }
}
