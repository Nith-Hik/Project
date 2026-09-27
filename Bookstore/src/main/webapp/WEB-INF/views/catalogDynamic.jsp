<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Catalog - Bookstore</title>
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
					<li><a href="catalog" class="active">Books</a></li>
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
		<section class="catalog-layout">

			<aside class="filters">
				<h2>Filters</h2>
				<h3>Category</h3>
				<ul class="filter-list">
					<li><label><input type="checkbox"> Fiction</label></li>
					<li><label><input type="checkbox"> Non-Fiction</label></li>
					<li><label><input type="checkbox"> Children</label></li>
					<li><label><input type="checkbox"> Stationery
							and Gifts</label></li>
				</ul>
			</aside>

			<div class="catalog-results">
				<div class="catalog-toolbar">
					<p>Showing ${books.size()} books</p>
					<select>
						<option>Sort by: Featured</option>
						<option>Price: Low to High</option>
						<option>Price: High to Low</option>
					</select>
				</div>

				<div class="book-grid">
					<c:forEach var="book" items="${books}">
						<article class="book-card">
							<div class="book-image-wrapper">
								<img src="images/${book.coverImage}"
									alt="Cover of ${book.title}">
							</div>
							<div class="book-info">
								<h3>${book.title}</h3>
								<p class="author">by ${book.author}</p>
								<p class="price">$${book.price}</p>
								<a href="book?id=${book.id}" class="btn-detail">View details</a>
							</div>
						</article>
					</c:forEach>
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