/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bookstoremanagementproject2;

/**
 *
 * @author Paydreanne E. Hinton
 * instructor Professor Van Custodio
 * courseSection ITSC1213-106-27949
 * Bookstore Management Project 2
 * Executes main program for bookstore management system
 */
import java.util.*;
import java.text.*;

public class BookstoreManagementProject2 {
    public static void main (String[] args) {
        boolean isRunning = true;
        DecimalFormat df = new DecimalFormat("#.##");
        Regular r = null;
        Premium p = null;
        double bookstoreBalance = 0;
        ArrayList<Membership> memberList = new ArrayList(); // contains list of premium/regular members
        Book book = new Book();
        CD cd = new CD();
        DVD dvd = new DVD();
        Scanner scnr = new Scanner(System.in);

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
                    
                    // 
                    // Display what store has on sale
                    System.out.println("What the bookstore has on sale now: ");
                    System.out.println("1) The Art of War - $5.99 (Book)");
                    System.out.println("2) Studio Ghibli Soundtrack - $19.99 (CD)");
                    System.out.println("3) Up - $7.99 (DVD)");

                    int booksPurchased = 0;
                    int cdsPurchased = 0;
                    int dvdsPurchased = 0;

                    // BOOKS
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
                    System.out.println("1) The Art of War - $5.99 (Book)");
                    System.out.println("2) Studio Ghibli Soundtrack - $19.99 (CD)");
                    System.out.println("3) Up - $7.99 (DVD)");

                    int booksPurchased = 0;
                    int cdsPurchased = 0;
                    int dvdsPurchased = 0;

                    // BOOKS
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
                    bookstoreBalance = bookstoreBalance + discountPrice;
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
                book.productDisplay();
                cd.productDisplay();
                dvd.productDisplay();
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
                System.exit(0);
            }
        }
    }
}
