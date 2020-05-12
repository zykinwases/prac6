<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Companies</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<c:set var="role" value="${user.role}"/>
	<c:if test="${role != 'admin'}">
		<font color="red"><h3 id="errorMessage">You are not allowed to be here</h3></font>
	</c:if>
	<c:if test="${role == 'admin'}">
		<h1>Companies</h1>
		<a href="/companies/add" id="addCompany">Add new company</a>
		<br>
		<table id="Companies">
			<tr> 
				<th>Name</th>
				<th>Address</th>
				<th>Action</th>
			</tr>
			<tr>
				<td><input type="text" id="Name" class="search_icon" onkeyup="FilterFunction(0, id)"></td>
				<td><input type="text" id="Address" class="search_icon" onkeyup="FilterFunction(1, id)"></td>
			</tr>
			<c:forEach items="${companyList}" var="company">
				<tr>
					<td>
						<a href="../company/${company.company_id}">${company.name}</a>
					</td>
					<td>${company.address}</td>
					<td>
						<a href="../company/${company.company_id}/edit" id="editCompany${company.company_id}">edit</a>
						<a href="../company/${company.company_id}/delete" id="deleteCompany${company.company_id}">delete</a>
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