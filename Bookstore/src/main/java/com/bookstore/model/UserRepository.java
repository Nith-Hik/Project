package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User("Site Admin", "admin@bookstore.com", "admin123", true));
    }

    public static void add(User user) {
        users.add(user);
    }

    public static User findByEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        }
        return null;
    }

    public static boolean emailExists(String email) {
        return findByEmail(email) != null;
    }
}