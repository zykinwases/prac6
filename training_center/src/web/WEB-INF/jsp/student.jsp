<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Student</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<c:set var="role" value="${user.role}"/>
	<font color="red" id="errorMessage">${errorMessage}</font>
	<h2>Information about student</h2>
	
	<label>Last name: </label>
		${student.last_name}
	<br><label>First name: </label>
		${student.first_name}
	<br><label>Mobile phone: </label>
		${student.mobile}
	
	<br><label>Attending courses: </label>
	<br>
	<table id="Students">
		<c:forEach items="${coursesList}" var="courses">
			<tr>
  	 			<td><a href="../course/${courses.course_id}" id="course${courses.course_id}">
   				    ${courses.name}
    			</a></td>
    			<c:if test="${role == 'admin'}">
    				<td><a href="/student/${student.student_id}/drop?course=${courses.course_id}" id="drop${courses.course_id}">
    					drop
    				</a></td>
    			</c:if>
    		</tr>
		</c:forEach>
	</table>

	<br>
	<br><a href="javascript:sh()">History</a><br />
	<div id="history" style="padding-top:15px;" hidden="">
		<c:forEach items="${oldList}" var="courses">
   			<a href="../course/${courses.course_id}" id="history${courses.course_id}">
            	${courses.name}
   			</a>
    	<br>
	</c:forEach>
	</div>
	
	<c:if test="${role == 'admin'}">
		<br><a href="javascript:sh1()">Sign up</a><br />
		<div id="sign" style="padding-top:15px;" hidden="">
			<c:forEach items="${allList}" var="course">
   				<a href="../student/${student.student_id}/sign?course=${course.course_id}" id="sign${course.course_id}">
            		${course.name}
	   			</a>
    			<br>
			</c:forEach>
		</div>
	</c:if>
	
	<script type="text/javascript">
		function sh() {
			obj = document.getElementById("history");
			obj.style.display == "block" ? obj.style.display = "none" : obj.style.display = "block";
		}
	</script>
	
	<script type="text/javascript">
		function sh1() {
			obj = document.getElementById("sign");
			obj.style.display == "block" ? obj.style.display = "none" : obj.style.display = "block";
		}
	</script>
</body>
</html>