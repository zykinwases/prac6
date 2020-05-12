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
	<c:if test="${role != 'admin'}"><h3 id="errorMessage">You are not allowed to be here</h3></c:if>
	<c:if test="${role == 'admin'}">
		<form:form modelAttribute = "adminForm" method="POST">
			<label for="id">Id</label> ${admin.admin_id}
				<form:input type="hidden" name="id" id="id" path="admin_id" value="${admin.admin_id}"/>
			<br>
			<label for="login">Login</label> ${admin.login}
				<form:input type="hidden" name="login" id="login" path="login" value="${admin.login}"/>
			<form:input type="hidden" name="password" id="password" path="pswd_hash" value="${admin.pswd_hash}"/>
			<br>
			<label for="first_name">First name</label>
				<form:input type="text" name="first_name" id="first_name" path="first_name" value="${admin.first_name}"/>
			<br>
			<label for="last_name">Last name</label>
				<form:input type="text" name="last_name" id="last_name" path="last_name" value="${admin.last_name}"/>
		<br>
		<input type="submit" value=Edit id="edit">
	</form:form>
	</c:if>
</body>
</html>