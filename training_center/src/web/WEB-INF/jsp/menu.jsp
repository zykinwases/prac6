<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="menu">
	<c:set var="role" value="${user.role}"/>
	<c:if test="${role == ''}"> <a href="/" id="home">Home</a> </c:if>
	<c:if test="${role != ''}"> <a href="/${user.role}/${user.id}" id="home">Home</a> </c:if>
	<a href="/timetable" id="timetable">Timetable</a>
	<a href="/courses" id="courses">Courses available</a>
	<c:if test="${role == 'admin'}"> 
		<a href="/students" id="students">Students</a>
		<a href="/professors" id="professors">Professors</a>
		<a href="/companies" id="companies">Companies</a>
		<a href="/admins" id="admins">Admins</a>
	</c:if>
	<a href="/logout" id="logout">Log out</a>
</div>