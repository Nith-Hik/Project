package com.bookstore.model;

/**
 * Represents one row of the "users" table.
 * Member 2 stores this in HttpSession after a successful login.
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private boolean admin;

    public User() { }

    public User(int userId, String username, String password, boolean admin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }
}
