<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Request Sent</title>
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

	<h1>Request Sent</h1>

	<c:choose>
	<c:when test="${empty requestPayments }">
		<h2>No results found. Send A Request</h2>
	</c:when>
	<c:otherwise>

	<table border="1" cellpadding="3">
		<tr>
			<td><b>Request ID</b></td>
			<td><b>Creation Date</b></td>
			<td><b>From User ID</b></td>
			<td><b>To User ID</b></td>
			<td><b>Request Code</b></td>
			<td><b>Status</b></td>
			<td><b>Request Approval Date</b></td>
			<td><b>Amount</b></td>
		</tr>

		<c:forEach items="${requestPayments}" var="requestPayments">
			<tr>
					<%-- <td><a href="/recipeDisplay" >${recipe.id }</td> --%>

				<td>${requestPayments.id }</td>
				<td>${requestPayments.creationDate }</td>
				<td>${requestPayments.fromUserId }</td>
				<td>${requestPayments.toUserId }</td>
				<td>${requestPayments.requestCode }</td>
				<td>${requestPayments.status }</td>
				<td>${requestPayments.requestApproveDate }</td>
				<td>${requestPayments.requestAmount }</td>

			</tr>
		</c:forEach>
	</table>
	</c:otherwise>
	</c:choose>
</div>
</body>

</html>