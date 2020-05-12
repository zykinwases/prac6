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
		<form:form modelAttribute = "lessonForm" method="POST">
			<label for="course">Course</label>
				<form:select name="course" path="course_id">
					<c:forEach items="${courseList}" var="course">
						<option value="${course.course_id}">${course.name}</option>
					</c:forEach>
				</form:select>
			<br>
			<label for="time">Time</label>
				<form:input type="text" name="time" id="time" path="time" placeholder="dd-MM-yyyy HH:mm"
						pattern="[0-3][0-9]-[0-1][0-9]-[0-9]{4}\s[0-2][0-9]:[0-5][0-9]"/>
			<br>
			<input type="submit" value=Add id="add">
		</form:form>
	</c:if>
</body>
</html>