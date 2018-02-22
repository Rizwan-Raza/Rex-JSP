<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String db = request.getParameter("db");
	String alter = request.getParameter("alter");
	String newtblname = request.getParameter("newtblname");
	String temp;
	String newtblfields = request.getParameter("newtblfields");
	DatabaseMetaData dbmd = con.getMetaData();
	int m;
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
			if (newtblname == "") {
				out.println("<br>");
				out.println("<div class='alert alert-danger text-center' role='alert'>Please Insert Table Name</div>");
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
			} else if (alter != "" && alter != null) {
				String columnnm;
				PreparedStatement pstm1 = con.prepareStatement("USE `" + db + "`");
				ResultSet rst = pstm1.executeQuery();
				pstm1 = con.prepareStatement("SELECT * FROM " + newtblname);
				rst = pstm1.executeQuery();
				ResultSetMetaData rsmd = rst.getMetaData();
				// for(int i=1;i<=rsmd.getColumnCount(); i++) {
				//       columnnm=rsmd.getColumnName(i).toString();
				//      rsmd.getColumnTypeName(i);
				//    rsmd.getColumnDisplaySize(i);
		%>
		<form
			action="tabledata.jsp?db=<%=db%>&table=<%=newtblname%>&newtblfields=<%=rsmd.getColumnCount()%>"
			method="post">
			<table class="table table-hovered table-fluid table-striped">
				<thead style="background-color: #d0dce0">
					<tr>
						<th>Field</th>
						<th>Type</th>
						<th>Length</th>
						<th>Constraints</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (m = 1; m <= rsmd.getColumnCount(); m++) {
					%>

					<tr>
						<td>
							<%
								temp = "row" + m + "col1";
							%> <input type="text" name=<%=temp%> class="form-control"
							maxlength=<%=dbmd.getMaxColumnNameLength()%>
							value=<%=rsmd.getColumnName(m)%>>
						</td>
						<td>
							<%
								temp = "row" + m + "col2";
							%> <select name=<%=temp%> class="form-control custom-control">
								<option selected="selected"><%=rsmd.getColumnTypeName(m).toString()%></option>
								<option>VARCHAR</option>
								<option>DATE</option>
								<option>TIMESTAMP</option>
								<option>INT</option>
								<option>TINYINT</option>
						</select>
						<td>
							<%
								temp = "row" + m + "col3";
							%> <input type="text" name=<%=temp%> class="form-control"
							value=<%=rsmd.getColumnDisplaySize(m)%>>
						</td>
						<td
							style="width: 100px; text-align: left; background-color: #f5f5f5">
							<%
								temp = "row" + m + "col4";
							%> <select name=<%=temp%> class="form-control custom-control">
								<option></option>
								<option>PRIMARY KEY</option>
								<option>PRIMARY KEY AUTO_INCREMENT</option>
								<option>NULL</option>
								<option>NOT NULL</option>
								<option>NULL DEFAULT NULL</option>
								<option>NOT NULL DEFAULT '0'</option>
								<option>NOT NULL DEFAULT CURRENT_TIMESTAMP</option>
								<option>NOT NULL DEFAULT
									'resources/uploads/users/temp.png'</option>
						</select>
						</td>

					</tr>
					<%
						}
					%>
				</tbody>
				<tfoot style="background-color: #d0dce0">
					<tr>
						<td colspan="4"></td>
					</tr>
				</tfoot>
			</table>
			<button type="submit" class="btn btn-primary" value="Alter"
				name="create">Alter</button>
		</form>

		<%
			//   }
			} else if (newtblfields.length() == 0) {
				out.println("<br>");
				out.println(
						"<div class='alert alert-danger text-center' role='alert'>Please Insert Table Fields</div>");
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
			} else if (Integer.parseInt(newtblfields) > dbmd.getMaxColumnsInTable()) {
				out.println("<br>");
				out.println(
						"<div class='alert alert-danger text-center' role='alert'>Please Enter Column No. Less than <b>"
								+ dbmd.getMaxColumnsInTable() + "<b></div>");
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
			} else {
		%>
		<br>
		<form
			action="tabledata.jsp?db=<%=db%>&table=<%=newtblname%>&newtblfields=<%=newtblfields%>"
			method="post">
			<table class="table table-hovered table-fluid table-striped">
				<thead style="background-color: #d0dce0">
					<tr>
						<th>Field</th>
						<th>Type</th>
						<th>Length</th>
						<th>Constraints</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (m = 1; m <= Integer.parseInt(newtblfields); m++) {
					%>


					<tr>
						<td>
							<%
								temp = "row" + m + "col1";
							%> <input type="text" name=<%=temp%> class="form-control"
							maxlength=<%=dbmd.getMaxColumnNameLength()%>>
						</td>
						<td>
							<%
								temp = "row" + m + "col2";
							%> <select name=<%=temp%> class="form-control custom-control">
								<option>VARCHAR</option>
								<option>DATE</option>
								<option>TIMESTAMP</option>
								<option>INT</option>
								<option>TINYINT</option>
						</select>
						<td>
							<%
								temp = "row" + m + "col3";
							%> <input type="text" name=<%=temp%> class="form-control">
						</td>
						<td>
							<%
								temp = "row" + m + "col4";
							%> <select name=<%=temp%> class="form-control custom-control">
								<option></option>
								<option>PRIMARY KEY</option>
								<option>PRIMARY KEY AUTO_INCREMENT</option>
								<option>NULL</option>
								<option>NOT NULL</option>
								<option>NULL DEFAULT NULL</option>
								<option>NOT NULL DEFAULT '0'</option>
								<option>NOT NULL DEFAULT CURRENT_TIMESTAMP</option>
								<option>NOT NULL DEFAULT
									'resources/uploads/users/temp.png'</option>
						</select>
						</td>

					</tr>
					<%
						}
					%>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4"></td>
					</tr>
				</tfoot>
			</table>
			<button type="submit" class="btn btn-primary" value="Create"
				name="create">Create</button>
		</form>
		<%
			}
		%>
	</div>
</body>
</html>
