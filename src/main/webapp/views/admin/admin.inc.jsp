<!-- <?php 
	// For Clients 
	include 'snacks/clients.html';
	include 'modals/clients/activation.html'; 
	include 'modals/clients/kickout.html'; 
	include 'modals/clients/promote.html'; 
	include 'modals/clients/edit.html'; 
	include 'modals/clients/address.html'; 
	include 'modals/clients/changePassword.html'; 
	include 'modals/clients/changePicture.html'; 
	include 'modals/clients/mail.php'; 

	// For Props 
	include 'modals/props/address.html'; 
	include 'modals/props/features.html'; 
	include 'modals/props/info.html'; 

	// For Self
	include 'snacks/me.html';
	include 'modals/editProfile.php'; 
	include 'components/errorSnacks.html';
?> -->
<jsp:directive.include file="modals/clients/activation.html" />
<jsp:directive.include file="modals/clients/kickout.html" />
<jsp:directive.include file="modals/clients/promote.html" />
<jsp:directive.include file="modals/clients/edit.html" />
<jsp:directive.include file="modals/clients/address.html" />
<jsp:directive.include file="modals/clients/change-password.html" />
<jsp:directive.include file="modals/clients/change-picture.html" />
<jsp:directive.include file="modals/clients/mail.jspf" />

<jsp:directive.include file="modals/edit-profile.jspf" />
<br>
<div class="container admin-section">
	<div
		class="alert alert-success text-center alert-dismissible fade show"
		role="alert">
		<label>Welcome to Admin Panel <strong>${user.getUser().getFname()}</strong>!
		</label>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>

	<nav class="nav nav-tabs nav-fill">
		<a class="nav-item nav-link active" href="#clients" data-toggle="tab"
			role="tab" aria-controls="clients" aria-selected="true">Clients</a> <a
			class="nav-item nav-link" href="#posts" data-toggle="tab" role="tab"
			aria-controls="posts" aria-selected="false">Posted Properties</a> <a
			class="nav-item nav-link disabled" href="#requires" data-toggle="tab"
			role="tab" aria-controls="requires" aria-selected="false">Requested
			Properties</a> <a class="nav-item nav-link disabled" href="#map"
			data-toggle="tab" role="tab" aria-controls="map"
			aria-selected="false">Google Map</a>
	</nav>

	<div class="tab-content">
		<jsp:directive.include file="clients.jspf" />
		<!-- <?php 
			include 'clients.php'; 
			include 'properties.php'; 
			include 'required-props.php'; 
		?> -->
		<div id="map" class="tab-pane fade">
			<h3>Google API Map containing Properties marks</h3>
			<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem
				accusantium doloremque laudantium, totam rem aperiam.</p>
			<br>
			<!-- <?php print_r($_SESSION); ?> -->
		</div>
	</div>
</div>
