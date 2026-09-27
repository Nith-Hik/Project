package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final List<User> users = new ArrayList<>();

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