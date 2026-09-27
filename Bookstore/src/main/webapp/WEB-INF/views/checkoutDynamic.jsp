<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Checkout - Bookstore</title>
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
					<li><a href="cart">Cart</a></li>
					<li><a href="login">Login</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<main class="main-container">
		<section class="cart-page">
			<h2>Checkout</h2>

			<c:set var="subtotal" value="${0}" />

			<div class="cart-layout">

				<form action="checkout" method="post" class="cart-items">
					<div class="cart-row checkout-form-block">
						<h3>Shipping Details</h3>
						<label for="fullName">Full Name</label>
						<input type="text" id="fullName" name="fullName" required>

						<label for="address">Address</label>
						<input type="text" id="address" name="address" required>

						<label for="city">City</label>
						<input type="text" id="city" name="city" required>

						<h3>Payment</h3>
						<label for="cardNumber">Card Number</label>
						<input type="text" id="cardNumber" name="cardNumber" placeholder="4111 1111 1111 1111" required>
					</div>

					<button type="submit" class="checkout-btn btn-auto-width">Place Order</button>
				</form>

				<div class="cart-summary">
					<h3>Order Summary</h3>
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
						<span>Total</span>
						<span>$${total}</span>
					</div>
				</div>

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