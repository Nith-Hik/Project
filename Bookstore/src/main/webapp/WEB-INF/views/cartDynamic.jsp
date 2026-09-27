<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Cart - Bookstore</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<header class="site-header">
		<div class="header-container">
			<h1>
				<a href="index.html">Bookstore</a>
			</h1>
			<nav>
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="catalog">Books</a></li>
					<li><a href="cart" class="active">Cart</a></li>
					<c:choose>
						<c:when test="${not empty sessionScope.loggedInUser}">
							<li><a href="logout">Logout
									(${sessionScope.loggedInUser.fullName})</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="login">Login</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
			<form class="search-form" action="search.html" method="get">
				<input type="text" name="q" placeholder="Search for books..."
					aria-label="Search books">
				<button type="submit">Search</button>
			</form>
		</div>
	</header>

	<main class="main-container">
		<section class="cart-page">
			<h2>Your Shopping Cart</h2>

			<c:set var="subtotal" value="${0}" />

			<div class="cart-layout">

				<div class="cart-items">
					<c:forEach var="item" items="${cartItems}">
						<c:set var="lineTotal" value="${item.book.price * item.quantity}" />
						<c:set var="subtotal" value="${subtotal + lineTotal}" />

						<div class="cart-row">
							<img src="images/${item.book.coverImage}"
								alt="Cover of ${item.book.title}">
							<div class="cart-row-info">
								<h3>${item.book.title}</h3>
								<p>by ${item.book.author}</p>
							</div>
							<div class="cart-row-qty">
								<label>Qty:</label> <input type="number"
									value="${item.quantity}" min="1">
							</div>
							<div class="cart-row-price">
								$
								<fmt:formatNumber value="${lineTotal}" minFractionDigits="2"
									maxFractionDigits="2" />
							</div>
							<a href="remove-from-cart?bookId=${item.book.id}"
								class="remove-link">Remove</a>
						</div>
					</c:forEach>
				</div>

				<c:set var="shipping" value="${4.00}" />
				<c:set var="total" value="${subtotal + shipping}" />

				<div class="cart-summary">
					<h3>Order Summary</h3>
					<div class="summary-line">
						<span>Subtotal</span> <span>$<fmt:formatNumber
								value="${subtotal}" minFractionDigits="2" maxFractionDigits="2" /></span>
					</div>
					<div class="summary-line">
						<span>Shipping</span> <span>$<fmt:formatNumber
								value="${shipping}" minFractionDigits="2" maxFractionDigits="2" /></span>
					</div>
					<div class="summary-line total">
						<span>Total</span> <span>$<fmt:formatNumber
								value="${total}" minFractionDigits="2" maxFractionDigits="2" /></span>
					</div>
					<a href="checkout" class="checkout-btn">Proceed to Checkout</a>
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