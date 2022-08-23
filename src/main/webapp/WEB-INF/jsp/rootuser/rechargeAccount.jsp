<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recharge Account</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<script
	
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous">
	</script>

</head>
<body>
<div class="header text-center">
	<h1>Flash</h1>
	<p>ATM Transfer</p>
</div>
	<!--  navbar   -->
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
					<li class="nav-item"><a class="nav-link" href="/createUser">Signup Root User</a>
					<li class="nav-item"><a class="nav-link" href="/createNormalUser">Register Normal User</a></li>
					<li class="nav-item"><a class="nav-link" href="/rechargeAccount">Recharge Account</a></li>
					<li class="nav-item"><a class="nav-link" href="/logout">Logout</a>
				</ul>
			</div>
		</div>
	</nav>
	<!-- navbar end -->

	<!-- Create User Form-->
	<div class="container">
		<form  method="post" action="/rechargeAccount">
			<h1>Recharge Account</h1>

			<c:choose>
				<c:when test="${not empty Error }">
					<h3>${Error }</h3>
				</c:when>
			</c:choose>

			<c:forEach var="error" items="${errors}">
				<span style='color: red'>${error}</span>
				<br>
			</c:forEach>

			<div class="mb-3">
				<label for="exampleInputEmail1" class="form-label">Account ID</label>
				<input type="number" class="form-control" name="id"
					   value="${form.id }">
				<c:forEach items="${errorFields}" var="errorField">
					<c:if test='${errorField.field == "id" }'>
						<br>
						<span style='color: red'>${errorField.defaultMessage}</span>
					</c:if>
				</c:forEach>
			</div>

			<div class="mb-3">
				<label for="exampleInputPassword1" class="form-label">
					Amount </label> <input type="number" name="amount"
											value="${form.amount }" class="form-control"
											id="exampleInput">
				<c:forEach items="${errorFields}" var="errorField">
					<c:if test='${errorField.field == "amount" }'>
						<br>
						<span style='color: red'>${errorField.defaultMessage}</span>
					</c:if>
				</c:forEach>
			</div>

			<button type="submit" value="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>