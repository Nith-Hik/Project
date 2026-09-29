-- Run this whole file in MySQL Workbench (or `mysql -u root -p < schema.sql`) once.
-- It creates the database and all three tables, plus a couple of sample rows.

CREATE DATABASE IF NOT EXISTS bookstore_db;
USE bookstore_db;

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    genre VARCHAR(100),
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    quantity INT NOT NULL,
    status VARCHAR(50) DEFAULT 'Pending',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Sample data so you have something to query immediately
INSERT INTO users (username, password, is_admin) VALUES
('admin', 'admin123', TRUE),
('testuser', 'password123', FALSE);

INSERT INTO books (title, author, genre, price, stock) VALUES
('Clean Code', 'Robert Martin', 'Programming', 25.00, 10),
('The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 15.50, 5),
('Effective Java', 'Joshua Bloch', 'Programming', 32.00, 8),
('Dune', 'Frank Herbert', 'Sci-Fi', 18.00, 3);
