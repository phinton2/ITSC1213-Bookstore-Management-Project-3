/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstoremanagementproject2;

/**
 *
 * @author Paydreanne E. Hinton
 * instructor Professor Van Custodio
 * courseSection ITSC1213-106-27949
 * Bookstore Management Project 2
 * Subclass for Inventory - DVD
 */
import java.text.*;
public class DVD extends Inventory implements prodDisplay {
    DecimalFormat df = new DecimalFormat("#.##");
    public DVD () {
        super();
    }
    public DVD(int stockSize, double productPrice) {
        super(stockSize, productPrice);
    }

    public void productDisplay () {
        String prodPrice = "DVDs cost $" + df.format(this.getProductPrice());
        String stoSize = "\nThere are currently " + this.getStockSize() + " DVDs available";
        System.out.println(prodPrice + stoSize);
    }
}

