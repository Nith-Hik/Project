<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order Confirmation - Bookstore</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<header class="site-header">
		<div class="header-container">
			<h1><a href="index.html">Bookstore</a></h1>
			<nav>
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="catalog">Books</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<main class="main-container">
		<section class="cart-page">
			<div class="auth-card invoice-card">
				<h2 class="invoice-title">Thank you, ${fullName}!</h2>
				<p class="auth-subtitle invoice-subtitle">Your order has been placed.</p>

				<p><strong>Order Number:</strong> ${orderNumber}</p>
				<p><strong>Shipping To:</strong> ${address}, ${city}</p>

				<h3 style="margin-top:20px;">Items</h3>
				<c:set var="subtotal" value="${0}" />
				<c:forEach var="item" items="${cartItems}">
					<c:set var="lineTotal" value="${item.book.price * item.quantity}" />
					<c:set var="subtotal" value="${subtotal + lineTotal}" />
					<div class="summary-line">
						<span>${item.book.title} × ${item.quantity}</span>
						<span>$${lineTotal}</span>
					</div>
				</c:forEach>

				<c:set var="shipping" value="${4.00}" />
				<c:set var="total" value="${subtotal + shipping}" />
				<div class="summary-line">
					<span>Shipping</span>
					<span>$${shipping}</span>
				</div>
				<div class="summary-line total">
					<span>Total Paid</span>
					<span>$${total}</span>
				</div>

				<a href="catalog.html" class="checkout-btn">Continue Shopping</a>
			</div>
		</section>
	</main>

	<footer>
		<div class="footer-container">
			<p>&copy; 2026 Online Bookstore. All rights reserved.</p>
		</div>
	</footer>
</body>
</html>