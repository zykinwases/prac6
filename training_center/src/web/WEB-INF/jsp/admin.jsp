<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Administrator</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<font color="red"><h3 id="errorMessage">${errorMessage}</h3></font>
	<h2>Information about administrator</h2>
	<label>Last name: </label>
		${admin.last_name}
	<br><label>First name: </label>
		${admin.first_name}
</body>
</html>