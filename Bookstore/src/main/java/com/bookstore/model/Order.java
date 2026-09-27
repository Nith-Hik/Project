package com.bookstore.model;

public class Order {

    private String orderNumber;
    private String customerName;
    private double total;
    private String status;

    public Order(String orderNumber, String customerName, double total, String status) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.total = total;
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}