<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Admins</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<c:set var="role" value="${user.role}"/>
	<c:if test="${role != 'admin'}">You are not allowed to be here</c:if>
	<c:if test="${role == 'admin'}">
		<h1>Admins</h1>
		<a href="/admins/add">Add new administrator</a>
		<br>
		<table id="Admins">
			<tr> 
				<th>Login</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Action</th>
			</tr>
			<tr>
				<td><input type="text" id="Login" class="search_icon" onkeyup="FilterFunction(0, id)"></td>
				<td><input type="text" id="FirstName" class="search_icon" onkeyup="FilterFunction(1, id)"></td>
				<td><input type="text" id="LastName" class="search_icon" onkeyup="FilterFunction(2, id)"></td>
			</tr>
			<c:forEach items="${adminList}" var="admin">
				<tr>
					<td>
						<a href="../admin/${admin.admin_id}">${admin.login}</a>
					</td>
					<td>${admin.first_name}</td>
					<td>${admin.last_name}</td>
					<td>
						<a href="../admin/${admin.admin_id}/edit">edit</a>
						<a href="../admin/${admin.admin_id}/delete">delete</a>
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
        table = document.getElementById("Admins");
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
