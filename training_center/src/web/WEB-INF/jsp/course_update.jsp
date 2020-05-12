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
		<form:form modelAttribute = "courseForm" method="POST">
			<label for="id">Id</label> ${course.course_id}
				<form:input type="hidden" name="id" id="id" path="course_id" value="${course.course_id}"/>
			<br>
			<label for="name">Name</label>
				<form:input type="text" name="name" id="name" path="name" value="${course.name}"/>
			<br>
			<label for="duration">Duration</label>
				<form:input type="text" name="duration" id="duration" path="duration" value="${course.duration}"/>
			<br>
			<label for="intence">Intense</label>
				<form:input type="text" name="intense" id="intense" path="intense" value="${course.intense}"/>
			<br>
			<label for="professor">Professor</label>
				<form:select name="professor" path="prof_id">
					<option value="${course.professor_id.professor_id}">${course.professor_id.first_name} ${course.professor_id.last_name}</option>
					<c:forEach items="${professorList}" var="professor">
						<option value="${professor.professor_id}">${professor.first_name} ${professor.last_name}</option>
					</c:forEach>
				</form:select>
		<br>
		<input type="submit" value=Edit id="edit">
	</form:form>
	</c:if>
</body>
</html>