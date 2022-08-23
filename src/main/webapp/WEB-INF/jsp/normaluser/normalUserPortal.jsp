<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Portal</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>
</head>
<!--  navbar   -->
<body>

<div class="header text-center">
	<h1>Flash</h1>
	<p>ATM Transfer</p>
</div>

	<nav class="navbar fixed-navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/home">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="/login/userPortal">User Portal</a></li>
					<li class="nav-item"><a class="nav-link" href="/newRequestPayment">Request Payment</a></li>
					<li class="nav-item"><a class="nav-link" href="/requestsSent">Requests Sent</a></li>
					<li class="nav-item"><a class="nav-link" href="/requestsReceived">Requests Received</a></li>
					<li class="nav-item"><a class="nav-link" href="/logout">Logout</a>
				</ul>
			</div>
		</div>
	</nav>
	<!-- navbar -->
	<div class="container">
	<h1>Welcome ${user.fullName }</h1>
		<h3>Amount: ${user.amount }</h3>
		<h3>ID: ${user.id }</h3>
		<h3>Email: ${user.email }</h3>
		<h3>Phone: ${user.phone }</h3>
		<h3>Address: ${user.address }</h3>
	</div>
</body>

</html>