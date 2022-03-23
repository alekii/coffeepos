/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coffeepos.controller;

import coffeepos.model.CoffeeData;
import coffeepos.model.CoffeePrices;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField; 
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author  Alex M 23 March 2022
 */
public class CoffeeposController implements Initializable {
    Map<String,CoffeeData> coffee_receipt = new LinkedHashMap<>();
    String[] coffee_flavours = {"hazelnut","caramel","mocha","peppermint","vanilla"};
    int total_coffee_quantity = 0; 
    int coffee_total_cost = 0;
    CoffeePrices coffeePrices = new CoffeePrices();
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField hazelnut;
            
    @FXML
    private TextField caramel;
            
     @FXML
    private TextField mocha;
             
     @FXML
    private TextField peppermint;
              
     @FXML
    private TextField vanilla; 
      
     @FXML
    private TextField cash; 
     
     @FXML
    private Text cash_balance; 
     
     @FXML
    private TextArea receipt;
     
    @FXML
    private Text totalorchange; 
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         hazelnut.setText("1");
         caramel.setText("1");
         mocha.setText("1");
         peppermint.setText("1");
         vanilla.setText("1");
         cash.setText("1000");  
         cash_balance.setText("Kshs 0.00");   
    }   
    
    
    @FXML
    public void calculateTotal(){  
        coffee_total_cost = 0;
        receipt.clear(); 
        coffee_receipt.clear();
        totalorchange.setText("Total");
        String hazelnut_quantity = hazelnut.getText().trim(); 
        String caramel_quantity = caramel.getText().trim(); 
        String mocha_quantity = mocha.getText().trim();
        String peppermint_quantity = peppermint.getText().trim(); 
        String vanilla_quantity = vanilla.getText().trim(); 
        
        String[] orders = {hazelnut_quantity,caramel_quantity,mocha_quantity,peppermint_quantity,vanilla_quantity,};   
        
        for(int i=0;i<orders.length;i++){
            String coffee_quantity = orders[i]; 
            if(coffee_quantity.matches("^[0-9]+$") && Integer.parseInt(coffee_quantity) > 0){ 
            int quantity = Integer.parseInt(coffee_quantity); 
               int coffee_price = CoffeePrices.getInstance().getCoffeePrice(i);
               int coffee_subtotal = quantity * coffee_price; 
               coffee_total_cost += coffee_subtotal;
               CoffeeData coffeeData = new CoffeeData(coffee_price,quantity,coffee_subtotal);
               String coffee_flavour = coffee_flavours[i];
               coffee_receipt.put(coffee_flavour,coffeeData);
            } 
        } 
           cash_balance.setText("Kshs "+coffee_total_cost+".00");
    }
    @FXML
    public void printReceipt(){ 
        String cash_money = cash.getText().trim(); 
        if(cash_money.matches("^[0-9]+$") && Integer.parseInt(cash_money) > 0){
           int change = Integer.parseInt(cash_money) - coffee_total_cost;
           cash_balance.setText("Kshs "+change+".00");
           totalorchange.setText("Change");
        
        //Print Receipt or store data in NoSQL Database 
        Formatter heading = new Formatter();
        Formatter itemsFormatter = new Formatter();
         Formatter balancesFormatter = new Formatter();
        heading.format("%1s%25s%20s%21s\n","Item","Quantity","Price","Amount"); 
            receipt.setText("\n"+heading);
            for(Map.Entry<String,CoffeeData> coffee_item: coffee_receipt.entrySet()){
                int space_between_first_printed_items = 25;
                int space_between_other_printed_items = 20;
                String coffee_item_Key = coffee_item.getKey();
                int coffee_item_Key_length = coffee_item.getKey().length();
                int offset = space_between_first_printed_items - coffee_item_Key_length; 
                 if(coffee_item_Key_length==10){
                     offset -= 3;
                 } else if(coffee_item_Key =="vanilla"){ 
                     offset += 3;
                 }
                 
                StringBuilder first_spacing = new StringBuilder();
                StringBuilder spacing = new StringBuilder();
                for(int i=0; i<space_between_other_printed_items;i++){
                    spacing.append(" ");
                }
                for(int i=0; i<offset;i++){
                    first_spacing.append(" ");
                } 
                String s = coffee_item.getKey()+ first_spacing;
                s = s +"x"+coffee_item.getValue().getQuantity() + spacing;
                s = s + coffee_item.getValue().getPrice() + spacing;
                s = s + coffee_item.getValue().getTotal();
                itemsFormatter.format("%"+((11-offset)*1.8)+"s%"+20+"s%25s%20s\n",
                         coffee_item.getKey(),
                        "x "+coffee_item.getValue().getQuantity(),
                        coffee_item.getValue().getPrice(),
                        coffee_item.getValue().getTotal() );
                
               receipt.appendText(s+"\n");
           };
            balancesFormatter.format("%70s\n%70s\n%68s\n",
                    "Total:                Kshs "+coffee_total_cost,
                    "Cash:                Kshs "+cash_money,
                    "Change:             Kshs "+change);
            
            receipt.appendText("\n\n"+balancesFormatter); 
        }
    }
 
}
