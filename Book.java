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

public class Book extends Inventory implements prodDisplay {
    DecimalFormat df = new DecimalFormat("#.##");
    public Book () {
        super();
    }
    public Book(int stockSize, double productPrice) {
        super(stockSize, productPrice);
    }

    public void productDisplay () {
        String prodPrice = "Books cost $" + df.format(this.getProductPrice());
        String stoSize = "\nThere are currently " + this.getStockSize() + " books available";
        System.out.println(prodPrice + stoSize);
    }
}
