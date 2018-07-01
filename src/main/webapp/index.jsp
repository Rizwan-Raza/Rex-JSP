<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/functions" prefix="cf"%>
<jsp:directive.page language="java"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Real Estate eXplorer - REX</title>
<jsp:directive.include file="views/head.inc.html" />
</head>
<body>
	<jsp:directive.include file="resources/js/detecter.jspf" />
	<jsp:directive.include file="views/nav.jspf" />
	<jsp:directive.include file="views/modals/error.inc.html" />
	<jsp:directive.include file="views/modals/success.inc.html" />
	<jsp:directive.include file="views/modals/image.inc.html" />
	<jsp:directive.include file="views/modals/wait.inc.html" />
	<jsp:directive.include file="views/modals/seller.jspf" />
	<c:choose>
		<c:when test="${log ne null and user ne null}">
			<jsp:directive.include file="views/modals/profile-picture.jspf" />
			<jsp:directive.include file="views/modals/change-password.inc.html" />
			<jsp:directive.include file="views/modals/logout.inc.html" />

			<jsp:directive.include file="views/modals/props/edit.inc.html" />
			<jsp:directive.include file="views/modals/props/delete.inc.html" />
			<jsp:directive.include
				file="views/modals/props/change-features.inc.html" />
			<jsp:directive.include file="views/modals/props/change-info.inc.html" />
			<jsp:directive.include file="views/modals/change-address.inc.html" />
			<jsp:directive.include
				file="views/modals/props/images/images.inc.html" />
			<jsp:directive.include
				file="views/modals/props/images/add-image.inc.html" />
			<jsp:directive.include
				file="views/modals/props/images/remove-image.inc.html" />
			<jsp:directive.include
				file="views/modals/props/request-delete.inc.html" />
			<jsp:directive.include
				file="views/modals/props/request-edit.inc.html" />
			<c:choose>
				<c:when test="${log eq 'client'}">
					<jsp:directive.include file="views/client/client.jspf" />
					<script type="text/javascript" src="resources/js/client.js"></script>
				</c:when>
				<c:otherwise>
					<jsp:directive.include file="views/admin/admin.jspf" />
					<script type="text/javascript" src="resources/js/admin.js"></script>
				</c:otherwise>
			</c:choose>
			<script type="text/javascript" src="resources/js/active.js"></script>
		</c:when>
		<c:otherwise>
			<jsp:directive.include file="views/home/home.jspf" />
			<script type="text/javascript" src="resources/js/inactive.js"></script>
		</c:otherwise>
	</c:choose>
	<jsp:directive.include file="views/footer.inc.html" />
	<script type="text/javascript" src="resources/js/common.js"></script>
</body>
</html>
