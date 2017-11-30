<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%
	String db = request.getParameter("db");
	String table = request.getParameter("table");
	String query1 = request.getParameter("textarea");
	String newtblname = request.getParameter("table");
	String newtblfields = request.getParameter("newtblfields");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	ResultSetMetaData rsmd;
	int num_rows = 0;
	ResultSet rst;

	if (request.getParameter("row1col1") != null) {
		String query = "";

		for (int i = 1; i <= Integer.parseInt(newtblfields); i++) {
			if (request.getParameter("row" + i + "col1") != "") {
				if (query == "") {
					query = request.getParameter("row" + i + "col1") + " "
							+ request.getParameter("row" + i + "col2");
					if (request.getParameter("row" + i + "col3").length() != 0) {
						query += "(" + request.getParameter("row" + i + "col3") + ") ";
					} else {
						query += " ";
					}
					query += request.getParameter("row" + i + "col4");
				} else {
					query = query + "," + request.getParameter("row" + i + "col1") + " "
							+ request.getParameter("row" + i + "col2");
					if (request.getParameter("row" + i + "col3").length() != 0) {
						query += "(" + request.getParameter("row" + i + "col3") + ") ";
					} else {
						query += " ";
					}
					query += request.getParameter("row" + i + "col4");
				}
			} else {
				break;
			}
		}
		query = "(" + query;
		query = query + ")";

		PreparedStatement pstm = con.prepareStatement("USE " + db);
		rst = pstm.executeQuery();
		pstm = con.prepareStatement("CREATE TABLE " + newtblname + query);
		pstm.execute();

		out.println("Table : " + newtblname + " created successfully!");

	}
%>

<!DOCTYPE>
<html>
<head>
<%@include file="head.inc.html"%>
<script src="confirm.js" type="text/javascript"></script>

<title>Table Data</title>
<body>
	<div class="container-fluid">

		<%@ include file="header.jsp"%>
		<%
			String columnnm, record;
			PreparedStatement pstm1 = con.prepareStatement("USE `" + db + "`");
			rst = pstm1.executeQuery();
			if (query1 == "" || query1 == null) {
				pstm1 = con.prepareStatement("SELECT * FROM " + table);
			} else {
				pstm1 = con.prepareStatement(query1);
			}
			rst = pstm1.executeQuery();
			rsmd = rst.getMetaData();
		%>

		<table class="table table-hovered table-fluid table-striped table-sm">
			<thead style="background-color: #d0dce0">
				<tr>
					<th>Action</th>
					<%
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
							columnnm = rsmd.getColumnName(i).toString();
					%>

					<th><%=columnnm + "  " + rsmd.getColumnTypeName(i) + "(" + rsmd.getColumnDisplaySize(i) + ")"%></th>

					<%
						}
					%>
				</tr>
			</thead>
			<tbody>
				<%
					while (rst.next()) {
				%>
				<tr>
					<td><a
						href="javascript:dRecord('deletedata.jsp?db=<%=db%>&table=<%=table%>&field=<%=rsmd.getColumnName(1)%>&val=<%=rst.getString(1)%>')"><i
							class="fa fa-fw fa-trash text-danger"></i></a></td>
					<%
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
								record = rst.getString(i);
					%>

					<td><%=record%></td>
					<%
						}
					%>
				</tr>
				<%
					num_rows++;
					}
				%>
			</tbody>
			<tfoot style="background-color: #d0dce0">
				<tr>
					<th colspan="<%=rsmd.getColumnCount() + 1%>"><%=num_rows%>
						Row(s)</th>
				</tr>
			</tfoot>
		</table>
		<div class="card bg-light w-100 my-4">
			<h4 class="card-header">
				<i class="fa fa-fw fa-indent"></i> Insert New Row
			</h4>
			<div class="card-body">
				<form method=post
					action="insertdata.jsp?newtblfields=<%=rsmd.getColumnCount()%>&db=<%=db%>&table=<%=table%>"
					class="form-inline">
					<%
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					%>
					<input type="text" name=<%="col" + i%>
						class="form-control mb-2 mr-2" style="width: 100px;"
						maxlength=<%=rsmd.getColumnDisplaySize(i)%>
						placeholder="<%=rsmd.getColumnName(i).toString()%>">

					<%
						}
					%>
					<button type="submit" value="Insert" name="insert"
						class="btn btn-primary">Insert</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
