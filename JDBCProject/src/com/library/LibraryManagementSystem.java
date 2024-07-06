package com.library;

import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {
	
	//connection for MYSQL

    private static final String DB_URL = "jdbc:mysql://localhost:3306/library2";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static Connection conn = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            boolean exit = false;
            while (!exit) {
            	
            	
            	System.out.println("-----------------------------");
                System.out.println("Library Management System");
                System.out.println("-----------------------------");
                System.out.println("1. Manage Books");
                System.out.println("2. Manage Members");
                System.out.println("3. Manage Publishers");
                System.out.println("4. Manage Librarians");
                System.out.println("5. Manage Checkouts");
                System.out.println("6. Exit");
                System.out.println("-----------------------------");
                System.out.print("Select an option: ");
              
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        manageBooks();
                        break;
                    case 2:
                        manageMembers();
                        break;
                    case 3:
                        managePublishers();
                        break;
                    case 4:
                        manageLibrarians();
                        break;
                    case 5:
                        manageCheckouts();
                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void manageBooks() throws SQLException {
        boolean back = false;
        while (!back) {
        	System.out.println("-------------");
            System.out.println("Manage Books");
            System.out.println("-------------");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() throws SQLException {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author(s): ");
        String authors = scanner.nextLine();
        System.out.print("Enter publication date (YYYY-MM-DD): ");
        String pubDate = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter publisher ID: ");
        int pubId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String query = "INSERT INTO Books (isbn, title, authors, pub_date, category, pub_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, isbn);
        pstmt.setString(2, title);
        pstmt.setString(3, authors);
        pstmt.setDate(4, Date.valueOf(pubDate));
        pstmt.setString(5, category);
        pstmt.setInt(6, pubId);
        pstmt.executeUpdate();
        System.out.println("Book added successfully.");
    }

    private static void viewBooks() throws SQLException {
        String query = "SELECT * FROM Books";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Books List:");
        while (rs.next()) {
            System.out.println("ISBN: " + rs.getString("isbn") + ", Title: " + rs.getString("title") + ", Authors: " + rs.getString("authors") + 
                               ", Publication Date: " + rs.getDate("pub_date") + ", Category: " + rs.getString("category") + 
                               ", Publisher ID: " + rs.getInt("pub_id"));
        }
    }

    private static void manageMembers() throws SQLException {
        boolean back = false;
        while (!back) {
        	System.out.println("---------------");
            System.out.println("Manage Members");
            System.out.println("---------------");
            System.out.println("1. Add Member");
            System.out.println("2. View Members");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    viewMembers();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMember() throws SQLException {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact information: ");
        String contact = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        String query = "INSERT INTO Members (name, contact, address) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, contact);
        pstmt.setString(3, address);
        pstmt.executeUpdate();
        System.out.println("Member added successfully.");
    }

    private static void viewMembers() throws SQLException {
        String query = "SELECT * FROM Members";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Members List:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Contact: " + rs.getString("contact") + ", Address: " + rs.getString("address"));
        }
    }

    private static void managePublishers() throws SQLException {
        boolean back = false;
        while (!back) {
        	System.out.println("-------------------");
            System.out.println("Manage Publishers");
            System.out.println("-------------------");
            System.out.println("1. Add Publisher");
            System.out.println("2. View Publishers");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addPublisher();
                    break;
                case 2:
                    viewPublishers();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addPublisher() throws SQLException {
        System.out.print("Enter publisher name: ");
        String name = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter contact information: ");
        String contact = scanner.nextLine();

        String query = "INSERT INTO Publishers (name, address, contact) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, address);
        pstmt.setString(3, contact);
        pstmt.executeUpdate();
        System.out.println("Publisher added successfully.");
    }

    private static void viewPublishers() throws SQLException {
        String query = "SELECT * FROM Publishers";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Publishers List:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Address: " + rs.getString("address") + ", Contact: " + rs.getString("contact"));
        }
    }

    private static void manageLibrarians() throws SQLException {
        boolean back = false;
        while (!back) {
        	System.out.println("-------------------");
            System.out.println("Manage Librarians");
            System.out.println("-------------------");
            System.out.println("1. Add Librarian");
            System.out.println("2. View Librarians");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addLibrarian();
                    break;
                case 2:
                    viewLibrarians();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addLibrarian() throws SQLException {
        System.out.print("Enter librarian name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact information: ");
        String contact = scanner.nextLine();

        String query = "INSERT INTO Librarians (name, contact) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, contact);
        pstmt.executeUpdate();
        System.out.println("Librarian added successfully.");
    }

    private static void viewLibrarians() throws SQLException {
        String query = "SELECT * FROM Librarians";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Librarians List:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Contact: " + rs.getString("contact"));
        }
    }

    private static void manageCheckouts() throws SQLException {
        boolean back = false;
        while (!back) {
        	System.out.println("-------------------");
            System.out.println("Manage Checkouts");
            System.out.println("-------------------");
            System.out.println("1. Add Checkout");
            System.out.println("2. View Checkouts");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addCheckout();
                    break;
                case 2:
                    viewCheckouts();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCheckout() throws SQLException {
        System.out.print("Enter member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter book ISBN: ");
        String bookIsbn = scanner.nextLine();
        System.out.print("Enter checkout date (YYYY-MM-DD): ");
        String checkoutDate = scanner.nextLine();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();

        String query = "INSERT INTO Checkouts (member_id, book_isbn, checkout_date, due_date) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, memberId);
        pstmt.setString(2, bookIsbn);
        pstmt.setDate(3, Date.valueOf(checkoutDate));
        pstmt.setDate(4, Date.valueOf(dueDate));
        pstmt.executeUpdate();
        System.out.println("Checkout added successfully.");
    }

    private static void viewCheckouts() throws SQLException {
        String query = "SELECT Checkouts.id, Members.name AS member_name, Books.title AS book_title, Checkouts.checkout_date, Checkouts.due_date " +
                       "FROM Checkouts " +
                       "JOIN Members ON Checkouts.member_id = Members.id " +
                       "JOIN Books ON Checkouts.book_isbn = Books.isbn";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Checkouts List:");
        while (rs.next()) {
            System.out.println("Checkout ID: " + rs.getInt("id") + ", Member: " + rs.getString("member_name") + ", Book: " + rs.getString("book_title") + 
                               ", Checkout Date: " + rs.getDate("checkout_date") + ", Due Date: " + rs.getDate("due_date"));
        }
    }
}
