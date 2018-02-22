<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String newtblfields = request.getParameter("newtblfields");
	String db = request.getParameter("db");
	String table = request.getParameter("table");
	boolean f = false;
	String query = "";
	if (request.getParameter("col1") != "") {

		for (int i = 1; i <= Integer.parseInt(newtblfields); i++) {
			if (request.getParameter("col" + i) != "") {
				if (query == "") {
					query = "\"" + request.getParameter("col" + i) + "\"";
				} else {
					query = query + ",\"" + request.getParameter("col" + i) + "\"";
				}
			} else {
				f = true;
				break;

			}
		}

		if (f == false) {
			query = " values(" + query;
			query = query + ")";

			PreparedStatement pstm = con.prepareStatement("USE " + db);
			pstm.execute();
			pstm = con.prepareStatement("INSERT INTO " + table + query);
			pstm.executeUpdate();
		}

	} else {
		f = true;
	}
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
			if (f == false) {
				out.println("<div class='alert alert-success text-center' role='alert'>");
				out.println("INSERT INTO <b>" + table + query);
				out.println("</b> ");
				out.println("<b>1</b> Row Inserted</div>");
			} else {
				out.println("<div class='alert alert-danger text-center' role='alert'>");
				out.println("Some fields are empty</div>");
			}
		%>
	</div>
</body>
</html>
