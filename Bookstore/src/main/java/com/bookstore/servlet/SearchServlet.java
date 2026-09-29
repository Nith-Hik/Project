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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getParameter("q");
        List<Book> results = new ArrayList<>();

        for (Book b : BookRepository.getAll()) {
            if (query == null || query.isBlank() ||
                b.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add(b);
            }
        }

        request.setAttribute("books", results);
        request.setAttribute("searchQuery", query);
        request.getRequestDispatcher("/WEB-INF/views/catalogDynamic.jsp").forward(request, response);
    }
}