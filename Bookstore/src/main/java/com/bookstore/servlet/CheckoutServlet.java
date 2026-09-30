package com.bookstore.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bookstore.model.Cart;
import com.bookstore.dao.OrderDAO;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		request.setAttribute("cartItems", cart.getItems());
		request.getRequestDispatcher("/WEB-INF/views/checkoutDynamic.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		String orderNumber = "ORD-" + System.currentTimeMillis() % 100000;
		String fullName = request.getParameter("fullName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");

		boolean success = false;
		try {
			OrderDAO orderDAO = new OrderDAO();
			success = orderDAO.saveOrder(orderNumber, cart.getItems(), fullName, address, city);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!success) {
			request.setAttribute("errorMessage", "Sorry, one or more items are out of stock.");
			request.setAttribute("cartItems", cart.getItems());
			request.getRequestDispatcher("/WEB-INF/views/checkoutDynamic.jsp").forward(request, response);
			return;
		}

		// Order saved successfully - clear the cart and show the invoice
		request.setAttribute("orderNumber", orderNumber);
		request.setAttribute("cartItems", cart.getItems());
		request.setAttribute("fullName", fullName);
		request.setAttribute("address", address);
		request.setAttribute("city", city);

		session.setAttribute("cart", new Cart()); // empty the cart after successful order

		request.getRequestDispatcher("/WEB-INF/views/invoiceDynamic.jsp").forward(request, response);
	}
}
