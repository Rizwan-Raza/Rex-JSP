/** *********** Home start ****************** */

var emailVerified = true;
var oldData;

$(document).ready(function() {
	var pass;
	$("#pwd").focusin(function() {
		pass = setInterval(passwordHelper, 200);
	});
	$("#pwd").focusout(function() {
		clearTimeout(pass);
	});
	$("#repwd").focusin(function() {
		pass = setInterval(passwordChecker, 200);
	});
	$("#repwd").focusout(function() {
		clearTimeout(pass);
	});
	$("#signupForm #email").keydown(function() {
		emailAvailable();
	});

	$("#adminLoginModal #usrname").keydown(function() {
		emailChecker("#adminLoginModal #usrname");
	});

	$("#clientLoginModal #usrname").keydown(function() {
		emailChecker("#clientLoginModal #usrname");
	});

	$("#range-all").click(function() {
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
	$("#amount-slider-range").slider({
		range : true,
		min : 100000,
		max : 100000000,
		step : 100000,
		values : [ 5000000, 10000000 ],
		slide : function(event, ui) {
			$("#amount").val("Rs. " + ui.values[0] + " - Rs. " + ui.values[1]);
		}
	});
	$("#amount").val(
			"Rs. " + $("#amount-slider-range").slider("values", 0) + " - Rs. "
					+ $("#amount-slider-range").slider("values", 1));
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
			$("#budget").val("Rs. " + ui.values[0] + " - Rs. " + ui.values[1]);
		}
	});
	$("#budget").val(
			"Rs. " + $("#budget-slider-range").slider("values", 0) + " - Rs. "
					+ $("#budget-slider-range").slider("values", 1));
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
function passwordChecker() {
	var l = $("#repwd").val();
	if (l == oldData) {
		return;
	} else {
		oldData = l;
	}

	if (l.length > 0) {
		if ($("#pwd").val() == l) {
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
function emailAvailable() {
	var email = $("#signupForm #email").val()
	// alert(email.substring(email.indexOf("@"), email.length-2).search(".") !=
	// -1);
	if (email.length > 8 && email.search("@") != -1) {
		$.ajax({
			type : 'POST',
			url : "EmailChecker",
			dataType : 'html',
			async : true,
			data : {
				email : email
			},
			success : function(data, status) {
				obj = JSON.parse(data);
				if (obj.response == "KO") {
					$("#email").removeClass('is-invalid');
					$("#email").addClass('is-valid');
					$("#email .valid-feedback").show();
					$("#email .invalid-feedback").hide();
					emailVerified = true;
				} else {
					$("#email").addClass('is-invalid');
					$("#email").removeClass('is-valid');
					$("#email .valid-feedback").hide();
					$("#email .invalid-feedback").show();
					emailVerified = false;
				}
			},
			error : function(data, status) {
				$("#email").addClass('is-invalid');
				$("#email").removeClass('is-valid');
				$("#email .valid-feedback").hide();
				$("#email .invalid-feedback").show();
				$("#email .invalid-feedback").text(
						"Can't Verify Email right now.");
				emailVerified = true;
			}
		});
	} else {
		$("#email").removeClass('is-invalid');
		$("#email").removeClass('is-valid');
		$("#email .valid-feedback").hide();
		$("#email .invalid-feedback").hide();
	}
}
function emailChecker(id) {
	var email = $(id).val()
	// alert(email.substring(email.indexOf("@"), email.length-2).search(".") !=
	// -1);
	if (email.length > 8 && email.search("@") != -1) {
		$.ajax({
			type : 'POST',
			url : "EmailChecker",
			dataType : 'html',
			async : true,
			data : {
				email : email
			},
			success : function(data, status) {
				obj = JSON.parse(data);
				if (obj.response == "OK") {
					$(id).removeClass('is-invalid');
					$(id).addClass('is-valid');
					$(id + " .valid-feedback").show();
					$(id + " .invalid-feedback").hide();
				} else {
					$(id).addClass('is-invalid');
					$(id).removeClass('is-valid');
					$(id + " .valid-feedback").hide();
					$(id + " .invalid-feedback").show();
				}
			},
			error : function(data, status) {
				$(id).addClass('is-invalid');
				$(id).removeClass('is-valid');
				$(id + " .valid-feedback").hide();
				$(id + " .invalid-feedback").show();
				$(id + " .invalid-feedback").text(
						"Can't Verify Email right now.");
			}
		});
	} else {
		$(id).removeClass('is-invalid');
		$(id).removeClass('is-valid');
		$(id + " .valid-feedback").hide();
		$(id + " .invalid-feedback").hide();
	}
}
function passwordHelper() {
	var l = $("#pwd").val().length;
	if (l == oldData) {
		return;
	} else {
		oldData = l;
	}
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
	if (!emailVerified) {
		$("#email").focus();
		$("#email").addClass("is-invalid");
		$("#email .invalid-feedback").show();
		return false;
	}
	if (elem.psw.value != elem.repsw.value) {
		$("#repwd").focus();
		$("#repwd").addClass("is-invalid");
		$("#repwd-holder invalid-feedback").show();
		return false;
	}
	$.ajax({
		type : 'POST',
		url : "SignUp",
		dataType : 'html',
		async : true,
		data : {
			fname : elem.fname.value,
			lname : elem.lname.value,
			email : elem.email.value,
			psw : elem.psw.value,
			repsw : elem.repsw.value,
			gender : elem.gender.value,
			cont : elem.cont.value,
			street : elem.street.value,
			town : elem.town.value,
			city : elem.city.value,
			state : elem.state.value,
			submit_btn : elem.submit_btn.value
		},
		success : function(data, status) {
			var obj = JSON.parse(data);
			if (obj.response == "OK") {
				$("#signupModal").modal("hide");
				$("#signupSuccessModal .modal-info b:nth-child(1)").text(
						obj.name);
				$("#signupSuccessModal .modal-info b:nth-child(3)").text(
						obj.email);
				$("#signupSuccessModal").modal("show");
				$("#errorHolder").css("display", "none");
				$("#pwdHolder").removeClass("input-group");
				$("#repwdHolder").removeClass("input-group");
				$("#pwdHolder #pwd").nextAll().remove();
				$("#repwdHolder #repwd").nextAll().remove();
				elem.reset();
				elem.removeClass("was-validated");
				return true;
			} else {
				alert(data);
				snackbar(obj.message);
				return false;
			}
		},
		error : errorHandler
	});
	return false;
}
/** **** Home end ********** */
