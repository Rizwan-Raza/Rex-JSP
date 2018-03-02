/** ** Common start */
var valid_url = true;
$(function() {
	var current = location.pathname;
	$('nav .navbar-nav li a').each(function() {
		var $this = $(this);
		// if the current path is like this link, make it active
		if (current.indexOf($this.attr('href')) !== -1) {
			$("nav .navbar-nav li").first().removeClass("active");
			$this.addClass('active');
		}
	})

	$("input[autofocus]").first().focus();
	$(".modal").on('shown.bs.modal', function() {
		$("#" + this.id + " input[autofocus]").first().focus();
	});
	$(".card [data-toggle='collapse']").click(function() {
		var acc = $(this).find("i.fa");
		if (acc.hasClass("fa-plus")) {
			acc.removeClass("fa-plus");
			acc.addClass("fa-minus");
		} else {
			acc.removeClass("fa-minus");
			acc.addClass("fa-plus");
		}
	});

	$('[data-toggle="popover"]').popover();

	$('[data-toggle="tooltip"]').tooltip();

	$('.dropdown a.dropdown-test').hover(function(e) {
		$(this).next('ul').toggle();
		e.stopPropagation();
		e.preventDefault();
	});
	$(document).ajaxSend(function(e, xhr, options) {
		// alert(options.url);
		if (options.url.search("EmailChecker") == -1) {
			$("#waitModal").modal({
				backdrop : 'static',
				keyboard : false
			});
			valid_url = true;
		} else {
			valid_url = false;
		}
	});
	$(document).ajaxComplete(function() {
		closingWaitModal();
	});
	$(document).ajaxSuccess(function() {
		closingWaitModal();
	});
	$(document).ajaxStop(function() {
		closingWaitModal();
	});
	$(document).click(function(event) {
		if ($(".navbar-collapse").hasClass("show")) {
			$("button.navbar-toggler").click();
		}
	});
	// $(document).click(function() {
	// $("#waitModal").modal({
	// backdrop : 'static',
	// keyboard : false
	// });
	// $("#waitModal").modal("show");
	// var int = setInterval(animateSlide, 1000);
});
/*
 * function animateSlide() { var temp = $(".about
 * .col-md-4:nth-child(1)").html(); $(".about .col-md-4:nth-child(1)").remove();
 * $(".about .row").append('<div class="col-md-4">'+temp+'</div>'); }
 */
function snackbar(text) {
	if (valid_url) {
		$("#waitModal").modal("hide");
	}
	var snack = $("<div class='snackbar'></div>").text(text);
	$("body").append(snack);
	setTimeout(function() {
		snack.addClass("showTheSnack");
	}, 200);
	setTimeout(function() {
		snack.removeClass("showTheSnack");
		snack.remove();
	}, 3200);
}
function closingWaitModal() {
	if (valid_url) {
		$("#waitModal").modal({
			backdrop : 'false',
			keyboard : true
		});
		$("#waitModal").modal("hide");
	}
}
function errorHandler(XMLHttpRequest, textStatus, errorThrown) {
	/* If something goes wrong we're comming here */
	switch (XMLHttpRequest.status) {
	case 404:
		snackbar("No Resource Found, 4O4");
		break;
	case 500:
		$("#errorModalBS p#upper-text").html(
				"Code: " + XMLHttpRequest.status + "<br>Message: "
						+ XMLHttpRequest.responseText);
		$("#errorModalBS p#lower-text").html(
				"Occur Due to:" + XMLHttpRequest.statusText + "");
		$("#errorModalBS").modal("show");
		break;
	default:
		snackbar("Something went wrong, Try again");
	}
}
function asyncProcess(url, id, successBlock) {
	$.ajax({
		type : 'POST',
		url : url,
		dataType : 'html',
		async : true,
		data : {
			id : id
		},
		success : successBlock,
		error : errorHandler
	});
}
function navTo(id) {
	$('.nav-tabs a[href="#' + id + '"]').tab('show');
}
function showImageModal(caption, src) {
	$("#imageModalBS #image-holder").attr("src", src);
	// $("#image-holder").css("background-image", "url('"+src+"')");
	$("#imageModalBS #image-caption").text(caption);
	$("#imageModalBS").modal("show");
	// $("#imageModal").modal("show");
	// $("#imageModal").css("display", "block");
}
function mailAction(url, elem, type) {
	$.ajax({
		type : 'POST',
		url : url,
		dataType : 'html',
		async : true,
		data : {
			to : elem.to.value,
			from : elem.from.value,
			msg : elem.msg.value
		},
		success : function(data, status) {
			snackbar(JSON.parse(data).response);
		},
		error : function() {
			snackbar("Can't mail to the " + type + " right now, try again.");
		}
	});
	if (type == "Client") {
		$("#clientMailModal").modal('hide');
	} else {
		$("#showSellerModal").modal('hide');
	}
	return false;
}
function showSeller(fname, lname, email, gender, contact, dp, type) {
	$(".modal").modal("hide")
	$("#showSellerModal h4 span").text(type);
	var g = gender;
	$("#showSellerModal .modal-body .user-dp-lg").attr("style",
			"background-image: url('" + dp + "');");
	$("#showSellerModal .modal-body .user-dp-lg").attr("onclick",
			"showImageModal('" + fname + " " + lname + "', '" + dp + "')");
	$("#showSellerModal .modal-body #name").text(fname + " " + lname);
	$("#showSellerModal .modal-body #email").text(email);
	$("#showSellerModal .modal-body #gender").text(
			gender.substring(0, 1).toUpperCase()
					+ g.substring(1, gender.length));
	$("#showSellerModal .modal-body #contact").text(contact);
	$("#showSellerModal input#to").val(email);
	$("#showSellerModal").modal('show');
}
/** ******** Common end *************** */
