<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
<title>Spring MVC example</title>

<spring:url value="/resources/css/bootstrap.min.css" var="boostrapCss" />
<link href="${boostrapCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="homeUrl" />
<spring:url value="/users/edit" var="addUserUrl" />
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header">
		<a class="navbar-brand" href="${homeUrl}">Spring User Website</a>
	</div>
	<div class="navbar-collapse">
		<div class="navbar-text navbar-right">
			<button class="btn btn-info" onclick="location.href='${addUserUrl}'">Add User</button>
		</div>
	</div>
</div>
</nav>