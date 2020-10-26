/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19gusarovaclothes;

import tools.CustomerManager;
import entity.Customer;
import entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tools.ProductManager;
import tools.StorageManager;

/**
 *
 * @author pupil
 */
public class App {
    
    public static enum storageFile{PRODUCTS,CUSTOMERS};
    
    private Scanner scan = new Scanner(System.in);
    private List<Product> listProducts = new ArrayList<>();
    private List<Customer> listCustomers = new ArrayList<>();
    
    private ProductManager productManager = new ProductManager();
    private CustomerManager customerManager = new CustomerManager();
    private StorageManager storageManager = new StorageManager();
    
    public App() {
        List<Product> loadedProducts = storageManager.load(App.storageFile.PRODUCTS.toString());
        if(loadedProducts != null)listProducts = loadedProducts;
        
        List<Customer> loadedCustomers = storageManager.load(App.storageFile.CUSTOMERS.toString());
        if(loadedCustomers != null)listCustomers = loadedCustomers;
    }
    
    public void run() {
        System.out.println(" --- Добро пожаловать в магазин одежды! --- ");
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
                    productManager.addProductToArray(product, listProducts);
                    break;
                case "2":
                    System.out.println(" --- СПИСОК ТОВАРОВ --- ");
                    productManager.printListProducts(listProducts);
                    break;
                case "3":
                    System.out.println(" --- ДОБАВЛЕНИЕ ПОКУПАТЕЛЯ --- ");
                    break;
                case "4":
                    System.out.println(" --- СПИСОК ПОКУПАТЕЛЕЙ --- ");
                    break;
                case "5":
                    System.out.println(" --- СДЕЛАТЬ ПОКУПКУ --- ");
                    break;
                default:
                    System.out.println("Нет такой задачи!");
            }
        } while (repeat);
        
    }
    
}
