package com.bookstore.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Represents one row of the "orders" table.
 * Some fields (username, bookTitle) aren't in the orders table itself —
 * they're filled in by OrderDAO via a JOIN, purely so Member 1's admin
 * order-tracking page (orders.jsp) can display readable info instead of raw IDs.
 */
public class Order {
    private int orderId;
    private int userId;
    private int bookId;
    private int quantity;
    private String status;
    private Timestamp orderDate;

    // Joined-in display fields (not actual columns on this table)
    private String username;
    private String bookTitle;
    private BigDecimal subtotal;

    public Order() { }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}
