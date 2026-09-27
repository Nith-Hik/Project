<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create Account - Bookstore</title>
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
					<li><a href="cart">Cart</a></li>
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
		<section class="auth-page">
			<div class="auth-card">
				<h2>Create Your Account</h2>
				<p class="auth-subtitle">Join us to start shopping</p>

				<c:if test="${not empty errorMessage}">
					<p class="auth-error">${errorMessage}</p>
				</c:if>

				<form action="register" method="post" class="auth-form">
					<label for="fullName">Full Name</label> <input type="text"
						id="fullName" name="fullName" placeholder="Jane Doe" required>

					<label for="email">Email</label> <input type="email" id="email"
						name="email" placeholder="you@example.com"
						value="${submittedEmail}" required> <label for="password">Password</label>
					<input type="password" id="password" name="password"
						placeholder="Create a password" required> <label
						for="confirmPassword">Confirm Password</label> <input
						type="password" id="confirmPassword" name="confirmPassword"
						placeholder="Re-enter your password" required>

					<button type="submit" class="auth-btn">Create Account</button>
				</form>

				<p class="auth-switch">
					Already have an account? <a href="login">Log in</a>
				</p>
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