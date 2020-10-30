/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Customer;
import java.util.List;
import java.util.Scanner;
import jktvr19gusarovaclothes.App;

/**
 *
 * @author pupil
 */
public class CustomerManager {
    public Customer createCustomer(){
        Customer customer = new Customer();
        System.out.printf("Имя: ");
        Scanner scan = new Scanner(System.in);
        customer.setFirstName(scan.nextLine());
        System.out.printf("Фамилия: ");
        customer.setLastName(scan.nextLine());
        System.out.printf("Номер телефона: ");
        customer.setPhone(scan.nextLine());
        double numBalance;
        do {            
            System.out.printf("Баланс счета: ");
            String strBalance = scan.nextLine();
            try {
                numBalance = Double.parseDouble(strBalance);
                break;
            } catch (Exception e) {
                System.out.println("Используйте цифры!");
            }
        } while (true);
        customer.setBalance(numBalance);
        return customer;
    }
    public void addCustomerToArray(Customer customer, List<Customer> listCustomers){
        listCustomers.add(customer);
        StorageManager storageManager = new StorageManager();
        storageManager.save(listCustomers, App.storageFile.CUSTOMERS.toString());
    }
    public void printListCustomers(List<Customer> listCustomers){
        if(listCustomers == null || listCustomers.size() < 1){
            System.out.println("Список клиентов пуст!");
            return;
        }
        int n = 0;
        for (Customer customer : listCustomers) {
            if(customer != null){
                System.out.println(n+1+". "+customer.toString());
                n++;
            }
        }
    }
}
