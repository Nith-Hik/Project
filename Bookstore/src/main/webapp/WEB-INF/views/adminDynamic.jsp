<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard - Bookstore</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<header class="site-header">
		<div class="header-container">
			<h1><a href="index.html">Bookstore — Admin</a></h1>
			<nav>
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="catalog">Books</a></li>
					<li><a href="admin" class="active">Admin</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<main class="main-container">
		<section class="admin-page">
			<h2>Admin Dashboard</h2>

			<div class="admin-stats">
				<div class="cart-summary admin-stat-card">
					<h3>Pending</h3>
					<p class="admin-stat-number stat-pending">${pendingCount}</p>
				</div>
				<div class="cart-summary admin-stat-card">
					<h3>Shipped</h3>
					<p class="admin-stat-number stat-shipped">${shippedCount}</p>
				</div>
				<div class="cart-summary admin-stat-card">
					<h3>Delivered</h3>
					<p class="admin-stat-number stat-delivered">${deliveredCount}</p>
				</div>
			</div>

			<h2>Order Tracking Queue</h2>
			<div class="table-responsive">
				<table class="admin-table">
					<tr>
						<th>Order #</th>
						<th>Customer</th>
						<th>Total</th>
						<th>Status</th>
					</tr>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.orderNumber}</td>
							<td>${order.customerName}</td>
							<td>$${order.total}</td>
							<td>
								<c:choose>
									<c:when test="${order.status == 'Pending'}">
										<span class="status-badge status-pending">Pending</span>
									</c:when>
									<c:when test="${order.status == 'Shipped'}">
										<span class="status-badge status-shipped">Shipped</span>
									</c:when>
									<c:otherwise>
										<span class="status-badge status-delivered">Delivered</span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
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