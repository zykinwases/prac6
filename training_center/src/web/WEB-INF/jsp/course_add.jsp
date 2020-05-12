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
	<c:if test="${role != 'admin'}"><h3 id="errorMessage">You are not allowed to be here</h3></c:if>
	<c:if test="${role == 'admin'}">
		<form:form modelAttribute = "courseForm" method="POST">
			<label for="name">Name</label>
				<form:input type="text" name="name" id="name" path="name"/>
			<br>
			<label for="duration">Duration</label>
				<form:input type="text" name="duration" id="duration" path="duration"/>
			<br>
			<label for="intence">Intense</label>
				<form:input type="text" name="intense" id="intense" path="intense"/>
			<br>
			<label for="professor">Professor</label>
				<form:select name="professor" path="prof_id">
					<c:forEach items="${professorList}" var="professor">
						<option value="${professor.professor_id}">${professor.first_name} ${professor.last_name}</option>
					</c:forEach>
				</form:select>
		<br>
		<input type="submit" value=Add id="add">
	</form:form>
	</c:if>
</body>
</html>