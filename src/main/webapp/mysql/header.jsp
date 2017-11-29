
<br>
<nav aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><i class="fa fa-fw fa-server"></i> <a
			href="welcome.jsp">Server : <b> <%=request.getParameter("server")%>
			</b>
		</a></li>
		<li class="breadcrumb-item" aria-current="page"><i
			class="fa fa-fw fa-database"></i> <a
			href="right.jsp?server=<%=request.getParameter("server")%>&db=<%=request.getParameter("db")%>">Database
				: <b> <%=request.getParameter("db")%>
			</b>
		</a></li>
		<%
			if (request.getParameter("table") != null && request.getParameter("table") != "") {
		%>
		<li class="breadcrumb-item" aria-current="page"><i
			class="fa fa-fw fa-table"></i> <a
			href="tabledata.jsp?server=<%=request.getParameter("server")%>&db=<%=request.getParameter("db")%>&table=<%=request.getParameter("table")%>">Table
				: <b> <%
 	out.println(request.getParameter("table"));
 %>
			</b>
		</a></li>
		<%
			}
		%>

	</ol>
</nav>
<nav class="nav nav-tabs">
	<a class="nav-item nav-link active"
		href="right.jsp?server=<%=request.getParameter("server")%>&db=<%=request.getParameter("db")%>"><i
		class="fa fa-fw fa-th-list"></i> Structure</a> <a
		class="nav-item nav-link"
		href="query.jsp?server=<%=request.getParameter("server")%>&db=<%=request.getParameter("db")%>"><i
		class="fa fa-fw fa-file-code-o"></i> Query</a> <a class="nav-item nav-link"
		href="tabledata.jsp?server=localhost&db=mysql&table=user"><i
		class="fa fa-fw fa-key"></i> Privileges</a>
	<%
		if (request.getParameter("table") != null && request.getParameter("table") != "") {
	%>
	<a class="nav-item nav-link text-success"
		href="export.jsp?db=<%=request.getParameter("db")%>&table=<%=request.getParameter("table")%>"><i
		class="fa fa-fw fa-sign-out"></i> Export</a> <a class="nav-item nav-link text-danger"
		href="javascript:dData('drop.jsp?db=<%=request.getParameter("db")%>&table=<%=request.getParameter("table")%>&empty=yes')"
		target="jspmain"><i class="fa fa-fw fa-trash"></i> Drop Data in <%=request.getParameter("table")%></a>
	<a class="nav-item nav-link text-danger"
		href="javascript:dTable('drop.jsp?db=<%=request.getParameter("db")%>&table=<%=request.getParameter("table")%>')"
		target="jspmain"><i class="fa fa-fw fa-trash"></i> Drop Data and
		Table <%=request.getParameter("table")%></a>
	<%
		} else {
	%>
	<a class="nav-item nav-link text-danger"
		href="javascript:dDatabase('drop.jsp?db=<%=request.getParameter("db")%>')"
		target="jspmain"><i class="fa fa-fw fa-trash"></i> Drop Database <%=request.getParameter("db")%></a>
	<%
		}
	%>
</nav>
