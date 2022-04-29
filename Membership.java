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
 * Stores methods regarding members of Bookstore Management System
 */
interface displayzz {
    public abstract void display();
}

public abstract class Membership {
    // protected instance variables
    protected String firstName;
    protected String lastName;
    protected double totalSpending;
    protected String payMethod; 
    protected boolean isPremiumMember;
    protected boolean hasPaidPremium;
    protected double bookstoreBalance = 0;

    public Membership(String firstName, String lastName, double totalSpending, String payMethod, boolean isPremiumMember, boolean hasPaidPremium) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalSpending = totalSpending;
        this.payMethod = payMethod;
        this.isPremiumMember = isPremiumMember;
        this.hasPaidPremium = hasPaidPremium;
    }
    public Membership() {}
    public String getFirstName() { 
        return firstName; 
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public double getTotalSpending() {
        return totalSpending;
    }
    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }
    public String getPayMethod() {
        return payMethod;
    }
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public boolean getIsPremiumMember() {
        return isPremiumMember;
    }
    public void setIsPremiumMember(boolean isPremium) {
        if (isPremium) {
            isPremiumMember = true;
        }
        else {
            isPremiumMember = false;
        }
    }
    public boolean getHasPaidPremium() {
        return hasPaidPremium;
    }
    public void setHasPaidPremium(boolean hasPaidPremium) {
        this.hasPaidPremium = hasPaidPremium;
    }

    public double getBookstoreBalance() {
        return bookstoreBalance;
    }
    public void setBookstoreBalance(double bookstoreProfit) {
        this.bookstoreBalance = bookstoreProfit + bookstoreBalance;
    }
}

