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
		<form:form modelAttribute = "professorForm" method="POST">
			<label for="login">Login</label> ${professor.login}
				<form:input type="text" name="login" id="login" path="login"/>
			<br>
			<label for="password">Password</label>	
				<form:input type="password" name="password" id="password" path="pswd_hash"/>
			<br>
			<label for="first_name">First name</label>
				<form:input type="text" name="first_name" id="first_name" path="first_name"/>
			<br>
			<label for="last_name">Last name</label>
				<form:input type="text" name="last_name" id="last_name" path="last_name"/>
			<br>
			<label for="company">Company</label>
				<form:select name="company" path="company_id">
					<c:forEach items="${companyList}" var="company">
						<option value="${company.company_id}">${company.name}</option>
					</c:forEach>
				</form:select>
		<br>
		<input type="submit" value=Add>
	</form:form>
	</c:if>
</body>
</html>