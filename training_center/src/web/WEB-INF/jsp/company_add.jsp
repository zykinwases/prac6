<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Add</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<c:set var="role" value="${user.role}"/>
	<c:if test="${role != 'admin'}">You are not allowed to be here</c:if>
	<c:if test="${role == 'admin'}">
		<form:form modelAttribute = "companyForm" method="POST">
			<label for="name">Name</label>
				<form:input type="text" name="name" id="name" path="name"/>
			<br>
			<label for="address">Address</label>
				<form:input type="text" name="address" id="address" path="address"/>
			<br>
			<input type="submit" value=Add>
		</form:form>
	</c:if>
</body>
</html>