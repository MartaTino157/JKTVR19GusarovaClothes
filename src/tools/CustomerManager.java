/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Customer;

/**
 *
 * @author pupil
 */
public class CustomerManager {
    public Customer crateCustomer(){
        Customer customer = new Customer();
        System.out.printf("Имя: ");
        System.out.printf("Фамилия: ");
        System.out.printf("Номер телефона: ");
        System.out.printf("Баланс счета: ");
        System.out.printf("");
        System.out.printf("");
        System.out.printf("");
        return customer;
    }
}
