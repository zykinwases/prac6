<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Course</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<font color="red">${errorMessage}</font>
	<h2>Information about course</h2>
	
	<label>Course: </label>
		${course.name}
	<br><label>Duration: </label>
		${course.duration}
	<br><label>Intense: </label>
		${course.duration}
	<br><label>Professor: </label> 
	<a href="../professor/${course.professor_id.professor_id}"> 
		 ${course.professor_id.last_name} ${course.professor_id.first_name} 
	</a>
	<br><label>Sheduled lessons: </label>
	<c:forEach items="${lessonList}" var="lesson">
	 	<br>${lesson.time}
	</c:forEach>
	
	<br><label>Attending students: </label>
	<c:forEach items="${studentList}" var="student">
	 	<br>
   		<a href="../student/${student.student_id}">
            ${student.last_name} ${student.first_name} 
    	</a>
	</c:forEach>
	
	<br>
	<br><a href="javascript:sh()">History</a><br />
	<div id="hystory" style="padding-top:15px;" hidden="">
		<c:forEach items="${oldList}" var="student">
	 		<br>
 	  		<a href="../student/${student.student_id}">
  	          ${student.last_name} ${student.first_name} 
    		</a>
	</c:forEach>
	</div>
	
	<script type="text/javascript">
		function sh() {
			obj = document.getElementById("hystory");
			obj.style.display == "block" ? obj.style.display = "none" : obj.style.display = "block";
		}
	</script>
</body>
</html>