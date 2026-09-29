package com.bookstore.dao;

import com.bookstore.model.Order;
import com.bookstore.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    /**
     * The most important method in the whole database layer.
     *
     * Buying a book must do TWO things together: insert an order row, AND
     * reduce the book's stock. If only one succeeded, the data would be wrong
     * (e.g. stock reduced with no order on record, or an order for stock that
     * doesn't exist). A transaction guarantees both happen, or neither does.
     *
     * The stock >= ? check inside the UPDATE is what stops stock from ever
     * going negative, even if two people buy the last copy at the same moment
     * (this is what Member 4 will stress-test).
     *
     * @return true if the purchase succeeded, false if there wasn't enough stock
     */
    public boolean purchaseBook(int userId, int bookId, int quantity) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // start transaction — nothing is permanent yet

            // 1. Try to reduce stock, but only if enough is available
            String updateStock = "UPDATE books SET stock = stock - ? WHERE book_id = ? AND stock >= ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateStock)) {
                stmt.setInt(1, quantity);
                stmt.setInt(2, bookId);
                stmt.setInt(3, quantity);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected == 0) {
                    // Not enough stock — undo anything in this transaction and stop
                    conn.rollback();
                    return false;
                }
            }

            // 2. Record the order
            String insertOrder = "INSERT INTO orders (user_id, book_id, quantity, status) VALUES (?, ?, ?, 'Pending')";
            try (PreparedStatement stmt = conn.prepareStatement(insertOrder)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, bookId);
                stmt.setInt(3, quantity);
                stmt.executeUpdate();
            }

            conn.commit(); // both steps succeeded — make it permanent
            return true;

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    /** Admin order-tracking page: every order, joined with username and book title for display. */
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, u.username, b.title AS book_title, (b.price * o.quantity) AS subtotal " +
                     "FROM orders o " +
                     "JOIN users u ON o.user_id = u.user_id " +
                     "JOIN books b ON o.book_id = b.book_id " +
                     "ORDER BY o.order_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                orders.add(mapRow(rs));
            }
        }
        return orders;
    }

    /** A single customer's own order history. */
    public List<Order> getOrdersForUser(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, u.username, b.title AS book_title, (b.price * o.quantity) AS subtotal " +
                     "FROM orders o " +
                     "JOIN users u ON o.user_id = u.user_id " +
                     "JOIN books b ON o.book_id = b.book_id " +
                     "WHERE o.user_id = ? " +
                     "ORDER BY o.order_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(mapRow(rs));
                }
            }
        }
        return orders;
    }

    /** Member 4's fulfillment workflow: Pending -> Shipped -> Delivered. */
    public void updateOrderStatus(int orderId, String newStatus) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        }
    }

    private Order mapRow(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setBookId(rs.getInt("book_id"));
        order.setQuantity(rs.getInt("quantity"));
        order.setStatus(rs.getString("status"));
        order.setOrderDate(rs.getTimestamp("order_date"));
        order.setUsername(rs.getString("username"));
        order.setBookTitle(rs.getString("book_title"));
        order.setSubtotal(rs.getBigDecimal("subtotal"));
        return order;
    }
}
