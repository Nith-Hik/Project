package com.bookstore.model;

import java.math.BigDecimal;

/**
 * Plain Java object representing one row of the "books" table.
 * Member 1's JSP pages read these fields directly, e.g. ${book.title}
 */
public class Book {
    private int bookId;
    private String title;
    private String author;
    private String genre;
    private BigDecimal price;
    private int stock;

    public Book() { }

    public Book(int bookId, String title, String author, String genre, BigDecimal price, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.stock = stock;
    }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}

