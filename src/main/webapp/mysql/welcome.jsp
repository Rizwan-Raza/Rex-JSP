<%@ page language="java" import="java.sql.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%
	String server = Host;
	PreparedStatement pstm = con.prepareStatement("SHOW STATUS");
	ResultSet rst = pstm.executeQuery();
	DatabaseMetaData dbmd = con.getMetaData();
	String i = "1";
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../resources/css/font-awesome.min.css">
<!-- <link href="./default.css" rel="stylesheet" type="text/css" /> -->


<script>
	function validate(objForm) {

		if (objForm.newdbname.value.length == 0) {
			alert("Please enter Database Name!");
			objForm.newdbname.focus();
			return false;
		}
	}
</script>
</head>

<body>
	<div class="container-fluid">
		<div class="card bg-light w-100 my-4">
			<h4 class="card-header">
				<i class="fa fa-fw fa-info-circle"></i> My SQL Information
			</h4>
			<ul class="list-group list-group-flush">
				<li class="list-group-item">Server Version : <%=dbmd.getDatabaseProductVersion()%></li>
				<li class="list-group-item">Server : <%=dbmd.getDatabaseProductName()%></li>
				<li class="list-group-item">User : <%=dbmd.getUserName()%></li>
			</ul>
		</div>
		<div class="card bg-light w-100 my-4">
			<h4 class="card-header">
				<i class="fa fa-fw fa-database"></i> Create New Database
			</h4>
			<div class="card-body">
				<form action="./createdb.jsp?mknewdb=<%=i%>" method="post"
					name="newdbname" onSubmit="return validate(this)">
					<div class="form-row">
						<div class="col-9">
							<input type="text" name="newdbname"
								maxlength=<%=dbmd.getMaxTableNameLength()%> class="form-control">
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
		<nav class="nav flex-column"> <a class="nav-link"
			href="createuser.jsp"><i class="fa fa-fw fa-user-plus"></i>
			Create User</a> <a class="nav-link"
			href="tabledata.jsp?server=localhost&db=mysql&table=user"><i
			class="fa fa-fw fa-key"></i> Privileges</a> </nav>
	</div>
</body>
</html>
