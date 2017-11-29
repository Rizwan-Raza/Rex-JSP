<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String db = request.getParameter("db");
	String table = request.getParameter("table");
	String field = request.getParameter("field");
	String val = request.getParameter("val");

	PreparedStatement pstm = con.prepareStatement("USE " + db);
	pstm.execute();
	pstm = con.prepareStatement("DELETE FROM " + table + " WHERE " + field + "='" + val + "'");
	pstm.executeUpdate();
%>
<jsp:include page="tabledata.jsp" />
<!DOCTYPE>
<html>
<head>
<%@include file="head.inc.html"%>
</head>
<body>
	<div class="container-fluid">
		<br>
		<%
			out.println("<div class='alert alert-success text-center' role='alert'>");
			out.println("<b>1</b> Row Deleted");
			out.println("</div>");
		%>
	</div>
</body>
</html>
