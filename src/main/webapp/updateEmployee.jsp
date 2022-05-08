<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Employee</title>
<link type="text/css" rel="stylesheet" href="style/main.css">
<link type="text/css" rel="stylesheet" href="style/addEmployee.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Gorderian AB</h2>
		</div>
	</div>
	<div id="container">
		<h3>Add Employee</h3>
		<form action="employeeServlet" method="get">
			<input type="hidden" name="command" value="update"/>
			<input type="hidden" name="employeeId" value="${employee.getEmployeeID()}"/>
		<table>
		<tbody>
		<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" value="${employee.getFirstName()}"/></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" value="${employee.getLastName()}" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" value="${employee.getEmail()}"/></td>
					</tr>
					<tr>
						<td><label>Address:</label></td>
						<td><textarea  name="address" rows="4" cols="50" >${employee.getAddress()}</textarea></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td align="left"><input type="submit" value="Update" class="save" /></td>
					</tr>
		</tbody>
		</table>
		</form>
	</div>
</body>
</html>