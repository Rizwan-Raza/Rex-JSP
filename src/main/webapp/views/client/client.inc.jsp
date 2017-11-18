<!--  <?php 
	include 'components/client/modals/editProfile.php'; 
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
<br>
<div class="container admin-section">
	<div class="alert alert-success text-center fade in">
		<a class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<label>Welcome to Client Panel Dummy User <%=session.getAttribute("fname") %>! </label>
	</div>
	<ul class="nav nav-tabs nav-justified">
		<li class="active"><a data-toggle="tab" href="#buy-property">Buy Property</a></li>
		<li><a data-toggle="tab" href="#sell-property">Sell Property</a></li>
		<li><a data-toggle="tab" href="#my-property">My Properties</a></li>
		<li><a data-toggle="tab" href="#wishlist">WishList</a></li>
	</ul>

	<div class="tab-content">
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