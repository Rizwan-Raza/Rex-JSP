<%@ page isErrorPage="true" import="java.util.*"%>
<!DOCTYPE>
<html>
<head>
<%@include file="head.inc.html"%>
</head>
<body>
	<div class="container-fluid">
		<br>
		<div class="alert alert-danger" role="alert">
			<h4 class="alert-heading">Error</h4>
			<p>
				The exception was: <b><%=exception.toString()%></b>
			</p>
			<hr>
			<p class="mb-0">
				The exception class was : <b><%=exception.getClass()%></b>
			</p>
		</div>
		<%
			/*PrintWriter tr = new PrintWriter(out);                        
			exception.printStackTrace(tr);*/

			application.log(request.getRequestURI() + request.getQueryString(), exception);
		%>
	</div>
</body>
</html>
