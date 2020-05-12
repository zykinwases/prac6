<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Students</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<c:set var="role" value="${user.role}"/>
	<c:if test="${role != 'admin'}"><h3 id="errorMessage">You are not allowed to be here</h3></c:if>
	<c:if test="${role == 'admin'}">
		<h1>Students</h1>
		<a href="/students/add" id="addStudent">Add new student</a>
		<br>
		<table id="Students">
			<tr> 
				<th>Login</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Mobile</th>
				<th>Action</th>
			</tr>
			<tr>
				<td><input type="text" id="Login" class="search_icon" onkeyup="FilterFunction(0, id)"></td>
				<td><input type="text" id="FirstName" class="search_icon" onkeyup="FilterFunction(1, id)"></td>
				<td><input type="text" id="LastName" class="search_icon" onkeyup="FilterFunction(2, id)"></td>
				<td><input type="text" id="Mobile" class="search_icon" onkeyup="FilterFunction(3, id)"></td>
			</tr>
			<c:forEach items="${studentList}" var="student">
				<tr>
					<td>
						<a href="../student/${student.student_id}" id="loginStudent${student.student_id}">${student.login}</a>
					</td>
					<td>${student.first_name}</td>
					<td>${student.last_name}</td>
					<td>${student.mobile}</td>
					<td>
						<a href="../student/${student.student_id}/edit" id="editStudent${student.student_id}">edit</a>
						<a href="../student/${student.student_id}/delete" id="deleteStudent${student.student_id}">delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
<script>
    function FilterFunction(id, input) {
        var real_input, filter, table, tr, td, i, txtValue;
        real_input = document.getElementById(input);
        filter = real_input.value.toUpperCase();
        table = document.getElementById("Students");
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
</html>
