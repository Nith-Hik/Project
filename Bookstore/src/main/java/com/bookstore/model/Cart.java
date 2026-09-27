package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public void addItem(Book book, int quantity) {
        for (CartItem item : items) {
            if (item.getBook().getId() == book.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(book, quantity));
    }

    public void removeItem(int bookId) {
        items.removeIf(item -> item.getBook().getId() == bookId);
    }

    public List<CartItem> getItems() {
        return items;
    }
}