package com.bookstore.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bookstore.model.Order;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Order> orders = new ArrayList<>();
        orders.add(new Order("ORD-10234", "Kong Sophanith", 42.97, "Pending"));
        orders.add(new Order("ORD-10235", "Maria Poet", 15.99, "Shipped"));
        orders.add(new Order("ORD-10236", "Bob Mather", 12.99, "Delivered"));
        orders.add(new Order("ORD-10237", "Jane Austen Fan", 21.98, "Pending"));
        orders.add(new Order("ORD-10238", "Harper Leeworth", 10.99, "Shipped"));

        // Quick stats, real filtering/DB queries are Member 2/3's job
        int pendingCount = 0, shippedCount = 0, deliveredCount = 0;
        for (Order o : orders) {
            if (o.getStatus().equals("Pending")) pendingCount++;
            else if (o.getStatus().equals("Shipped")) shippedCount++;
            else if (o.getStatus().equals("Delivered")) deliveredCount++;
        }

        request.setAttribute("orders", orders);
        request.setAttribute("pendingCount", pendingCount);
        request.setAttribute("shippedCount", shippedCount);
        request.setAttribute("deliveredCount", deliveredCount);

        request.getRequestDispatcher("/WEB-INF/views/adminDynamic.jsp").forward(request, response);
    }
}