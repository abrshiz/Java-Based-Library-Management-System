/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author mc
 */
public class LibraryManagement {

    public static void main(String[] args) {
        int choice;
        Admin admin =new Admin();
        Librarian librarian = new Librarian();
        AboutUs about=new AboutUs();
        Scanner input=new Scanner(System.in);
        while(true){
            System.out.println("****welcome to Group 1 Library management system****");
            System.out.println("choose from the following... ");
            System.out.println("1. Admin");
            System.out.println("2. Librarian");
            System.out.println("3. About us");
            System.out.println("4. exit");
            
            try {
                System.out.print("\nEnter your choice: ");
                choice=input.nextInt();
                switch (choice){
                    case 1:
                        admin.adminInterface();
                        break;
                    case 2:
                        librarian.loginInterface();
                        break;
                    case 3:
                       AboutUs.main(new String[]{});
                       break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("inter valid input.");
                }
            } catch (InputMismatchException e) {
                System.out.println("The input must be Integer!");
                input.nextLine();
            }
            
            
        }

    }
}
