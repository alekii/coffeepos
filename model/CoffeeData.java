/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coffeepos.model;

import coffeepos.model.CoffeePrices;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class CoffeeData { 
    // we need to get the price and amount of each coffee item 
    private int price;
    private int quantity;
    private int total;  

    public CoffeeData(int price, int quantity, int total) {
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }
   
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

   
 
}
