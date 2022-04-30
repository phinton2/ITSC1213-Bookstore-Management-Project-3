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
    public Book(int productID, String itemType, String title, String authorArtist, int stockSize, double productPrice) {
        super(productID, itemType, title, authorArtist, stockSize, productPrice);
    }

    public void productDisplay () {
        System.out.println("=====================================================\n");
        System.out.println("\"" + title + "\" by " + authorArtist);
        System.out.println("Product ID: " + productID + "\t\t\t" + "Item Type: " + itemType);
        System.out.println("Copies available: " + stockSize + "\t\t" + "Price: " + productPrice +"\n");
        System.out.println("=====================================================");
    }
}
