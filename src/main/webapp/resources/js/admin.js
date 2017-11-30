/** **** Admin Start ********** */
function activate(name, user_id) {
	$("#activationModal .modal-info > span").text("A");
	$("#activationModal .modal-info > b > span").text(name);
	$("#activationModal .modal-footer > .btn-success").attr(
			"onclick",
			"asyncProcess('ClientActivation?activate=true', " + user_id
					+ ", activateSuccess)");
	$("#activationModal").modal('show');
}
function activateSuccess(data, status) {
	var obj = JSON.parse(data);
	$("#activationModal").modal("hide");
	snackbar(obj.message);
	$("tr#for-" + obj.user_id + " .act").html(
			"<i class='fa fa-check text-success'></i>");
	var elem = $("tr#for-" + obj.user_id + " .act-link");
	var action = elem.attr("onclick");
	elem.attr("onclick", "de" + action);
	elem.html("<i class='fa fa-remove'></i> Deactivate");
}
function deactivate(name, user_id) {
	$("#activationModal .modal-info > span").text("Dea");
	$("#activationModal .modal-info > b > span").text(name);
	$("#activationModal .modal-footer > .btn-success").attr(
			"onclick",
			"asyncProcess('ClientActivation?activate=false', " + user_id
					+ ", deactivateSuccess)");
	$("#activationModal").modal('show');
}
function deactivateSuccess(data, status) {
	var obj = JSON.parse(data);
	$("#activationModal").modal("hide");
	snackbar(obj.message);
	$("tr#for-" + obj.user_id + " .act").html(
			"<i class='fa fa-remove text-danger'></i>");
	var elem = $("tr#for-" + obj.user_id + " .act-link");
	var action = elem.attr("onclick");
	elem.attr("onclick", action.substring(2, action.length));
	elem.html("<i class='fa fa-check'></i> Activate");
}
function kickout(name, user_id) {
	$("#kickoutModal .modal-info > b > span").text(name);
	$("#kickoutModal .modal-footer > .btn-danger").attr("onclick",
			"asyncProcess('ClientDelete'," + user_id + ", kickoutSuccess)");
	$("#kickoutModal").modal('show');
}
function kickoutSuccess(data, status) {
	var obj = JSON.parse(data);
	$("#clients b#clients-num").text((+$("#clients b#clients-num").text()) - 1);
	$("#kickoutModal").modal("hide");
	$("tr#for-" + obj.user_id + "").hide("slow", function() {
		$("tr#for-" + obj.user_id + "").remove();
	});
	snackbar(obj.message);
}
function makeAdmin(fname, cid) {
	$("#makeAdminModal .modal-info > b > span").text(fname);
	$("#makeAdminModal .modal-footer .btn-success").attr(
			"onclick",
			"asyncProcess('actions/admin/clients/promote.php'," + cid
					+ ", makeAdminSuccess)");
	$("#makeAdminModal").modal("show");
}
function makeAdminSuccess(data, status) {
	var obj = JSON.parse(data);
	$("#clients #adminMBtn-" + obj.cid).remove();
	snackbar(obj.name + " is now a admin, Login with old data");
}
function edit(fname, lname, email, gender, cont, dp, street, town, city, state,
		cid, add_id) {
	$("#clientEditModal .modal-body .user-dp-lg").attr("style",
			"background-image: url('" + dp + "');");
	$("#clientEditModal .modal-body #fname").val(fname);
	$("#clientEditModal .modal-body #lname").val(lname);
	$("#clientEditModal .modal-body #email").val(email);
	if (gender == "Male") {
		$("#clientEditModal .modal-body #male").attr("checked", "checked");
	} else if (gender == "Female") {
		$("#clientEditModal .modal-body #female").attr("checked", "checked");
	}
	$("#clientEditModal .modal-body #cont").val(cont);
	$("#clientEditModal .modal-body #editForm").attr("action",
			"actions/admin/clients/update.php?cid=" + cid);
	// $("#clientEditModal .modal-body #editForm").attr("onsubmit",
	// "editClientAction('actions/admin/client-update.php', "+cid+")");
	$("#clientEditModal .modal-body #change-address").attr(
			"onclick",
			"changeAddress('" + street + "', '" + town + "', '" + city + "', '"
					+ state + "', " + add_id + ")");
	$("#clientEditModal .modal-body #change-password").attr("onclick",
			"changePassword(" + cid + ")");
	$("#clientEditModal .modal-body .change-picture").attr(
			"onclick",
			"changePicture('" + dp + "', '" + fname + " " + lname + "', " + cid
					+ ")");
	$("#clientEditModal").modal('show');
}
function editClientAction(url, cid) {
	// var elem = document.getElementById("editForm");
	// $.ajax({
	// type: 'POST',
	// url: url,
	// dataType: 'html',
	// async: true,
	// data: {
	// cid: cid,
	// fname: elem.fname.value,
	// lname: elem.lname.value,
	// email: elem.email.value,
	// gender: elem.gender.value,
	// cont: elem.cont.value,
	// submit_btn: elem.submit_btn.value
	// },
	// success: editClientSuccess,
	// error: errorHandler
	// });
}
function editClientSuccess(data, status) {
	// $("#clientEditModal").modal('hide');
	// var elem = data.split("-*-");
	// $('#for-'+elem[0]+' .c-fname').html(elem[1]);
	// $('#for-'+elem[0]+' .c-lname').html(elem[2]);
	// $('#for-'+elem[0]+' .c-email').html("<a
	// onclick=\"clientMail('"+elem[3]+"')\">"+elem[3]+"</a>");
	// $('#for-'+elem[0]+' .c-gender').html(elem[4]);
	// $('#for-'+elem[0]+' .c-cont').html(elem[5]);
	// var action = $('#for-'+elem[0]+' .btn-primary').attr("onclick");
	// var vars = action.split("'");
	// vars[1] = elem[1];
	// vars[3] = elem[2];
	// vars[5] = elem[3];
	// vars[7] = elem[4];
	// vars[9] = elem[5];
	// var newAction = vars[0];
	// for (var i = 1; i < vars.length; i++) {
	// newAction += "'"+vars[i];
	// }
	// $('#for-'+elem[0]+' .btn-primary').attr("onclick", newAction);
	// showSnackbar("clientEditedSnackbar");
}
function address(street, town, city, state, add_id) {
	// alert(street);
	$("#clientAddressModal table tbody").html(
			"<tr><td>" + street + "</td><td>" + town + "</td><td>" + city
					+ "</td><td>" + state + "</td></tr>");
	$("#clientAddressModal .modal-footer #change-address-2").attr(
			"onclick",
			"changeAddress('" + street + "', '" + town + "', '" + city + "', '"
					+ state + "', " + add_id + ", 'client')");
	$("#clientAddressModal").modal('show');
}
function changePassword(cid) {
	// $("#clientEditModal").modal('hide');
	$("#clientChangePasswordModal form").attr("action",
			"actions/admin/clients/change-password.php?cid=" + cid);
	$("#clientChangePasswordModal").modal('show');
}
function changePicture(dp, name, cid) {
	// $("#clientEditModal").modal('hide');
	$("#clientChangePictureModal .user-dp-xl").attr("style",
			"background-image: url('" + dp + "');");
	$("#clientChangePictureModal .user-dp-xl").attr("onclick",
			"showImageModal('" + name + "', '" + dp + "')");
	$("#clientChangePictureModal form").attr("action",
			"actions/admin/clients/profile.php?old_dp=" + dp + "&cid=" + cid);
	$("#clientChangePictureModal").modal('show');
}
function clientMail(email) {
	$("#clientMailModal input#to").val(email);
	$("#clientMailModal").modal('show');
}
function mailAction(url, elem, type) {
	$.ajax({
		type : 'POST',
		url : url,
		dataType : 'html',
		async : true,
		data : {
			to : elem.to.value,
			msg : elem.msg.value
		},
		success : mailClientSuccess,
		error : mailClientError
	});
	if (type == "client") {
		$("#clientMailModal").modal('hide');
	} else {
		$("#showSellerModal").modal('hide');
	}
	return false;
}
function mailClientSuccess(data, status) {
	snackbar("User has been mailed Successfully!");
}
function mailClientError(data, status) {
	snackbar("Can't mail to the user right now, try again.");
}
function showPropAddress(street, town, city, state, add_id) {
	$("#propAddressModal table tbody").html(
			"<tr><td>" + street + "</td><td>" + town + "</td><td>" + city
					+ "</td><td>" + state + "</td></tr>");
	$("#propAddressModal .modal-footer #change-prop-address").attr(
			"onclick",
			"changeAddress('" + street + "', '" + town + "', '" + city + "', '"
					+ state + "', " + add_id + ", 'prop')");
	$("#propAddressModal").modal('show');
}
function showPropFeatures(bhk, bath, age, furn, area, l_area, hosp, school,
		rail, pid) {
	var furnished = "<i class='fa fa-";
	if (furn == 1) {
		furnished += "check text-success";
	} else {
		furnished += "remove text-danger";
	}
	furnished += "'></i>"
	$("#propFeaturesModal table tbody").html(
			"<tr><td>" + bhk + "</td><td>" + bath + "</td><td>" + age
					+ " Years</td><td class='text-center'>" + furnished
					+ "</td><td>" + hosp + " KMS.</td><td>" + school
					+ " KMS</td><td>" + rail + " KMS</td><td>" + area
					+ " Sq-Ft.</td><td>" + l_area + " Sq-Ft.</td></tr>");
	$("#propFeaturesModal #change-prop-features").attr(
			"onclick",
			"editPropFeatures(" + bhk + "," + bath + "," + age + "," + furn
					+ "," + hosp + "," + school + "," + rail + "," + area + ","
					+ l_area + ", " + pid + ")");
	$("#propFeaturesModal").modal('show');
}
function showPropInfo(amens, units, floor, t_floors, desc, tnc, time, edit, pid) {
	var empty_a = false, empty_f = false, empty_d = false, empty_t = false;
	var num_of_a = amens.length;
	var amenities = "";
	for (var i = 0; i < num_of_a; i++) {
		amenities += "<code style='margin: 0px 5px;'>" + amens[i] + "</code>";
	}
	if (amens == "NULL") {
		amenities = "<i class='fa fa-minus text-info'></i>";
		empty_a = true;
	}
	if (floor == "-*-") {
		var floorno = "<i class='fa fa-minus text-info'></i>";
		floor = "'-*-'";
		empty_f = true;
	} else {
		var floorno = floor;
	}
	if (desc == "NULL") {
		var descr = "<i class='fa fa-minus text-info'></i>";
		empty_d = true;
	} else {
		var descr = desc;
	}
	if (tnc == "NULL") {
		var t_n_c = "<i class='fa fa-minus text-info'></i>";
		empty_t = true;
	} else {
		var t_n_c = tnc;
	}
	// $("#propInfoModal table
	// #amen").html("<tr><td>"+amenities+"</td><td>"+units+"</td><td>"+floor+"</td><td>"+t_floors+"</td><td>"+desc+"</td><td>"+tnc+"</td><td>"+time+"</td><td>"+edit+"</td></tr>");
	$("#propInfoModal table #amen").html(amenities);
	$("#propInfoModal table #units").html(units);
	$("#propInfoModal table #floor").html(floorno);
	$("#propInfoModal table #t-floors").html(t_floors);
	$("#propInfoModal table #b-desc").html(descr);
	$("#propInfoModal table #tnc").html(t_n_c);
	$("#propInfoModal table #p-on").html(time);
	$("#propInfoModal table #e-on").html(edit);
	if (empty_a) {
		$("#propInfoModal table tbody tr td#amen").addClass("text-center");
	} else {
		$("#propInfoModal table tbody tr td#amen").removeClass("text-center");
	}
	if (empty_f) {
		$("#propInfoModal table tbody tr td#floor").addClass("text-center");
	} else {
		$("#propInfoModal table tbody tr td#floor").removeClass("text-center");
	}
	if (empty_d) {
		$("#propInfoModal table tbody tr td#b-desc").addClass("text-center");
	} else {
		$("#propInfoModal table tbody tr td#b-desc").removeClass("text-center");
	}
	if (empty_t) {
		$("#propInfoModal table tbody tr td#tnc").addClass("text-center");
	} else {
		$("#propInfoModal table tbody tr td#tnc").removeClass("text-center");
	}
	$("#propInfoModal #change-prop-info").attr(
			"onclick",
			"editPropInfo('" + amens + "'," + units + "," + floor + ","
					+ t_floors + ",'" + desc + "','" + tnc + "'," + pid + ")");
	$("#propInfoModal").modal('show');
}
/** **** Admin end ********** */
