<%@ page language="java" import="java.sql.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%@ include file="functions.jsp"%>
<%
	if (con == null) {
		response.sendRedirect("index.jsp");
	}
%>
<%
	String server = Host;

	PreparedStatement pstm = con.prepareStatement("SHOW DATABASES");
	ResultSet rst = pstm.executeQuery();
	String[] dblist = null;
	int num_dbs = 0;
	if (dblist == null) {

		dblist = new String[mysql_num_rows(rst)];
		while (rst.next()) {
			dblist[num_dbs] = rst.getString(1);
			num_dbs++;
		}
		rst.close();
	}
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
<%@include file="head.inc.html"%>
</head>
<body class="bg-light">
	<figure class="pt-3 px-2">
		<img class="icon" src="./Images/logosml.png" alt="Logo">
	</figure>
	<nav class="nav flex-column">
		<a class="nav-link active" href="welcome.jsp" target="jspmain"><i
			class="fa fa-fw fa-home"></i> <b>Home</b></a> <a
			class="nav-link disabled" href="Introduction.pdf" target="jspmain"><i
			class="fa fa-fw fa-book"></i> <b>Documentation</b></a>
	</nav>
	<div class="list-group">
		<%
			for (int i = 0; i < num_dbs; i++) {
				String localdb = dblist[i];
		%>

		<a class="list-group-item list-group-item-action"
			href="right.jsp?server=<%=server%>&amp;db=<%=localdb%>"
			target="jspmain"><i class="fa fa-fw fa-database"></i> <%=localdb%></a>
		<%
			}
		%>
	</div>
</body>
</html>