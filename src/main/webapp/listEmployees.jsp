<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="style/main.css">
<title>Employee Management System</title>
</head>



<body>
<div id="wrapper">
		<div id="header">
			<h2>Gorderian AB</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		 <div>
		 
		 </div>
		 <div>
		 
		 </div>
			<input type="button" value="Add Employee" 
				   onclick="window.location.href='addEmployee.jsp'; return false;"
				   class="add-employee-button"
			/>
			<form action="employeeServlet" method="GET">
		
				<input type="hidden" name="command" value="filter" />
			
                Search Employee: <input type="text" name="filterText" />
                
                <input type="submit" value="Filter" class="add-employee-button" />
            
            </form>
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Address</th>
					<th>Action</th>
				</tr>
				 
				
				 
				<c:forEach var="employee" items="${employeeList}">
					
					<!-- set up a link for each student -->
					<c:url var="forwardLink" value="employeeServlet">
						<c:param name="command" value="load" />
						<c:param name="employeeId" value="${employee.getEmployeeID()}" />
					</c:url>
					
					<c:url var="deleteLink" value="employeeServlet">
						<c:param name="command" value="delete" />
						<c:param name="employeeId" value="${employee.getEmployeeID()}" />
					</c:url>
					
					<tr>
						<td> ${employee.getFirstName()} </td>
						<td> ${employee.getLastName()} </td>
						<td> ${employee.getEmail()} </td>
						<td> ${employee.getAddress()} </td>
						<td> <a href="${forwardLink}">Update</a> 
						 <a href="${deleteLink}" onclick="if(!confirm('Are you sure you want to delete this student?')) return false;">Delete</a> </td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>
</html>