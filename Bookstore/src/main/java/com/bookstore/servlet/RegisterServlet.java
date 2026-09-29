package com.bookstore.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.bookstore.model.User;
import com.bookstore.model.UserRepository;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registerDynamic.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (UserRepository.emailExists(email)) {
            request.setAttribute("errorMessage", "An account with that email already exists.");
            request.setAttribute("submittedEmail", email);
            request.getRequestDispatcher("/WEB-INF/views/registerDynamic.jsp").forward(request, response);
            return;
        }

        User newUser = new User(fullName, email, password, false);
        UserRepository.add(newUser);

        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", newUser);
        response.sendRedirect("catalog");
    }
}