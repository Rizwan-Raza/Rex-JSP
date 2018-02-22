<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String user = request.getParameter("user");
	String pass = request.getParameter("pass");

	DatabaseMetaData dbmd = con.getMetaData();
%>

<!DOCTYPE>
<html>
<head>
<%@include file="head.inc.html"%>
<script>
	function validate(objForm) {

		if (objForm.user.value.length == 0) {
			alert("Please enter User Name!");
			objForm.user.focus();
			return false;
		}
		if (objForm.pass.value.length == 0) {
			alert("Please enter Password!");
			objForm.pass.focus();
			return false;
		}
	}
</script>
</head>

<body>
	<div class="container-fluid">
		<br>
		<%
			if (user != null && user != "") {
				if (pass != null && pass != "") {

					PreparedStatement pstm = con.prepareStatement("USE mysql");
					pstm.execute();
					pstm = con.prepareStatement("INSERT INTO user (Host,User,Password) VALUES('%','" + user
							+ "',PASSWORD('" + pass + "'))");
					pstm.executeUpdate();

					pstm = con.prepareStatement("GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON *.* TO " + user
							+ "@'%' IDENTIFIED BY '" + pass + "'");
					pstm.execute();
					pstm = con.prepareStatement("FLUSH PRIVILEGES");
					pstm.execute();
					out.println("<div class='alert alert-success text-center' role='alert'>User : <b>" + user
							+ "</b> created Successfully with Connect And Resource Grant!</div>");
				}
			} else {
		%>
		<form action="createuser.jsp" method="post"
			onSubmit="return validate(this)" class="form-inline">
			Enter Username and Password (Note: User will be given Select, Insert,
			Update, Delete, Create And Drop Grant Automatically<br> <br>
			<div class="form-row">
				<div class="form-group col-md-4 col-sm-6">
					<label for="db-user">Username</label> <input type="text"
						name="user" class="form-control" id="db-user"
						placeholder="Username" maxlength=<%=dbmd.getMaxUserNameLength()%>>
				</div>
				<div class="form-group col-md-4 col-sm-6">
					<label for="db-pass">Password</label> <input type="password"
						class="form-control" id="db-pass" placeholder="Password"
						name="pass">
				</div>
				<div class="form-group col-md-4 col-sm-6">
					<button type="submit" class="btn btn-primary mt-4" value="Create"
						name="create">
						<i class="fa fa-fw fa-plus"></i> Create
					</button>
				</div>
			</div>
		</form>
		<%
			}
		%>
	</div>
</body>
</html>
