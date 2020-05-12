<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<font color="red" id="errorMessage">${errorMessage}</font>
	<h2>Information about professor</h2>
	
	<label>Last name: </label>
		${professor.last_name}
	<br><label>First name: </label>
		${professor.first_name}
	<br><label>Company: </label> 
   	<a href="../company/${professor.company_id.company_id}">
            ${professor.company_id.name}
    </a>
    <br><label>Сourses taught: </label>
    <c:forEach items="${courseList}" var="course">
	 	<br>
   		<a href="../course/${course.course_id}" id="course${course.course_id}">
            ${course.name}
    	</a>
	</c:forEach>
</body>
</html>