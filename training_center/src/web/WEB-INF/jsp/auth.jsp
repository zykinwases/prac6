<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Authentication</title>
</head>
<body>
	<font color="red" id="errorMessage">${errorMessage}</font>
	<form:form modelAttribute = "authForm" method="POST">
		<label for="username">Username</label>
			<form:input type="text" name="username" id="username" path="login"/>
		<br>
		<label for="password">Password</label>
			<form:input type="password" name="password" id="password" path="pswd_hash"/>
		<br>
		<form:select name="type" path="role" id="selection">
			<option>Choose your role</option>
			<option>Student</option>
			<option>Professor</option>
			<option>Administrator</option>
		</form:select>
		<br>
		<input type="submit" value="Login" id="login">
	</form:form>
</body>
</html>