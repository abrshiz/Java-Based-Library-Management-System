/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagement;

/**
 *
 * @author mc
 */
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.*;
/*
                  Library management system
    -----------------------------------------------------
 this module is the admin side module with the following components
      *there is an abstract class called MyAdmin with different abstract methodes
      * there is Admin class that inherit MyAdmin class and add its own methodes
         -adminInterface with the following option
                 -login to admin privilege
                 -forget admin password
                 -back to main menu
         -adminPrivilege with the following function
                 -register librarian
                 -block librarian
                 -activate librarian
                 -display all books
                 -display all librarian
                 -change admin passwored
                 -change admin email
 */
 abstract class MyAdmin {
     
    String adminfullName;
    String LibrarianFullName;
    private String librarianPassword;
    private String librarianUsername;
     
    public void setLibrarianUsername(String uname){
        librarianUsername=uname;
    }
    
    public void setLibrarianPassword(String password){
        librarianPassword=password;
    }
    
    public String getLibrarianPassword(){
        return librarianPassword;
    }
    public String getLibrarianUsername(){
        return librarianUsername;
    }
    
    abstract void passworedChanger();

    abstract void librarianRegistration();

    abstract void adminInterface();

    abstract void adminPrivilage();

    abstract void staffManagement();

    abstract void blockingLibrarian();

    abstract void displayAllLibrarian();

    abstract void activateLibrarian();
}

public class Admin extends MyAdmin{
    private String librarianPassword;
    private String librarianUsername;
    private String  adminPass="pass123";
    private String email="admin@gmail.com";
    private HashSet<String> adminPasswords = new HashSet<>();
    public Admin(){
    File passwordFile = new File("adminPassword.txt");
    File emailFile=new File("adminEmail.txt");
    //  Predefined admin passwords.
    adminPasswords.add(adminPass);
    try {
        if (!passwordFile.exists()) {
            FileWriter writer = new FileWriter(passwordFile);
            for (String pass : adminPasswords) {
                writer.write(pass + "\n"); //  Writing default passwords to file
            }
            writer.close();
            System.out.println("Default passwords saved to file!");
        } else {
            //  Load existing passwords from file
            Scanner fileScanner = new Scanner(passwordFile);
            while (fileScanner.hasNextLine()) {
                adminPasswords.add(fileScanner.nextLine()); //  Ensures file passwords are included
            }
            fileScanner.close();
        }
        if (!emailFile.exists()){
            FileWriter emailWriter=new FileWriter("adminEmail.txt");
            emailWriter.write(email);
            emailWriter.close();
            System.out.println("default email is saved");
        }

    }
    catch (Exception e) {
        System.out.println("Error handling password or email file: " + e.getMessage());
    }}

    public void setLibrarianUsername(String uname){
        librarianUsername=uname;
    }
    
    public void setLibrarianPassword(String password){
        librarianPassword=password;
    }
    
    public String getLibrarianPassword(){
        return librarianPassword;
    }
    public String getLibrarianUsername(){
        return librarianUsername;
    }
    
