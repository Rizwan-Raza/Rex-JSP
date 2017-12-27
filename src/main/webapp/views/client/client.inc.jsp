<!--  <?php 
	include 'components/client/modals/editProfile.php'; -- done -- 
	include 'components/client/modals/postRequirement.php';
	include 'components/client/modals/myRequirement.php';
	include 'components/client/modals/othersRequirement.php';
	include 'components/client/modals/buyProperty.html';
	include 'components/client/modals/likers.html';
	include 'components/client/snacks/me.html';
	include 'components/client/snacks/props.html';

	include 'components/client/showProps.php';
	$GLOBALS['script'] = ""; 
?> -->
<jsp:directive.include file="modals/edit-profile.jspf" />
<br>
<div class="container client-section">
	<div
		class="alert alert-success text-center alert-dismissible fade show"
		role="alert">
		<label>Welcome to Client Panel <strong>${user.getUser().getFname()}</strong>!
		</label>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<nav class="nav nav-tabs nav-fill">
		<a class="nav-item nav-link active" href="#buy-property"
			data-toggle="tab" role="tab" aria-controls="buy-property"
			aria-selected="true">Buy Property</a> <a class="nav-item nav-link"
			href="#sell-property" data-toggle="tab" role="tab"
			aria-controls="sell-property" aria-selected="false">Sell Property</a>
		<a class="nav-item nav-link disabled" href="#my-property"
			data-toggle="tab" role="tab" aria-controls="my-property"
			aria-selected="false">My Properties</a> <a
			class="nav-item nav-link disabled" href="#wishlist" data-toggle="tab"
			role="tab" aria-controls="wishlist" aria-selected="false">WishList</a>
	</nav>

	<div class="tab-content">
		<jsp:directive.include file="sell.inc.html" />
		<!-- <?php 
			$conn = connectDB();
			include 'components/client/buyProperty.php'; 
			include 'components/client/sellProperty.php'; 
			include 'components/client/myProperty.php'; 
			include 'components/client/wishlist.php'; 
			closeDB($conn);
		?> -->
	</div>
</div>
<!-- <?php 
	echo "<script>function myMap() { ".$GLOBALS['script']." }</script>";
	// echo "<script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyCEq9Rz6ZI_LgkwBWA-2QT09nFhTBphPAU&callback=myMap'></script>";
?> -->