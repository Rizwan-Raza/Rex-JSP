<%@ page language="java" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ include file="login.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String db = request.getParameter("db");
	String table = request.getParameter("table");
	String server = request.getParameter("server");
%>
<!DOCTYPE>
<html>
<head>
<%@include file="head.inc.html"%>
<script>
	function validate(objForm) {

		if (objForm.textarea.value.length == 0) {
			alert("Please enter SQL Query!");
			objForm.textarea.focus();
			return false;
		}
	}
</script>

</head>

<body>
	<div class="container-fluid">

		<%@ include file="header.jsp"%>
		<div class="card bg-light w-100 my-4">
			<h4 class="card-header">
				<i class="fa fa-fw fa-terminal"></i> Run SQL query/queries on
				database :
				<%=db%>
			</h4>
			<div class="card-body">
				<form action="tabledata.jsp?db=<%=db%>" method="post"
					onSubmit="return validate(this)">
					<textarea class="form-control mb-2" id="query" name="textarea"
						cols="0" rows="10"></textarea>
					<label class="custom-control custom-checkbox"> <input
						type="checkbox" class="custom-control-input" name="select"
						id="select"> <span class="custom-control-indicator"></span>
						<span class="custom-control-description">Non-Select Query</span>
					</label>
					<button type="submit" value="Go" name="go" class="btn btn-primary">Go</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
