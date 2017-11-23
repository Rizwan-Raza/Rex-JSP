/************* Home start *******************/
$(document).ready(function() {
	$("#pwd").focusin(function () {
		pass = setInterval(passwordHelper, 200);
	});
 	$("#pwd").focusout(function () {
		clearTimeout(pass);
	});
	$("#repwd").focusin(function () {
		pass = setInterval(passwordChecker, 200);
	});
	$("#repwd").focusout(function () {
		clearTimeout(pass);
	});
	$("#signupForm #email").focusin(function () {
		pass = setInterval(emailChecker, 200);
	});
	$("#signupForm #email").focusout(function () {
		clearTimeout(pass);
	});

	$("#range-all").click(function(){
		$("#amount").toggle("slow");
		$("#amount-slider-range").toggle("slow");
	});
});
function viewPropSuccess(data, status) {
	// alert(data);
	$("#viewPropModal .modal-dialog").html(data);
	$("#viewPropModal").modal("show");
}
$(function() {
	$( "#amount-slider-range" ).slider({
		range: true,
		min: 100000,
		max: 100000000,
		step: 100000,
		values: [ 5000000, 10000000 ],
		slide: function( event, ui ) {
			$( "#amount" ).val("Rs. " + ui.values[ 0 ] + " - Rs. " + ui.values[ 1 ]);
		}
	});
	$( "#amount" ).val("Rs. " + $( "#amount-slider-range" ).slider( "values", 0 ) +
	" - Rs. " + $( "#amount-slider-range" ).slider( "values", 1 ));
	$( "#area-slider-range" ).slider({
		range: true,
		min: 100,
		max: 10000,
		step: 100,
		values: [ 1500, 3000 ],
		slide: function( event, ui ) {
			$( "#c-area" ).val( ui.values[ 0 ] + " Sq-Ft. - " + ui.values[ 1 ] + " Sq-Ft.");
		}
	});
	$( "#c-area" ).val( $( "#area-slider-range" ).slider( "values", 0 ) +
	" Sq-Ft. - " + $( "#area-slider-range" ).slider( "values", 1 ) + " Sq-Ft.");
	$( "#budget-slider-range" ).slider({
		range: true,
		min: 100000,
		max: 100000000,
		step: 100000,
		values: [ 5000000, 10000000 ],
		slide: function( event, ui ) {
			$( "#budget" ).val( "Rs. " + ui.values[ 0 ] + " - Rs. " + ui.values[ 1 ] );
		}
	});
	$( "#budget" ).val( "Rs. " + $( "#budget-slider-range" ).slider( "values", 0 ) +
	" - Rs. " + $( "#budget-slider-range" ).slider( "values", 1 ) );
	$( "#area-slider-range-e" ).slider({
		range: true,
		min: 100,
		max: 10000,
		step: 100,
		values: [ 1500, 3000 ],
		slide: function( event, ui ) {
			$( "#e_c_area" ).val( ui.values[ 0 ] + " Sq-Ft. - " + ui.values[ 1 ] + " Sq-Ft.");
		}
	});
	$( "#e_c_area" ).val( $( "#area-slider-range-e" ).slider( "values", 0 ) +
	" Sq-Ft. - " + $( "#area-slider-range-e" ).slider( "values", 1 ) + " Sq-Ft.");
	$( "#budget-slider-range-e" ).slider({
		range: true,
		min: 100000,
		max: 100000000,
		step: 100000,
		values: [ 5000000, 10000000 ],
		slide: function( event, ui ) {
			$( "#e_budget" ).val( "Rs. " + ui.values[ 0 ] + " - Rs. " + ui.values[ 1 ] );
		}
	});
	$( "#e_budget" ).val( "Rs. " + $( "#budget-slider-range-e" ).slider( "values", 0 ) +
	" - Rs. " + $( "#budget-slider-range-e" ).slider( "values", 1 ) );
});
function passwordChecker() {
	if ($("#repwd").val() > 0) {
		if ($("#pwd").val() == $("#repwd").val()) {
			$("#repwd").removeClass('is-invalid');
			$("#repwd").addClass('is-valid');
			$("#repwd-holder .invalid-feedback").hide();
			$("#repwd-holder .valid-feedback").show();
		} else {
			$("#repwd").addClass('is-invalid');
			$("#repwd").removeClass('is-valid');
			$("#repwd-holder .valid-feedback").hide();
			$("#repwd-holder .invalid-feedback").show();
		}
	} else {
		$("#repwd-holder").removeClass('is-invaid');
		$("#repwd-holder").removeClass('is-valid');
		$("#repwd-holder .valid-feedback").hide();
		$("#repwd-holder .invalid-feedback").hide();
	}
}
function emailChecker() {
	var email = $("#signupForm #email").val()
	// alert(email.substring(email.indexOf("@"), email.length-2).search(".") != -1);
	if (email.length > 8 && email.search("@") != -1) {
		$.ajax({
			type: 'POST',
			url: "emailChecker",
			dataType: 'html',
			async: true,
			data: {
				email: email
			},
			success: function(data, status) {
				$("#email-holder").removeClass('has-error');
				$("#email-holder .form-control-feedback").removeClass('fa-remove');
				$("#email-holder .form-control-feedback").addClass('fa-check');
				$("#email-holder").addClass('has-success');
				$("#email-holder span.feedback-label").text("(Available)");
			},
			error: function(data, status) {
				$("#email-holder").addClass('has-error');
				$("#email-holder .form-control-feedback").removeClass('fa-check');
				$("#email-holder .form-control-feedback").addClass('fa-remove');
				$("#email-holder").removeClass('has-success');
				$("#email-holder span.feedback-label").text("(Already Exist)");				
			}
		});
	} else {
		$("#email-holder").removeClass('has-error');
		$("#email-holder").removeClass('has-success');
		$("#email-holder .form-control-feedback").removeClass('fa-check');
		$("#email-holder .form-control-feedback").removeClass('fa-remove');
		$("#email-holder span.feedback-label").text("");
	}
}
function passwordHelper() {
	var l = $("#pwd").val().length;
	if (l > 0) {
		if (l < 8 || l > 20) {
			$("#pwd").addClass('is-invalid');
			$("#pwd").removeClass('is-valid');
			$("#pwd-holder .valid-feedback").hide();
			$("#pwd-holder .invalid-feedback").show();
		} else if (l > 7 && l < 21) {
			$("#pwd").addClass('is-valid');
			$("#pwd").removeClass('is-invalid');
			$("#pwd-holder .invalid-feedback").hide();
			$("#pwd-holder .valid-feedback").show();
		}
	} else {
		$("#pwd").removeClass('is-invalid');
		$("#pwd").removeClass('is-valid');
		$("#pwd-holder .valid-feedback").hide();
		$("#pwd-holder .invalid-feedback").hide();
	}
}
function signup(elem) {
	elem.classList.add("was-validated");
	if (elem.psw.value == elem.repsw.value) {
		$.ajax({
			type: 'POST',
			url: "SignUp",
			dataType: 'html',
			async: true,
			data: {
				fname: elem.fname.value,
				lname: elem.lname.value,
				email: elem.email.value,
				psw: elem.psw.value,
				repsw: elem.repsw.value,
				gender: elem.gender.value,
				cont: elem.cont.value,
				street: elem.street.value,
				town: elem.town.value,
				city: elem.city.value,
				state: elem.state.value,
				submit_btn: elem.submit_btn.value
			},
			success: function(data, status) {
				var obj = JSON.parse(data);
				if(obj.response == "OK") {
					$("#signupModal").modal("hide");
					$("#signupSuccessModal .modal-info span").text(obj.message);
					$("#signupSuccessModal").modal("show");
					$("#errorHolder").css("display","none");
					$("#pwdHolder").removeClass("input-group");
					$("#repwdHolder").removeClass("input-group");
					$("#pwdHolder #pwd").nextAll().remove();
					$("#repwdHolder #repwd").nextAll().remove();
					elem.reset();
					return true;
				} else {
					return false;
				}
			},
			error: function(data, status) {
				alert(data.response+ " "+data.message);
			}
//			error: errorHandler
		});
		return false;
	} else {
		// alert("Now Here");
		$("#repwd").focus();
		$("#repwd").addClass("is-invalid");
		$("#repwd-holder invalid-feedback").show();
		return false;
	}
}
/****** Home end ***********/
