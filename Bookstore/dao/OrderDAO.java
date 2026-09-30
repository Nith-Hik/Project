package com.bookstore.dao;

import com.bookstore.util.DBConnection;
import com.bookstore.model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDAO {

    /**
     * Saves every item in the cart as an order row, and reduces stock for each.
     * Returns false if any item didn't have enough stock (nothing is saved in that case).
     */
    public boolean saveOrder(String orderNumber, List<CartItem> cartItems,
                              String fullName, String address, String city) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            for (CartItem item : cartItems) {
                int bookId = item.getBook().getId();
                int quantity = item.getQuantity();

                String updateStock = "UPDATE inventory SET stock = stock - ? WHERE book_id = ? AND stock >= ?";
                try (PreparedStatement stmt = conn.prepareStatement(updateStock)) {
                    stmt.setInt(1, quantity);
                    stmt.setInt(2, bookId);
                    stmt.setInt(3, quantity);
                    if (stmt.executeUpdate() == 0) {
                        conn.rollback();
                        return false; // not enough stock
                    }
                }

                String insertOrder = "INSERT INTO orders (order_number, book_id, book_title, quantity, price, full_name, address, city, status) " +
                                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Pending')";
                try (PreparedStatement stmt = conn.prepareStatement(insertOrder)) {
                    stmt.setString(1, orderNumber);
                    stmt.setInt(2, bookId);
                    stmt.setString(3, item.getBook().getTitle());
