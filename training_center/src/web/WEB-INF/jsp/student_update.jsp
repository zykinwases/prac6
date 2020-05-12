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
		<form:form modelAttribute = "studentForm" method="POST">
			<label for="id">Id</label> ${student.student_id}
				<form:input type="hidden" name="id" id="id" path="student_id" value="${student.student_id}"/>
			<br>
			<label for="login">Login</label> ${student.login}
				<form:input type="hidden" name="login" id="login" path="login" value="${student.login}"/>
				<form:input type="hidden" name="password" id="password" path="pswd_hash" value="${student.pswd_hash}"/>
			<br>
			<label for="first_name">First name</label>
				<form:input type="text" name="first_name" id="first_name" path="first_name" value="${student.first_name}"/>
			<br>
			<label for="last_name">Last name</label>
				<form:input type="text" name="last_name" id="last_name" path="last_name" value="${student.last_name}"/>
			<br>
			<label for="mobile">Mobile</label>
				<form:input type="text" name="mobile" id="mobile" path="mobile" value="${student.mobile}"/>
		<br>
		<input type="submit" value=Edit>
	</form:form>
	</c:if>
</body>
</html>