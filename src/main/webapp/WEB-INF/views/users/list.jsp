<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>

<html>
<jsp:include page="../partial/header.jsp" />

<body>
<div class="container">
	<c:if test="${msg != null}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>${msg}</strong>
		</div>
	</c:if>
	
	<h1>Users</h1>
	<table class="table">
	<thead>
		<tr>
			<th>ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="user" items="${users}">
		<tr>
			<td>${user.id}</td>
			<td>${user.firstName}</td>
			<td>${user.lastName}</td>
			<td>
				<spring:url value="/users/${user.id}" var="userShowUrl" />
				<spring:url value="/users/edit/${user.id}" var="userEditUrl" />
				<spring:url value="/users/delete/${user.id}" var="userDeleteUrl" />
				<button class="btn btn-info" onclick="location.href='${userShowUrl}'">Open</button>
				<button class="btn btn-primary" onclick="location.href='${userEditUrl}'">Update</button>
				<button class="btn btn-danger" onclick="deleteUser('${userDeleteUrl}',this)">Delete</button>
			</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
</div>
</body>

<jsp:include page="../partial/footer.jsp" />
</html>