    public void adminInterface() {
        int choice;
        Admin obj = new Admin();
        Scanner input = new Scanner(System.in);
        try {
        do {
            System.out.println("choose from the following.");
            System.out.println("1. Login");
            System.out.println("2. forget password");
            System.out.println("3. back to main menu");

            System.out.print("\nEnter your choice: ");

                choice = input.nextInt();

            File passwordFile = new File("adminPassword.txt");
            switch (choice) {
                case 1:
                    Scanner scanner=new Scanner(System.in);
                        //  Load passwords from file for authentication
                        HashSet<String> adminPasswords = new HashSet<>();
                        if (passwordFile.exists()) {
                            Scanner fileScanner = new Scanner(passwordFile);
                            while (fileScanner.hasNextLine()) {
                                adminPasswords.add(fileScanner.nextLine()); //  Read passwords dynamically
                            }
                            fileScanner.close();
                        } else {
                            System.out.println("Error: Password file is missing.");
                            return;
                        }
                        System.out.println("default(pass123)");
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();

                        if (adminPasswords.contains(password)) {
                            System.out.println("You are logged in successfully!");
                        } else {
                            System.out.println("Incorrect password.");
                            adminInterface(); //  Redirect to the admin menu
                        }

                        adminPrivilage();
                    break;
                case 2:
                    forgetPassword();
                    break;
                case 3:
                    LibraryManagement.main(new String[]{});
                default:
                    System.out.println("enter valid input");
                 }
        } while (choice != 2);
    } catch (InputMismatchException e){
            System.out.println("input must be integer");
            input.nextLine();
            adminInterface();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void adminPrivilage(){
        int choice;

        Scanner input=new Scanner(System.in);
            BookManager bookManager = new BookManager();
            try{
            do{
                System.out.println("choose from the following.");
                System.out.println("1. view books");
                System.out.println("2. staff management");
                System.out.println("3. change password");
                System.out.println("4. change email");
                System.out.println("5. back to admin interface");
                System.out.println("6. back to main menu");
                
                System.out.print("\nEnter your choice: ");
                choice=input.nextInt();
                switch (choice){
                    case 1:
                        List<Book> books = bookManager.getAllBooks();
                        int count = 1;
                        
                        System.out.println("\nAll Books List:\n");
                        
                        System.out.printf(
                            "%-6s | %-6s | %-20s | %-15s | %-12s | %-6s | %-9s | %-10s\n",
                            "#", "ID", "Title", "Author", "Genre", "Amount", "Available", "Added By"
                        );
                        System.out.println("------------------------------------------------------------------------------------------------------");

                        for(Book book : books) {
                            System.out.println(book.toTableFormat(count));
                            count++;
                        }
                        System.out.println("\n");
                        break;
                    case 2:
                        staffManagement();
                        break;
                    case 3:
                        passworedChanger();
                        break;
                    case 4:
                        emailChanger();
                        break;
                    case 5:
                        return;
                    case 6:
                      LibraryManagement.main(new String[]{});
                      break;
                    default:
                        System.out.println("enter valid input.");
                }
            }while (choice!=6);

        } catch (InputMismatchException e) {
            System.out.println("Input must be integer");
            adminPrivilage();
        } catch (Exception e) {
            System.out.println("Error occured: " + e.getMessage());
        }
            
    }
    public void passworedChanger(){
        Scanner input = new Scanner(System.in);
        File passwordFile = new File("adminPassword.txt");

        try {
            //  Read existing passwords

            if (passwordFile.exists()) {
                Scanner fileScanner = new Scanner(passwordFile);
                while (fileScanner.hasNextLine()) {
                    adminPasswords.add(fileScanner.nextLine());
                }
                fileScanner.close();
            }

            System.out.print("Enter current password: ");
            String currentPass = input.nextLine();

            if (adminPasswords.contains(currentPass)) {
                System.out.print("Enter new password: ");
                String newPass = input.nextLine();

                //  Update password in HashSet
                adminPasswords.remove(currentPass);
                adminPasswords.add(newPass);

                //  Save updated passwords permanently
                FileWriter writer = new FileWriter(passwordFile, false); // Overwrites file
                for (String pass : adminPasswords) {
                    writer.write(pass + "\n");
                }
                writer.close();

                System.out.println("Password changed successfully!");
            } else {
                System.out.println("Incorrect current password. Cannot change.");
            }
        } catch (Exception e) {
            System.out.println("Error handling password: " + e.getMessage());
        }
}
    public void emailChanger(){
        Admin chengeEmail=new Admin();
        boolean found=false;
        Scanner input = new Scanner(System.in);
        File emailFile = new File("adminEmail.txt");

        try {
            //  Read existing passwords
            Scanner fileScanner=new Scanner(emailFile);
            System.out.print("Enter current email: ");
            String currentEmail = input.nextLine();
            String gmailRegex = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@gmail\\.com$";
            if(!Pattern.matches(gmailRegex,currentEmail)){
                System.out.println("invalid email format.");
                return;
            }
                while (fileScanner.hasNextLine()){
                    String data=fileScanner.nextLine();
                    if (currentEmail.equals(data)){
                        found=true;
                        break;
                    }
                }
                if(!found){
                    System.out.println("incorrect email");
                    return;
                }
                    System.out.print("Enter new email: ");
                    String newEmail = input.nextLine();
                if (!Pattern.matches(gmailRegex,newEmail)){
                    System.out.println("incorrect email format");
                    return;
                }
                    FileWriter writer=new FileWriter(emailFile);
                writer.write(newEmail);
            System.out.println("email changed successfully.");
                writer.close();
        } catch (Exception e) {
            System.out.println("Error handling password: " + e.getMessage());
        }
    }
    public void staffManagement(){
        int choice;
        Admin obj=new Admin();
        Scanner input=new Scanner(System.in);
        try {
            do {
                System.out.println("choose from the following." );
                System.out.println("1. add new librarist" );
                System.out.println("2. block librarist" );
                System.out.println("3. activate librarian" );
                System.out.println("4. display all librarian" );
                System.out.println("5. back to admin privilage" );
                System.out.println("6. back to main menu" );

                System.out.print("\nEnter your choice: " );
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        librarianRegistration();
                        break;
                    case 2:
                        blockingLibrarian();
                        break;
                    case 3:
                        activateLibrarian();
                        break;
                    case 4:
                        displayAllLibrarian();
                        break;
                    case 5:
                        adminPrivilage();
                        break;
                    case 6:
                        LibraryManagement.main(new String[]{});
                        break;
                    default:
                        System.out.println("enter valid input." );
                }
            } while (choice == 6);
        }catch (InputMismatchException e){
            input.nextLine();
            System.out.println("input must be intiger");
            staffManagement();
        }
    }
    public void librarianRegistration() {
        String username, fullName, password;
        boolean isActive = true;
        Admin obj = new Admin();
        Scanner input = new Scanner(System.in);

        File registration = new File("librarianProfile.txt");

        try {
            if (registration.createNewFile()) {
                System.out.println("file created successfully.");
            } else
                System.out.println("file is already existed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {

            FileWriter LIbrarianWriter = new FileWriter("librarianProfile.txt", true);
            System.out.print("Enter full Name: ");
            fullName = input.nextLine();
            System.out.print("Enter username: ");
            username = input.nextLine();
            System.out.print("Enter password: ");
            password = input.nextLine();
            
        if (password.length() < 4) {
            System.out.println("Error: Password must be **equal or greater than 4 character**.");
            staffManagement();
            return;
        }
        
        String stringLibrarian = toString(fullName, username, password, isActive);
        File checking = new File("librarianProfile.txt");
        Scanner checker = new Scanner(checking);
        while (checker.hasNextLine()) {
            String data = checker.nextLine();
            String[] splited = data.split(" \\| ");
            if(splited[1].equals(username)){
                System.out.println("username already exist!");
                staffManagement();
                return;
            }
        }
        setLibrarianUsername(username);
        setLibrarianPassword(password);
        LIbrarianWriter.append(stringLibrarian).append("\n");

        LIbrarianWriter.close();
        System.out.println("written successefully");
    }  catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void displayAllLibrarian(){
        int count = 0;
        File reader=new File("librarianProfile.txt");
        try{

            Scanner show=new Scanner(reader);
            if (!reader.canRead())
                System.out.println("file can't readable.");
            else {
                while(show.hasNextLine()){
                    String data=show.nextLine();
                    System.out.println(data);
                    count++;
                }
                System.out.println("\nTotal number of Librarian: " + count);
            }
            show.close();
        } catch (Exception e){
            System.out.println("an error ocurred: " + e.getMessage());
        }
    }
    public void blockingLibrarian(){

        File librarianFile = new File("librarianProfile.txt");
        File tempFile = new File("tempLibrarianProfile.txt");

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter librarian's username to block: ");
            String unameToBlock = input.nextLine();

            Scanner fileScanner = new Scanner(librarianFile);
            FileWriter tempWriter = new FileWriter(tempFile);

            boolean found = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                // Extract password from the line (trim whitespace)
                String currentusername = parts[1].trim();

                if (currentusername.equals(unameToBlock)) {
                    found = true;
                    // Change status to Inactive
                    String newLine = toString(parts[0], parts[1], parts[2], false);
                    tempWriter.write(newLine + "\n");
                    System.out.println("Librarian with username \"" + unameToBlock + "\" has been blocked.");
                } else {
                    // Write the line unchanged
                    tempWriter.write(line + "\n");
                }
            }

            fileScanner.close();
            tempWriter.close();

            if (!found) {
                System.out.println("No librarian found with username: " + unameToBlock);
                tempFile.delete(); // Delete the temporary file if no changes were made
            } else {
                // Replace the original file with the updated temporary file
                librarianFile.delete();
                tempFile.renameTo(librarianFile);
            }

        } catch (Exception e) {
            System.out.println("Error blocking librarian: " + e.getMessage());
        }
    }
    public void activateLibrarian(){
        File librarianFile = new File("librarianProfile.txt");
        File tempFile = new File("tempLibrarianProfile.txt");

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter librarian's username to activate: ");
            String unameToActivate = input.nextLine();

            Scanner fileScanner = new Scanner(librarianFile);
            FileWriter tempWriter = new FileWriter(tempFile);

            boolean found = false;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                // Extract password from the line (trim whitespace)
                String currentUname = parts[1].trim();

                if (currentUname.equals(unameToActivate)) {
                    found = true;
                    // Change status to active
                    String newLine = toString(parts[0], parts[1], parts[2], true);
                    tempWriter.write(newLine + "\n");
                    System.out.println("Librarian with username \"" + unameToActivate+ "\" has been activate.");
                } else {
                    // Write the line unchanged
                    tempWriter.write(line + "\n");
                }
            }

            fileScanner.close();
            tempWriter.close();

            if (!found) {
                System.out.println("No librarian found with username: " + unameToActivate);
                tempFile.delete(); // Delete the temporary file if no changes were made
            } else {
                // Replace the original file with the updated temporary file
                librarianFile.delete();
                tempFile.renameTo(librarianFile);
            }

        } catch (Exception e) {
            System.out.println("Error activating librarian: " + e.getMessage());
        }
    }
    public void forgetPassword(){
        Admin forget=new Admin();
        boolean found=false;
        String gmailRegex = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@gmail\\.com$";
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter your recovery email(default: admin@gmail.com)");
        String inputEmail=scanner.nextLine();
        if (!Pattern.matches(gmailRegex,inputEmail)){
            System.out.println("invallid email format");
            adminInterface();
        }
        File emailFile=new File("adminEmail.txt");
        File passFile=new File("adminPassword.txt");
        try{
            Scanner checker=new Scanner(emailFile);
            while(checker.hasNextLine()){
                String data=checker.nextLine();
                if (inputEmail.equals(data)){
                   found=true;
                   break;
                }
            }
         if (!found){
             System.out.println("incorrect email");
            adminInterface();
         }
         else{
         Scanner passReader=new Scanner(passFile);
             System.out.println("your password is: " + passReader.nextLine());
             System.out.println("please make it unforgettable. would you like to change this password.");
             System.out.println("1. yes");
             System.out.println("2. No");
             int choice=scanner.nextInt();
             if (choice==1)
                 passworedChanger();
             if (choice==2)
                adminInterface();
        }}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public String toString(String fname, String uname, String passwd, boolean isActive) {
        return fname + " | " + uname + " | " + passwd + " | " + isActive;
    }

}
