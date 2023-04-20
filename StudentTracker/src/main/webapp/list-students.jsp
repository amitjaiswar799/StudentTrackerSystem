
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>Student Tracker</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body>

	<div class="container mt-5">

		<h2 class="text-primary mb-4">List of students - Home Page</h2>
		   
		   <div class="container mb-4 ">
		     <a href="AddStudent.html" class="btn btn-primary" role="button" >Add Students</a>
		     <a href="Login.html" class="btn btn-danger float-end" role="button">logout</a>
		   </div>
		
		<table class="table table-bordered">

			<thead style="background-color: #0063f9" class="text-white">

				<tr>

					<th scope="col">StuId</th>

					<th scope="col">First</th>

					<th scope="col">Last</th>

					<th scope="col">Email</th>

					<th scope="col"></th>

				</tr>

			</thead>

			<tbody>
			
			<c:forEach var="tempStudent" items="${LIST_STUDENT}">
			
			   <!-- Setting Link for Delete Operation -->
			   
			   <c:url var="deletelink" value="studentController">
			   
			     <c:param name="command" value="Delete"/>
			     <c:param name="studentId" value="${tempStudent.id }"/>
			     
			   </c:url>
			
			
			  <!-- Setting Link for Update Operation -->
			   
			   <c:url var="updatelink" value="studentController">
			   
			     <c:param name="command" value="LOAD"/>
			     <c:param name="studentId" value="${tempStudent.id }"/>
			     
			   </c:url>
			   
				<tr>
					<th scope="row">${tempStudent.id}</th>

					<td>${tempStudent.firstName}</td>

					<td>${tempStudent.lastName}</td>

					<td>${tempStudent.email}</td>
					
					<td>
					<div class="float-end">
					<a class="btn btn-info mr-5" href="${updatelink}" role="button">Update</a>
					
					<a class="btn btn-danger" href="${deletelink}" role="button">Delete</a>
					
					</div>
					
					</td>
			   </tr>
			
			</c:forEach>

			</tbody>

		</table>

	</div>

</body>

</html>

