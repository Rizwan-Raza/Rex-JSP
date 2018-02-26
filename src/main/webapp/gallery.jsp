<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:directive.page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About Us | R.E.X</title>
<jsp:directive.include file="views/head.inc.html" />
</head>
<body>
	<jsp:directive.include file="resources/js/detecter.jspf" />
	<jsp:directive.include file="views/nav.jspf" />
	<jsp:directive.include file="views/modals/error.inc.html" />
	<jsp:directive.include file="views/modals/success.inc.html" />
	<jsp:directive.include file="views/modals/image.inc.html" />
	<jsp:directive.include file="views/modals/wait.inc.html" />
	<c:choose>
		<c:when test="${log ne null}">
			<jsp:directive.include file="views/modals/profile-picture.jspf" />
			<jsp:directive.include file="views/modals/change-password.inc.html" />
			<jsp:directive.include file="views/modals/logout.inc.html" />
			<script type="text/javascript" src="resources/js/active.js"></script>
		</c:when>
		<c:otherwise>
			<jsp:directive.include file="views/client/modals/login.inc.html" />
			<jsp:directive.include file="views/client/modals/auth-error.inc.html" />
			<jsp:directive.include
				file="views/client/modals/active-error.inc.html" />

			<jsp:directive.include file="views/admin/modals/login.inc.html" />
			<jsp:directive.include file="views/admin/modals/auth-error.inc.html" />

			<jsp:directive.include file="views/modals/signup.inc.html" />
			<jsp:directive.include file="views/modals/signup-success.inc.html" />
			<jsp:directive.include file="views/modals/forgot-password.inc.html" />

			<script type="text/javascript" src="resources/js/inactive.js"></script>
		</c:otherwise>
	</c:choose>
	<div class="container py-4">
		<h1 class="text-center">Gallery</h1>
	</div>
	<jsp:directive.include file="views/footer.inc.html" />

	<script type="text/javascript" src="resources/js/common.js"></script>
</body>
</html>