package com.bookstore.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bookstore.model.Book;
import com.bookstore.model.BookRepository;

@WebServlet("/book")
public class BookDetailServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int bookId = Integer.parseInt(request.getParameter("id"));
        Book book = BookRepository.getById(bookId);

        List<Book> related = new ArrayList<>();
        for (Book b : BookRepository.getAll()) {
            if (b.getId() != bookId && related.size() < 3) {
                related.add(b);
            }
        }

        request.setAttribute("book", book);
        request.setAttribute("relatedBooks", related);
        request.getRequestDispatcher("/WEB-INF/views/bookDetailDynamic.jsp").forward(request, response);
    }
}