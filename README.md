# 📚 Library Management System

A robust Java application designed for secure library operations, featuring role-based access control (RBAC) and persistent inventory management. This system provides distinct interfaces for **Administrators** and **Librarians** to ensure efficient book tracking and user oversight.



## 🛠 Project Overview
This application streamlines the daily operations of a library. It focuses on secure authentication, librarian management by admins, and a full suite of book management tools for librarians.

---

## 🏗 System Modules

### 1. Admin Module
Responsible for high-level system oversight and security.
* **Librarian Management:** Create, block, and activate librarian accounts.
* **Security:** Password policy enforcement.
* **Technical Details:** * Inherits from the abstract class `MyAdmin`.
    * Stores administrative credentials in `adminPasswords.txt`.

### 2. Librarian Module
The operational heart of the system for day-to-day book handling.
* **CRUD Operations:** Add, Update, and Delete book records.
* **Advanced Search:** Locate books instantly by ISBN or Title.
* **Availability:** Real-time tracking of book status.
* **Technical Details:** Integrates directly with the `BookManager` class.

### 3. Book Management
* Maintains detailed inventory logs (including which librarian added each book).
* **Technical Details:** Implements `Serializable` for efficient binary storage in `books.dat`.
* Supports structured table-format display for easy reading.

---

## 💻 Technical Specifications

| Feature | Implementation |
| :--- | :--- |
| **Language** | Java |
| **Data Persistence** | Binary Serialization (`.dat`) & Plaintext (`.txt`) |
| **Architecture** | Object-Oriented (Abstract Classes, Interfaces) |
| **Security** | Role-Based Access Control (RBAC) |
| **UI** | Console-based Table Formatting |



---

## 👥 Development Team
* **Abrham Wendesen** (DDU1600055)
* **Makbel Hailu** (DDU1600488)
* **Yeabsira Eskinder** (DDU1600747)
* **Wegen Geremew** (DDU1601938)
* **Eyob Jira** (RMD9401)


---

## 🔮 Future Enhancements
- [ ] **Database Migration:** Transition from file-based storage to **MySQL**.
- [ ] **Security Upgrades:** Implement password encryption (hashing).
- [ ] **GUI Development:** Introduction of a graphical user interface.

---

## 📄 License & Rights
**All rights reserved.** *Date: 30-09-2017 E.C*
