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
 * Stores methods regarding inventory of Bookstore Management System
 */
interface prodDisplay {
    public abstract void productDisplay();
}

public abstract class Inventory {
    // omitted randomizers
    /*
    protected int stockSize = ((int)(5 + Math.random() * 15));
    protected double productPrice = ((double)(8 + Math.random() * 20));
    protected double totalProductPrice;*/
    protected int productID;
    protected String itemType;
    protected String title;
    protected String authorArtist;
    protected int stockSize;
    protected double productPrice;
    protected double totalProductPrice;
    

    public Inventory() {}
    public Inventory(int productID, String itemType, String title, String authorArtist, int stockSize, double productPrice) {
        this.productID = productID;
        this.itemType = itemType;
        this.title = title;
        this.authorArtist = authorArtist;
        this.stockSize = stockSize;
        this.productPrice = productPrice;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID (int productID) {
        this.productID = productID;
    }
    public String itemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthorArtist (String authorArtist) {
        this.authorArtist = authorArtist;
    }
    public int getStockSize() {
        return stockSize;
    }
    public void setStockSize(int stockSize) {
        this.stockSize = stockSize;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    public void deductStockSize (int productsSold) {
        this.stockSize = stockSize - productsSold;
    }
    public void setTotalPurchasePrice (int productsBought) {
        this.totalProductPrice = getProductPrice() * productsBought; // could be problem area
    }
    public double getTotalPurchasePrice () {
        return totalProductPrice;
    }
}
