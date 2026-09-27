package com.bookstore.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bookstore.model.BookRepository;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("books", BookRepository.getAll());
        request.getRequestDispatcher("/WEB-INF/views/catalogDynamic.jsp").forward(request, response);
    }
}