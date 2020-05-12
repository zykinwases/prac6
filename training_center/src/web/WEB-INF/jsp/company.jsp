<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Company</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<h2>Information about company</h2>
	
	<label>Company: </label>
		${company.name}
	<br><label>Address: </label>
		${company.address}
	<br><label>Working professors: </label>
	<c:forEach items="${professorList}" var="professor">
		<br>
   		<a href="../professor/${professor.professor_id}" id="professor${professor.professor_id}">
            ${professor.last_name} ${professor.first_name} 
   		</a>
	</c:forEach>
</body>
</html>