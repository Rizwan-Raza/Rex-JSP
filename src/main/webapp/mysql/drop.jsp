<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String db = request.getParameter("db");
	String table = request.getParameter("table");
	String empty = request.getParameter("empty");
%>

<!DOCTYPE>
<html>
<head>
<%@include file="head.inc.html"%>
</head>

<body>
	<div class="container-fluid">
		<br>
		<%
			if (empty == "" || empty == null) {
				if (db != null && table == null) {
					PreparedStatement pstm = con.prepareStatement("DROP DATABASE " + db);
					pstm.execute();
					out.println("<div class='alert alert-success text-center' role='alert'>Database : <b>" + db
							+ "</b> Dropped Successfully!</div>");
				} else if (table != null) {
					PreparedStatement pstm = con.prepareStatement("USE " + db);
					pstm.execute();
					pstm = con.prepareStatement("DROP TABLE " + table);
					pstm.execute();
					out.println("<div class='alert alert-success text-center' role='alert'>Table : <b>" + table
							+ "</b> dropped Successfully!</div>");
				}
			} else {
				if (db != null && table != null) {
					PreparedStatement pstm = con.prepareStatement("USE " + db);
					pstm.execute();
					pstm = con.prepareStatement("DELETE FROM " + table);
					pstm.execute();
					out.println("<div class='alert alert-success text-center' role='alert'>Table : <b>" + table
							+ "</b> Emptied Successfully!</div>");
				}
			}
		%>
	</div>
</body>
</html>
