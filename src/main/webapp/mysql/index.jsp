<%@ page language="java" pageEncoding="ISO-8859-1" errorPage="error.jsp"
	contentType="text/html"%>
<%@page import="java.util.*"%>
<%
	//
	String t = new GregorianCalendar().getTime().toString();
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<title>JSPMyAdmin 1.0</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<script>
	function validate(objForm) {

		if (objForm.user.value.length == 0) {
			alert("Please enter username!");
			objForm.user.focus();
			return false;
		}

		if (objForm.pass.value.length == 0) {
			alert("Please enter password!");
			objForm.pass.focus();
			return false;
		}
		if (objForm.host.value.length == 0) {
			alert("Please enter Host Name!");
			objForm.host.focus();
			return false;
		}

		if (objForm.port.value.length == 0) {
			alert("Please enter port name!");
			objForm.port.focus();
			return false;
		}

		return true;
	}
</script>
</head>

<body class="bg-default">
	<br>
	<div class="card mx-auto col-12 col-sm-6 col-md-4 col-lg-3">
		<img class="card-img-top pt-3 px-3" src="mysql/Images/logo.png"
			alt="JSP MyAdmin">
		<div class="card-body">
			<p class="card-text text-center text-muted"><%=t%></p>

			<form action="mysql/Main.jsp" method="post" name="login"
				onSubmit="return validate(this)" target="_top">
				<div class="form-group">
					<label class="col-form-label" for="db-username">Username</label> <input
						type="text" class="form-control" id="db-username"
						placeholder="Username" name="user">
				</div>
				<div class="form-group">
					<label class="col-form-label" for="db-password">Password</label> <input
						type="password" class="form-control" id="db-password"
						placeholder="Password" name="pass">
				</div>
				<div class="form-group">
					<label class="col-form-label" for="db-host">Host</label> <input
						type="text" class="form-control" id="db-host" placeholder="Host"
						name="host" value="mysql">
				</div>
				<div class="form-group">
					<label class="col-form-label" for="db-port">Port</label> <input
						type="number" class="form-control" id="db-port" placeholder="Port"
						name="port" value="3306">
				</div>

				<button class="btn btn-success" type="submit" value="Login"
					name="login">Submit</button>
			</form>

		</div>
	</div>
</body>
</html>
