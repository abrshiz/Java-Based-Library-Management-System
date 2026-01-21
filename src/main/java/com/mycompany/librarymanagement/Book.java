/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagement;

/**
 * Library Management System
 *
 * This module provides a book management system.
 * It includes the following components:
 *
 * 1. BookOperations (abstract class):
 *    - Defines the core methods for managing books: add, update, delete, search, load, and save.
 *
 * 2. Book (class):
 *    - Represents a book with fields: id (ISBN), title, author, genre, amount, availability status, and addedBy (visible only to admin).
 *    - Implements Serializable to allow saving and loading book data from a file.
 *
 * 3. BookManager (class):
 *    - Implements BookOperations.
 *    - Stores books in a List.
 *    - Reads from and writes to a binary file "books.dat".
 *    - Supports full CRUD operations and title search.
 *
 * Usage:
 * the BookManager is extended to the Librarian and Admin and does all the book
 * operation(CRUD, search and load/save).
 */


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

abstract class BookOperations {
    public abstract void addBook(String id, String title, String author, String genre, int amount, String addedBy);
    public abstract void updateBook(String bookId, Book updatedBook);
    public abstract void deleteBook(String bookId);
    public abstract Book searchBookById(String bookId);
    public abstract List<Book> getAllBooks();
    public abstract void saveBooksToFile();
    public abstract void loadBooksFromFile();
    public abstract List<Book> searchBookByTitle(String title);
}


class Book implements Serializable {
    private String id;
    private String title;
    private String author;
    private String genre;
    private int amount;
    private boolean available;
    private String addedBy;



    // to insert book data imediatly after object initialization
    public Book(String id, String title, String author, String genre, int amount, String addedBy ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.amount = amount;
        this.available = true;
        this.addedBy = addedBy;
    }

    // getter methods to get each book data
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getAmount() { return amount; }
    public boolean isAvailable() { return available; }
    public String getAddedBy() { return addedBy; }


    // setter method to toggle the availability boolean value
    public void setAvailable(boolean available) {
        this.available = available;
    }

    // changes book info into a string(iff called form admin page, addedBy field included)
    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + genre + " | Amount: " + amount + " | Available: " + available + " | AddedBy: " + addedBy;
    }
    
    // changes book info into a table like string(iff called form admin page, addedBy field included)
    public String toTableFormat(int count) {
        return String.format(
                    "%-4d | %-6s | %-20s | %-15s | %-12s | %-6d | %-9s | %-10s ",
                    count, id, title, author, genre, amount, available ? "Yes" : "No", addedBy
                );

    }
}


class BookManager extends BookOperations {
    private List<Book> books = new ArrayList<>();
    private final String fileName = "books.dat";

    // fetchs stored book data at initialization
    public BookManager() {
        loadBooksFromFile();
    }

    @Override
    public void addBook(String id, String title, String author, String genre, int amount, String addedBy) {
        Book book = new Book(id, title, author, genre, amount, addedBy);
        books.add(book);
        saveBooksToFile();
    }

    @Override
    public void updateBook(String bookId, Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(bookId)) {
                books.set(i, updatedBook);
                saveBooksToFile();
                return;
            }
        }
        System.out.println("Book not found");
    }

    @Override
    public void deleteBook(String bookId) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(bookId)) {
                iterator.remove();
                saveBooksToFile();
                return;
            }
        }
        System.out.println("Book not found");
    }

    @Override
    public Book searchBookById(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> searchBookByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    @Override
    public void saveBooksToFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(books);
            out.close();
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    @Override
    public void loadBooksFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            books = (List<Book>) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Book file not found. Starting new.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
}





