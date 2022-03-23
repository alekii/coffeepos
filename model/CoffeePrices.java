/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coffeepos.model;

import java.util.ArrayList;

/**
 *
 * @author Alex M 23 March 2022
 */
public class CoffeePrices {
   private static CoffeePrices instance = new CoffeePrices();
   
    private final ArrayList<Integer> coffee_prices= new ArrayList<>();
    private final int hazelnut_price = 115;
    private final int caramel_price = 240;
    private final int mocha_price = 140;
    private final int peppermint_price = 100;
    private final int vanilla_price = 180;
    
     public static CoffeePrices getInstance() {
         return instance;
     }
    public CoffeePrices() {
        
    }
   
    public int getHazelnut_price() {
        return hazelnut_price;
    }

    public int getCaramel_price() {
        return caramel_price;
    }

    public int getMocha_price() {
        return mocha_price;
    }

    public int getPeppermint_price() {
        return peppermint_price;
    }

    public int getVanilla_price() {
        return vanilla_price;
    }

    public void LoadCoffeePrices() {
       coffee_prices.add(hazelnut_price);
       coffee_prices.add(caramel_price);
       coffee_prices.add(mocha_price);
       coffee_prices.add(peppermint_price);
       coffee_prices.add(vanilla_price);
    }
    
    public ArrayList getCoffeePrices(){
        return coffee_prices;
    }
    
     public int getCoffeePrice(int id) { 
        return coffee_prices.get(id);
    }

     
    
}
