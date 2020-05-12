<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Courses</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<c:set var="role" value="${user.role}"/>
	<h1>Courses</h1>
	<c:if test="${role == 'admin'}">
		<a href="/courses/add" id="addCourse">Add new course</a>
		<br>
	</c:if>
	<table id="Courses">
		<tr> 
			<th>Name</th>
			<th>Duration</th>
			<th>Intense</th>
			<th>Professor</th>
			<c:if test="${role == 'admin'}"><th>Action</th></c:if>
		</tr>
		<tr>
			<td><input type="text" id="Name" class="search_icon" onkeyup="FilterFunction(0, id)"></td>
			<td></td>
			<td></td>
			<td><input type="text" id="Professor" class="search_icon" onkeyup="FilterFunction(3, id)"></td>
		</tr>
		<c:forEach items="${courseList}" var="course">
			<tr>
				<td>
					<a href="../course/${course.course_id}">${course.name}</a>
				</td>
				<td>${course.duration}</td>
				<td>${course.intense}</td>
				<td>
					<a href="../professor/${course.professor_id.professor_id}">
						${course.professor_id.first_name} ${course.professor_id.last_name}
					</a>
				</td>
				<c:if test="${role == 'admin'}">
					<td>
						<a href="../course/${course.course_id}/edit" id="editCourse${course.course_id}">edit</a>
						<a href="../course/${course.course_id}/delete" id="deleteCourse${course.course_id}">delete</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
<script>
    function FilterFunction(id, input) {
        var real_input, filter, table, tr, td, i, txtValue;
        real_input = document.getElementById(input);
        filter = real_input.value.toUpperCase();
        table = document.getElementById("Companies");
        tr = table.getElementsByTagName("tr");
        for (i = 2; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[id];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
</body>