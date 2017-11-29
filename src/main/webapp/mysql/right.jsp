<%@ page language="java" import="java.sql.*,java.lang.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ include file="login.jsp"%>
<%@ include file="functions.jsp"%>


<%
	String db = request.getParameter("db");
	String server = request.getParameter("server");
	DatabaseMetaData dbmd = con.getMetaData();
%>
<!DOCTYPE>
<html>
<head>
<script src="confirm.js" type="text/javascript"></script>
<%@include file="head.inc.html" %>
<script>
<!--
	function IsNumeric(strString) //  check for valid numeric strings	
	{
		if (!/\D/.test(strString))
			return true;
		else
			return false;
	}
	function validate(objForm) {
		if (objForm.newtblfields.value.length == 0) {
			alert("Please Enter No. of Table Columns");
			return false;
		} else if (objForm.newtblname.value.length == 0) {
			alert("Please Enter Table Name!");
			return false;
		} else if (IsNumeric(objForm.newtblfields.value) == false) {
			alert("Invalid No. of Table Columns!");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container-fluid">

		<%@ include file="header.jsp"%>
		<%
			String[] tablelist = null;
			PreparedStatement pstm = con.prepareStatement("SHOW TABLES FROM " + db);
			ResultSet rst = pstm.executeQuery();
			tablelist = new String[mysql_num_rows(rst)];
			int num_tables = 0;
			while (rst.next()) {
				tablelist[num_tables] = rst.getString(1);
				num_tables++;
			}
		%>

		<table class="table table-hovered table-fluid table-striped">
			<thead style="background-color: #d0dce0">
				<tr>
					<th></th>
					<th>Tables</th>
					<th>Actions</th>
					<th>Records</th>
					<th>Type</th>
					<th>Collation</th>
				</tr>
			</thead>

			<tbody>
				<%
					for (int m = 0; m < num_tables; m++) {
						String localtable = tablelist[m];
				%>
				<tr>
					<td><label class="custom-control custom-checkbox"> <input
							type="checkbox" class="custom-control-input" name="kk"> <span
							class="custom-control-indicator"></span>
					</label></td>
					<td><a
						href="./tabledata.jsp?server=<%=server%>&db=<%=db%>&table=<%=localtable%>"
						target="jspmain"><%=localtable%></a></td>
					<td><a
						href="./tabledata.jsp?server=<%=server%>&db=<%=db%>&table=<%=localtable%>"
						target="jspmain"><i class="fa fa-fw fa-external-link"></i>
							Browse</a> <a
						href="./createtbl.jsp?newtblname=<%=localtable%>&db=<%=db%>&alter=yes"><i
							class="fa fa-fw fa-th-list"></i> Structure </a> <a
						href="./drop.jsp?table=<%=localtable%>&db=<%=db%>&empty=yes">
							<i class="fa fa-fw fa-inbox"></i> Empty
					</a> <a href="./drop.jsp?table=<%=localtable%>&db=<%=db%>"
						class="text-danger"><i class="fa fa-fw fa-trash"></i> Drop</a></td>
					<td>
						<%
							pstm = con.prepareStatement("USE `" + db + "`");
								pstm.execute();
								pstm = con.prepareStatement("SELECT COUNT(*) \"count\" FROM " + localtable);
								rst = pstm.executeQuery();
								String rows = "";
								while (rst.next()) {
									rows = rst.getString(1);
								}
								//                 String rows=rst.getString(0);
						%> <%=rows%>
					</td>
					<td>
						<%
							pstm = con.prepareStatement("USE `" + db + "`");
								pstm.execute();
								pstm = con.prepareStatement("SHOW TABLE STATUS");
								rst = pstm.executeQuery();

								rows = "";
								while (rst.next()) {
									rows = rst.getString(2);
								}
						%> <%=rows%>
					</td>
					<td>
						<%
							pstm = con.prepareStatement("USE `" + db + "`");
								pstm.execute();
								pstm = con.prepareStatement("SHOW TABLE STATUS");
								rst = pstm.executeQuery();

								rows = "";
								while (rst.next()) {
									rows = rst.getString(15);
								}
						%> <%=rows%>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
			<tfoot style="background-color: #d0dce0">
				<tr class="text-center">
					<th colspan="6"><%=num_tables%> table(s)</th>
				</tr>
			</tfoot>

		</table>
		<div class="card bg-light w-100 my-4">
			<h4 class="card-header">
				<i class="fa fa-fw fa-table"></i> Create New Table
			</h4>
			<div class="card-body">
				<form action="./createtbl.jsp?db=<%=db%>" method="post"
					onSubmit="return validate(this)">
					<div class="form-row">
						<div class="col-7">
							<label class="sr-only" for="tb-name">Name</label> <input
								type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
								id="tb-name" placeholder="Table Name" name="newtblname"
								maxlength=<%=dbmd.getMaxTableNameLength()%>>
						</div>
						<div class="col">
							<label class="sr-only" for="tb-o-fields">No. of Fields</label> <input
								type="number" class="form-control mb-2 mr-sm-2 mb-sm-0"
								id="tb-o-fields" placeholder="No. of Fields" name="newtblfields"
								maxlength=<%=String.valueOf(dbmd.getMaxColumnsInTable()).length()%>>
						</div>
						<div class="col">
							<button type="submit" class="btn btn-primary" value="Create"
								name="create"><i class="fa fa-fw fa-plus"></i> Create</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>