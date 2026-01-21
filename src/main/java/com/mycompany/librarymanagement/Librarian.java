/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagement;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *  Library Management System
 *
 * This module provides the librarian interface.
 * It includes the following components:
 * 
 * 1. adding books
 * 2. viewing all books
 * 3. searching books by ID and title
 * 4. update Book's availability(boolean)
 * 5. Delete books by ID
 * 
 * methods:
 * 1. login: logins the librarian by prompting username and password
 * 2. loginInterface: a page prompts user to login or get back to main menu
 * 3. main: the main Librarian dashboard page
 * 
 * @author mc
 */
public class Librarian {
    private String fullname, username, password;
    private boolean isActive = false;
    public void login() {
        
        String uname, passwd;
        boolean found = false;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter username: ");
        uname = input.nextLine();
        System.out.print("enter password: ");
        passwd = input.nextLine();
        
        try (FileReader file = new FileReader("librarianProfile.txt")) {
            
            Scanner fscanner = new Scanner(file);
            
            while(fscanner.hasNextLine()) {
                String line = fscanner.nextLine();
                // splits the string into array string by " | " 
                String[] data = line.split(" \\| ");
                
                /** 
                 * checks for username and password
                 * and then check if blocked by admin or not
                 * if active it redirects to the main librarian page
                 * */
                if(data[1].equals(uname) && data[2].equals(passwd)) {
                    found = true;
                    fullname = data[0];
                    username = data[1];
                    password = data[2];
                    isActive = Boolean.parseBoolean(data[3]);
                    
                    if(isActive)
                        dashboard();
                    else
                        System.out.println("Account blocked contact the admin");    
                }
            }
            
            if(!found)
                System.out.println("Invalid username or password!");
            
        } catch(IOException e) {
            System.out.println("Error occured while reading the file: " + e.getMessage());
        }
        
        
    }
    
    public void loginInterface() {
//        Librarian librarian = new Librarian();
        int choice;
        Scanner input = new Scanner(System.in);
        
        do{
            
            System.out.println("choose from the following.");
            System.out.println("1. Login");
            System.out.println("2. back to main menu");
            
            System.out.print("\nEnter your choice: ");
            choice=input.nextInt();
            switch (choice){
                case 1:
                    login();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("enter valid input");
            }
        }while (choice!=2);
        
    }
    public void dashboard() {
        Scanner scanner = new Scanner(System.in);
        BookManager manager = new BookManager();
        int choice = 0;

        System.out.println("\n****** Welcome to the Librarian Dashboard ******\n");
        
        do {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ISBN");
            System.out.println("4. Search Book by Title");
            System.out.println("5. Update Book Availability");
            System.out.println("6. Delete Book");
            System.out.println("7. Exit");
            
            try {
                System.out.print("Choose an option: ");
                 choice = scanner.nextInt();
                 scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Book ISBN: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Enter Amount: ");

                        int amount = scanner.nextInt();
                        
                        
                        manager.addBook(id, title, author, genre, amount, fullname);
                        break;
                    case 2:
                        int count = 1;
                        
                        System.out.println("\nAll Books List:\n");
                        // create a table like format with a space spacified (s for string, d for decimal)
                        // all the strings except the first will be inserted in the first string with order
                        System.out.printf(
                            "%-4s | %-6s | %-20s | %-15s | %-12s | %-6s | %-9s | %-10s\n",
                            "#", "ID", "Title", "Author", "Genre", "Amount", "Available", "Added By"
                        );
                        System.out.println("------------------------------------------------------------------------------------------------------");

                        // loops all the books and print in tabular format
                        for(Book book : manager.getAllBooks()) {
                            System.out.println(book.toTableFormat(count));
                            count++;
                        }
                        System.out.println("\n");
                        break;
                    case 3:
                        System.out.print("Enter Book ISBN to search: ");
                        Book found = manager.searchBookById(scanner.nextLine());
                        if (found != null) {
                            System.out.printf(
                                "%-4s | %-6s | %-20s | %-15s | %-12s | %-6s | %-9s | %-10s\n",
                                "#", "ID", "Title", "Author", "Genre", "Amount", "Available", "Added By"
                            );
                            System.out.println("------------------------------------------------------------------------------------------------------");

                            System.out.println(found.toTableFormat(1));
                        } else {
                            System.out.println("Book not found");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Book Title to search: ");
                        String searchTitle = scanner.nextLine();
                        List<Book> matches = manager.searchBookByTitle(searchTitle);
                        if (!matches.isEmpty()) {
                            System.out.printf(
                                "%-4s | %-6s | %-20s | %-15s | %-12s | %-6s | %-9s | %-10s\n",
                                "#", "ID", "Title", "Author", "Genre", "Amount", "Available", "Added By"
                            );
                            System.out.println("------------------------------------------------------------------------------------------------------");

                            count = 1;
                            for (Book b : matches) {
                                System.out.println(b.toTableFormat(count));
                            }
                        } else {
                            System.out.println("No book found with that title.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter Book ISBN to update availability: ");
                        String updateId = scanner.nextLine();
                        Book bookToUpdate = manager.searchBookById(updateId);
                        if (bookToUpdate != null) {
                            bookToUpdate.setAvailable(!bookToUpdate.isAvailable());
                            manager.updateBook(updateId, bookToUpdate);
                            System.out.println("Book availability updated.");
                        } else {
                            System.out.println("Book not found");
                        }
                        break;
                    case 6:
                        System.out.print("Enter Book ISBN to delete: ");
                        manager.deleteBook(scanner.nextLine());
                        break;
                    case 7:
                        return;// ends the loops leading to login inerface
                        
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            
            } catch(InputMismatchException e) {
                System.out.println("The input must be Integer!");
                scanner.nextInt();
            }
            
        } while (choice != 7); 
        scanner.close();
    }
}
