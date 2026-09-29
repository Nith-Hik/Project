package com.bookstore;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.model.Book;
import com.bookstore.model.Order;
import com.bookstore.model.User;

import java.util.List;

/**
 * Run this as a plain Java Application (right-click -> Run As -> Java Application)
 * to test your database layer WITHOUT needing Tomcat, Servlets, or JSP at all.
 *
 * If this prints book titles and "LOGIN SUCCESS", your database connection,
 * schema, and DAO code are all working correctly — you're ready to hand this
 * off to Member 2 to wire into Servlets.
 */
public class TestDatabaseConnection {

    public static void main(String[] args) {
        try {
            testGetAllBooks();
            testLogin();
            testPurchase();
            testGetAllOrders();
        } catch (Exception e) {
            System.out.println("FAILED — see error below:");
            e.printStackTrace();
        }
    }

    static void testGetAllBooks() throws Exception {
        System.out.println("=== Test 1: Get all books ===");
        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.getAllBooks();
        for (Book b : books) {
            System.out.println(b.getBookId() + " | " + b.getTitle() + " | $" + b.getPrice() + " | stock=" + b.getStock());
        }
        System.out.println();
    }

    static void testLogin() throws Exception {
        System.out.println("=== Test 2: Login check ===");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.checkLogin("admin", "admin123");
        if (user != null) {
            System.out.println("LOGIN SUCCESS: " + user.getUsername() + " (admin=" + user.isAdmin() + ")");
        } else {
            System.out.println("LOGIN FAILED — check username/password or that schema.sql was run");
        }
        System.out.println();
    }

    static void testPurchase() throws Exception {
        System.out.println("=== Test 3: Purchase a book (transaction test) ===");
        OrderDAO orderDAO = new OrderDAO();
        // userId 2 = testuser, bookId 1 = Clean Code, buying 1 copy
        boolean success = orderDAO.purchaseBook(2, 1, 1);
        System.out.println("Purchase result: " + (success ? "SUCCESS" : "FAILED (not enough stock)"));
        System.out.println();
    }

    static void testGetAllOrders() throws Exception {
        System.out.println("=== Test 4: Get all orders (admin view) ===");
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getAllOrders();
        for (Order o : orders) {
            System.out.println("#" + o.getOrderId() + " | " + o.getUsername() + " bought " +
                    o.getQuantity() + "x " + o.getBookTitle() + " | status=" + o.getStatus());
        }
    }
}
