package com.bookstore.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bookstore.model.CartItem;
import com.bookstore.model.BookRepository;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CartItem> getMockCart() {
		List<CartItem> cartItems = new ArrayList<>();
		cartItems.add(new CartItem(BookRepository.getById(5), 1));
		cartItems.add(new CartItem(BookRepository.getById(6), 2));
		return cartItems;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("cartItems", getMockCart());
		request.getRequestDispatcher("/WEB-INF/views/checkoutDynamic.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Real order creation is Member 2/3's job. For now, fake an order number.
		String orderNumber = "ORD-" + System.currentTimeMillis() % 100000;

		request.setAttribute("orderNumber", orderNumber);
		request.setAttribute("cartItems", getMockCart());
		request.setAttribute("fullName", request.getParameter("fullName"));
		request.setAttribute("address", request.getParameter("address"));
		request.setAttribute("city", request.getParameter("city"));

		request.getRequestDispatcher("/WEB-INF/views/invoiceDynamic.jsp").forward(request, response);
	}
}