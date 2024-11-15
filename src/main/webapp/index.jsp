<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Here</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
}

.login-container {
	width: 100%;
	max-width: 400px;
	margin: 100px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.login-form h2 {
	margin-bottom: 20px;
	text-align: center;
}

.input-group {
	margin-bottom: 20px;
}

.input-group label {
	display: block;
	margin-bottom: 5px;
}

.input-group input {
	width: 95%;
	padding: 10px;
	font-size: 16px;
	border-radius: 5px;
	border: 1px solid #ccc;
}

button[type="submit"] {
	width: 100%;
	padding: 10px;
	font-size: 16px;
	border: none;
	border-radius: 5px;
	background-color: #007bff;
	color: #fff;
	cursor: pointer;
}

button[type="submit"]:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="login-container">
		<form action="login" method="POST" class="login-form">
			<h2>Login</h2>
			<div class="input-group">
				<label for="email">Email Id</label> <input type="email" id="email"
					name="email" required>
			</div>
			<div class="input-group">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" required>
			</div>
			<br>
			<button type="submit">Login</button>
		</form>
		<br>
		<div>
			<a href="/SimpleLoginPage/SignUp.jsp">Create New Account</a>
		</div>
		<br>


	</div>
</body>
</html>