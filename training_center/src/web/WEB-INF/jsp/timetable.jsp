<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Timetable</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<c:set var="role" value="${user.role}"/>
	<h1>Timetable</h1>
${t}	
<br>
	<c:if test="${role == 'admin'}">
		<a href="/timetable/add">Add new lesson</a>
	</c:if>
	<br> Show timetable for: 
		<a href="/timetable?time=today">Today</a>
		<a href="/timetable?time=tomorrow">Two days</a>
		<a href="/timetable?time=week">Week</a>
		<a href="/timetable">All</a>
	<table id="timetable">
		<tr>
			<th>Course name</th>
			<th>Time</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${lessonList}" var="lesson">
			<tr>
				<td>${lesson.course_id.name}</td>
				<td>${lesson.time}</td>
				<c:if test="${role == 'admin'}">
					<td>
						<a href="/timetable/delete?lesson=${lesson.lesson_id}">delete</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>