<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Update</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<c:set var="role" value="${user.role}"/>
	<c:if test="${role != 'admin'}">You are not allowed to be here</c:if>
	<c:if test="${role == 'admin'}">
		<form:form modelAttribute = "companyForm" method="POST">
			<label for="id">Id</label> ${company.company_id}
				<form:input type="hidden" name="id" id="id" path="company_id" value="${company.company_id}"/>
			<br>
			<label for="name">Name</label>
				<form:input type="text" name="name" id="name" path="name" value="${company.name}"/>
			<br>
			<label for="address">Address</label>
				<form:input type="text" name="address" id="address" path="address" value="${company.address}"/>
			<br>
			<input type="submit" value=Edit>
		</form:form>
	</c:if>
</body>
</html>