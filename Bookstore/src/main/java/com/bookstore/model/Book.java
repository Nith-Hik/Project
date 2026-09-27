package com.bookstore.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private String coverImage;
    private String description;

    public Book(int id, String title, String author, double price, String coverImage, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.coverImage = coverImage;
        this.description = description;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getCoverImage() { return coverImage; }
    public String getDescription() { return description; }
}