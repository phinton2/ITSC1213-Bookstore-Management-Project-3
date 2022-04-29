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
    protected int stockSize = ((int)(5 + Math.random() * 15));
    protected double productPrice = ((double)(8 + Math.random() * 20));
    protected double totalProductPrice;

    public Inventory() {}
    public Inventory(int stockSize, double productPrice) {
        this.stockSize = stockSize;
        this.productPrice = productPrice;
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

