package com.bookstore.model;

public class User {
    private String fullName;
    private String email;
    private String password;
    private boolean admin;

    public User(String fullName, String email, String password, boolean admin) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isAdmin() { return admin; }
}