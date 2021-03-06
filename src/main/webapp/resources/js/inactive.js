/** *********** Home start ****************** */

var emailVerified = true;
var oldData = "";

$(function() {
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
	$("#signupForm #email").keyup(function() {
		emailAvailable();
	});

	$("#adminLoginModal input[name='usrname']").keyup(function() {
		emailChecker("#adminLoginModal input[name='usrname']");
	});

	$("#clientLoginModal input[name='usrname']").keyup(function() {
		emailChecker("#clientLoginModal input[name='usrname']");
	});

	$("#range-all").click(function() {
		$("#amount").toggle("slow");
		$("#amount-slider-range").toggle("slow");
	});
	$("#amount-slider-range").slider({
		range : true,
		min : 100000,
		max : 100000000,
		step : 100000,
		values : [ 5000000, 10000000 ],
		slide : function(event, ui) {
			$("#amount").val("₹ " + ui.values[0]/100000 + " Lac - ₹ " + ui.values[1]/100000+" Lac");
		}
	});
	$("#amount").val(
			"₹ " + $("#amount-slider-range").slider("values", 0)/100000 + " Lac - ₹ "
					+ $("#amount-slider-range").slider("values", 1)/100000 +" Lac");
	$("#pwd-holder .control-label i").click(function() {
		if ($("#pwd-holder .control-label i").hasClass("fa-eye-slash")) {
			$("#pwd-holder input").attr("type", "text");
			$("#pwd-holder .control-label i").removeClass("fa-eye-slash");
			$("#pwd-holder .control-label i").addClass("fa-eye");
		} else {
			$("#pwd-holder input").attr("type", "password");
			$("#pwd-holder .control-label i").removeClass("fa-eye");
			$("#pwd-holder .control-label i").addClass("fa-eye-slash");
		}
	});

	$("#repwd-holder .control-label i").click(function() {
		if ($("#repwd-holder .control-label i").hasClass("fa-eye-slash")) {
			$("#repwd-holder input").attr("type", "text");
			$("#repwd-holder .control-label i").removeClass("fa-eye-slash");
			$("#repwd-holder .control-label i").addClass("fa-eye");
		} else {
			$("#repwd-holder input").attr("type", "password");
			$("#repwd-holder .control-label i").removeClass("fa-eye");
			$("#repwd-holder .control-label i").addClass("fa-eye-slash");
		}
	});
});
function viewPropSuccess(data, status) {
	// alert(data);
	$("#viewPropModal .modal-dialog").html(data);
	$("#viewPropModal").modal("show");
}
function passwordChecker() {
	var l = $("#repwd").val();
	if (l == oldData) {
		return;
	} else {
		oldData = l;
	}

	if (l.length > 4) {
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
	var email = $("#signupForm #email").val();

	if (oldData == email || email.length == 0)
		return;
	else
		oldData = email;
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
				var obj = JSON.parse(data);
				if (obj.response == "KO") {
					if (obj.message != "KO") {
						$(id + " ~ .invalid-feedback").text(obj.message);
						$("#email").addClass('is-invalid');
						$("#email").removeClass('is-valid');
						$("#email ~ .invalid-feedback").show();
						$("#email ~ .valid-feedback").hide();
					} else {
						$("#email").removeClass('is-invalid');
						$("#email").addClass('is-valid');
						$("#email ~ .valid-feedback").show();
						$("#email ~ .invalid-feedback").hide();
					}
					emailVerified = true;
				} else {
					$("#email").addClass('is-invalid');
					$("#email").removeClass('is-valid');
					$("#email ~ .valid-feedback").hide();
					$("#email ~ .invalid-feedback").show();
					emailVerified = false;
				}
			},
			error : function(data, status) {
				$("#email").addClass('is-invalid');
				$("#email").removeClass('is-valid');
				$("#email ~ .valid-feedback").hide();
				$("#email ~ .invalid-feedback").show();
				$("#email ~ .invalid-feedback").text(
						"Can't Verify Email right now.");
				emailVerified = true;
			}
		});
	} else {
		$("#email").removeClass('is-invalid');
		$("#email").removeClass('is-valid');
		$("#email ~ .valid-feedback").hide();
		$("#email ~ .invalid-feedback").hide();
	}
}
function emailChecker(id) {
	var email = $(id).val();
	if (oldData == email || email.length == 0)
		return;
	else
		oldData = email;
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
				var obj = JSON.parse(data);
				if (obj.response == "OK") {
					$(id).removeClass('is-invalid');
					$(id).addClass('is-valid');
					$(id + " ~ .valid-feedback").show();
					$(id + " ~ .invalid-feedback").hide();
				} else {
					if (obj.message != "KO") {
						$(id + " ~ .invalid-feedback").text(obj.message);
					}
					$(id).addClass('is-invalid');
					$(id).removeClass('is-valid');
					$(id + " ~ .valid-feedback").hide();
					$(id + " ~ .invalid-feedback").show();
				}
			},
			error : function(data, status) {
				$(id).addClass('is-invalid');
				$(id).removeClass('is-valid');
				$(id + " ~ .valid-feedback").hide();
				$(id + " ~ .invalid-feedback").show();
				$(id + " ~ .invalid-feedback").text(
						"Can't Verify Email right now.");
			}
		});
	} else {
		$(id).removeClass('is-invalid');
		$(id).removeClass('is-valid');
		$(id + " ~ .valid-feedback").hide();
		$(id + " ~ .invalid-feedback").hide();
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
			if (obj.response == "KK") {
				$("#signupSuccessModal .modal-info span:nth-child(1)").text(
						"Can't Send Mail to ");
				$("#signupSuccessModal .modal-info span:nth-child(2)")
						.text(",");
			}
			if (obj.response == "OK" || obj.response == "KK") {
				$("#signupModal").modal("hide");
				$("#signupSuccessModal .modal-info b:nth-child(1)").text(
						obj.name);
				$("#signupSuccessModal .modal-info b.email").text(obj.email);
				$("#signupSuccessModal").modal("show");
				$("#errorHolder").css("display", "none");
				$("#pwdHolder").removeClass("input-group");
				$("#repwdHolder").removeClass("input-group");
				$("#pwdHolder #pwd").nextAll().remove();
				$("#repwdHolder #repwd").nextAll().remove();
				elem.reset();
				$(elem).removeClass("was-validated");
				return true;
			} else {
				// alert(data);
				snackbar(obj.message);
				return false;
			}
		},
		error : errorHandler
	});
	return false;
}
/** **** Home end ********** */
