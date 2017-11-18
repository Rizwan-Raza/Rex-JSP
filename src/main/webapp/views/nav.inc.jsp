<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#myNavbar" aria-controls="myNavbar" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand" href="/"><i class="fa fa-university"></i>
		REX</a>

	<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
			<li class="nav-item active"><a class="nav-link" href="/">Home
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#">About Us</a></li>
			<%
				if (session.getAttribute("log") == null) {
			%>
			<li class="nav-item"><a class="nav-link" href="#">Gallery</a></li>
			<%
				}
			%>
			<li class="nav-item"><a class="nav-link disabled" href="#">Contact
					Us</a></li>
		</ul>
		<%
			if (session.getAttribute("log") == null) {
		%>
		<ul class="navbar-nav ml-auto mt-2 my-lg-0">
			<li class="nav-item"><a class="nav-link" data-toggle="modal" data-target="#signupModal"><i class="fa fa-fw fa-user"></i>
					Sign Up</a></li>
			<div class="dropdown">
				<button class="btn btn-success dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Login</button>
				<div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" data-toggle="modal" data-target="#clientLoginModal"><i class="fa fa-fw fa-user"></i>
						Client Login</a> <a class="dropdown-item" data-toggle="modal" data-target="#adminLoginModal"><i
						class="fa fa-fw fa-user-secret"></i> Admin Login</a>
				</div>
			</div>
		</ul>
		<%
			} else {
		%>
		<!-- User Info -->
		<%
			}
		%>
	</div>
</nav>
