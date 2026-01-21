package com.mycompany.librarymanagement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

    /**
     * Standardized About Page for Library Management System
     * - Generates comprehensive documentation
     * - Displays system overview and team credits
     */
    public class AboutUs {
        private static final String FILE_PATH = "library_system_about.txt";

        private static final String ABOUT_CONTENT = """
            =====================================================
                   LIBRARY MANAGEMENT SYSTEM - DOCUMENTATION
            =====================================================

            **Project Overview**
              A robust Java application for managing library operations with 
            secure role-based access (Admin/Librarian) and efficient 
            book inventory management.
                This java application is used by system administrator and librarian 
            for proper book management.
  
            -------------------------------
            **System Modules**
            -------------------------------
            1. ADMIN MODULE
               - Functions: 
                 • Librarian account management (create/block/activate)
                 • Password policy enforcement
                 • System oversight
               - Technical:
                 • Stores credentials in adminPasswords.txt
                 • Uses abstract class MyAdmin for core logic

            2. LIBRARIAN MODULE
               - Functions:
                 • Book CRUD operations (Add/Update/Delete)
                 • Search by ISBN/Title
                 • Availability management
               - Technical:
                 • Integrates with BookManager class
                 • Persists data via serialization (books.dat)

            3. BOOK MANAGEMENT
               - Functions:
                 • Maintains book inventory
                 • Tracks availability status
                 • Records added-by librarian
               - Technical:
                 • Implements Serializable for binary storage
                 • Supports table-format display

            -------------------------------
            **Technical Specifications**
            -------------------------------
            - Storage: 
              • Binary serialization for books
              • Plaintext for admin and librarian profiles
            - Security: Role-based access control

            -------------------------------
            **Development Team**
            -------------------------------
                1. Abrham Wendesen                       DDU1600055
                2. Makbel Hailu                          DDU1600488
                3. Yeabsira Eskinder                     DDU1600747
                4. Wegen Geremew                         DDU1601938
                5. Eyob Jira                             RMD9401
                6. Kalkidan Tsehaye                      RMD1418

            -------------------------------
            **Future Enhancements**
            -------------------------------
            - [0] Database migration (MySQL)
            - [1] Password encryption
            - 
                                       All rights are reserved.
                                       30-09-2017 E.C
            =====================================================
                          END OF DOCUMENTATION
            =====================================================
            
            """;

        public static void generateAboutFile() {
            try {
                File file = new File(FILE_PATH);
                if (!file.exists()) {
                    FileWriter writer = new FileWriter(file);
                    writer.write(ABOUT_CONTENT);
                    writer.close();
                    System.out.println("[System] Documentation file generated at: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                System.err.println("[Error] Failed to create documentation: " + e.getMessage());
            }
        }

        public static void displayAboutPage() {
            generateAboutFile();
            try {
                System.out.println("\n");
                String content = Files.readString(Paths.get(FILE_PATH));
                System.out.println(content);
            } catch (IOException e) {
                System.err.println("[Error] Could not display documentation: " + e.getMessage());
            }
        }

        // Test method
        public static void main(String[] args) {
            generateAboutFile();
            displayAboutPage();
        }
    }

