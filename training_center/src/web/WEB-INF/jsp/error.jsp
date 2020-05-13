<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
</head>
<body>
	<h3>Something went wrong</h3>
	<br>
	<h3 id="errorMessage">${errorMessage}</h3>
	<br>
	<a href="/logout" id="error">Click here to go to the main page</a>
</body>
</html>