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
			$("#budget").val("₹ " + ui.values[0]/100000 + " Lac - ₹ " + ui.values[1]/100000 + " Lac");
		}
	});
	$("#budget").val(
			"₹" + $("#budget-slider-range").slider("values", 0)/100000 + " Lac - ₹"
					+ $("#budget-slider-range").slider("values", 1)/100000 + " Lac");
});
function ageChecker() {
	if ($("#sell-property #t-type").val() == "Under Construction") {
		$("#sell-property #age").removeAttr("required");
		$("#sell-property #age").attr("disabled", "disabled");
	} else {
		$("#sell-property #age").removeAttr("disabled");
	}

	var frm = $('#postRequirementModal form');
    frm.submit(function (ev) {
    	$("#postRequirementModal").modal("hide");
        $.ajax({
            type: frm.attr('method'),
            url: frm.attr('action'),
            data: frm.serialize(),
            success: function (data) {
            	snackbar(data.message)
            }
        });
        ev.preventDefault();
    });

}
function likeSuccessBlock(data, status) {
	var obj = JSON.parse(data);
	var elem = $("#prop-BUY-" + obj.pid + " .prop-act .btn-outline-danger");
	elem.removeClass("btn-outline-danger");
	elem.addClass("btn-danger");
	elem.attr("onclick", "asyncProcess('Unlike-Prop', "
			+ obj.wid + ", unlikeSuccessBlock)");
	elem.html(" <i class='fa fa-fw fa-heart'></i> Liked ");
	snackbar(obj.message);
}
function unlikeSuccessBlock(data, status) {
	var obj = JSON.parse(data);
	if (obj.pid != 0) {
		var elem = $("#prop-BUY-" + obj.pid + " .prop-act .btn-danger, #prop-LIKED-" + obj.pid + " .prop-act .btn-danger");
		elem.removeClass("btn-danger");
		elem.addClass("btn-outline-danger");
		elem.attr("onclick", "asyncProcess('Like-Prop', "
				+ obj.pid + ", likeSuccessBlock)");
		elem.html(" <i class='far fa-fw fa-heart'></i> Like ");
	}
	snackbar(obj.message);
}
function showLikersSuccess(data, status) {
	// alert(data);
	$("#likersModal .modal-body #likers-data").html(data);
	$("#likersModal").modal("show");
}

/** ******** Client End *************** */

