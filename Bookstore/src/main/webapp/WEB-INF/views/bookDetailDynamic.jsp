<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${book.title} - Bookstore</title>
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
			<form class="search-form" action="search.html" method="get">
				<input type="text" name="q" placeholder="Search for books..." aria-label="Search books">
				<button type="submit">Search</button>
			</form>
		</div>
	</header>

	<main class="main-container">
		<section class="book-detail">
			<div class="book-detail-image">
				<img src="images/${book.coverImage}" alt="Cover of ${book.title}">
			</div>
			<div class="book-detail-info">
				<h2>${book.title}</h2>
				<p class="author">by ${book.author}</p>
				<p class="price">$${book.price}</p>
				<div class="detail-meta">
					<p><strong>Category:</strong> Fiction</p>
					<p><strong>Pages:</strong> 180</p>
					<p><strong>In Stock:</strong> Yes</p>
				</div>

				<form action="add-to-cart" method="post">
					<input type="hidden" name="bookId" value="${book.id}">
					<div class="quantity-row">
						<label for="qty">Quantity:</label>
						<input type="number" id="qty" name="quantity" value="1" min="1">
					</div>
					<button type="submit" class="add-to-cart-btn">Add to Cart</button>
				</form>
			</div>
		</section>

		<section class="section-container">
			<h2>You Might Also Like</h2>
			<div class="book-grid">
				<c:forEach var="rb" items="${relatedBooks}">
					<article class="book-card">
						<div class="book-image-wrapper">
							<img src="images/${rb.coverImage}" alt="Cover of ${rb.title}">
						</div>
						<div class="book-info">
							<h3>${rb.title}</h3>
							<p class="author">by ${rb.author}</p>
							<p class="price">$${rb.price}</p>
							<a href="book?id=${rb.id}" class="btn-detail">View details</a>
						</div>
					</article>
				</c:forEach>
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