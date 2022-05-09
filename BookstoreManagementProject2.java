/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bookstoremanagementproject2;
// The program can currently take customer orders but is unable to assign total amount spent for customer, apply discounts, and print csv file after program closes
// REMAINING REQUIREMENTS: 1) store total cost in customer info's total spentI and 3) print csv file after store closes
// additionally monetary cost of purchase is being stored in the orderList array


/**
 *
 * @author Paydreanne E. Hinton
 * instructor Nadia Najjar
 * courseSection ITSC1213-106-27949
 * Bookstore Management Project 3
 * Executes main program for bookstore management system
 */

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.*;
import java.text.*;

public class BookstoreManagementProject2 {
    public static void main (String[] args) throws FileNotFoundException , IOException {
        boolean isRunning = true;
        DecimalFormat df = new DecimalFormat("#.##");
        Regular r = null;
        Premium p = null;
        double bookstoreBalance = 0;
        ArrayList<Membership> memberList = new ArrayList(); // contains list of premium/regular members
        ArrayList<Book> bookList = new ArrayList<>();
        ArrayList<CD> cdList = new ArrayList<>();
        ArrayList<DVD> dvdList = new ArrayList<>();
        ArrayList<Double> pricingOrderList = new ArrayList<>();
        ArrayList<String> orderList = new ArrayList<>();
        ArrayList<Integer> idPurchase = new ArrayList<>();
        ArrayList<Integer> purchaseQTY = new ArrayList<>();


        Book book = new Book();
        CD cd = new CD();
        DVD dvd = new DVD();
        Scanner scnr = new Scanner(System.in);

        // Set stock size and pricing
        // PROJECT 3 START
        String line;
        File file = new File("ProductInventory-1.csv");
        Scanner in = null;
        // try {
            in = new Scanner(file);
            while(in.hasNext())
            {
                line=in.nextLine();
                if(line.contains("book")) { // if line contains book
                    String[] details = line.split(",");

                    // variables
                    int productID = Integer.parseInt(details[0]); 
                    String itemType = details[1]; 
                    String title = details[2]; 
                    String authorArtist = details[3]; 
                    int stockSize = Integer.parseInt(details[4]); 
                    double productPrice = Double.parseDouble(details[5]);

                    // object
                    Book bookz = new Book(productID, itemType, title, authorArtist, stockSize, productPrice);
                    bookList.add(bookz);
                }
                else if(line.contains("cd")) { // if line contains cd

                    String[] details = line.split(",");

                    // variables
                    int productID = Integer.parseInt(details[0]); 
                    String itemType = details[1]; 
                    String title = details[2]; 
                    String authorArtist = details[3]; 
                    int stockSize = Integer.parseInt(details[4]); 
                    double productPrice = Double.parseDouble(details[5]);

                    // object
                    CD cdz = new CD(productID, itemType, title, authorArtist, stockSize, productPrice);
                    cdList.add(cdz);
                }
                else if(line.contains("dvd")) { // if line contains dvdd

                    String[] details = line.split(",");

                    // variables
                    int productID = Integer.parseInt(details[0]); 
                    String itemType = details[1]; 
                    String title = details[2]; 
                    String authorArtist = details[3]; 
                    int stockSize = Integer.parseInt(details[4]); 
                    double productPrice = Double.parseDouble(details[5]);

                    // object
                    DVD dvdz = new DVD(productID, itemType, title, authorArtist, stockSize, productPrice);
                    dvdList.add(dvdz);
                }
            }        

        while (isRunning) {
            // see what user wants to do
            System.out.println("\nWhat would you like to do now? \n");
            System.out.println("1) Take customer order");
            System.out.println("2) View all customers' info"); // will include payment method, membership status, total spent at bookstore
            System.out.println("3) View all premium members");
            System.out.println("4) View all free members");
            System.out.println("5) Check store inventory/product prices");
            System.out.println("6) Check store balance");
            System.out.println("7) Exit program");

            boolean choiceComplete = false;
            int choice = scnr.nextInt();
            
            // Take customer order
            while (choice == 1 && !choiceComplete) {
                System.out.println("What is the customer's current membership status?");
                System.out.println("1) Regular (free)\n2) Premium (paid)");
                int isPremMem = scnr.nextInt();
                if (isPremMem == 1) { // REGULAR
                    System.out.println("What is the customer's first name?");
                    String firstName = scnr.next();
                    System.out.println("What is the customer's last name?");
                    String lastName = scnr.next();
                    System.out.println("What is the customer's preferred payment method for this purchase?");
                    System.out.println("\n1) Credit card\n2) Debit card\n3) Cash");
                    int payMethodNum = scnr.nextInt();
                    String payMethod = "";
                    switch(payMethodNum) {
                        case 1:
                            payMethod = "Credit card";
                            break;
                        case 2:
                            payMethod = "Debit card";
                            break;
                        case 3:
                            payMethod = "Cash";
                            break;
                        default:
                            payMethod = null;
                    }
                    

                    // adds member information to bookstore member listing
                    Regular regMem = new Regular(firstName, lastName, 0, payMethod, false, false,pricingOrderList, orderList);                
                    memberList.add(regMem);
                    r = regMem;

                    // Displays customer information after fulfillment
                    System.out.println("Here's the customer's information: ");
                    System.out.println("\nCustomer name: " + r.getFirstName() + " " + r.getLastName() + "\nPremium member? " + r.getIsPremiumMember() + "\nPaid monthly premium? " + r.getHasPaidPremium() + "\nTotal spent at bookstore: " + "$" + r.getTotalSpending() + "\nPreferred Payment Method: " + r.getPayMethod() + "\n");
                    
                    // DISPLAY WHAT STORE HAS ON SALE
                    // Project 3 Starting...
                    System.out.println("What the bookstore has on sale now: ");
                    boolean continueShopping = true;
                    while (continueShopping) {
                    for (int i = 0; i < bookList.size(); i++) {System.out.println(bookList.get(i).getProductID() + ") \"" + bookList.get(i).getTitle() + "\" by " + bookList.get(i).getAuthorArtist() + " (Book) - $" + bookList.get(i).getProductPrice());}
                    for (int i = 0; i < cdList.size(); i++) {System.out.println(cdList.get(i).getProductID() + ") \"" + cdList.get(i).getTitle() + "\" by " + cdList.get(i).getAuthorArtist()+ " (CD) - $" + cdList.get(i).getProductPrice());}
                    for (int i = 0; i < dvdList.size(); i++) {System.out.println(dvdList.get(i).getProductID() + ") \"" + dvdList.get(i).getTitle() + "\" by " + dvdList.get(i).getAuthorArtist() + " (DVD) - $" + dvdList.get(i).getProductPrice());}
                    System.out.println("Enter number beside the product to purchase");
                    int purchaseChoice = scnr.nextInt();
                    
                    if ((purchaseChoice >= 0 && purchaseChoice < 10)){ // WHEN PURCHASING BOOK
                        System.out.println("How many copies of \"" + bookList.get(purchaseChoice).getTitle() + "\" would you like?");
                        int purchaseQuantity = scnr.nextInt();

                        if (purchaseQuantity > bookList.get(purchaseChoice).getStockSize() && bookList.get(purchaseChoice).getStockSize() != 0) {
                            System.out.println("We only have " + bookList.get(purchaseChoice).getStockSize() + " copies of \"" + bookList.get(purchaseChoice).getTitle() + "\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemaining = scnr.next().charAt(0);
                            if (purchaseRemaining == 'Y' || purchaseRemaining == 'y') {
                                purchaseQuantity = bookList.get(purchaseChoice).getStockSize();
                                bookList.get(purchaseChoice).setStockSize(purchaseChoice);
                                pricingOrderList.add((bookList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                                idPurchase.add(purchaseChoice);
                                purchaseQTY.add(purchaseQuantity);
                                r.orderList.add(purchaseQuantity + " \"" + bookList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                                orderList.add(purchaseQuantity + " \"" + bookList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                            }
                            else if (bookList.get(purchaseChoice).getStockSize() == 0) {
                                purchaseQuantity = bookList.get(0).getStockSize();
                            }
                            else if (purchaseRemaining == 'N' || purchaseRemaining == 'n') {
                                purchaseQuantity = 0;
                            }
                        }
                        else if (purchaseQuantity <= bookList.get(purchaseChoice).getStockSize()) {
                            bookList.get(purchaseChoice).deductStockSize(purchaseQuantity);
                            bookList.get(purchaseChoice).setTotalPurchasePrice(purchaseQuantity);
                            orderList.add(purchaseQuantity + " " + bookList.get(purchaseChoice).getTitle() + "\t\t\t");
                            r.orderList.add(purchaseQuantity + " \"" + bookList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                            pricingOrderList.add((bookList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                            idPurchase.add(purchaseChoice);
                            purchaseQTY.add(purchaseQuantity);
                        }
                        else if (bookList.get(purchaseChoice).getStockSize() == 0) {
                            purchaseQuantity = 0;
                            System.out.println("\nWe're sorry, \"" + bookList.get(purchaseChoice).getTitle() + "\" is currently out of stock.");
                        }
                        }
                    else if (purchaseChoice >= 10 && purchaseChoice <= 12){ // WHEN PURCHASING CD
                        if (purchaseChoice == 10)
                        purchaseChoice = 0;
                        else if (purchaseChoice == 11)
                        purchaseChoice = 1;
                        else if (purchaseChoice == 12)
                        purchaseChoice = 2;
                        System.out.println("How many copies of \"" + cdList.get(purchaseChoice).getTitle() + "\" would you like?");
                        int purchaseQuantity = scnr.nextInt();

                        if (purchaseQuantity > cdList.get(purchaseChoice).getStockSize() && cdList.get(purchaseChoice).getStockSize() != 0) {
                            System.out.println("We only have " + cdList.get(purchaseChoice).getStockSize() + " copies of \"" + cdList.get(purchaseChoice).getTitle() + "\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemaining = scnr.next().charAt(0);
                            if (purchaseRemaining == 'Y' || purchaseRemaining == 'y') {
                                purchaseQuantity = cdList.get(purchaseChoice).getStockSize();
                                cdList.get(purchaseChoice).setStockSize(purchaseChoice);
                                pricingOrderList.add((cdList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                                idPurchase.add(purchaseChoice);
                                purchaseQTY.add(purchaseQuantity);
                                orderList.add(purchaseQuantity + " \"" + cdList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                                r.orderList.add(purchaseQuantity + " \"" + cdList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                            }
                            else if (cdList.get(purchaseChoice).getStockSize() == 0) {
                                purchaseQuantity = cdList.get(0).getStockSize();
                            }
                            else if (purchaseRemaining == 'N' || purchaseRemaining == 'n') {
                                purchaseQuantity = 0;
                            }
                        }
                        else if (purchaseQuantity <= cdList.get(purchaseChoice).getStockSize()) {
                            cdList.get(purchaseChoice).deductStockSize(purchaseQuantity);
                            cdList.get(purchaseChoice).setTotalPurchasePrice(purchaseQuantity);
                            orderList.add(purchaseQuantity + " " + cdList.get(purchaseChoice).getTitle() + "\t\t\t");
                            r.orderList.add(purchaseQuantity + " \"" + cdList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                            idPurchase.add(purchaseChoice);
                            purchaseQTY.add(purchaseQuantity);
                            pricingOrderList.add((cdList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                        }
                        else if (cdList.get(purchaseChoice).getStockSize() == 0) {
                            purchaseQuantity = 0;
                            System.out.println("\nWe're sorry, \"" + cdList.get(purchaseChoice).getTitle() + "\" is currently out of stock.");
                        }
                    }
                    else if (purchaseChoice == 13 || purchaseChoice == 14) { // WHEN PURCHASING DVD
                        if (purchaseChoice == 13)
                        purchaseChoice = 0;
                        else if (purchaseChoice == 14)
                        purchaseChoice = 1;
                        System.out.println("How many copies of \"" + dvdList.get(purchaseChoice).getTitle() + "\" would you like?");
                        int purchaseQuantity = scnr.nextInt();

                        if (purchaseQuantity > dvdList.get(purchaseChoice).getStockSize() && dvdList.get(purchaseChoice).getStockSize() != 0) {
                            System.out.println("We only have " + dvdList.get(purchaseChoice).getStockSize() + " copies of \"" + dvdList.get(purchaseChoice).getTitle() + "\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemaining = scnr.next().charAt(0);
                            if (purchaseRemaining == 'Y' || purchaseRemaining == 'y') {
                                purchaseQuantity = dvdList.get(purchaseChoice).getStockSize();
                                dvdList.get(purchaseChoice).setStockSize(purchaseChoice);
                                pricingOrderList.add((dvdList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                                idPurchase.add(purchaseChoice);
                                purchaseQTY.add(purchaseQuantity);
                                orderList.add(purchaseQuantity + " \"" + dvdList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                                r.orderList.add(purchaseQuantity + " " + dvdList.get(purchaseChoice).getTitle() + "\t\t\t");
                            }
                            else if (dvdList.get(purchaseChoice).getStockSize() == 0) {
                                purchaseQuantity = dvdList.get(0).getStockSize();
                            }
                            else if (purchaseRemaining == 'N' || purchaseRemaining == 'n') {
                                purchaseQuantity = 0;
                            }
                        }
                        else if (purchaseQuantity <= dvdList.get(purchaseChoice).getStockSize()) {
                            dvdList.get(purchaseChoice).deductStockSize(purchaseQuantity);
                            dvdList.get(purchaseChoice).setTotalPurchasePrice(purchaseQuantity);
                            orderList.add(purchaseQuantity + " " + dvdList.get(purchaseChoice).getTitle() + "\t\t\t");
                            r.orderList.add(purchaseQuantity + " " + dvdList.get(purchaseChoice).getTitle() + "\t\t\t");
                            idPurchase.add(purchaseChoice);
                            purchaseQTY.add(purchaseQuantity);
                            pricingOrderList.add((dvdList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                        }
                        else if (dvdList.get(purchaseChoice).getStockSize() == 0) {
                            purchaseQuantity = 0;
                            System.out.println("\nWe're sorry, \"" + dvdList.get(purchaseChoice).getTitle() + "\" is currently out of stock.");
                        }
                    }
                    System.out.println("Does the customer want to purchase anything else? (y/n)");
                        char purchaseMore = scnr.next().charAt(0);
                        if (purchaseMore == 'Y' || purchaseMore == 'y') {}
                        else if (purchaseMore == 'N' || purchaseMore == 'n') {
                            System.out.println("\n\nHere is " + regMem.getFirstName() + " " + regMem.getLastName() + "'s receipt: \n");
                            System.out.println(r.getOrderList() + " -- $" + r.getPricingOrderList());
                            double sum = 0;
                            for(Double d : pricingOrderList)
                            sum += d;
                            double discountTotal = sum * .85;
                            r.setTotalSpending(discountTotal);
                            System.out.println("INITIAL TOTAL \t\t\t\t$" + sum);
                            System.out.println("FINAL TOTAL (REGULAR DISCOUNT) \t\t$" + discountTotal);
                            bookstoreBalance = bookstoreBalance + discountTotal;
                            r.orderList.clear();
                            r.pricingOrderList.clear();
                            continueShopping = false;
                        }
                }
                    
                }
                else if (isPremMem == 2) { // PREMIUM
                    System.out.println("Has this premium member paid their monthly fee yet? (y/n)");
                    char hasPaidPremium = scnr.next().charAt(0);
                    boolean paidPremium = false;
                    if (hasPaidPremium == 'Y' || hasPaidPremium == 'y')
                        paidPremium = true;
                    else if (hasPaidPremium == 'N' || hasPaidPremium == 'n')
                        paidPremium = false;

                    System.out.println("What is the customer's first name?");
                    String firstName = scnr.next();
                    System.out.println("What is the customer's last name?");
                    String lastName = scnr.next();
                    System.out.println("What is the customer's preferred payment method for this purchase?");
                    System.out.println("\n1) Credit card\n2) Debit card\n3) Cash");
                    int payMethodNum = scnr.nextInt();
                    String payMethod = "";
                    switch(payMethodNum) {
                        case 1:
                            payMethod = "Credit card";
                            break;
                        case 2:
                            payMethod = "Debit card";
                            break;
                        case 3:
                            payMethod = "Cash";
                            break;
                        default:
                            payMethod = null;
                    }

                    Premium premMem = new Premium(firstName, lastName, 0, payMethod, true, paidPremium, pricingOrderList, orderList);
                    memberList.add(premMem);
                    p = premMem;

                    // Displays customer information after fulfillment
                    System.out.println("Here's the customer's information: ");
                    System.out.println("\nCustomer name: " + p.getFirstName() + " " + p.getLastName() + "\nPremium member? " + p.getIsPremiumMember() + "\nPaid monthly premium? " + p.getHasPaidPremium() + "\nTotal spent at bookstore: " + "$" + p.getTotalSpending() + "\nPreferred Payment Method: " + p.getPayMethod() + "\n");
                    
                    // DISPLAY WHAT STORE HAS ON SALE
                    // Project 3 Starting...
                    System.out.println("What the bookstore has on sale now: ");
                    boolean continueShopping = true;
                    while (continueShopping) {
                    for (int i = 0; i < bookList.size(); i++) {System.out.println(bookList.get(i).getProductID() + ") \"" + bookList.get(i).getTitle() + "\" by " + bookList.get(i).getAuthorArtist() + " (Book) - $" + bookList.get(i).getProductPrice());}
                    for (int i = 0; i < cdList.size(); i++) {System.out.println(cdList.get(i).getProductID() + ") \"" + cdList.get(i).getTitle() + "\" by " + cdList.get(i).getAuthorArtist()+ " (CD) - $" + cdList.get(i).getProductPrice());}
                    for (int i = 0; i < dvdList.size(); i++) {System.out.println(dvdList.get(i).getProductID() + ") \"" + dvdList.get(i).getTitle() + "\" by " + dvdList.get(i).getAuthorArtist() + " (DVD) - $" + dvdList.get(i).getProductPrice());}
                    System.out.println("Enter number beside the product to purchase");
                    int purchaseChoice = scnr.nextInt();
                    
                    if (purchaseChoice >= 0 && purchaseChoice <= 10){ // WHEN PURCHASING BOOK
                        System.out.println("How many copies of \"" + bookList.get(purchaseChoice).getTitle() + "\" would you like?");
                        int purchaseQuantity = scnr.nextInt();

                        if (purchaseQuantity > bookList.get(purchaseChoice).getStockSize() && bookList.get(purchaseChoice).getStockSize() != 0) {
                            System.out.println("We only have " + bookList.get(purchaseChoice).getStockSize() + " copies of \"" + bookList.get(purchaseChoice).getTitle() + "\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemaining = scnr.next().charAt(0);
                            if (purchaseRemaining == 'Y' || purchaseRemaining == 'y') {
                                purchaseQuantity = bookList.get(purchaseChoice).getStockSize();
                                bookList.get(purchaseChoice).setStockSize(purchaseChoice);
                                pricingOrderList.add((bookList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                                idPurchase.add(purchaseChoice);
                                purchaseQTY.add(purchaseQuantity);
                                p.orderList.add(purchaseQuantity + " \"" + bookList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                                orderList.add(purchaseQuantity + " \"" + bookList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                            }
                            else if (bookList.get(purchaseChoice).getStockSize() == 0) {
                                purchaseQuantity = bookList.get(0).getStockSize();
                            }
                            else if (purchaseRemaining == 'N' || purchaseRemaining == 'n') {
                                purchaseQuantity = 0;
                            }
                        }
                        else if (purchaseQuantity <= bookList.get(purchaseChoice).getStockSize()) {
                            bookList.get(purchaseChoice).deductStockSize(purchaseQuantity);
                            bookList.get(purchaseChoice).setTotalPurchasePrice(purchaseQuantity);
                            orderList.add(purchaseQuantity + " " + bookList.get(purchaseChoice).getTitle() + "\t\t\t");
                            p.orderList.add(purchaseQuantity + " \"" + bookList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                            pricingOrderList.add((bookList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                            idPurchase.add(purchaseChoice);
                            purchaseQTY.add(purchaseQuantity);
                        }
                        else if (bookList.get(purchaseChoice).getStockSize() == 0) {
                            purchaseQuantity = 0;
                            System.out.println("\nWe're sorry, \"" + bookList.get(purchaseChoice).getTitle() + "\" is currently out of stock.");
                        }
                        }
                    else if (purchaseChoice >= 11 && purchaseChoice <= 13){ // WHEN PURCHASING CD
                        System.out.println("How many copies of \"" + cdList.get(purchaseChoice).getTitle() + "\" would you like?");
                        int purchaseQuantity = scnr.nextInt();

                        if (purchaseQuantity > cdList.get(purchaseChoice).getStockSize() && cdList.get(purchaseChoice).getStockSize() != 0) {
                            System.out.println("We only have " + cdList.get(purchaseChoice).getStockSize() + " copies of \"" + cdList.get(purchaseChoice).getTitle() + "\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemaining = scnr.next().charAt(0);
                            if (purchaseRemaining == 'Y' || purchaseRemaining == 'y') {
                                purchaseQuantity = cdList.get(purchaseChoice).getStockSize();
                                cdList.get(purchaseChoice).setStockSize(purchaseChoice);
                                pricingOrderList.add((cdList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                                idPurchase.add(purchaseChoice);
                                purchaseQTY.add(purchaseQuantity);
                                orderList.add(purchaseQuantity + " \"" + cdList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                                p.orderList.add(purchaseQuantity + " \"" + cdList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                            }
                            else if (cdList.get(purchaseChoice).getStockSize() == 0) {
                                purchaseQuantity = cdList.get(0).getStockSize();
                            }
                            else if (purchaseRemaining == 'N' || purchaseRemaining == 'n') {
                                purchaseQuantity = 0;
                            }
                        }
                        else if (purchaseQuantity <= cdList.get(purchaseChoice).getStockSize()) {
                            cdList.get(purchaseChoice).deductStockSize(purchaseQuantity);
                            cdList.get(purchaseChoice).setTotalPurchasePrice(purchaseQuantity);
                            orderList.add(purchaseQuantity + " " + cdList.get(purchaseChoice).getTitle() + "\t\t\t");
                            p.orderList.add(purchaseQuantity + " \"" + cdList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                            idPurchase.add(purchaseChoice);
                            purchaseQTY.add(purchaseQuantity);
                            pricingOrderList.add((cdList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                        }
                        else if (cdList.get(purchaseChoice).getStockSize() == 0) {
                            purchaseQuantity = 0;
                            System.out.println("\nWe're sorry, \"" + cdList.get(purchaseChoice).getTitle() + "\" is currently out of stock.");
                        }
                    }
                    else if (purchaseChoice >= 14 && purchaseChoice <= 15) { // WHEN PURCHASING DVD
                        System.out.println("How many copies of \"" + dvdList.get(purchaseChoice).getTitle() + "\" would you like?");
                        int purchaseQuantity = scnr.nextInt();

                        if (purchaseQuantity > dvdList.get(purchaseChoice).getStockSize() && dvdList.get(purchaseChoice).getStockSize() != 0) {
                            System.out.println("We only have " + dvdList.get(purchaseChoice).getStockSize() + " copies of \"" + dvdList.get(purchaseChoice).getTitle() + "\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemaining = scnr.next().charAt(0);
                            if (purchaseRemaining == 'Y' || purchaseRemaining == 'y') {
                                purchaseQuantity = dvdList.get(purchaseChoice).getStockSize();
                                dvdList.get(purchaseChoice).setStockSize(purchaseChoice);
                                pricingOrderList.add((dvdList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                                idPurchase.add(purchaseChoice);
                                purchaseQTY.add(purchaseQuantity);
                                orderList.add(purchaseQuantity + " \"" + dvdList.get(purchaseChoice).getTitle() + "\"\t\t\t" + "\n");
                                p.orderList.add(purchaseQuantity + " " + dvdList.get(purchaseChoice).getTitle() + "\t\t\t");
                            }
                            else if (dvdList.get(purchaseChoice).getStockSize() == 0) {
                                purchaseQuantity = dvdList.get(0).getStockSize();
                            }
                            else if (purchaseRemaining == 'N' || purchaseRemaining == 'n') {
                                purchaseQuantity = 0;
                            }
                        }
                        else if (purchaseQuantity <= dvdList.get(purchaseChoice).getStockSize()) {
                            dvdList.get(purchaseChoice).deductStockSize(purchaseQuantity);
                            dvdList.get(purchaseChoice).setTotalPurchasePrice(purchaseQuantity);
                            orderList.add(purchaseQuantity + " " + dvdList.get(purchaseChoice).getTitle() + "\t\t\t");
                            p.orderList.add(purchaseQuantity + " " + dvdList.get(purchaseChoice).getTitle() + "\t\t\t");
                            idPurchase.add(purchaseChoice);
                            purchaseQTY.add(purchaseQuantity);
                            pricingOrderList.add((dvdList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                        }
                        else if (dvdList.get(purchaseChoice).getStockSize() == 0) {
                            purchaseQuantity = 0;
                            System.out.println("\nWe're sorry, \"" + dvdList.get(purchaseChoice).getTitle() + "\" is currently out of stock.");
                        }
                    }
                    System.out.println("Does the customer want to purchase anything else? (y/n)");
                        char purchaseMore = scnr.next().charAt(0);
                        if (purchaseMore == 'Y' || purchaseMore == 'y') {}
                        else if (purchaseMore == 'N' || purchaseMore == 'n') {
                            System.out.println("\n\nHere is " + premMem.getFirstName() + " " + premMem.getLastName() + "'s receipt: \n");
                            System.out.println(p.getOrderList() + " -- $" + p.getPricingOrderList());
                            double sum = 0;
                            for(Double d : pricingOrderList)
                            sum += d;
                            double discountTotal = sum * .6;
                            p.setTotalSpending(discountTotal);
                            System.out.println("INITIAL TOTAL \t\t\t\t$" + sum);
                            System.out.println("FINAL TOTAL (PREMIUM DISCOUNT) \t\t$" + discountTotal);
                            bookstoreBalance = bookstoreBalance + discountTotal;
                            p.orderList.clear();
                            p.pricingOrderList.clear();
                            continueShopping = false;
                        }
                }
            }
                choiceComplete = true;
            }


            while (choice == 2 && !choiceComplete) {
                /*====================================
                     VIEW ALL MEMBERS INFORMATION
                ====================================*/
                System.out.println("=====================================");
                System.out.println("        BOOKSTORE'S MEMBERS");
                System.out.println("=====================================");
                for (int i = 0; i < memberList.size(); i++) {
                    System.out.println("\nCustomer name: " + memberList.get(i).getFirstName() + " " + memberList.get(i).getLastName() + "\nPremium member? " + memberList.get(i).getIsPremiumMember() + "\nPaid monthly premium? " + memberList.get(i).getHasPaidPremium() + "\nTotal spent at bookstore: " + "$" + df.format(memberList.get(i).getTotalSpending()) + "\nPreferred Payment Method: " + memberList.get(i).getPayMethod() + "\n");
                }
                choiceComplete = true;
            }
            
            
            while (choice == 3 && !choiceComplete) {
                 /*====================================
                         VIEW PREMIUM MEMBERS
                ====================================*/
                 System.out.println("=============================");
                 System.out.println("   ALL PREMIUM MEMBERS");
                 System.out.println("=============================");
                 for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getIsPremiumMember() == true)
                    System.out.println("\nCustomer name: " + memberList.get(i).getFirstName() + " " + memberList.get(i).getLastName() + "\nPremium member? " + memberList.get(i).getIsPremiumMember() + "\nPaid monthly premium? " + memberList.get(i).getHasPaidPremium() + "\nTotal spent at bookstore: " + "$" + df.format(memberList.get(i).getTotalSpending()) + "\nPreferred Payment Method: " + memberList.get(i).getPayMethod() + "\n");
                }
                choiceComplete = true;
            }
            
            
            while (choice == 4 && !choiceComplete) {
                /*====================================
                          VIEW FREE MEMBERS
                ====================================*/
                 System.out.println("=============================");
                 System.out.println("      ALL FREE MEMBERS");
                 System.out.println("=============================");
                 for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getIsPremiumMember() == false)
                        System.out.println("\nCustomer name: " + memberList.get(i).getFirstName() + " " + memberList.get(i).getLastName() + "\nPremium member? " + memberList.get(i).getIsPremiumMember() + "\nPaid monthly premium? " + memberList.get(i).getHasPaidPremium() + "\nTotal spent at bookstore: " + "$" + df.format(memberList.get(i).getTotalSpending()) + "\nPreferred Payment Method: " + memberList.get(i).getPayMethod() + "\n");
                }
                 choiceComplete = true;
            }


            while (choice == 5 && !choiceComplete) {
                /*====================================
                         CHECK STORE INVENTORY
                ====================================*/
                System.out.println("=====================================");
                System.out.println("     STORE INVENTORY & PRICING");
                System.out.println("=====================================");
                for (int i = 0; i < bookList.size(); i++) {bookList.get(i).productDisplay();}
                for (int i = 0; i < cdList.size(); i++) {cdList.get(i).productDisplay();}
                for (int i = 0; i < dvdList.size(); i++) {dvdList.get(i).productDisplay();}
                choiceComplete = true;
            }
            
            
            while (choice == 6 && !choiceComplete) {
                System.out.println("The bookstore's current balance is $" + df.format(bookstoreBalance));
                choiceComplete = true;
            }
            
            
            while (choice == 7 && !choiceComplete) {
                /*====================================
                            EXIT PROGRAM
                ====================================*/
                // Code below was largely sourced from: https://codingface.com/how-to-convert-csv-to-txt-in-java/
                // Declare CSV file path as a String
                String path = "ProductInventory-1.csv";
                // Use File
                file = new File(path);
                // Use FileReader to red CSV file
                FileReader fr = new FileReader(file);
                // User BufferReader
                BufferedReader br = new BufferedReader(fr);
                line = "";

                String[] tempArr;
                // User FileWriter to write content to text file
                FileWriter writer = new FileWriter("output.txt");
                // Use while loop to check when file contains data
                while ((line = br.readLine()) != null) {
                    tempArr = line.split(",");
                    // User for loop to iterate String Array and write data to text file
                    for (String str : tempArr) {
                        writer.write(str + " ");
                    }
                    // Write each line of CSV file to multiple lines
                    writer.write("\n");
                }
                writer.close();
                        scnr.close();
                        System.exit(0);
            }
        }
        
    }
    
}
