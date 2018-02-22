/** **** Client start ********** */
$(function() {
	ageChecker();
	$("#area-slider-range").slider(
			{
				range : true,
				min : 100,
				max : 10000,
				step : 100,
				values : [ 1500, 3000 ],
				slide : function(event, ui) {
					$("#c-area").val(
							ui.values[0] + " Sq-Ft. - " + ui.values[1]
									+ " Sq-Ft.");
				}
			});
	$("#c-area").val(
			$("#area-slider-range").slider("values", 0) + " Sq-Ft. - "
					+ $("#area-slider-range").slider("values", 1) + " Sq-Ft.");
	$("#budget-slider-range").slider({
		range : true,
		min : 100000,
		max : 100000000,
		step : 100000,
		values : [ 5000000, 10000000 ],
		slide : function(event, ui) {
			$("#budget").val("Rs. " + ui.values[0]/100000 + " Lac - Rs. " + ui.values[1]/100000 + " Lac");
		}
	});
	$("#budget").val(
			"Rs. " + $("#budget-slider-range").slider("values", 0)/100000 + " Lac - Rs. "
					+ $("#budget-slider-range").slider("values", 1)/100000 + " Lac");
	$("#area-slider-range-e").slider(
			{
				range : true,
				min : 100,
				max : 10000,
				step : 100,
				values : [ 1500, 3000 ],
				slide : function(event, ui) {
					$("#e_c_area").val(
							ui.values[0] + " Sq-Ft. - " + ui.values[1]
									+ " Sq-Ft.");
				}
			});
	$("#e_c_area")
			.val(
					$("#area-slider-range-e").slider("values", 0)
							+ " Sq-Ft. - "
							+ $("#area-slider-range-e").slider("values", 1)
							+ " Sq-Ft.");
	$("#budget-slider-range-e").slider(
			{
				range : true,
				min : 100000,
				max : 100000000,
				step : 100000,
				values : [ 5000000, 10000000 ],
				slide : function(event, ui) {
					$("#e_budget").val(
							"Rs. " + ui.values[0] + " - Rs. " + ui.values[1]);
				}
			});
	$("#e_budget").val(
			"Rs. " + $("#budget-slider-range-e").slider("values", 0)
					+ " - Rs. "
					+ $("#budget-slider-range-e").slider("values", 1));
});
function ageChecker() {
	if ($("#sell-property #t-type").val() == "Under Construction") {
		$("#sell-property #age").removeAttr("required");
		$("#sell-property #age").attr("disabled", "disabled");
	} else {
		$("#sell-property #age").removeAttr("disabled");
	}
}
function likeSuccessBlock(data, status) {
	var obj = JSON.parse(data);
	var elem = $("#prop-" + obj.pid + " .prop-act .btn-info");
	elem.removeClass("btn-info");
	elem.addClass("btn-danger");
	elem.attr("onclick", "asyncProcess('actions/client/props/unlike.php', "
			+ obj.wid + ", unlikeSuccessBlock)");
	elem.html(" <i class='fa fa-heart'></i> Liked ");
	snackbar(data);
}
function unlikeSuccessBlock(data, status) {
	var obj = JSON.parse(data);
	if (obj.pid != 0) {
		var elem = $("#prop-" + obj.pid + " .prop-act .btn-danger");
		elem.removeClass("btn-danger");
		elem.addClass("btn-info");
		elem.attr("onclick", "asyncProcess('actions/client/props/like.php', "
				+ obj.pid + ", likeSuccessBlock)");
		elem.html(" <i class='fa fa-heart-o'></i> Like ");
		showSnackbar("unlikePropSnackbar");
	} else {
		// alert(obj.pid);
		snackbar(data);
	}
}
function showLikersSuccess(data, status) {
	// alert(data);
	$("#likersModal .modal-body #likers-data").html(data);
	$("#likersModal").modal("show");
}/** ******** Client End *************** */
