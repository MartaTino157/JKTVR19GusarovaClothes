/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import savers.FileSaver;
import entity.Product;
import entity.facades.ProductFacade;
import factory.FacadeFactory;
import java.util.List;
import java.util.Scanner;
import jktvr19gusarovaclothes.App;

/**
 *
 * @author pupil
 */
public class ProductManager {
    private ProductFacade productFacade = FacadeFactory.getProductFacade();
    public Product createProduct(){
        Product product = new Product();
        System.out.printf("Название товара: ");
        Scanner scan = new Scanner(System.in);
        product.setName(scan.nextLine());
        System.out.printf("Страна производства: ");
        product.setCountry(scan.nextLine());
        double numPrice;
        do {            
            System.out.printf("Стоимость: ");
            String strPrice = scan.nextLine();
            try {
                numPrice = Double.parseDouble(strPrice);
                break;
            } catch (Exception e) {
                System.out.println("Используйте цифры!");
            }
        } while (true);
        product.setPrice(numPrice);
        productFacade.create(product);
        return product;
    }
    
    public boolean printListProducts(){
        List<Product> listProducts = productFacade.findAll();
        if(listProducts == null){
            System.out.println("Полки пусты!");
            return false;
        }
        int n = 0;
        for (Product listProduct : listProducts) {
            if(listProduct != null){
                System.out.println(n+1+". "+listProduct.toString());
                n++;
            }
        }
        return true;
    }
}
