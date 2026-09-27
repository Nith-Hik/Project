<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - Bookstore</title>
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
							<li><a href="login" class="active">Login</a></li>
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
				<h2>Welcome Back</h2>
				<p class="auth-subtitle">Log in to your account</p>

				<c:if test="${not empty errorMessage}">
					<p class="auth-error">${errorMessage}</p>
				</c:if>

				<form action="login" method="post" class="auth-form">
					<label for="email">Email</label> <input type="email" id="email"
						name="email" placeholder="yours@example.com" required> <label
						for="password">Password</label> <input type="password"
						id="password" name="password" placeholder="Enter your password"
						required>

					<button type="submit" class="auth-btn">Log In</button>
				</form>

				<p class="auth-switch">
					Don't have an account? <a href="register">Create one</a>
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