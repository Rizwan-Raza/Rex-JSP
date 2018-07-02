<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/functions" prefix="cf"%>

<jsp:directive.page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
<head>
<title>Search Result | R.E.X</title>
<jsp:directive.include file="head.inc.html" /></head>
<style>
.carousel .user-dp {
	left: 50%;
	margin-left: -100px;
}
</style>
<body class="bg-default">
	<%
		if (request.getAttribute("result") != null) {
	%>
	<jsp:directive.include file="../resources/js/detecter.jspf" />
	<jsp:directive.include file="../views/modals/seller.jspf" />

	<jsp:directive.include file="nav.jspf" />
	<jsp:directive.include file="modals/error.inc.html" />
	<jsp:directive.include file="modals/success.inc.html" />
	<jsp:directive.include file="modals/image.inc.html" />
	<jsp:directive.include file="modals/wait.inc.html" />
	<c:choose>
		<c:when test="${log ne null}">
			<jsp:directive.include file="modals/profile-picture.jspf" />
			<jsp:directive.include file="modals/change-password.inc.html" />
			<jsp:directive.include file="modals/logout.inc.html" />
			<script type="text/javascript" src="resources/js/active.js"></script>
		</c:when>
		<c:otherwise>
			<jsp:directive.include file="client/modals/login.inc.html" />
			<jsp:directive.include file="client/modals/auth-error.inc.html" />
			<jsp:directive.include file="client/modals/active-error.inc.html" />

			<jsp:directive.include file="admin/modals/login.inc.html" />
			<jsp:directive.include file="admin/modals/auth-error.inc.html" />

			<jsp:directive.include file="modals/signup.inc.html" />
			<jsp:directive.include file="modals/signup-success.inc.html" />
			<jsp:directive.include file="modals/forgot-password.inc.html" />

			<script type="text/javascript" src="resources/js/inactive.js"></script>
		</c:otherwise>
	</c:choose>

	<%
		request.setAttribute("props", request.getAttribute("result"));
	%>
	<c:set var="fetcherType" value="SEARCH" />
	<div class="container pt-4">
		<c:if test="${props ne null or props.size() ne '0' or empty props }">
			<h5>
				<b id='post-num'>${props.size() }</b> Search results found.
			</h5>
		</c:if>
		<jsp:directive.include file="../views/client/propFetcher.jspf" />
	</div>

	<jsp:directive.include file="footer.inc.html" />
	<script type="text/javascript" src="resources/js/common.js"></script>
	<%
		} else {
	%>
	<%
		response.sendError(404);
		}
	%>

</body>
</html>