package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

	private static final List<Book> books = new ArrayList<>();

	static {
		books.add(new Book(1, "The Great Adventure", "Samantha van Riet", 12.99, "book1.jpg",
				"Story is about three lesser-known small creatures\u2014Otter, Mongoose, and Squirrel\u2014who live together and have never traveled anywhere before. One sunrise, they wake up to the excited chatter of migratory birds returning from their annual journey to the north. Inspired to embark on an adventure of their own, but unable to fly, the three friends build a boat to set off across the water."));
		books.add(new Book(2, "Coding for Kids in C++", "Bob Mather", 12.99, "book2.jpg",
				"This is a beginner-friendly programming book that introduces kids to C++ coding. It teaches basic concepts such as variables, loops, conditions, functions, and simple programming problems."));
		books.add(new Book(3, "A Quiet Garden", "Maria Poet", 15.99, "book3.jpg",
				"A Quiet Garden is a peaceful piece about a garden and the feeling of calmness, nature, and reflection. It describes how a quiet natural place can help people relax and appreciate the beauty around them."));
		books.add(new Book(4, "Pride and Prejudice", "Jane Austen", 10.99, "book4.jpg",
				"Pride and Prejudice is a novel about love, marriage, social class, and first impressions. It follows Elizabeth Bennet as she gets to know Mr. Darcy and learns that people are not always what they first appear to be."));
		books.add(new Book(5, "The Great Gatsby", "F. Scott Fitzgerald", 12.99, "book5.jpg",
				"A story of wealth, love, and the American Dream in the roaring 1920s, following the mysterious Jay Gatsby and his obsession with the beautiful Daisy Buchanan."));
		books.add(new Book(6, "To Kill a Mockingbird", "Harper Lee", 12.99, "book6.jpg",
				"This novel follows a young girl named Scout Finch as she grows up in the American South. Through her father's defense of a Black man wrongly accused of a crime, the story explores racism, injustice, courage, and empathy."));
		books.add(new Book(7, "War and Peace", "Leo Tolstoy", 10.99, "book7.jpg",
				"War and Peace is a large historical novel set during the Napoleonic Wars. It follows several Russian families and explores love, war, family, society, and the effects of historical events on ordinary people."));
	}

	public static List<Book> getAll() {
		return books;
	}

	public static Book getById(int id) {
		for (Book b : books) {
			if (b.getId() == id)
				return b;
		}
		return null;
	}
}