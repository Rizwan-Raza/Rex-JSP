<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String newdbname = request.getParameter("newdbname");
	String mknewdb = request.getParameter("mknewdb");
%>

<!DOCTYPE>
<html>
<head>
<%@include file="head.inc.html"%>
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
		} else if (IsNumeric(objForm.newtblfields.value) == false) {
			alert("Invalid No. of Table Columns!");
			return false;
		}
	}
</script>
</head>

<body>
	<div class="container-fluid">
		<br>

		<%
			// if (mknewdb=="1")
			//{
			//out.println("Please ");
			//}
			if (newdbname == "") {
				out.println("<br>");
				out.println(
						"<div class='alert alert-danger text-center' role='alert'>Please Insert Database Name</div>");
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");

			} else if (newdbname != null) {
				PreparedStatement pstm = con.prepareStatement("CREATE DATABASE " + newdbname);
				pstm.execute();
				out.println("<div class='alert alert-success text-center' role='alert'>Database : <b>" + newdbname
						+ "</b> Created Successfully!</div>");
		%>
		<div class="card bg-light w-100 my-4">
			<h4 class="card-header">
				<i class="fa fa-fw fa-table"></i> Create New Table
			</h4>
			<div class="card-body">
				<form action="createtbl.jsp?db=<%=newdbname%>" method="post"
					onSubmit="return validate(this)">
					<div class="form-row">
						<div class="col-7">
							<label class="sr-only" for="tb-name">Name</label> <input
								type="text" class="form-control mb-2 mr-sm-2 mb-sm-0"
								id="tb-name" placeholder="Table Name" name="newtblname">
						</div>
						<div class="col">
							<label class="sr-only" for="tb-o-fields">No. of Fields</label> <input
								type="number" class="form-control mb-2 mr-sm-2 mb-sm-0"
								id="tb-o-fields" placeholder="No. of Fields" name="newtblfields">
						</div>
						<div class="col">
							<button type="submit" class="btn btn-primary" value="Create"
								name="create">
								<i class="fa fa-fw fa-plus"></i> Create
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>
