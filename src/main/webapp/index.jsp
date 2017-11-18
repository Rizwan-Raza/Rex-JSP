<!DOCTYPE html>
<html lang="en">
<head>
	<title>Real Estate eXplorer - REX</title>
	<%@include file="views/head.inc.html" %>
</head>
<body>
	<jsp:include  page="views/nav.inc.jsp"/>
	<% if (session.getAttribute("log")!=null) { %>
	<div class="seperator"></div>
	<% if (session.getAttribute("log").equals("client")) { %>
	<jsp:include  page="views/client/client.inc.jsp"/>
	<% } else { %>
	<jsp:include  page="views/admin/admin.inc.jsp"/>
	<% } %>
	<script type="text/javascript" src="resources/js/active.js"></script>
	<% } else { %>
	<jsp:include page="views/home/home.inc.jsp"/>	
	<script type="text/javascript" src="resources/js/inactive.js"></script>
	<% } %>
	<!--  <?php
		include "actions/detecter.php";
		include "components/nav.php";
		include 'components/modals/image.html';	
		include 'components/modals/wait.html';	
		include 'components/modals/seller.php'; 

		if (isset($_SESSION['log'])) {
			// Common Modals, Snacks and Modules ...
			include 'components/modals/profilePicture.php'; 
			include 'components/modals/changePassword.html'; 
			include 'components/modals/logout.html';
			
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
			
			echo '<div class="seperator"></div>';

			if ($_SESSION['log'] == "client") {
				include "components/client/client.php";
				echo '<script type="text/javascript" src="js/client.js"></script>';
			} else {
				include "components/admin/admin.php";
				echo '<script type="text/javascript" src="js/admin.js"></script>';
			}

			echo '<script type="text/javascript" src="js/active.js"></script>';
		} else {
			include "components/home/home.php";
			echo '<script type="text/javascript" src="js/inactive.js"></script>';
		}
		include 'components/footer.html';
	?> -->
	<%@include file="views/footer.inc.html" %>	
	<script type="text/javascript" src="resources/js/common.js"></script>
</body>
</html>
