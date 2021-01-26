<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	
	<h1>User</h1>
	<table class="table">
		<tr>
			<td>Id</td>
			<td>${user.id}</td>
		</tr>
		<tr>
			<td>First name</td>
			<td>${user.firstName}</td>
		</tr>
		<tr>
			<td>Last name</td>
			<td>${user.lastName}</td>
		</tr>
	</table>
</div>
</body>

<jsp:include page="../partial/footer.jsp" />
</html>
