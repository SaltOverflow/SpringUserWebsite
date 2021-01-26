<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

	<c:choose>
	<c:when test="${user.isNew()}">
		<h1>Add</h1>
	</c:when>
	<c:otherwise>
		<h1>Update</h1>
	</c:otherwise>
	</c:choose>
	
	<spring:url value="/users/save" var="updateUserUrl" />
	<form:form method="post" modelAttribute="user" action="${updateUserUrl}">
		<form:hidden path="id"/>
		
		<spring:bind path="firstName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm control-label">First name</label>
			<div class="col-sm">
				<form:input path="firstName" type="text" id="firstName" />
				<form:errors path="firstName" class="control-label" />
			</div>
			</div>
		</spring:bind>
		
		<spring:bind path="lastName">
			<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm control-label">Last name</label>
			<div class="col-sm">
				<form:input path="lastName" type="text" id="lastName" />
				<form:errors path="lastName" class="control-label" />
			</div>
			</div>
		</spring:bind>
		
		<div class="form-group">
		<div class="pull-right">
			<c:choose>
			<c:when test="${user.isNew()}">
				<button type="submit" class="btn btn-primary">Add</button>
			</c:when>
			<c:otherwise>
				<button type="submit" class="btn btn-primary">Update</button>
			</c:otherwise>
			</c:choose>
		</div>
		</div>
	</form:form>
</div>
</body>

<jsp:include page="../partial/footer.jsp" />
</html>
