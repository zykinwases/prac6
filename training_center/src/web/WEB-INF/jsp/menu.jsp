<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menu">
	<c:set var="role" value="${user.role}"/>
	<c:if test="${role == ''}"> <a href="/">Home</a> </c:if>
	<c:if test="${role != ''}"> <a href="/${user.role}/${user.id}">Home</a> </c:if>
	<a href="/timetable">Timetable</a>
	<a href="/courses">Courses available</a>
	<c:if test="${role == 'admin'}"> 
		<a href="/students">Students</a>
		<a href="/professors">Professors</a>
		<a href="/companies">Companies</a>
		<a href="/admins">Admins</a>
	</c:if>
	<a href="/logout">Log out</a>
</div>