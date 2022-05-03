/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bookstoremanagementproject2;
// The program can currently take customer orders but is unable to assign total amount spent for customer, apply discounts, and print csv file after program closes
// REMAINING REQUIREMENTS: 1) store total cost in customer info's total spent, 2) apply discount, and 3) print csv file after store closes
// additionally monetary cost of purchase is being stored in the orderList array
/**
 *
 * @author Paydreanne E. Hinton
 * instructor Professor Van Custodio
 * courseSection ITSC1213-106-27949
 * Bookstore Management Project 2
 * Executes main program for bookstore management system
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.text.*;

public class BookstoreManagementProject2 {
    public static void main (String[] args) throws FileNotFoundException {
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
                    // bookz.productDisplay(); 
                }
                else if(line.contains("cd")) { // if line contains book

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
                    // cdz.productDisplay(); 
                }
                else if(line.contains("dvd")) { // if line contains book

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
                    // dvdz.productDisplay(); 
                }
            }
        //} catch (Exception e) {}
        

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
                // Regular
                if (isPremMem == 1) {
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
                    Regular regMem = new Regular(firstName, lastName, 0, payMethod, false, false);                
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
                            pricingOrderList.add((bookList.get(purchaseChoice).getProductPrice() * purchaseQuantity));
                        }
                        else if (bookList.get(purchaseChoice).getStockSize() == 0) {
                            purchaseQuantity = 0;
                            System.out.println("\nWe're sorry, \"" + bookList.get(purchaseChoice).getTitle() + "\" is currently out of stock.");
                        }
                        // System.out.println("Customer bought " + purchaseQuantity + " copies for total of $" + (bookList.get(purchaseChoice).getProductPrice() * purchaseQuantity) + "(original price was: " + bookList.get(purchaseChoice).getProductPrice() + ")");
                        }
                    else if (purchaseChoice >= 11 && purchaseChoice <= 13){ // WHEN PURCHASING CD
                        System.out.println("How many copies of \"" + cdList.get(purchaseChoice).getTitle() + "\" would you like?");
                        int purchaseQuantity = scnr.nextInt();

                        if (purchaseQuantity > bookList.get(0).getStockSize() && bookList.get(0).getStockSize() != 0) {
                            System.out.println("We only have " + bookList.get(0).getStockSize() + " copies of \"Fahrenheit 451\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemaining = scnr.next().charAt(0);
                            if (purchaseRemaining == 'Y' || purchaseRemaining == 'y') {
                                purchaseQuantity = bookList.get(0).getStockSize();
                                bookList.get(0).setStockSize(0);
                            }
                            else if (bookList.get(0).getStockSize() == 0) {
                                purchaseQuantity = bookList.get(0).getStockSize();
                            }
                            else if (purchaseRemaining == 'N' || purchaseRemaining == 'n') {
                                purchaseQuantity = 0;
                            }
                        }
                        else if (purchaseQuantity <= bookList.get(0).getStockSize()) {
                            bookList.get(0).deductStockSize(purchaseQuantity);
                            bookList.get(0).setTotalPurchasePrice(purchaseQuantity);
                        }
                        else if (bookList.get(0).getStockSize() == 0) {
                            purchaseQuantity = 0;
                            System.out.println("\nWe're sorry, \"Fahrenheit 451\" is currently out of stock.");
                        }
                        System.out.println("Customer bought " + purchaseQuantity + " copies for total of $" + (bookList.get(0).getProductPrice() * purchaseQuantity) + "(original price was: " + bookList.get(0).getProductPrice() + ")");
                        
                    }
                    else if (purchaseChoice >= 14 && purchaseChoice <= 15) { // WHEN PURCHASING DVD
                        System.out.println("How many copies of \"" + dvdList.get(purchaseChoice).getTitle() + "\" would you like?");
                        int purchaseQuantity = scnr.nextInt();

                        if (purchaseQuantity > bookList.get(0).getStockSize() && bookList.get(0).getStockSize() != 0) {
                            System.out.println("We only have " + bookList.get(0).getStockSize() + " copies of \"Fahrenheit 451\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemaining = scnr.next().charAt(0);
                            if (purchaseRemaining == 'Y' || purchaseRemaining == 'y') {
                                purchaseQuantity = bookList.get(0).getStockSize();
                                bookList.get(0).setStockSize(0);
                            }
                            else if (bookList.get(0).getStockSize() == 0) {
                                purchaseQuantity = bookList.get(0).getStockSize();
                            }
                            else if (purchaseRemaining == 'N' || purchaseRemaining == 'n') {
                                purchaseQuantity = 0;
                            }
                        }
                        else if (purchaseQuantity <= bookList.get(0).getStockSize()) {
                            bookList.get(0).deductStockSize(purchaseQuantity);
                            bookList.get(0).setTotalPurchasePrice(purchaseQuantity);
                        }
                        else if (bookList.get(0).getStockSize() == 0) {
                            purchaseQuantity = 0;
                            System.out.println("\nWe're sorry, \"Fahrenheit 451\" is currently out of stock.");
                        }
                        System.out.println("Customer bought " + purchaseQuantity + " copies for total of $" + (bookList.get(0).getProductPrice() * purchaseQuantity) + "(original price was: " + bookList.get(0).getProductPrice() + ")");
                        
                    }
                    System.out.println("Does the customer want to purchase anything else? (y/n)");
                        char purchaseMore = scnr.next().charAt(0);
                        if (purchaseMore == 'Y' || purchaseMore == 'y') {}
                        else if (purchaseMore == 'N' || purchaseMore == 'n') {
                            System.out.println("\n\nHere is " + regMem.getFirstName() + " " + regMem.getLastName() + "'s receipt: \n");
                            for (int i = 0; i < orderList.size(); i++) {
                                System.out.println(orderList.get(i) + "\t\t\t\t$" + pricingOrderList.get(i));
                            }
                            double sum = 0;
                            for(Double d : pricingOrderList)
                            sum += d;
                            double discountTotal = sum * .85;
                            r.setTotalSpending(discountTotal);
                            System.out.println("INITIAL TOTAL \t\t\t\t$" + sum);
                            System.out.println("FINAL TOTAL (REGULAR DISCOUNT) \t\t$" + discountTotal);
                            continueShopping = false;
                        }
                }
                    // Project 3 ending...

                    // BOOKS
                    /*
                    if (book.getStockSize() > 0) {
                        System.out.println("\nWould the customer like to purchase the book \"The Art of War\"? (y/n)");
                        char confirmBookPurchase = scnr.next().charAt(0);
                        if (confirmBookPurchase == 'Y' || confirmBookPurchase == 'y') { 
                            System.out.println("How many books does the customer want to purchase?");
                            booksPurchased = scnr.nextInt();
                            if (booksPurchased > book.getStockSize()) {
                                System.out.println("We only have " + book.getStockSize() + " copies of \"The Art of War\" available. Would you like to purchase the remaining copies? (y/n)");
                                char purchaseRemainingBooks = scnr.next().charAt(0);
                                if (purchaseRemainingBooks == 'Y' || purchaseRemainingBooks == 'y') {
                                    booksPurchased = book.getStockSize();
                                    book.setStockSize(0);
                                }
                                else if (book.getStockSize() == 0) {
                                    booksPurchased = book.getStockSize();
                                }
                                else if (purchaseRemainingBooks == 'N' || purchaseRemainingBooks == 'n') {
                                    book.setStockSize(0);
                                }
                            }
                            if (booksPurchased <= book.getStockSize()) {
                            book.deductStockSize(booksPurchased);
                            book.setTotalPurchasePrice(booksPurchased);
                            }
                        }
                        else if (confirmBookPurchase == 'N' || confirmBookPurchase == 'n') {
                            book.deductStockSize(booksPurchased);
                            book.setTotalPurchasePrice(booksPurchased);
                        }
                    }
                    else if (book.getStockSize() <= 0) {
                        System.out.println("\nWe're sorry, book \"The Art of War\" is currently out of stock.");
                    }
                    book.setTotalPurchasePrice(booksPurchased);

                    // CDS
                    System.out.println("\nWould the customer like to purchase the CD \"Studio Ghibli Soundtrack\"? (y/n)");
                    char confirmCDPurchase = scnr.next().charAt(0);
                    if (confirmCDPurchase == 'Y' || confirmCDPurchase == 'y') {
                        System.out.println("How many CDs does the customer want to purchase?");
                        cdsPurchased = scnr.nextInt();
                        if (cdsPurchased > cd.getStockSize()) {
                            System.out.println("We only have " + cd.getStockSize() + " copies of \"Studio Ghibli Soundtrack\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemainingCDs = scnr.next().charAt(0);
                            if (purchaseRemainingCDs == 'Y' || purchaseRemainingCDs == 'y') {
                                cdsPurchased = cd.getStockSize();
                                cd.setStockSize(0);
                            }
                            else if (cd.getStockSize() == 0) {
                                cdsPurchased = cd.getStockSize();
                            }
                            else if (purchaseRemainingCDs == 'N' || purchaseRemainingCDs == 'n') {
                                cd.setStockSize(0);
                            }
                        }
                        if (cdsPurchased <= cd.getStockSize()) {
                            cd.deductStockSize(cdsPurchased);
                            cd.setTotalPurchasePrice(cdsPurchased);
                        }
                    }
                    else if (confirmCDPurchase == 'N' || confirmCDPurchase == 'n') {
                        cd.deductStockSize(cdsPurchased);
                        cd.setTotalPurchasePrice(cdsPurchased);
                    }
                    else if (cd.getStockSize() <= 0) {
                        System.out.println("\nWe're sorry, the CD \"Studio Ghibli Soundtrack\" is currently out of stock.");
                    }
                    cd.setTotalPurchasePrice(cdsPurchased);


                    // DVDS
                    System.out.println("\nWould the customer like to purchase the DVD \"Up\"? (y/n)");
                    char confirmDVDPurchase = scnr.next().charAt(0);
                    if (confirmDVDPurchase == 'Y' || confirmDVDPurchase == 'y') {
                        System.out.println("How many DVDs does the customer want to purchase?");
                        dvdsPurchased = scnr.nextInt();
                        if (dvdsPurchased > dvd.getStockSize()) {
                            System.out.println("We only have " + dvd.getStockSize() + " copies of \"Up\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemainingDVDs = scnr.next().charAt(0);
                            if (purchaseRemainingDVDs == 'Y' || purchaseRemainingDVDs == 'y') {
                                dvdsPurchased = dvd.getStockSize();
                                dvd.setStockSize(0);
                            }
                            else if (dvd.getStockSize() == 0) {
                                dvdsPurchased = dvd.getStockSize();
                            }
                            else if (purchaseRemainingDVDs == 'N' || purchaseRemainingDVDs == 'n') {
                                dvd.setStockSize(0);
                            }
                        }
                        if (dvdsPurchased <= dvd.getStockSize()) {
                            dvd.deductStockSize(dvdsPurchased);
                            dvd.setTotalPurchasePrice(dvdsPurchased);
                        }
                    }
                    else if (confirmDVDPurchase == 'N' || confirmDVDPurchase == 'n') {
                        dvd.deductStockSize(dvdsPurchased);
                        dvd.setTotalPurchasePrice(dvdsPurchased);
                    }
                    else if (dvd.getStockSize() <= 0) {
                        System.out.println("\nWe're sorry, the DVD \"Up\" is currently out of stock.");
                    }
                    dvd.setTotalPurchasePrice(dvdsPurchased);

                    double customerTotalSpent = book.getTotalPurchasePrice() + cd.getTotalPurchasePrice() + dvd.getTotalPurchasePrice();
                    
                    System.out.println("*** RECEIPT ***");
                    System.out.println("The customer is ordering " + booksPurchased + " book(s) for a total of $" + df.format(book.getTotalPurchasePrice()));
                    System.out.println("The customer is ordering " + cdsPurchased + " CD(s) for a total of $" + df.format(cd.getTotalPurchasePrice()));
                    System.out.println("The customer is ordering " + dvdsPurchased + " DVD(s) for a total of $" + df.format(dvd.getTotalPurchasePrice()));

                    System.out.println("Total would have been $" + df.format(customerTotalSpent));
                    double discountPrice = customerTotalSpent * .85;
                    System.out.println("Given customer's regular membership discount (15% off), the amount is now $" + df.format(discountPrice));
                    r.setTotalSpending(discountPrice);
                    bookstoreBalance = bookstoreBalance + discountPrice;    
                }*/
            }
                // Premium
                else if (isPremMem == 2) {
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

                    Premium premMem = new Premium(firstName, lastName, 0, payMethod, true, paidPremium);
                    memberList.add(premMem);
                    p = premMem;

                    
                    
                    
                    // Display what store has on sale
                    System.out.println("What the bookstore has on sale now: ");
                    

                    // BOOKS
                    /*
                    if (book.getStockSize() > 0) {
                        System.out.println("\nWould the customer like to purchase the book \"The Art of War\"? (y/n)");
                        char confirmBookPurchase = scnr.next().charAt(0);
                        if (confirmBookPurchase == 'Y' || confirmBookPurchase == 'y') { 
                            System.out.println("How many books does the customer want to purchase?");
                            booksPurchased = scnr.nextInt();
                            if (booksPurchased > book.getStockSize()) {
                                System.out.println("We only have " + book.getStockSize() + " copies of \"The Art of War\" available. Would you like to purchase the remaining copies? (y/n)");
                                char purchaseRemainingBooks = scnr.next().charAt(0);
                                if (purchaseRemainingBooks == 'Y' || purchaseRemainingBooks == 'y') {
                                    booksPurchased = book.getStockSize();
                                    book.setStockSize(0);
                                }
                                else if (book.getStockSize() == 0) {
                                    booksPurchased = book.getStockSize();
                                }
                                else if (purchaseRemainingBooks == 'N' || purchaseRemainingBooks == 'n') {
                                    book.setStockSize(0);
                                }
                            }
                            if (booksPurchased <= book.getStockSize()) {
                            book.deductStockSize(booksPurchased);
                            book.setTotalPurchasePrice(booksPurchased);
                            }
                        }
                        else if (confirmBookPurchase == 'N' || confirmBookPurchase == 'n') {
                            book.deductStockSize(booksPurchased);
                            book.setTotalPurchasePrice(booksPurchased);
                        }
                    }
                    else if (book.getStockSize() <= 0) {
                        System.out.println("\nWe're sorry, book \"The Art of War\" is currently out of stock.");
                    }
                    book.setTotalPurchasePrice(booksPurchased);

                    // CDS
                    System.out.println("\nWould the customer like to purchase the CD \"Studio Ghibli Soundtrack\"? (y/n)");
                    char confirmCDPurchase = scnr.next().charAt(0);
                    if (confirmCDPurchase == 'Y' || confirmCDPurchase == 'y') {
                        System.out.println("How many CDs does the customer want to purchase?");
                        cdsPurchased = scnr.nextInt();
                        if (cdsPurchased > cd.getStockSize()) {
                            System.out.println("We only have " + cd.getStockSize() + " copies of \"Studio Ghibli Soundtrack\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemainingCDs = scnr.next().charAt(0);
                            if (purchaseRemainingCDs == 'Y' || purchaseRemainingCDs == 'y') {
                                cdsPurchased = cd.getStockSize();
                                cd.setStockSize(0);
                            }
                            else if (cd.getStockSize() == 0) {
                                cdsPurchased = cd.getStockSize();
                            }
                            else if (purchaseRemainingCDs == 'N' || purchaseRemainingCDs == 'n') {
                                cd.setStockSize(0);
                            }
                        }
                        if (cdsPurchased <= cd.getStockSize()) {
                            cd.deductStockSize(cdsPurchased);
                            cd.setTotalPurchasePrice(cdsPurchased);
                        }
                    }
                    else if (confirmCDPurchase == 'N' || confirmCDPurchase == 'n') {
                        cd.deductStockSize(cdsPurchased);
                        cd.setTotalPurchasePrice(cdsPurchased);
                    }
                    else if (cd.getStockSize() <= 0) {
                        System.out.println("\nWe're sorry, the CD \"Studio Ghibli Soundtrack\" is currently out of stock.");
                    }
                    cd.setTotalPurchasePrice(cdsPurchased);


                    // DVDS
                    System.out.println("\nWould the customer like to purchase the DVD \"Up\"? (y/n)");
                    char confirmDVDPurchase = scnr.next().charAt(0);
                    if (confirmDVDPurchase == 'Y' || confirmDVDPurchase == 'y') {
                        System.out.println("How many DVDs does the customer want to purchase?");
                        dvdsPurchased = scnr.nextInt();
                        if (dvdsPurchased > dvd.getStockSize()) {
                            System.out.println("We only have " + dvd.getStockSize() + " copies of \"Up\" available. Would you like to purchase the remaining copies? (y/n)");
                            char purchaseRemainingDVDs = scnr.next().charAt(0);
                            if (purchaseRemainingDVDs == 'Y' || purchaseRemainingDVDs == 'y') {
                                dvdsPurchased = dvd.getStockSize();
                                dvd.setStockSize(0);
                            }
                            else if (dvd.getStockSize() == 0) {
                                dvdsPurchased = dvd.getStockSize();
                            }
                            else if (purchaseRemainingDVDs == 'N' || purchaseRemainingDVDs == 'n') {
                                dvd.setStockSize(0);
                            }
                        }
                        if (dvdsPurchased <= dvd.getStockSize()) {
                            dvd.deductStockSize(dvdsPurchased);
                            dvd.setTotalPurchasePrice(dvdsPurchased);
                        }
                    }
                    else if (confirmDVDPurchase == 'N' || confirmDVDPurchase == 'n') {
                        dvd.deductStockSize(dvdsPurchased);
                        dvd.setTotalPurchasePrice(dvdsPurchased);
                    }
                    else if (dvd.getStockSize() <= 0) {
                        System.out.println("\nWe're sorry, the DVD \"Up\" is currently out of stock.");
                    }
                    dvd.setTotalPurchasePrice(dvdsPurchased);

                    double customerTotalSpent = book.getTotalPurchasePrice() + cd.getTotalPurchasePrice() + dvd.getTotalPurchasePrice();
                    
                    System.out.println("*** RECEIPT ***");
                    System.out.println("The customer is ordering " + booksPurchased + " book(s) for a total of $" + df.format(book.getTotalPurchasePrice()));
                    System.out.println("The customer is ordering " + cdsPurchased + " CD(s) for a total of $" + df.format(cd.getTotalPurchasePrice()));
                    System.out.println("The customer is ordering " + dvdsPurchased + " DVD(s) for a total of $" + df.format(dvd.getTotalPurchasePrice()));

                    System.out.println("Total would have been $" + df.format(customerTotalSpent));
                    double discountPrice = customerTotalSpent * .6;
                    System.out.println("Given customer's premium membership discount (40% off), the amount is now $" + df.format(discountPrice));
                    p.setTotalSpending(discountPrice);
                    bookstoreBalance = bookstoreBalance + discountPrice;*/
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
                /*
                book.productDisplay();
                cd.productDisplay();
                dvd.productDisplay();
                */
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
                scnr.close();
                System.exit(0);
            }
        }
        
    }
}
