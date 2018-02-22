/** **** Admin Start ********** */
$(function() {
	if ($(window).width() < 768) {
		$(".admin-section table").addClass("table-sm");
	} else {
		$(".admin-section table").removeClass("table-sm");
	}
});
function activate(name, user_id) {
	$("#activationModal .modal-info > span").text("A");
	$("#activationModal .modal-info > b > span").text(name);
	$("#activationModal .modal-footer > .btn-success").attr(
			"onclick",
			"asyncProcess('Admin-Client-Activation?activate=true', " + user_id
					+ ", activateSuccess)");
	$("#activationModal").modal('show');
}
function activateSuccess(data, status) {
	var obj = JSON.parse(data);
	$("#activationModal").modal("hide");
	if (obj.response == "OK") {
		$("tr#for-" + obj.user_id + " .act").html(
				"<i class='fa fa-td fa-check text-success'></i>");
		var elem = $("tr#for-" + obj.user_id + " .act-link");
		var action = elem.attr("href");
		elem.attr("href", "javascript:de" + action.replace("javascript:", ""));
		elem.html("<i class='fa fa-fw fa-remove'></i> Deactivate");
	}
	snackbar(obj.message);
}
function deactivate(name, user_id) {
	$("#activationModal .modal-info > span").text("Dea");
	$("#activationModal .modal-info > b > span").text(name);
	$("#activationModal .modal-footer > .btn-success").attr(
			"onclick",
			"asyncProcess('Admin-Client-Activation?activate=false', " + user_id
					+ ", deactivateSuccess)");
	$("#activationModal").modal('show');
}
function deactivateSuccess(data, status) {
	var obj = JSON.parse(data);
	$("#activationModal").modal("hide");
	if (obj.response == "OK") {
		$("tr#for-" + obj.user_id + " .act").html(
				"<i class='fa fa-td fa-remove text-danger'></i>");
		var elem = $("tr#for-" + obj.user_id + " .act-link");
		var action = elem.attr("href");
		elem.attr("href", "javascript:" + action.substring(13, action.length));
		elem.html("<i class='fa fa-fw fa-check'></i> Activate");
	}
	snackbar(obj.message);
}
function kickout(name, user_id) {
	$("#kickoutModal .modal-info > b > span").text(name);
	$("#kickoutModal .modal-footer > .btn-danger").attr(
			"onclick",
			"asyncProcess('Admin-Client-Delete'," + user_id
					+ ", kickoutSuccess)");
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
function promote(fname, cid) {
	$("#promoteModal .modal-info > span#adm-act").text("Pro");
	$("#promoteModal .modal-info > span#adm-ud").text("as");
	$("#promoteModal .modal-info > b > span").text(fname);
	$("#promoteModal .modal-footer .btn-success").attr(
			"onclick",
			"asyncProcess('Admin-Client-Promote?promote=true'," + cid
					+ ", promoteSuccess)");
	$("#promoteModal").modal("show");
}
function promoteSuccess(data, status) {
	var obj = JSON.parse(data);
	$("#promoteModal").modal("hide");
	if (obj.response == "OK") {
		$("tr#for-" + obj.user_id + " .act").html(
				"<i class='fa fa-td fa-user-secret text-info'></i>");
		var elem = $("tr#for-" + obj.user_id + " .adm-link");
		var action = elem.attr("href");
		elem.attr("href", action.replace("promote", "demote"));
		elem.html("<i class='fa fa-fw fa-user-secret'></i> Demote");
	}
	snackbar(obj.message);
}
function demote(fname, cid) {
	$("#promoteModal .modal-info > span#adm-act").text("De");
	$("#promoteModal .modal-info > span#adm-ud").text("from");
	$("#promoteModal .modal-info > b > span").text(fname);
	$("#promoteModal .modal-footer .btn-success").attr(
			"onclick",
			"asyncProcess('Admin-Client-Promote?promote=false'," + cid
					+ ", demoteSuccess)");
	$("#promoteModal").modal("show");
}
function demoteSuccess(data, status) {
	var obj = JSON.parse(data);
	$("#promoteModal").modal("hide");
	if (obj.response == "OK") {
		$("tr#for-" + obj.user_id + " .act").html(
				"<i class='fa fa-td fa-check text-success'></i>");
		var elem = $("tr#for-" + obj.user_id + " .adm-link");
		var action = elem.attr("href");
		elem.attr("href", action.replace("demote", "promote"));
		elem.html("<i class='fa fa-fw fa-user-secret'></i> Promote");
	}
	snackbar(obj.message);
}
function edit(fname, lname, email, gender, cont, dp, street, town, city, state,
		uid, add_id, auth) {
	$("#clientEditModal .modal-body .user-dp-lg").attr("style",
			"background-image: url('" + dp + "');");
	$("#clientEditModal .modal-body #fname").val(fname);
	$("#clientEditModal .modal-body #lname").val(lname);
	$("#clientEditModal .modal-body #email").val(email);
	if (gender == "Male") {
		$("#clientEditModal .modal-body #g_l").removeClass("fa-venus");
		$("#clientEditModal .modal-body #g_l").addClass("fa-mars");
	} else {
		$("#clientEditModal .modal-body #g_l").removeClass("fa-mars");
		$("#clientEditModal .modal-body #g_l").addClass("fa-venus");
	}
	$("#clientEditModal .modal-body #clientEditGender" + gender).attr(
			"checked", "checked");
	$("#clientEditModal .modal-body #cont").val(cont);
	$("#clientEditModal .modal-body #editForm").attr("action",
			"Admin-Client-Update?uid=" + uid);
	// $("#clientEditModal .modal-body #editForm").attr("onsubmit",
	// "editClientAction('actions/admin/client-update.php', "+uid+")");
	$("#clientEditModal .modal-body #change-address").attr(
			"onclick",
			"changeAddress('" + street + "', '" + town + "', '" + city + "', '"
					+ state + "', " + add_id + ", 'Client')");
	$("#clientEditModal .modal-body #change-password").attr("onclick",
			"changePassword(" + uid + ")");
	$("#clientEditModal .modal-body .change-picture").attr(
			"onclick",
			"changePicture('" + dp + "', '" + fname + " " + lname + "', " + uid
					+ ", " + auth + ")");
	$("#clientEditModal").modal('show');
}
function editClientAction(url, uid) {
	// var elem = document.getElementById("editForm");
	// $.ajax({
	// type: 'POST',
	// url: url,
	// dataType: 'html',
	// async: true,
	// data: {
	// uid: uid,
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
function address(street, town, city, state, add_id, who) {
	// alert(street);
	$("#addressModal table tbody").html(
			"<tr scope='row'><td scope='col'>" + street
					+ "</td><td scope='col'>" + town + "</td><td scope='col'>"
					+ city + "</td><td scope='col'>" + state + "</td></tr>");
	$("#addressModal .modal-footer #change-address").attr(
			"onclick",
			"changeAddress('" + street + "', '" + town + "', '" + city + "', '"
					+ state + "', " + add_id + ", '" + who + "')");
	$("#addressModalLabel span").html(who);
	$("#addressModal").modal('show');
}
function changePassword(uid) {
	// $("#clientEditModal").modal('hide');
	$("#clientChangePasswordModal form").attr("action",
			"Admin-Client-Change-Password?uid=" + uid);
	$("#clientChangePasswordModal").modal('show');
}
function changePicture(dp, name, uid, auth) {
	// $("#clientEditModal").modal('hide');
	$("#clientChangePictureModal .user-dp-xl").attr("style",
			"background-image: url('" + dp + "');");
	$("#clientChangePictureModal .user-dp-xl").attr("onclick",
			"showImageModal('" + name + "', '" + dp + "')");
	$("#clientChangePictureModal form").attr(
			"action",
			"Admin-Client-Profile-Update?old_dp=" + dp + "&uid=" + uid
					+ "&auth=" + auth);
	$("#clientChangePictureModal").modal('show');
}
function clientMail(email) {
	$("#clientMailModal input#to").val(email);
	$("#clientMailModal").modal('show');
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
	var furnished = "<i class='fa fa-fw fa-td fa-";
	if (furn == 1) {
		furnished += "check text-success";
	} else {
		furnished += "remove text-danger";
	}
	furnished += "'></i>"
	$("#propFeaturesModal table tbody").html(
			"<tr><td scope='col'>" + bhk + "</td><td scope='col'>" + bath
					+ "</td><td scope='col'>" + age
					+ " Years</td><td scope='col' class='text-center'>"
					+ furnished + "</td><td scope='col'>" + hosp
					+ " KMS.</td><td scope='col'>" + school
					+ " KMS</td><td scope='col'>" + rail
					+ " KMS</td><td scope='col'>" + area
					+ " Sq-Ft.</td><td scope='col'>" + l_area
					+ " Sq-Ft.</td></tr>");
	$("#propFeaturesModal #change-prop-features").attr(
			"onclick",
			"editPropFeatures(" + bhk + "," + bath + "," + age + "," + furn
					+ "," + hosp + "," + school + "," + rail + "," + area + ","
					+ l_area + ", " + pid + ")");
	$("#propFeaturesModal").modal('show');
}
function showPropInfo(amens, units, floor, t_floors, desc, tnc, time, edit, pid) {
	var empty_a = false, empty_f = false, empty_d = false, empty_t = false;
	// Amenities
	var amenities = "";
	if (amens == "[]") {
		amenities = "<i class='fa fa-minus text-info'></i>";
		$("#propInfoModal table tbody tr td#amen").addClass("text-center");
	} else {
		var num_of_a = amens.length;
		for (var i = 0; i < num_of_a; i++) {
			amenities += "<code style='margin: 0px 5px;'>" + amens[i]
					+ "</code>";
		}
		$("#propInfoModal table tbody tr td#amen").removeClass("text-center");
	}
	$("#propInfoModal table #amen").html(amenities);
	// Floor Number
	if (floor == "-5") {
		var floorno = "<i class='fa fa-minus text-info'></i>";
		$("#propInfoModal table tbody tr td#floor").addClass("text-center");
	} else {
		var floorno = floor;
		$("#propInfoModal table tbody tr td#floor").removeClass("text-center");
	}
	$("#propInfoModal table #floor").html(floorno);
	// Brief Description
	if (desc == "NULL" || desc == "" || desc == null) {
		var descr = "<i class='fa fa-minus text-info'></i>";
		$("#propInfoModal table tbody tr td#b-desc").addClass("text-center");
	} else {
		var descr = desc;
		$("#propInfoModal table tbody tr td#b-desc").removeClass("text-center");
	}
	$("#propInfoModal table #b-desc").html(descr);
	// Terms and Condition
	if (tnc == "NULL" || tnc == "" || tnc == null) {
		var t_n_c = "<i class='fa fa-minus text-info'></i>";
		$("#propInfoModal table tbody tr td#tnc").addClass("text-center");
	} else {
		var t_n_c = tnc;
		$("#propInfoModal table tbody tr td#tnc").removeClass("text-center");
	}
	$("#propInfoModal table #tnc").html(t_n_c);
	// Edited on
	if (edit == "NULL" || edit == "" || edit == null) {
		var e_on = "<i class='fa fa-minus text-info'></i>";
		$("#propInfoModal table tbody tr td#e-on").addClass("text-center");
	} else {
		var e_on = edit;
		$("#propInfoModal table tbody tr td#e-on").removeClass("text-center");
	}
	$("#propInfoModal table #e-on").html(e_on);
	// Remaining Features
	$("#propInfoModal table #units").html(units);
	$("#propInfoModal table #t-floors").html(t_floors);
	$("#propInfoModal table #p-on").html(time);
	// Changes again
	$("#propInfoModal #change-prop-info").attr(
			"onclick",
			"editPropInfo('" + amens + "'," + units + "," + floor + ","
					+ t_floors + ",'" + desc + "','" + tnc + "'," + pid + ")");
	$("#propInfoModal").modal('show');
}
/** **** Admin end ********** */
