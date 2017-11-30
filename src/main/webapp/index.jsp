<jsp:directive.page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Real Estate eXplorer - REX</title>
<jsp:directive.include file="views/head.inc.html" />
</head>
<body>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<jsp:directive.include file="resources/js/detecter.jspf" />
	<jsp:directive.include file="views/nav.jspf" />
	<jsp:directive.include file="views/modals/error.inc.html" />
	<jsp:directive.include file="views/modals/image.inc.html" />
	<c:choose>
		<c:when test="${log ne null}">
			<jsp:directive.include file="views/modals/profile-picture.jspf" />
			<jsp:directive.include file="views/modals/change-password.inc.html" />
			<jsp:directive.include file="views/modals/logout.inc.html" />
			<c:choose>
				<c:when test="${log eq 'client'}">
					<jsp:directive.include file="views/client/client.inc.jsp" />
					<script type="text/javascript" src="resources/js/client.js"></script>
				</c:when>
				<c:otherwise>
					<jsp:directive.include file="views/admin/admin.inc.jsp" />
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
	<!--  <?php
		include "actions/detecter.php"; -- Done -- 
		include "components/nav.php"; -- Done --
		include 'components/modals/image.html';	 -- Done --
		include 'components/modals/wait.html';	
		include 'components/modals/seller.php'; 

		if (isset($_SESSION['log'])) {
			// Common Modals, Snacks and Modules ...
			include 'components/modals/profilePicture.php'; -- Done -- 
			include 'components/modals/changePassword.html';  -- Done --
			include 'components/modals/logout.html'; -- Done --
			
			include 'components/snacks/common.html'; 
			
			include 'components/modals/props/edit.html'; 
			include 'components/modals/props/delete.html'; 
			include 'components/modals/props/changeFeatures.html'; 
			include 'components/modals/props/changeInfo.html'; 
			include 'components/modals/cnpChangeAddress.html'; 
			include 'components/admin/modals/props/images.html'; 
			include 'components/admin/modals/props/addImage.html'; 
			include 'components/admin/modals/props/removeImage.html'; 
			include 'components/modals/props/requestEdit.html'; 
			include 'components/modals/props/requestDelete.html'; 
			
			echo '<div class="seperator"></div>'; -- Done --

			if ($_SESSION['log'] == "client") {
				include "components/client/client.php"; -- Done --
				echo '<script type="text/javascript" src="js/client.js"></script>'; -- Done --
			} else {
				include "components/admin/admin.php"; -- Done --
				echo '<script type="text/javascript" src="js/admin.js"></script>'; -- Done --
			}

			echo '<script type="text/javascript" src="js/active.js"></script>'; -- Done --
		} else {
			include "components/home/home.php"; -- Done --
			echo '<script type="text/javascript" src="js/inactive.js"></script>'; -- Done --
		}
		include 'components/footer.html'; -- Done --
	?> -->
	<jsp:directive.include file="views/footer.inc.html" />
	<script type="text/javascript" src="resources/js/common.js"></script>
</body>
</html>
