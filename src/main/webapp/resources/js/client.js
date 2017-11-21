/****** Client start ***********/
$(document).ready(function (){
	ageChecker();
});
function ageChecker() {
	if ($("#sell-property #t-type").val() == "Under Construction") {
		$("#sell-property #age").removeAttr("required");
		$("#sell-property #age").attr("disabled","disabled");
	} else {
		$("#sell-property #age").removeAttr("disabled");
	}
}
function likeSuccessBlock(data , status) {
	var obj = JSON.parse(data);
	var elem = $("#prop-"+obj.pid+" .prop-act .btn-info");
	elem.removeClass("btn-info");
	elem.addClass("btn-danger");
	elem.attr("onclick", "asyncProcess('actions/client/props/unlike.php', "+obj.wid+", unlikeSuccessBlock)");
	elem.html(" <i class='fa fa-heart'></i> Liked ");
	showSnackbar('likedPropSnackbar');
}
function unlikeSuccessBlock(data , status) {
	var obj = JSON.parse(data);
	if (obj.pid != 0) {	
		var elem = $("#prop-"+obj.pid+" .prop-act .btn-danger");
		elem.removeClass("btn-danger");
		elem.addClass("btn-info");
		elem.attr("onclick", "asyncProcess('actions/client/props/like.php', "+obj.pid+", likeSuccessBlock)");
		elem.html(" <i class='fa fa-heart-o'></i> Like ");
		showSnackbar("unlikePropSnackbar");
	} else {
		// alert(obj.pid);
		showSnackbar("unlikeErrorSnackbar");
	}
}
function showLikersSuccess(data, status) {
	// alert(data);
	$("#likersModal .modal-body #likers-data").html(data);
	$("#likersModal").modal("show");
}/********** Client End ****************/
