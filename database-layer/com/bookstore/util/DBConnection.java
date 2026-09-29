package com.bookstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * One shared place to open a connection to MySQL.
 * Every DAO class calls DBConnection.getConnection() instead of
 * writing the URL/username/password itself.
 */
public class DBConnection {

    // ⚠️ Update these three values to match your own MySQL setup.
    private static final String URL = "jdbc:mysql://localhost:3306/bookstore_db?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Malika@168";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
