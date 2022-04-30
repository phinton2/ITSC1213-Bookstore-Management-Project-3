// PROJECT 3 START
            String line;
            File file = new File("ProductInventory-1.csv"); // file contains csv
            Scanner fileScanner = new Scanner(file); // scanners reads file
            if (fileScanner.hasNext()) { // while file has next line
                line = fileScanner.nextLine();
                if (line.contains("book")) {
                    ArrayList<Book> bookList = new ArrayList<>();
                    String details[] = line.split(",");

                    // variables
                    int productID = Integer.parseInt(details[0]); 
                    String itemType = details[1]; 
                    String title = details[2]; 
                    String authorArtist = details[3]; 
                    int stockSize = Integer.parseInt(details[4]); 
                    double productPrice = Double.parseDouble(details[5]);

                    // object
                    Book iAmBook = new Book(productID, itemType, title, authorArtist, stockSize, productPrice);
                    bookList.add(iAmBook);
                    iAmBook.productDisplay();// remove if not in the view inventory section of program
                    System.out.println("book");
                }
                else if (line.contains("cd")) {
                    ArrayList<Book> cdList = new ArrayList<>();
                    String details[] = line.split(",");

                    // variables
                    int productID = Integer.parseInt(details[0]); 
                    String itemType = details[1]; 
                    String title = details[2]; 
                    String authorArtist = details[3]; 
                    int stockSize = Integer.parseInt(details[4]); 
                    double productPrice = Double.parseDouble(details[5]);

                    // object
                    Book iAmCD = new Book(productID, itemType, title, authorArtist, stockSize, productPrice);
                    cdList.add(iAmCD);
                    iAmCD.productDisplay();// remove if not in the view inventory section of program
                }
                else if (line.contains("dvd")) {
                    ArrayList<Book> dvdList = new ArrayList<>();
                    String details[] = line.split(",");

                    // variables
                    int productID = Integer.parseInt(details[0]); 
                    String itemType = details[1]; 
                    String title = details[2]; 
                    String authorArtist = details[3]; 
                    int stockSize = Integer.parseInt(details[4]); 
                    double productPrice = Double.parseDouble(details[5]);

                    // object
                    Book iAmDVD = new Book(productID, itemType, title, authorArtist, stockSize, productPrice);
                    dvdList.add(iAmDVD);
                    iAmDVD.productDisplay();// remove if not in the view inventory section of program
                }
            }
