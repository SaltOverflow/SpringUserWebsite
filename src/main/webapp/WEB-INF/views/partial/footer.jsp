<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/helpers.js" var="helpersJs" />
<script src="${bootstrapJs}"></script>
<script src="${helpersJs}"></script>
