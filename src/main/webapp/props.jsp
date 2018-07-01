<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.GregorianCalendar"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Properties File Editor</title>
<jsp:directive.include file="views/head.inc.html" />
<script>
	function validate(objForm) {
		if (objForm.pass.value.length == 0) {
			alert("Please enter password!");
			objForm.pass.focus();
			return false;
		}

		return true;
	}
	function updator(key, value, file) {
		$.ajax({
			url : "PropValUpdator",
			type : "post",
			data : {
				key : key,
				value : value,
				file : file
			},
			success : function(data) {
				snackbar(data + "Updated Successfully");
			},
			error : function(xhr, status) {
				snackbar("Something went wrong");
			}
		});
	}
	function valPasser(elem) {
		/*alert($(elem).parent().parent().find("input.key").val() + ", "
				+ $(elem).parent().parent().find("input.value").val() + ", "
				+ $(elem).parent().parent().parent().attr("id"));*/

		updator($(elem).parent().parent().find("input.key").val(), $(elem)
				.parent().parent().find("input.value").val(), $(elem).parent()
				.parent().parent().parent().attr("id"));
	}
</script>
</head>
<body class="bg-default">
	<div class="container my-4">
		<%
			if (request.getMethod().equalsIgnoreCase("post")) {
				session.setAttribute("prop_auth", request.getParameter("pass"));
			}
			if (request.getParameter("logout") != null && request.getParameter("logout").equals("true"))
				session.removeAttribute("prop_auth");

			if (session.getAttribute("prop_auth") != null && session.getAttribute("prop_auth").equals("Rex")) {
		%>
		<div class="row">
			<div class="col-md-6" id="db">
				<div class="card card-body">
					<h4 class="card-title">Database Credentials</h4>
					<%
						Properties db = new Properties();
							db.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
							for (String key : db.stringPropertyNames()) {
								String value = db.getProperty(key);
					%>
					<div class="row no-gutters my-2">
						<div class="col-sm-4">
							<input type='text' disabled class="form-control key"
								value="<%=key%>" />
						</div>
						<div class="col-sm-7">
							<input type='text' class="form-control value" value="<%=value%>" />
						</div>
						<div class="col-sm-1">
							<button class="btn btn-success btn-block"
								onclick="valPasser(this)">
								<i class="fa fa-check"></i>
							</button>
						</div>
					</div>
					<%
						}
					%>
				</div>
			</div>
			<div class="col-md-6" id="mailer">
				<div class="card card-body">
					<h4 class="card-title">Mailer Credentials</h4>
					<%
						Properties mailer = new Properties();
							mailer.load(getClass().getClassLoader().getResourceAsStream("mailer.properties"));
							for (String key : mailer.stringPropertyNames()) {
								String value = mailer.getProperty(key);
					%>
					<div class="row no-gutters my-2">
						<div class="col-sm-4">
							<input type='text' disabled class="form-control key"
								value="<%=key%>" />
						</div>
						<div class="col-sm-7">
							<input type='text' class="form-control value" value="<%=value%>" />
						</div>
						<div class="col-sm-1">
							<button class="btn btn-success btn-block"
								onclick="valPasser(this)">
								<i class="fa fa-check"></i>
							</button>
						</div>
					</div>
					<%
						}
					%>
				</div>
			</div>
		</div>
		<button class="btn btn-success btn-lg fixed-top hoverable"
			style="left: auto;" type="submit"
			onclick="window.location.href='props.jsp?logout=true'">
			<i class="fa fa-power-off fa-2x"></i>
		</button>
		<%
			} else {
		%>
		<div class="card mx-auto col-12 col-sm-6 py-3">
			<h3 class="text-center">
				<i class="fa fa-edit"></i> Props Editor and Viewer
			</h3>
			<div class="card-body">
				<p class="card-text text-center text-muted"><%=new GregorianCalendar().getTime().toString()%></p>

				<form action="props.jsp" method="post" name="login"
					onSubmit="return validate(this)">
					<div class="form-group">
						<label class="col-form-label" for="prop-password">Password</label>
						<input type="password" class="form-control" id="prop-password"
							placeholder="Password" name="pass">
					</div>
					<button class="btn btn-success float-right" type="submit"
						value="Login" name="login">Submit</button>
					<button class="btn btn-primary" onclick="window.location.href='./'"
						type="button">Main Site</button>
				</form>
			</div>
		</div>

		<%
			}
		%>
	</div>
	<script type="text/javascript" src="resources/js/common.js"></script>
</body>
</html>