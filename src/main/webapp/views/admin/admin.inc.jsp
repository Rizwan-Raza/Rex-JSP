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
<br>
<div class="container">
	<div class="alert alert-success text-center fade in">
		<a class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<label>Welcome to Admin Panel Dummy User ! </label>
	</div>
	<ul class="nav nav-tabs nav-justified">
		<li class="active"><a data-toggle="tab" href="#clients">Clients</a></li>
		<li><a data-toggle="tab" href="#posts">Posted Properties</a></li>
		<li><a data-toggle="tab" href="#requires">Requested Properties</a></li>
		<li>
			<!-- <a data-toggle="tab" href="#map">Google Map</a> -->
			<a>Google Map</a>
		</li>
	</ul>

	<div class="tab-content">
		<!-- <?php 
			include 'clients.php'; 
			include 'properties.php'; 
			include 'required-props.php'; 
		?> -->
		<div id="map" class="tab-pane fade">
			<h3>Google API Map containing Properties marks</h3>
			<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
			<br>
			<!-- <?php print_r($_SESSION); ?> -->
		</div>
	</div>
</div>
